package com.manager.handler;

import com.manager.core.ActionContext;
import com.manager.core.AuthUser;
import com.manager.exception.DatabaseException;
import com.manager.exception.YCException;
import com.manager.pojo.manual.MenuInfoDto;
import com.manager.request.menu.MenuRequest;
import com.manager.request.menu.UpdateRoleMenuRequest;
import com.manager.response.MenuInfoResponse;
import com.manager.response.SelectedSecondMenuResponse;
import com.manager.service.MenuService;
import com.manager.utils.Validator;
import com.manager.utils.YCSystemStatusEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by shencx on 2017/3/27.
 */
@Service
public class MenuInfoHandler {

    @Resource
    private MenuService menuService;

    Logger LOG = LoggerFactory.getLogger(MenuInfoHandler.class);

    /**
     * 获取菜单列表
     * @param request
     * @return
     * @throws YCException
     */
    public List<MenuInfoResponse> getMenuInfoList(MenuRequest request) throws YCException {
        AuthUser user = ActionContext.getActionContext().currentUser();
        List<MenuInfoResponse> menus = null;
        try {
            List<MenuInfoDto> list = menuService.fetchMenuInfo(request);

            if (list != null && list.size() > 0) {
                menus = list.stream().filter(m -> m.getParentId() == null || m.getParentId().equals(0))
                        .map(m -> {
                            MenuInfoResponse menu = new MenuInfoResponse();
                            menu.setId(m.getId());
                            menu.setName(m.getMenuName());
                            menu.setCssClass(m.getMenuUrl());

                            //转换子菜单
                            List<MenuInfoResponse> subMenus = list.stream()
                                    .filter(sm -> sm.getParentId() != null && sm.getParentId() > 0 && sm.getParentId().equals(m.getId())
                                            && sm.getRoleType() != null && (sm.getRoleType() & user.getUserRole()) > 0)
                                    .map(sm -> {
                                        MenuInfoResponse subMenu = new MenuInfoResponse();
                                        subMenu.setId(sm.getId());
                                        subMenu.setName(sm.getMenuName());
                                        subMenu.setUrl(sm.getMenuUrl());
                                        return subMenu;
                                    }).distinct().collect(Collectors.toList());

                            menu.setChildNodes(subMenus);

                            return menu;
                        }).filter(m -> m.getChildNodes().size() > 0)
                        .collect(Collectors.toList());

            }
        } catch (Throwable e) {
            LOG.error("获取菜单列表异常", request);
            throw new YCException(YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getCode(), YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getDesc());
        }
        return menus;
    }

    /**
     * 获取所有二级菜单
     * @return
     * @throws YCException
     */
    public List<MenuInfoDto> getSecondMenuInfo() throws YCException {
        MenuRequest request = new MenuRequest();
        try {
            List<MenuInfoDto> list = menuService.fetchSecondMenuName(request);
            return list;
        } catch (Throwable e) {
            LOG.error("获取二级菜单列表异常");
            throw new YCException(YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getCode(), YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getDesc());
        }
    }

    /**
     * 根据角色ID获取菜单
     * @param roleId
     * @return
     * @throws YCException
     */
    public SelectedSecondMenuResponse getSelectedMenu(Integer roleId) throws YCException {
        SelectedSecondMenuResponse selectedSecondMenuResponse = new SelectedSecondMenuResponse();
        List<Integer> list = new ArrayList<>();
        MenuRequest request = new MenuRequest();
        try {
            List<MenuInfoDto> menuInfoDtos = menuService.fetchSecondMenuName(request);
            if (menuInfoDtos != null && menuInfoDtos.size() > 0){
                for (MenuInfoDto menuInfoDto : menuInfoDtos){
                    //传入的角色ID与获取的ID与运算
                    Integer role = roleId & menuInfoDto.getRoleType();
                    if (role > 0){
                        list.add(menuInfoDto.getMenuInfoId());
                    }
                }
            }
            selectedSecondMenuResponse.setMenuInfoId(list);
            return selectedSecondMenuResponse;
        }  catch (Throwable e) {
            LOG.error("根据角色ID获取二级菜单异常",roleId);
            throw new YCException(YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getCode(), YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getDesc());
        }
    }


    /**
     * 批量更新
     * @param roleType
     * @param menuInfoIds
     * @throws YCException
     */
    public void updateBatchInfo(Integer roleType, String menuInfoIds) throws YCException {
        /** 参数校验 */
        Validator.isEmpty(roleType,"角色类型不能为空");
        Validator.isEmpty(menuInfoIds,"菜单不能为空");
        List<Integer> list = new ArrayList<>();
        String[] idsArr = menuInfoIds.split(",");
        for (String ids : idsArr){
            list.add(Integer.valueOf(ids));
        }

        try {
            //清除所有包含此角色值的菜单
            List<UpdateRoleMenuRequest> updateRoleMenuRequests1 = new ArrayList<>();
            MenuRequest request1 = new MenuRequest();
            List<MenuInfoDto> menuInfoDtoList =menuService.fetchSecondMenuName(request1);
            if (menuInfoDtoList != null && menuInfoDtoList.size() > 0){
                for (MenuInfoDto menuInfoDto : menuInfoDtoList){
                    UpdateRoleMenuRequest updateRoleMenuRequest = new UpdateRoleMenuRequest();
                    Integer role_type = roleType & menuInfoDto.getRoleType();
                    //判断是否包含此角色值，若包含则异或清除，否则不操作
                    if (role_type > 0){
                        Integer containRole =  roleType^menuInfoDto.getRoleType();
                        updateRoleMenuRequest.setRoleType(containRole);
                    }else {
                        updateRoleMenuRequest.setRoleType(menuInfoDto.getRoleType());
                    }
                    updateRoleMenuRequest.setMenuInfoId(menuInfoDto.getMenuInfoId());
                    updateRoleMenuRequests1.add(updateRoleMenuRequest);
                }
            }
            menuService.batchUpdateRoleMenuInfo(updateRoleMenuRequests1);

            //添加此角色值选中的菜单
            List<UpdateRoleMenuRequest> updateRoleMenuRequests = new ArrayList<>();
            MenuRequest request = new MenuRequest();
            request.setMenuInfoIds(list);
            List<MenuInfoDto> menuInfoDtos = menuService.fetchSecondMenuName(request);
            if (menuInfoDtos != null && menuInfoDtos.size() > 0){
                for (MenuInfoDto menuInfoDto : menuInfoDtos){
                    UpdateRoleMenuRequest updateRoleMenuRequest = new UpdateRoleMenuRequest();
                    updateRoleMenuRequest.setMenuInfoId(menuInfoDto.getMenuInfoId());
                    roleType = roleType | menuInfoDto.getRoleType();
                    updateRoleMenuRequest.setRoleType(roleType);
                    updateRoleMenuRequests.add(updateRoleMenuRequest);
                }
            }
            menuService.batchUpdateRoleMenuInfo(updateRoleMenuRequests);
        }  catch (Throwable e) {
            LOG.error("批量更新角色值异常",roleType);
            throw new YCException(YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getCode(), YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getDesc());
        }
    }
}

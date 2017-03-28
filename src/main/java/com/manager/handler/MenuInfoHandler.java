package com.manager.handler;

import com.manager.core.ActionContext;
import com.manager.core.AuthUser;
import com.manager.exception.DatabaseException;
import com.manager.exception.YCException;
import com.manager.pojo.manual.MenuInfoDto;
import com.manager.request.menu.MenuRequest;
import com.manager.response.MenuInfoResponse;
import com.manager.service.IBusinessStatusEnum;
import com.manager.service.MenuService;
import com.manager.utils.BusinessStatusEnum;
import com.manager.utils.YCSystemStatusEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
}

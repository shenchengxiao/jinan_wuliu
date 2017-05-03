package com.manager.controller;

import com.manager.exception.YCException;
import com.manager.handler.MenuInfoHandler;
import com.manager.pojo.manual.MenuInfoDto;
import com.manager.request.menu.MenuRequest;
import com.manager.response.MenuInfoResponse;
import com.manager.response.SelectedSecondMenuResponse;
import com.manager.utils.APIResponse;
import com.manager.utils.YCSystemStatusEnum;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by shencx on 2017/3/27.
 */
@Controller
@RequestMapping(value = "/api/menu")
public class MenuController {

    @Resource
    private MenuInfoHandler menuInfoHandler;

    @ResponseBody
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public APIResponse<List<MenuInfoResponse>> list(HttpServletRequest request, MenuRequest menuRequest){
        APIResponse<List<MenuInfoResponse>> apiResponse = new APIResponse<>();
        try {
            List<MenuInfoResponse> list = menuInfoHandler.getMenuInfoList(menuRequest);
            apiResponse.setData(list);
            apiResponse.setStatus(YCSystemStatusEnum.SUCCESS.getCode());
            apiResponse.setMsg(YCSystemStatusEnum.SUCCESS.getDesc());
        } catch (YCException e) {
            apiResponse.setStatus(YCSystemStatusEnum.SYSTEM_ERROR.getCode());
            apiResponse.setMsg(YCSystemStatusEnum.SYSTEM_ERROR.getDesc());
        }
        return apiResponse;
    }


    /**
     * 获取二级菜单
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/second_menu_list",method = RequestMethod.GET)
    public APIResponse<List<MenuInfoDto>> secondMenuList(HttpServletRequest request){
        APIResponse<List<MenuInfoDto>> apiResponse = new APIResponse<>();
        try {
            List<MenuInfoDto> list = menuInfoHandler.getSecondMenuInfo();
            apiResponse.setData(list);
            apiResponse.setStatus(YCSystemStatusEnum.SUCCESS.getCode());
            apiResponse.setMsg(YCSystemStatusEnum.SUCCESS.getDesc());
        } catch (YCException e) {
            apiResponse.setStatus(YCSystemStatusEnum.SYSTEM_ERROR.getCode());
            apiResponse.setMsg(YCSystemStatusEnum.SYSTEM_ERROR.getDesc());
        }
        return apiResponse;
    }

    /**
     * 获取选中的菜单
     * @param request
     * @param roleId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/selected_menu",method = RequestMethod.GET)
    public APIResponse<SelectedSecondMenuResponse> selectedMenu(HttpServletRequest request, Integer roleId){
        APIResponse<SelectedSecondMenuResponse> apiResponse = new APIResponse<>();
        try {
            SelectedSecondMenuResponse selectedSecondMenuResponse = menuInfoHandler.getSelectedMenu(roleId);
            apiResponse.setData(selectedSecondMenuResponse);
            apiResponse.setStatus(YCSystemStatusEnum.SUCCESS.getCode());
            apiResponse.setMsg(YCSystemStatusEnum.SUCCESS.getDesc());
        } catch (YCException e) {
            apiResponse.setStatus(YCSystemStatusEnum.SYSTEM_ERROR.getCode());
            apiResponse.setMsg(YCSystemStatusEnum.SYSTEM_ERROR.getDesc());
        }
        return apiResponse;
    }

    /**
     * 批量更新
     * @param request
     * @param roleType
     * @param menuInfoIds
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/batch_update",method = RequestMethod.POST)
    public APIResponse batchUpdate(HttpServletRequest request,Integer roleType, String menuInfoIds){
        APIResponse apiResponse = new APIResponse<>();
        try {
            menuInfoHandler.updateBatchInfo(roleType,menuInfoIds);
            apiResponse.setStatus(YCSystemStatusEnum.SUCCESS.getCode());
            apiResponse.setMsg(YCSystemStatusEnum.SUCCESS.getDesc());
        } catch (YCException e) {
            apiResponse.setStatus(YCSystemStatusEnum.SYSTEM_ERROR.getCode());
            apiResponse.setMsg(YCSystemStatusEnum.SYSTEM_ERROR.getDesc());
        }
        return apiResponse;
    }
}

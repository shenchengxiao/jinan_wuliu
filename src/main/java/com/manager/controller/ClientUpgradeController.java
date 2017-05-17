package com.manager.controller;

import com.manager.annotations.Authentication;
import com.manager.enums.UserRoleEnum;
import com.manager.handler.ClientUpgradeHandler;
import com.manager.pojo.ClientUpgradeInfo;
import com.manager.request.clientupgrade.ClientUpgradeRequest;
import com.manager.response.ClientUpgradeResponse;
import com.manager.utils.APIResponse;
import com.manager.utils.Page;
import com.manager.utils.YCSystemStatusEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by shencx on 2017/4/14.
 */
@Controller
@RequestMapping(value = "/api/upgrade")
//@Authentication(allow = UserRoleEnum.SuperAdmin)
public class ClientUpgradeController {

    Logger LOG = LoggerFactory.getLogger(ClientUpgradeController.class);

    @Resource
    private ClientUpgradeHandler clientUpgradeHandler;

    /**
     * 添加修改
     * @param request
     * @param clientUpgradeRequest
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    public APIResponse edit(HttpServletRequest request, ClientUpgradeRequest clientUpgradeRequest){
        APIResponse apiResponse = new APIResponse();
        try {
            clientUpgradeHandler.addClientUpgrade(clientUpgradeRequest);
            apiResponse.setStatus(YCSystemStatusEnum.SUCCESS.getCode());
            apiResponse.setMsg(YCSystemStatusEnum.SUCCESS.getDesc());
        } catch (Throwable e) {
            LOG.error("添加修改版本信息 发生异常",clientUpgradeRequest);
            apiResponse.setStatus(YCSystemStatusEnum.SYSTEM_ERROR.getCode());
            apiResponse.setMsg(YCSystemStatusEnum.SYSTEM_ERROR.getDesc());
        }
        return apiResponse;
    }

    /**
     * 获取版本信息列表
     * @param request
     * @param clientUpgradeRequest
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public APIResponse<Page<ClientUpgradeInfo>> list(HttpServletRequest request, ClientUpgradeRequest clientUpgradeRequest){
        APIResponse<Page<ClientUpgradeInfo>> apiResponse = new APIResponse();
        Page<ClientUpgradeInfo> page = null;
        try {
            page = clientUpgradeHandler.getUpgradeInfoPage(clientUpgradeRequest);
            apiResponse.setStatus(YCSystemStatusEnum.SUCCESS.getCode());
            apiResponse.setMsg(YCSystemStatusEnum.SUCCESS.getDesc());
            apiResponse.setData(page);
        } catch (Throwable e) {
            LOG.error("获取版本信息列表 发生异常",clientUpgradeRequest);
            apiResponse.setStatus(YCSystemStatusEnum.SYSTEM_ERROR.getCode());
            apiResponse.setMsg(YCSystemStatusEnum.SYSTEM_ERROR.getDesc());
        }
        return apiResponse;
    }

    /**
     * 获取详情
     * @param request
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/detail",method = RequestMethod.GET)
    public APIResponse<ClientUpgradeResponse> detail(HttpServletRequest request, Integer id){
        APIResponse<ClientUpgradeResponse> apiResponse = new APIResponse();
        ClientUpgradeResponse clientUpgradeResponse = null;
        try {
            clientUpgradeResponse = clientUpgradeHandler.getUpgradeInfoDetail(id);
            apiResponse.setStatus(YCSystemStatusEnum.SUCCESS.getCode());
            apiResponse.setMsg(YCSystemStatusEnum.SUCCESS.getDesc());
            apiResponse.setData(clientUpgradeResponse);
        } catch (Throwable e) {
            LOG.error("获取版本信息详情 发生异常",id);
            apiResponse.setStatus(YCSystemStatusEnum.SYSTEM_ERROR.getCode());
            apiResponse.setMsg(YCSystemStatusEnum.SYSTEM_ERROR.getDesc());
        }
        return apiResponse;
    }

}

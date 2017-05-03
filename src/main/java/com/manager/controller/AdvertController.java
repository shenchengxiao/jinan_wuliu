package com.manager.controller;
import com.manager.annotations.Authentication;
import com.manager.enums.UserRoleEnum;
import com.manager.exception.YCException;
import com.manager.handler.AdvertHandler;
import com.manager.pojo.Advert;
import com.manager.request.advert.AdvertInfoRequest;
import com.manager.response.AdvertContentResponse;
import com.manager.response.AdvertInfoResponse;
import com.manager.utils.APIResponse;
import com.manager.utils.Page;
import com.manager.utils.YCSystemStatusEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by shencx on 2017/3/30.
 */
@Controller
@RequestMapping(value = "/api/advert")
//@Authentication(allow = UserRoleEnum.SuperAdmin)
public class AdvertController {

    @Resource
    private AdvertHandler advertHandler;

    Logger LOG = LoggerFactory.getLogger(AdvertController.class);

    /**
     * 添加/修改
     * @param request
     * @param advertInfoRequest
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/editor",method = RequestMethod.POST)
    public APIResponse editor(HttpServletRequest request , AdvertInfoRequest advertInfoRequest){
        APIResponse apiResponse = new APIResponse<>();
        try {
            advertHandler.addAdvertInfo(advertInfoRequest);
            apiResponse.setStatus(YCSystemStatusEnum.SUCCESS.getCode());
            apiResponse.setMsg(YCSystemStatusEnum.SUCCESS.getDesc());
        } catch (Throwable e) {
            LOG.error("添加广告信息发生异常",advertInfoRequest);
            apiResponse.setStatus(YCSystemStatusEnum.SYSTEM_ERROR.getCode());
            apiResponse.setMsg(YCSystemStatusEnum.SYSTEM_ERROR.getDesc());
        }
        return apiResponse;
    }

    /**
     * 获取广告信息列表
     * @param request
     * @param advertInfoRequest
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public APIResponse<Page<AdvertInfoResponse>> list(HttpServletRequest request , AdvertInfoRequest advertInfoRequest){
        APIResponse<Page<AdvertInfoResponse>> apiResponse = new APIResponse<>();
        Page<AdvertInfoResponse> page = null;
        try {
            page = advertHandler.getAdvertInfoList(advertInfoRequest);
            apiResponse.setStatus(YCSystemStatusEnum.SUCCESS.getCode());
            apiResponse.setMsg(YCSystemStatusEnum.SUCCESS.getDesc());
            apiResponse.setData(page);
        } catch (Throwable e) {
            LOG.error("获取广告信息列表发生异常",advertInfoRequest);
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
    public APIResponse<AdvertInfoResponse> detail(HttpServletRequest request , Integer id){
        APIResponse<AdvertInfoResponse> apiResponse = new APIResponse<>();
        AdvertInfoResponse advertInfoResponse = null;
        try {
            advertInfoResponse = advertHandler.getAdvertDetail(id);
            apiResponse.setStatus(YCSystemStatusEnum.SUCCESS.getCode());
            apiResponse.setMsg(YCSystemStatusEnum.SUCCESS.getDesc());
            apiResponse.setData(advertInfoResponse);
        } catch (Throwable e) {
            LOG.error("获取广告详情信息发生异常",id);
            apiResponse.setStatus(YCSystemStatusEnum.SYSTEM_ERROR.getCode());
            apiResponse.setMsg(YCSystemStatusEnum.SYSTEM_ERROR.getDesc());
        }
        return apiResponse;
    }

    /**
     * 删除
     * @param request
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public APIResponse delete(HttpServletRequest request , Integer id){
        APIResponse apiResponse = new APIResponse<>();
        try {
            advertHandler.deleteAdvertInfo(id);
            apiResponse.setStatus(YCSystemStatusEnum.SUCCESS.getCode());
            apiResponse.setMsg(YCSystemStatusEnum.SUCCESS.getDesc());
        } catch (Throwable e) {
            LOG.error("删除广告信息发生异常",id);
            apiResponse.setStatus(YCSystemStatusEnum.SYSTEM_ERROR.getCode());
            apiResponse.setMsg(YCSystemStatusEnum.SYSTEM_ERROR.getDesc());
        }
        return apiResponse;
    }

    /**
     * 更新广告状态
     * @param request
     * @param id
     * @param status
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/modify",method = RequestMethod.POST)
    public APIResponse modify(HttpServletRequest request ,Integer id,Integer status){
        APIResponse apiResponse = new APIResponse<>();
        try {
            advertHandler.modifyStatus(id,status);
            apiResponse.setStatus(YCSystemStatusEnum.SUCCESS.getCode());
            apiResponse.setMsg(YCSystemStatusEnum.SUCCESS.getDesc());
        } catch (Throwable e) {
            LOG.error("更新广告状态发生异常",id);
            apiResponse.setStatus(YCSystemStatusEnum.SYSTEM_ERROR.getCode());
            apiResponse.setMsg(YCSystemStatusEnum.SYSTEM_ERROR.getDesc());
        }
        return apiResponse;
    }

    /**
     * 获取广告内容（客户端）
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/pc_advert",method = RequestMethod.GET)
    public APIResponse<List<AdvertContentResponse>> pcAdvert(HttpServletRequest request){
        APIResponse apiResponse = new APIResponse<>();
        try {
            List<AdvertContentResponse> list = advertHandler.fetchContent();
            apiResponse.setStatus(YCSystemStatusEnum.SUCCESS.getCode());
            apiResponse.setMsg(YCSystemStatusEnum.SUCCESS.getDesc());
            apiResponse.setData(list);
        } catch (Throwable e) {
            LOG.error("获取所有广告内容发生异常",request);
            apiResponse.setStatus(YCSystemStatusEnum.SYSTEM_ERROR.getCode());
            apiResponse.setMsg(YCSystemStatusEnum.SYSTEM_ERROR.getDesc());
        }
        return apiResponse;
    }

}

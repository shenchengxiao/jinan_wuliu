package com.manager.handler;

import com.manager.enums.YesNoEnum;
import com.manager.exception.DatabaseException;
import com.manager.exception.ValidationException;
import com.manager.exception.YCException;
import com.manager.pojo.Advert;
import com.manager.request.advert.AdvertInfoRequest;
import com.manager.response.AdvertInfoResponse;
import com.manager.service.AdvertService;
import com.manager.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by shencx on 2017/3/30.
 */
@Service
public class AdvertHandler {


    @Resource
    private AdvertService advertService;

    Logger LOG = LoggerFactory.getLogger(AdvertHandler.class);

    /**
     * 编辑广告信息
     * @param request
     * @throws YCException
     */
    public void addAdvertInfo(AdvertInfoRequest request) throws YCException {
        /** 参数校验 */
        Validator.isEmpty(request,YCSystemStatusEnum.PARAM_EMPTY);
        Validator.isEmpty(request.getStartTime(),"开始时间不能为空");
        Validator.isEmpty(request.getEndTime(),"结束时间不能为空");
        Validator.isEmpty(request.getLinkedName(),"联系人不能为空");
        Validator.isEmpty(request.getPhoneNumber(),"联系电话不能为空");
        Validator.isEmpty(request.getBeUsed(),"广告状态不能为空");
        Validator.isEmpty(request.getContent(),"广告内容不能为空");
        Validator.isEmpty(request.getPrice(),"价格不能为空");
        Validator.isEmpty(request.getLinkUrl(),"连接地址不能为空");


        Advert advert = new Advert();
        advert.setId(request.getId());
        advert.setStartTime(DateTimeUtil.convertDate(request.getStartTime()));
        advert.setStopTime(DateTimeUtil.convertDate(request.getEndTime()+" 23:59:59"));
        advert.setPrice(request.getPrice());
        advert.setLinkman(request.getLinkedName());
        advert.setLinkphone(request.getPhoneNumber());
        advert.setContent(request.getContent());
        advert.setStatus(YesNoEnum.create(request.getBeUsed()));
        advert.setLinkUrl(request.getLinkUrl());
        advert.setAdvertTitle(request.getAdvertTitle());
        try {
            //id为空则添加，否则为修改
            if (advert.getId() == null){
                advertService.addAdvertInfo(advert);
            }else {
                advertService.updateAdvert(advert);
            }
        }catch (DatabaseException e) {
            LOG.error("addAdvertInfo exception",advert);
            throw new YCException(YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getCode(), YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getDesc());
        }
    }

    /**
     * 获取广告信息列表
     * @param advertInfoRequest
     * @return
     * @throws YCException
     */
    public Page<AdvertInfoResponse> getAdvertInfoList(AdvertInfoRequest advertInfoRequest) throws YCException {
        try {
            Page<Advert> advertPage = advertService.fetchAdvertList(advertInfoRequest);

            List<AdvertInfoResponse> list = new ArrayList<>();

            if (advertPage != null) {
                for (Advert advert : advertPage.getResult()){
                    //进行对象转换
                    list.add(getAdvertInfoConvert(advert));
                }
            }

            Page<AdvertInfoResponse> page = new Page<>();
            page.setPagesize(advertPage.getPagesize());
            page.setPageindex(advertPage.getPageindex());
            page.setTotal(advertPage.getTotal());
            page.setResult(list);
            return page;
        } catch (DatabaseException e) {
            LOG.error("getAdvertInfoList exception",advertInfoRequest);
            throw new YCException(YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getCode(), YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getDesc());
        }
    }

    /**
     * 获取广告详情信息
     * @param id
     * @return
     * @throws YCException
     */
    public AdvertInfoResponse getAdvertDetail(Integer id) throws YCException {
        /** 参数校验 */
        Validator.isEmpty(id,"广告主键ID不能为空");
        try {
            Advert advert = advertService.fetchAdvertDetail(id);
            AdvertInfoResponse advertInfoResponse = getAdvertInfoConvert(advert);
            return advertInfoResponse;
        } catch (DatabaseException e) {
            LOG.error("getAdvertDetail exception",id);
            throw new YCException(YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getCode(), YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getDesc());
        }
    }

    /**
     * 删除
     * @param id
     * @throws YCException
     */
    public void deleteAdvertInfo(Integer id) throws YCException {
        /** 参数校验 */
        Validator.isEmpty(id,"广告主键ID不能为空");
        try {
            advertService.deleteAdvert(id);
        } catch (DatabaseException e) {
            LOG.error("deleteAdvertInfo exception",id);
            throw new YCException(YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getCode(), YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getDesc());
        }
    }

    /**
     * 更新状态
     * @param id
     * @param status
     * @throws YCException
     */
    public void modifyStatus(Integer id,Integer status) throws YCException {
        /** 参数校验 */
        Validator.isEmpty(id,"广告主键ID不能为空");
        Validator.isEmpty(status,"广告状态不能为空");
        try {
            advertService.modifyStatus(id,status);
        } catch (DatabaseException e) {
            LOG.error("modifyStatus exception",id);
            throw new YCException(YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getCode(), YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getDesc());
        }
    }

    /**
     * 广告信息对象转换
     * @param advert
     * @return
     */
    private static AdvertInfoResponse getAdvertInfoConvert(Advert advert){
        AdvertInfoResponse advertInfoResponse = new AdvertInfoResponse();
        advertInfoResponse.setId(advert.getId());
        advertInfoResponse.setStartTime(DateTimeUtil.formatDate(advert.getStartTime()));
        advertInfoResponse.setEndTime(DateTimeUtil.formatDate(advert.getStopTime()));
        advertInfoResponse.setPrice(advert.getPrice());
        advertInfoResponse.setLinkedName(advert.getLinkman());
        advertInfoResponse.setPhoneNumber(advert.getLinkphone());
        advertInfoResponse.setContent(advert.getContent());
        advertInfoResponse.setBeUsed(advert.getStatus().getValue());
        advertInfoResponse.setLinkUrl(advert.getLinkUrl());
        advertInfoResponse.setAdvertTitle(advert.getAdvertTitle());
        return advertInfoResponse;
    }
}

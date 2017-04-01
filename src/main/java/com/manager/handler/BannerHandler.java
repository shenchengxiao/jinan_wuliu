package com.manager.handler;

import com.manager.exception.DatabaseException;
import com.manager.exception.ValidationException;
import com.manager.exception.YCException;
import com.manager.pojo.BannerInfo;
import com.manager.request.BaseQuery;
import com.manager.response.BannerInfoResponse;
import com.manager.service.BannerService;
import com.manager.utils.Page;
import com.manager.utils.Validator;
import com.manager.utils.YCSystemStatusEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by shencx on 2017/4/1.
 */
@Service
public class BannerHandler {

    @Resource
    private BannerService bannerService;


    Logger LOG = LoggerFactory.getLogger(BannerHandler.class);

    /**
     * 添加 修改
     * @param bannerInfo
     * @param imageFile
     * @param request
     * @throws YCException
     * @throws IOException
     */
    public void addBanner(BannerInfo bannerInfo,File imageFile, HttpServletRequest request) throws YCException, IOException {
        /** 参数校验 */
        Validator.isEmpty(bannerInfo, YCSystemStatusEnum.PARAM_EMPTY);
        Validator.isEmpty(bannerInfo.getBannerName(),"图片名称不能为空");
        Validator.isEmpty(bannerInfo.getImageUrl(),"图片地址不能为空");

        //获取文件上传的名称
//        String image_url = uploadFile(imageFile,request);
//        bannerInfo.setImageUrl(image_url);

        try {
            if (bannerInfo == null) {
                bannerService.addBannerInfo(bannerInfo);
            }else {
                bannerService.updateBannerInfo(bannerInfo);
            }
        } catch (DatabaseException e) {
            LOG.error("addBanner exception",bannerInfo);
            throw new YCException(YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getCode(), YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getDesc());
        }

    }

    /**
     * 获取banner列表
     * @param baseQuery
     * @return
     * @throws YCException
     */
    public Page<BannerInfoResponse> getBannerList(BaseQuery baseQuery) throws YCException {
        try {
            Page<BannerInfo> bannerInfoPage = bannerService.fetchBannerInfoList(baseQuery);
            List<BannerInfoResponse> list = new ArrayList<>();
            if (bannerInfoPage.getResult() != null){
                for (BannerInfo bannerInfo : bannerInfoPage.getResult()){
                    list.add(fetchBannerInfoConvert(bannerInfo));
                }
            }
            Page<BannerInfoResponse> page = new Page<>();
            page.setPageindex(bannerInfoPage.getPageindex());
            page.setPagesize(bannerInfoPage.getPagesize());
            page.setTotal(bannerInfoPage.getTotal());
            page.setResult(list);
            return page;
        } catch (DatabaseException e) {
            LOG.error("getBannerList exception",baseQuery);
            throw new YCException(YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getCode(), YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getDesc());
        }
    }

    /**
     * banner对象转换
     * @param bannerInfo
     * @return
     */
    private BannerInfoResponse fetchBannerInfoConvert(BannerInfo bannerInfo){
        BannerInfoResponse bannerInfoResponse = new BannerInfoResponse();
        bannerInfoResponse.setId(bannerInfo.getId());
        bannerInfoResponse.setBannerName(bannerInfo.getBannerName());
        bannerInfoResponse.setImageUrl(bannerInfo.getImageUrl());
        bannerInfoResponse.setStatus(bannerInfo.getBeUsed().getValue());
        return bannerInfoResponse;
    }

}

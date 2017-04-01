package com.manager.service;

import com.manager.exception.DatabaseException;
import com.manager.pojo.BannerInfo;
import com.manager.request.BaseQuery;
import com.manager.utils.Page;

/**
 * Created by shencx on 2017/4/1.
 */
public interface BannerService {

    Integer addBannerInfo(BannerInfo bannerInfo) throws DatabaseException;

    boolean deleteBannerInfo(Integer id) throws DatabaseException;

    boolean updateBannerInfo(BannerInfo bannerInfo) throws DatabaseException;

    Page<BannerInfo> fetchBannerInfoList(BaseQuery baseQuery) throws DatabaseException;

    BannerInfo fetchBannerInfoDetail(Integer id) throws DatabaseException;

    boolean modifyStatus(Integer id,Integer status) throws DatabaseException;
}

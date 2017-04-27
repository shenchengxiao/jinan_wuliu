package com.manager.service;

import com.manager.exception.DatabaseException;
import com.manager.pojo.Advert;
import com.manager.request.BaseQuery;
import com.manager.request.advert.AdvertInfoRequest;
import com.manager.utils.Page;

import java.util.List;

/**
 * Created by shencx on 2017/3/31.
 */
public interface AdvertService {

    Page<Advert> fetchAdvertList(AdvertInfoRequest advertInfoRequest) throws DatabaseException;

    Integer addAdvertInfo(Advert advert) throws DatabaseException;

    Advert fetchAdvertDetail(Integer id) throws DatabaseException;

    boolean deleteAdvert(Integer id) throws DatabaseException;

    boolean updateAdvert(Advert advert) throws DatabaseException;

    boolean modifyStatus(Integer id,Integer status) throws DatabaseException;

    List<Advert> fetchAllContent(AdvertInfoRequest advertInfoRequest) throws DatabaseException;
}

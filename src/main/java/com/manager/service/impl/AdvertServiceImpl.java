package com.manager.service.impl;

import com.manager.enums.YesNoEnum;
import com.manager.exception.DatabaseException;
import com.manager.interceptor.PageMybatisInterceptor;
import com.manager.mapper.AdvertMapper;
import com.manager.pojo.Advert;
import com.manager.pojo.AdvertExample;
import com.manager.request.advert.AdvertInfoRequest;
import com.manager.service.AdvertService;
import com.manager.utils.Page;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by shencx on 2017/3/31.
 */
@Service
public class AdvertServiceImpl implements AdvertService{

    Logger LOG = LoggerFactory.getLogger(AdvertServiceImpl.class);

    @Resource
    private AdvertMapper advertMapper;

    /**
     * 获取广告列表
     * @param advertInfoRequest
     * @return
     * @throws DatabaseException
     */
    @Override
    public Page<Advert> fetchAdvertList(AdvertInfoRequest advertInfoRequest) throws DatabaseException {
        try {
            if (advertInfoRequest == null){
                LOG.error("fetchAdvertList 信息为空",advertInfoRequest);
                return null;
            }
            AdvertExample example = new AdvertExample();
            AdvertExample.Criteria criteria = example.createCriteria();
            //拼接查询条件，模糊查询
            if (StringUtils.isNotBlank(advertInfoRequest.getLinkedName())){
                StringBuffer sb = new StringBuffer();
                String linkedName = advertInfoRequest.getLinkedName();
                sb.append("%");
                sb.append(linkedName);
                sb.append("%");
                criteria.andLinkmanLike(sb.toString());
            }
            if (advertInfoRequest.getPrice() != null){
                criteria.andPriceEqualTo(advertInfoRequest.getPrice());
            }
            if (StringUtils.isNoneBlank(advertInfoRequest.getPhoneNumber())){
                criteria.andLinkphoneEqualTo(advertInfoRequest.getPhoneNumber());
            }
            if (advertInfoRequest.getBeUsed() != null ){
                criteria.andStatusEqualTo(YesNoEnum.create(advertInfoRequest.getBeUsed()));
            }
            example.setOrderByClause("create_time desc");
            //分页开始
            PageMybatisInterceptor.startPage(advertInfoRequest.getPageNum(),advertInfoRequest.getPageSize());
            advertMapper.selectByExample(example);
            Page<Advert> page = PageMybatisInterceptor.endPage();
            return page;
        } catch (Throwable e) {
            LOG.error("fetchAdvertList 异常",advertInfoRequest);
            throw new DatabaseException(e.getMessage());
        }
    }

    /**
     * 添加广告信息
     * @param advert
     * @return
     * @throws DatabaseException
     */
    @Override
    public Integer addAdvertInfo(Advert advert) throws DatabaseException {
        try {
            if (advert == null){
                LOG.error("addAdvertInfo 信息为空",advert);
                return Integer.valueOf(0);
            }
            advert.setCreateTime(new Date());
            int val = advertMapper.insert(advert);
            if (val>0){
                return advert.getId();
            }else {
                return Integer.valueOf(0);
            }
        } catch (Throwable e) {
            LOG.error("addAdvertInfo 异常",advert);
            throw new DatabaseException(e.getMessage());
        }
    }

    /**
     * 根据主键ID获取详情信息
     * @param id
     * @return
     * @throws DatabaseException
     */
    @Override
    public Advert fetchAdvertDetail(Integer id) throws DatabaseException {
        try {
            if (id == null){
                LOG.error("id 为空",id);
                return null;
            }
            Advert advert = advertMapper.selectByPrimaryKey(id);
            return advert;
        } catch (Throwable e) {
            LOG.error("fetchAdvertDetail 异常",id);
            throw new DatabaseException(e.getMessage());
        }
    }

    /**
     * 删除广告信息（物理删）
     * @param id
     * @return
     * @throws DatabaseException
     */
    @Override
    public boolean deleteAdvert(Integer id) throws DatabaseException {
        try {
            if (id == null){
                LOG.error("id 为空",id);
                return false;
            }
            int val = advertMapper.deleteByPrimaryKey(id);
            return val>0?true:false;
        } catch (Throwable e) {
            LOG.error("deleteAdvert 异常",id);
            throw new DatabaseException(e.getMessage());
        }
    }

    /**
     * 更新广告信息
     * @param advert
     * @return
     */
    @Override
    public boolean updateAdvert(Advert advert) throws DatabaseException {
        try {
            if (advert == null){
                LOG.error("updateAdvert 信息为空",advert);
                return false;
            }
            int val = advertMapper.updateByPrimaryKeySelective(advert);
            return val>0?true:false;
        } catch (Throwable e) {
            LOG.error("updateAdvert 异常",advert);
            throw new DatabaseException(e.getMessage());
        }
    }

    /**
     * 更新广告状态
     * @param id
     * @param status
     * @return
     * @throws DatabaseException
     */
    @Override
    public boolean modifyStatus(Integer id, Integer status) throws DatabaseException {
        try {
            if (id == null){
                LOG.error("id 为空",id);
                return false;
            }
            if (status == null){
                LOG.error("status 为空",status);
                return false;
            }
            Advert record = new Advert();
            record.setId(id);
            record.setStatus(YesNoEnum.create(status));
            AdvertExample example = new AdvertExample();
            AdvertExample.Criteria criteria = example.createCriteria();
            criteria.andIdEqualTo(id);
            int val = advertMapper.updateByExampleSelective(record,example);
            return val>0?true:false;
        } catch (Throwable e) {
            LOG.error("modifyStatus 异常",id);
            throw new DatabaseException(e.getMessage());
        }
    }
}

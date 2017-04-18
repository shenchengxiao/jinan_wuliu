package com.manager.service.impl;

import com.manager.enums.YesNoEnum;
import com.manager.exception.DatabaseException;
import com.manager.interceptor.PageMybatisInterceptor;
import com.manager.mapper.BannerInfoMapper;
import com.manager.pojo.BannerInfo;
import com.manager.pojo.BannerInfoExample;
import com.manager.request.BaseQuery;
import com.manager.service.BannerService;
import com.manager.utils.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by shencx on 2017/4/1.
 */
@Service
public class BannerServiceImpl implements BannerService{

    @Resource
    private BannerInfoMapper bannerInfoMapper;

    Logger LOG = LoggerFactory.getLogger(BannerServiceImpl.class);

    /**
     * 添加banner信息
     * @param bannerInfo
     * @return
     * @throws DatabaseException
     */
    @Override
    public Integer addBannerInfo(BannerInfo bannerInfo) throws DatabaseException {
        try {
            if (bannerInfo == null){
                LOG.error("addBannerInfo 信息为空",bannerInfo);
                return Integer.valueOf(0);
            }
            bannerInfo.setBeUsed(YesNoEnum.YES);
            bannerInfo.setCreateTime(new Date());
            //暂时默认为0
            bannerInfo.setSort(0);
            int val = bannerInfoMapper.insert(bannerInfo);
            if (val>0){
                return bannerInfo.getId();
            }else {
                return Integer.valueOf(0);
            }
        } catch (Throwable e) {
           LOG.error("addBannerInfo 异常",bannerInfo);
           throw new DatabaseException(e.getMessage());
        }
    }

    /**
     * 删除
     * @param id
     * @return
     * @throws DatabaseException
     */
    @Override
    public boolean deleteBannerInfo(Integer id) throws DatabaseException {
        try {
            if (id == null){
                LOG.error("deleteBannerInfo id为空",id);
                return false;
            }
            Integer val = bannerInfoMapper.deleteByPrimaryKey(id);
            return (val>0)?true:false;
        } catch (Throwable e) {
            LOG.error("deleteBannerInfo 异常",id);
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public boolean updateBannerInfo(BannerInfo bannerInfo) throws DatabaseException {
        try {
            if (bannerInfo == null){
                LOG.error("updateBannerInfo 为空",bannerInfo);
                return false;
            }
            Integer val = bannerInfoMapper.updateByPrimaryKeySelective(bannerInfo);
            return (val>0)?true:false;
        } catch (Throwable e) {
            LOG.error("updateBannerInfo 异常",bannerInfo);
            throw new DatabaseException(e.getMessage());
        }
    }

    /**
     * 获取列表
     * @param baseQuery
     * @return
     */
    @Override
    public Page<BannerInfo> fetchBannerInfoList(BaseQuery baseQuery) throws DatabaseException {
        try {
            if (baseQuery == null){
                LOG.error("fetchBannerInfoList 信息为空",baseQuery);
                return null;
            }
            BannerInfoExample example = new BannerInfoExample();
            BannerInfoExample.Criteria criteria = example.createCriteria();
            example.setOrderByClause("create_time desc");
            PageMybatisInterceptor.startPage(baseQuery.getPageNum(),baseQuery.getPageSize());
            bannerInfoMapper.selectByExample(example);
            Page<BannerInfo> page = PageMybatisInterceptor.endPage();
            return page;
        } catch (Throwable e) {
            LOG.error("fetchBannerInfoList 异常",baseQuery);
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public BannerInfo fetchBannerInfoDetail(Integer id) throws DatabaseException {
        try {
            if (id == null){
                LOG.error("fetchBannerInfoDetail id 为空",id);
                return null;
            }
            BannerInfo bannerInfo = bannerInfoMapper.selectByPrimaryKey(id);
            return bannerInfo;
        } catch (Throwable e) {
            LOG.error("fetchBannerInfoDetail 异常",id);
            throw new DatabaseException(e.getMessage());
        }
    }

    /**
     * 更新状态
     * @param id
     * @param status
     * @return
     * @throws DatabaseException
     */
    @Override
    public boolean modifyStatus(Integer id, Integer status) throws DatabaseException {
        try {
            if (id == null){
                LOG.error("modifyStatus id为空",id);
                return false;
            }
            if (status == null){
                LOG.error("modifyStatus status为空",status);
                return false;
            }
            BannerInfo record = new BannerInfo();
            record.setId(id);
            record.setBeUsed(YesNoEnum.create(status));
            Integer val = bannerInfoMapper.updateByPrimaryKeySelective(record);
            return (val>0)?true:false;
        } catch (Throwable e) {
            LOG.error("modifyStatus 异常",id);
            throw new DatabaseException(e.getMessage());
        }
    }

    /**
     * 获取所有Banner
     * @return
     * @throws DatabaseException
     */
    @Override
    public List<BannerInfo> fetchAllBannerInfo() throws DatabaseException {
        try {
            BannerInfoExample example = new BannerInfoExample();
            BannerInfoExample.Criteria criteria = example.createCriteria();
            criteria.andBeUsedEqualTo(YesNoEnum.YES);
            example.setOrderByClause("create_time desc");
            List<BannerInfo> list = bannerInfoMapper.selectByExample(example);
            return list;
        } catch (Throwable e) {
            LOG.error("fetchAllBannerInfo 异常");
            throw new DatabaseException(e.getMessage());
        }
    }
}

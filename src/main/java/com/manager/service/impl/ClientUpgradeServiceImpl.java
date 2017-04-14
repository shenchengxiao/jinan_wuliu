package com.manager.service.impl;

import com.manager.exception.DatabaseException;
import com.manager.interceptor.PageMybatisInterceptor;
import com.manager.mapper.ClientUpgradeInfoMapper;
import com.manager.pojo.ClientUpgradeInfo;
import com.manager.pojo.ClientUpgradeInfoExample;
import com.manager.request.clientupgrade.ClientUpgradeRequest;
import com.manager.service.ClientUpgradeService;
import com.manager.utils.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by shencx on 2017/4/14.
 */
@Service
public class ClientUpgradeServiceImpl implements ClientUpgradeService{

    Logger LOG = LoggerFactory.getLogger(ClientUpgradeServiceImpl.class);

    @Resource
    private ClientUpgradeInfoMapper clientUpgradeInfoMapper;

    @Override
    public Integer addClientUpgradeInfo(ClientUpgradeInfo clientUpgradeInfo) throws DatabaseException {
        try {
            if (clientUpgradeInfo == null){
                LOG.error("addClientUpgradeInfo 信息为空",clientUpgradeInfo);
                return Integer.valueOf(0);
            }
            clientUpgradeInfo.setCreateTime(new Date());
            int val = clientUpgradeInfoMapper.insertSelective(clientUpgradeInfo);
            if (val > 0){
                return clientUpgradeInfo.getId();
            }else {
                return Integer.valueOf(0);
            }
        } catch (Throwable e) {
            LOG.error("addClientUpgradeInfo 异常",clientUpgradeInfo);
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public boolean updateClientUpgradeInfo(ClientUpgradeInfo clientUpgradeInfo) throws DatabaseException {
        try {
            if (clientUpgradeInfo == null){
                LOG.error("updateClientUpgradeInfo 信息为空",clientUpgradeInfo);
                return false;
            }
            int val = clientUpgradeInfoMapper.updateByPrimaryKeySelective(clientUpgradeInfo);
            return (val>0)?true:false;
        } catch (Throwable e) {
            LOG.error("updateClientUpgradeInfo 异常",clientUpgradeInfo);
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public Page<ClientUpgradeInfo> fetchUpgradeList(ClientUpgradeRequest request) throws DatabaseException {
        try {
            if (request == null){
                LOG.error("fetchUpgradeList 信息为空",request);
                return null;
            }
            ClientUpgradeInfoExample example = new ClientUpgradeInfoExample();
            ClientUpgradeInfoExample.Criteria criteria = example.createCriteria();
            example.setOrderByClause("create_time desc");
            PageMybatisInterceptor.startPage(request.getPageNum(),request.getPageSize());
            clientUpgradeInfoMapper.selectByExample(example);
            Page<ClientUpgradeInfo> page = PageMybatisInterceptor.endPage();
            return page;
        }  catch (Throwable e) {
            LOG.error("fetchUpgradeList 异常",request);
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public ClientUpgradeInfo fetchUpgradeById(Integer id) throws DatabaseException {
        try {
            if (id == null){
                LOG.error("fetchUpgradeById id为空",id);
                return null;
            }
            ClientUpgradeInfo clientUpgradeInfo = clientUpgradeInfoMapper.selectByPrimaryKey(id);
            return clientUpgradeInfo;
        }catch (Throwable e) {
            LOG.error("fetchUpgradeById 异常",id);
            throw new DatabaseException(e.getMessage());
        }
    }
}

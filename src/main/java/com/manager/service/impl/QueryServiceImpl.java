package com.manager.service.impl;

import com.manager.exception.DatabaseException;
import com.manager.interceptor.PageMybatisInterceptor;
import com.manager.mapper.manual.ICustomizedQueryMapper;
import com.manager.request.query.QueryRequest;
import com.manager.response.QueryInfoResponse;
import com.manager.service.QueryService;
import com.manager.utils.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by shencx on 2017/4/19.
 */
@Service
public class QueryServiceImpl implements QueryService{

    Logger LOG = LoggerFactory.getLogger(QueryServiceImpl.class);

    @Resource
    private ICustomizedQueryMapper customizedQueryMapper;

    /**
     * 获取查询日志列表
     * @param request
     * @return
     * @throws DatabaseException
     */
    @Override
    public Page<QueryInfoResponse> fetchQueryInfoList(QueryRequest request) throws DatabaseException {
        try {
            if (request == null){
                LOG.error("fetchQueryInfoList 信息为空",request);
                return null;
            }
            PageMybatisInterceptor.startPage(request.getPageNum(),request.getPageSize());
            customizedQueryMapper.findQueryInfoPage(request);
            Page<QueryInfoResponse> page = PageMybatisInterceptor.endPage();
            return page;
        } catch (Throwable e) {
           LOG.error("fetchQueryInfoList 异常",e);
           throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public boolean batchDeleteQuery(List<Integer> list) throws DatabaseException {
        try {
            if (list == null || list.size() <= 0){
                LOG.error("batchDeleteQuery 信息为空",list);
                return false;
            }
            Integer val = customizedQueryMapper.batchDelete(list);
            return val>0?true:false;
        }catch (Throwable e) {
            LOG.error("batchDeleteQuery 异常",e);
            throw new DatabaseException(e.getMessage());
        }
    }
}

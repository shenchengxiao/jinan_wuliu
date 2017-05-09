package com.manager.handler;

import com.manager.exception.DatabaseException;
import com.manager.exception.YCException;
import com.manager.request.query.QueryRequest;
import com.manager.response.QueryInfoResponse;
import com.manager.service.QueryService;
import com.manager.utils.DateTimeUtil;
import com.manager.utils.Page;
import com.manager.utils.YCSystemStatusEnum;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by shencx on 2017/4/19.
 */
@Service
public class QueryHandler {

    Logger LOG = LoggerFactory.getLogger(QueryHandler.class);

    @Resource
    private QueryService queryService;

    /**
     * 获取查询日志列表
     * @param queryRequest
     * @return
     * @throws YCException
     */
    public Page<QueryInfoResponse> getQueryInfoList(QueryRequest queryRequest) throws YCException {
        Page<QueryInfoResponse> page = null;
//        if (StringUtils.isNoneBlank(queryRequest.getAppointed_day())){
//            queryRequest.setAppointedDay(DateTimeUtil.convertDate(queryRequest.getAppointed_day()));
//        }
        try {
            page = queryService.fetchQueryInfoList(queryRequest);
            return page;
        } catch (DatabaseException e) {
            LOG.error("getQueryInfoList exception",queryRequest);
            throw new YCException(YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getCode(), YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getDesc());
        }
    }

    /**
     * 批量删除日志
     * @param ids
     * @throws YCException
     */
    public void batchDelete(String[] ids) throws YCException {
        List<Integer> list = new ArrayList<>();
        for (String id : ids){
            list.add(Integer.valueOf(id));
        }
        try {
            queryService.batchDeleteQuery(list);
        }  catch (DatabaseException e) {
            LOG.error("batchDelete exception",ids);
            throw new YCException(YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getCode(), YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getDesc());
        }
    }
}

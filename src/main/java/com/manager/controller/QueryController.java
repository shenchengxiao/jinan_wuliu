package com.manager.controller;

import com.manager.exception.YCException;
import com.manager.handler.QueryHandler;
import com.manager.request.query.QueryRequest;
import com.manager.response.QueryInfoResponse;
import com.manager.utils.APIResponse;
import com.manager.utils.Page;
import com.manager.utils.YCSystemStatusEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by shencx on 2017/4/19.
 */
@Controller
@RequestMapping(value = "/api/query")
public class QueryController {

    Logger LOG = LoggerFactory.getLogger(QueryController.class);

    @Resource
    private QueryHandler queryHandler;

    /**
     * 获取查询日志列表
     * @param request
     * @param queryRequest
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public APIResponse<Page<QueryInfoResponse>> list(HttpServletRequest request, QueryRequest queryRequest){
        APIResponse<Page<QueryInfoResponse>> apiResponse = new APIResponse<>();
        Page<QueryInfoResponse> queryInfoResponsePage = null;
        try {
            queryInfoResponsePage = queryHandler.getQueryInfoList(queryRequest);
            apiResponse.setStatus(YCSystemStatusEnum.SUCCESS.getCode());
            apiResponse.setMsg(YCSystemStatusEnum.SUCCESS.getDesc());
            apiResponse.setData(queryInfoResponsePage);
        } catch (Throwable e) {
            LOG.error("获取查询日志列表发生异常",queryRequest);
            apiResponse.setStatus(YCSystemStatusEnum.SYSTEM_ERROR.getCode());
            apiResponse.setMsg(YCSystemStatusEnum.SYSTEM_ERROR.getDesc());
        }
        return apiResponse;
    }
}

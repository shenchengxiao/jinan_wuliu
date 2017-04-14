package com.manager.handler;


import com.manager.exception.DatabaseException;
import com.manager.exception.YCException;
import com.manager.pojo.BlackWord;
import com.manager.pojo.IpVisit;
import com.manager.request.blackword.BlackWordRequest;
import com.manager.request.ipvisit.IpVisitRequest;
import com.manager.response.BlackWordResponse;
import com.manager.response.IpVisitResponse;
import com.manager.service.BlackWordService;
import com.manager.service.IpVisitService;
import com.manager.utils.Page;
import com.manager.utils.Validator;
import com.manager.utils.YCSystemStatusEnum;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 
 * @author zcy
 *
 */
@Service
public class IpVisitHandler {

    @Resource
    private IpVisitService ipVisitService;

    Logger LOG = LoggerFactory.getLogger(IpVisitHandler.class);


    /**
     * 获取IP访问列表信息
     * @param request
     * @return
     * @throws YCException
     */
    public Page<IpVisitResponse> fetchIpVisitList(IpVisitRequest request) throws YCException {
        /** 参数校验 */
        //Validator.isEmpty(request,YCSystemStatusEnum.PARAM_EMPTY);
        Page<IpVisitResponse> page = null;
        try {
            page = ipVisitService.fetchIpVisitList(request);
            page.setPagesize(page.getPagesize());
            page.setPageindex(page.getPageindex());
            page.setTotal(page.getTotal());
        } catch (DatabaseException e) {
            LOG.error("fetchIpVisitList exception",request);
            throw new YCException(YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getCode(), YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getDesc());
        }
        return page;
    }
    
}

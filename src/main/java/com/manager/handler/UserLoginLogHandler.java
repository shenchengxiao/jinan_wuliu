package com.manager.handler;


import com.manager.exception.DatabaseException;
import com.manager.exception.YCException;
import com.manager.pojo.manual.UserLoginlogResponse;
import com.manager.request.userloginlog.UserLoginLogRequest;
import com.manager.service.IpVisitService;
import com.manager.utils.Page;
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
public class UserLoginLogHandler {

    @Resource
    private IpVisitService ipVisitService;

    Logger LOG = LoggerFactory.getLogger(UserLoginLogHandler.class);


    /**
     * 获取IP访问列表信息
     * @param request
     * @return
     * @throws YCException
     */
    public Page<UserLoginlogResponse> fetchIpVisitList2(UserLoginLogRequest request) throws YCException {
        /** 参数校验 */
        //Validator.isEmpty(request,YCSystemStatusEnum.PARAM_EMPTY);
        Page<UserLoginlogResponse> page = null;
        try {
            page = ipVisitService.fetchIpVisitList2(request);
            page.setPagesize(page.getPagesize());
            page.setPageindex(page.getPageindex());
            page.setTotal(page.getTotal());
        } catch (DatabaseException e) {
            LOG.error("fetchIpVisitList2 exception",request);
            throw new YCException(YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getCode(), YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getDesc());
        }
        return page;
    }


	/**
	 * 用户登录日志
	 * @param request
	 * @return
	 */
	public Page<UserLoginlogResponse> fetchIpVisitList3(UserLoginLogRequest request) throws YCException{
		Page<UserLoginlogResponse> page = null;
        try {
            page = ipVisitService.fetchIpVisitList3(request);
            page.setPagesize(page.getPagesize());
            page.setPageindex(page.getPageindex());
            page.setTotal(page.getTotal());
        } catch (DatabaseException e) {
            LOG.error("fetchIpVisitList3 exception",request);
            throw new YCException(YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getCode(), YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getDesc());
        }
        return page;
	}
    
}

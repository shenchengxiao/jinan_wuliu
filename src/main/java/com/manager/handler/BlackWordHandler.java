package com.manager.handler;


import com.manager.exception.DatabaseException;
import com.manager.exception.YCException;
import com.manager.pojo.BlackWord;
import com.manager.request.blackword.BlackWordRequest;
import com.manager.response.BlackWordResponse;
import com.manager.service.BlackWordService;
import com.manager.utils.Page;
import com.manager.utils.Validator;
import com.manager.utils.YCSystemStatusEnum;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by shencx on 2017/3/28.
 */
@Service
public class BlackWordHandler {

    @Resource
    private BlackWordService blackWordService;

    Logger LOG = LoggerFactory.getLogger(BlackWordHandler.class);


    /**
     * 获取黑词列表信息
     * @param request
     * @return
     * @throws YCException
     */
    public Page<BlackWordResponse> fetchBlackwordList(BlackWordRequest request) throws YCException {
        /** 参数校验 */
        //Validator.isEmpty(request,YCSystemStatusEnum.PARAM_EMPTY);
        Page<BlackWordResponse> page = null;
        try {
            page = blackWordService.fetchBlackwordList(request);
            page.setPagesize(page.getPagesize());
            page.setPageindex(page.getPageindex());
            page.setTotal(page.getTotal());
        } catch (DatabaseException e) {
            LOG.error("fetchBlackwordList exception",request);
            throw new YCException(YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getCode(), YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getDesc());
        }
        return page;
    }
    
    public void deleteBlackword(Integer id) throws YCException {
        /** 参数校验 */
        Validator.isEmpty(id,YCSystemStatusEnum.USER_ID_EMPTY);
        try {
        	blackWordService.deleteBlackword(id);
        } catch (DatabaseException e) {
            LOG.error("deleteUserInfo exception",id);
            throw new YCException(YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getCode(), YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getDesc());
        }
    }
    
    public void updateBlackword(BlackWord blackWord) throws YCException {
        /** 参数校验 */
        Validator.isEmpty(blackWord.getbWId(),YCSystemStatusEnum.USER_ID_EMPTY);
        try {
        	blackWordService.updateBlackword(blackWord);
        } catch (DatabaseException e) {
            LOG.error("updateBlackword exception",blackWord.toString());
            throw new YCException(YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getCode(), YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getDesc());
        }
    }

    public void addBlackword(BlackWord blackWord) throws YCException {
        /** 参数校验 */
        Validator.isEmpty(blackWord.getBlackWord(),YCSystemStatusEnum.USER_ID_EMPTY);
        Validator.isEmpty(blackWord.getEnabled(),YCSystemStatusEnum.USER_ID_EMPTY);
        try {
        	blackWordService.addBlackword(blackWord);
        } catch (DatabaseException e) {
            LOG.error("addBlackword exception",blackWord.toString());
            throw new YCException(YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getCode(), YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getDesc());
        }
    }
    
    /**
     * 获取黑词详情信息
     * @param id
     * @return
     * @throws YCException
     */
    public BlackWord getBlackwordDetail(Integer id) throws YCException {
        /** 参数校验 */
        Validator.isEmpty(id,"黑词主键ID不能为空");
        try {
        	BlackWord blackWordResponse = blackWordService.fetchBlackWordDetail(id);
            return blackWordResponse;
        } catch (DatabaseException e) {
            LOG.error("getBlackwordDetail exception",id);
            throw new YCException(YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getCode(), YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getDesc());
        }
    }
}

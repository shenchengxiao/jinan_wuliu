package com.manager.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.manager.exception.DatabaseException;
import com.manager.interceptor.PageMybatisInterceptor;
import com.manager.mapper.BlackWordMapper;
import com.manager.pojo.Advert;
import com.manager.pojo.BlackWord;
import com.manager.pojo.BlackWordExample;
import com.manager.request.blackword.BlackWordRequest;
import com.manager.response.BlackWordResponse;
import com.manager.service.BlackWordService;
import com.manager.utils.Page;

@Service
public class BlackWordServiceImpl implements BlackWordService {
	
	@Resource
    private BlackWordMapper blackWordMapper;

    Logger LOG = LoggerFactory.getLogger(BlackWordServiceImpl.class);

	@Override
	public Page<BlackWordResponse> fetchBlackwordList(BlackWordRequest request) throws DatabaseException {
		try {
            /*if (request == null){
                LOG.error("fetchUserInfoList 信息为空",request);
                return null;
            }*/
            BlackWordExample example = new BlackWordExample();
            BlackWordExample.Criteria criteria = example.createCriteria();
            if (StringUtils.isNotBlank(request.getBlackWord())){
                StringBuffer sb = new StringBuffer();
                String content = request.getBlackWord();
                sb.append("%");
                sb.append(content);
                sb.append("%");
                criteria.andBlackWordLike(sb.toString());
            }
            if (request.getEnabled() != null){
                criteria.andEnabledEqualTo(request.getEnabled());
            }
            example.setOrderByClause("create_time desc");
            PageMybatisInterceptor.startPage(request.getPageNum(),request.getPageSize());
            blackWordMapper.selectByExample(example);
            Page<BlackWordResponse> page = PageMybatisInterceptor.endPage();
            return page;
        } catch (Throwable e) {
            LOG.error("fetchBlackwordList 异常",request);
            throw new DatabaseException(e.getMessage());
        }
	}

	@Override
	public boolean deleteBlackword(Integer bWId) throws DatabaseException{
		try {
            if (bWId == null){
                LOG.error("deleteUserInfo id为空",bWId);
                return false;
            }
            int result = blackWordMapper.deleteByPrimaryKey(bWId);
    		
    		return result> 0?true:false;
        } catch (Throwable e) {
            LOG.error("deleteUserInfo 异常",bWId);
            throw new DatabaseException(e.getMessage());
        }
	}

	@Override
	public boolean updateBlackword(BlackWord blackWord) throws DatabaseException {
		try {
            if (blackWord == null){
                LOG.error("updateBlackword 信息为空",blackWord);
                return false;
            }
           int val = blackWordMapper.updateByPrimaryKeySelective(blackWord);
           return (val > 0)?true:false;
        } catch (Throwable e) {
            LOG.error("updateBlackword 异常",blackWord);
            throw new DatabaseException(e.getMessage());
        }
	}

	@Override
	public boolean addBlackword(BlackWord blackWord) throws DatabaseException {
		try {
			int val = -1;
            if (blackWord == null){
                LOG.error("addBlackword 信息为空",blackWord);
                return false;
            }
            
           if(blackWord.getbWId() != null){
        	   blackWord.setUpdateTime(new Date());
        	   val = blackWordMapper.updateByPrimaryKeySelective(blackWord);
           }else{
        	   blackWord.setCreateTime(new Date());
               val = blackWordMapper.insertSelective(blackWord);
           }
           return (val > 0)?true:false;
        } catch (Throwable e) {
            LOG.error("addBlackword 异常",blackWord);
            throw new DatabaseException(e.getMessage());
        }
	}

	@Override
	public BlackWord fetchBlackWordDetail(Integer id) throws DatabaseException {
		try {
            if (id == null){
                LOG.error("id 为空",id);
                return null;
            }
            BlackWord blackWord = blackWordMapper.selectByPrimaryKey(id);
            return blackWord;
        } catch (Throwable e) {
            LOG.error("fetchAdvertDetail 异常",id);
            throw new DatabaseException(e.getMessage());
        }
	}

}

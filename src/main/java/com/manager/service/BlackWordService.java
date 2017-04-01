package com.manager.service;

import com.manager.exception.DatabaseException;
import com.manager.pojo.Advert;
import com.manager.pojo.BlackWord;
import com.manager.request.blackword.BlackWordRequest;
import com.manager.response.BlackWordResponse;
import com.manager.utils.Page;

public interface BlackWordService {
	
	
	/**
     * 获取黑词列表
     * @param request
     * @return
     * @throws DatabaseException
     */
    Page<BlackWordResponse> fetchBlackwordList(BlackWordRequest request) throws DatabaseException;
    
    
    public boolean deleteBlackword(Integer id) throws DatabaseException;
    
    public boolean updateBlackword(BlackWord blackWord) throws DatabaseException;
    
    public boolean addBlackword(BlackWord blackWord) throws DatabaseException;
    
    BlackWord fetchBlackWordDetail(Integer id) throws DatabaseException;

}

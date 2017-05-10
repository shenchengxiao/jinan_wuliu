package com.manager.service;

import java.util.List;

import com.manager.exception.DatabaseException;

public interface UserMessageService {

	boolean sendUserMessage(List<Integer> list, Integer mType, String content) throws DatabaseException;

	boolean sendSysMessage(Integer mType, String content) throws DatabaseException;


}

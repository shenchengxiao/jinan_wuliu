package com.manager.service;

import java.util.List;

import com.manager.exception.DatabaseException;

public interface UserMessageService {

	boolean sendUserMessage(List<Integer> list, String content) throws DatabaseException;

	boolean sendSysMessage(String content) throws DatabaseException;


}

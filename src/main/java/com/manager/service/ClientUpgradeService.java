package com.manager.service;

import com.manager.exception.DatabaseException;
import com.manager.pojo.ClientUpgradeInfo;
import com.manager.request.clientupgrade.ClientUpgradeRequest;
import com.manager.utils.Page;

/**
 * Created by shencx on 2017/4/14.
 */
public interface ClientUpgradeService {

    Integer addClientUpgradeInfo(ClientUpgradeInfo clientUpgradeInfo) throws DatabaseException;

    boolean updateClientUpgradeInfo(ClientUpgradeInfo clientUpgradeInfo) throws DatabaseException;

    Page<ClientUpgradeInfo> fetchUpgradeList(ClientUpgradeRequest request) throws DatabaseException;

    ClientUpgradeInfo fetchUpgradeById(Integer id) throws DatabaseException;
}

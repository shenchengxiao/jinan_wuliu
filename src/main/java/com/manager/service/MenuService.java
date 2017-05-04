package com.manager.service;

import com.manager.exception.DatabaseException;
import com.manager.pojo.manual.MenuInfoDto;
import com.manager.request.menu.MenuRequest;
import com.manager.request.menu.UpdateRoleMenuRequest;

import java.util.List;

/**
 * Created by shencx on 2017/3/27.
 */
public interface MenuService {

    /**
     * 获取所有菜单列表
     * @return
     */
    List<MenuInfoDto> fetchMenuInfo(MenuRequest request) throws DatabaseException;

    List<MenuInfoDto> fetchSecondMenuName(MenuRequest request) throws DatabaseException;

    Integer batchUpdateRoleMenuInfo(List<UpdateRoleMenuRequest> updateRoleMenuRequests) throws DatabaseException;
}

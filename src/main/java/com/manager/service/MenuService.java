package com.manager.service;

import com.manager.exception.DatabaseException;
import com.manager.pojo.manual.MenuInfoDto;
import com.manager.request.menu.MenuRequest;

import java.util.List;

/**
 * Created by shencx on 2017/3/27.
 */
public interface MenuService {

    /**
     * 获取所有菜单列表
     * @return
     */
    public List<MenuInfoDto> fetchMenuInfo(MenuRequest request) throws DatabaseException;
}

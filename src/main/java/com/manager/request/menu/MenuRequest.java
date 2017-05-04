package com.manager.request.menu;

import com.manager.request.BaseQuery;

import java.util.List;

/**
 * Created by shencx on 2017/3/27.
 */
public class MenuRequest extends BaseQuery{

    private List<Integer> menuInfoIds;

    public List<Integer> getMenuInfoIds() {
        return menuInfoIds;
    }

    public void setMenuInfoIds(List<Integer> menuInfoIds) {
        this.menuInfoIds = menuInfoIds;
    }
}

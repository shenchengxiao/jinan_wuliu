package com.manager.response;

import java.io.Serializable;
import java.util.List;

/**
 * Created by shencx on 2017/5/2.
 */
public class SelectedSecondMenuResponse implements Serializable{

    private List<Integer> menuInfoId;

    public List<Integer> getMenuInfoId() {
        return menuInfoId;
    }

    public void setMenuInfoId(List<Integer> menuInfoId) {
        this.menuInfoId = menuInfoId;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("SelectedSecondMenuResponse{");
        sb.append("menuInfoId=").append(menuInfoId);
        sb.append('}');
        return sb.toString();
    }
}

package com.manager.mapper.manual;

import com.manager.pojo.manual.MenuInfoDto;
import com.manager.request.menu.MenuRequest;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by shencx on 2017/3/27.
 */
@Repository
public interface ICustomizedMenuInfoMapper {

    /**
     * 获取所有菜单列表
     * @param request
     * @return
     */
    List<MenuInfoDto> findAllMenuInfo(MenuRequest request);
}

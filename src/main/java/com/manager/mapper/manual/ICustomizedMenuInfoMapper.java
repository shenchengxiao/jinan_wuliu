package com.manager.mapper.manual;

import com.manager.pojo.manual.MenuInfoDto;
import com.manager.request.menu.MenuRequest;
import com.manager.request.menu.UpdateRoleMenuRequest;
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

    /**
     * 获取所有二级菜单名称
     * @return
     */
    List<MenuInfoDto> findMenuInfoNameAndId(MenuRequest request);

    Integer batchUpdateRoleType(List<UpdateRoleMenuRequest> list);
}

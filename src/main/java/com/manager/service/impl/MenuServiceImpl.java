package com.manager.service.impl;

import com.manager.exception.DatabaseException;
import com.manager.mapper.manual.ICustomizedMenuInfoMapper;
import com.manager.pojo.manual.MenuInfoDto;
import com.manager.request.menu.MenuRequest;
import com.manager.service.MenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by shencx on 2017/3/27.
 */
@Service
public class MenuServiceImpl implements MenuService{

    @Resource
    private ICustomizedMenuInfoMapper customizedMenuInfoMapper;

    Logger LOG = LoggerFactory.getLogger(MenuServiceImpl.class);

    /**
     * 获取菜单列表
     * @param request
     * @return
     * @throws DatabaseException
     */
    @Override
    public List<MenuInfoDto> fetchMenuInfo(MenuRequest request) throws DatabaseException {

        try {
            if (request == null){
                LOG.error("fetchMenuInfo 参数为空" ,request);
                return null;
            }
            List<MenuInfoDto> list = customizedMenuInfoMapper.findAllMenuInfo(request);
            if (list == null || list.size() == 0){
                return null;
            }else {
                return list;
            }
        } catch (Throwable e) {
            LOG.error("fetchMenuInfo 异常",e,request);
            throw new DatabaseException(e.getMessage());
        }

    }
}

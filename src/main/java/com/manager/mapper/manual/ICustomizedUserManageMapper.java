package com.manager.mapper.manual;

import com.manager.request.user.OnlineUserRequest;
import com.manager.request.user.UserManageRequest;
import com.manager.response.UserMangeResponse;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by shencx on 2017/4/11.
 */
@Repository
public interface ICustomizedUserManageMapper {

    /**
     * 获取用户详情信息
     * @param request
     * @return
     */
    UserMangeResponse findUserInfoDetail(UserManageRequest request);

    /**
     * 获取用户列表信息
     * @param request
     * @return
     */
    List<UserMangeResponse> findUserInfoPage(UserManageRequest request);

    /**
     * 批量查询用户
     * @param request
     * @return
     */
    List<UserMangeResponse> findUserByUserIds(OnlineUserRequest request);

    /**
     * 批量更新
     * @param ids
     * @return
     */
    Integer batchUpdateStatus(List<Integer> ids);
}

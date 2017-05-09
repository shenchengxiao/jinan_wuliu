package com.manager.mapper.manual;

import com.manager.request.query.QueryRequest;
import com.manager.response.QueryInfoResponse;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by shencx on 2017/4/19.
 */
@Repository
public interface ICustomizedQueryMapper {

   List<QueryInfoResponse> findQueryInfoPage(QueryRequest request);

   int batchDelete(List<Integer> ids);
}

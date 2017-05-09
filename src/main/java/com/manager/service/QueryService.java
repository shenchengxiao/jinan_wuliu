package com.manager.service;

import com.manager.exception.DatabaseException;
import com.manager.request.query.QueryRequest;
import com.manager.response.QueryInfoResponse;
import com.manager.utils.Page;

import java.util.List;

/**
 * Created by shencx on 2017/4/19.
 */
public interface QueryService {

    Page<QueryInfoResponse> fetchQueryInfoList(QueryRequest request) throws DatabaseException;

    boolean batchDeleteQuery(List<Integer> list) throws DatabaseException;
}

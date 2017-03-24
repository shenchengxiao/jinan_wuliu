package com.manager.request;

import com.google.common.collect.Sets;

import java.util.Set;

public class BaseQuery extends BaseEntity {

    /**
     * 按ID查询
     */
    private Long id;

    /**
     * id列表查询
     */
    private Set<Long> ids;

    /**
     * 当前页数
     */
    private Integer pageNum = 1;

    /**
     * 每页条数
     */
    private Integer pageSize = 10;   //每页记录数(缺省10条)

    /**
     * 修改记录人ID
     * @author:
     * @date: 2015-7-27 14:24
     */
    private Long modifierId;

    /**
     * 排序组合 modify_date desc, id asc
     */
    private String orderByClause;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Long> getIds() {
        return ids;
    }

    public void setIds(Set<Long> ids) {
        this.ids = ids;
    }

    public BaseQuery addId(Long id){
        if(this.ids==null) this.ids = Sets.newConcurrentHashSet();
        this.ids.add(id);
        return this;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public void setModifierId(Long modifierId){
        this.modifierId = modifierId;
    }

    public Long getModifierId(){
        return this.modifierId;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }
}

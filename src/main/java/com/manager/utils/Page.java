package com.manager.utils;

import java.io.Serializable;
import java.util.List;

/**
 * 分页对象
 * @param <E>
 */
public class Page<E> implements Serializable {

    public static final int DEFAULT_PAGE_NUM = 1;  //当前页数(缺省第1页)
    public static final int DEFAULT_PAGE_SIZE = 10;    //每页记录数(缺省10条)

    //查询参数
    private int pageindex = DEFAULT_PAGE_NUM;    //当前页数
    private int pagesize = DEFAULT_PAGE_SIZE;   //每页记录数

    //计算值
//    private int startRow;   //开始行数(从0开始)
//    private int endRow;     //结束行数

    //查询结果值
    private int total;     //总记录数
    private List<E> result; //分页记录集

    /**
     * 构造分页对象
     * @param pageindex
     * @param pagesize
     */
    public Page(int pageindex, int pagesize) {
        this.pageindex = pageindex;
        this.pagesize = pagesize;
//        this.startRow = pageindex > 0 ? (pageindex - 1) * pagesize : 0;
//        this.endRow = pageindex * pagesize;
    }

    /**
     * 构造分页对象
     * @param pageindex 页号
     * @param pagesize 每页条数
     * @param total 返回总数
     */
    public Page(int pageindex, int pagesize, int total) {
        this.pageindex = pageindex;
        this.pagesize = pagesize;
        this.total = total;
    }

    public Page(Page page, List<E> result) {
        this(page.getPageindex(), page.getPagesize(), page.getTotal());
        this.result = result;
    }

    public Page(){
        this.pageindex = DEFAULT_PAGE_NUM;
        this.pagesize = DEFAULT_PAGE_SIZE;
    }

    /**
     * 获取分页记录集
     * @return result
     */
    public List<E> getResult() {
        return result;
    }

    /**
     * 设置分页记录集
     * @param result
     */
    public void setResult(List<E> result) {
        this.result = result;
    }

    /**
     * 获取总页数
     * @return pages
     */
    public int getPagecount() {
        return (total + pagesize - 1)/ pagesize;
    }

    /**
     * 获取总记录数
     * @return total
     */
    public int getTotal() {
        return total;
    }

    /**
     * 设置总记录数
     * @param total
     */
    public void setTotal(int total) {
        this.total = total;
    }

    /**
     * 获取开始行数
     * @return startRow
     */
//    public int getStartRow() {
//        return startRow;
//    }

    /**
     * 设置开始行数
     * @param startRow
     */
//    public void setStartRow(int startRow) {
//        this.startRow = startRow;
//    }

    /**
     * 获取结束行数
     * @return endRow
     */
//    public int getEndRow() {
//        return endRow;
//    }

    /**
     * 设置结束行数
     * @param endRow
     */
//    public void setEndRow(int endRow) {
//        this.endRow = endRow;
//    }

    /**
     * 获取当前页数
     * @return pageindex
     */
    public int getPageindex() {
        return pageindex;
    }

    /**
     * 设置当前页数
     * @param pageindex
     */
    public void setPageindex(int pageindex) {
        this.pageindex = pageindex;
    }

    /**
     * 获取每页记录数
     * @return pagesize
     */
    public int getPagesize() {
        return pagesize;
    }

    /**
     * 设置每页记录数
     * @param pagesize
     */
    public void setPagesize(int pagesize) {
        this.pagesize = pagesize;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageindex=" + pageindex +
                ", pagesize=" + pagesize +
//                ", startRow=" + startRow +
//                ", endRow=" + endRow +
                ", total=" + total +
                ", result=" + result +"}";
    }
}
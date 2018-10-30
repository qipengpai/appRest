/**
 * 
 */
package com.company.platform.base.model.base;

import java.io.Serializable;
import java.util.Map;

import javax.validation.constraints.NotNull;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * @author zhengjn
 *
 */
@SuppressWarnings("serial")
public class BaseHttpParamsPageReq implements Serializable {

    // 当前页
    @JsonProperty("pageNum")
    @NotNull
    private int pageNum;
    // 每页的数量
    @JsonProperty("pageSize")
    private int pageSize = 10;
    // 总记录数
    @JsonProperty("total")
    private long total;
    // 总页数
    @JsonProperty("pages")
    private int pages;
    // 查询条件
    private Map<String, Object> queryCond;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public Map<String, Object> getQueryCond() {
        return queryCond;
    }

    public void setQueryCond(Map<String, Object> queryCond) {
        this.queryCond = queryCond;
    }
}

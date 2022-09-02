package com.hitqz.sjtc.core.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PageResultEntity<T> implements Serializable {

    /**
     * chengxr
     */
    private static final long serialVersionUID = 8884140342393975411L;
    /**
     * 总记录数
     */
    private long total = 0;

    /**
     * 当前分页（从1开始）
     */
    private long current = 1;

    /**
     * 分页大小
     */
    private long size;
    /**
     * 共多少页
     */
    private long pages;

    /**
     * 当前分页中包含的记录列表
     */
    private List<T> records = new ArrayList<T>();

    /**
     * 构造函数
     */
    public PageResultEntity() {
        super();
    }

    /**
     * 构造函数
     *
     * @param total       总记录数
     * @param currentPage 当前分页（从1开始）
     * @param rows        当前分页中包含的记录列表
     */
    public PageResultEntity(long total, long currentPage, long totalPage, long size, List<T> rows) {
        super();
        this.total = total;
        this.current = currentPage;
        this.pages = totalPage;
        this.size = size;
        this.records = rows;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getCurrent() {
        return current;
    }

    public void setCurrent(long current) {
        this.current = current;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public long getPages() {
        return pages;
    }

    public void setPages(long pages) {
        this.pages = pages;
    }

    public List<T> getRecords() {
        return records;
    }

    public void setRecords(List<T> records) {
        this.records = records;
    }
}

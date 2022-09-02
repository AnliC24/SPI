package com.hitqz.sjtc.core.model;

import java.io.Serializable;

/**
 * @author windC
 * */
public class PageCondition implements Serializable {

    private int currentPage;
    private int pageSize;


    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}

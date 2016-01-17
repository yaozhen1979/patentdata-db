package com.patentdata.vo.common;

public class PageSortInfo {

    public static final int DEFAULT_PAGE_SIZE = 50;

    private int pageNo = 1;
    private int pageIndex = 0;
    private int pageSize = DEFAULT_PAGE_SIZE;
    private int pageCount = 0;
    private int recordCount = 0;

    public PageSortInfo() {

    }

    public PageSortInfo(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
        this.pageIndex = pageNo - 1;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
        this.pageNo = pageIndex + 1;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(int recordCount) {
        this.recordCount = recordCount;
    }

    public int getStart() {
        return pageSize * pageIndex + 1;
    }

    public int getLimit() {
        return pageSize;
    }

}

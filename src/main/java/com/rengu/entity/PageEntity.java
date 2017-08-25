package com.rengu.entity;

import java.util.List;

public class PageEntity {
    int totalPageNum;
    int firstIndexNum;
    int maxIndexNum;
    List tableList;

    public PageEntity(int totalPageNum, int firstIndexNum, int maxIndexNum, List tableList) {
        this.totalPageNum = totalPageNum;
        this.firstIndexNum = firstIndexNum;
        this.maxIndexNum = maxIndexNum;
        this.tableList = tableList;
    }

    public int getTotalPageNum() {
        return totalPageNum;
    }

    public void setTotalPageNum(int totalPageNum) {
        this.totalPageNum = totalPageNum;
    }

    public int getFirstIndexNum() {
        return firstIndexNum;
    }

    public void setFirstIndexNum(int firstIndexNum) {
        this.firstIndexNum = firstIndexNum;
    }

    public int getMaxIndexNum() {
        return maxIndexNum;
    }

    public void setMaxIndexNum(int maxIndexNum) {
        this.maxIndexNum = maxIndexNum;
    }

    public List getTableList() {
        return tableList;
    }

    public void setTableList(List tableList) {
        this.tableList = tableList;
    }
}

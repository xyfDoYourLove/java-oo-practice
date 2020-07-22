package com.twu.bean;

public class HotSearchRank {
    // 热搜榜单排名

    // 排名的名次
    private int sort;

    // 排名的当前竞价
    private int price = 0;

    // 排名对应的热搜事件
    private HotSearchEvent currentEvent = null;

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public HotSearchEvent getCurrentEvent() {
        return currentEvent;
    }

    public void setCurrentEvent(HotSearchEvent currentEvent) {
        this.currentEvent = currentEvent;
    }
}

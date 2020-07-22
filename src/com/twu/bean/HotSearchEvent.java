package com.twu.bean;

public class HotSearchEvent {
    // 热搜事件

    // 热搜名称
    private String eventName;

    // 获得的投票数，即热度
    private Integer heatNum = 0;

    // 区分是否是超级热搜
    private Boolean isSuperEvent = false;

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Integer getHeatNum() {
        return heatNum;
    }

    public void setHeatNum(Integer heatNum) {
        this.heatNum = heatNum;
    }

    public Boolean getSuperEvent() {
        return isSuperEvent;
    }

    public void setSuperEvent(Boolean superEvent) {
        isSuperEvent = superEvent;
    }
}

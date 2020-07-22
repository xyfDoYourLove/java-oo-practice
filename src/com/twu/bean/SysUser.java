package com.twu.bean;

public class SysUser {

    // 昵称
    private String name;

    // 票数，可投票的票数
    private Integer ticketNum = 10;

    // 区分是不是管理员
    private Boolean isAdmin = false;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTicketNum() {
        return ticketNum;
    }

    public void setTicketNum(Integer ticketNum) {
        this.ticketNum = ticketNum;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }
}

package com.twu.constant;

import com.twu.bean.HotSearchEvent;
import com.twu.bean.HotSearchRank;
import com.twu.bean.SysUser;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class RunTimeVar {

    // 是否退出系统，结束程序
    public static Boolean isQuit = false;

    public static String currentUser;

    // 存储所有的热搜事件    <事件名称， 事件对象>
    public static Map<String, HotSearchEvent> allEventMap = new LinkedHashMap<>();

    // 存储所有Rank        <排名， 事件对象>
    public static Map<Integer, HotSearchRank> allRankMap = new HashMap<>();

    // 当前的菜单指令集和要打印的菜单内容
    public static Map<Integer, String> currentMenu = Constant.MAIN_MENU;
    public static List<String> currentMenuText = Constant.MAIN_MENU_TEXT;

    // 登陆过的用户信息，存储用   <呢称， 用户对象>
    public static Map<String, SysUser> loginUserMap = new HashMap<>();

//    static {
//        // 初始化热搜榜单，假数据
//        HotSearchEvent event1 = new HotSearchEvent();
//        event1.setEventName("猪肉");
//        HotSearchEvent event2 = new HotSearchEvent();
//        event2.setEventName("石油");
//        event2.setHeatNum(2);
//        HotSearchEvent event3 = new HotSearchEvent();
//        event3.setEventName("美元");
//        event3.setHeatNum(2);
//        HotSearchEvent event4 = new HotSearchEvent();
//        event4.setEventName("鸡肉");
//        event4.setHeatNum(10);
//        event4.setSuperEvent(true);
//        HotSearchEvent event5 = new HotSearchEvent();
//        event5.setEventName("森林");
//
//        HotSearchRank rank1 = new HotSearchRank();
//        rank1.setSort(6);
//        rank1.setCurrentEvent(event2);
//        rank1.setPrice(10);
//        HotSearchRank rank2 = new HotSearchRank();
//        rank2.setSort(3);
//        rank2.setCurrentEvent(event4);
//        rank2.setPrice(5);
//        HotSearchRank rank3 = new HotSearchRank();
//        rank3.setSort(7);
//        rank3.setCurrentEvent(event1);
//        rank3.setPrice(5);
//        HotSearchRank rank4 = new HotSearchRank();
//        rank4.setSort(10);
//        rank4.setCurrentEvent(event3);
//        rank4.setPrice(5);
//        HotSearchRank rank5 = new HotSearchRank();
//        rank5.setSort(1);
//        rank5.setCurrentEvent(event5);
//        rank5.setPrice(5);
//
//
//        allEventMap.put(event1.getEventName(), event1);
//        allEventMap.put(event2.getEventName(), event2);
//        allEventMap.put(event3.getEventName(), event3);
//        allEventMap.put(event4.getEventName(), event4);
//        allEventMap.put(event5.getEventName(), event5);
//
////        allRankMap.put(rank1.getSort(), rank1);
////        allRankMap.put(rank2.getSort(), rank2);
////        allRankMap.put(rank3.getSort(), rank3);
////        allRankMap.put(rank4.getSort(), rank4);
////        allRankMap.put(rank5.getSort(), rank5);
//
//    }

}

package com.twu.constant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Constant {

    public static final String adminUserName = "admin";
    public static final String adminPassword = "admin123";

    // 超级热搜的投票和热度比例
    public static Integer GROW_FACTOR = 2;

    // 指令集，通过指令String，对照对应的action
    public static Map<String, Integer> ORDER_MAP = new HashMap<>();

    // 主菜单
    public static Map<Integer, String> MAIN_MENU = new HashMap<>();
    public static List<String> MAIN_MENU_TEXT = new ArrayList<>();

    // 普通用户菜单
    public static Map<Integer, String> USER_MENU = new HashMap<>();
    public static List<String> USER_MENU_TEXT = new ArrayList<>();

    // 管理员菜单
    public static Map<Integer, String> ADMIN_MENU = new HashMap<>();
    public static List<String> ADMIN_MENU_TEXT = new ArrayList<>();

    static {

        // 初始化指令集
        ORDER_MAP.put("adminLogin", 1);
        ORDER_MAP.put("userLogin", 2);
        ORDER_MAP.put("quitSystem", 3);
        ORDER_MAP.put("viewHotSearchList", 4);
        ORDER_MAP.put("addHotSearchEvent", 5);
        ORDER_MAP.put("voteHotSearchEvent", 6);
        ORDER_MAP.put("buyHotSearchRank", 7);
        ORDER_MAP.put("logOut", 8);
        ORDER_MAP.put("addSuperHotSearchEvent", 9);
        ORDER_MAP.put("adminViewHotSearchList", 10);
        ORDER_MAP.put("adminAddHotSearchEvent", 11);

        // 初始化主菜单
        MAIN_MENU.put(1, "userLogin");
        MAIN_MENU.put(2, "adminLogin");
        MAIN_MENU.put(3, "quitSystem");

        MAIN_MENU_TEXT.add("欢迎来到热搜管理系统，你是？");
        MAIN_MENU_TEXT.add("1、普通用户");
        MAIN_MENU_TEXT.add("2、管理员");
        MAIN_MENU_TEXT.add("3、退出");


        // 初始化普通用户菜单
        USER_MENU.put(1, "viewHotSearchList");
        USER_MENU.put(2, "addHotSearchEvent");
        USER_MENU.put(3, "voteHotSearchEvent");
        USER_MENU.put(4, "buyHotSearchRank");
        USER_MENU.put(5, "logOut");

        USER_MENU_TEXT.add("普通用户,你好");
        USER_MENU_TEXT.add("1、查看热搜排行榜");
        USER_MENU_TEXT.add("2、添加热搜事件");
        USER_MENU_TEXT.add("3、为热搜事件投票");
        USER_MENU_TEXT.add("4、购买热搜排名");
        USER_MENU_TEXT.add("5、退出登录");


        // 初始化管理员用户菜单
        ADMIN_MENU.put(1, "adminViewHotSearchList");
        ADMIN_MENU.put(2, "adminAddHotSearchEvent");
        ADMIN_MENU.put(3, "addSuperHotSearchEvent");
        ADMIN_MENU.put(4, "logOut");

        ADMIN_MENU_TEXT.add("管理员,你好");
        ADMIN_MENU_TEXT.add("1、查看热搜排行榜");
        ADMIN_MENU_TEXT.add("2、添加热搜");
        ADMIN_MENU_TEXT.add("3、添加超级热搜");
        ADMIN_MENU_TEXT.add("4、退出登录");
    }

}

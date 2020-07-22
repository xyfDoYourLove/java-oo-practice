package com.twu.action;

import com.twu.constant.Constant;
import com.twu.constant.RunTimeVar;

public class SysAction {

    // 根据指令，执行相应的操作
    public static void actionByOrder(String order) {

        if (Constant.ORDER_MAP.get(order) == null) {
            // 非法指令
            System.out.println("非法指令");
        }

        switch (Constant.ORDER_MAP.get(order)) {
            case 1:
                // 管理员登录
                UserAction.adminLogin();
                break;
            case 2:
                // 普通用户登录
                UserAction.userLogin();
                break;
            case 3:
                // 退出系统
                RunTimeVar.isQuit = true;
                System.out.println("===================分隔符=====================");
                System.out.println("正在退出系统……");
                System.out.println("感谢使用本系统，祝您生活愉快，谢谢！！");
                break;
            case 4:
                // 查看热搜排行榜
                UserAction.viewHotSearchList();
                UserAction.switchMenu(Constant.USER_MENU, Constant.USER_MENU_TEXT, true);
                break;
            case 5:
                // 添加热搜事件
                UserAction.addHotSearchEvent(false);
                UserAction.switchMenu(Constant.USER_MENU, Constant.USER_MENU_TEXT, true);
                break;
            case 6:
                // 为热搜事件投票
                UserAction.voteEvent();
                break;
            case 7:
                // 购买热搜排名
                UserAction.buyHotSearchRank();
                break;
            case 8:
                // 退出登录
                UserAction.logOut();
                break;
            case 9:
                // 添加超级热搜
                UserAction.addHotSearchEvent(true);
                UserAction.switchMenu(Constant.ADMIN_MENU, Constant.ADMIN_MENU_TEXT, true);
                break;
            case 10:
                // 查看热搜排行榜，管理员
                UserAction.viewHotSearchList();
                UserAction.switchMenu(Constant.ADMIN_MENU, Constant.ADMIN_MENU_TEXT, true);
                break;
            case 11:
                // 添加热搜，管理员
                UserAction.addHotSearchEvent(false);
                UserAction.switchMenu(Constant.ADMIN_MENU, Constant.ADMIN_MENU_TEXT, true);
                break;
            default:
                System.out.println("该指令未指派操作，请完善系统");
                break;
        }

    }

}

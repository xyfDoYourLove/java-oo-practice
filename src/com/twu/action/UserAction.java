package com.twu.action;

import com.twu.bean.HotSearchEvent;
import com.twu.bean.HotSearchRank;
import com.twu.bean.SysUser;
import com.twu.constant.Constant;
import com.twu.constant.RunTimeVar;

import java.util.*;

public class UserAction {

    // 普通用户登录操作
    public static void userLogin() {

        System.out.println("===================分隔符=====================");
        System.out.println("请输入您的昵称：");
        // 从控制台获取用户昵称
        Scanner sc = new Scanner(System.in);
        String userName = sc.next();

        while (userName.equals(Constant.adminUserName)) {
            System.out.println("昵称非法，请重新输入:");
            userName = sc.next();
        }

        // 判断是否存在当前用户
        if (RunTimeVar.loginUserMap.get(userName) == null) {
            // 当前昵称用户未记录，创建用户并记录用户登录
            RunTimeVar.currentUser = createUser(userName, false).getName();
        }else {
            // 存在该用户，记录登录
            RunTimeVar.currentUser = userName;
        }
        System.out.println("用户登陆成功！！");
        // 切换到用户菜单，并打印
        switchMenu(Constant.USER_MENU, Constant.USER_MENU_TEXT, true);

    }

    // 管理员登陆
    public static void adminLogin() {
        // 从控制台获取用户昵称
        Scanner sc = new Scanner(System.in);
        System.out.println("===================分隔符=====================");
        System.out.println("请输入管理员账号：");
        String userName = sc.next();
        System.out.println("请输入管理员密码：");
        String password = sc.next();

        if (userName.equals(Constant.adminUserName) && password.equals(Constant.adminPassword)) {

            // 判断该管理员用户是否初始化过
            if (RunTimeVar.loginUserMap.get(Constant.adminUserName) == null) {
                RunTimeVar.currentUser = createUser(Constant.adminUserName, true).getName();
            }else {
                RunTimeVar.currentUser = Constant.adminUserName;
            }
            System.out.println("管理员登陆成功!!!");
            switchMenu(Constant.ADMIN_MENU, Constant.ADMIN_MENU_TEXT, true);
        }else {

            System.out.println("账号密码错误，请重试……");
            switchMenu(Constant.MAIN_MENU, Constant.MAIN_MENU_TEXT, false);
        }
    }

    // 用户退出登录
    public static void logOut() {
        System.out.println("===================分隔符=====================");
        System.out.println("退出登录");
        // 更改登录用户记录
        RunTimeVar.currentUser = null;
        switchMenu(Constant.MAIN_MENU, Constant.MAIN_MENU_TEXT, false);

    }

    // 从控制台新建用户，并进行初始化和存储
    public static SysUser createUser(String userName, Boolean isAdmin) {

        SysUser user = new SysUser();
        user.setName(userName);
        if (isAdmin) {
            user.setAdmin(true);
        }
        // 将创建的用户对象进行存储
        RunTimeVar.loginUserMap.put(user.getName(), user);

        return user;
    }

    // 切换菜单
    public static void switchMenu(Map<Integer, String> switchMenu, List<String> switchMenuText, Boolean isLogin) {

        // 切换菜单
        RunTimeVar.currentMenu = switchMenu;
        RunTimeVar.currentMenuText = switchMenuText;

        System.out.println("===================分隔符=====================");

        if (isLogin) {
            // 打印普通用户菜单内容
            for (int i = 0; i < RunTimeVar.currentMenuText.size(); i++) {
                if (i <= 0) {
                    String[] helloText = RunTimeVar.currentMenuText.get(0).split(",");
                    System.out.println(helloText[0]+ RunTimeVar.currentUser + "，" + helloText[1]);
                }else {
                    System.out.println(RunTimeVar.currentMenuText.get(i));
                }
            }
        }else {
            for (int i = 0; i < RunTimeVar.currentMenuText.size(); i++) {
                System.out.println(RunTimeVar.currentMenuText.get(i));
            }
        }

    }


    // 输出热搜排行榜
    // 先将allEventMap当中的除购买过排名的热搜以外的其他热搜事件，进行按热度排名，得到一个List
    // 再将这个List进行循环（循环长度用allEventMap长度），每步进行校验，优先输出allRankMap当中的，次要输出list，即可得到排行榜
    public static void viewHotSearchList() {
        System.out.println("===================分隔符=====================");

        if (RunTimeVar.allEventMap.size() > 0) {
            System.out.println("热搜排行榜：");
            System.out.println("排名  名称  热度");
            System.out.println("-----------------");

            Map<String, HotSearchEvent> rankingEvent = new HashMap<>();
            Map<Integer, HotSearchRank> rankingRanks = new HashMap<>();
            List<HotSearchEvent> noRankEvents = new ArrayList<>();

            for (HotSearchRank rank : RunTimeVar.allRankMap.values()) {
                rankingEvent.put(rank.getCurrentEvent().getEventName(), rank.getCurrentEvent());
                rankingRanks.put(rank.getSort(), rank);
            }
            for (HotSearchEvent event : RunTimeVar.allEventMap.values()) {
                if (rankingEvent.get(event.getEventName()) == null) {
                    noRankEvents.add(event);
                }
            }

            // 对noRankEvents进行冒泡排序
            if (noRankEvents.size() > 0) {
                // 对noRankEvents进行排序，按照热度大小顺序排，冒泡排序
                noRankEvents = sortEvents(noRankEvents);

                // 如果出现断层的极端情况，则从rankMap当中顺序往上补，不再考虑排名的问题
                // 输出热搜排行榜
                for (int i = 0, j = 0; i < noRankEvents.size() + RunTimeVar.allRankMap.size(); i++) {
                    if (j < noRankEvents.size()) {
                        // 首先紧着event全部输出
                        if (RunTimeVar.allRankMap.get(i+1) != null) {
                            rankingRanks.remove(i+1);
                            System.out.println(i+1 + "    " + RunTimeVar.allRankMap.get(i+1).getCurrentEvent().getEventName() + "  " + RunTimeVar.allRankMap.get(i+1).getCurrentEvent().getHeatNum());
                        }else {
                            System.out.println(i+1 + "    " + noRankEvents.get(j).getEventName() + "  " + noRankEvents.get(j).getHeatNum());
                            j++;
                        }
                    }else {
                        // 此时情况为，event已经全部输出完毕，剩下map中的还没全部输出
                        if (RunTimeVar.allRankMap.get(i+1) != null) {
                            rankingRanks.remove(i+1);
                            System.out.println(i+1 + "    " + RunTimeVar.allRankMap.get(i+1).getCurrentEvent().getEventName() + "  " + RunTimeVar.allRankMap.get(i+1).getCurrentEvent().getHeatNum());
                        }else {
                            // 出现了断层，极端情况，退出当前for循环，将map中剩余的单独输出
                            if (rankingRanks.size() <= 1) {
                                // 直接将剩余的一个进行输出
                                for (Integer key : rankingRanks.keySet()){
                                    System.out.println(i+1 + "    " + rankingRanks.get(key).getCurrentEvent().getEventName() + "  " + rankingRanks.get(key).getCurrentEvent().getHeatNum());
                                }
                            }else {
                                // 剩余多个，需要先进行排序，再顺序输出
                                List<HotSearchRank> lastRanks = sortRanks(rankingRanks);
                                for (int k = 0; k < lastRanks.size(); k++) {
                                    System.out.println(i+k+1 + "    " + lastRanks.get(k).getCurrentEvent().getEventName() + " " + lastRanks.get(k).getCurrentEvent().getHeatNum());
                                }
                            }
                            // 直接退出循环
                            break;
                        }
                    }
                }
            }else {
                // 仅根据购买的，输出热搜排行榜
                // 将购买的，进行排序，输出
                List<HotSearchRank> lastRanks = sortRanks(rankingRanks);
                for (int k = 0; k < lastRanks.size(); k++) {
                    System.out.println(k+1 + "  " + lastRanks.get(k).getCurrentEvent().getEventName() + "  " + lastRanks.get(k).getCurrentEvent().getHeatNum());
                }
            }
        }else {
            System.out.println("当前榜上无热搜，请先添加热搜后再查看");
        }



    }

    // 对排名rank进行排序，冒泡
    public static List<HotSearchRank> sortRanks(Map<Integer, HotSearchRank> rankingRanks) {
        List<HotSearchRank> lastRanks = new ArrayList<>(rankingRanks.values());
        for (int k = 0; k < lastRanks.size(); k++) {
            for (int l = 0; l < lastRanks.size() - 1 - k; l++) {
                if (lastRanks.get(l+1).getSort() < lastRanks.get(l).getSort()) {
                    HotSearchRank tmp2 = lastRanks.get(l+1);
                    lastRanks.set(l+1, lastRanks.get(l));
                    lastRanks.set(l, tmp2);
                }
            }
        }
        return lastRanks;
    }

    // 对热搜事件进行排序，冒泡
    public static List<HotSearchEvent> sortEvents(List<HotSearchEvent> noRankEvents) {
        // 对noRankEvents进行排序，按照热度大小顺序排，冒泡排序
        for (int i = 0; i < noRankEvents.size(); i++) {
            for (int j = 0; j < noRankEvents.size() - 1 - i; j++) {
                if (noRankEvents.get(j+1).getHeatNum() > noRankEvents.get(j).getHeatNum()) {
                    HotSearchEvent tmp = noRankEvents.get(j+1);
                    noRankEvents.set(j+1, noRankEvents.get(j));
                    noRankEvents.set(j, tmp);
                }
            }
        }
        return noRankEvents;
    }

    // 添加热搜事件
    public static void addHotSearchEvent(Boolean isSuper) {

        System.out.println("===================分隔符=====================");

        if (isSuper) {
            System.out.println("请输入超级热搜事件名称（超级热搜获得投票数为两倍）：");
        }else {
            System.out.println("请输入热搜事件名称：");
        }

        Scanner sc = new Scanner(System.in);
        String hotSearchName = sc.next();

        while (RunTimeVar.allEventMap.get(hotSearchName) != null) {
            System.out.println("该热搜已存在，请重新输入：");
            hotSearchName = sc.next();
        }

        HotSearchEvent newEvent = new HotSearchEvent();
        newEvent.setEventName(hotSearchName);
        newEvent.setSuperEvent(isSuper);

        RunTimeVar.allEventMap.put(hotSearchName, newEvent);

        System.out.println("添加热搜成功!!");
    }

    // 给热搜事件投票投票
    public static void voteEvent() {

        System.out.println("===================分隔符=====================");

        // 首先查询当前登录用户的信息，剩余票数
        SysUser currUser = RunTimeVar.loginUserMap.get(RunTimeVar.currentUser);

        Scanner sc = new Scanner(System.in);
        System.out.println("请输入你要投票的热搜名称：");
        String eventName = sc.next();

        // 控制台获取投票数，并对票数进行判定是否合法
        if (RunTimeVar.allEventMap.get(eventName) != null) {
            if (RunTimeVar.allEventMap.get(eventName).getSuperEvent()) {
                System.out.println("请输入您要投多少票，当前热搜为超级热搜，一票等于两热度，（您目前还有" + currUser.getTicketNum() + "票）");
            }else {
                System.out.println("请输入您要投多少票，(您目前还有" + currUser.getTicketNum() + "票）");
            }
            int voteNum = sc.nextInt();

            while (voteNum <= 0 || voteNum > currUser.getTicketNum()) {
                System.out.println("票数输入不合法，请重新输入:");
                voteNum = sc.nextInt();
            }

            // 更新用户数据
            currUser.setTicketNum(currUser.getTicketNum() - voteNum);
            RunTimeVar.loginUserMap.replace(currUser.getName(), currUser);

            // 根据票数，对allEvent进行更新
            HotSearchEvent currEvent = RunTimeVar.allEventMap.get(eventName);
            if (currEvent.getSuperEvent()) {
                currEvent.setHeatNum(currEvent.getHeatNum() + voteNum * Constant.GROW_FACTOR);
            }else {
                currEvent.setHeatNum(currEvent.getHeatNum() + voteNum);
            }
            RunTimeVar.allEventMap.replace(currEvent.getEventName(), currEvent);

            System.out.println("投票成功！！（您目前剩余" + currUser.getTicketNum() + "票）");
            switchMenu(Constant.USER_MENU, Constant.USER_MENU_TEXT, true);
        }else {
            // 名称错误，退回到用户菜单
            System.out.println("没有该热搜事件，请添加后再进行操作");
            switchMenu(Constant.USER_MENU, Constant.USER_MENU_TEXT, true);
        }


    }

    // 购买热搜排名
    public static void buyHotSearchRank() {

        System.out.println("===================分隔符=====================");

        System.out.println("请输入您要购买的热搜名称：");
        Scanner sc = new Scanner(System.in);

        String eventName = sc.next();

        if (RunTimeVar.allEventMap.get(eventName) == null) {
            System.out.println("当前热搜不存在，请添加后再进行购买");
            switchMenu(Constant.USER_MENU, Constant.USER_MENU_TEXT, true);
        }else {

            System.out.println("请输入您要购买的热搜排名：");
            int sortNum = sc.nextInt();

            Boolean sortNumIsLegal = false;

            while (!sortNumIsLegal) {
                if (RunTimeVar.allRankMap.get(sortNum) != null) {
                    // 该排名已经被竞价
                    if (RunTimeVar.allRankMap.get(sortNum).getCurrentEvent().getEventName().equals(eventName)) {
                        // 该排名已经是该事件
                        System.out.println("当前事件已经在当前排名，无需再次竞价！！");
                        switchMenu(Constant.USER_MENU, Constant.USER_MENU_TEXT, true);
                    }else {
                        // 有热搜会被顶替

                        // 获取竞价
                        System.out.println("当前排名已被竞价，当前价格为" + RunTimeVar.allRankMap.get(sortNum).getPrice() + "元，价高者得，请输入您的竞价");
                        int rankPrice = sc.nextInt();

                        // 校验是否拍卖成功
                        while (rankPrice <= RunTimeVar.allRankMap.get(sortNum).getPrice()) {
                            System.out.println("这个价钱您可什么都买不到噢，再次出价吧");
                            rankPrice = sc.nextInt();
                        }

                        HotSearchRank currRank = RunTimeVar.allRankMap.get(sortNum);

                        // 更新eventList
                        RunTimeVar.allEventMap.remove(currRank.getCurrentEvent().getEventName());

                        // 更新RankMap
                        currRank.setPrice(rankPrice);
                        currRank.setCurrentEvent(RunTimeVar.allEventMap.get(eventName));
                        RunTimeVar.allRankMap.replace(sortNum, currRank);

                        System.out.println("拍卖成功！！！这个排名是您的了");
                        switchMenu(Constant.USER_MENU, Constant.USER_MENU_TEXT, true);
                    }
                    sortNumIsLegal = true;
                }else {
                    // 没有会被顶替的现象

                    // 校验排名是否合法
                    if (sortNum <= 0 || sortNum > RunTimeVar.allEventMap.size()) {
                        System.out.println("排名不合法，请重新输入:");
                        sortNum = sc.nextInt();
                        continue;
                    }

                    // 获取竞价
                    System.out.println("您想要花多少钱购买该排名？（该排名当前无人购买，任意整数价格即可购买成功）");

                    int rankPrice = sc.nextInt();

                    // 校验竞价
                    while (rankPrice <= 0) {
                        System.out.println("这个价钱您可什么都买不到噢，再次出价吧");
                        rankPrice = sc.nextInt();
                    }

                    // 添加RankMap
                    HotSearchRank newRank = new HotSearchRank();
                    newRank.setPrice(rankPrice);
                    newRank.setSort(sortNum);
                    newRank.setCurrentEvent(RunTimeVar.allEventMap.get(eventName));
                    RunTimeVar.allRankMap.put(newRank.getSort(), newRank);

                    System.out.println("拍卖成功！！！这个排名是您的了");
                    switchMenu(Constant.USER_MENU, Constant.USER_MENU_TEXT, true);
                    // 退出循环
                    sortNumIsLegal = true;
                }
            }
        }
    }

}

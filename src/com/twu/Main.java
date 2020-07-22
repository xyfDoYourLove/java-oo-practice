package com.twu;

import com.twu.action.SysAction;
import com.twu.constant.Constant;
import com.twu.constant.RunTimeVar;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);

        // 打印菜单
        for (int i = 0; i < Constant.MAIN_MENU_TEXT.size(); i++) {
            System.out.println(Constant.MAIN_MENU_TEXT.get(i));
        }

        while(true) {

            // 获取输入的菜单命令
            int inputOrder = sc.nextInt();

            // 根据输入的数字，到对应的菜单当中找到对应的指令String
            if (RunTimeVar.currentMenu.get(inputOrder) != null) {
                // 开始对应的操作
                SysAction.actionByOrder(RunTimeVar.currentMenu.get(inputOrder));
            }else {
                System.out.println("无效选项，请重新选择");
            }

            if (RunTimeVar.isQuit) {
                break;
            }
        }




    }



}

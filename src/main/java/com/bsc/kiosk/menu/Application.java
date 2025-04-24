package com.bsc.kiosk.menu;

import com.bsc.kiosk.menu.controller.MenuController;

public class Application {
    public static void main(String[] args) {
        MenuController mc = new MenuController();
        mc.categoryCheck();
        //mc.menuSearch();

    }
}

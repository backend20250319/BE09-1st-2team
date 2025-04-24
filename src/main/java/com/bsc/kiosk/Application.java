package com.bsc.kiosk;

import com.bsc.kiosk.menu.controller.MenuController;

public class Application {
    public static void main(String[] args) {
        MenuController menuController = new MenuController();
        menuController.categoryCheck();
    }
}

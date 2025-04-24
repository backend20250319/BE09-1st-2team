package com.bsc.kiosk.admin.view;

import com.bsc.kiosk.admin.controller.AdminController;

public class Application {
    public static void main(String[] args) {
        AdminController admin = new AdminController();
        admin.adminMenu();
    }
}

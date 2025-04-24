package com.bsc.kiosk.menu.model.service;

import com.bsc.kiosk.menu.model.dao.MenuRepository;
import com.bsc.kiosk.menu.model.dto.MenuDTO;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.bsc.kiosk.common.JDBCTemplate.close;
import static com.bsc.kiosk.common.JDBCTemplate.getConnection;

public class MenuService {

    private final MenuRepository mr;

    public MenuService() {
        mr = new MenuRepository();
    }


    public int selectChickenMenu() {
        System.out.println("[MenuService] selectChickenMenu() : 메뉴 조회 성공 - 개발 완료 후 삭제 구문");
        Connection con = getConnection();
        List<MenuDTO> chickenList = mr.getChickenMenu(con);
        close(con);
        for (MenuDTO menu : chickenList) {
            System.out.println(menu);
        }
        return 1;
    }

    public int selectSideMenu() {
        System.out.println("[MenuService] selectSideMenu() : 메뉴 조회 성공 - 개발 완료 후 삭제 구문");
        Connection con = getConnection();
        List<MenuDTO> sideList = mr.getSideMenu(con);
        close(con);
        for (MenuDTO menu : sideList) {
            System.out.println(menu);
        }
        return 2;
    }

    public int selectDrinkMenu() {
        System.out.println("[MenuService] selectDrinkMenu() : 메뉴 조회 성공 - 개발 완료 후 삭제 구문");
        Connection con = getConnection();
        List<MenuDTO> drinkList = mr.getDrinkMenu(con);
        close(con);
        for (MenuDTO menu : drinkList) {
            System.out.println(menu);
        }
        return 3;
    }


    public void chickenIntoCart() {
        Scanner sc = new Scanner(System.in);

    }

    public void sideIntoCart() {
    }

    public void drinkIntoCart() {
    }
}

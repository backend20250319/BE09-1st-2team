package com.bsc.kiosk.menu.model.service;

import com.bsc.kiosk.menu.model.dao.MenuRepository;
import com.bsc.kiosk.menu.model.dto.MenuDTO;
import com.bsc.kiosk.cart.model.service.CartService;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.bsc.kiosk.common.JDBCTemplate.close;
import static com.bsc.kiosk.common.JDBCTemplate.getConnection;

public class MenuService {

    private final MenuRepository mr;
    CartService cs = new CartService();

    public MenuService() {
        mr = new MenuRepository();
    }


    public int selectChickenMenu() {
        Connection con = getConnection();
        List<MenuDTO> chickenList = mr.getChickenMenu(con);
        close(con);
        System.out.println("");
        System.out.println("======== 치킨 카테고리 ========");
        for (MenuDTO menu : chickenList) {
            System.out.println(menu.toString());
        }
        System.out.println("0 : 뒤로가기");
        return 0;
    }

    public int selectSideMenu() {
        Connection con = getConnection();
        List<MenuDTO> sideList = mr.getSideMenu(con);
        close(con);
        System.out.println("");
        System.out.println("======== 사이드 카테고리 ========");
        for (MenuDTO menu : sideList) {
            System.out.println(menu.toString());
        }
        System.out.println("0 : 뒤로가기");
        return  0;
    }

    public int selectDrinkMenu() {
        Connection con = getConnection();
        List<MenuDTO> drinkList = mr.getDrinkMenu(con);
        close(con);
        System.out.println("");
        System.out.println("======== 음료/주류 카테고리 ========");
        for (MenuDTO menu : drinkList) {
            System.out.println(menu.toString());
        }
        System.out.println("0 : 뒤로가기");
        return 0;
    }

    public void selectMenuIntoCart(int a) {
        while (true) {
            System.out.println("");
            Scanner sc = new Scanner(System.in);
            System.out.print("원하시는 메뉴의 번호를 입력해주세요 : ");
            int menuId = sc.nextInt();
            if (menuId < mr.findAllmenu().size()) {
                break;
            }
            System.out.print('\n' + "원하시는 수량을 입력해주세요 : ");
            int quantity = sc.nextInt();
            mr.insertIntoCart(menuId, quantity);
        }
    }

    public void showKeywordMenu(String searchInput) {
            Connection con = getConnection();
        List<MenuDTO> allMenu = mr.findAllmenu(con);
        for (MenuDTO menu : allMenu) {
            if (menu.getMenuName().toLowerCase().contains(searchInput.toLowerCase())) {
                System.out.println(menu);
            }
        }
    }
}


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
        return 1;
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
        return 2;
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
        return 3;
    }

    public void selectMenuIntoCart (int boardKind) {
        switch (boardKind) {
            case 1: caseBoard(1);
            break;
            case 2: caseBoard(2);
            break;
            case 3: caseBoard(3);
            break;
        }
    }

    public void caseBoard (int boardKind) {
        while (true) {
            System.out.println("");
            Scanner sc = new Scanner(System.in);
            System.out.print("원하시는 메뉴의 번호를 입력해주세요 : ");
            int menuId = sc.nextInt();
            if (menuId == 0) {
                break;
            } else if (!mr.getMenuIdsByCategory(boardKind).contains(menuId)) {
                System.out.println("해당 카테고리에 없는 번호입니다. 다시 입력해주세요.");
                break;
            }
            System.out.print('\n' + "원하시는 수량을 입력해주세요 : ");
            int quantity = sc.nextInt();
            mr.insertIntoCart(menuId, quantity);
        }
    }

    public void insertSearchedMenu () {
        System.out.println("");
        Scanner sc = new Scanner(System.in);
        System.out.print("원하시는 메뉴의 번호를 입력해주세요 : ");
        int menuId = sc.nextInt();
        System.out.print('\n' + "원하시는 수량을 입력해주세요 : ");
        int quantity = sc.nextInt();
        mr.insertIntoCart(menuId, quantity);
    }



    public boolean showKeywordMenu(String searchInput) {
        Connection con = getConnection();
        List<MenuDTO> allMenu = mr.findAllmenu(con);
        close(con);
        List<MenuDTO> menuDto = new ArrayList<>();
        while (true) {
            for (MenuDTO menu : allMenu) {
                if (menu.getMenuName().toLowerCase().contains(searchInput.toLowerCase())) {
                    menuDto.add(menu);
                }
            }
            if (menuDto.isEmpty()) {
                System.out.println('\n' + "<검색 결과가 없습니다.>");
                return false;
            } else {
                for (MenuDTO m : menuDto) {
                    System.out.println(m);
                    return true;
                }
            }
        }

    }


}





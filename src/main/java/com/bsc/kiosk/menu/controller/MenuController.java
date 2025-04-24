package com.bsc.kiosk.menu.controller;

import com.bsc.kiosk.menu.model.dto.MenuDTO;
import com.bsc.kiosk.menu.model.service.MenuService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuController {

    MenuService ms = new MenuService();

    public MenuController() {}
    Scanner sc = new Scanner(System.in);

    public void categoryCheck() {
        String category = """
                ======== 카테고리 조회 ========
                1. 치킨
                2. 사이드
                3. 음료/주류
                0. 돌아가기
                
                원하시는 메뉴 카테고리를 선택해주세요 :
                """;
        System.out.println(category);

        while (true) {
            int categoryInt = sc.nextInt();
            switch (categoryInt) {
                case 1:
                    selectMenuIntoCart(ms.selectChickenMenu());
                    break;
                case 2:
                    selectMenuIntoCart(ms.selectSideMenu());
                    break;
                case 3:
                    selectMenuIntoCart(ms.selectDrinkMenu());
                case 0: return;
                default:
                        System.out.println("번호를 다시 입력해주세요.");
            }
        }
    }

    public void selectMenuIntoCart(int categoryId) {

        List<MenuDTO> cart = new ArrayList<>();

        while (true) {
            switch (categoryId) {
                case 1: ms.chickenIntoCart();
                    break;
                case 2: ms.sideIntoCart();
                    break;
                case 3: ms.drinkIntoCart();
                    break;
            }
        }

    }

}

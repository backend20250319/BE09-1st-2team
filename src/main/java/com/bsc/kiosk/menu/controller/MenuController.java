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

    /***
     * 카테고리 조회를 위하 메소드
     */
    public void categoryCheck() {
        while (true) {
            String category = """
                ======== 카테고리 조회 ========
                1. 치킨
                2. 사이드
                3. 음료/주류
                0. 돌아가기
                
                원하시는 메뉴 카테고리를 선택해주세요 :  """ + " ";
            System.out.print(category);

            int categoryInt = sc.nextInt();
            switch (categoryInt) {
                case 1:
                    ms.selectMenuIntoCart(ms.selectChickenMenu());
                    break;
                case 2:
                    ms.selectMenuIntoCart(ms.selectSideMenu());
                    break;
                case 3:
                    ms.selectMenuIntoCart(ms.selectDrinkMenu());
                case 0: return;
                default:
                    System.out.println("번호를 다시 입력해주세요.");
            }


        }

    }

    public void menuSearch() {
         String search = """
                 ============== 메뉴 검색 ==============
                 검색어를 입력해주세요 :  """ ;
        Scanner sc = new Scanner(System.in);
        System.out.println(search);
        String searchInput = sc.nextLine();
        ms.showKeywordMenu(searchInput);
        ms.selectMenuIntoCart(0);
    }

}

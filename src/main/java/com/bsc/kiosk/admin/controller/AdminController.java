package com.bsc.kiosk.admin.controller;

import com.bsc.kiosk.admin.model.dto.AdminDTO;
import com.bsc.kiosk.admin.model.service.AdminService;

import java.util.Scanner;

public class AdminController {
    public void adminMenu() {
        String adminMenu = """
                ============== BSC 관리자 ==============
                1. 메뉴 추가
                2. 메뉴 수정
                3. 메뉴 삭제
                0. 돌아가기
                
                메뉴를 선택해주세요 : """;
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print(adminMenu);
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    insertMenu();
                    break;
                case 2:
                    updateMenu();
                    break;
                case 3:
                    deleteMenu();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("잘못된 번호를 입력하셨습니다.");
                    break;
            }
        }
    }

    public void insertMenu() {
        AdminService admin = new AdminService();
        Scanner sc = new Scanner(System.in);
        System.out.print("카테고리 ID를 입력해주세요(1. 치킨 2.사이드 3.음료/주류) : ");
        int id = sc.nextInt();
        System.out.print("메뉴명을 입력해주세요 : ");
        String menu = sc.next();
        System.out.print("가격을 입력해주세요 : ");
        int price = sc.nextInt();
        AdminDTO admins = new AdminDTO(id, menu, price);
        System.out.println("[Application] main() : admins ====> " + admins);
        admin.insertMenu(admins);

    }

    public void updateMenu() {
        AdminService admin = new AdminService();
        Scanner sc = new Scanner(System.in);
        System.out.print("변경할 메뉴 ID를 입력해주세요 : ");
        int id = sc.nextInt();
        System.out.print("새로운 메뉴명을 입력해주세요 : ");
        String menu = sc.next();
        System.out.print("새로운 가격을 입력해주세요 : ");
        int price = sc.nextInt();
        System.out.print("품절여부 (y/n) : ");
        String is_sold_out = sc.next().toUpperCase();

        AdminDTO admins = new AdminDTO(id, menu, price, is_sold_out);
        System.out.println("[Application] main() : admins ====> " + admins);
        admin.updateMenu(admins);
    }

    public void deleteMenu() {
        AdminService admin = new AdminService();
        Scanner sc = new Scanner(System.in);
        System.out.print("삭제할 메뉴 ID를 입력해주세요 : ");
        int menu_id = sc.nextInt();
        AdminDTO admins = new AdminDTO(menu_id);
        admin.deleteMenu(admins);
    }
}

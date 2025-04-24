package com.bsc.kiosk;

import com.bsc.kiosk.admin.controller.AdminController;
import com.bsc.kiosk.cart.controller.CartController;
import com.bsc.kiosk.cart.model.service.CartService;
import com.bsc.kiosk.menu.controller.MenuController;
import com.bsc.kiosk.payment.controller.PaymentController;

import java.util.Scanner;

public class Application {
    public static CartController cartController = new CartController();
    public static AdminController adminController = new AdminController();
    public static PaymentController paymentController = new PaymentController();
    public static MenuController menuController = new MenuController();

    public static void main(String[] args) {
        mainMenu();
    }

    public static void mainMenu() {
        String msg = """
                ============== BSC ==============
                1. 주문하기
                2. 직원호출
                0. 프로그램 종료
                ===============================
                메뉴를 선택해주세요 : """ + " ";

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print(msg);
            int input = sc.nextInt();
            switch (input) {
                case 0:// 프로그램 종료
                    System.exit(0);
                case 1: // 주문하기
                    orderMenu();
                    break;
                case 2: // 직원호출
                    System.out.println("직원을 호출했습니다. \n잠시만 기다려주세요.");
                    break;
                case 999: // 관리자모드
                    adminController.adminMenu();
                    break;
            }
        }
    }

    public static void orderMenu() {
        String msg = '\n' + """
                ============== 주문하기==============
                1. 카테고리 조회
                2. 메뉴 검색
                3. 장바구니
                4. 결제하기
                0. 돌아가기
                ==================================
                
                메뉴를 선택해주세요 : """ + " ";
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print(msg);
            int input = sc.nextInt();

            switch (input) {
                case 0: // 돌아가기
                    return;
                case 1: // 카테고리 조회
                    menuController.categoryCheck();
                    break;
                case 2: // 메뉴 검색
                    menuController.menuSearch();
                    break;
                case 3: // 장바구니
                    cartController.cartMenu();
                    break;
                case 4:
                    paymentController.Payment();// 결제하기
                    break;
            }
        }
    }
}

package com.bsc.kiosk.cart.controller;

import com.bsc.kiosk.cart.model.dto.CartItemDto;
import com.bsc.kiosk.cart.model.service.CartService;

import java.util.List;
import java.util.Scanner;

public class CartController {
    public static CartService cs = new CartService();

    public void cartMenu() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            // 장바구니 조회
            List<CartItemDto> cartDtoList = cs.getCart();

            System.out.println("========================= 장바구니 =========================");
            int totalPrice = 0;
            for (int i = 0; i < cartDtoList.size(); i++) {
                CartItemDto cartDto = cartDtoList.get(i);
                System.out.println((i + 1) + ". " + cartDto.getMenuName()
                        + "  |  " + cartDto.getPrice() + "원"
                        + "  |  수량 " + cartDto.getQuantity());
                totalPrice += cartDto.getPrice() * cartDto.getQuantity();
            }
            System.out.println("총 금액 : " + totalPrice + "원");
            System.out.println("===========================================================");
            System.out.println("[1] 수량 변경  [2] 항목 삭제  [3] 전체 삭제  [0] 돌아가기");
            System.out.print("\n메뉴를 선택해주세요 : ");
            int menuNum = sc.nextInt();
            sc.nextLine(); // 버퍼 제거

            switch (menuNum) {
                case 0: // 돌아가기
                    return;
                case 1: // 수량 변경
                    System.out.print("수량을 변경할 항목 번호를 입력해주세요 :");
                    int updateIndex = sc.nextInt() - 1;
                    sc.nextLine(); // 버퍼 제거
                    System.out.print("수량을 입력해주세요 :");
                    int updateQuantity = sc.nextInt();
                    sc.nextLine(); // 버퍼 제거

                    int udpateMenuId = cartDtoList.get(updateIndex).getMenuId();
                    cs.updateCart(udpateMenuId, updateQuantity);
                    break;
                case 2: // 항목 삭제
                    System.out.print("삭제할 항목 번호 :");
                    int deleteIndex = sc.nextInt() - 1;
                    sc.nextLine(); // 버퍼 제거

                    int deleteMenuId = cartDtoList.get(deleteIndex).getMenuId();
                    cs.deleteCart(deleteMenuId);
                    break;
                case 3: // 전체 삭제
                    cs.deleteAllCart();
                    break;
            }
        }
    }
}

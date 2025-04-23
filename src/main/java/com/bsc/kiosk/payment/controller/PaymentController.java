package com.bsc.kiosk.payment.controller;

import com.bsc.kiosk.payment.model.dto.PaymentDTO;
import com.bsc.kiosk.payment.model.service.PaymentService;

import java.util.Scanner;

public class PaymentController {

        public static void main(String[] args) {
            PaymentService ps = new PaymentService();
            Scanner sc = new Scanner(System.in);
            String menu = """
                ============== 결제하기 ==============
                0. 장바구니 조회
                1. 카드 결제
                2. 기프티콘 사용
                3. 돌아가기 
                ==================================
                메뉴를 선택해주세요 : 
               """;
            System.out.println(menu);
            while(true) {
                System.out.println(menu);
                int input = sc.nextInt();
                switch (input) {
                    case 0:
                        break;
                    case 1:
                        System.out.println("카드 결제가 되셨습니다.");
                        break;
                    case 2:
                        System.out.println("바코드를 입력해주세요");
                        String con = sc.next();
                        if (ps.isVaildBarcode(con)){
                            int discount_price = ps.getDiscountByBarcode(con);
                            System.out.println("총 주문 금액을 입력해주세요 (test) : ");
                            int totalPrice = sc.nextInt();
                            System.out.println("기프티콘 금액 : " + discount_price + "원");
                            System.out.println("총 주문 금액 : " + totalPrice + "원");
                            if (totalPrice > discount_price) {
                                int remain = totalPrice - discount_price;
                                System.out.println("\n남은 금액 " + remain + "원을 카드로 추가 결제합니다.");
                                System.out.println("결제를 완료했습니다.\n");
                            } else if (totalPrice == discount_price) {
                                System.out.println("결제를 완료했습니다.\n");
                            } else {
                                System.out.println("\n기프티콘 금액보다 주문 금액이 작습니다.");
                                System.out.println("\n기프티콘을 사용하시겠습니까?");
                                System.out.println("\n[ 1 ] 결제 진행    [ 2 ] 취소");
                                System.out.print("\n메뉴를 선택해주세요 : ");
                                int choice = sc.nextInt();
                                if (choice == 1) {
                                    System.out.println("결제를 완료했습니다.\n");
                                } else {
                                    System.out.println("결제를 취소했습니다.\n");
                                }
                            }
                        }else {
                            System.out.println("유효하지 않은 기프티콘 입니다.");
                        }break;
                        case 3:
                            System.out.println("메인 메뉴로 돌아갑니다.");
                        break;
                        default:
                            System.out.println("잘못된 숫자를 입력하셨습니다.");
                }
            }
        }
    }


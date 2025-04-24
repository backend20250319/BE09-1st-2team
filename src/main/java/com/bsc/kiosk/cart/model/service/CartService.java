package com.bsc.kiosk.cart.model.service;

import com.bsc.kiosk.cart.model.dao.CartRepository;
import com.bsc.kiosk.cart.model.dto.CartItemDto;

import java.sql.Connection;
import java.util.List;

import static com.bsc.kiosk.common.JDBCTemplate.*;

public class CartService {
    private final CartRepository cartRepository;

    public CartService() {
        cartRepository = new CartRepository();
    }


    public List<CartItemDto> getCart() {
        Connection con = getConnection();

        List<CartItemDto> cart = cartRepository.selectAllCart(con);

        close(con);

        return cart;
    }

    public void updateCart(int menuId, int updateQuantity) {
        Connection con = getConnection();
        int updatedCount = cartRepository.updateCart(con, menuId, updateQuantity);

        if (updatedCount > 0) {
            commit(con);
            System.out.println("수량이 변경되었습니다.");
        } else {
            rollback(con);
            System.out.println("항목 번호를 다시 확인해주세요.");
        }
        close(con);
    }

    public void deleteCart(int menuId) {
        Connection con = getConnection();
        int deletedCount = cartRepository.deleteCart(con, menuId);

        if (deletedCount > 0) {
            commit(con);
            System.out.println("항목이 삭제되었습니다.");
        } else {
            rollback(con);
            System.out.println("항목 번호를 다시 확인해주세요.");
        }
        close(con);
    }

    public void deleteAllCart() {
        Connection con = getConnection();
        int deletedCount = cartRepository.deleteAllCart(con);

        if (deletedCount > 0) {
            commit(con);
        } else {
            rollback(con);
        }
        close(con);
    }

    public void createCart(int menuId, int quantity) {
        Connection con = getConnection();
        CartItemDto cartItem = cartRepository.selectCart(con, menuId);
        int count;

        if (cartItem != null) {
            quantity += cartItem.getQuantity();
            count = cartRepository.updateCart(con, menuId, quantity);
        } else {
            count = cartRepository.createCart(con, menuId, quantity);
        }

        if (count > 0) {
            commit(con);
            System.out.println("장바구니 담기 완료");
        } else {
            rollback(con);
            System.out.println("존재하지 않는 메뉴 ID 입니다.");
        }
        close(con);
    }
}

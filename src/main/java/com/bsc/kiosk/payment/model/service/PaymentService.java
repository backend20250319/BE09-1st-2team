package com.bsc.kiosk.payment.model.service;

import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.List;

import com.bsc.kiosk.admin.model.dto.AdminDTO;
import com.bsc.kiosk.cart.model.dao.CartRepository;
import com.bsc.kiosk.cart.model.dto.CartItemDto;
import com.bsc.kiosk.payment.model.dao.PaymentRepository;
import com.bsc.kiosk.payment.model.dto.OrderDTO;
import com.bsc.kiosk.payment.model.dto.OrderItemDTO;
import com.bsc.kiosk.payment.model.dto.PaymentDTO;

import static com.bsc.kiosk.common.JDBCTemplate.*;
import static com.bsc.kiosk.common.JDBCTemplate.rollback;

public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final CartRepository cartRepository;
    public PaymentService(){
        paymentRepository = new PaymentRepository();
         cartRepository = new CartRepository();
    }
    public List<PaymentDTO> giftcon() {

        Connection con = getConnection();
        List<PaymentDTO> paymentDTOList = paymentRepository.selectAllgifticon(con);

        for (PaymentDTO paymentDTO : paymentDTOList) {
            System.out.println(paymentDTO);
        }
        close(con);
        return paymentDTOList;
    }

    public boolean isVaildBarcode(String barcode) {
        Connection con = getConnection();
        boolean isValid = false;

        try {
            // barcode를 기반으로 DB에서 일치하는 기프티콘 하나를 가져오는 로직
            isValid = paymentRepository.existsByBarcode(con, barcode);
        } finally {
            close(con); // 반드시 연결 닫기
        }

        return isValid;
    }
    public int getDiscountByBarcode(String barcode) {
        Connection con = getConnection();
        int discount = 0;
        try {
            discount = paymentRepository.findDiscountByBarcode(con, barcode);
        } finally {
            close(con);
        }
        return discount;
    }
    public List<CartItemDto> getCart() {
        Connection con = getConnection();
        List<CartItemDto> cartItemDtoList = cartRepository.selectAllCart(con);
        close(con);
        return cartItemDtoList;
    }

    public void insertOrder() {
        OrderDTO orders = new OrderDTO(LocalDateTime.now());
        Connection con = getConnection();
        int result = paymentRepository.insertMenu(con, orders);

        // 수행 결과에 따라 Commit, Rollback 정해야한다.
        if (result > 0) {
            commit(con);
        } else {
            rollback(con);
        }
        close(con);
    }

    public void insertOrderItem() {
        Connection con = getConnection();
        int orderId = PaymentRepository.getOrderId(con);
        List<CartItemDto> cartList = cartRepository.selectAllCart(con);
        for (CartItemDto item : cartList) {
            paymentRepository.insertOrderItem(con, orderId, item);
        }
        commit(con);
    }

}


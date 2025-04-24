package com.bsc.kiosk.payment.model.service;

import java.sql.Connection;
import java.util.List;

import com.bsc.kiosk.cart.model.dao.CartRepository;
import com.bsc.kiosk.cart.model.dto.CartItemDto;
import com.bsc.kiosk.payment.model.dao.PaymentRepository;
import com.bsc.kiosk.payment.model.dto.PaymentDTO;
import static com.bsc.kiosk.common.JDBCTemplate.getConnection;
import static com.bsc.kiosk.common.JDBCTemplate.close;
// DB에서 가져온 데이터를 활용하여 구현하는 코드
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
}


package com.bsc.kiosk.payment.model.dao;

import com.bsc.kiosk.admin.model.dto.AdminDTO;
import com.bsc.kiosk.cart.model.dto.CartItemDto;
import com.bsc.kiosk.payment.model.dto.OrderDTO;
import com.bsc.kiosk.payment.model.dto.PaymentDTO;

import java.sql.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import static com.bsc.kiosk.common.JDBCTemplate.close;
import static java.time.LocalDateTime.now;

public class PaymentRepository {

    private Properties prop = null;

    public PaymentRepository() {
        prop = new Properties();
        try {
            prop.loadFromXML(new FileInputStream("src/main/resources/mapper/PaymentMapper.xml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public List<PaymentDTO> selectAllgifticon(Connection con) {
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        String sql = prop.getProperty("selectAllgifticon");
        System.out.println("sql = " + sql);

        List<PaymentDTO> paymentDTOList = null;

        try {
            pstmt = con.prepareStatement(sql);
            rset = pstmt.executeQuery();
            paymentDTOList = new ArrayList<>();
            while (rset.next()) {
                PaymentDTO paymentDTO = new PaymentDTO();
                paymentDTO.setGift_id(rset.getInt("gift_id"));
                paymentDTO.setBarcode(rset.getString("barcode"));
                paymentDTO.setDiscount(rset.getInt("discount"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
            close(pstmt);
            close(rset);
        }
        return paymentDTOList;
    }
    public boolean existsByBarcode(Connection con, String barcode) {
        boolean exists = false;
        String query = "SELECT COUNT(*) FROM gifticon WHERE barcode = ?";

        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setString(1, barcode);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                exists = rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace(); // 예외 처리 또는 로깅
        }

        return exists;
    }
    public int findDiscountByBarcode(Connection con, String barcode) {
        int discount = 0;
        String query = "SELECT discount_price FROM gifticon WHERE barcode = ?";  // 컬럼명 수정

        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setString(1, barcode);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                discount = rs.getInt("discount_price");  // 컬럼명 수정
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return discount;
    }
    public List<CartItemDto> selectAllCart(Connection con) {
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        String sql = prop.getProperty("selectAllCart");
        System.out.println("sql = " + sql);

        List<CartItemDto> cartItemDtoList = null;

        try {
            pstmt = con.prepareStatement(sql);
            rset = pstmt.executeQuery();
            cartItemDtoList = new ArrayList<>();
            while (rset.next()) {
                CartItemDto cartItemDto = new CartItemDto((rset.getInt("menu_id"))
                                                            , rset.getString("menu_name")
                                                            , rset.getInt("price")
                                                            ,rset.getInt("quantity"));
                cartItemDtoList.add(cartItemDto);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
            close(pstmt);
            close(rset);
        }
        return cartItemDtoList;
    }

    public int insertMenu(Connection con, OrderDTO order) {
        PreparedStatement pstmt = null;
        int result = 0;

        try {
            String sql = prop.getProperty("insertOrder");
            pstmt = con.prepareStatement(sql);
            pstmt.setTimestamp(1, Timestamp.valueOf(order.getOrderTime()));
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
        }
        return result;
    }

    public static int getOrderId(Connection con) {
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        int orderId = 0;

        String sql = "SELECT MAX(order_id) FROM `order`";
        try {
            pstmt = con.prepareStatement(sql);
            rset = pstmt.executeQuery();
            if (rset.next()) {
                orderId = rset.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
            close(rset);
        }
        return orderId;
    }

    public int insertOrderItem(Connection con, int orderId, CartItemDto item) {
        PreparedStatement pstmt = null;
        int result = 0;
        try {
            String sql = prop.getProperty("insertOrderItem");
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, orderId);
            pstmt.setInt(2, item.getMenuId());
            pstmt.setInt(3, item.getQuantity());

            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
        }
        return result;
    }
}

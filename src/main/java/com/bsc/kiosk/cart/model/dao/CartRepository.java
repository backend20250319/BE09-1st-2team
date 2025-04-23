package com.bsc.kiosk.cart.model.dao;

import com.bsc.kiosk.cart.model.dto.CartItemDto;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static com.bsc.kiosk.common.JDBCTemplate.close;
import static com.bsc.kiosk.common.JDBCTemplate.commit;

public class CartRepository {
    private Properties prop = null;

    public CartRepository() {
        prop = new Properties();
        try {
            prop.loadFromXML(new FileInputStream("src/main/resources/mapper/CartMapper.xml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<CartItemDto> selectAllCart(Connection con) {

        PreparedStatement pstmt = null;
        ResultSet rset = null;
        String sql = prop.getProperty("selectAllCart");

        List<CartItemDto> cart;
        try {
            pstmt = con.prepareStatement(sql);

            rset = pstmt.executeQuery();
            cart = new ArrayList<>();
            while (rset.next()) {
                CartItemDto cartItem = new CartItemDto(
                        rset.getInt("menu_id"),
                        rset.getString("menu_name"),
                        rset.getInt("price"),
                        rset.getInt("menu_quantity")
                );

                cart.add(cartItem);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
            close(rset);
        }

        return cart;
    }

    public int updateCart(Connection con, int menuId, int quantity) {
        PreparedStatement pstmt = null;
        String sql = prop.getProperty("updateCart");


        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, quantity);
            pstmt.setInt(2, menuId);

            return pstmt.executeUpdate(); // update count
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
        }
    }

    public int deleteCart(Connection con, int menuId) {
        PreparedStatement pstmt = null;
        String sql = prop.getProperty("deleteCart");

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, menuId);

            return pstmt.executeUpdate(); // delete count
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
        }
    }
}

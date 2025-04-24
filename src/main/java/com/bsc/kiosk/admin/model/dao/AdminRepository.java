package com.bsc.kiosk.admin.model.dao;

import com.bsc.kiosk.admin.model.dto.AdminDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import static com.bsc.kiosk.common.JDBCTemplate.close;

public class AdminRepository {
    private final Properties prop;

    public AdminRepository() {
        prop = new Properties();
        try {
            prop.loadFromXML(new FileInputStream("src/main/resources/mapper/adminMapper.xml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int insertMenu(Connection con, AdminDTO admins) {
        PreparedStatement pstmt = null;
        int result = 0;

        try {
            String sql = prop.getProperty("insertMenu");

            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, admins.getMenu_category_id());
            pstmt.setString(2, admins.getMenu_name());
            pstmt.setInt(3, admins.getPrice());

            result = pstmt.executeUpdate();
            System.out.println("result = " + result);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
        }
        System.out.println("[AdminRepository] insertMenu() result ====> " + result);
        return result;
    }

    public int updateMenu(Connection con, AdminDTO admins) {
        PreparedStatement pstmt = null;
        int result = 0;
        try {
        String sql = prop.getProperty("updateMenu");

        pstmt = con.prepareStatement(sql);

        pstmt.setString(1, admins.getMenu_name());
        pstmt.setInt(2, admins.getPrice());
        pstmt.setString(3, admins.getIs_sold_out());
        pstmt.setInt(4, admins.getMenu_id());
        result = pstmt.executeUpdate();
    } catch (SQLException e) {
        throw new RuntimeException(e);
    } finally {
        close(pstmt);
    }
        return result;
    }

    public int deleteMenu(Connection con, AdminDTO admins) {
    PreparedStatement pstmt = null;
    int result = 0;
        try {
        String sql = prop.getProperty("deleteMenu");
        pstmt = con.prepareStatement(sql);

        pstmt.setInt(1, admins.getMenu_id());
        result = pstmt.executeUpdate();
    } catch (SQLException e) {
        throw new RuntimeException(e);
    } finally {
        close(pstmt);
    }
        return result;
    }
}

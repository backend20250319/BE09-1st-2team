package com.bsc.kiosk.menu.model.dao;

import com.bsc.kiosk.menu.model.dto.MenuDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static com.bsc.kiosk.cart.controller.CartController.cs;
import static com.bsc.kiosk.common.JDBCTemplate.close;

public class MenuRepository {

    private final Properties prop;

    public MenuRepository() {
        prop = new Properties();
        try {
            prop.loadFromXML(new FileInputStream("src/main/resources/mapper/MenuMapper.xml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<MenuDTO> getChickenMenu(Connection con) {
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        String sql = prop.getProperty("selectChickenMenu");
        List<MenuDTO> chickenMenu = new ArrayList<>();
        try {
            pstmt = con.prepareStatement(sql);
            rset = pstmt.executeQuery();
            while (rset.next()) {
                MenuDTO menu = new MenuDTO();
                menu.setMenuName(rset.getString("menu_name"));
                menu.setPrice(rset.getInt("price"));
                menu.setMenuId(rset.getInt("menu_id"));
                chickenMenu.add(menu);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
            close(rset);
        }
        return chickenMenu;
    }

    public List<MenuDTO> getSideMenu(Connection con) {
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        String sql = prop.getProperty("selectSideMenu");
        List<MenuDTO> chickenMenu = null;
        try {
            pstmt = con.prepareStatement(sql);
            rset = pstmt.executeQuery();
            chickenMenu = new ArrayList<>();
            while (rset.next()) {
                MenuDTO menu = new MenuDTO();
                menu.setMenuName(rset.getString("menu_name"));
                menu.setPrice(rset.getInt("price"));
                menu.setMenuId(rset.getInt("menu_id"));
                chickenMenu.add(menu);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
            close(rset);
        }
        return chickenMenu;
    }


    public List<MenuDTO> getDrinkMenu(Connection con) {
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        String sql = prop.getProperty("selectDrinkMenu");
        List<MenuDTO> chickenMenu = null;
        try {
            pstmt = con.prepareStatement(sql);
            rset = pstmt.executeQuery();
            chickenMenu = new ArrayList<>();
            while (rset.next()) {
                MenuDTO menu = new MenuDTO();
                menu.setMenuName(rset.getString("menu_name"));
                menu.setPrice(rset.getInt("price"));
                menu.setMenuId(rset.getInt("menu_id"));
                chickenMenu.add(menu);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
            close(rset);
        }
        return chickenMenu;
    }


    public List<MenuDTO> findAllmenu(Connection con) {
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        String sql = prop.getProperty("searchAllMenu");
        List<MenuDTO> allMenu = null;
        try {
            pstmt = con.prepareStatement(sql);
            rset = pstmt.executeQuery();
            allMenu = new ArrayList<>();
            while(rset.next()) {
                MenuDTO menu = new MenuDTO();
                menu.setMenuId(rset.getInt("menu_id"));
                menu.setMenuName(rset.getString("menu_name"));
                menu.setPrice(rset.getInt("price"));
                allMenu.add(menu);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
            close(rset);
        }
        return allMenu;
    }

    public void insertIntoCart(int menuId, int quantity) {
        cs.createCart(menuId, quantity);
    }
}

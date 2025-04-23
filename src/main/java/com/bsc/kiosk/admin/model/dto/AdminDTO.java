package com.bsc.kiosk.admin.model.dto;

public class AdminDTO {
    private int menu_id;
    private int menu_category_id;
    private String menu_name;
    private int price;
    private String is_sold_out;

    public AdminDTO(int menu_category_id, String menu_name, int price) {
        this.menu_category_id = menu_category_id;
        this.menu_name = menu_name;
        this.price = price;
    }

    public AdminDTO(int menu_id, String menu_name, int price, String is_sold_out) {
        this.menu_id = menu_id;
        this.menu_name = menu_name;
        this.price = price;
        this.is_sold_out = is_sold_out;
    }

    public AdminDTO(int menu_id) {
        this.menu_id = menu_id;
    }

    public int getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(int menu_id) {
        this.menu_id = menu_id;
    }

    public int getMenu_category_id() {
        return menu_category_id;
    }

    public String getIs_sold_out() {
        return is_sold_out;
    }

    public void setIs_sold_out(String is_sold_out) {
        this.is_sold_out = is_sold_out;
    }

    public void setMenu_category_id(int menu_category_id) {
        this.menu_category_id = menu_category_id;
    }

    public String getMenu_name() {
        return menu_name;
    }

    public void setMenu_name(String menu_name) {
        this.menu_name = menu_name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "[" +
                "menu_category_id=" + menu_category_id +
                ", menu_name='" + menu_name + '\'' +
                ", price=" + price +
                ", is_sold_out=" + is_sold_out +
                ']';
    }
}

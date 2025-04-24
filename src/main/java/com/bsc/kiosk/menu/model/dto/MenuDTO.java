package com.bsc.kiosk.menu.model.dto;

public class MenuDTO {

    private Integer menuId;
    private Integer menuCategoryId;
    private String menuName;
    private Integer price;
    private Boolean isSoldOut;

    public MenuDTO() {
    }

    public MenuDTO(String menuName, Integer price) {
        this.menuName = menuName;
        this.price = price;
    }

    public MenuDTO(Integer menuId, Integer menuCategoryId, String menuName, Integer price, Boolean isSoldOut) {
        this.menuId = menuId;
        this.menuCategoryId = menuCategoryId;
        this.menuName = menuName;
        this.price = price;
        this.isSoldOut = isSoldOut;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public Integer getMenuCategoryId() {
        return menuCategoryId;
    }

    public void setMenuCategoryId(Integer menuCategoryId) {
        this.menuCategoryId = menuCategoryId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Boolean getSoldOut() {
        return isSoldOut;
    }

    public void setSoldOut(Boolean soldOut) {
        isSoldOut = soldOut;
    }

    @Override
    public String toString() {
        return "MenuDTO{" +
                "menuId=" + menuId +
                ", menuCategoryId=" + menuCategoryId +
                ", menuName='" + menuName + '\'' +
                ", price=" + price +
                ", isSoldOut=" + isSoldOut +
                '}';
    }
}

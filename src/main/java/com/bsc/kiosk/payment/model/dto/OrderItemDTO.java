package com.bsc.kiosk.payment.model.dto;

public class OrderItemDTO {
    private int itemId;
    private int orderId;
    private int menu_id;
    private int item_quantity;

    public OrderItemDTO(int orderId, int menu_id, int item_quantity) {
        this.orderId = orderId;
        this.menu_id = menu_id;
        this.item_quantity = item_quantity;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(int menu_id) {
        this.menu_id = menu_id;
    }

    public int getItem_quantity() {
        return item_quantity;
    }

    public void setItem_quantity(int item_quantity) {
        this.item_quantity = item_quantity;
    }
}

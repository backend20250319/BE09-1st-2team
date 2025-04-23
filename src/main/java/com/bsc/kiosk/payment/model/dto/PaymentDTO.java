package com.bsc.kiosk.payment.model.dto;

public class PaymentDTO {
    private String menu_name;
    private int price;
    private int quantity;
    private int gift_id;
    private String barcode;
    private int discount;

    public PaymentDTO() {
        this.barcode = barcode;
        this.discount = discount;
        this.gift_id = gift_id;
        this.menu_name = menu_name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getGift_id() {
        return gift_id;
    }

    public void setGift_id(int gift_id) {
        this.gift_id = gift_id;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "PaymentDTO{" +
                "barcode='" + barcode + '\'' +
                ", menu_name='" + menu_name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", gift_id=" + gift_id +
                ", discount=" + discount +
                '}';
    }
}


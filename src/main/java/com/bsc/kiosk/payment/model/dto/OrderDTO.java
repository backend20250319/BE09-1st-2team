package com.bsc.kiosk.payment.model.dto;

import java.time.LocalDateTime;

public class OrderDTO {
    private int orderId;
    private LocalDateTime orderTime;

    public OrderDTO(LocalDateTime orderTime) {
        this.orderTime = orderTime;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
    }
}

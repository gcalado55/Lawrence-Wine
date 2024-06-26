package com.wine.Domain.Entities;

public enum OrderStatus {

    AWAITING_PAYMENT(1),
    PAYMENT_CONFIRMED(2),
    IN_SEPARATION(3),
    IN_PREPARATION(4),
    SHIPPED(5),
    IN_TRANSIT(6),
    DELIVERED(7),
    CANCELED(8);

    private int code;

    private OrderStatus(int code){
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static OrderStatus valueOf(int code){
        for(OrderStatus value: OrderStatus.values()){
            if(value.getCode() == code){
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid Order Status Code");
    }
}

package com.movers.eazymovers.common.enums;

public enum OrderStatus {
    SUBMIT("Submitted",1),
    PENDING("Pending",2),
    CANCEL("Canceled",3),
    REORDER("Reorder",4),
    COMPLETE("Completed",5),
    WITHDRAW("Withdrawn",6);

    String name;
    int value;

    OrderStatus(String name, int value){
        this.name = name;
        this.value = value;
    }

    public static OrderStatus fromVale(int val){
        for(OrderStatus status: OrderStatus.values()){
            if(status.value == val){
                return status;
            }
        }
        return null;
    }

    public static int getValue(OrderStatus status){
        return status.value;
    }
}

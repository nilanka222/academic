package com.movers.eazymovers.common.enums;

public enum UserStatus {
    PENDING("Pending",1),
    ACTIVE("Active",2),
    INACTIVE("Inactive",3);

    String name;
    int value;

    UserStatus(String name, int value){
        this.name = name;
        this.value = value;
    }

    public static UserStatus fromVale(int val){
        for(UserStatus status: UserStatus.values()){
            if(status.value == val){
                return status;
            }
        }
        return null;
    }

    public static int getValue(UserStatus status){
        return status.value;
    }
}

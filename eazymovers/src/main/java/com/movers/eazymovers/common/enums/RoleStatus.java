package com.movers.eazymovers.common.enums;

public enum RoleStatus {
    ACTIVE("Active",1),
    INACTIVE("Inactive",2);

    String name;
    int value;

    RoleStatus(String name, int value){
        this.name = name;
        this.value = value;
    }

    public static RoleStatus fromVale(int val){
        for(RoleStatus status: RoleStatus.values()){
            if(status.value == val){
                return status;
            }
        }
        return null;
    }

    public static int getValue(RoleStatus status){
        return status.value;
    }
}

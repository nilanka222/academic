package com.movers.eazymovers.common.response;

import lombok.Data;

@Data
public class Result<T> {

    private T data;
    private String message;
    private boolean success;

}

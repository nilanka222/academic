package com.movers.eazymovers.common.response;

import lombok.Data;

import java.util.List;

@Data
public class ResultList<T> {
    private List<T> data;
    private String message;
    private boolean success;
    private int pageSize;
    private int currentPage;
    private long totalRecords;
}

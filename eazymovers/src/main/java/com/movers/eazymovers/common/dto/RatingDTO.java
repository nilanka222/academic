package com.movers.eazymovers.common.dto;

import com.movers.eazymovers.dal.entity.Order;
import lombok.Data;

import java.io.Serializable;

@Data

public class RatingDTO implements Serializable {

    private Long id;

    private Order order;

    private Integer consumerScore;

    private Integer serviceProviderScore;

    private String commentsForConsumer;

    private String CommentsForProvider;


}

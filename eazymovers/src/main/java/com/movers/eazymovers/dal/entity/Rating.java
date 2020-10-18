package com.movers.eazymovers.dal.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "order_rating")
public class Rating implements Serializable {

    @Id
    @GeneratedValue
    private Long id;


   @OneToOne(targetEntity = Order.class,cascade = CascadeType.ALL)
   @JoinColumn(name="order_id")
    private Order order;

    @Column(name="consumer_score")
    private Integer consumerScore;

    @Column(name="sp_score")
    private Integer serviceProviderScore;

    @Column(name="comments_for_consumer")
    private String commentsForConsumer;

    @Column(name="comments_for_sp")
    private String CommentsForProvider;


}

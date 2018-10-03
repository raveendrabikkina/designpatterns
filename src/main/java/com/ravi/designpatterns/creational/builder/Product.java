package com.ravi.designpatterns.creational.builder;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by ravi on 1/10/18.
 */
@Builder
@EqualsAndHashCode
@ToString
class Product {
    private int id;
    private String name;
    private String colour;
    private BigDecimal offer;
    private List<String> features;
    private List<WEEK_DAYS> availableDays;
    private FreeProduct freeProduct;
}

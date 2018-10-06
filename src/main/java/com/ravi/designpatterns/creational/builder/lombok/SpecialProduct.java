package com.ravi.designpatterns.creational.builder.lombok;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by ravi on 1/10/18.
 */
@Data
public class SpecialProduct {
    private int id;
    private String name;
    private String colour;
    private BigDecimal offer;
    private List<String> features;
    private WEEK_DAYS availableDays;
}

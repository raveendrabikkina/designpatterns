package com.ravi.designpatterns.creational.builder.general;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

/**
 * Created by ravi on 1/10/18.
 */
public final class SpecialProduct {

    private final int id;
    private final String name;
    private final String colour;
    private final BigDecimal offer;
    private final List<String> features;
    private final WEEK_DAYS availableDays;

    public SpecialProduct(final int id, final String name, final String colour, final BigDecimal offer, final List<String> features, final WEEK_DAYS availableDays) {
        this.id = id;
        this.name = name;
        this.colour = colour;
        this.offer = offer;
        this.features = features;
        this.availableDays = availableDays;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getColour() {
        return colour;
    }

    public BigDecimal getOffer() {
        return offer;
    }

    public List<String> getFeatures() {
        return Collections.unmodifiableList(features);
    }

    public WEEK_DAYS getAvailableDays() {
        return availableDays;
    }
}

package com.ravi.designpatterns.creational.builder.general;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

/**
 * Created by ravi on 1/10/18.
 */

public class Product {
    private final int id;
    private final String name;
    private final String colour;
    private final BigDecimal offer;
    private final List<String> features;
    private final List<WEEK_DAYS> availableDays;
    private final FreeProduct freeProduct;

    private Product(final Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.colour = builder.colour;
        this.offer = builder.offer;
        this.features = builder.features;
        this.availableDays = builder.availableDays;
        this.freeProduct = builder.freeProduct;
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

    public List<WEEK_DAYS> getAvailableDays() {
        return Collections.unmodifiableList(availableDays);
    }

    public FreeProduct getFreeProduct() {
        return freeProduct;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", colour='" + colour + '\'' +
                ", offer=" + offer +
                ", features=" + features +
                ", availableDays=" + availableDays +
                ", freeProduct=" + freeProduct +
                '}';
    }

    public static class Builder {
        private int id;
        private String name;
        private String colour;
        private BigDecimal offer;
        private List<String> features;
        private List<WEEK_DAYS> availableDays;
        private FreeProduct freeProduct;


        public Builder id(final int id) {
            this.id = id;
            return this;
        }

        public Builder name(final String name) {
            this.name = name;
            return this;
        }

        public Builder colour(final String colour) {
            this.colour = colour;
            return this;
        }

        public Builder offer(final BigDecimal offer) {
            this.offer = offer;
            return this;
        }

        public Builder features(final List<String> features) {
            this.features = features;
            return this;
        }

        public Builder availableDays(final List<WEEK_DAYS> availableDays) {
            this.availableDays = availableDays;
            return this;
        }

        public Builder freeProduct(final FreeProduct freeProduct) {
            this.freeProduct = freeProduct;
            return this;
        }

        public Product build() {

            return new Product(this);
        }
    }
}

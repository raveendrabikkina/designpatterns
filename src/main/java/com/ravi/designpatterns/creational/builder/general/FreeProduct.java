package com.ravi.designpatterns.creational.builder.general;

/**
 * Created by ravi on 3/10/18.
 */
public final class FreeProduct {

    private final int id;
    private final String name;

    public FreeProduct(final int id, final String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
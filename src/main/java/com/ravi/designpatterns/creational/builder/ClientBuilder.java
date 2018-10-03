package com.ravi.designpatterns.creational.builder;

import java.math.BigDecimal;
import java.util.Arrays;

import static com.ravi.designpatterns.creational.builder.WEEK_DAYS.*;
import static com.ravi.designpatterns.creational.builder.WEEK_DAYS.FRIDAY;

/**
 * Created by ravi on 1/10/18.
 */
class ClientBuilder {

    public static void main(final String[] args) {
        testFreeProductBuilder();
    }

    public static void testFreeProductBuilder() {
        FreeProduct freeProduct = new FreeProduct();
        freeProduct.setId(123);
        freeProduct.setName("Magic Mouse");

        Product p = Product.builder()
                .colour("SILVER")
                .name("MAC BOOK PRO")
                .features(Arrays.asList("16 GB RAM", "1TB Hard disk", "Touch Bar", "Touch ID"))
                .freeProduct(freeProduct)
                .offer(new BigDecimal(2000))
                .availableDays(Arrays.asList(MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY))
                .build();
        System.out.println(p);
    }
}

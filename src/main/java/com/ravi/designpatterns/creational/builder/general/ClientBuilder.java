package com.ravi.designpatterns.creational.builder.general;

import java.math.BigDecimal;
import java.util.Arrays;

import static com.ravi.designpatterns.creational.builder.general.WEEK_DAYS.MONDAY;
import static com.ravi.designpatterns.creational.builder.general.WEEK_DAYS.WEDNESDAY;

/**
 * Created by ravi on 1/10/18.
 */
class ClientBuilder {

    public static void main(final String[] args) {
        testFreeProductBuilder();
    }

    public static void testFreeProductBuilder() {
        final FreeProduct freeProduct = new FreeProduct(123, "Magic Mouse");

        final Product p = new Product.Builder()
                .id(12345)
                .name("Mac Book Pro 15\"")
                .availableDays(Arrays.asList(MONDAY, WEDNESDAY))
                .colour("RED")
                .features(Arrays.asList("16 GB RAM", "1TB Hard disk", "Touch Bar", "Touch ID"))
                .offer(new BigDecimal("2000.00"))
                .freeProduct(freeProduct)
                .build();
                /*
                .colour("SILVER")
                .name("MAC BOOK PRO")
                .features(Arrays.asList("16 GB RAM", "1TB Hard disk", "Touch Bar", "Touch ID"))
                .freeProduct(freeProduct)
                .offer(new BigDecimal(2000))
                .availableDays(Arrays.asList(com.ravi.designpatterns.creational.builder.lombok.WEEK_DAYS.MONDAY, com.ravi.designpatterns.creational.builder.lombok.WEEK_DAYS.TUESDAY, com.ravi.designpatterns.creational.builder.lombok.WEEK_DAYS.WEDNESDAY, com.ravi.designpatterns.creational.builder.lombok.WEEK_DAYS.THURSDAY, WEEK_DAYS.FRIDAY))
                .build();*/
        System.out.println(p);
        p.getAvailableDays().stream().forEach(day -> System.out.println(day.name() + ":" + day.getOpenHours()));


    }
}

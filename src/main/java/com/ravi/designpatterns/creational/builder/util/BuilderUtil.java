package com.ravi.designpatterns.creational.builder.util;

import com.ravi.designpatterns.creational.builder.lombok.FreeProduct;
import com.ravi.designpatterns.creational.builder.lombok.Product;
import com.ravi.designpatterns.creational.builder.lombok.WEEK_DAYS;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 * Created by ravi on 6/10/18.
 */
public class BuilderUtil {


    public static void testFreeProductBuilder() {
        final FreeProduct freeProduct = new FreeProduct();
        freeProduct.setId(123);
        freeProduct.setName("Magic Mouse");

        final Product p = Product.builder()
                .colour("SILVER")
                .name("MAC BOOK PRO")
                .features(Arrays.asList("16 GB RAM", "1TB Hard disk", "Touch Bar", "Touch ID"))
                .freeProduct(freeProduct)
                .offer(new BigDecimal(2000))
                .availableDays(Arrays.asList(WEEK_DAYS.MONDAY, WEEK_DAYS.TUESDAY, WEEK_DAYS.WEDNESDAY, WEEK_DAYS.THURSDAY, WEEK_DAYS.FRIDAY))
                .build();
        System.out.println(p);
        p.getAvailableDays().stream().forEach(day -> System.out.println(day.name() + ":" + day.getOpenHours()));

        
    }
}

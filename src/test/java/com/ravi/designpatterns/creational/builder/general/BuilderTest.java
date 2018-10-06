package com.ravi.designpatterns.creational.builder.general;

import com.anarsoft.vmlens.concurrent.junit.ConcurrentTestRunner;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.math.BigDecimal;
import java.util.Arrays;

import static com.ravi.designpatterns.creational.builder.general.WEEK_DAYS.MONDAY;
import static com.ravi.designpatterns.creational.builder.general.WEEK_DAYS.WEDNESDAY;
import static org.junit.Assert.assertEquals;

/**
 * Created by ravi on 6/10/18.
 */
@RunWith(ConcurrentTestRunner.class)
public class BuilderTest {
    final FreeProduct freeProduct = new FreeProduct(123, "Magic Mouse");

    final Product product = new Product.Builder()
            .id(12345)
            .name("Mac Book Pro 15\"")
            .availableDays(Arrays.asList(MONDAY, WEDNESDAY))
            .colour("RED")
            .features(Arrays.asList("16 GB RAM", "1TB Hard disk", "Touch Bar", "Touch ID"))
            .offer(new BigDecimal("2000.00"))
            .freeProduct(freeProduct)
            .build();

    @Test
    public void addOne() {
        product.getTimesBought().getAndIncrement();
        System.out.println(product.getTimesBought().intValue());
    }

    @After
    public void testCount() {
        assertEquals("4 Threads running buying product in parallel should lead to 4 times bought", 4, product.getTimesBought().intValue()
        );
    }

}
package com.ravi.designpatterns.creational.builder.lombok;

import lombok.Getter;

/**
 * Created by ravi on 1/10/18.
 */
@Getter
public enum WEEK_DAYS {

    MONDAY("9 AM to 5 PM"),
    TUESDAY("3 AM to 5 PM"),
    WEDNESDAY("11 AM to 5 PM"),
    THURSDAY("9 AM to 3 PM"),
    FRIDAY("8:30 AM to 1 PM");
    private final String openHours;

    WEEK_DAYS(final String openHours) {
        this.openHours = openHours;
    }
}
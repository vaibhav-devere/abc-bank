package com.abc;

import java.time.Instant;
import java.util.Date;

public class DateProvider {
    private static DateProvider instance = null;

    private DateProvider() {
    }


    //Calendar for somewhat older version and less efficent in comparison with java.time

    public static DateProvider getInstance() {
        if (instance == null) {
            synchronized (DateProvider.class) {
                if (instance == null) {
                    instance = new DateProvider();
                }
            }
        }
        return instance;
    }

    public Date now() {
        return Date.from(Instant.now());
    }
}

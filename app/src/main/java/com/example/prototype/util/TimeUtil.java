package com.example.prototype.util;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TimeUtil {
    public static boolean isToday(LocalDateTime time) {
        LocalDate today = LocalDate.now();
        LocalDate date = time.toLocalDate();
        return date.equals(today);
    }
}

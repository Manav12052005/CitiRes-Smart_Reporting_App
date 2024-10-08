package com.example.prototype;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class TimeUtil {
    public static boolean isToday(LocalDateTime time) {
        LocalDate today = LocalDate.now();
        LocalDate date = time.toLocalDate();
        return date.equals(today);
    }
}

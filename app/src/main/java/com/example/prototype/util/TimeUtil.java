package com.example.prototype.util;

import com.example.prototype.data.DataHolder;
import com.example.prototype.entity.Report;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class TimeUtil {
    public static boolean isToday(LocalDateTime time) {
        LocalDate today = LocalDate.now();
        LocalDate date = time.toLocalDate();
        return date.equals(today);
    }

    public static int getPostsToday() {
        int count = 0;
        List<Report> reports = DataHolder.avlTree.fromLargeToSmall();
        for (Report report : reports) {
            if (isToday(report.getLocalDateTime())) {
                count++;
            }
        }
        return count;
    }
}

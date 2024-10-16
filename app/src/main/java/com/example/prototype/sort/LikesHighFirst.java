package com.example.prototype.sort;

import com.example.prototype.entity.Report;

import java.util.List;

/**
 * Class for Sorting based on Likes.
 * @author Harry Xia u7556816 original author for sorting feature
 * @author Yuan Shi u7787385 Refactored the class using Factory Design Pattern.
 */
public class LikesHighFirst implements ReportSorter {
    @Override
    public List<Report> sort(List<Report> reports) {
        reports.sort((report1, report2) -> Integer.compare(report2.getLikes(), report1.getLikes()));
        return reports;
    }
}

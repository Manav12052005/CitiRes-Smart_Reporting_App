package com.example.prototype.sort;

import com.example.prototype.entity.Report;

import java.util.List;

/**
 * Class for Sorting based on newest first.
 * @author Harry Xia u7556816 original author for sorting feature
 * @author Yuan Shi u7787385 Refactored the class using Factory Design Pattern.
 */
public class NewestFirst implements ReportSorter {
    @Override
    public List<Report> sort(List<Report> reports) {
        reports.sort((r1, r2) -> r2.getLocalDateTime().compareTo(r1.getLocalDateTime()));
        return reports;
    }
}

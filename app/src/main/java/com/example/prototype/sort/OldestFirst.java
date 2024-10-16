package com.example.prototype.sort;

import com.example.prototype.entity.Report;

import java.util.Comparator;
import java.util.List;

/**
 * Class for Sorting based on oldest first.
 * @author Harry Xia u7556816 original author for sorting feature
 * @author Yuan Shi u7787385 Refactored the class using Factory Design Pattern.
 */
public class OldestFirst implements ReportSorter {
    @Override
    public List<Report> sort(List<Report> reports) {
        reports.sort(Comparator.comparing(Report::getLocalDateTime));
        return reports;
    }
}

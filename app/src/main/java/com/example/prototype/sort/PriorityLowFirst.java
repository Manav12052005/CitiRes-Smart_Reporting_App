package com.example.prototype.sort;

import com.example.prototype.entity.Report;
import com.example.prototype.util.PriorityUtil;

import java.util.List;

/**
 * Class for Sorting based on priority low first.
 * @author Harry Xia u7556816 original author for sorting feature
 * @author Yuan Shi u7787385 Refactored the class using Factory Design Pattern.
 */
public class PriorityLowFirst implements ReportSorter {
    @Override
    public List<Report> sort(List<Report> reports) {
        reports.sort((report1, report2) -> PriorityUtil.comparePriority(report1.getPriority(), report2.getPriority()));
        return reports;
    }
}

package com.example.prototype.sort;

import com.example.prototype.entity.Report;
import com.example.prototype.util.PriorityUtil;

import java.util.List;

/**
 * Class for Sorting based on priority high first.
 * @author Harry Xia u7556816 original author for sorting feature
 * @author Yuan Shi u7787385 Refactored the class using Factory Design Pattern.
 */
public class PriorityHighFirst implements ReportSorter {
    @Override
    public List<Report> sort(List<Report> reports) {
        reports.sort((report1, report2) -> PriorityUtil.comparePriority(report2.getPriority(), report1.getPriority()));
        return reports;
    }
}

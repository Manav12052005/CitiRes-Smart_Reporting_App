package com.example.prototype.sort;

import com.example.prototype.entity.Report;
import com.example.prototype.util.PriorityUtil;

import java.util.List;

public class PriorityHighFirst implements ReportSorter {
    @Override
    public List<Report> sort(List<Report> reports) {
        reports.sort((report1, report2) -> PriorityUtil.comparePriority(report2.getPriority(), report1.getPriority()));
        return reports;
    }
}

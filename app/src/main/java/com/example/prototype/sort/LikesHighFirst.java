package com.example.prototype.sort;

import com.example.prototype.entity.Report;

import java.util.List;

public class LikesHighFirst implements ReportSorter {
    @Override
    public List<Report> sort(List<Report> reports) {
        reports.sort((report1, report2) -> Integer.compare(report2.getLikes(), report1.getLikes()));
        return reports;
    }
}

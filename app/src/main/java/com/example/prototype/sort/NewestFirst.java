package com.example.prototype.sort;

import com.example.prototype.entity.Report;

import java.util.List;

public class NewestFirst implements ReportSorter {
    @Override
    public List<Report> sort(List<Report> reports) {
        reports.sort((r1, r2) -> r2.getLocalDateTime().compareTo(r1.getLocalDateTime()));
        return reports;
    }
}

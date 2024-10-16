package com.example.prototype.sort;

import com.example.prototype.entity.Report;

import java.util.Comparator;
import java.util.List;

public class OldestFirst implements ReportSorter {
    @Override
    public List<Report> sort(List<Report> reports) {
        reports.sort(Comparator.comparing(Report::getLocalDateTime));
        return reports;
    }
}

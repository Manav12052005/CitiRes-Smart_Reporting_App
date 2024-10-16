package com.example.prototype.sort;

import com.example.prototype.entity.Report;

import java.util.List;

public interface ReportSorter {
    List<Report> sort(List<Report> reports);
}

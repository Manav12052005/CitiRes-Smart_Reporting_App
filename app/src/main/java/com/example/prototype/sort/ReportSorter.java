package com.example.prototype.sort;

import com.example.prototype.entity.Report;

import java.util.List;

/**
 * Interface for sorting.
 * @author Yuan Shi u7787835
 */
public interface ReportSorter {
    List<Report> sort(List<Report> reports);
}

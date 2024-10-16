package com.example.prototype.report;

/**
 * Class for generating report-id
 * @author Yuan Shi u7787385
 */
public class ReportCounter {
    static int reportId = 2501;

    public static int getReportId() {
        return reportId;
    }

    public static void inc() {
        reportId++;
    }
}

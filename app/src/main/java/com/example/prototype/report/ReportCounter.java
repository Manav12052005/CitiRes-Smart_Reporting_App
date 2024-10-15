package com.example.prototype.report;

public class ReportCounter {
    static int reportId = 2501;

    public static int getReportId() {
        return reportId;
    }

    public static void inc() {
        reportId++;
    }
}

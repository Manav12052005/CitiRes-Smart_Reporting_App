package com.example.prototype;

public class ReportCounter {
    static int reportId = 2500;

    public static int getReportId() {
        return reportId;
    }

    public static void inc() {
        reportId++;
    }
}

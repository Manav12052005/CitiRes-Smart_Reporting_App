package com.example.prototype;

import static org.junit.Assert.assertEquals;

import com.example.prototype.report.ReportCounter;

import org.junit.Test;

public class ReportTest {
    @Test
    public void testReportCounter() {
        assertEquals(2501, ReportCounter.getReportId());
        for (int i = 0; i < 1000; i++) {
            assertEquals(2501 + i, ReportCounter.getReportId());
            ReportCounter.inc();
        }
    }
}

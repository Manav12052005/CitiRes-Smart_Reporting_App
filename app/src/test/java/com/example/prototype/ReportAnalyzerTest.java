package com.example.prototype;

import static org.junit.Assert.assertEquals;
import com.example.prototype.chart.ReportAnalyzer;
import com.example.prototype.entity.Category;
import com.example.prototype.entity.Priority;
import com.example.prototype.entity.Report;
import com.example.prototype.entity.User;
import org.junit.Before;
import org.junit.Test;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ReportAnalyzerTest {

    private ReportAnalyzer reportAnalyzer;

    @Before
    public void setUp() {
        reportAnalyzer = new ReportAnalyzer();
    }

    private List<Report> createTestReports() {
        List<Report> reports = new ArrayList<>();
        reports.add(new Report(1, "Report 1", "Location1", Priority.LOW, new User("aaa"), Category.Maintenance, LocalDateTime.now(), 5));
        reports.add(new Report(2, "Report 2", "Location2", Priority.MIDDLE, new User("aaa"), Category.Maintenance, LocalDateTime.now(), 3));
        reports.add(new Report(3, "Report 3", "Location1", Priority.HIGH, new User("bbb"), Category.Environmental, LocalDateTime.now(), 10));
        reports.add(new Report(4, "Report 4", "Location2", Priority.HIGH, new User("bbb"), Category.Environmental, LocalDateTime.now(), 7));
        reports.add(new Report(5, "Report 5", "Location2", Priority.HIGH, new User("ccc"), Category.Infrastructure, LocalDateTime.now(), 12));
        reports.add(new Report(6, "Report 6", "Location1", Priority.LOW, new User("ccc"), Category.Maintenance, LocalDateTime.now(), 1));
        return reports;
    }

    @Test
    public void testGetPriorityCounts() {
        List<Report> reports = createTestReports();
        Map<String, Integer> priorityCounts = reportAnalyzer.getPriorityCounts(reports);

        assertEquals(2, (int) priorityCounts.get("LOW"));
        assertEquals(1, (int) priorityCounts.get("MIDDLE"));
        assertEquals(3, (int) priorityCounts.get("HIGH"));
    }

    @Test
    public void testGetCategoryCounts() {
        List<Report> reports = createTestReports();
        Map<String, Integer> categoryCounts = reportAnalyzer.getCategoryCounts(reports);

        assertEquals(3, (int) categoryCounts.get("Maintenance"));
        assertEquals(2, (int) categoryCounts.get("Environmental"));
        assertEquals(1, (int) categoryCounts.get("Infrastructure"));
    }

    @Test
    public void testGetLocationCounts() {
        List<Report> reports = createTestReports();
        Map<String, Integer> locationCounts = reportAnalyzer.getLocationCounts(reports);

        assertEquals(3, (int) locationCounts.get("Location1"));
        assertEquals(3, (int) locationCounts.get("Location2"));
    }
}

package com.example.prototype;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

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

/**
* @author Amogh Agarwal u7782814
 * This class is used to test the ReportAnalyzer class and fix the various cases. I have implemented
 * the test cases as well as made changes to the code in the reportAnalyzer class.
 * I have used ChatGPT forhelp with the test cases.
 */
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
        List<Report> nullCase = new ArrayList<>();
        Map<String, Integer> priorityCounts = reportAnalyzer.getPriorityCounts(reports);
        Map<String, Integer> nullCounts = reportAnalyzer.getPriorityCounts(nullCase);

        assertEquals(2, (int) priorityCounts.get("LOW"));
        assertEquals(1, (int) priorityCounts.get("MIDDLE"));
        assertEquals(3, (int) priorityCounts.get("HIGH"));
        assertEquals(0, (int) nullCounts.get("LOW"));
        assertEquals(0, (int) nullCounts.get("MIDDLE"));
        assertEquals(0, (int) nullCounts.get("HIGH"));
    }

    @Test
    public void testGetCategoryCounts() {
        List<Report> reports = createTestReports();
        List<Report> nullCase = new ArrayList<>();
        Map<String, Integer> categoryCounts = reportAnalyzer.getCategoryCounts(reports);
        Map<String, Integer> nullCounts = reportAnalyzer.getCategoryCounts(nullCase);

        assertEquals(3, (int) categoryCounts.get("Maintenance"));
        assertEquals(2, (int) categoryCounts.get("Environmental"));
        assertEquals(1, (int) categoryCounts.get("Infrastructure"));

        assertEquals(0, (int) nullCounts.get("Maintenance"));
        assertEquals(0, (int) nullCounts.get("Environmental"));
        assertEquals(0, (int) nullCounts.get("Infrastructure"));
    }

    @Test
    public void testGetLocationCounts() {
        List<Report> reports = createTestReports();
        List<Report> nullCase = new ArrayList<>();
        Map<String, Integer> locationCounts = reportAnalyzer.getLocationCounts(reports);
        Map<String, Integer> nullCounts = reportAnalyzer.getLocationCounts(nullCase);

        assertEquals(3, (int) locationCounts.get("Location1"));
        assertEquals(3, (int) locationCounts.get("Location2"));
        assertEquals(0, nullCounts.size());
    }

    @Test
    public void testEmptyReports() {
        List<Report> emptyReports = new ArrayList<>();
        Map<String, Integer> priorityCounts = reportAnalyzer.getPriorityCounts(emptyReports);
        Map<String, Integer> categoryCounts = reportAnalyzer.getCategoryCounts(emptyReports);
        Map<String, Integer> locationCounts = reportAnalyzer.getLocationCounts(emptyReports);

        assertEquals(0, priorityCounts.get("LOW").intValue());
        assertEquals(0, priorityCounts.get("MIDDLE").intValue());
        assertEquals(0, priorityCounts.get("HIGH").intValue());

        for (Category category : Category.values()) {
            assertEquals(0, categoryCounts.get(category.toString()).intValue());
        }

        assertEquals(0, locationCounts.size());
    }

    @Test
    public void testReportsWithNullValues() {
        // Create a list of reports, some with null fields
        List<Report> reports = new ArrayList<>();
        reports.add(new Report(1, "Report 1", null, Priority.LOW, new User("aaa"), null, LocalDateTime.now(), 5));
        reports.add(new Report(2, "Report 2", "Location2", null, new User("bbb"), Category.Maintenance, LocalDateTime.now(), 3));

        // Test priority counts
        Map<String, Integer> priorityCounts = reportAnalyzer.getPriorityCounts(reports);
        assertEquals(1, (int) priorityCounts.get("LOW"));
        assertEquals(0, (int) priorityCounts.get("MIDDLE"));
        assertEquals(0, (int) priorityCounts.get("HIGH"));

        // Test category counts (the null category should be ignored)
        Map<String, Integer> categoryCounts = reportAnalyzer.getCategoryCounts(reports);
        assertEquals(1, (int) categoryCounts.get("Maintenance"));
        assertEquals(0, categoryCounts.getOrDefault("Environmental", 0).intValue());
        assertEquals(0, categoryCounts.getOrDefault("Infrastructure", 0).intValue());

        // Test location counts (null locations should be ignored)
        Map<String, Integer> locationCounts = reportAnalyzer.getLocationCounts(reports);
        assertEquals(1, (int) locationCounts.get("Location2"));
        assertEquals(0, locationCounts.getOrDefault(null, 0).intValue());
    }

}

package com.example.prototype;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Mock DataHolder and AVLTree for the purpose of testing
class MockDataHolder {
    public static AVLTree avlTree;

    public static List<Report> getMockReports() {
        List<Report> mockReports = new ArrayList<>();
        // Create some mock reports with different priorities, categories, and locations
        mockReports.add(new Report(1, "Report 1", "Location1", Priority.LOW, new User("aaa"), Category.Maintenance, LocalDateTime.now(), 5));
        mockReports.add(new Report(2, "Report 2", "Location2", Priority.MIDDLE, new User("aaa"), Category.Maintenance, LocalDateTime.now(), 3));
        mockReports.add(new Report(3, "Report 3", "Location1", Priority.HIGH, new User("bbb"), Category.Environmental, LocalDateTime.now(), 10));
        mockReports.add(new Report(4, "Report 4", "Location2", Priority.HIGH, new User("bbb"), Category.Environmental, LocalDateTime.now(), 7));
        mockReports.add(new Report(5, "Report 5", "Location2", Priority.HIGH, new User("ccc"), Category.Infrastructure, LocalDateTime.now(), 12));
        mockReports.add(new Report(6, "Report 6", "Location1", Priority.LOW, new User("ccc"), Category.Maintenance, LocalDateTime.now(), 1));
        return mockReports;
    }
}

// Mock AVLTree class for the purpose of testing
class MockAVLTree {
    private List<Report> reports;

    public MockAVLTree(List<Report> reports) {
        this.reports = reports;
    }

    public List<Report> fromSmallToLarge() {
        // Simply return the reports as they are (or sorted if necessary)
        return reports;
    }
}

public class ChartActivityTest {

    private ChartActivity chartActivity;

    @Before
    public void setUp() {
        chartActivity = new ChartActivity();
        // Set up the mock AVL tree with mock reports
        List<Report> mockReports = MockDataHolder.getMockReports();
        DataHolder.avlTree = new AVLTree<Report>(mockReports); // Properly set the mock AVL tree in the DataHolder
    }

    @Test
    public void testGetPriorityCounts() {
        // Call the method
        Map<String, Integer> priorityCounts = chartActivity.getPriorityCounts();

        // Expected results
        Map<String, Integer> expectedCounts = new HashMap<>();
        expectedCounts.put("LOW", 2);     // 2 LOW priority reports
        expectedCounts.put("MIDDLE", 1);  // 1 MIDDLE priority report
        expectedCounts.put("HIGH", 3);    // 3 HIGH priority reports

        assertEquals(expectedCounts, priorityCounts);
    }

    @Test
    public void testGetCategoryCounts() {
        // Call the method
        Map<String, Integer> categoryCounts = chartActivity.getCategoryCounts();

        // Expected results
        Map<String, Integer> expectedCounts = new HashMap<>();
        expectedCounts.put("Maintenance", 3); // 3 reports in CategoryA
        expectedCounts.put("Environmental", 2); // 2 reports in CategoryB
        expectedCounts.put("Infrastructure", 1); // 1 report in CategoryC

        assertEquals(expectedCounts, categoryCounts);
    }

    @Test
    public void testGetLocationCounts() {
        // Call the method
        Map<String, Integer> locationCounts = chartActivity.getLocationCounts();

        // Expected results
        Map<String, Integer> expectedCounts = new HashMap<>();
        expectedCounts.put("Location1", 2); // 2 reports from Location1
        expectedCounts.put("Location2", 3); // 3 reports from Location2

        assertEquals(expectedCounts, locationCounts);
    }
}

package com.example.prototype;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import com.example.prototype.entity.Report;
import com.example.prototype.entity.User;
import com.example.prototype.entity.Category;
import com.example.prototype.entity.Priority;

import com.example.prototype.sort.ReportSorter;
import com.example.prototype.sort.NewestFirst;
import com.example.prototype.sort.OldestFirst;
import com.example.prototype.sort.PriorityHighFirst;
import com.example.prototype.sort.PriorityLowFirst;
import com.example.prototype.sort.LikesHighFirst;
import com.example.prototype.sort.ReportSorterFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Test for sort and filter function
 * @author Harry Xia u7556816
 */
public class ReportSortTest {

    private List<Report> reportList;

    @Before
    public void setUp() {
        // Initialize sample data
        reportList = new ArrayList<>();

        User user1 = new User("Alice");
        User user2 = new User("Bob");

        Report report1 = new Report(1, "Broken streetlight on Main St", "Main St", Priority.HIGH, user1, Category.Infrastructure, LocalDateTime.now().minusDays(1), 10);
        Report report2 = new Report(2, "Graffiti on wall", "2nd Ave", Priority.LOW, user2, Category.Public, LocalDateTime.now().minusDays(2), 5);
        Report report3 = new Report(3, "Pothole needs fixing", "Main St", Priority.MIDDLE, user1, Category.Maintenance, LocalDateTime.now().minusHours(5), 8);
        Report report4 = new Report(4, "Water leakage", "3rd St", Priority.HIGH, user2, Category.Utilities, LocalDateTime.now().minusDays(3), 2);
        Report report5 = new Report(5, "Park needs cleaning", "Central Park", Priority.LOW, user1, Category.Environmental, LocalDateTime.now().minusHours(2), 15);
        Report report6 = new Report(6, "Safety concern on bridge", "River Bridge", Priority.MIDDLE, user2, Category.Safety, LocalDateTime.now().minusHours(1), 7);
        Report report7 = new Report(7, "Community event announcement", "Community Hall", Priority.LOW, user1, Category.Community, LocalDateTime.now().minusHours(4), 3);

        reportList.add(report1);
        reportList.add(report2);
        reportList.add(report3);
        reportList.add(report4);
        reportList.add(report5);
        reportList.add(report6);
        reportList.add(report7);
    }

    @Test
    public void testSortNewestFirst() {
        ReportSorter sorter = new NewestFirst();
        List<Report> sortedReports = sorter.sort(new ArrayList<>(reportList));

        // The report with the most recent LocalDateTime should be first (report6)
        assertEquals(6, sortedReports.get(0).getReportId());
    }

    @Test
    public void testSortOldestFirst() {
        ReportSorter sorter = new OldestFirst();
        List<Report> sortedReports = sorter.sort(new ArrayList<>(reportList));

        // The report with the earliest LocalDateTime should be first (report4)
        assertEquals(4, sortedReports.get(0).getReportId());
    }

    @Test
    public void testSortPriorityHighFirst() {
        ReportSorter sorter = new PriorityHighFirst();
        List<Report> sortedReports = sorter.sort(new ArrayList<>(reportList));

        // Reports with HIGH priority first
        assertEquals(Priority.HIGH, sortedReports.get(0).getPriority());
        assertEquals(Priority.HIGH, sortedReports.get(1).getPriority());
    }

    @Test
    public void testSortPriorityLowFirst() {
        ReportSorter sorter = new PriorityLowFirst();
        List<Report> sortedReports = sorter.sort(new ArrayList<>(reportList));

        // Reports with LOW priority first
        assertEquals(Priority.LOW, sortedReports.get(0).getPriority());
        assertEquals(Priority.LOW, sortedReports.get(1).getPriority());
    }

    @Test
    public void testSortLikesHighFirst() {
        ReportSorter sorter = new LikesHighFirst();
        List<Report> sortedReports = sorter.sort(new ArrayList<>(reportList));

        // Reports with highest likes first (report5)
        assertEquals(5, sortedReports.get(0).getReportId());
        assertEquals(15, sortedReports.get(0).getLikes());
    }

    @Test
    public void testReportSorterFactory() {
        ReportSorter sorter = ReportSorterFactory.createSorter(1); // position 1 corresponds to NewestFirst
        assertTrue(sorter instanceof NewestFirst);

        sorter = ReportSorterFactory.createSorter(2); // position 2 corresponds to OldestFirst
        assertTrue(sorter instanceof OldestFirst);

        sorter = ReportSorterFactory.createSorter(3); // position 3 corresponds to PriorityHighFirst
        assertTrue(sorter instanceof PriorityHighFirst);

        sorter = ReportSorterFactory.createSorter(4); // position 4 corresponds to PriorityLowFirst
        assertTrue(sorter instanceof PriorityLowFirst);

        sorter = ReportSorterFactory.createSorter(5); // position 5 corresponds to LikesHighFirst
        assertTrue(sorter instanceof LikesHighFirst);

        sorter = ReportSorterFactory.createSorter(0); // position 0 corresponds to default (no sort)
        assertNotNull(sorter);
    }

    // Edge case: Empty list
    @Test
    public void testSortEmptyList() {
        List<Report> emptyList = new ArrayList<>();
        ReportSorter sorter = new NewestFirst();
        List<Report> sortedReports = sorter.sort(emptyList);

        assertEquals(0, sortedReports.size());
    }

    // Edge case: Single item list
    @Test
    public void testSortSingleItemList() {
        List<Report> singleItemList = new ArrayList<>();
        singleItemList.add(reportList.get(0)); // Add first report

        ReportSorter sorter = new OldestFirst();
        List<Report> sortedReports = sorter.sort(singleItemList);

        assertEquals(1, sortedReports.size());
        assertEquals(reportList.get(0).getReportId(), sortedReports.get(0).getReportId());
    }

    // Edge case: Items with equal values in sort field (e.g., same priority)
    @Test
    public void testSortWithEqualValues() {
        // Add another report with HIGH priority
        Report report8 = new Report(8, "Another high priority issue", "5th Ave", Priority.HIGH, new User("Charlie"), Category.Safety, LocalDateTime.now().minusHours(6), 4);
        reportList.add(report8);

        ReportSorter sorter = new PriorityHighFirst();
        List<Report> sortedReports = sorter.sort(new ArrayList<>(reportList));

        // The first three reports should have HIGH priority
        assertEquals(Priority.HIGH, sortedReports.get(0).getPriority());
        assertEquals(Priority.HIGH, sortedReports.get(1).getPriority());
        assertEquals(Priority.HIGH, sortedReports.get(2).getPriority());

        // Verify that report IDs of high priority reports are included
        List<Integer> highPriorityIds = List.of(sortedReports.get(0).getReportId(), sortedReports.get(1).getReportId(), sortedReports.get(2).getReportId());
        assertTrue(highPriorityIds.contains(1));
        assertTrue(highPriorityIds.contains(4));
        assertTrue(highPriorityIds.contains(8));
    }

    // Edge case: Sort after adding new report
    @Test
    public void testSortAfterAddingReport() {
        // Add a new report
        Report newReport = new Report(9, "Urgent issue", "Downtown", Priority.HIGH, new User("Dana"), Category.Safety, LocalDateTime.now(), 20);
        reportList.add(newReport);

        // Sort by likes
        ReportSorter sorter = new LikesHighFirst();
        List<Report> sortedReports = sorter.sort(new ArrayList<>(reportList));

        // The new report should be first
        assertEquals(9, sortedReports.get(0).getReportId());
        assertEquals(20, sortedReports.get(0).getLikes());
    }

    // Edge case: All items have the same value in sort field
    @Test
    public void testSortAllSameValues() {
        // Set all likes to 10
        for (Report report : reportList) {
            report.setLikes(10);
        }

        ReportSorter sorter = new LikesHighFirst();
        List<Report> sortedReports = sorter.sort(new ArrayList<>(reportList));

        // The order should remain unchanged
        assertEquals(reportList.size(), sortedReports.size());
        for (int i = 0; i < reportList.size(); i++) {
            assertEquals(reportList.get(i).getReportId(), sortedReports.get(i).getReportId());
        }
    }
}

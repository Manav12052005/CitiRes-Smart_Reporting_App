package com.example.prototype;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import com.example.prototype.entity.Report;
import com.example.prototype.entity.User;
import com.example.prototype.entity.Category;
import com.example.prototype.entity.Priority;

import com.example.prototype.search.Parser;
import com.example.prototype.search.Tokenizer;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReportSearchTest {

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
    public void testSearchByDescription() {
        String query = "description:pothole";
        List<String> tokens = Tokenizer.tokenize(query);
        List<Report> results = Parser.parseWithGrammar(tokens, reportList);

        assertEquals(1, results.size());
        assertEquals(3, results.get(0).getReportId());
    }

    @Test
    public void testSearchByLocation() {
        String query = "location:Main";
        List<String> tokens = Tokenizer.tokenize(query);
        List<Report> results = Parser.parseWithGrammar(tokens, reportList);

        assertEquals(2, results.size());
        // Should include reports with ID 1 and 3
        List<Integer> expectedIds = List.of(1, 3);
        List<Integer> resultIds = new ArrayList<>();
        for (Report r : results) {
            resultIds.add(r.getReportId());
        }
        assertTrue(resultIds.containsAll(expectedIds));
    }

    @Test
    public void testSearchByPriority() {
        String query = "priority:high";
        List<String> tokens = Tokenizer.tokenize(query);
        List<Report> results = Parser.parseWithGrammar(tokens, reportList);

        assertEquals(2, results.size());
        // Should include reports with ID 1 and 4
        List<Integer> expectedIds = List.of(1, 4);
        List<Integer> resultIds = new ArrayList<>();
        for (Report r : results) {
            resultIds.add(r.getReportId());
        }
        assertTrue(resultIds.containsAll(expectedIds));
    }

    @Test
    public void testSearchByCategory() {
        String query = "category:infrastructure";
        List<String> tokens = Tokenizer.tokenize(query);
        List<Report> results = Parser.parseWithGrammar(tokens, reportList);

        assertEquals(1, results.size());
        assertEquals(1, results.get(0).getReportId());
    }

    @Test
    public void testSearchByUser() {
        String query = "user:alice";
        List<String> tokens = Tokenizer.tokenize(query);
        List<Report> results = Parser.parseWithGrammar(tokens, reportList);

        assertEquals(4, results.size());
        // Reports with user Alice are IDs 1, 3, 5, and 7
        List<Integer> expectedIds = List.of(1, 3, 5, 7);
        List<Integer> resultIds = new ArrayList<>();
        for (Report r : results) {
            resultIds.add(r.getReportId());
        }
        assertTrue(resultIds.containsAll(expectedIds));
    }

    @Test
    public void testSearchByLikes() {
        String query = "likes:5";
        List<String> tokens = Tokenizer.tokenize(query);
        List<Report> results = Parser.parseWithGrammar(tokens, reportList);

        assertEquals(1, results.size());
        assertEquals(2, results.get(0).getReportId());
    }

    @Test
    public void testSearchGeneralToken() {
        String query = "Main";
        List<String> tokens = Tokenizer.tokenize(query);
        List<Report> results = Parser.parseWithGrammar(tokens, reportList);

        assertEquals(2, results.size());
        // Should include reports with ID 1 and 3
        List<Integer> expectedIds = List.of(1, 3);
        List<Integer> resultIds = new ArrayList<>();
        for (Report r : results) {
            resultIds.add(r.getReportId());
        }
        assertTrue(resultIds.containsAll(expectedIds));
    }

    @Test
    public void testSearchMultipleTokens() {
        String query = "priority:low alice";
        List<String> tokens = Tokenizer.tokenize(query);
        List<Report> results = Parser.parseWithGrammar(tokens, reportList);

        assertEquals(2, results.size());
        // Should include reports with ID 5 and 7
        List<Integer> expectedIds = List.of(5, 7);
        List<Integer> resultIds = new ArrayList<>();
        for (Report r : results) {
            resultIds.add(r.getReportId());
        }
        assertTrue(resultIds.containsAll(expectedIds));
    }

    // Edge case: Empty query
    @Test
    public void testEmptyQuery() {
        String query = "";
        List<String> tokens = Tokenizer.tokenize(query);
        List<Report> results = Parser.parseWithGrammar(tokens, reportList);

        // Should return all reports
        assertEquals(reportList.size(), results.size());
    }

    // Edge case: Invalid key
    @Test
    public void testInvalidKey() {
        String query = "invalidkey:value";
        List<String> tokens = Tokenizer.tokenize(query);
        List<Report> results = Parser.parseWithGrammar(tokens, reportList);

        // Should return all reports (since invalid key is ignored)
        assertEquals(reportList.size(), results.size());
    }

    // Edge case: No results
    @Test
    public void testNoResults() {
        String query = "description:nonexistent";
        List<String> tokens = Tokenizer.tokenize(query);
        List<Report> results = Parser.parseWithGrammar(tokens, reportList);

        assertEquals(0, results.size());
    }

    // Edge case: Special characters
    @Test
    public void testSpecialCharacters() {
        String query = "description:wall!";
        List<String> tokens = Tokenizer.tokenize(query);
        List<Report> results = Parser.parseWithGrammar(tokens, reportList);

        // Should handle special characters and find report 2
        assertEquals(1, results.size());
        assertEquals(2, results.get(0).getReportId());
    }

    // Edge case: Mixed case sensitivity
    @Test
    public void testCaseInsensitivity() {
        String query = "Location:main st";
        List<String> tokens = Tokenizer.tokenize(query);
        List<Report> results = Parser.parseWithGrammar(tokens, reportList);

        // Should be case-insensitive and find reports 1 and 3
        assertEquals(2, results.size());
        List<Integer> expectedIds = List.of(1, 3);
        List<Integer> resultIds = new ArrayList<>();
        for (Report r : results) {
            resultIds.add(r.getReportId());
        }
        assertTrue(resultIds.containsAll(expectedIds));
    }

    // Edge case: Invalid likes value
    @Test
    public void testInvalidLikesValue() {
        String query = "likes:abc";
        List<String> tokens = Tokenizer.tokenize(query);
        List<Report> results = Parser.parseWithGrammar(tokens, reportList);

        // Should ignore invalid likes value and return all reports
        assertEquals(reportList.size(), results.size());
    }

    // Edge case: Multiple key-value pairs
    @Test
    public void testMultipleKeyValuePairs() {
        String query = "priority:low category:environmental";
        List<String> tokens = Tokenizer.tokenize(query);
        List<Report> results = Parser.parseWithGrammar(tokens, reportList);

        // Should return report with ID 5
        assertEquals(1, results.size());
        assertEquals(5, results.get(0).getReportId());
    }

    // Edge case: Partial matches
    @Test
    public void testPartialMatches() {
        String query = "description:graff";
        List<String> tokens = Tokenizer.tokenize(query);
        List<Report> results = Parser.parseWithGrammar(tokens, reportList);

        // Should find report with ID 2
        assertEquals(1, results.size());
        assertEquals(2, results.get(0).getReportId());
    }
}

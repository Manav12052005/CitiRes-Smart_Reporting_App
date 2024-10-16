package com.example.prototype;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import com.example.prototype.data.AVLTree;
import com.example.prototype.entity.Category;
import com.example.prototype.entity.Priority;
import com.example.prototype.entity.Report;
import com.example.prototype.entity.User;
import com.example.prototype.report.ReportCounter;
import com.example.prototype.util.TimeUtil;

import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Integration test on AVLTree's behaviours on Reports
 * @author Yuan Shi u7787385
 */
public class ReportStorageTest {

    AVLTree<Report> reportsTree = new AVLTree<>();

    @Test
    public void integratedTest() {
        LocalDateTime dateTime = LocalDateTime.now();
        assertTrue(reportsTree.isEmpty());
        Report report1 = new Report(1, "hello", "home", Priority.HIGH, new User("John"), Category.Community, dateTime, 100);
        reportsTree.put(1, report1);
        assertEquals(1, reportsTree.size());
        assertEquals(report1, reportsTree.get(1));
        reportsTree.put(1, new Report(1, "hi", "home", Priority.HIGH, new User("John"), Category.Community, dateTime, 100));
        assertEquals("hi", reportsTree.get(1).getDescription());
        reportsTree.put(1, report1);

        Report report2 = new Report(2, "hello2", "home", Priority.HIGH, new User("John"), Category.Community, dateTime, 100);
        reportsTree.put(2, report2);
        assertEquals(2, reportsTree.size());
        Report report3 = new Report(100, "hello100", "home", Priority.HIGH, new User("John"), Category.Community, dateTime, 1);
        reportsTree.put(100, report3);

        assertEquals(List.of(report1, report2, report3), reportsTree.fromSmallToLarge());
        assertEquals(List.of(report3, report2, report1), reportsTree.fromLargeToSmall());
        reportsTree.remove(1);
        assertNull(reportsTree.get(1));
        assertThrows(NoSuchElementException.class, () -> reportsTree.remove(1));
        reportsTree.remove(2);
        reportsTree.remove(100);
        assertNull(reportsTree.get(2));
        assertTrue(reportsTree.isEmpty());
        for (int i = 0; i < 100; i++) {
            int var = i;
            assertThrows(NoSuchElementException.class, () -> reportsTree.remove(var));
        }
    }

    @Test
    public void testReportCounter() {
        assertEquals(2501, ReportCounter.getReportId());
        for (int i = 0; i < 1000; i++) {
            assertEquals(2501 + i, ReportCounter.getReportId());
            ReportCounter.inc();
        }
    }
}

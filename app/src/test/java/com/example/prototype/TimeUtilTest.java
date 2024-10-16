package com.example.prototype;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import com.example.prototype.util.TimeUtil;

import org.junit.Test;

import java.time.LocalDateTime;

/**
 * Test on helper methods on TimeUtil
 */
public class TimeUtilTest {

    @Test
    public void testIsToday() {
        LocalDateTime now = LocalDateTime.now();
        assertTrue(TimeUtil.isToday(now));
        now = now.minusDays(1);
        assertFalse(TimeUtil.isToday(now));
    }
}

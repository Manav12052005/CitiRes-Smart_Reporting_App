package com.example.prototype;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import com.example.prototype.entity.Category;
import com.example.prototype.entity.Priority;
import com.example.prototype.entity.Report;
import com.example.prototype.entity.User;
import com.example.prototype.util.JsonDeserialiser;

import com.google.gson.JsonParser;

import org.junit.Test;

import java.time.LocalDateTime;

public class JsonDeserializerTest {
    String userJsonStrCorr = "{\"reportId\": 1, \"description\": \"Broken\", \"location\": \"Lane\",\"priority\": \"LOW\", \"category\": \"Infrastructure\",\"user\": \"Frank Brown\",\"localDateTime\": \"2023-12-09T04:32:04\",\"likes\": 420}";

    String userJsonStrWrong = "{\"reportId\": 1, \"description\": \"Broken\", \"location\": \"Lane\", \"category\": \"Infrastructure\",\"user\": \"Frank Brown\",\"localDateTime\": \"2023-12-09T04:32:04\",\"likes\": 420}";


    @Test
    public void testDeserializeWithCorrectJsonString() {
        JsonDeserialiser deserialiser = new JsonDeserialiser();
        Report report = deserialiser.deserialize(JsonParser.parseString(userJsonStrCorr), Report.class, null);
        assertEquals(1, report.getReportId());
        assertEquals("Broken", report.getDescription());
        assertEquals(Category.Infrastructure, report.getCategory());
        assertEquals("Broken", report.getDescription());
        assertEquals(Priority.LOW, report.getPriority());
        assertEquals(new User("Frank Brown"), report.getUser());
        assertEquals(LocalDateTime.of(2023, 12, 9, 4, 32, 4), report.getLocalDateTime());
        assertEquals(420, report.getLikes());
    }

    @Test
    public void testDeserializeWithWrongJsonString() {
        JsonDeserialiser deserialiser = new JsonDeserialiser();
        assertThrows(NullPointerException.class, () -> {
            deserialiser.deserialize(JsonParser.parseString(userJsonStrWrong), Report.class, null);
        });
    }
}

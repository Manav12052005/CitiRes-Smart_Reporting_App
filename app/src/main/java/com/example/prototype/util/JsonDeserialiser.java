package com.example.prototype.util;

import com.example.prototype.entity.Category;
import com.example.prototype.entity.Priority;
import com.example.prototype.entity.Report;
import com.example.prototype.entity.User;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.time.LocalDateTime;

/**
 * A class for building customized json-deserializer
 * @author Yuan Shi u7787385
 * Since it is not directly taught in the course, I reference but not copy the following external resource:
 * https://medium.com/@alexandre.therrien3/personalized-serializer-and-deserializer-using-java-gson-library-c079de3974d4
 */
public class JsonDeserialiser implements JsonDeserializer<Report> {
    @Override
    public Report deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        Report report = new Report();
        report.setReportId(jsonObject.get("reportId").getAsInt());
        report.setDescription(jsonObject.get("description").getAsString());
        report.setLocation(jsonObject.get("location").getAsString());
        report.setPriority(Priority.valueOf(jsonObject.get("priority").getAsString()));
        report.setCategory(Category.valueOf(jsonObject.get("category").getAsString()));
        report.setUser(new User(jsonObject.get("user").getAsString()));
        String dtStr = jsonObject.get("localDateTime").getAsString();
        report.setLocalDateTime(LocalDateTime.parse(dtStr));
        report.setLikes(jsonObject.get("likes").getAsInt());
        return report;
    }
}

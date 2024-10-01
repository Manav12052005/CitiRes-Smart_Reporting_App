package com.example.prototype;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.time.LocalDateTime;

public class ReportAdapterJson implements JsonDeserializer<Report> {
    @Override
    public Report deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

        JsonObject jsonObject = jsonElement.getAsJsonObject();
        Report report = new Report();
        report.setReportId(jsonObject.get("reportId").getAsInt());
        report.setDescription(jsonObject.get("description").getAsString());
        report.setLocation(jsonObject.get("location").getAsString());
        report.setPriority(Priority.valueOf(jsonObject.get("priority").getAsString()));
        report.setCategory(jsonObject.get("category").getAsString());
        report.setUser(new User(jsonObject.get("user").getAsString()));
        String dtStr = jsonObject.get("localDateTime").getAsString();
        report.setLocalDateTime(LocalDateTime.parse(dtStr));
        report.setLikes(jsonObject.get("likes").getAsInt());
        return report;
    }
}

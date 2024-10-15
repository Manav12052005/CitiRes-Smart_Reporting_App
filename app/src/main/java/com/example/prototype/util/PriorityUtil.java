package com.example.prototype.util;

import com.example.prototype.entity.Priority;

public class PriorityUtil {

    // Custom priority comparator function
    public static int comparePriority(Priority p1, Priority p2) {
        // Define the custom order: LOW < MIDDLE < HIGH
        int priorityValue1 = getPriorityValue(p1);
        int priorityValue2 = getPriorityValue(p2);
        return Integer.compare(priorityValue1, priorityValue2);
    }

    // Helper function to assign custom values to priorities
    public static int getPriorityValue(Priority priority) {
        switch (priority) {
            case LOW:
                return 1;
            case MIDDLE:
                return 2;
            case HIGH:
                return 3;
            default:
                return Integer.MAX_VALUE; // Default case for safety
        }
    }
}

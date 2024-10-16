package com.example.prototype.sort;

/**
 * Factory method for generating sorters based on position.
 * @author Harry Xia u7556816 original author for sorting feature
 * @author Yuan Shi u7787385 Refactored the class using Factory Design Pattern.
 */
public class ReportSorterFactory {
    public static ReportSorter createSorter(int position) {
        switch (position) {
            case 1:
                return new NewestFirst();
            case 2:
                return new OldestFirst();
            case 3:
                return new PriorityHighFirst();
            case 4:
                return new PriorityLowFirst();
            case 5:
                return new LikesHighFirst();
            default:
                return reports -> reports;
        }
    }

};

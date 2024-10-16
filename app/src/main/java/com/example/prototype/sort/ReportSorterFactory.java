package com.example.prototype.sort;

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

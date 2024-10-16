package com.example.prototype.report;

public interface Observer {
    /**
     * once the button is clicked, notify and pass the data to the observer.
     * @param data the data to pass between activities
     * @author Yuan Shi u7787385
     */
    void onClickPassData(int data);
}

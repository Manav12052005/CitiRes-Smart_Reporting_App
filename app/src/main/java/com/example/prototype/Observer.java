package com.example.prototype;

public interface Observer {
    /**
     * once the button is clicked, notify and pass the data to the observer.
     * @param data
     */
    void onClickPassData(int data);
}

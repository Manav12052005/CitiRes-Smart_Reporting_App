package com.example.prototype.data;

import java.util.List;

/**
 * Interface for Tree
 *
 * @param <R>
 * @author Yuan Shi u7787385
 */
public interface Tree<R> {
    void put(int key, R value);
    R get(int key);
    void remove(int key);
    List<R> fromLargeToSmall();
    List<R> fromSmallToLarge();
}

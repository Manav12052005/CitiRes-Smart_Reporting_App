package com.example.prototype;

import static org.junit.Assert.*;

import com.example.prototype.data.DataHolder;
import com.example.prototype.entity.Category;
import com.example.prototype.entity.Priority;
import com.example.prototype.entity.Report;
import com.example.prototype.entity.User;
import com.example.prototype.util.TimeUtil;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

/**
 * Unit testing for DataHolder's singleton behaviour.
 * @author Yuan Shi u7787385
 */
public class DataHolderTest {

    @Before
    public void setup() {
        DataHolder.getInstance().avlTree.empty();
    }

    @Test
    public void testSingleton() {
        DataHolder instance1 = DataHolder.getInstance();
        DataHolder instance2 = DataHolder.getInstance();

        assertSame("Instances are not the same, singleton failed.", instance2, instance1);
    }

    @Test
    public void testAVLTreeInitialization() {
        assertNotNull("AVLTree is not initialized.", DataHolder.getInstance().avlTree);
    }

    @Test
    public void testSingletonAfterDataChanged() {

        LocalDateTime dateTime = LocalDateTime.now();

        Report report = new Report(1, "hello", "home", Priority.HIGH, new User("John"), Category.Community, dateTime, 100);
        DataHolder.getInstance().avlTree.put(1, report);
        DataHolder instance1 = DataHolder.getInstance();
        DataHolder instance2 = DataHolder.getInstance();
        assertEquals(1, TimeUtil.getPostsToday());

        assertSame("Instances are not the same, singleton failed.", instance2, instance1);
        DataHolder.getInstance().avlTree.remove(1);
        DataHolder instance3 = DataHolder.getInstance();
        DataHolder instance4 = DataHolder.getInstance();
        assertSame("Instances are not the same, singleton failed.", instance2, instance1);
        assertEquals(0, TimeUtil.getPostsToday());
    }

}

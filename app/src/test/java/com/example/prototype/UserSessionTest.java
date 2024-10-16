package com.example.prototype;

import org.junit.Test;
import static org.junit.Assert.*;

import com.example.prototype.login.UserSession;

public class UserSessionTest {

    @Test
    public void testSetAndGetCurrentUser() {
        // Set the current user
        UserSession.setCurrentUser("testUser");

        // Verify that the current user is correctly stored and retrieved
        String currentUser = UserSession.getCurrentUser();
        assertEquals("The current user should be 'testUser'", "testUser", currentUser);
    }
}

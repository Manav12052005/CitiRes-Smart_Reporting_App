package com.example.prototype;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import com.example.prototype.login.Authenticator;
import com.example.prototype.login.UserSession;

import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class LoginTest {

    private Authenticator authenticator;

    @Before
    public void setUp() {
        // Inject test credentials to avoid Android-specific dependencies (e.g., Context)
        List<Authenticator.Credential> testCredentials = new ArrayList<>();
        testCredentials.add(new Authenticator.Credential("comp2100@anu.edu.au", "comp2100"));
        testCredentials.add(new Authenticator.Credential("comp6442@anu.edu.au", "comp6442"));
        testCredentials.add(new Authenticator.Credential("1", "1"));
        authenticator = Authenticator.getInstance(testCredentials); // Use test credentials

        // Reset UserSession before each test to ensure clean state
        UserSession.setCurrentUser(null);
    }

    @Test
    public void testValidLoginComp2100() {
        String validUsername = "comp2100@anu.edu.au";
        String validPassword = "comp2100";

        boolean loginSuccess = authenticator.login(validUsername, validPassword);

        assertTrue(loginSuccess);
        UserSession.setCurrentUser(validUsername);
        assertEquals(validUsername, UserSession.getCurrentUser());
    }

    @Test
    public void testValidLoginComp6442() {
        String validUsername = "comp6442@anu.edu.au";
        String validPassword = "comp6442";

        boolean loginSuccess = authenticator.login(validUsername, validPassword);

        assertTrue(loginSuccess);
        UserSession.setCurrentUser(validUsername);
        assertEquals(validUsername, UserSession.getCurrentUser());
    }

    @Test
    public void testValidLoginShortCredentials() {
        String validUsername = "1";
        String validPassword = "1";

        boolean loginSuccess = authenticator.login(validUsername, validPassword);

        assertTrue(loginSuccess);
        UserSession.setCurrentUser(validUsername);
        assertEquals(validUsername, UserSession.getCurrentUser());
    }

    @Test
    public void testInvalidLogin() {
        String invalidUsername = "invalidUser";
        String invalidPassword = "invalidPass";

        boolean loginSuccess = authenticator.login(invalidUsername, invalidPassword);

        assertFalse(loginSuccess);
        assertNull(UserSession.getCurrentUser()); // Ensure no user is set after failed login
    }

    @Test
    public void testEmptyCredentials() {
        String emptyUsername = "";
        String emptyPassword = "";

        boolean loginSuccess = authenticator.login(emptyUsername, emptyPassword);

        assertFalse(loginSuccess);
        assertNull(UserSession.getCurrentUser()); // Ensure no user is set after failed login
    }
}

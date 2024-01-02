package ManagersTests;

import Managers.CreateLoginForm;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestIsValidLogIn {


    @Test
    public void testIsValidUsername() {
        CreateLoginForm form = new CreateLoginForm();
        assertTrue(form.isValidUsername("ValidUsername123"));
        assertFalse(form.isValidUsername("Invalid@Username"));
    }
    @Test
    public void testIsValidPassword() {
        CreateLoginForm form = new CreateLoginForm();
        assertTrue(form.isValidPassword("ValidPass!23"));
        assertFalse(form.isValidPassword("WeakPass"));
    }
}


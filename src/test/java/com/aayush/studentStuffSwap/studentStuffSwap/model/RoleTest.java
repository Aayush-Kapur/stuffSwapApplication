package com.aayush.studentStuffSwap.studentStuffSwap.model;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RoleTest {

    @Test
    public void testNoArgsConstructor() {
        Role role = new Role();

        assertNotNull(role);
    }

    @Test
    public void testAllArgsConstructor() {
        Long roleId = 1L;
        String roleName = "ROLE_USER";
        Set<User> users = new HashSet<>();

        Role role = new Role(roleId, roleName, users);

        assertEquals(roleId, role.getId());
        assertEquals(roleName, role.getName());
        assertEquals(users, role.getUsers());
    }

    @Test
    public void testDataAnnotation() {
        Role role = new Role();

        assertNotNull(role);
        assertTrue(role.toString().contains("Role("));
        assertEquals(role, role);
        assertTrue(role.hashCode() != 0);
    }

    @Test
    public void testGetAuthority() {
        Role role = new Role("ROLE_ADMIN");

        assertEquals("ROLE_ADMIN", role.getAuthority());
    }
}

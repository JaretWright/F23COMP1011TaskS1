package com.example.f23comp1011tasks1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    private User newUser;
    @BeforeEach
    void setUp() {
         newUser = new User("fred@rocks.com","Freddie");
    }

    @Test
    void setEmail() {
        User newUser = new User("fred@rocks.com","Freddie");
        assertEquals("fred@rocks.com", newUser.getEmail());
    }

    @Test
    void setEmailInvalid() {
        Assertions.assertThrows(IllegalArgumentException.class, ()->{
            newUser.setEmail("notMuch");});
        Assertions.assertThrows(IllegalArgumentException.class, ()->{
            newUser.setEmail("notMuch.@hmm");});
        Assertions.assertThrows(IllegalArgumentException.class, ()->{
            newUser.setEmail("notMuch.@hmm");});
        Assertions.assertThrows(IllegalArgumentException.class, ()->{
            newUser.setEmail("@hmm.com");});
    }
}
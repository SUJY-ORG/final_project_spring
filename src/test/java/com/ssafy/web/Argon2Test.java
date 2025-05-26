package com.ssafy.web;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import com.ssafy.web.util.Argon2Hash;


public class Argon2Test {

    @Test
    @RepeatedTest(10)
    @DisplayName("Argon2 해시 테스트")
    public void test1() {
        byte[] salt = Argon2Hash.createSalt();
        String hashValue = Argon2Hash.createHash(salt, "password");
    }
}

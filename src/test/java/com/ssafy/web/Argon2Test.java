package com.ssafy.web;

import org.bouncycastle.crypto.generators.Argon2BytesGenerator;
import org.bouncycastle.crypto.params.Argon2Parameters;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
public class Argon2Test {


    @Test
    @DisplayName("Argon2 해시 테스트")
    public void test2() {
        // 100회 반복 190초
        // 1회 약 2초
        for (int i = 0; i < 100; i++) {
            SecureRandom secureRandom = new SecureRandom();
            byte[] salt = new byte[16];
            secureRandom.nextBytes(salt);

            String password = "Baeldung";

            int iterations = 20;
            int memLimit = 66536;
            int hashLength = 32;
            int parallelism = 1;

            Argon2Parameters.Builder builder = new Argon2Parameters.Builder(Argon2Parameters.ARGON2_id)
                    .withVersion(Argon2Parameters.ARGON2_VERSION_13)
                    .withIterations(iterations)
                    .withMemoryAsKB(memLimit)
                    .withParallelism(parallelism)
                    .withSalt(salt);

            Argon2BytesGenerator generate = new Argon2BytesGenerator();
            generate.init(builder.build());
            byte[] result = new byte[hashLength];
            generate.generateBytes(password.getBytes(StandardCharsets.UTF_8), result, 0, result.length);

            Argon2BytesGenerator verifier = new Argon2BytesGenerator();
            verifier.init(builder.build());
            byte[] testHash = new byte[hashLength];
            verifier.generateBytes(password.getBytes(StandardCharsets.UTF_8), testHash, 0, testHash.length);
        }

    }
}

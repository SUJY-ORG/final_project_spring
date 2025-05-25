package com.ssafy.web.util;

import org.springframework.stereotype.Component;

import java.util.concurrent.*;
import java.util.*;

@Component
public class EmailCodeManager {
    // 이메일(또는 사용자ID)와 인증코드, 만료시간을 저장
    private static class CodeInfo {
        String code;
        long expireAt; // 만료 타임스탬프(밀리초)
        CodeInfo(String code, long expireAt) {
            this.code = code;
            this.expireAt = expireAt;
        }
    }

    private final ConcurrentHashMap<String, CodeInfo> codeMap = new ConcurrentHashMap<>();

    // 코드 저장 (30분 후 만료)
    public void saveCode(String email, String code) {
        long expireAt = System.currentTimeMillis() + 30 * 60 * 1000; // 30분
        codeMap.put(email, new CodeInfo(code, expireAt));
    }

    // 코드 검증
    public boolean verifyCode(String email, String inputCode) {
        CodeInfo info = codeMap.get(email);
        if (info == null) return false;
        if (System.currentTimeMillis() > info.expireAt) {
            codeMap.remove(email); // 만료된 건 삭제
            return false;
        }
        return info.code.equals(inputCode);
    }

    // 주기적으로 만료 코드 삭제 (옵션)
    public void startCleanupTask() {
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(() -> {
            long now = System.currentTimeMillis();
            codeMap.entrySet().removeIf(entry -> entry.getValue().expireAt < now);
        }, 1, 1, TimeUnit.MINUTES);
    }
}

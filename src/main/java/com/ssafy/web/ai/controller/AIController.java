package com.ssafy.web.ai.controller;

import com.ssafy.web.ai.dto.JudgeRequestDto;
import com.ssafy.web.user.dto.UserDto;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;

import com.ssafy.web.ai.service.AIService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/ai")
@RequiredArgsConstructor
public class AIController {
	
	private final AIService aiService;
	
	@GetMapping("/judge")
	public String judge_get(JudgeRequestDto judgeRequestDto, HttpSession httpSession) {
		if (httpSession.getAttribute("user") != null) {
			return aiService.queryingWithLogin(judgeRequestDto, httpSession);
		} else {
			return aiService.querying(judgeRequestDto);
		}
	}
	
}

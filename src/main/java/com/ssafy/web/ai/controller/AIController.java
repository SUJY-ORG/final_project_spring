package com.ssafy.web.ai.controller;

import com.ssafy.web.ai.dto.JudgeRequestDto;
import org.springframework.web.bind.annotation.*;

import com.ssafy.web.ai.service.AIService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/ai")
@RequiredArgsConstructor
public class AIController {
	
	private final AIService aiService;
	
	@GetMapping("/judge")
	public String judge_get(JudgeRequestDto judgeRequestDto) {
		return aiService.querying(judgeRequestDto);
	}
	
}

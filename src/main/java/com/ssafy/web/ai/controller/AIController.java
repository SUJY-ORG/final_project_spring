package com.ssafy.web.ai.controller;

import org.springframework.web.bind.annotation.*;

import com.ssafy.web.ai.service.AIService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/ai")
@RequiredArgsConstructor
public class AIController {
	
	private final AIService aiService;
	
	@GetMapping
	public String test() {
		var res = aiService.querying("안녕");
		return res.toString();
	}
	
}

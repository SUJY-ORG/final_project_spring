package com.ssafy.web.ai.controller;

import org.springframework.web.bind.annotation.*;

import com.ssafy.web.ai.service.AIService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/ai")
@RequiredArgsConstructor
public class AIController {
	
	private final AIService aiService;
	
//	private final OpenAiChatModel openAiChatModel;
//	
//	@PostMapping("/query")
//    public String ai(AIDto dto, Model model) {
//        String prompt = "한국의 주거 공간으로써 어떤지 1~3문장으로 주관적	으로 평가해줘. 내가 살 곳 반경 5km 내의 시설이야 ";
//        prompt += "병원 " + dto.getHospital() + "개";
//        prompt += "교육시설 " + dto.getEducation() + "개";
//        prompt += "편의점 " + dto.getStore() + "개";
//        prompt += "음식점 " + dto.getRestaurant() + "개";
//        String openAiResponse = openAiChatModel.call(prompt);
//        model.addAttribute("result", openAiResponse);
//        return "ai";
//    }
	
	@GetMapping
	public String test() {
		var res = aiService.querying("안녕");
		return res.toString();
	}
	
}

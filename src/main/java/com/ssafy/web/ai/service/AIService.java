package com.ssafy.web.ai.service;

import com.ssafy.web.ai.dto.JudgeRequestDto;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class AIService {

	private final ChatClient chatClient;
	
	public String querying(JudgeRequestDto dto) {
		PromptTemplate template = new PromptTemplate("""
		주거 공간을 2~3문장으로 요약해서 주거 공간으로써 어떤지 평가해줘. 왠만하면 프롬프트에 있는 내용을 중복해서 넣지 말아줘.
		위치는 대한민국 {1}이고, 
		위치 기준 반경 250m 내에 병원 수는 {2}개, 
		교육 시설은 {3}개, 편의점은 {4}개, 음식점 수는 {5}개가 있어. 
		""");
		Prompt prompt = template.create(Map.of(
				"1", dto.getLocation(),
				"2", dto.getHospital(),
				"3", dto.getEducation(),
				"4", dto.getConvenience_store(),
				"5", dto.getRestaurant()
		));

		return chatClient.prompt(prompt)
				.call()
				.content();
	}
	
}

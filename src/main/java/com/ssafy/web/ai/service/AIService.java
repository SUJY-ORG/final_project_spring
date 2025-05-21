package com.ssafy.web.ai.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AIService {

	private final ChatClient chatClient;
	
	public Object querying(String input) {
		var responseSpec = chatClient.prompt()
				.system(spec -> spec.param("language", "korean").param("character", "chill"))
				.user(input)
				.call();
		return responseSpec.content();
	}
	
}

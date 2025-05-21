package com.ssafy.web.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;


@Configuration
public class AIConfig {

	@Value("${spring.ai.system-prompt}")
	String systemPrompt;
	
	@Bean
	ChatClient chatClient(ChatClient.Builder builder) {
		System.out.println(systemPrompt);
		return builder.defaultSystem(systemPrompt).build();
	}
	
}

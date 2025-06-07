package com.ssafy.web.user.controller;

import com.ssafy.web.user.dto.EmailVerificationDto;
import com.ssafy.web.user.dto.UserLoginRequestDto;
import com.ssafy.web.user.dto.UserSignupRequestDto;
import com.ssafy.web.user.dto.UserDto;
import com.ssafy.web.user.service.MailService;
import com.ssafy.web.user.service.UserService;
import com.ssafy.web.util.EmailCodeManager;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.Random;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
	
	private final UserService userService;
	private final MailService mailService;
	private final EmailCodeManager emailCodeManager;
	
	@GetMapping
	public ResponseEntity<Boolean> user_get(HttpSession httpSession) {
		if (httpSession.getAttribute("user") != null) {
			return new ResponseEntity<>(true, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(false, HttpStatus.OK);
		}
	}

	@PostMapping("/signup")
	public ResponseEntity<String> signup_post(@RequestBody UserSignupRequestDto dto) throws InterruptedException {
		UserDto signupUser = new UserDto(dto);
		boolean result = userService.signup(signupUser);
		if (result) {
			return new ResponseEntity<>("성공", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("실패", HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/login")
	public ResponseEntity<String> login_post(@RequestBody UserLoginRequestDto dto, HttpSession httpSession) throws InterruptedException {
		try {
			UserDto loginUser = userService.login(dto.getServiceId(), dto.getPassword());
			httpSession.setAttribute("user", loginUser);
			return new ResponseEntity<>("로그인 성공", HttpStatus.OK);
		} catch (Exception e) {
			log.debug(e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
		}
	}

	@PostMapping("/logout")
	public ResponseEntity<String> logout(HttpSession httpSession) {
		httpSession.invalidate();
		return new ResponseEntity<>("성공", HttpStatus.OK);
	}

	// TODO: 개선 필요
	@GetMapping("/signup/email-verification-request")
	public ResponseEntity<String> sendMessage(@RequestParam String email) {
		Random random = new Random();
		String authCode = (100000 + random.nextInt(900000)) + "";
		String title = "SSAFY 관통 프로젝트 이메일 인증";
		mailService.sendEmail(email, title, authCode);
		emailCodeManager.saveCode(email, authCode);
		return new ResponseEntity<>("성공", HttpStatus.OK);
	}

	@PostMapping("/signup/email-verification")
	public ResponseEntity<String> email_verification(@RequestBody EmailVerificationDto dto) {
		System.out.println(dto);
		boolean res = emailCodeManager.verifyCode(dto.getEmail(), dto.getAuthCode());
		if (res) {
			return new ResponseEntity<>("성공", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("실패", HttpStatus.BAD_REQUEST);
		}
	}

}

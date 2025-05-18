package com.ssafy.web.user.controller;

import com.ssafy.web.user.dto.UserLoginRequestDto;
import com.ssafy.web.user.dto.UserSignupRequestDto;
import com.ssafy.web.user.dto.UserDto;
import com.ssafy.web.user.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
	
	private final UserService userService;
	
	@PostMapping("/signup")
	public ResponseEntity<String> signup_post(@RequestBody UserSignupRequestDto userSignupRequestDto) {
		UserDto signupUser = new UserDto(userSignupRequestDto);
		int result = userService.signup(signupUser);
		if (result == 1) {
			return new ResponseEntity<>("성공", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("실패", HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/login")
	public ResponseEntity<String> login_post(
			@RequestBody UserLoginRequestDto userLoginRequestDto,
			HttpServletResponse response,
			HttpSession httpSession
	) {
		UserDto loginUser = userService.login(userLoginRequestDto.getServiceId(), userLoginRequestDto.getPassword());
		if (loginUser != null) {
			// TODO : 세션 or 토큰
			String uuid = UUID.randomUUID().toString();
			httpSession.setAttribute(uuid, loginUser);
			Cookie cookie = new Cookie("sessionId", uuid);
			cookie.setMaxAge(60 * 60);
			cookie.setPath("/");
			response.addCookie(cookie);
			return new ResponseEntity<>("성공", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("실패", HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/logout")
	public ResponseEntity<String> logout(HttpSession httpSession) {
		httpSession.invalidate();
		return new ResponseEntity<>("성공", HttpStatus.OK);
	}

}

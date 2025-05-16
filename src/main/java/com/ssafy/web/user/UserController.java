package com.ssafy.web.user;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
	
	private final UserService userService;
	
	@PostMapping("/signup")
	public ResponseEntity<String> signup_post(@RequestBody SignupRequestUserDto signupRequestUserDto) {
		UserDto signupUser = new UserDto(signupRequestUserDto);
		userService.signup(signupUser);
		return new ResponseEntity<>("성공", HttpStatusCode.valueOf(200));
	}

}

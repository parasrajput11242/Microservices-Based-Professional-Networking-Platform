package com.paras.linkedInProject.userService.controller;

import com.paras.linkedInProject.userService.dto.LoginRequestDto;
import com.paras.linkedInProject.userService.dto.SignUpRequestDto;
import com.paras.linkedInProject.userService.dto.UserDto;
import com.paras.linkedInProject.userService.repository.UserRepository;
import com.paras.linkedInProject.userService.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserController {

    private final AuthService authService;
    private final UserRepository userRepository;

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signUp(@RequestBody SignUpRequestDto signUpRequestDto){
        UserDto userDto = authService.signUp(signUpRequestDto);
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }


    @PostMapping("/login")
    public String login( @RequestBody  LoginRequestDto loginRequestDto){
        return authService.login(loginRequestDto);
    }
}

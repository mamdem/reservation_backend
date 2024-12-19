package com.sens.reservation.controller;

import com.sens.reservation.dto.JwtAuthResponse;
import com.sens.reservation.dto.LoginDto;
import com.sens.reservation.dto.RegisterDto;
import com.sens.reservation.dto.UserDto;
import com.sens.reservation.entity.User;
import com.sens.reservation.repository.UserRepository;
import com.sens.reservation.service.AuthService;
import com.sens.reservation.service.Impl.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private AuthService authService;
    @Autowired
    UserRepository userRepository;
    UserServiceImpl userService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
       String response =  authService.register(registerDto);
       return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginDto loginDto){

      JwtAuthResponse  jwtAuthResponse =  authService.login(loginDto);
      return new ResponseEntity<>(jwtAuthResponse, HttpStatus.OK);
    }

    @PostMapping("/info/{phoneNumber}")
    public UserDto getUserByPhoneNumber(@PathVariable String phoneNumber){
        User user = userRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        return new UserDto(user.getId(),user.getFirstname(), user.getLastname(),user.getEmail(), user.getPhoneNumber(), user.getAddress(), user.getPhoneNumber());
    }
}

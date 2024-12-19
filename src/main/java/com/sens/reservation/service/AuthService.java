package com.sens.reservation.service;

import com.sens.reservation.dto.JwtAuthResponse;
import com.sens.reservation.dto.LoginDto;
import com.sens.reservation.dto.RegisterDto;

public interface AuthService {
    String register(RegisterDto registerDto);
    JwtAuthResponse login(LoginDto loginDto);
}

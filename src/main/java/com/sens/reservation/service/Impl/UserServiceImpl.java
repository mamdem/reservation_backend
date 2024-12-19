package com.sens.reservation.service.Impl;

import com.sens.reservation.entity.User;
import com.sens.reservation.repository.UserRepository;
import com.sens.reservation.service.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public Optional<User> findByUsername(String phoneNumber) {
    return   userRepository.findByPhoneNumber(phoneNumber);
    }
}

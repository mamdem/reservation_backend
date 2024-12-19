package com.sens.reservation.service;

import com.sens.reservation.entity.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findByUsername(String username);
}

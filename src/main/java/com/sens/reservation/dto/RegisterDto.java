package com.sens.reservation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDto {
    private String firstname;
    private String lastname;
    private String email;
    private String phoneNumber;
    private String address;
    private String password;
}

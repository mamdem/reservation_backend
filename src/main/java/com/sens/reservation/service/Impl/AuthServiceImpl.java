package com.sens.reservation.service.Impl;

import com.sens.reservation.dto.JwtAuthResponse;
import com.sens.reservation.dto.LoginDto;
import com.sens.reservation.dto.RegisterDto;
import com.sens.reservation.entity.Role;
import com.sens.reservation.entity.User;
import com.sens.reservation.exception.ReservationException;
import com.sens.reservation.repository.RoleRepository;
import com.sens.reservation.repository.UserRepository;
import com.sens.reservation.security.JwtTokenProvider;
import com.sens.reservation.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtTokenProvider;
    @Override
    public String register(RegisterDto registerDto) {

        System.out.println(registerDto.getPhoneNumber());
        System.out.println(registerDto.getFirstname());
        System.out.println(registerDto.getLastname());
        System.out.println(registerDto.getPassword());
        System.out.println(registerDto.getEmail());
        System.out.println(registerDto.getAddress());

        // check if username is already exist in database
        if (userRepository.existsByPhoneNumber(registerDto.getPhoneNumber())){
            throw new ReservationException(HttpStatus.BAD_REQUEST, "PhoneNumber already exist");
        }

        // check if email exist in database
        /*if (userRepository.existsByEmail(registerDto.getEmail())){
            throw new ReservationException(HttpStatus.BAD_REQUEST, "Email already exist");
        }*/

        User user = new User();
        user.setFirstname(registerDto.getFirstname());
        user.setLastname(registerDto.getLastname());
        user.setEmail(registerDto.getEmail());
        user.setPhoneNumber(registerDto.getPhoneNumber());
        user.setAddress(registerDto.getAddress());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName("ROLE_USER");
        roles.add(userRole);
        user.setRoles(roles);

        userRepository.save(user);

        return "User Register successfuly";
    }


    @Override
    public JwtAuthResponse login(LoginDto loginDto) {
      Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getPhoneNumber(),
                loginDto.getPassword()
        ));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);
        Optional<User> optionalUser =   userRepository.findByPhoneNumber(loginDto.getPhoneNumber());

     String role = null;
     String firstname = null;

     if(optionalUser.isPresent()) {

         User loggedInUser = optionalUser.get();
         firstname = loggedInUser.getFirstname();
         Optional<Role> optionalRole =   loggedInUser.getRoles().stream().findFirst();

      if(optionalRole.isPresent()) {

          Role userRole = optionalRole.get();
          role = userRole.getName();
      }
     }

     JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
     jwtAuthResponse.setRole(role);
     jwtAuthResponse.setAccessToken(token);
     jwtAuthResponse.setFirstname(firstname);

     return jwtAuthResponse;
    }
}

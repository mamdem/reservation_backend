package com.sens.reservation.dto;

import com.sens.reservation.entity.User;
import com.sens.reservation.utils.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDto {
    private Long id;
    private String source;
    private String destination;
    private LocalDateTime departure;
    private LocalDateTime arrived;
    private Integer quantity;
    private String detail;
    private Status status;
    private User user;
   // private String currentUser;

}

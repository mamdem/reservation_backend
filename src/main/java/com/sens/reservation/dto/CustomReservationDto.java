package com.sens.reservation.dto;

import com.sens.reservation.utils.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomReservationDto {
    private Long id;
    private String source;
    private String destination;
    private LocalDateTime departure;
    private LocalDateTime arrived;
    private Integer quantity;
    private String detail;
    private Status status;
}

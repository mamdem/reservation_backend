package com.sens.reservation.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class ReservationException extends RuntimeException{
    private HttpStatus status;
    private String message;
}

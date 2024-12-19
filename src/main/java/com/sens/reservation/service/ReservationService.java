package com.sens.reservation.service;

import com.sens.reservation.dto.CustomReservationDto;
import com.sens.reservation.dto.ReservationDto;
import com.sens.reservation.utils.Status;

import java.util.List;

public interface ReservationService {

    ReservationDto addReservation(ReservationDto reservationDto);
    List<CustomReservationDto> getReservationByUser(Long userId);

    CustomReservationDto getReservationById(Long id);

    ReservationDto updateReservation(Long id, ReservationDto reservationDto);

    void updateStatus(Long reservationId, Status status);
}

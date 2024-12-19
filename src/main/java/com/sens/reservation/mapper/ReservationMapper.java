package com.sens.reservation.mapper;

import com.sens.reservation.dto.CustomReservationDto;
import com.sens.reservation.dto.ReservationDto;
import com.sens.reservation.entity.Reservation;

public class ReservationMapper {
    public static CustomReservationDto mapToReservationDto(Reservation reservation){
        return new CustomReservationDto(
            reservation.getId(),
            reservation.getSource(),
            reservation.getDestination(),
            reservation.getDeparture(),
            reservation.getArrived(),
            reservation.getQuantity(),
            reservation.getDetail(),
            reservation.getStatus()
        );
    }

    public static Reservation maptoReservation(CustomReservationDto customReservationDto){
        Reservation reservation = new Reservation();
        reservation.setId(customReservationDto.getId());
        reservation.setSource(customReservationDto.getSource());
        reservation.setDestination(customReservationDto.getDestination());
        reservation.setDeparture(customReservationDto.getDeparture());
        reservation.setArrived(customReservationDto.getArrived());
        reservation.setDetail(customReservationDto.getDetail());
        reservation.setStatus(customReservationDto.getStatus());

        return reservation;
    }

    // Méthode utilitaire pour la conversion de Reservation à ReservationDto
    public static ReservationDto mapPostReservationToDto(Reservation reservation) {
        ReservationDto reservationDto = new ReservationDto();
        reservationDto.setDestination(reservation.getDestination());
        reservationDto.setSource(reservation.getSource());
        reservationDto.setDeparture(reservation.getDeparture());
        reservationDto.setArrived(reservation.getArrived());
        reservationDto.setDetail(reservation.getDetail());
        reservationDto.setStatus(reservation.getStatus());
        return reservationDto;
    }
}

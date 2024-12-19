package com.sens.reservation.service.Impl;

import com.sens.reservation.dto.CustomReservationDto;
import com.sens.reservation.dto.ReservationDto;
import com.sens.reservation.entity.Reservation;
import com.sens.reservation.mapper.ReservationMapper;
import com.sens.reservation.repository.ReservationRepository;
import com.sens.reservation.service.ReservationService;
import com.sens.reservation.utils.Status;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationServiceImpl implements ReservationService {
    public ReservationServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    private ReservationRepository reservationRepository;
    @Override
    public ReservationDto addReservation(ReservationDto reservationDto) {

        // convert reservationDto to entity JPA

        Reservation reservation = new Reservation();
        reservation.setSource(reservationDto.getSource());
        reservation.setDestination(reservationDto.getDestination());
        reservation.setDeparture(reservationDto.getDeparture());
        reservation.setArrived(reservationDto.getArrived());
        reservation.setDetail(reservationDto.getDetail());
        reservation.setStatus(reservationDto.getStatus());
        reservation.setQuantity(reservationDto.getQuantity());
        reservation.setUser(reservationDto.getUser());

        // Reservation Jpa entity
        Reservation savedReservation = reservationRepository.save(reservation);

        // Convert saved Reservation JPA entity object into ReservationDto object

        return ReservationMapper.mapPostReservationToDto(savedReservation);
    }

    @Override
    public List<CustomReservationDto> getReservationByUser(Long userId) {
        List<Reservation> reservations = reservationRepository.findByUserId(userId);
        return reservations.stream().map((reservation -> ReservationMapper.mapToReservationDto(reservation)))
                .collect(Collectors.toList());
    }


    public CustomReservationDto getReservationById(Long id){
      Reservation reservation =  reservationRepository.findById(id).get();
      return ReservationMapper.mapToReservationDto(reservation);
    }

    @Override
    public ReservationDto updateReservation(Long id, ReservationDto reservationDto) {
     Reservation reservation =  reservationRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("not found user."));
        reservation.setDestination(reservationDto.getDestination());
        reservation.setSource(reservationDto.getSource());
        reservation.setDeparture(reservationDto.getDeparture());
        reservation.setArrived(reservationDto.getArrived());
        reservation.setDetail(reservationDto.getDetail());
        reservation.setStatus(reservationDto.getStatus());

      Reservation updatedReservation  =  reservationRepository.save(reservation);

        // Convert Reservation JPA ENTITY to ReservationDto

        return ReservationMapper.mapPostReservationToDto(updatedReservation) ;
    }

    @Override
    public void updateStatus(Long reservationId, Status status) {
        Reservation reservation =  reservationRepository.findById(reservationId).orElseThrow(() -> new UsernameNotFoundException("not found user."));
        reservation.setStatus(status);
        reservationRepository.save(reservation);

    }
}

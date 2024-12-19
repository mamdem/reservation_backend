package com.sens.reservation.controller;

import com.sens.reservation.dto.CustomReservationDto;
import com.sens.reservation.dto.ReservationDto;
import com.sens.reservation.entity.User;
import com.sens.reservation.service.ReservationService;
import com.sens.reservation.service.UserService;
import com.sens.reservation.utils.Status;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/reservations")
@AllArgsConstructor
public class ReservationController {
    private ReservationService reservationService;
    private UserDetailsService userDetailsService;
    private UserService userService;
    private static final Logger LOGGER = LoggerFactory.getLogger(ReservationController.class);
    // Build add reservation

    // Enable authorization in endpoint with method level security
   // @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("")
    public ResponseEntity<ReservationDto> addReservation(@RequestBody ReservationDto reservationDto){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LOGGER.info(String.valueOf(authentication));
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        UserDetails user =  userDetailsService.loadUserByUsername(userDetails.getUsername());
        LOGGER.info(String.valueOf(user));

        User currentUser =  userService.findByUsername(user.getUsername()).orElseThrow(() -> new UsernameNotFoundException("not found user."));
        reservationDto.setUser(currentUser);


        LOGGER.info("current user {}", String.valueOf(reservationDto.getUser()));
        LOGGER.info("reservationDto {}", reservationDto);
        ReservationDto savedReservation =  reservationService.addReservation(reservationDto);

      return  new ResponseEntity<>(savedReservation, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CustomReservationDto>> getReservationByUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        UserDetails user = userDetailsService.loadUserByUsername(userDetails.getUsername());
        User currentUser = userService.findByUsername(user.getUsername()).orElseThrow(() -> new UsernameNotFoundException("not found user."));

        List<CustomReservationDto> reservationDto = reservationService.getReservationByUser(currentUser.getId());
        return ResponseEntity.ok(reservationDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomReservationDto> getReservationById(@PathVariable("id") Long id){
      CustomReservationDto customReservationDto =  reservationService.getReservationById(id);
      return ResponseEntity.ok(customReservationDto);
    }

    @PutMapping("{id}")
    public ResponseEntity<ReservationDto> updateReservation(@PathVariable("id") Long id,
                                                            @RequestBody ReservationDto reservationDto){
    ReservationDto updatedReservation =    reservationService.updateReservation(id, reservationDto);
    return ResponseEntity.ok(updatedReservation);
    }

    @PutMapping("/{reservationId}/update-status/{status}")
    public ResponseEntity<Void> updateStatus(@PathVariable Long reservationId,
                                             @PathVariable  Status status){
         reservationService.updateStatus(reservationId, status);
         return new ResponseEntity<>(HttpStatus.OK);
    }


}

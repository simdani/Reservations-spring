package com.simonasdaniliauskas.landon.business.service;

import com.simonasdaniliauskas.landon.business.domain.RoomReservation;
import com.simonasdaniliauskas.landon.data.entity.Guest;
import com.simonasdaniliauskas.landon.data.entity.Reservation;
import com.simonasdaniliauskas.landon.data.entity.Room;
import com.simonasdaniliauskas.landon.data.repository.GuestRepository;
import com.simonasdaniliauskas.landon.data.repository.ReservationRepository;
import com.simonasdaniliauskas.landon.data.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ReservationService {
    private final RoomRepository roomRepository;
    private final GuestRepository guestRepository;
    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationService(RoomRepository roomRepository, GuestRepository guestRepository, ReservationRepository reservationRepository) {
        this.roomRepository = roomRepository;
        this.guestRepository = guestRepository;
        this.reservationRepository = reservationRepository;
    }

    public List<RoomReservation> getRoomReservationsForDate(Date date) {
        Iterable<Room> rooms = this.roomRepository.findAll();
        Map<Long, RoomReservation> roomReservationMap = new HashMap<>();
        rooms.forEach(room -> {
            RoomReservation roomReservation = new RoomReservation();
            roomReservation.setRoomId(room.getId());
            roomReservation.setRoomName(room.getName());
            roomReservation.setRoomNumber(room.getNumber());
            roomReservationMap.put(room.getId(), roomReservation);
        });

        Iterable<Reservation> reservations = this.reservationRepository.findByDate(new java.sql.Date(date.getTime()));
        if (reservations != null) {
            reservations.forEach(reservation -> {
                Optional<Guest> guestResponse = this.guestRepository.findById(reservation.getGuestId());
                if (guestResponse.isPresent()) {
                    Guest guest = guestResponse.get();
                    RoomReservation roomReservation = roomReservationMap.get(reservation.getId());
                    roomReservation.setDate(date);
                    roomReservation.setFirstName(guest.getFirstName());
                    roomReservation.setLastName(guest.getLastName());
                    roomReservation.setGuestId(guest.getId());
                }
            });
        }
        List<RoomReservation> roomReservations = new ArrayList<>();
        for  (Long roomId: roomReservationMap.keySet()) {
            roomReservations.add(roomReservationMap.get(roomId));
        }

        return roomReservations;
    }
}

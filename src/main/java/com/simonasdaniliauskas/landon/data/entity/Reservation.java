package com.simonasdaniliauskas.landon.data.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="Reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="RESERVATION_ID")
    private long id;

    @Column(name="ROOM_ID")
    private long roomId;

    @Column(name="GUEST_ID")
    private long guestId;

    @Column(name="RES_DATE")
    private Date date;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getRoomId() {
        return roomId;
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }

    public long getGuestId() {
        return guestId;
    }

    public void setGuestId(long guestId) {
        this.guestId = guestId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

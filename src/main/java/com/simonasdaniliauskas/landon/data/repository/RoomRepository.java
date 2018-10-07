package com.simonasdaniliauskas.landon.data.repository;

import com.simonasdaniliauskas.landon.data.entity.Room;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends CrudRepository<Room, Long> {
    Room findByNumber(String number);
}

package com.simonasdaniliauskas.landon.data.repository;

import com.simonasdaniliauskas.landon.data.entity.Guest;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestRepository extends PagingAndSortingRepository<Guest, Long> {
}

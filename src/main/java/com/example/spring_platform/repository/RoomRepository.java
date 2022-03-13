package com.example.spring_platform.repository;

import com.example.spring_platform.entity.Room;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Integer> {
    Slice<Room> findAllByHotel_Id(Integer hotel_id, Pageable pageable);
}

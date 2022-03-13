package com.example.spring_platform.repository;

import com.example.spring_platform.entity.Hotel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HotelRepository extends JpaRepository<Hotel, Integer> {
    Page<Hotel> findAllById(Integer id, Pageable pageable);
}

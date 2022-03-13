package com.example.spring_platform.controller;

import com.example.spring_platform.dto.HotelDto;
import com.example.spring_platform.entity.Hotel;
import com.example.spring_platform.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hotel")
public class HotelController {

    private final HotelRepository hotelRepository;

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody HotelDto hotelDto) {
        Hotel hotel = new Hotel();
        hotel.setName(hotelDto.getName());
        hotelRepository.save(hotel);
        return ResponseEntity.ok("Success");
    }

    @GetMapping("/get")
    public ResponseEntity<?> get() {
        return ResponseEntity.ok().body(hotelRepository.findAll());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> get(@PathVariable Integer id, @RequestParam int page) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<Hotel> allById = hotelRepository.findAllById(id, pageable);
        return ResponseEntity.ok().body(allById);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        Optional<Hotel> optionalHotel = hotelRepository.findById(id);
        if (optionalHotel.isPresent()){
            hotelRepository.deleteById(id);
            return ResponseEntity.ok("Deleted successfully");
        }
        return ResponseEntity.badRequest().body("Not found");
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> edit(@PathVariable Integer id, @RequestBody HotelDto hotelDto){
        Optional<Hotel> byId = hotelRepository.findById(id);
        if (byId.isPresent()){
            Hotel hotel = new Hotel();
            hotel.setId(byId.get().getId());
            hotel.setName(hotelDto.getName());
            hotelRepository.save(hotel);
            return ResponseEntity.ok("Successfully edited");
        }
        return ResponseEntity.badRequest().body("Not found");
    }
}

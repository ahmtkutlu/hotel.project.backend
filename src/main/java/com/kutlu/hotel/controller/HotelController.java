package com.kutlu.hotel.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kutlu.hotel.exception.ResourceNotFoundException;
import com.kutlu.hotel.model.Hotel;
import com.kutlu.hotel.repository.HotelRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/hotel/")
public class HotelController {
	
	@Autowired
	private HotelRepository hotelRepository;
	
	@GetMapping("hotel")
	public List<Hotel> getAll(){
		return hotelRepository.findAll();
	}
	
	@PostMapping("hotel")
	public Hotel create(@RequestBody Hotel hotel) {
		return hotelRepository.save(hotel);
	}
	
	@GetMapping("/hotel/{id}")
	public ResponseEntity<Hotel> getById(@PathVariable Long id) {
		Hotel hotel = hotelRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Hotel not exist with id : " + id));
		return ResponseEntity.ok(hotel);
	}
	
	@PutMapping("/hotel/{id}")
	public ResponseEntity<Hotel> update(@PathVariable Long id, @RequestBody Hotel hotel){
		Hotel hotelDetail = hotelRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Hotel not exist with id : " + id));
		hotelDetail.setName(hotel.getName());
		hotelDetail.setStatus(hotel.isStatus());
		hotelDetail = hotelRepository.save(hotelDetail);
		return ResponseEntity.ok(hotelDetail);
	}
	
	@DeleteMapping("/hotel/{id}")
	public ResponseEntity<Map<String, Boolean>> delete(@PathVariable Long id){
		Hotel hotelDetail = hotelRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Hotel not exist with id : " + id));
		hotelRepository.delete(hotelDetail);
		Map<String, Boolean> response = new HashMap<>();
		response.put("result", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
//	@PutMapping("/hotel/passive/{id}")
//	public ResponseEntity<Map<String, Boolean>> delete(@PathVariable Long id){
//		// silme işleminde sadece statusu false yapacam
//		Hotel hotelDetail = hotelRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Hotel not exist with id : " + id));
//		hotelDetail.setStatus(false);
//		hotelDetail = hotelRepository.save(hotelDetail);
//		Map<String, Boolean> response = new HashMap<>();
//		response.put("result", Boolean.TRUE);
//		return ResponseEntity.ok(response);
//	}

}
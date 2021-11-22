package com.kutlu.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kutlu.hotel.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}

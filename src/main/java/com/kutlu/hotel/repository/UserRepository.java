package com.kutlu.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.kutlu.hotel.model.AdminUser;

@Repository
public interface UserRepository extends JpaRepository<AdminUser, Long>{


	@Query(value="select * from admin_user where mail = ?1 and password = ?2", nativeQuery = true)
	AdminUser login(String email, String password);
	
	AdminUser findByMail(String mail);
}
package com.kutlu.hotel.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kutlu.hotel.model.AdminUser;
import com.kutlu.hotel.repository.UserRepository;

@Service
public class AuthService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
		AdminUser user = userRepository.findByMail(mail);
		if (user == null) {
			throw new UsernameNotFoundException(mail);
		}
		return user;
	}
}

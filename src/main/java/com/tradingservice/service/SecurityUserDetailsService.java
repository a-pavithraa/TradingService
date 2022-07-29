package com.tradingservice.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tradingservice.controller.DataIngestionController;
import com.tradingservice.entity.User;
import com.tradingservice.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.stream.Collectors;

@Service("userDetailsService")
@Transactional
public class SecurityUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    private static final Logger logger = LoggerFactory.getLogger(SecurityUserDetailsService.class);

    public SecurityUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    	
        Optional<User> user = userRepository.findByEmail(email);
       
        if (user.isPresent()) {
        	 logger.debug("username:{}",user.get().getName());
            return convert(user.get());
        }
        throw new UsernameNotFoundException("User not found");
    }

    private org.springframework.security.core.userdetails.User convert(User user) {
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                user.getRoles().stream().map(r -> new SimpleGrantedAuthority(r.getName()))
                        .collect(Collectors.toList())
        );
    }
}

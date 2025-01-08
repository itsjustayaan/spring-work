package com.working.services;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import  org.springframework.security.core.userdetails.User;

import com.working.dao.UserRepository;
import com.working.model.Users;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        Set<GrantedAuthority> authorities = user.getAuthorities().stream()
            .map(auth -> new SimpleGrantedAuthority(auth.getAuthority()))
            .collect(Collectors.toSet());
//        System.out.println(user.getPassword());

        return new User(user.getUsername(),"{noop}"+user.getPassword(), user.isEnabled(), true, true, true, authorities);
    }
}
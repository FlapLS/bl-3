package com.example.bl_lab1.service;

import com.example.bl_lab1.model.User;
import com.example.bl_lab1.utils.CustomUserDetails;
import lombok.SneakyThrows;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthenticationService
      implements UserDetailsService {
    
    private final UserService userService;
    
    public AuthenticationService(UserService userService) {
        this.userService = userService;
    }
    
    @SneakyThrows
    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       
        User user = userService.getUserByLogin(username);
    
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole()));
        return new CustomUserDetails(user.getId(), username, user.getPassword(), authorities);
    }
}

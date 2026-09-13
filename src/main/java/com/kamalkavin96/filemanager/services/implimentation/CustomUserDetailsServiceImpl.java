package com.kamalkavin96.filemanager.services.implimentation;

import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kamalkavin96.filemanager.models.Role;
import com.kamalkavin96.filemanager.models.User;
import com.kamalkavin96.filemanager.repository.UserRepo;
import com.kamalkavin96.filemanager.repository.UserRolesRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    private final UserRepo userRepo;
    private final UserRolesRepo userRolesRepo;

    @Override
    public UserDetails loadUserByUsername(String emailId) throws UsernameNotFoundException {
        
        User user = userRepo.findByEmail(emailId)
            .orElseThrow(()-> new UsernameNotFoundException("Email not found: "+ emailId));

        List<Role> roles = userRolesRepo.findByUserId(user.getId());
        List<SimpleGrantedAuthority> authorities = roles
            .stream()
            .map(r -> new SimpleGrantedAuthority(r.getName())).toList();

        return org.springframework.security.core.userdetails.User
            .withUsername(user.getEmail())
            .password(user.getPassword())
            .authorities(authorities)
            .build();
    }

}

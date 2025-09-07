package com.learn.rest_crud.service;

import com.learn.rest_crud.model.User;
import com.learn.rest_crud.model.UserImpl;
import com.learn.rest_crud.repo.UserRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String input) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(input);

        if (user == null) {
            user = userRepo.findByUsername(input);
        }

        if (user == null) {
            log.warn("User not found!");
            throw new UsernameNotFoundException("User not found!");
        }

        return new UserImpl(user);
    }
}


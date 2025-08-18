package com.handoff.service;

import com.handoff.model.entity.User;
import com.handoff.repository.UserRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class UserLookupService {
    private final UserRepository userRepository;

    public UserLookupService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Cacheable(value = "userByEmail", key = "#email")
    @Transactional(readOnly = true)
    public User findByEmailOrThrow(String email) {
        return userRepository.findByEmail(email.toLowerCase())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Cacheable(value = "userById", key = "#id")
    @Transactional(readOnly = true)
    public User findByIdOrThrow(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}


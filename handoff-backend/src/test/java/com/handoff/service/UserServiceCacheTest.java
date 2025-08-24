package com.handoff.service;

import com.handoff.model.entity.User;
import com.handoff.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = UserServiceCacheTest.TestConfig.class)
class UserServiceCacheTest {

    @Configuration
    @EnableCaching
    static class TestConfig {
        @Bean
        CacheManager cacheManager() {
            return new ConcurrentMapCacheManager("userByEmail");
        }

        @Bean
        UserRepository userRepository() {
            return Mockito.mock(UserRepository.class);
        }

        @Bean
        UserLookupService userLookupService(UserRepository userRepository) {
            return new UserLookupService(userRepository);
        }

        @Bean
        PasswordEncoder passwordEncoder() {
            return Mockito.mock(PasswordEncoder.class);
        }

        @Bean
        UserService userService(UserRepository userRepository, PasswordEncoder passwordEncoder, UserLookupService userLookupService) {
            return new UserService(userRepository, passwordEncoder, userLookupService);
        }
    }

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CacheManager cacheManager;

    @Test
    void findByEmailOrThrow_isCached() {
        String email = "test@example.com";
        User user = new User();
        user.setId(UUID.randomUUID());
        user.setEmail(email);
        Mockito.when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(user));

        // First call: hits repository
        User u1 = userService.findByEmailOrThrow(email);
        // Second call: served from cache
        User u2 = userService.findByEmailOrThrow(email);

        assertThat(u1).isSameAs(u2);
        verify(userRepository, times(1)).findByEmail(email);
    }
}
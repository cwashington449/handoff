package com.handoff.it;

import com.handoff.model.entity.User;
import com.handoff.model.enums.UserRole;
import com.handoff.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Testcontainers
@ActiveProfiles("test")
@TestPropertySource(properties = {
        "spring.flyway.enabled=false",
        "spring.jpa.hibernate.ddl-auto=create-drop",
        "spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect"
})
class PostgresContainerIntegrationTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16-alpine");

    @Autowired
    private UserRepository userRepository;

    @Test
    void canSaveAndLoadUser() {
        // Arrange
        User u = new User();
        u.setEmail("test@example.com");
        u.setPasswordHash("$2a$10$abcdefghijklmnopqrstuv"); // dummy bcrypt-like string
        u.setFirstName("Test");
        u.setLastName("User");
        u.setRole(UserRole.CREATOR);

        // Act
        User saved = userRepository.save(u);

        // Assert
        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getEmail()).isEqualTo("test@example.com");

        User found = userRepository.findById(saved.getId()).orElseThrow();
        assertThat(found.getFirstName()).isEqualTo("Test");
        assertThat(found.getRole()).isEqualTo(UserRole.CREATOR);
    }
}

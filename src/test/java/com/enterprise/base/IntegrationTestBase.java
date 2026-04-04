package com.enterprise.base;

import com.enterprise.posapp.PosAppApplication;
import com.enterprise.posapp.config.SecurityConfig;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

// =================================================
// Para Entornos CI: @ServiceConnection
// =================================================

//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@AutoConfigureMockMvc
////@Testcontainers
//public class IntegrationTestBase {
//    @Container
//    @ServiceConnection
//    static MySQLContainer <?> mysql = new MySQLContainer<>("mysql:8.0")
//            .withReuse(true); // Reutiliza el container entre tests
//
//}

// =====================================
// PARA LOCAL: Docker Compose Support
// =====================================

@SpringBootTest(
        classes = PosAppApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public abstract class IntegrationTestBase {
    // Spring Boot levanta docker-compose automáticamente al iniciar los test
}

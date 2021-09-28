package ru.mail.senokosov.artem.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import ru.mail.senokosov.artem.web.TravelBotApplication;

@SpringBootTest(classes = TravelBotApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class BaseIT {

    @Autowired
    protected TestRestTemplate testRestTemplate;

    public static final MySQLContainer mySQLContainer;

    static {
        mySQLContainer = (MySQLContainer) new MySQLContainer("mysql:8.0")
                .withUsername("test")
                .withPassword("1111")
                .withReuse(true);
        mySQLContainer.start();
    }

    @DynamicPropertySource
    public static void setDataSourceProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
        dynamicPropertyRegistry.add("spring.datasource.url", mySQLContainer::getJdbcUrl);
        dynamicPropertyRegistry.add("spring.datasource.username", mySQLContainer::getUsername);
        dynamicPropertyRegistry.add("spring.datasource.password", mySQLContainer::getPassword);
    }
}
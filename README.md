# Spring Boot Testing Guide

## Table of Contents
1. [Introduction](#introduction)
2. [Testing Frameworks](#testing-frameworks)
    - [JUnit 5](#junit-5)
    - [Mockito](#mockito)
    - [Spring Boot Test](#spring-boot-test)
    - [Testcontainers](#testcontainers)
    - [Hamcrest](#hamcrest)
    - [AssertJ](#assertj)
3. [Setup](#setup)
4. [Writing Tests](#writing-tests)
    - [Unit Tests](#unit-tests)
    - [Integration Tests](#integration-tests)
    - [End-to-End Tests](#end-to-end-tests)
5. [Running Tests](#running-tests)
6. [Best Practices](#best-practices)
7. [Resources](#resources)

## Introduction
Testing is a crucial part of developing a robust Spring Boot application. This guide covers various testing frameworks that can be used to ensure your application functions correctly.

## Testing Frameworks
### JUnit 5
JUnit 5 is the de facto standard for unit testing Java applications. It provides a rich set of annotations and assertions to write and manage tests.

### Mockito
Mockito is a popular mocking framework for Java. It allows you to create mock objects and define their behavior for testing purposes.

### Spring Boot Test
Spring Boot Test provides support for writing integration tests in a Spring context. It includes various utilities and annotations to simplify integration testing.

### Testcontainers
Testcontainers is a Java library that supports JUnit tests, providing lightweight, throwaway instances of common databases, Selenium web browsers, or anything else that can run in a Docker container.

### Hamcrest
Hamcrest is a framework for writing matcher objects allowing 'match' rules to be defined declaratively.

### AssertJ
AssertJ provides a rich and fluent set of assertions to improve test readability and maintainability.

## Setup
To start testing a Spring Boot application, include the following dependencies in your `pom.xml` file:

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.mockito</groupId>
        <artifactId>mockito-core</artifactId>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.testcontainers</groupId>
        <artifactId>junit-jupiter</artifactId>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.assertj</groupId>
        <artifactId>assertj-core</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>
```

## Writing Tests

### Unit Tests
Unit tests focus on testing individual components in isolation. Typically, these tests use Mockito to mock dependencies.

Example:
```java
@RunWith(MockitoJUnitRunner.class)
public class MyServiceTest {

    @InjectMocks
    private MyService myService;

    @Mock
    private MyRepository myRepository;

    @Test
    public void testMyServiceMethod() {
        // Arrange
        when(myRepository.findSomething()).thenReturn(new Something());

        // Act
        Something result = myService.doSomething();

        // Assert
        assertThat(result).isNotNull();
        verify(myRepository).findSomething();
    }
}
```

### Integration Tests
Integration tests verify the interaction between multiple components and use Spring Boot's test support.

Example:
```java
@SpringBootTest
@AutoConfigureMockMvc
public class MyControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetEndpoint() throws Exception {
        mockMvc.perform(get("/api/my-endpoint"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.key").value("value"));
    }
}
```

### End-to-End Tests
End-to-end tests ensure the complete flow of the application works as expected. Testcontainers can be used to provide the necessary infrastructure.

Example:
```java
@SpringBootTest
@Testcontainers
public class ApplicationE2ETest {

    @Container
    public static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:latest")
        .withDatabaseName("testdb")
        .withUsername("user")
        .withPassword("password");

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testApplicationFlow() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/my-endpoint", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).contains("expectedContent");
    }
}
```

## Running Tests
Tests can be run using your build tool's standard test task:

**Maven:**
```sh
mvn test
```

## Best Practices
- Write clear and concise test cases.
- Use meaningful names for test methods.
- Test one scenario per test method.
- Mock external dependencies in unit tests.
- Use integration tests to verify the interaction between components.
- Use end-to-end tests sparingly to test critical application flows.
- Clean up resources after tests run.

## Resources
- [Spring Boot Testing Documentation](https://docs.spring.io/spring-boot/docs/current/reference/html/features.html#features.testing)
- [JUnit 5 User Guide](https://junit.org/junit5/docs/current/user-guide/)
- [Mockito Documentation](https://site.mockito.org/)
- [Testcontainers Documentation](https://www.testcontainers.org/)
- [Hamcrest Documentation](http://hamcrest.org/JavaHamcrest/)
- [AssertJ Documentation](https://assertj.github.io/doc/)


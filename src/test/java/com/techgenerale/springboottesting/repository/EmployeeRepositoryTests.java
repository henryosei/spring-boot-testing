package com.techgenerale.springboottesting.repository;

import com.techgenerale.springboottesting.model.Employee;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
public class EmployeeRepositoryTests {

    @Autowired
    private EmployeeRepository employeeRepository;

    private Employee employee;

    @BeforeEach
    void setup(){
         employee = new Employee().builder()
                .email("rochanry@gmail.com")
                .firstName("Rochan")
                .lastName("Golden")
                .build();
    }
    @DisplayName("Junit test for getAllEmployee operations")
    @Test
    void giveEmployeeObject_whenSave_thenReturnSavedEmployee() {

        Employee save = employeeRepository.save(employee);
        assertThat(save).isEqualTo(employee);
        assertThat(save.getEmail()).isEqualTo("rochanry@gmail.com");
        assertThat(save.getFirstName()).isEqualTo("Rochan");
    }

    @DisplayName("test findById operation")
    @Test
    void givenEmployee_whenFindById_thenReturnEmployee() {



        Employee employee1 = employeeRepository.save(employee);
        assertThat(employeeRepository.findById(employee.getId())).isNotNull();
    }

    @Test
    void givenEmployee_whenSaveAll_thenAssertEqual() {
        //given
      /*  new Employee();
        Employee employee = Employee.builder()
                .id(Long.valueOf(1))
                .email("rochanry@gmail.com")
                .firstName("Rochan")
                .lastName("Golden")
                .build();*/


        employeeRepository.saveAll(List.of(employee, Employee.builder()
                .id(1L)
                .email("rochanry@gmail.com")
                .firstName("Rochan")
                .lastName("Golden")
                .build()));
        List<Employee> employees = employeeRepository.findAll();
        assertThat(employees).isNotNull();
        assertThat(employees.size()).isEqualTo(2);
        //when
        //then
    }

    @DisplayName("Test Employee Email")
    @Test
    void givenEmployeeObject_whenEmailISet_thenReturnEmployee() {
        //given
//        Employee employee = new Employee().builder()
//                .id(Long.valueOf(1))
//                .email("rochanry@gmail.com")
//                .firstName("Rochan")
//                .lastName("Golden")
//                .build();
        ;

//
        employeeRepository.save(employee);
        Employee employeeDB = employeeRepository.findEmployeeByEmail(employee.getEmail()).get();
        assertThat(employeeDB).isNotNull();
        //when
        //then
    }

    @DisplayName("Junit test for the employee update")
    @Test
    void givenEmployeeObject_whenUpdateEmployee_thenEmployeeUpdated(){
     //given
        Employee employee = new Employee().builder()
                .email("rochanry@gmail.com")
                .firstName("Rochan")
                .lastName("Golden")
                .build();
     //when
        Employee employeeDB= employeeRepository.save(employee);
        Employee updateEmployee=employeeRepository.findById(employeeDB.getId()).get();
        updateEmployee.setEmail("henryoseimensah@gmail.com");
        employeeRepository.save(updateEmployee);
        //then
        employee = employeeRepository.findEmployeeByEmail(updateEmployee.getEmail()).get();
        assertThat(employee.getEmail()).isEqualTo("henryoseimensah@gmail.com");
    }
}

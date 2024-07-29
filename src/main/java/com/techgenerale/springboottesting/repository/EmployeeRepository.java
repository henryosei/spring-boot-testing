package com.techgenerale.springboottesting.repository;

import com.techgenerale.springboottesting.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findEmployeeByEmail(String email);

    @Query("Select e from Employee e where e.firstName=?1 and e.lastName=?2")
    Employee findByJPQU(String firstName, String lastName);
}

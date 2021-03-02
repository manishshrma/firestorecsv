package com.example.assignment.repository;

import com.example.assignment.model.Employee;
import com.example.assignment.model.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UploadRepo extends CrudRepository<Employee,Long> {
}

package com.example.assignment.repository;

import com.example.assignment.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UploadRepo extends CrudRepository<User,Long> {
}

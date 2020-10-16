package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.User;

/**
 * User Interface respository that extends JpaRepository
 * for use all custom methods of ORM of JPA
 *
 * @author Min Ku Nam
 * @version 1.0
 * @created 2020/09/22
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}

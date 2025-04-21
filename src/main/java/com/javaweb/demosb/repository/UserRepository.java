package com.javaweb.demosb.repository;

import com.javaweb.demosb.entity.UserEntity;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUserName(String username);

    Optional<UserEntity> findByEmail(String email);



}

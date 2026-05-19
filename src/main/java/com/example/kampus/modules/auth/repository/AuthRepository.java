package com.example.kampus.modules.auth.repository;

import com.example.kampus.modules.auth.entity.AuthCredential;
import jakarta.persistence.Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AuthRepository extends JpaRepository<AuthCredential, Long> {

}


package com.dev.Email.repository;


import com.dev.Email.entity.Recipient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipientRepository extends JpaRepository<Recipient, Long> {

    boolean existsByEmail(String email);
    }

package com.example.springjpa.repository;

import com.example.springjpa.entity.onetoone.Fingerprint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FingerprintRepository extends JpaRepository<Fingerprint, Integer> {
}
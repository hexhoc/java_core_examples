package com.example.springjpa.repository.readonly;

import com.example.springjpa.entity.onetoone.Fingerprint;
import org.springframework.stereotype.Repository;

@Repository
public interface FingerprintRepository extends ReadOnlyRepository<Fingerprint, Integer> {

}
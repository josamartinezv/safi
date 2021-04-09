package com.called.attention.repository;

import com.called.attention.domain.ProgramNumber;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProgramNumberRepository extends JpaRepository<ProgramNumber, Long> {

    Optional<ProgramNumber> findByNumber (String number);

}

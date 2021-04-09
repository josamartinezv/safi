package com.called.attention.repository;

import com.called.attention.domain.Rols;
import com.called.attention.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolRepository extends JpaRepository<Rols, Integer> {

    Optional<Rols> findByDescription (String description);

}

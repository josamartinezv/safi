package com.called.attention.repository;

import com.called.attention.domain.DetailUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetailUserRepository extends JpaRepository<DetailUser, Long> {
}

package com.ems.repository;

import com.ems.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerRepository extends JpaRepository<Manager , Long> {
}

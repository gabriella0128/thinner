package com.nns.thinner.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nns.thinner.entity.LogEntity;

public interface LogRepository extends JpaRepository<LogEntity,Long> {
}

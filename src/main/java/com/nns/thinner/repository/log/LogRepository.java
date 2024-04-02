package com.nns.thinner.repository.log;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nns.thinner.entity.LogEntity;

public interface LogRepository extends JpaRepository<LogEntity,Long> {
}

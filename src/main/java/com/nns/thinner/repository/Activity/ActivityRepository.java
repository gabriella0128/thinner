package com.nns.thinner.repository.Activity;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nns.thinner.entity.ActivityEntity;

public interface ActivityRepository extends JpaRepository<ActivityEntity, Long> {
}

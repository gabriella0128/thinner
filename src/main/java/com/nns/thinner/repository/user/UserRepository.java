package com.nns.thinner.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nns.thinner.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

	@Query(value = "SELECT EXISTS (SELECT 1 FROM `user` u WHERE u.user_id = :userId AND u.use_yn = true)", nativeQuery = true)
	Long existsUserEntitiesByUserId(@Param("userId") String userId);
}

package com.nns.thinner.service.dtoService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nns.thinner.dto.base.UserDto;
import com.nns.thinner.entity.UserEntity;
import com.nns.thinner.mapper.UserMapper;
import com.nns.thinner.repository.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserMapper userMapper;
	private final UserRepository userRepository;

	@Transactional(readOnly = true)
	public Long existByUserId(String userId) {
		return userRepository.existsUserEntitiesByUserId(userId);
	}

	public UserDto.Info findByUserId(String userId) {
		return userMapper.toInfoDto(userRepository.findUserByUserId(userId).orElse(null));
	}

	@Transactional
	public UserDto.Info save(UserDto.Info user) {

		UserEntity userEntity = userMapper.fromInfoToEntity(user);

		userRepository.save(userEntity);

		return user;
	}

}

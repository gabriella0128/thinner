package com.nns.thinner.service.dtoService;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nns.thinner.dto.base.DietDto;
import com.nns.thinner.entity.DietEntity;
import com.nns.thinner.mapper.DietMapper;
import com.nns.thinner.repository.diet.DietRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DietService {

	private final DietMapper dietMapper;

	private final DietRepository dietRepository;

	@Transactional(readOnly = true)
	public List<DietDto.Info> findByUserIdx(Long userIdx) {
		return dietMapper.toInfoDtoList(dietRepository.findDietEntityByUser(userIdx));
	}

	@Transactional
	public DietDto.Info save(DietDto.Info diet) {
		DietEntity dietEntity = dietMapper.fromInfoToEntity(diet);

		dietRepository.save(dietEntity);

		return diet;
	}

}

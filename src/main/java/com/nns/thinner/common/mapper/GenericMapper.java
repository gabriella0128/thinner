package com.nns.thinner.common.mapper;

import java.util.List;

public interface GenericMapper<D, E> {

	D toDto(E entity);

	E toEntity(D dto);

	List<D> getDtoList(List<E> entityList);
}

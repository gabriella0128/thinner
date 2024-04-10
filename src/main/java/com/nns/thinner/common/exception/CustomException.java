package com.nns.thinner.common.exception;

import com.nns.thinner.common.code.ServiceCode;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {

	private final ServiceCode serviceCode;

	public CustomException(ServiceCode serviceCode) {
		super(serviceCode.getCode());
		this.serviceCode = serviceCode;
	}

	public ServiceCode getServiceCode() {
		return serviceCode;
	}
}

package com.olx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.olx.dto.UserDto;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class LoginDelegateImp implements LoginDelegate {

	@Autowired
	RestTemplate restTemplate;

	@Override
	@CircuitBreaker(name = "TOKEN-VALIDATE-CIRCUIT-BREAKER", fallbackMethod = "fallbackIsTokenValidate")
	public boolean isTokenValid(String authToken) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", authToken);
		headers.set("Content-Type","application/json");
		HttpEntity<Boolean> httpEntity = new HttpEntity<>(null, headers);
		ResponseEntity<Boolean> responseEntity = this.restTemplate
				.exchange("http://API-GATEWAY/olx/user/validate/token", HttpMethod.GET, httpEntity, Boolean.class);
		return responseEntity.getBody();

	}

	public boolean fallbackIsTokenValidate(Throwable throwable) {
		System.out.print("Token Validate Service Fail : " + throwable);
		return false;
	}

	@Override
	@CircuitBreaker(name = "USER-DETAILS-CIRCUIT-BREAKER", fallbackMethod = "fallbackGetUserDetails")
	public UserDto getUserDetails(String authToken) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("auth-token", authToken);
		headers.set("Content-Type","application/json");
		HttpEntity<UserDto> httpEntity = new HttpEntity<>(null, headers);
		ResponseEntity<UserDto> responseEntity = this.restTemplate
				.exchange("http://API-GATEWAY/olx/user/details", HttpMethod.GET, httpEntity, UserDto.class);
		return responseEntity.getBody();

	}

	public UserDto fallbackGetUserDetails(Throwable throwable) {
		System.out.print("User Details Service Fail : " + throwable);
		return null;
	}

}

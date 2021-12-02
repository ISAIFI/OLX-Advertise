package com.olx.service;

import com.olx.dto.UserDto;

public interface LoginDelegate {

	public boolean isTokenValid(String authToken);
	public UserDto getUserDetails(String authToken);
}

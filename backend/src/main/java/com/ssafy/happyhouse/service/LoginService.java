package com.ssafy.happyhouse.service;

import com.ssafy.happyhouse.dto.UserDto;

public interface LoginService {

	public UserDto login(UserDto memberDto) throws Exception;
	public String getServerInfo();
	
}

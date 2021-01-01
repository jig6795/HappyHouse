package com.ssafy.happyhouse.service;


import java.util.List;

import com.ssafy.happyhouse.dto.UserDto;
import com.ssafy.happyhouse.dto.UserResultDto;

public interface UserService {
	public UserResultDto userRegister(UserDto userDto);
	public UserResultDto userUpdate(UserDto userDto);
	public UserResultDto userDelete(String userEmail);
	public List<UserDto> userList();
	public List<UserDto> userListSearchWord(String searchword);
	public UserDto userDetail(String userEmail);
	
}

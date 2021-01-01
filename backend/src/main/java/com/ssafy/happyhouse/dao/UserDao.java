package com.ssafy.happyhouse.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.dto.UserDto;



@Mapper
public interface UserDao {
	public int userRegister(UserDto userDto);
	public int userUpdate(UserDto userDto);
	public int userDelete(String userEmail);
	public List<UserDto> userList();
	public List<UserDto> userListSearchWord(String searchword);
	public UserDto userDetail(String userEmail);
}

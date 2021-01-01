package com.ssafy.happyhouse.mapper;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.dto.UserDto;


@Mapper
public interface MemberMapper {

	public UserDto login(UserDto userDto) throws SQLException;
	
}

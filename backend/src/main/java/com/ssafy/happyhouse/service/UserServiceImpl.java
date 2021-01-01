package com.ssafy.happyhouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.dao.UserDao;
import com.ssafy.happyhouse.dto.UserDto;
import com.ssafy.happyhouse.dto.UserResultDto;



@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserDao userDao;
	
	private static final int SUCCESS = 1;
	private static final int FAIL = -1;
	
	@Override
	public UserResultDto userRegister(UserDto userDto) {
//		System.out.println("suc22");
//		System.out.println(userDto.getUserEmail());
//		System.out.println(userDto.getUserPassword());
//		System.out.println(userDto.getUserName());
		UserResultDto userResultDto = new UserResultDto();
		if( userDao.userRegister(userDto) == 1 ) {
//			System.out.println("suc");
			userResultDto.setResult(SUCCESS);
		}else {
			userResultDto.setResult(FAIL);
//			System.out.println("fail");
		}
		return userResultDto;
	}

	@Override
	public UserResultDto userUpdate(UserDto userDto) {
		UserResultDto userResultDto = new UserResultDto();
//		System.out.println("service");
//		System.out.println(userDto.getUserEmail());
//		System.out.println(userDto.getUserPassword());
		
		userDao.userUpdate(userDto);

		userResultDto.setResult(SUCCESS);

		
		return userResultDto;
	}

	@Override
	public UserResultDto userDelete(String userEmail) {
		UserResultDto userResultDto = new UserResultDto();
		userDao.userDelete(userEmail);
		userResultDto.setResult(SUCCESS);
		return userResultDto;
	}

	@Override
	public List<UserDto> userList() {
		return userDao.userList();
	}

	@Override
	public UserDto userDetail(String userEmail) {
		
		return userDao.userDetail(userEmail);
	}

	@Override
	public List<UserDto> userListSearchWord(String searchword) {
		return userDao.userListSearchWord(searchword);
	}


	
}

package com.ssafy.happyhouse.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.happyhouse.dto.UserDto;
import com.ssafy.happyhouse.dto.UserResultDto;
import com.ssafy.happyhouse.service.UserService;



@RestController
public class UserController {

	@Autowired
	UserService userService;

	private static final int SUCCESS = 1;
	private static final int FAIL = -1;
	


	@RequestMapping(value = "/register", method = RequestMethod.POST, headers = { "Content-type=application/json" })
	public  ResponseEntity<UserResultDto> userRegister(@RequestBody UserDto dto) {
//		System.out.println(dto.getUserEmail());

		UserResultDto userResultDto = userService.userRegister(dto);

		if (userResultDto.getResult() == SUCCESS) {
//			System.out.println("succc");
			return new ResponseEntity<UserResultDto>(userResultDto, HttpStatus.OK);
		} else {
//			System.out.println("failll");
			return new ResponseEntity<UserResultDto>(userResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST, headers = { "Content-type=application/json" })
	private ResponseEntity<UserResultDto> userUpdate(@RequestBody UserDto userDto) {
//		System.out.println("updatecontroller");
//		System.out.println(userDto.getUserEmail());
//		System.out.println(userDto.getUserPassword());

		UserResultDto userResultDto = userService.userUpdate(userDto);

		if (userResultDto.getResult() == SUCCESS) {
			return new ResponseEntity<UserResultDto>(userResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<UserResultDto>(userResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/delete/{userEmail}", method = RequestMethod.DELETE, headers = { "Content-type=application/json" })
	public ResponseEntity<UserResultDto> userDelete(@PathVariable("userEmail") String userEmail) {
//		System.out.println(userEmail);
		UserResultDto userResultDto = userService.userDelete(userEmail);
		
		if (userResultDto.getResult() == SUCCESS) {
			return new ResponseEntity<UserResultDto>(userResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<UserResultDto>(userResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
//
//	@GetMapping(value="/searchuser")
//	public List<UserDto> searchuser(UserParamDto userParamDto){
//		List<UserDto> List = userService.userList(userParamDto.getLimit(), userParamDto.getOffset());
//		return List;
//	}
	
	@RequestMapping(value = "/searchuser", method = RequestMethod.GET, headers = { "Content-type=application/json" })
	public ResponseEntity<List<UserDto>>  userList() {
		List<UserDto> List = userService.userList();
		
		if (List != null) {
//			System.out.println(List.get(0).getUserManager());
			return new ResponseEntity<List<UserDto>>(List, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<UserDto>>(List, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@RequestMapping(value = "/searchuserword/{searchword}", method = RequestMethod.GET, headers = { "Content-type=application/json" })
	public ResponseEntity<List<UserDto>>  userListSearchWord(@PathVariable("searchword") String searchword) {
//		System.out.println("Searchuserword controller");
		List<UserDto> List = userService.userListSearchWord(searchword);
		
		if (List != null) {
//			System.out.println(List.get(0).getUserManager());
			return new ResponseEntity<List<UserDto>>(List, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<UserDto>>(List, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value="/detail/{userEmail}")
	private UserDto userDetail(@PathVariable String userEmail){
//		System.out.println(userEmail+" 상세조회 ");

		return userService.userDetail(userEmail);
	}
	



	
	

}

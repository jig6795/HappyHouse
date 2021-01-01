package com.ssafy.happyhouse.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ssafy.happyhouse.dto.UserDto;
import com.ssafy.happyhouse.service.JwtService;
import com.ssafy.happyhouse.service.LoginService;


@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/user")
public class LoginController {

	@Autowired
	private JwtService jwtService;

	@Autowired
	private LoginService loginService;
	
	public static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@PostMapping("/confirm/login")
	public ResponseEntity<Map<String, Object>> login(@RequestBody UserDto userDto, HttpServletResponse response, HttpSession session) {
		Map<String, Object> resultMap = new HashMap<>();
//		System.out.println(userDto.getUserEmail());
//		System.out.println(userDto.getUserPassword());
		HttpStatus status = null;
		try {
			UserDto loginUser = loginService.login(userDto);
//			System.out.println(loginUser.getUserEmail());
//			System.out.println(loginUser.getUserRegisterDate());
//			System.out.println(loginUser.getUserManager());
			if(loginUser != null) {
//				jwt.io에서 확인
//				로그인 성공했다면 토큰을 생성한다.
				String token = jwtService.create(loginUser);
				logger.trace("로그인 토큰정보 : {}", token);
				
//				토큰 정보는 response의 헤더로 보내고 나머지는 Map에 담는다.
//				response.setHeader("auth-token", token);
				resultMap.put("auth-token", token);
				resultMap.put("user-Email", loginUser.getUserEmail());
				resultMap.put("user-Name", loginUser.getUserName());
				resultMap.put("user-Manager", loginUser.getUserManager());
//				resultMap.put("status", true);
//				resultMap.put("data", loginUser);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", "로그인 실패");
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			logger.error("로그인 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

//	@PostMapping("/info")
//	public ResponseEntity<Map<String, Object>> getInfo(HttpServletRequest req, HttpSession session) {
//		Map<String, Object> resultMap = new HashMap<>();
//		HttpStatus status = null;
//		try {
//			MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
//			if(memberDto != null) {
//				// 보너스로 토큰에 담긴 정보도 전달해보자. 서버에서 토큰을 사용하는 방법임을 알 수 있다.
//				resultMap.putAll(jwtService.get(req.getHeader("auth-token")));
//	
//				resultMap.put("status", true);
//				resultMap.put("info", info);
//				resultMap.put("request_body", memberDto);
//				status = HttpStatus.ACCEPTED;
//			}
//		} catch (RuntimeException e) {
//			logger.error("정보조회 실패 : {}", e);
//			resultMap.put("message", e.getMessage());
//			status = HttpStatus.INTERNAL_SERVER_ERROR;
//		}
//		return new ResponseEntity<Map<String, Object>>(resultMap, status);
//	}
	
	@GetMapping("/info")
	public ResponseEntity<Map<String, Object>> getInfo(HttpServletRequest req) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
//		System.out.println(">>>>>> " + jwtService.get(req.getHeader("auth-token")));
		try {
			// 사용자에게 전달할 정보이다.
//			String info = memberService.getServerInfo();
			
			resultMap.putAll(jwtService.get(req.getHeader("auth-token")));
//
//			resultMap.put("status", true);
//			resultMap.put("info", info);
			status = HttpStatus.ACCEPTED;
		} catch (RuntimeException e) {
			logger.error("정보조회 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

}

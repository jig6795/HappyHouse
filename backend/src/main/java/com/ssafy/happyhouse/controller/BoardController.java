package com.ssafy.happyhouse.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ssafy.happyhouse.dto.BoardDto;
import com.ssafy.happyhouse.dto.BoardParamDto;
import com.ssafy.happyhouse.dto.BoardResultDto;
import com.ssafy.happyhouse.dto.UserDto;
import com.ssafy.happyhouse.service.BoardService;

@RestController
public class BoardController {

	@Autowired
	BoardService service;
	
	private static final int SUCCESS = 1;
	private static final int FAIL = -1;
	
	
//	@GetMapping(value="boardstart")
//	public String board() {
//		System.out.println("board호출");
//		return "login";
//	}
	
//	@GetMapping(value="boardMain")
//	public String board() {
//		System.out.println("board호출");
//		return "boardMain";
//	}
	
	@GetMapping(value="/boards/{searchWord}")
	private ResponseEntity<BoardResultDto> boardList(BoardParamDto boardParamDto, @PathVariable String searchWord){
//		System.out.println(searchWord);
		BoardResultDto boardResultDto;
		boardParamDto.setLimit(10);
		boardParamDto.setOffset(0);
		boardParamDto.setSearchWord(searchWord);
		boardResultDto = service.boardList(boardParamDto);

		//String s = null; s.length();
		if( boardParamDto.getSearchWord().equals("total") ) {
//			System.out.println("No Search Word Function");
			boardResultDto = service.boardList(boardParamDto);
		}else {
//			System.out.println("Search Word Function");
			boardResultDto = service.boardListSearchWord(boardParamDto);
		}
		
		if( boardResultDto.getResult() == SUCCESS ) {
			return new ResponseEntity<BoardResultDto>(boardResultDto, HttpStatus.OK);
		}else {
			return new ResponseEntity<BoardResultDto>(boardResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value="/boards/count")
	private ResponseEntity<Integer> boardListCount(BoardParamDto boardParamDto){
		System.out.println("count!");
		if( boardParamDto.getSearchWord().isEmpty() ) {
			return new ResponseEntity<Integer>( service.boardListTotalCount(), HttpStatus.OK);
		}else {
			return new ResponseEntity<Integer>( service.boardListSearchWordTotalCount(boardParamDto), HttpStatus.OK);
		}
		 
	}
	
	@GetMapping(value="/boardsDetail/{boardId}")
	private ResponseEntity<BoardResultDto> boardDetail(@PathVariable int boardId, HttpSession session){
//		System.out.println("Detail");
		BoardParamDto boardParamDto = new BoardParamDto();
		boardParamDto.setBoardId(boardId);
		// session을 사용하지 않기 때문에 Session을 통해 Userinfo를 가져오는 것은 불가능!
		// UserSeq는 모두 1로 설정되어 있기 때문에 직접 1로 전달
		boardParamDto.setUserSeq(1);
//		boardParamDto.setUserSeq(((UserDto) session.getAttribute("userinfo")).getUserSeq());
		BoardResultDto boardResultDto = service.boardDetail(boardParamDto);

		if( boardResultDto.getResult() == SUCCESS ) {
			return new ResponseEntity<BoardResultDto>(boardResultDto, HttpStatus.OK);
		}else {
			return new ResponseEntity<BoardResultDto>(boardResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}		 
	}
//	
//	@PostMapping(value="/boards")
//	private ResponseEntity<BoardResultDto> boardInsert(
//			BoardDto boardDto, 
//			@RequestParam(value="file", required = false) MultipartFile file) {
//		
//		BoardResultDto boardResultDto = service.boardInsert(boardDto, file);
//		
//		if( boardResultDto.getResult() == SUCCESS ) {
//			return new ResponseEntity<BoardResultDto>(boardResultDto, HttpStatus.OK);
//		}else {
//			return new ResponseEntity<BoardResultDto>(boardResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
//		}		 
//	}
	
	// PUT + multipart/form-data (X)
	// In RESTful,
	// PUT & DELETE methods are defined to be idempotent
	// 만약 글만 수정한다면?? PUT mapping OK  여러 번 수정해도 back-end 결과가 같음
	// 첨부파일이 포함되면 idempotent 하지 않음.
	
//	@PostMapping(value="/boards/{boardId}") 
//	private ResponseEntity<BoardResultDto> boardUpdate(
//			BoardDto boardDto, 
//			@RequestParam(value="file", required = false) MultipartFile file){
//
//		BoardResultDto boardResultDto = service.boardUpdate(boardDto, file);
//		
//		if( boardResultDto.getResult() == SUCCESS ) {
//			return new ResponseEntity<BoardResultDto>(boardResultDto, HttpStatus.OK);
//		}else {
//			return new ResponseEntity<BoardResultDto>(boardResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
//		}		
//	}
//	
	@DeleteMapping(value="/boards/{boardId}") 
	private ResponseEntity<BoardResultDto> boardDelete(@PathVariable(value="boardId") int boardId){
		BoardResultDto boardResultDto = service.boardDelete(boardId);
		
		if( boardResultDto.getResult() == SUCCESS ) {
			return new ResponseEntity<BoardResultDto>(boardResultDto, HttpStatus.OK);
		}else {
			return new ResponseEntity<BoardResultDto>(boardResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}		 
	}

//	@ExceptionHandler(Exception.class)
//	public String handleErrorView(HttpServletRequest req, Exception ex) {
//		ModelAndView mav = new ModelAndView();
//		mav.addObject("exception", ex);
//		mav.addObject("url", req.getRequestURL());
//		mav.setViewName("error");
//		return "error";
//	}
//	
//	@ExceptionHandler(Exception.class)
//	public String handleErrorView() {
//		return "error";
//	}
//	
//	@ExceptionHandler({ SQLException.class, IOException.class })
//	public String handleErrorViewSQL() {
//		return "error";
//	}
	
//	@ExceptionHandler(Exception.class)
//	public ResponseEntity<BoardResultDto> handleErrorResponseEntity() {
//		BoardResultDto boardResultDto = new BoardResultDto();
//		boardResultDto.setResult(FAIL);
//		return new ResponseEntity<BoardResultDto>(boardResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
//	}
}

package com.ssafy.happyhouse.service;

import org.springframework.web.multipart.MultipartFile;

import com.ssafy.happyhouse.dto.BoardDto;
import com.ssafy.happyhouse.dto.BoardParamDto;
import com.ssafy.happyhouse.dto.BoardResultDto;

public interface BoardService {
	
	public BoardResultDto boardDetail(BoardParamDto boardParamDto);
//	
	public BoardResultDto boardDelete(int boardId);
//	
//	public BoardResultDto boardUpdate(BoardDto dto, MultipartFile file);
//	
//	public BoardResultDto boardInsert(BoardDto dto, MultipartFile file);
//	
	public BoardResultDto boardList(BoardParamDto boardParamDto);
	public int boardListTotalCount();	
	public BoardResultDto boardListSearchWord(BoardParamDto boardParamDto);
	public int boardListSearchWordTotalCount(BoardParamDto boardParamDto);
	
	// Changes for FileUpload
}

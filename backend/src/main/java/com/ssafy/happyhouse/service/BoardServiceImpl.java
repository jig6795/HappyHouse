package com.ssafy.happyhouse.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.happyhouse.dao.BoardDao;
import com.ssafy.happyhouse.dto.BoardDto;
import com.ssafy.happyhouse.dto.BoardFileDto;
import com.ssafy.happyhouse.dto.BoardParamDto;
import com.ssafy.happyhouse.dto.BoardResultDto;

	@Service

	public class BoardServiceImpl implements BoardService {
	
		@Autowired
		BoardDao dao;
//		String uploadFolder = "upload";
	
	/* for production code */
//	String uploadPath = getServletContext().getRealPath("/") + File.separator + uploadFolder;
//	System.out.println("BoardServlet : boardInsert : uploadPath " + uploadPath);
//	//C:\EDU_HOME\java\eclipse\workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\BoardWebFileUpload\\upload
	
	/* for eclipse development code */
	//D:\SSAFY\java\eclipse\ssafy4_sts3\BoardFileUploadSpringMVCMybatis
//	String uploadPath = "D:" + File.separator + "SSAFY" + File.separator + "java" + File.separator + "eclipse" + File.separator + 
//			"ssafy4_sts3" + File.separator + "BoardFileUploadSpringMVCMybatis" +  File.separator + "src" + File.separator + "main" + 
//			File.separator + "webapp" + File.separator + "resources" + File.separator + "static";
	
	
	private static final int SUCCESS = 1;
	private static final int FAIL = -1;

//	@Override
//	public BoardResultDto boardInsert(BoardDto dto, MultipartFile uploadFile) {
//		
//		BoardResultDto boardResultDto = new BoardResultDto();
//		
//		try {
//			dao.boardInsert(dto); // useGeneratedKeys="true" keyProperty="boardId"
//			
//			if( uploadFile != null && ! uploadFile.isEmpty() ) {
//				int boardId = dto.getBoardId();
//
//				File uploadDir = new File(uploadPath + File.separator + uploadFolder);
//				if (!uploadDir.exists()) uploadDir.mkdir();
//
//				String fileName = uploadFile.getOriginalFilename();
//		    
//			    //Random File Id
//				UUID uuid = UUID.randomUUID();
//			
//				//file extension
//				String extension = FilenameUtils.getExtension(fileName); // vs FilenameUtils.getBaseName()
//			
//				String savingFileName = uuid + "." + extension;
//			
//				File destFile = new File(uploadPath + File.separator + uploadFolder + File.separator + savingFileName);
//				
//				System.out.println(uploadPath + File.separator + uploadFolder + File.separator + savingFileName);
//				uploadFile.transferTo(destFile);
//		    
//			    // Table Insert
//			    BoardFileDto boardFileDto = new BoardFileDto();
//			    boardFileDto.setBoardId(boardId);
//			    boardFileDto.setFileName(fileName);
//			    boardFileDto.setFileSize(uploadFile.getSize());
//				boardFileDto.setFileContentType(uploadFile.getContentType());
//				String boardFileUrl = uploadFolder + "/" + savingFileName;
//				boardFileDto.setFileUrl(boardFileUrl);
//				
//				dao.boardFileInsert(boardFileDto);
//			}
//			
//			boardResultDto.setResult(SUCCESS);
//			
//		}catch(IOException e) {
//			e.printStackTrace();
//			boardResultDto.setResult(FAIL);
//		}
//		return boardResultDto;
//	}
//
//	@Override
//	public BoardResultDto boardUpdate(BoardDto dto, MultipartFile uploadFile){
//		
//		BoardResultDto boardResultDto = new BoardResultDto();
//
//		try {
//			dao.boardUpdate(dto);
//			
//			if( uploadFile != null && ! uploadFile.isEmpty() ) {
//										
//				File uploadDir = new File(uploadPath + File.separator + uploadFolder);
//				if (!uploadDir.exists()) uploadDir.mkdir();
//				
//				// 물리 파일 삭제, 첨부파일 여러개 고려
//		    	List<String> fileUrlList = dao.boardFileUrlDeleteList(dto.getBoardId());	
//				for(String fileUrl : fileUrlList) {	
//					File file = new File(uploadPath + File.separator, fileUrl);
//					if(file.exists()) {
//						file.delete();
//					}
//				}
//
//		    	dao.boardFileDelete(dto.getBoardId()); // 테이블 파일 삭제
//				
//		    	String fileName = uploadFile.getOriginalFilename();
//	    
//			    //Random File Id
//				UUID uuid = UUID.randomUUID();
//			
//				//file extension
//				String extension = FilenameUtils.getExtension(fileName); // vs FilenameUtils.getBaseName()
//			
//				String savingFileName = uuid + "." + extension;
//
//				File destFile = new File(uploadPath + File.separator + uploadFolder + File.separator + savingFileName);
//				System.out.println(uploadPath + File.separator + uploadFolder + File.separator + savingFileName);
//				uploadFile.transferTo(destFile);
//		    
//			    // Table Insert
//			    BoardFileDto boardFileDto = new BoardFileDto();
//			    boardFileDto.setBoardId(dto.getBoardId());
//			    boardFileDto.setFileName(fileName);
//			    boardFileDto.setFileSize(uploadFile.getSize());
//				boardFileDto.setFileContentType(uploadFile.getContentType());
//				String boardFileUrl = uploadFolder + "/" + savingFileName;
//				boardFileDto.setFileUrl(boardFileUrl);
//				
//				dao.boardFileInsert(boardFileDto);
//			}
//			boardResultDto.setResult(SUCCESS);
//			
//		}catch(Exception e) {
//			e.printStackTrace();
//			boardResultDto.setResult(FAIL);
//		}
//		
//		return boardResultDto;
//	}
//
	@Override
	public BoardResultDto boardDelete(int boardId) {
		
		BoardResultDto boardResultDto = new BoardResultDto();
		
		try {
//			List<String> fileUrlList = dao.boardFileUrlDeleteList(boardId);
//			for(String fileUrl : fileUrlList) {
//				File file = new File(uploadPath + File.separator, fileUrl);				
//				if(file.exists()) {
//					file.delete();
//				}
//			}
//			dao.boardFileDelete(boardId);
			dao.boardReadCountDelete(boardId);
			dao.boardDelete(boardId);		
			boardResultDto.setResult(SUCCESS);
		}catch(Exception e) {
			e.printStackTrace();
			boardResultDto.setResult(FAIL);
		}
		
		return boardResultDto;
	}

	@Override
	public BoardResultDto boardDetail(BoardParamDto boardParamDto) {
		
		BoardResultDto boardResultDto = new BoardResultDto();
		
		try {
			int userReadCnt = dao.boardUserReadCount(boardParamDto);
			if( userReadCnt == 0 ) {
				dao.boardUserReadInsert(boardParamDto.getBoardId(), boardParamDto.getUserSeq());
				dao.boardReadCountUpdate(boardParamDto.getBoardId());
			}
			
			BoardDto dto = dao.boardDetail(boardParamDto);
//			List<BoardFileDto> fileList = dao.boardDetailFileList(dto.getBoardId());

//			dto.setFileList(fileList);
			boardResultDto.setDto(dto);
			
			boardResultDto.setResult(SUCCESS);
			
		}catch(Exception e) {
			e.printStackTrace();
			boardResultDto.setResult(FAIL);
		}
		
		return boardResultDto;
	}

	@Override
	public BoardResultDto boardList(BoardParamDto boardParamDto) {
		
		BoardResultDto boardResultDto = new BoardResultDto();
		try {
			List<BoardDto> list = dao.boardList(boardParamDto);
			int count = dao.boardListTotalCount();			
//			System.out.println("리스트 가져옴");
//			System.out.println(list.size());
			boardResultDto.setList(list);
			boardResultDto.setCount(count);
			boardResultDto.setResult(SUCCESS);
		}catch(Exception e) {
			e.printStackTrace();
			boardResultDto.setResult(FAIL);
		}
		return boardResultDto;
	}

	@Override
	public int boardListTotalCount() {
		return dao.boardListTotalCount();
	}

	@Override
	public BoardResultDto boardListSearchWord(BoardParamDto boardParamDto) {
		
		BoardResultDto boardResultDto = new BoardResultDto();
		
		try {
			List<BoardDto> list = dao.boardListSearchWord(boardParamDto);
			int count = dao.boardListSearchWordTotalCount(boardParamDto);
			
			boardResultDto.setList(list);
			boardResultDto.setCount(count);
			
			boardResultDto.setResult(SUCCESS);
		
		}catch(Exception e) {
			e.printStackTrace();
			boardResultDto.setResult(FAIL);
		}
		
		return boardResultDto;
	}

	@Override
	public int boardListSearchWordTotalCount(BoardParamDto boardParamDto) {
		return dao.boardListSearchWordTotalCount(boardParamDto);
	}
}
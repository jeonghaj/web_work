package com.example.boot14.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.example.boot14.dto.CafeCommentDto;
import com.example.boot14.dto.CafeDto;
import com.example.boot14.repository.CafeCommentDao;
import com.example.boot14.repository.CafeDao;

@Service
public class CafeServiceImpl implements CafeService{
	
	// 페이징 처리를 위한 필드값
	
	//한 페이지에 몇개씩 표시할 것인지
	final int PAGE_ROW_COUNT = 5;
	//하단 페이지를 몇개씩 표시할 것인지
	final int PAGE_DISPLAY_COUNT = 5;
	
	@Autowired private CafeDao cafeDao;
	@Autowired private CafeCommentDao cafeCommentDao;
	
	@Override
	public Map<String, Object> getList(CafeDto dto) {
		//CafeDto 로 부터 페이지 번호를 얻어낸다 
		int pageNum=dto.getPageNum();
		//보여줄 페이지의 시작 ROWNUM
		int startRowNum=1+(pageNum-1)*PAGE_ROW_COUNT;
		//보여줄 페이지의 끝 ROWNUM
		int endRowNum=pageNum*PAGE_ROW_COUNT;
		
		//하단 시작 페이지 번호 
		int startPageNum = 1 + ((pageNum-1)/PAGE_DISPLAY_COUNT)*PAGE_DISPLAY_COUNT;
		//하단 끝 페이지 번호
		int endPageNum=startPageNum+PAGE_DISPLAY_COUNT-1;
		
		//전체 글의 갯수
		int totalRow=cafeDao.getCount(dto);
		//전체 페이지의 갯수 구하기
		int totalPageCount=(int)Math.ceil(totalRow/(double)PAGE_ROW_COUNT);
		//끝 페이지 번호가 이미 전체 페이지 갯수보다 크게 계산되었다면 잘못된 값이다.
		if(endPageNum > totalPageCount){
			endPageNum=totalPageCount; //보정해 준다. 
		}
		//위에서 계산된 startRowNum 과 endRowNum 을 dto 담고
		dto.setStartRowNum(startRowNum);
		dto.setEndRowNum(endRowNum);
		//CafeDto 를 인자로 전달해서 글목록 얻어오기
		List<CafeDto> list=cafeDao.getList(dto);
		
		//글목록과 페이징 처리에 관련된 정보를 Map 에 담아서 리턴해 준다. 
		return Map.of("list", list,
				"startPageNum", startPageNum,
				"endPageNum", endPageNum,
				"totalPageCount", totalPageCount,
				"pageNum", pageNum,
				"totalRow", totalRow);
	}

	@Override
	public void saveContent(CafeDto dto) {
		//글작성자는 spring security 에서 1회성 인증을 받은 userName 이다
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		//글작성자를 dto 에 담는다
		dto.setWriter(userName);
		//DB 에 저장
		cafeDao.insert(dto);
	}

	@Override
	public Map<String, Object> getDetail(CafeDto dto) {
		//글번호를 이용해서 글 하나의 정보를 얻어와서
		CafeDto resultDto = cafeDao.getDetail(dto);
		//원래의 검색 조건을 글 정보가 들어있는 결과 dto 에 추가해준다.
		resultDto.setCondition(dto.getCondition());
		resultDto.setKeyword(dto.getKeyword());
		
		// Map.of("key값", value값)
		return Map.of("dto", resultDto);
	}

	@Override
	public void deleteContent(int num) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getData(Model model, int num) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateContent(CafeDto dto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CafeCommentDto saveComment(CafeCommentDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> getCommentList(CafeCommentDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteComment(int num) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateComment(CafeCommentDto dto) {
		// TODO Auto-generated method stub
		
	}

	
}

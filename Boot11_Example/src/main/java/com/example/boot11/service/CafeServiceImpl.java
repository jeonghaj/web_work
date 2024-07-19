package com.example.boot11.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.example.boot11.dto.CafeCommentDto;
import com.example.boot11.dto.CafeDto;
import com.example.boot11.exception.NotOwnerException;
import com.example.boot11.repository.CafeCommentDao;
import com.example.boot11.repository.CafeDao;

@Service
public class CafeServiceImpl implements CafeService{
	
	//한 페이지에 글을 몇개씩 표시할 것인지
	final int PAGE_ROW_COUNT=5;
	//하단 페이지 UI를 몇개씩 표시할 것인지
	final int PAGE_DISPLAY_COUNT=5;
	
	//DI
	@Autowired private CafeDao cafeDao;
	@Autowired private CafeCommentDao commentDao;
	
	@Override
	public void getList(Model model, CafeDto dto) {
		// pageNum 에 해당하는 글정보를 select 에서 Model 객체에 담는 작업을 하면 된다.
		
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
		
		// view page 에 전달할 내용을 Model 객체에 담는다. 
		model.addAttribute("list", list);
		model.addAttribute("startPageNum", startPageNum);
		model.addAttribute("endPageNum", endPageNum);
		model.addAttribute("totalPageCount", totalPageCount);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("dto", dto); //키워드정보가 들어 있는 dto 를 모델에 담기 
		model.addAttribute("totalRow", totalRow);
	}

	@Override
	public void saveContent(CafeDto dto) {
		//로그인된 userName 을 읽어와서
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		//dto 에 담고
		dto.setWriter(userName);
		//cafeDao 를 이용해서 DB 에 저장
		cafeDao.insert(dto);
	}

	@Override
	public void getDetail(Model model, CafeDto dto) {
		//이전글의 글번호 다음글의 글번호도 받아 올수 있도록 getDetail() 메소드를 호출한다.
		CafeDto resultDto=cafeDao.getDetail(dto);
		//원래의 검색 조건을 글정보가 들어 있는 결과 dto 에 추가해준다. 
		resultDto.setCondition(dto.getCondition());
		resultDto.setKeyword(dto.getKeyword());
		
		//댓글 목록을 얻어온다 
		//CafeDto 에 ref_group 번호를 담아서 dao 에 전달해서 댓글 목록을 얻어낸다
		CafeCommentDto commentDto=new CafeCommentDto();
		//원글의 글번호를 담아서 
		commentDto.setRef_group(dto.getNum());
		//댓글의 페이지 번호
		int pageNum=1;
		/*
			[ 댓글 페이징 처리에 관련된 로직 ]
		*/
		//한 페이지에 댓글을 몇개씩 표시할 것인지
		final int PAGE_ROW_COUNT=10;
	
		//보여줄 페이지의 시작 ROWNUM
		int startRowNum=1+(pageNum-1)*PAGE_ROW_COUNT;
		//보여줄 페이지의 끝 ROWNUM
		int endRowNum=pageNum*PAGE_ROW_COUNT;
		//계산된 값을 dto 에 담는다
		commentDto.setStartRowNum(startRowNum);
		commentDto.setEndRowNum(endRowNum);
		
		//원글에 달린 댓글 목록 얻어내기 
		List<CafeCommentDto> commentList=commentDao.getList(commentDto);
		
		//원글의 글번호를 이용해서 댓글 전체의 갯수를 얻어낸다.
		int totalRow=commentDao.getCount(dto.getNum());
		//댓글 전체 페이지의 갯수
		int totalPageCount=(int)Math.ceil(totalRow/(double)PAGE_ROW_COUNT);
		
		//Model 객체에 template 페이지에서 필요한 정보를 담는다.
		model.addAttribute("dto", resultDto);
		model.addAttribute("commentList", commentList);
		model.addAttribute("totalPageCount", totalPageCount);
	}

	@Override
	public void deleteContent(int num) {
		//글 작성자와
		CafeDto dto = cafeDao.getData(num);
		String writer = dto.getWriter();
		//로그인된 사용자와 같은 경우에만 삭제
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		if(userName.equals(writer)) {
			cafeDao.delete(num);
		}else {
			//만일 일치하지 않으면 예외(NotOwnerException) 발생 시키기
			throw new NotOwnerException("금지된 요청입니다.");
		}
		
	
	}

	@Override
	public void getData(Model model, int num) {
		CafeDto dto = cafeDao.getData(num);
		model.addAttribute("dto",dto);
	}

	@Override
	public void updateContent(CafeDto dto) {
		String writer = cafeDao.getData(dto.getNum()).getWriter();
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		if(!writer.equals(userName)) {
			throw new NotOwnerException("금지된 요청입니다");
		}
		cafeDao.update(dto);
	}

	@Override
	public void saveComment(CafeCommentDto dto) {
		//댓글 작성자는 SpringSecurity 로 부터 얻어내기
		String writer = SecurityContextHolder.getContext().getAuthentication().getName();
		
		//글 번호를 미리 얻어낸다.
		int num = commentDao.getSequence();
		dto.setWriter(writer);
		dto.setNum(num);
		
		//만일 comment_group 번호가 넘어오지 않으면 원글의 댓글이다
		if(dto.getComment_group()==0) {
			//원글의 댓글인 경우 댓글의 번호(num) 가 곧 comment_group 번호가 된다
			dto.setComment_group(num);
		}
		//DB 에 저장하기 
		commentDao.insert(dto);
	}

	@Override
	public void deleteComment(int num) {
		
	}

	@Override
	public void updateComment(CafeCommentDto dto) {
		
	}

	@Override
	public void getCommentList(Model model, CafeCommentDto dto) {
		
	}

}

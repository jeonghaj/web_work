package com.example.boot11.dto;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Alias("cafeCommentDto")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CafeCommentDto {
	private int num;
	private String writer;
	private String content;
	private String target_id;
	private int ref_group;
	private int comment_group;
	private String deleted;
	private String refdate;
	//댓글 작성자의 profile 이미지를 출력하기 위한 필드 (user_info 테이블과 join 필요)
	private String profile;
	//페이징 처리를 위한 필드
	private int startRowNum;
	private int endRowNum;
	private int pageNum;
}

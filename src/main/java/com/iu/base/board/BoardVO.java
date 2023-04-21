package com.iu.base.board;

import java.sql.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BoardVO {
	private Long num;
	private String title;
	private String contents;
	private String writer;
	private Date date;
	private Long hit;
	
	private List<BoardFileVO> boardFileVOs;
	

}

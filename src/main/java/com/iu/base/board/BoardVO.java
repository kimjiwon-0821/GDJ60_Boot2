package com.iu.base.board;

import java.sql.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BoardVO {
	private Long num;
	@NotBlank
	@Size(min=3,max=20)
	private String title;
	@NotBlank
	private String contents;
	@NotBlank
	private String writer;
	private Date date;
	private Long hit;
	
	private List<BoardFileVO> boardFileVOs;

}

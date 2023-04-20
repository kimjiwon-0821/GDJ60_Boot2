package com.iu.base.util;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Pager {
	
	/* Paging */
	//page 번호 담을 변수
	private Long page;
	
	//한페이지에 보여줄 글의 갯수
	private Long perPage;
	
	private Long perBlock;
	
	//시작 index 번호
	private Long startRow;
	
	//시작 page 번호
	private Long startNum;
	//끝 page 번호
	private Long lastNum;
	
	//이전 블럭 유무
	private boolean pre; //false 이전이 없음, true 이전이 있음
	
	//다음 블럭 유무
	private boolean next; // false 다음이 없음 , true 다음이 있음
	
	
	//page 계산하는 메서드
	public void makeNum(Long totalCount ) {
		//1. 전체 글의 갯수를 받아옴 -> 매개변수로 받아옴
		
		//2. 전체 글의 갯수로 전체 페이지 갯수 구하기
		Long totalPage = totalCount/this.getPerPage();
		if(totalCount%this.getPerPage() != 0) {
			totalPage++;
		}
		
		//3. 전체 페이지로 전체 블럭의 갯수 구하기
		Long totalBlock = (totalPage / this.getPerBlock());
		if(totalPage %getPerBlock()!=0) {
			totalBlock++;
		}
		
		//4. 현재 page 번호로 현재 블럭 번호 구하기
		Long curBlock = this.getPage() / this.getPerBlock();
		if(this.getPage()% getPerBlock() !=0) {
			curBlock++;
		}
		
		//5. 현재 블럭 번호로 시작 번호와 끝번호 구하기
		this.startNum = ((curBlock-1)*this.getPerBlock())+1;
		this.lastNum = curBlock * this.getPerBlock();
		
		//6. 현재 블럭 번호가 마지막 블럭이라면 끝번호는 전체 페이지 번호와 같음
		if(curBlock == totalBlock) {
			lastNum = totalPage;
		}
		
		//7. 이전 블럭, 다음 블럭 가능한지 유무
		if(curBlock != 1) {
			this.pre = true;
		}
		if(curBlock != totalBlock) {
			this.next = true;
		}
		
	}
	
	//시작 index 번호를 계산하는 메서드
	public void makeStartRow () {
		//page = 1 , startRow=0
		//page = 2 , startRow=10
		//page = 3 , startRow=20
		this.startRow = (this.getPage()-1)*getPerPage();
	}
	
	public Long getPerBlock() {
		if(this.perBlock == null || this.perBlock < 1) {
			this.perBlock = 5L;
		}
		
		return perBlock;
	}
	
	public Long getPage() {
		if(this.page==null || this.page==0) {
			this.page=1L;
		}
		return this.page;
	}
	
	public Long getPerPage() {
		if(this.perPage==null || this.perPage==0) {
			this.perPage=10L;
		}
		return this.perPage;
	}
	
	
	
	/* Search */
	private String kind;
	private String search;
	
	public String getKind() {
		if(this.kind==null) {
			this.kind="";
		}
		return this.kind;
	}
	public String getSearch() {
		if(this.search==null) {
			this.search="";
		}
		return this.search;
	}

}

package com.iu.base.board.notice;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.iu.base.board.BoardDAO;
import com.iu.base.board.BoardVO;

@SpringBootTest
@Rollback(true)
class NoticeDAOTest {

	@Autowired
	private NoticeDAO noticeDAO;
	
	//@Test
	void setInsertTest() throws Exception{
		for(int i=0;i<120;i++) {
		BoardVO boardVO = new NoticeVO();
		
		boardVO.setWriter("kinnzzi"+i);
		boardVO.setTitle("insert test title ["+i+"]");
		boardVO.setContents("insert test contents ["+i+"]");
		
		int result = noticeDAO.setInsert(boardVO);
		if(i%10==0) {
			Thread.sleep(500);
		}
		}
		System.out.println("종료");
	}
	
//	@Test
//	void getDetail() throws Exception{
//		BoardVO boardVO = new NoticeVO();
//		boardVO.setNum(1L);
//		boardVO = noticeDAO.getDetail(boardVO);
//		
//		
//	}

}

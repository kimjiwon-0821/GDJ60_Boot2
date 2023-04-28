package com.iu.base.util;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.iu.base.board.notice.NoticeDAO;
import com.iu.base.board.notice.NoticeVO;
import com.iu.base.member.MemberDAO;
import com.iu.base.member.MemberVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TestSchedule {
	@Autowired
	private MemberDAO memberDAO;
	
	@Autowired
	private NoticeDAO noticeDAO;
	
	@Autowired
	private MailManager mailManager;
	
	//@Scheduled(cron = "30 * * * * *")
	public void Test() throws Exception {
		//log.error("=========반복중=======");
//		List<MemberVO> ar = memberDAO.getList();
//		for(MemberVO memberVO : ar) {
//			int lastTime= memberDAO.setScheduleCheck(memberVO);
//			if(lastTime>3) {
//				int result = memberDAO.setEnabled(memberVO);
//				log.error("=====lastTime======={}",result);
//			}
//			//log.error("===ID:{}",ar);
//			
			List<MemberVO> arr = memberDAO.getBirthday();
//			StringBuffer sb = new StringBuffer();
//			sb.append("오늘은 ");
//			for(MemberVO memberVO2 : arr) {
//				sb.append(memberVO2.getName());
//				sb.append(" ");
//			}
//			sb.append("님의 생일입니다!! 모두 축하해주세요!!");
//			NoticeVO noticeVO = new NoticeVO();
//			noticeVO.setTitle("생축");
//			noticeVO.setWriter("Admin");
//			noticeVO.setContents(sb.toString());
//			noticeDAO.setInsert(noticeVO);
//		}
			for(MemberVO memberVO:arr) {
				mailManager.send(memberVO.getEmail(), "생일 축하", "<h1>생일 축하</h1>");
			}
		
	}
	
	
}

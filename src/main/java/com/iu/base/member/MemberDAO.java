package com.iu.base.member;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper
public interface MemberDAO {
	
	public MemberVO getLogin(MemberVO memberVO) throws Exception;

	public int setJoin(MemberVO memberVO) throws Exception;
	
	public int setMemberRole(Map<String, Object> map) throws Exception;
	
	public MemberVO idDuplicateCheck(MemberVO memberVO) throws Exception;
	
	public List<MemberVO> getList () throws Exception;
	
	public int setLastTime (MemberVO memberVO) throws Exception;
	
	public int setEnabled (MemberVO memberVO) throws Exception;
	
	public int setScheduleCheck(MemberVO memberVO) throws Exception;
	
	public List<MemberVO> getBirthday() throws Exception;
}

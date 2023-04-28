package com.iu.base.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import com.iu.base.util.MailManager;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class MemberService implements UserDetailsService{
	
	@Autowired
	private MemberDAO memberDAO;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private MailManager mailManager;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		log.error("=========== Spring Security Login ============");
		log.error("=========== {} ===========",username);
		MemberVO memberVO = new MemberVO();
		memberVO.setUsername(username);
		try {
			memberVO = memberDAO.getLogin(memberVO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return memberVO;
	}

	//password가 일치하는지 검증하는 메소드
	public boolean memberCheck(MemberVO memberVO, BindingResult bindingResult) throws Exception{
		boolean result = false;
		//false : error가 없음, 검증 성공
		//true  : error가 있음. 검증 실패

		//1. annotation 검증 결과
		result = bindingResult.hasErrors();
		
		//2. password 일치 검증
		if(!memberVO.getPassword().equals(memberVO.getPasswordCheck())) {
			result = true;
			bindingResult.rejectValue("passwordCheck", "member-password-notEqual");
		}
	
		//3. id 중복 검사
		MemberVO checkMember = memberDAO.idDuplicateCheck(memberVO);
		if(checkMember !=null) {
			result=true;
			bindingResult.rejectValue("username", "member-id-duplicate");
		}
		return result;
	}
	
	public MemberVO getLogin(MemberVO memberVO) throws Exception{
		return memberDAO.getLogin(memberVO);
	}
	
	public int setJoin( MemberVO memberVO) throws Exception{
		//memberVO.setEnabled(true);
		memberVO.setPassword(passwordEncoder.encode(memberVO.getPassword()));
		int result = memberDAO.setJoin(memberVO);
		Map<String,Object> map = new HashMap<>();
		map.put("username",memberVO.getUsername());
		map.put("num",3);
		result = memberDAO.setMemberRole(map);
		return result;
	}
	
	public MemberVO idDuplicateCheck(MemberVO memberVO) throws Exception{
		return memberDAO.idDuplicateCheck(memberVO);
	}
	
	public int setLastTime(MemberVO memberVO) throws Exception{
		return memberDAO.setLastTime(memberVO);
	}
	
	public MemberVO getFindEmail(MemberVO memberVO) throws Exception{
		return memberDAO.getFindEmail(memberVO);
	}
	
	
}

package com.iu.base.member;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import lombok.val;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/member/*")
@Slf4j
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Value("${spring.security.oauth2.client.registration.kakao.client-secret}")
	private String adminKey;
	
	@GetMapping("delete")
	public String delete() throws Exception {
		MemberVO memberVO = (MemberVO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		//회원가입 방법 구분
		this.kakaoDelete(memberVO);
		
		return "redirect:./logout";
		
	}
	
	private void kakaoDelete(MemberVO memberVO) throws Exception{
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "KakaoAK "+adminKey);
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("target_id_type", "user_id");
		params.add("target_id", memberVO.getAttributes().get("id").toString());
		
		HttpEntity<MultiValueMap<String, String>> req = new HttpEntity<>(params, headers);
		String id = restTemplate.postForObject("https://kapi.kakao.com/v1/user/unlink", req, String.class);
		log.error("Delete ::: {}",id);
	}
	
	@GetMapping("info")
	public void info(HttpSession session) {
		String pw="member";
		MemberVO memberVO = (MemberVO) memberService.loadUserByUsername("member");
		log.error("========= {} ========",memberVO.getPassword());
		log.error("========= {} ========",passwordEncoder.encode(pw));
		log.error("========= {} ========", memberVO.getPassword().equals(passwordEncoder.encode(pw)));
		
		boolean check = passwordEncoder.matches(pw, memberVO.getPassword());
		log.error("======= {} =====",check);
		
		log.error("==============login info===========");
		//SPRING_SECURITY_CONTEXT
//		Enumeration<String> names = session.getAttributeNames();
//		while(names.hasMoreElements()) {
//			log.error("======== {} ========",names.nextElement());
//		}
		Object obj = session.getAttribute("SPRING_SECURITY_CONTEXT");
		SecurityContextImpl contextImpl = (SecurityContextImpl) obj;
		Authentication authentication = contextImpl.getAuthentication();
		
		log.error("=== {} ===",obj);
		log.error("=== UserName : {} ===",authentication.getName());
		log.error("=== Detail : {} ===",authentication.getDetails());
		log.error("=== MemberVO : {} ===",authentication.getPrincipal());
	}
	
	@GetMapping("mypage")
	public void getMyPage() throws Exception{}
	
	@GetMapping("admin")
	public void getAdminPage() throws Exception{}
	
	@GetMapping("login")
	public ModelAndView getLogin(HttpSession session) throws Exception{
		ModelAndView mv = new ModelAndView();
		Object obj = session.getAttribute("SPRING_SECURITY_CONTEXT");
		if(obj==null) {
			mv.setViewName("member/login"); //갈 jsp 주소
		}else {
			mv.setViewName("redirect:/");
		}
		return mv;
	}
//	@PostMapping("login")
//	public ModelAndView getLogin(MemberVO memberVO, HttpSession session) throws Exception{
//		ModelAndView mv = new ModelAndView();
//		memberVO = memberService.getLogin(memberVO);
//		mv.setViewName("redirect:./login/");
//		if(memberVO !=null) {
//			session.setAttribute("member", memberVO);
//			mv.setViewName("redirect:../");
//		}
//		
//		return mv;
//	}
//	
//	@GetMapping("socialLogout")
//	public ModelAndView getLogout(HttpSession session) throws Exception{
//		ModelAndView mv = new ModelAndView();
//
//		
//		mv.setViewName("redirect:../");
//		return mv;
//	}
	
	@GetMapping("join")
	public ModelAndView setJoin(@ModelAttribute MemberVO memberVO) throws Exception{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("member/join");
		return mv;
	}
	@PostMapping("join")
	public ModelAndView setJoin(@Valid MemberVO memberVO, BindingResult bindingResult) throws Exception{
		ModelAndView mv = new ModelAndView();
		
		boolean check = memberService.memberCheck(memberVO, bindingResult);
		if(check) {
			mv.setViewName("member/join");
			log.warn("검증 실패");
			return mv;
		}
		int result = memberService.setJoin(memberVO);
		mv.setViewName("redirect:../");
		return mv;
	}
	
	@GetMapping("idDuplicateCheck")
	@ResponseBody // 이 aunotation을 줘야 무슨 타입이든 return할 수 있음;
	public boolean idDuplicateCheck(MemberVO memberVO) throws Exception{
		
		log.debug("========ID 중복 체크==========");
		boolean check = true;
		memberVO = memberService.idDuplicateCheck(memberVO);
		if(memberVO !=null ) {
			check = false;
		}
		return check;
	}
	
	@GetMapping("findPassword")
	public ModelAndView getFindEmail() throws Exception{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("member/findPassword"); //갈 jsp 주소
		return mv;
	}
	@PostMapping("findPassword")
	public ModelAndView getFindEmail(MemberVO memberVO) throws Exception{
		ModelAndView mv = new ModelAndView();
		memberVO = memberService.getFindEmail(memberVO);
		if((memberVO.getEmail()).equals("wldnjsqkek29@naver.com")) {
			log.error("== {} ==",memberVO.getUsername());
		}
		return mv;
	}
	

}

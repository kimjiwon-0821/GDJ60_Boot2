package com.iu.base.member;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/member/*")
@Slf4j
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@GetMapping("mypage")
	public void getMyPage() throws Exception{}
	
	@GetMapping("admin")
	public void getAdminPage() throws Exception{}
	
	@GetMapping("login")
	public ModelAndView getLogin() throws Exception{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("member/login"); //갈 jsp 주소
		return mv;
	}
	@PostMapping("login")
	public ModelAndView getLogin(MemberVO memberVO, HttpSession session) throws Exception{
		ModelAndView mv = new ModelAndView();
		memberVO = memberService.getLogin(memberVO);
		mv.setViewName("redirect:./login/");
		if(memberVO !=null) {
			session.setAttribute("member", memberVO);
			mv.setViewName("redirect:../");
		}
		
		return mv;
	}
	
	@GetMapping("logout")
	public ModelAndView getLogout(HttpSession session) throws Exception{
		ModelAndView mv = new ModelAndView();
		session.invalidate();
		mv.setViewName("redirect:../");
		return mv;
	}
	
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
	
	
	
	

}

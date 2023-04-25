package com.iu.base.board.qna;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.iu.base.board.BoardFileVO;
import com.iu.base.board.BoardVO;
import com.iu.base.board.notice.NoticeVO;
import com.iu.base.util.Pager;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/qna/*")
public class QnaController {
	
	@Autowired
	private QnaService qnaService;
	
	@ModelAttribute("board")
	public String getBoard() {
		return "qna";
	}

	@GetMapping("list")
	public ModelAndView getList(Pager pager) throws Exception{
		ModelAndView mv = new ModelAndView();
		List<BoardVO> ar = qnaService.getList(pager);
		mv.addObject("list", ar);
		mv.setViewName("board/list");
		return mv;
	}
	
	@GetMapping("detail")
	public ModelAndView getDetail(QnaVO qnaVO) throws Exception{
		ModelAndView mv = new ModelAndView();
		BoardVO boardVO = qnaService.getDetail(qnaVO);
		mv.addObject("boardVO", boardVO);
		mv.setViewName("board/detail");
		return mv;
	}
	
	@GetMapping("fileDown")
	public ModelAndView getFileDown(BoardFileVO boardFileVO) throws Exception{
		boardFileVO = qnaService.getFileDetail(boardFileVO);
		ModelAndView mv = new ModelAndView();
		mv.addObject("boardFileVO",boardFileVO);
		mv.setViewName("fileManager");
		return mv;
	}
	
	//add
		@GetMapping("add")
		public ModelAndView setInsert(@ModelAttribute BoardVO boardVO) throws Exception{
			ModelAndView mv = new ModelAndView();
			mv.setViewName("board/add");
			return mv;
		}
		@PostMapping("add")
		public ModelAndView setInsert(@Valid BoardVO boardVO,BindingResult bindingResult ,MultipartFile[] boardFiles) throws Exception{
			ModelAndView mv = new ModelAndView();
			if(bindingResult.hasErrors()) {
				mv.setViewName("board/add");
				log.warn("검증 실패");
				return mv;
			}
			
			for(MultipartFile multipartFile: boardFiles) {
				log.info("OriginalName :{} Size: {}",multipartFile.getOriginalFilename(),multipartFile.getSize());
			}
			int result = qnaService.setInsert(boardVO,boardFiles);
			mv.setViewName("redirect:./list");
			return mv;
		}
	
}

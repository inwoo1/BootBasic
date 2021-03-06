package com.simple.basic.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.simple.basic.command.MemoVO;
import com.simple.basic.memo.MemoService;

@Controller
@RequestMapping("/memo")
public class MemoController {
	
	@Autowired
	@Qualifier("memoService")
	private MemoService memoService;
	
	//예제코드 ( 컨트롤러 - 서비스 - 매퍼 연결 )
	@GetMapping("/ex")
	public void ex(Model model) {
		
		String time = memoService.getTime();
		model.addAttribute("time", time);
	}
	
	@GetMapping("/memoWrite")
	public String memoWrite() {
		return "memo/memoWrite";
	}
	
	@PostMapping("/memoForm")
	public String memoForm(@Valid MemoVO vo,
						   Errors errors,
						   RedirectAttributes RA,
						   Model model) {
		
		if(errors.hasErrors()) {
			List<FieldError> list = errors.getFieldErrors();
			
			for(FieldError err : list) {
				
				model.addAttribute("valid_" + err.getField(), err.getDefaultMessage());//에러가뜨면 에러 메세지와 위치를 보냄
				
			}
			model.addAttribute("vo", vo);
			return "memo/memoWrite"; //실패시 작성화면으로
			
		}
		memoService.write(vo);
		RA.addFlashAttribute("list", vo);
		return "redirect:/memo/memoList"; //성공시 리스트화면으로
	}
	
	@GetMapping("/memoList")
	public String memoList(Model model) {
		
		ArrayList<MemoVO> list = memoService.getList();
		model.addAttribute("list", list);
		return "memo/memoList";
	}
}

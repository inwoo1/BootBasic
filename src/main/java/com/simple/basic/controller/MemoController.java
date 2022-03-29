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
						   RedirectAttributes RA,
						   Errors errors,
						   Model model) {
		
		if(errors.hasErrors()) {
			List<FieldError> list = errors.getFieldErrors();
			
			for(FieldError err : list) {
				
				if(err.isBindingFailure()) {
					
					model.addAttribute("valid_" + err.getField(), "숫자로 입력하세요");
				}else {
					model.addAttribute("valid_" + err.getField(), err.getDefaultMessage());
				}
			}
			
			model.addAttribute("vo", vo);
			return "memo/memoWrite";
			
			
		}
		memoService.write(vo);
		RA.addFlashAttribute("list", vo);
		return "redirect:/memo/memoList";
	}
	
	@GetMapping("/memoList")
	public String memoList(Model model) {
		
		ArrayList<MemoVO> list = memoService.getList();
		model.addAttribute("list", list);
		return "memo/memoList";
	}
}

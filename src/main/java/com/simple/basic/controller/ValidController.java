package com.simple.basic.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.simple.basic.command.ValidVO;

@Controller
@RequestMapping("/valid")
public class ValidController {
	
	@GetMapping("/view")
	public void view() {
		
	}
	
	@PostMapping("/viewForm")
	public String viewForm(@Valid ValidVO vo, Errors errors, Model model) {
		System.out.println(vo);
		if(errors.hasErrors()) { //유효성 검사에 실패한 결과가 있다면 true 아니면 false
			
		//유효성 검사에 실패한 필드목록 확인
		List<FieldError> list = errors.getFieldErrors();
		
		//반복문 회전
		for(FieldError err : list) {
			System.out.println(err.getField()); //유효성 검사에 실패한 변수명
			System.out.println(err.getDefaultMessage()); //유효성 검사에 실패했을때 만들어지는 변수의 에러메세지
			System.out.println(err.isBindingFailure()); //유효성 검사에 바인딩이 안된결과
			
			if(err.isBindingFailure()) {//유효성검사는 성공했으나, 자바측에서 에러가 난 경우(ex: Integer가 문자로 입력되었을때)
				
				model.addAttribute("valid_" + err.getField(), "숫자로 입력하세요");  //메세지값을 직접 지정
				
			}else {  //유효성 검사에 실패한경우 모델에 메세지를 담음
				
				model.addAttribute("valid_" + err.getField(), err.getDefaultMessage());
				
			}
		} //end for
		
		//화면에 입력데이터를 유지하기 위해 model에 저장
		model.addAttribute("vo", vo);
		return "valid/view";
			
	}// end if
			
			return "redirect:/valid/result"; //결과화면으로 리다이렉트
		
		
	}
	
	@GetMapping("result")
	public void result() {
		
	}

}

package com.simple.basic.command;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemoVO {
	
	private Integer mno;
	
	@NotBlank(message = "내용은 5자 이상입니다")
	private String memo;
	
	@Pattern(regexp = "[0-9]{3}-[0-9]{4}-[0-9]{4}", message = "전화번호는 000-0000-0000형식 입니다")
	private String phone;
	
	@Pattern(regexp = "^[A-Za-z0-9]{4}$", message = "비밀번호는 4글자 입니다")
	private String pw;
	
	private String secret;

}

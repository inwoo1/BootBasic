package com.simple.basic.command;

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
	
	@NotNull
	private String memo;
	
	@Pattern(regexp = "[0-9]{3}-[0-9]{4}-[0-9]{4}", message = "전화번호는 000-0000-0000형식 입니다")
	private Integer phone;
	
	@Pattern(regexp = "/^.{0,4}$/")
	private String pw;
	private String secret;

}

package com.simple.basic.command;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SimpleVO {
	
	//Spring boot에서는 vo를 표기할때 wrapper형으로 선언
	private Integer a;
	private String first;
	private String last;
	private LocalDateTime regdate; //날짜형
	

}

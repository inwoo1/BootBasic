package com.simple.basic.memo;



import java.util.ArrayList;

import com.simple.basic.command.MemoVO;

public interface MemoService {
	
	public String getTime();
	public void write(MemoVO vo);
	public ArrayList<MemoVO> getList();
}

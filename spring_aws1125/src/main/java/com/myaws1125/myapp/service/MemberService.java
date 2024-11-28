package com.myaws1125.myapp.service;

import com.myaws1125.myapp.domain.MemberVo;

public interface MemberService {

	public int memberInsert(MemberVo mv);
	public int memberIdCheck(String memberId); // 아이디 중복체크
}

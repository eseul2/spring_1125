package com.myaws1125.myapp.persistance;

import com.myaws1125.myapp.domain.MemberVo;



public interface MemberMapper {
	
	/* 마이바티스용 메서드 Service에 있는 메서드와 이름이 동일 */
	public int memberInsert(MemberVo mv);
	public int memberIdCheck(String memberId); // 아이디 중복체크
	public MemberVo memberLoginCheck(String memberId); // 로그인시 아이디 확인

}

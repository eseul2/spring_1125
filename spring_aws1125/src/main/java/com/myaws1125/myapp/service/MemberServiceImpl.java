package com.myaws1125.myapp.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myaws1125.myapp.domain.MemberVo;
import com.myaws1125.myapp.persistance.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {

	private MemberMapper mm;
	
	@Autowired /* ���Խ�Ų�� */  
	public MemberServiceImpl(SqlSession sqlSession) {
		this.mm = sqlSession.getMapper(MemberMapper.class); /* �� �ڿ� Ŭ������ �پ���Ѵ�. */ 
	}
	
	@Override
	public int memberInsert(MemberVo mv) {
		int value = mm.memberInsert(mv);
	
		return value;
	}

	@Override
	public int memberIdCheck(String memberId) {
		int value = mm.memberIdCheck(memberId);
		return value;
	}
	
	
	
	

}

package com.myaws1125.myapp.persistance;

import com.myaws1125.myapp.domain.MemberVo;



public interface MemberMapper {
	
	/* ���̹�Ƽ���� �޼��� Service�� �ִ� �޼���� �̸��� ���� */
	public int memberInsert(MemberVo mv);
	public int memberIdCheck(String memberId); // ���̵� �ߺ�üũ
	public MemberVo memberLoginCheck(String memberId); // �α��ν� ���̵� Ȯ��

}

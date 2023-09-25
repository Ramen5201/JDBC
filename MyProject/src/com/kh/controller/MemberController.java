package com.kh.controller;

import com.kh.model.service.MemberService;
import com.kh.view.MemberMenu;
import com.kh.model.vo.Member;

public class MemberController {
	
	public void insertMember(String userId, String userPwd) {
		
		Member m = new Member(userId, userPwd);
		
		int result = new MemberService().insertMember(m);
		
		if(result > 0) {
			new MemberMenu().displaySuccess("성공적으로 회원 추가 완료.");
		} else {
			new MemberMenu().displayFail("회원 추가 실패.");
		}
		
	}
	
	public void loginMember(String userId, String userPwd) {
		
		Member m = new MemberService().loginMember(m);
		
		
	}
}

package com.kh.view;

import java.util.Scanner;
import com.kh.controller.MemberController;
import com.kh.model.vo.Member;

//View : 사용자가 보게될 시각적인 요소(화면) 출력 및 입력
public class MemberMenu {
	private Scanner sc = new Scanner(System.in);
	private MemberController mc = new MemberController();
	
	public void mainMenu() {
		
		System.out.println("==== 로그인 ====");
		System.out.println("1. 회원가입");
		System.out.println("2. 로그인 :");
		System.out.println("4. 종료");
		
		System.out.println("메뉴 선택");
		int menu = sc.nextInt();
		
		switch(menu) {
			case 1 :
				inputMember();
				break;
			case 2 :
				loginMember();
			case 4 :
			
			default :
		}
		
		
		
	}
	
public void inputMember() {
		
		System.out.println("\n=== 회원 추가===");
		
		
		System.out.print("아이디 : ");
		String memberId = sc.nextLine();
		
		System.out.print("비밀번호 : ");
		String memberPwd = sc.nextLine();
		
		
		mc.insertMember(memberId, memberPwd);
}

public void loginMember() {
	System.out.println("=== 로그인 ===");
	
	System.out.println("아이디 : ");
	String memberId = sc.nextLine();
	
	System.out.println("비밀번호 : ");
	String memberPwd = sc.nextLine();
	
	mc.loginMember(memberId, memberPwd);
}



public void displaySuccess(String message) {
	System.out.println("\n서비스 요청 성공 : " + message);
}

public void displayFail(String message) {
	System.out.println("\n" + message);
}

public void displayLoginMember(Member m) {
	System.out.println("로그인에 성공하셨습니다.");
}
	
}

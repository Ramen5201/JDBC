package com.kh.model.view.music;

import java.util.Scanner;

public class MusicMenu {
	private Scanner sc = new Scanner(System.in);
	
	public void MusicMenu() {
		System.out.println("=== 노래 메뉴 ===");
		System.out.println("1. 노래추가");
		System.out.println("2. 노래선택");
		System.out.println("3. 노래삭제");
		System.out.println("9. 시스템종료");
		
		System.out.println("메뉴 선택");
		int menu = sc.nextInt();
		sc.nextLine();
		
		switch(menu) {
			case 1 :
				
			case 2 :
				
			case 3 :
				
			case 9 :

			default :
		}
	}
}

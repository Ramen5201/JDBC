package com.kh.review2;

import java.util.*;

//회원생성, 수정, 삭제
//도서 등록,수정,삭제
//및 도서대여관리
public class Library {
	// 책들을 저장한 공간
	ArrayList<Book> bookList;
	// 회원들을 저장할 공간
	ArrayList<Human> humanList;
	Scanner sc;

	public Library() {
		this.bookList = new ArrayList<>();
		this.bookList.add(new Book("간디", "최지원", 152));
		this.bookList.add(new Book("이순신", "김수민", 242));
		this.bookList.add(new Book("잔다르크", "이철수", 457));
		
		humanList = new ArrayList<>();
		humanList.add(new Human(1, "최지원", "910524", 42, 'F'));
		humanList.add(new Human(2, "최수민", "700224", 32, 'M'));
		humanList.add(new Human(3, "박지수", "200114", 11, 'F'));
		sc = new Scanner(System.in);
	}

	// 도서등록, 도서대여, 도서반납
	// 을 선택하는 메인화면 출력
	public void mainMenu() {
		int number = 0;
		while (number != 9) {
			System.out.println("============================");
			System.out.println("1. 도서등록");
			System.out.println("2. 도서대여");
			System.out.println("3. 도서반납");
			System.out.println("4. 도서삭제");
			System.out.println("5. 회원등록");
			System.out.println("9. 프로그램 종료");
			System.out.println("============================");
			System.out.println("원하시는 서비스 번호를 입력하세요 : ");

			// 원하는 서비스 번호 입력받기
			number = sc.nextInt();
			sc.nextLine();
			
			switch (number) {
				case 1:
					//도서등록
					printBookList(bookList);
					bookList.add(this.createBook());
					break;
				case 2:
					//도서대여
					rentBook();
					break;
				case 3:
					//도서반납
					returnBook();
					break;
				case 4:
					//도서삭제
					deleteBook();
					break;
				case 5:
					//회원등록
					printHumanList(humanList);
					humanList.add(this.createHuman());
					break;
				case 9:
					//프로그램 종료
					System.out.println("프로그램을 종료합니다.");
					break;
				default:
					System.out.println("잘못 입력하셨습니다.");
			}
		}
	}

	// 도서 대여를 위한 메서드
	public void rentBook() {
		//대여가능한 책이 있는지 검사
		boolean isBookCheck = false; // true이면 책이 하나라도 대여가능상태 false => 책을 하나도 빌릴 수 없는상태
		boolean isHumanCheck = false; // true라면 책을 빌릴 수 있는 사람이 한명이라도 있다. false => 책을 빌릴 수 있는 사람이 한명도 없다.
		
		for (Book b : bookList) {
			if (b.getIsRent()) { // isRent가 true인 book은 대여가 가능한 상태
				isBookCheck = true;
				break;
			}
		}
		
		// 북을 빌릴 수 있는 사람이 있는지 검사
		for (Human h : humanList) {
			if (h.getRentBookCode() == 0) { // rentBookCode가 0인 human은 대여가 가능한 상태
				isHumanCheck = true;
				break;
			}
		}
		
				
		if (isBookCheck == false) {//책 자체가 없거나 또는 빌릴 수 있는 책이 하나도 없다
			System.out.println("도서등록이 필요합니다.");
			return;
		} else if (!isHumanCheck) { //책을 빌릴 사람이 아얘 없거나 또는 책을 빌릴 수 있는 사람이 한명도 없다.
			System.out.println("회원등록이 필요합니다.");
			return;
		}
		
		
		// 대여하는 사람을 선택하는 코드
		Human selectHuman = selectHuman();
		
		// 대여할 book을 선택하는 코드
		Book selectBook = selectBook();
		
		//책을 대여해준다.
		// 책에는 isRent상태를 false로 변경
		// 사람은 대여책 코드를 등록시킨다.
		selectHuman.setRentBookCode(selectBook.getCode());
		selectBook.setIsRent(false);
		
	}
	
	//도서를 반납하기위한 메서드
	public void returnBook() {
		//책을 빌린사람들을 추린다.
		// humanList => 전체검사하면서 책을 대여한 사람만 tmpHumanList 추가 
		ArrayList<Human> tmpHumanList = new ArrayList<>();
		for (Human man : humanList) {
			if (man.getRentBookCode() != 0) {
				tmpHumanList.add(man);
			}
		}
		
		if (tmpHumanList.size() == 0) {
			System.out.println("반납할 책이 없습니다.");
			return;
		}
		//tmpHumanList담긴 사람들을 보여준다.
		printHumanList(tmpHumanList);
		
		Human selectHuman = null;
		while(selectHuman == null) {
			//리스트에 있는 사람중 어떤 사람의 책을 반납할지 id를 입력받는다.
			System.out.println("어떤 사람의 책을 반납하시겠습니까? (id입력) : ");
			
			int selectID = sc.nextInt();
			//해당 사람을 selectHuman이라는 변수를 만들어 담아준다.
			
			for (Human man : tmpHumanList) {
				if (man.getKey() == selectID) {
					selectHuman = man;
				}
			}
			
			if (selectHuman == null) {
				System.out.println("입력하신 id와 일치하는 회원이 없습니다.");
			}
		}
		
		//selectHuman // <-빌린사람, 반납할사람 -> 반납할 책의 코드를 가지고있겠네
		//해당 사람이 빌린 책을 rentBookCode를 이용해서 bookList에서 찾아준다.
		//해당 책을 selectBook이라는 변수를 만들어 담아준다.
		//selectHuman의 rentBookCode를 0으로 변경
		//selectBook의 isRent를 true로 변경
		//반납이 완료되었습니다. 출력
		
		for (Book book : bookList) {
			if (book.getCode() == selectHuman.getRentBookCode()) {
				book.setIsRent(true);
				selectHuman.setRentBookCode(0);
				System.out.println("반납이 완료되었습니다.");
				break;
			}
		}
		
	}
	
	public void deleteBook() {
		//도서목록을 보여준다.
		printBookList(bookList);
		//삭제할 도서코드를 입력받는다.
		System.out.println("삭제할 도서코드를 입력해주세요 : ");
		int deleteBookCode = sc.nextInt();
		//해당도서가 대여중이면 "대여중인 도서는 삭제가 불가합니다"하고 return;
		// booklist를 순회하며 입력학 book코드와 동일한 북코드를 가진 객체를 찾는다
		//반복은 for문을 이용하여 한다.
		for(int i = 0; i < bookList.size(); i++) {
			Book b = bookList.get(i);
			if(deleteBookCode == b.getCode()){
				if(!b.getIsRent()) {
					System.out.println("대여중인 도서는 삭제가 불가합니다.");
					return;
				} else {
					bookList.remove(i);
					System.out.println("도서가 삭제되었습니다.");
				}
			}
		}
		
		//사람목록을 보여준다.
		printHumanList(humanList);
		//삭제할 도서코드를 입력받는다.
		System.out.println("삭제할 사람을 입력해주세요 : ");
		int deleteHumanCode = sc.nextInt();
		//해당사람이 대여중이면 "대여중인 도서는 삭제가 불가합니다"하고 return;
		// humanList를 순회하며 입력학 rentBookCode코드와 동일한 북코드를 가진 객체를 찾는다
		//반복은 for문을 이용하여 한다.
		for(int i = 0; i < humanList.size(); i++) {
			Human h = humanList.get(i);
			if(deleteHumanCode == h.getRentBookCode()){
				if(h.getRentBookCode() != 0) {
					System.out.println("대여중인 도서는 삭제가 불가합니다.");
					return;
				} else {
					humanList.remove(i);
					System.out.println("도서가 삭제되었습니다.");
					}
				}
			}
		}
		//찾았으면 해당 book의 isrent상태를 확인한다.
		//상태에 따라서 list에서 remove해주거나 대여중으로 삭제할 수 없습니다.
		
		//해당도서가 대여중이 아니면 도서목록에서 해당도서 삭제
	public Human selectHuman() {
		Human selectHuman = null;
		while(selectHuman == null) { //selectHuman에 어떤 값이 들어올 때까지 반복이구나
			printHumanList(humanList);
			
			System.out.print("어떤 회원으로 대여하시겠습니까?(id입력) : ");
			int selectKey = sc.nextInt();
			sc.nextLine();
			
			for (Human human : humanList) {
				if (selectKey == human.getKey()) {
					if (human.getRentBookCode() != 0) {
						System.out.println("대여중인 책을 반납 후 이용 부탁드립니다.");
					} else {
						selectHuman = human;	
					}
				}
			}
		}
		return selectHuman;
	}
	
	// 대여할 book을 선택해서 반환해주는 메서드
	public Book selectBook() {
		Book selectBook = null;
		while(selectBook == null) {
			printBookList(bookList);
			System.out.println("어떤 책을 대여하시겠습니까?(도서코드입력) : ");
			
			int selectCode = sc.nextInt();
			sc.nextLine();
			
			for (int i = 0; i < bookList.size(); i++) {
				Book book = bookList.get(i);
				if (selectCode == book.getCode()) {
					if (!book.getIsRent()) {
						System.out.println("이미 대여중인 책입니다.");
					} else {
						selectBook = book;	
					}
				}
			}
		}
		
		return selectBook;
	}
	
	// bookList의 목록을 보여주는 메서드
	public void printBookList(ArrayList<Book> tmpList) {
		System.out.println("--------------------------------");
		if (tmpList.size() > 0) {
			System.out.println("번호 \t 제목 \t 작가 \t 대여여부");
			for (Book book : tmpList) {
				System.out.println(book.toString());
			}
		} else {
			System.out.println("등록된 도서 없음");
		}
		System.out.println("--------------------------------");
	}

	// humanList의 목록을 보여주는 메서드
	public void printHumanList(ArrayList<Human> tmpList) {
		System.out.println("--------------------------------");
		if (tmpList.size() > 0) {
			System.out.println("ID \t 이름 \t 생년월일 \t 나이 \t 성별 \t 도서대여현황");
			for (Human human : tmpList) {
				System.out.println(human.toString());
			}
		} else {
			System.out.println("등록된 회원 없음");
		}
		System.out.println("--------------------------------");
	}

	// 사용자에 입력에 따라 사람객체를 생성해서 반환한다.
	public Human createHuman() {
		// 입력받기위한 객체
		String name, residentNumber;
		int age, key;
		char gender;
		// 이름, 나이, 주민등록번호, 성별을 입력받아 사람객체 한개를 생성한다.

		System.out.print("이름을 입력하세요 : ");
		name = sc.nextLine();
		System.out.print("나이를 입력하세요 : ");
		age = sc.nextInt();
		sc.nextLine();
		System.out.print("주민등록번호 앞 6자리를 입력하세요. : ");
		residentNumber = sc.nextLine();
		System.out.print("성별을 입력해주세요.(남 : M, 여자는: F) : ");
		gender = sc.nextLine().toUpperCase().charAt(0);

		System.out.print("고객 코유코드를 입력하세요. : ");
		key = sc.nextInt();
		sc.nextLine();

		Human human = new Human(key, name, residentNumber, age, gender);
		System.out.println(human.toString() + " 생성완료");

		return human;
	}

	// 사용자에 입력에 따라 책객체를 생성해서 반환한다.
	public Book createBook() {
		// 입력받기위한 객체

		String title, author;
		int code;
		// 제목, 작가, 책코드를 입력받는다.

		System.out.print("책 제목을 입력하세요 : ");
		title = sc.nextLine();
		System.out.print("작가를 입력하세요 : ");
		author = sc.nextLine();
		System.out.print("책 코유코드를 입력하세요. : ");
		code = sc.nextInt();
		sc.nextLine();

		Book book = new Book(title, author, code);
		System.out.println(book.toString() + " 생성완료");

		return book;
	}
}
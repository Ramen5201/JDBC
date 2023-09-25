package com.kh.model.vo;



/*
 * VO(value object)
 * 한명의 회원(db테이블의 한 맿ㅇ의 데이터)에 대한 데이터를 기록할 수 있는 저장용 객체
 */
public class Member {
	// 필드
	// 필드는 기본적으로 DB컬럼명과 유사하게 작업
	private int userNo;
	private String userId;
	private String userPwd;
	private String email;
	private String address;

	// 생성자
	// 단축키 alt shit s o
	public Member() {
		super();
	}

	
	public Member(int userNo, String userId,  String userPwd, String email, String address) {
		super();
		this.userNo = userNo;
		this.userId = userId;
		this.userPwd = userPwd;
		this.address = address;
	
	}


	public Member(String userId, String userPwd, String userName, String gender, int age, String email, String phone, String address,
			String hoppy) {
		super();
		this.userId = userId;
		this.userPwd = userPwd;
		this.userName = userName;
		this.gender = gender;
		this.age = age;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.hoppy = hoppy;
	}

	
	// 메소드
		//getter / setter메소드 toString
	//alt shit s r
	public int getUserNo() {
		return userNo;
	}


	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPwd() {
		return userPwd;
	}


	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}

	
	//alt shit s s
	@Override
	public String toString() {
		return  userNo + ", " + userId + "," +  userPwd + ", " + email + ", " + address;
	}
	
	
	
	
}

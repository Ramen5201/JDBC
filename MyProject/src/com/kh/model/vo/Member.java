package com.kh.model.vo;
/*
 * VO(value object)
 * 한명의 회원(db테이블의 한 맿ㅇ의 데이터)에 대한 데이터를 기록할 수 있는 저장용 객체
 */
public class Member {
	private int memberNo;
	private String memberId;
	private String memberPwd;

	
	
	
	public Member() {
		super();
	}

	public Member(int memberNo, String memberId,String memberPwd) {
		super();
		this.memberNo = memberNo;
		this.memberId = memberId;
		this.memberPwd = memberPwd;
	}
	
	public Member(String memberId, String memberPwd) {
		super();
		this.memberId = memberId;
		this.memberPwd = memberPwd;
	}


	public int getMemberNo() {
		return memberNo;
	}


	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}


	public String getMemberId() {
		return memberId;
	}


	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}


	public String getMemberPwd() {
		return memberPwd;
	}


	public void setMemberPwd(String memberPwd) {
		this.memberPwd = memberPwd;
	}





	@Override
	public String toString() {
		return  memberNo + ", " + memberId + ", " + memberPwd; 
	}
	
	
	
}

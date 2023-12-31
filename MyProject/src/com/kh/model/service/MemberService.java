package com.kh.model.service;

import java.sql.Connection;

import com.kh.common.JDBCTemplate;
import com.kh.model.dao.MemberDao;
import com.kh.model.vo.Member;

public class MemberService {
	static Member loginMember;

	public int insertMember(Member m) {

		Connection conn = JDBCTemplate.getConnection();
		int result = new MemberDao().insertMember(conn, m);

		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}

		JDBCTemplate.close(conn);

		return result;
	}

	public void loginMember(String userId, String userPwd) {
		Connection conn = JDBCTemplate.getConnection();
		loginMember =  new MemberDao().loginMember(conn, userId, userPwd);
		JDBCTemplate.close(conn);
	}

}

package com.kh.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.common.JDBCTemplate;
import com.kh.model.vo.Member;

public class MemberDao {
	
	 	private Properties prop = new Properties();
		
		public MemberDao() {
			try {
				prop.loadFromXML(new FileInputStream("resources/query.xml"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		public int insertMember(Connection conn, Member m) {
			
			int result = 0;

			PreparedStatement pstmt = null;
			String sql = prop.getProperty("insertMember");
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, m.getMemberId());
				pstmt.setString(2, m.getMemberPwd());
				
				result = pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCTemplate.close(pstmt);
			}

			return result;
		}
		
		public Member loginMember(Connection conn, String memberId, String memberPwd) {
			
			Member m = new Member();
			
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			
			String sql = prop.getProperty("loginMember");
			
			try {
				pstmt = conn.prepareStatement(sql);
				
				rset = pstmt.executeQuery();
				
				pstmt.setString(1, m.getMemberId());
				pstmt.setString(2, m.getMemberPwd());
				
				if(rset.next()) {
					m = new Member();
					m.setMemberNo(rset.getInt("meberno"));
					m.setMemberId(rset.getString("memberid"));
					m.setMemberPwd(rset.getString("memberpwd"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCTemplate.close(rset);
				JDBCTemplate.close(pstmt);
			}
			
			return m;
		}
			
}


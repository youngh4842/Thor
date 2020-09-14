package thor.common.login.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import thor.db.DBManager;
import thor.vo.MemberVO;

public class LoginDAO {
	private LoginDAO() { }
	private static LoginDAO inst = new LoginDAO();
	public static LoginDAO getInstance() {
		return inst;
	}
	
	
	public int loginCheck(String id, String pw) {
		String sql              = "select m_pw from member where m_id=?";
		Connection conn         = null;
		PreparedStatement pstmt = null;
		ResultSet rs            = null;
		int result = 0;
		
		try {
			conn  = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs    = pstmt.executeQuery();
			
			if ( rs.next() ) {
				if ( rs.getString("m_pw") != null && rs.getString("m_pw").equals(pw) )
					result = 1;		// 로그인 성공 시
				else
					result = 0;		// 아이디는 있지만 비밀번호가 틀렸을 시
			}
			else
				result = -1;		// 아이디조차 존재하지 않을 때
		} catch ( SQLException se ) { se.printStackTrace(); }
		finally { DBManager.close(conn, pstmt, rs); }
		
		return result;
	}
	
	
	public MemberVO login(String id, String pw) {
		String sql              = "select * from member where m_id=? and m_pw=?";
		Connection conn         = null;
		PreparedStatement pstmt = null;
		ResultSet rs            = null;
		MemberVO mVo            = null;
		
		try {
			conn  = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			rs    = pstmt.executeQuery();
			
			if ( rs.next() ) {
				mVo = new MemberVO();
				
				mVo.setM_id(rs.getString("m_id"));
				mVo.setM_name(rs.getString("m_name"));
				mVo.setM_email(rs.getString("m_email"));
				mVo.setM_phone(rs.getString("m_phone"));
				mVo.setM_admin(rs.getInt("m_admin"));
			} 
		} catch ( SQLException se ) { se.printStackTrace(); }
		finally { DBManager.close(conn, pstmt, rs); }
		
		return mVo;
	}
	
	public MemberVO findPw(String id, String name, String phone) {
		String sql              = "select m_pw, m_email from member where m_id=? and m_name=? and m_phone=?";
		MemberVO mVo            = null;
		Connection conn         = null;
		PreparedStatement pstmt = null;
		ResultSet rs            = null;
		
		try {
			conn  = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, name);
			pstmt.setString(3, phone);
			rs    = pstmt.executeQuery();
			
			if ( rs.next() ) {
				mVo = new MemberVO();
				mVo.setM_pw(rs.getString("m_pw"));
				mVo.setM_email(rs.getString("m_email"));
			}
		} catch ( SQLException se ) { se.printStackTrace(); }
		finally { DBManager.close(conn, pstmt, rs); }
		
		return mVo;
	}
	
	public int findIDCheck (String name, String phone) {
		String sql 				= "select m_phone from member where m_name=?";
		Connection conn         = null;
		PreparedStatement pstmt = null;
		ResultSet rs            = null;
		int result = 0;
		
		try {
			conn  = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			rs    = pstmt.executeQuery();
			
			if (rs.next()) {
				if (rs.getString("m_phone")!=null  && rs.getString("m_phone").equals(phone))
					result = 1; // 두 정보가 일치한 경우
				else 
					result = 0; // 이름의 정보만 일치한 경우
			}
			else 
				result = -1; 	// 두 정보가 일치하지 않는 경우
		} catch ( SQLException se ) { se.printStackTrace(); }
		finally { DBManager.close(conn, pstmt, rs); }
		
		return result;
	}
	
	public String findID (String name, String phone) {
		String sql              = "select * from member where m_name=? and m_phone=?";
		Connection conn         = null;
		PreparedStatement pstmt = null;
		ResultSet rs            = null;
		String findid			= null;
		
		try {
			conn  = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, phone);
			rs    = pstmt.executeQuery();
			
			if ( rs.next() ) {
				findid = rs.getString("m_id");
			} 
		} catch ( SQLException se ) { se.printStackTrace(); }
		finally { DBManager.close(conn, pstmt, rs); }
		
		return findid;
	}
	
	public int signupMember(MemberVO mVo) {
		String sql				= "insert into member values (?, ?, ?, ?, ?, ?, ?)";
		Connection conn			= null;
		PreparedStatement pstmt = null;

		int result = 0;
		
		try {
			conn  = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mVo.getM_id());
			pstmt.setString(2, mVo.getM_pw());
			pstmt.setString(3, mVo.getM_name());
			pstmt.setString(4, mVo.getM_email());
			pstmt.setString(5, mVo.getM_phone());
			pstmt.setInt(6, 0);
			pstmt.setInt(7, 0);
			
			result = pstmt.executeUpdate();
			
			
		} catch ( SQLException se ) { se.printStackTrace(); }
		finally { DBManager.close(conn, pstmt, null); }
		
		return result;
	}
	
	public int duplicateCheck(String id) {
		String sql				= "select m_id from member where m_id=?";
		Connection conn			= null;
		PreparedStatement pstmt = null;
		ResultSet rs			= null;
		int result = 0; 
		
		try {
			conn  = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs 	  = pstmt.executeQuery();
			
			if ( rs.next() ) {
				result = 1;
			}
			else {
				result = 0;
			}
		} catch (SQLException se) { se.printStackTrace(); }
		finally { DBManager.close(conn, pstmt, rs); }
		
		return result;
	}
}

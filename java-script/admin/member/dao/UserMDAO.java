package thor.admin.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import thor.db.DBManager;
import thor.vo.MemberVO;

public class UserMDAO {
	private UserMDAO() {}
	private static UserMDAO inst = new UserMDAO();
	public static UserMDAO getInstance() {
		return inst;
	}
	
	public ArrayList<MemberVO> userManage() {
		String sql				= "select * from member";
		Connection conn         = null;
		PreparedStatement pstmt = null;
		ResultSet rs            = null;

		int check = 0;	//관리자(1)와 회원(0) 구분
		
		MemberVO mVo = null;
		ArrayList<MemberVO> member = new ArrayList<>();
		
		try {
			conn  = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs    = pstmt.executeQuery();
			
			while (rs.next()) {
				
				mVo = new MemberVO();
				
				if (rs.getInt("m_admin") == check) {	//회원일 경우
					
					mVo.setM_id(rs.getString("m_id"));
					mVo.setM_name(rs.getString("m_name"));
					mVo.setM_email(rs.getString("m_email"));
					mVo.setM_phone(rs.getString("m_phone"));
					mVo.setM_point(rs.getInt("m_point"));
					mVo.setM_admin(rs.getInt("m_admin"));
					
					member.add(mVo);
					
				}
						
			}
						
		}catch ( SQLException se ) { se.printStackTrace(); }
		finally { DBManager.close(conn, pstmt, rs); }
		
		return member;
	}
	
	//포인트 정보 변경을 위해
	public void userManagePoint() {
		
	}
	
	
	public ArrayList<MemberVO> userSelect(String filter, String find){
		
		String sql 				= "select * from member where " + filter + "=?";
		Connection conn         = null;
		PreparedStatement pstmt = null;
		ResultSet rs            = null;
		MemberVO mVo            = null;
		ArrayList<MemberVO> member =  new ArrayList<>();
		
		try {
			conn  = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, find);
			rs    = pstmt.executeQuery();
			
			while ( rs.next() ) {
				
				mVo = new MemberVO();
				
				mVo.setM_id(rs.getString("m_id"));
				mVo.setM_name(rs.getString("m_name"));
				mVo.setM_email(rs.getString("m_email"));
				mVo.setM_phone(rs.getString("m_phone"));
				mVo.setM_point(rs.getInt("m_point"));
				mVo.setM_admin(rs.getInt("m_admin"));
				
				member.add(mVo);
			}
		}catch ( SQLException se ) { se.printStackTrace(); }
		finally { DBManager.close(conn, pstmt, rs); }
		
		return member;
	}
	
	public MemberVO userCheck (String id) {
		String sql              = "select * from member where m_id=?";
		Connection conn         = null;
		PreparedStatement pstmt = null;
		ResultSet rs            = null;
		MemberVO mVo            = null;
		
		try {
			conn  = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs    = pstmt.executeQuery();
			
			if ( rs.next() ) {
				mVo = new MemberVO();
				
				mVo.setM_id(rs.getString("m_id"));
				mVo.setM_name(rs.getString("m_name"));
				mVo.setM_email(rs.getString("m_email"));
				mVo.setM_phone(rs.getString("m_phone"));
				mVo.setM_point(rs.getInt("m_point"));
				mVo.setM_admin(rs.getInt("m_admin"));
			} 
		} catch ( SQLException se ) { se.printStackTrace(); }
		finally { DBManager.close(conn, pstmt, rs); }
		
		return mVo;
		
		
	}
	
	//Member테이블에서 해당 아이디정보 삭제
	public int userDelete (String id) {
		String sql 				= "Delete from member where m_id=?";
		Connection conn        	= null;
		PreparedStatement pstmt = null;
		int result				= 0;
		
		try {
			userQuestionDelete(id);
			userRentDelete(id);
			userPointDelete(id);
			
			conn  = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			result = pstmt.executeUpdate();		//행이 하나 삭제되면 1이 리턴
		
		}catch ( SQLException se ) { se.printStackTrace(); }
		finally { DBManager.close(conn, pstmt, null); }
		
		
		return result;
	}
	
	//Question테이블에서 해당 아이디정보 삭제
	public void userQuestionDelete (String id) {
		String sql				="Delete from question where q_ref_Mid=?";
		Connection conn        	= null;
		PreparedStatement pstmt = null;
		
		try {
			conn  = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
			
		}catch ( SQLException se ) { se.printStackTrace(); }
		finally { DBManager.close(conn, pstmt, null); }
	}
	
	//Rent테이블에서 해당 아이디정보 삭제
	public void userRentDelete (String id) {
		String sql				="Delete from rent where r_ref_Mid=?";
		Connection conn        	= null;
		PreparedStatement pstmt = null;
		
		try {
			conn  = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
			
		}catch ( SQLException se ) { se.printStackTrace(); }
		finally { DBManager.close(conn, pstmt, null); }
	}
	
	//Point테이블에서 해당 아이디정보 삭제
	public void userPointDelete (String id) {
		String sql				="Delete from point where p_ref_Mid=?";
		Connection conn        	= null;
		PreparedStatement pstmt = null;
		
		try {
			conn  = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
			
		}catch ( SQLException se ) { se.printStackTrace(); }
		finally { DBManager.close(conn, pstmt, null); }
	}
		
}

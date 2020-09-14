package thor.user.mypage.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import thor.db.DBManager;
import thor.vo.MemberVO;
import thor.vo.PointVO;

public class MyPageDAO {
	private MyPageDAO() { }
	private static MyPageDAO inst = new MyPageDAO();
	public static MyPageDAO getInstance() {
		return inst;
	}
	
	public MemberVO selectMember(String m_id) {
		String sql              = "select * from member where m_id=?";
		Connection conn         = null;
		PreparedStatement pstmt = null;
		ResultSet rs            = null;
		MemberVO mVo            = null;
		
		try {
			conn  = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m_id);
			rs    = pstmt.executeQuery();
			
			if ( rs.next() ) {
				mVo = new MemberVO();
				mVo.setM_id(rs.getString("m_id"));
				mVo.setM_name(rs.getString("m_name"));
				mVo.setM_email(rs.getString("m_email"));
				mVo.setM_phone(rs.getString("m_phone"));
				mVo.setM_point(rs.getInt("m_point"));
			}
		} catch ( SQLException se ) { se.printStackTrace(); }
		finally { DBManager.close(conn, pstmt, rs); }

		return mVo;
	}
	
	public int deleteMember(String m_id) {
		String sql              = "delete from member where m_id=?";
		Connection conn         = null;
		PreparedStatement pstmt = null;
		int result              = 0;
		
		try {
			deleteMemberFromQuestion(m_id);
			deleteMemberFromRent(m_id);
			deleteMemberFromPoint(m_id);
			
			conn   = DBManager.getConnection();
			pstmt  = conn.prepareStatement(sql);
			pstmt.setString(1, m_id);
			result = pstmt.executeUpdate();
		} catch ( SQLException se ) { se.printStackTrace(); }
		finally { DBManager.close(conn, pstmt, null); }

		return result;
	}
	
	private void deleteMemberFromQuestion(String m_id) {
		String sql = "delete from question where q_ref_Mid=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m_id);
			pstmt.executeUpdate();
		} catch ( SQLException se ) { se.printStackTrace(); }
		finally { DBManager.close(conn, pstmt, null); }
	}
	
	private void deleteMemberFromRent(String m_id) {
		String sql = "delete from rent where r_ref_Mid=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m_id);
			pstmt.executeUpdate();
		} catch ( SQLException se ) { se.printStackTrace(); }
		finally { DBManager.close(conn, pstmt, null); }
	}
	
	private void deleteMemberFromPoint(String m_id) {
		String sql = "delete from point where p_ref_Mid=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m_id);
			pstmt.executeUpdate();
		} catch ( SQLException se ) { se.printStackTrace(); }
		finally { DBManager.close(conn, pstmt, null); }
	}
	
	public int updateMember(MemberVO mVo) {
		String sql              = "update member set m_name=?, m_email=?, m_phone=? where m_id=?";
		Connection conn         = null;
		PreparedStatement pstmt = null;
		int result              = 0;
		
		try {
			conn   = DBManager.getConnection();
			pstmt  = conn.prepareStatement(sql);
			pstmt.setString(1, mVo.getM_name());
			pstmt.setString(2, mVo.getM_email());
			pstmt.setString(3, mVo.getM_phone());
			pstmt.setString(4, mVo.getM_id());
			result = pstmt.executeUpdate();
		} catch ( SQLException se ) { se.printStackTrace(); }
		finally { DBManager.close(conn, pstmt, null); }
		
		return result;
	}
	
	public int pwChange(String m_id, String newPw, String oldPw) {
		String sql = "update member set m_pw=? where m_id=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			if ( pwChangeCheck(m_id, oldPw) ) {
				conn   = DBManager.getConnection();
				pstmt  = conn.prepareStatement(sql);
				pstmt.setString(1, newPw);
				pstmt.setString(2, m_id);
				result = pstmt.executeUpdate();
			}
			else
				result = -1;
		} catch ( SQLException se ) { se.printStackTrace(); }
		finally { DBManager.close(conn, pstmt, null); }
	
		return result;
	}
	
	private boolean pwChangeCheck(String m_id, String oldPw) {
		String sql              = "select m_pw from member where m_id=?";
		Connection conn         = null;
		PreparedStatement pstmt = null;
		ResultSet rs            = null;
		boolean result          = false;
		
		try {
			conn  = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m_id);
			rs    = pstmt.executeQuery();
			
			if ( rs.next() )
				if ( oldPw.equals(rs.getString("m_pw")) )
					result = true;
		} catch ( SQLException se ) { se.printStackTrace(); }
		finally { DBManager.close(conn, pstmt, rs); }
		
		return result;
	}
	
	public ArrayList<PointVO> selectPoint(String m_id) {
		String sql              = "select * from point where p_ref_Mid=? order by p_days desc";
		Connection conn         = null;
		PreparedStatement pstmt = null;
		ResultSet rs            = null;
		ArrayList<PointVO> list = new ArrayList<PointVO>();
		PointVO pVo             = null;
		
		try {
			conn  = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m_id);
			rs    = pstmt.executeQuery();
			
			while ( rs.next() ) {
				pVo = new PointVO();
				pVo.setP_ref_Mid(rs.getString("p_ref_Mid"));
				pVo.setP_days(rs.getString("p_days"));
				pVo.setP_changep(rs.getInt("p_changep"));
				pVo.setP_remainp(rs.getInt("p_remainp"));
				pVo.setP_content(rs.getString("p_content"));
				
				list.add(pVo);
			} 
		} catch ( SQLException se ) { se.printStackTrace(); }
		finally { DBManager.close(conn, pstmt, rs); }
		
		return list;
	}
}

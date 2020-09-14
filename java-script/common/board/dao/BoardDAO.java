package thor.common.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import thor.db.DBManager;
import thor.vo.AnswerVO;
import thor.vo.QuestionVO;

public class BoardDAO {
	private BoardDAO() { }
	private static BoardDAO inst = new BoardDAO();
	public static BoardDAO getInstance() {
		return inst;
	}
	
	public ArrayList<QuestionVO> selectQuestion(int startRow, int endRow, String searchType, String searchTerm) {
		String sql                 = "select * from question where q_num between ? and ? order by q_num desc";
		Connection conn            = null;
		PreparedStatement pstmt    = null;
		ResultSet rs               = null;
		ArrayList<QuestionVO> list = new ArrayList<QuestionVO>();
		QuestionVO qVo             = null;
		
		if ( searchType != null && !"".equals(searchType) && searchTerm != null && !"".equals(searchTerm) ) {
			sql = "select * from question where q_num between ? and ? and " + searchType + "=? order by q_num desc";
		}
		
		try {
			conn  = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			if ( searchType != null && !"".equals(searchType) && searchTerm != null && !"".equals(searchTerm) )
				pstmt.setString(3, searchTerm);
			rs    = pstmt.executeQuery();
			
			while ( rs.next() ) {
				qVo = new QuestionVO();
				qVo.setQ_num(rs.getInt("q_num"));
				qVo.setQ_title(rs.getString("q_title"));
				qVo.setQ_ref_Mid(rs.getString("q_ref_Mid"));
				qVo.setQ_content(rs.getString("q_content"));
				qVo.setQ_days(rs.getString("q_days"));
				
				list.add(qVo);
			}
		} catch ( SQLException se ) { se.printStackTrace(); }
		finally { DBManager.close(conn, pstmt, rs); }
		
		return list;
	}
	
	public ArrayList<AnswerVO> selectAnswer() {
		String sql                 = "select * from answer";
		Connection conn            = null;
		PreparedStatement pstmt    = null;
		ResultSet rs               = null;
		ArrayList<AnswerVO> list   = new ArrayList<AnswerVO>();
		AnswerVO aVo               = null;
		
		try {
			conn  = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs    = pstmt.executeQuery();
			
			while ( rs.next() ) {
				aVo = new AnswerVO();
				aVo.setA_num(rs.getInt("a_num"));
				aVo.setA_qnum(rs.getInt("a_qnum"));
				aVo.setA_title(rs.getString("a_title"));
				aVo.setA_content(rs.getString("a_content"));
				aVo.setA_name(rs.getString("a_name"));
				aVo.setA_days(rs.getString("a_days"));
				
				list.add(aVo);
			}
		} catch ( SQLException se ) { se.printStackTrace(); }
		finally { DBManager.close(conn, pstmt, rs); }
		
		return list;
	}
	
	public int countQuestion(String searchType, String searchTerm) {
		String sql              = "select count(*) from question";
		Connection conn         = null;
		PreparedStatement pstmt = null;
		ResultSet rs            = null;
		int result              = 0;
		
		if ( searchType != null && !"".equals(searchType) )
			sql = "select count(*) from question where " + searchType + "=?";
		
		try {
			conn  = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			if ( searchType != null && !"".equals(searchType) )
				pstmt.setString(1, searchTerm);
			rs    = pstmt.executeQuery();
			
			if ( rs.next() )
				result = rs.getInt("count(*)");
		} catch ( SQLException se ) { se.printStackTrace(); }
		finally { DBManager.close(conn, pstmt, rs); }
		
		return result;
	}
	
	public int countAnswer(String searchType, String searchTerm) {
		String sql              = "select count(*) from answer";
		Connection conn         = null;
		PreparedStatement pstmt = null;
		ResultSet rs            = null;
		int result              = 0;
		
		if ( searchType != null && !"".equals(searchType) )
			sql = "select count(*) from answer where " + searchType + "=?";
		
		try {
			conn  = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			if ( searchType != null && !"".equals(searchType) )
				pstmt.setString(1, searchTerm);
			rs    = pstmt.executeQuery();
			
			if ( rs.next() )
				result = rs.getInt("count(*)");
		} catch ( SQLException se ) { se.printStackTrace(); }
		finally { DBManager.close(conn, pstmt, rs); }
		
		return result;
	}
	
	public int insertQuestion(QuestionVO qVo) {
		String sql              = "insert into question values (null, ?, ?, ?, now())";
		Connection conn         = null;
		PreparedStatement pstmt = null;
		int result              = 0;
		
		try {
			conn   = DBManager.getConnection();
			pstmt  = conn.prepareStatement(sql);
			pstmt.setString(1, qVo.getQ_title());
			pstmt.setString(2, qVo.getQ_ref_Mid());
			pstmt.setString(3, qVo.getQ_content());
			result = pstmt.executeUpdate();
		} catch ( SQLException se ) { se.printStackTrace(); }
		finally { DBManager.close(conn, pstmt, null); }
		
		return result;
	}
	
	public int insertAnswer(AnswerVO aVo) {
		String sql              = "insert into answer values (null, ?, ?, ?, ?, now())";
		Connection conn         = null;
		PreparedStatement pstmt = null;
		int result              = 0;
		
		try {
			conn   = DBManager.getConnection();
			pstmt  = conn.prepareStatement(sql);
			pstmt.setInt(1, aVo.getA_qnum());
			pstmt.setString(2, aVo.getA_title());
			pstmt.setString(3, aVo.getA_content());
			pstmt.setString(4, aVo.getA_name());
			result = pstmt.executeUpdate();
		} catch ( SQLException se ) { se.printStackTrace(); }
		finally { DBManager.close(conn, pstmt, null); }
		
		return result;
	}
	
	public QuestionVO viewQuestion(int q_num) {
		String sql              = "select * from question where q_num=?";
		Connection conn         = null;
		PreparedStatement pstmt = null;
		ResultSet rs            = null;
		QuestionVO qVo          = null;
		
		try {
			conn  = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, q_num);
			rs    = pstmt.executeQuery();
			
			if ( rs.next() ) {
				qVo = new QuestionVO();
				qVo.setQ_num(rs.getInt("q_num"));
				qVo.setQ_title(rs.getString("q_title"));
				qVo.setQ_ref_Mid(rs.getString("q_ref_Mid"));
				qVo.setQ_content(rs.getString("q_content"));
				qVo.setQ_days(rs.getString("q_days"));
			}
		} catch ( SQLException se ) { se.printStackTrace(); }
		finally { DBManager.close(conn, pstmt, rs); }
		
		return qVo;
	}
	
	public AnswerVO viewAnswer(int a_num) {
		String sql              = "select * from answer where a_num=?";
		Connection conn         = null;
		PreparedStatement pstmt = null;
		ResultSet rs            = null;
		AnswerVO aVo            = null;
		
		try {
			conn  = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, a_num);
			rs    = pstmt.executeQuery();
			
			if ( rs.next() ) {
				aVo = new AnswerVO();
				aVo.setA_num(rs.getInt("a_num"));
				aVo.setA_qnum(rs.getInt("a_qnum"));
				aVo.setA_title(rs.getString("a_title"));
				aVo.setA_content(rs.getString("a_content"));
				aVo.setA_name(rs.getString("a_name"));
				aVo.setA_days(rs.getString("a_days"));
			}
		} catch ( SQLException se ) { se.printStackTrace(); }
		finally { DBManager.close(conn, pstmt, rs); }
		
		return aVo;
	}
	
	public int updateQuestion(QuestionVO qVo) {
		String sql              = "update question set q_title=?, q_content=?, q_days=now() where q_num=?";
		Connection conn         = null;
		PreparedStatement pstmt = null;
		int result              = 0;
		
		try {
			conn   = DBManager.getConnection();
			pstmt  = conn.prepareStatement(sql);
			pstmt.setString(1, qVo.getQ_title());
			pstmt.setString(2, qVo.getQ_content());
			pstmt.setInt(3, qVo.getQ_num());
			result = pstmt.executeUpdate();
		} catch ( SQLException se ) { se.printStackTrace(); }
		finally { DBManager.close(conn, pstmt, null); }
		
		return result;
	}
	
	public int updateAnswer(AnswerVO aVo) {
		String sql              = "update answer set a_title=?, a_content=?, a_days=now() where a_num=?";
		Connection conn         = null;
		PreparedStatement pstmt = null;
		int result              = 0;
		
		try {
			conn   = DBManager.getConnection();
			pstmt  = conn.prepareStatement(sql);
			pstmt.setString(1, aVo.getA_title());
			pstmt.setString(2, aVo.getA_content());
			pstmt.setInt(3, aVo.getA_num());
			result = pstmt.executeUpdate();
		} catch ( SQLException se ) { se.printStackTrace(); }
		finally { DBManager.close(conn, pstmt, null); }
		
		return result;
	}
	
	public int deleteQuestion(int q_num) {
		String sql              = "delete from question where q_num=?";
		Connection conn         = null;
		PreparedStatement pstmt = null;
		int result              = 0;
		
		try {
			deleteAnswerFromQuestion(q_num);
			conn   = DBManager.getConnection();
			pstmt  = conn.prepareStatement(sql);
			pstmt.setInt(1, q_num);
			result = pstmt.executeUpdate();
		} catch ( SQLException se ) { se.printStackTrace(); }
		finally { DBManager.close(conn, pstmt, null); }
		
		return result;
	}
	
	public int deleteAnswer(int a_num) {
		String sql              = "delete from answer where a_num=?";
		Connection conn         = null;
		PreparedStatement pstmt = null;
		int result              = 0;
		
		try {
			conn   = DBManager.getConnection();
			pstmt  = conn.prepareStatement(sql);
			pstmt.setInt(1, a_num);
			result = pstmt.executeUpdate();
		} catch ( SQLException se ) { se.printStackTrace(); }
		finally { DBManager.close(conn, pstmt, null); }
		
		return result;
	}
	
	private void deleteAnswerFromQuestion(int q_num) {
		String sql              = "delete from answer where a_qnum=?";
		Connection conn         = null;
		PreparedStatement pstmt = null;
		
		try {
			conn  = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, q_num);
			pstmt.executeUpdate();
		} catch ( SQLException se ) { se.printStackTrace(); }
		finally { DBManager.close(conn, pstmt, null); }
	}
}

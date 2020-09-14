package thor.admin.rent.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import thor.db.DBManager;
import thor.vo.PointVO;
import thor.vo.RentVO;

public class RentDAO {
	private RentDAO() { }
	private static RentDAO inst = new RentDAO();
	public static RentDAO getInstance() {
		return inst;
	}
	
	/* 버그1 : 검색타입이 '상태' and 검색어가 ' '일 때 아무 것도 검색이 안되야 하는게 정상인데
			  r_num이 1부터 5까지 and r_status=0 인 ResultSet을 리턴한다. (2018/08/01) */
	public ArrayList<RentVO> selectAllRent(int startRow, int endRow, String searchType, String searchTerm) {
		String sql              = "select * from rent where r_num between ? and ? order by r_num desc";
		Connection conn         = null;
		PreparedStatement pstmt = null;
		ResultSet rs            = null;
		ArrayList<RentVO> list  = new ArrayList<RentVO>();
		RentVO rVo              = null;
		
		if ( searchType != null && !"".equals(searchType) )
			sql = "select * from rent where r_num between ? and ? and " + searchType + "=? order by r_num desc";
		
		try {
			conn  = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			if ( searchType != null && !"".equals(searchType) ) {
				pstmt.setString(3, searchTerm);
			}
			rs    = pstmt.executeQuery();
			
			while ( rs.next() ) {
				rVo = new RentVO();
				rVo.setR_num(rs.getInt("r_num"));
				rVo.setR_ref_Mid(rs.getString("r_ref_Mid"));
				rVo.setR_name(rs.getString("r_name"));
				rVo.setR_startDays(rs.getString("r_startdays"));
				rVo.setR_endDays(rs.getString("r_enddays"));
				rVo.setR_status(rs.getInt("r_status"));
				rVo.setR_carUrl(rs.getString("r_carurl"));
				
				list.add(rVo);
			}
		} catch ( SQLException se ) { se.printStackTrace(); }
		finally { DBManager.close(conn, pstmt, rs); }
		
		return list;
	}
	
	public int approveRent(int r_num) {
		String sql              = "update rent set r_status=? where r_num=?";
		Connection conn         = null;
		PreparedStatement pstmt = null;
		int result              = 0;
		PointVO pVo             = new PointVO();
		
		String m_id   = getMemberId(r_num);
		int p_changep = increasePointToMember(m_id, r_num);
		int m_point   = getMemberPoint(m_id);
		pVo.setP_ref_Mid(m_id);
		pVo.setP_changep(p_changep);
		pVo.setP_remainp(m_point);
		pVo.setP_content("구매");
		writePointContent(pVo);
		
		try {
			conn   = DBManager.getConnection();
			pstmt  = conn.prepareStatement(sql);
			pstmt.setInt(1, 1);
			pstmt.setInt(2, r_num);
			result = pstmt.executeUpdate();
		} catch ( SQLException se ) { se.printStackTrace(); }
		finally { DBManager.close(conn, pstmt, null); }
		
		return result;
	}
	
	public int denyRent(int r_num) {
		String sql              = "update rent set r_status=? where r_num=?";
		Connection conn         = null;
		PreparedStatement pstmt = null;
		int result              = 0;
		
		try {
			conn   = DBManager.getConnection();
			pstmt  = conn.prepareStatement(sql);
			pstmt.setInt(1, 0);
			pstmt.setInt(2, r_num);
			result = pstmt.executeUpdate();
		} catch ( SQLException se ) { se.printStackTrace(); }
		finally { DBManager.close(conn, pstmt, null); }
		
		return result;
	}
	
	public String getMemberId(int r_num) {
		String sql              = "select r_ref_Mid from rent where r_num=?";
		Connection conn         = null;
		PreparedStatement pstmt = null;
		ResultSet rs            = null;
		String result           = "";
		
		try {
			conn   = DBManager.getConnection();
			pstmt  = conn.prepareStatement(sql);
			pstmt.setInt(1, r_num);
			rs     = pstmt.executeQuery();
			
			if ( rs.next() )
				result = rs.getString("r_ref_Mid");
		} catch ( SQLException se ) { se.printStackTrace(); }
		finally { DBManager.close(conn, pstmt, null); }
		
		return result;
	}
	
	// approveRent 메서드에서 호출
	private int increasePointToMember(String m_id, int r_num) {
		String sql              = "update member set m_point=m_point+? where m_id=?";
		Connection conn         = null;
		PreparedStatement pstmt = null;
		String c_url            = getCarUrl(r_num);		// 자동차 사진 URL
		int c_cost              = getCost(c_url);		// 자동차 하루 대여료
		String date1            = getStartDays(r_num);	// 대여일
		String date2            = getEndDays(r_num);	// 반납일

		String date1_year       = date1.substring(0, 4);
	    String date2_year       = date2.substring(0, 4);
	    String date1_month      = date1.substring(5, 7);
	    String date2_month      = date2.substring(5, 7);
	    String date1_day        = date1.substring(8, 10);
	    String date2_day        = date2.substring(8, 10);
	      
	    int year1   = Integer.parseInt(date1_year);
	    int year2   = Integer.parseInt(date2_year);
	    int month1  = Integer.parseInt(date1_month);
	    int month2  = Integer.parseInt(date2_month);
	    int day1    = Integer.parseInt(date1_day);
	    int day2    = Integer.parseInt(date2_day);
	      
	    long d1, d2;
	    Calendar c1 = Calendar.getInstance();
	    Calendar c2 = Calendar.getInstance();
	    c1.set(year1, month1 - 1, day1);
	    c2.set(year2, month2 - 1, day2);
	    d1          = c1.getTime().getTime();
	    d2          = c2.getTime().getTime();
	    int days    = (int)((d2 - d1) / (1000 * 60 * 60 * 24));	// 대여일부터 반납일까지의 일 수
	    int point   = (c_cost * days) / 10;						// 합산할 포인트
     
		try {
			conn  = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, point);
			pstmt.setString(2, m_id);
			pstmt.executeUpdate();
		} catch ( SQLException se ) { se.printStackTrace(); }
		finally { DBManager.close(conn, pstmt, null); }
		
		return point;
	}
	
	// increasePointToMember 메서드에서 호출
	private String getStartDays(int r_num) {
		String sql              = "select r_startdays from rent where r_num=?";
		Connection conn         = null;
		PreparedStatement pstmt = null;
		ResultSet rs            = null;
		String result           = "";
		
		try {
			conn  = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, r_num);
			rs    = pstmt.executeQuery();
			
			if ( rs.next() )
				result = rs.getString("r_startdays");
		} catch ( SQLException se ) { se.printStackTrace(); }
		finally { DBManager.close(conn, pstmt, rs); }
		
		return result;
	}
	
	// increasePointToMember 메서드에서 호출
	private String getEndDays(int r_num) {
		String sql              = "select r_enddays from rent where r_num=?";
		Connection conn         = null;
		PreparedStatement pstmt = null;
		ResultSet rs            = null;
		String result           = "";
		
		try {
			conn  = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, r_num);
			rs    = pstmt.executeQuery();
			
			if ( rs.next() )
				result = rs.getString("r_enddays");
		} catch ( SQLException se ) { se.printStackTrace(); }
		finally { DBManager.close(conn, pstmt, rs); }
		
		return result;
	}
	
	// increasePointToMember 메서드에서 호출
	private int getCost(String c_url) {
		String sql              = "select c_cost from car where c_url=?";
		Connection conn         = null;
		PreparedStatement pstmt = null;
		ResultSet rs            = null;
		int result              = 0;
		
		try {
			conn  = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, c_url);
			rs    = pstmt.executeQuery();
			
			if ( rs.next() )
				result = rs.getInt("c_cost");
		} catch ( SQLException se ) { se.printStackTrace(); }
		finally { DBManager.close(conn, pstmt, rs); }
		
		return result;
	}
	
	// increasePointToMember 메서드에서 호출
	private String getCarUrl(int r_num) {
		String sql              = "select r_carurl from rent where r_num=?";
		Connection conn         = null;
		PreparedStatement pstmt = null;
		ResultSet rs            = null;
		String result           = "";
		
		try {
			conn  = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, r_num);
			rs    = pstmt.executeQuery();
			
			if ( rs.next() )
				result = rs.getString("r_carurl");
		} catch ( SQLException se ) { se.printStackTrace(); }
		finally { DBManager.close(conn, pstmt, rs); }
		
		return result;
	}
	
	// approveRent 메서드에서 호출
	private void writePointContent(PointVO pVo) {
		String sql              = "insert into point values (null, ?, now(), ?, ?, ?)";
		Connection conn         = null;
		PreparedStatement pstmt = null;
		
		try {
			conn  = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pVo.getP_ref_Mid());
			pstmt.setInt(2, pVo.getP_changep());
			pstmt.setInt(3, pVo.getP_remainp());
			pstmt.setString(4, pVo.getP_content());
			pstmt.executeUpdate();
		} catch ( SQLException se ) { se.printStackTrace(); }
		finally { DBManager.close(conn, pstmt, null); }
	}
	
	// approveRent 메서드에서 호출
	private int getMemberPoint(String m_id) {
		String sql              = "select m_point from member where m_id=?";
		Connection conn         = null;
		PreparedStatement pstmt = null;
		ResultSet rs            = null;
		int result              = 0;
		
		try {
			conn   = DBManager.getConnection();
			pstmt  = conn.prepareStatement(sql);
			pstmt.setString(1, m_id);
			rs     = pstmt.executeQuery();
			
			if ( rs.next() )
				result = rs.getInt("m_point");
		} catch ( SQLException se ) { se.printStackTrace(); }
		finally { DBManager.close(conn, pstmt, rs); }
		
		return result;
	}
	
	public int pageList(String searchType, String searchTerm) {
		String sql              = "select count(*) from rent";
		Connection conn         = null;
		PreparedStatement pstmt = null;
		ResultSet rs            = null;
		int result              = 0;
		
		if ( searchType != null && !"".equals(searchType) )
			sql = "select count(*) from rent where " + searchType + "=?";
		
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
}

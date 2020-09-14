package thor.admin.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import thor.db.DBManager;
import thor.vo.PointVO;

public class UserPDAO {
	private UserPDAO() {}
	private static UserPDAO inst = new UserPDAO();
	public static UserPDAO getInstance() {
		return inst;
	}
	
	//해당 아이디에 해당하는 포인트 정보를 저장하는 리스트
	public ArrayList<PointVO> userPointL(String id) {
		String sql				= "select * from point where p_ref_Mid=? order by p_days desc";
		Connection conn         = null;
		PreparedStatement pstmt = null;
		ResultSet rs            = null;
		
		ArrayList<PointVO> pList= new ArrayList<>();
		PointVO pVo				= null;
		
		try {
			conn  = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs    = pstmt.executeQuery();
			
			while (rs.next()) {
				
				pVo = new PointVO();
				
				//if (rs.getString("p_ref_Mid").equals(id)) {
					pVo.setP_ref_Mid(rs.getString("p_ref_Mid"));
					pVo.setP_days(rs.getString("p_days"));
					pVo.setP_changep(rs.getInt("p_changep"));
					pVo.setP_remainp(rs.getInt("p_remainp"));
					pVo.setP_content(rs.getString("p_content"));
					
					pList.add(pVo);
				//}							
			}				
			
		}catch ( SQLException se ) { se.printStackTrace(); }
		finally { DBManager.close(conn, pstmt, rs); }
			
		return pList;
	}

	//포인트 정보를 추가하는 메소드 - (String days, int change, String content, String id)
	public int addPoint(PointVO pVO) {
				
		//마지막 remainp를 가져오기 위해서
		ArrayList<PointVO> pList	= new ArrayList<PointVO>();
		
		pList	= userPointL(pVO.getP_ref_Mid());
		
		String sql	=	"insert into point (p_ref_Mid, p_days, p_changep, p_content, p_remainp) values (?, ? ,?,?, ?)";
		Connection conn         = null;
		PreparedStatement pstmt = null;
		
		int result				= 0;
		int remain				= 0;

		remain = pVO.getP_changep() + pList.get(0).getP_remainp();
		pVO.setP_remainp(remain);
		
		try {		
			
			conn  = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, pVO.getP_ref_Mid());
			pstmt.setString(2, pVO.getP_days());
			pstmt.setInt(3, pVO.getP_changep());
			pstmt.setString(4, pVO.getP_content());
			pstmt.setInt(5, pVO.getP_remainp());

			result = pstmt.executeUpdate();
			
			if ( result > 0 )
				changePoint(pVO.getP_ref_Mid(), pVO.getP_remainp());
		}catch ( SQLException se ) { se.printStackTrace(); }
		finally { DBManager.close(conn, pstmt, null); }
		
		return result;
	}
	
	private void changePoint(String m_id, int m_point) {
		String sql              = "update member set m_point=? where m_id=?";
		Connection conn         = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, m_point);
			pstmt.setString(2, m_id);
			pstmt.executeUpdate();
		} catch ( SQLException se ) { se.printStackTrace(); }
		finally { DBManager.close(conn, pstmt, null); }
		
	}
	
}

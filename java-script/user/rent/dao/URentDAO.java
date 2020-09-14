package thor.user.rent.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import thor.db.DBManager;
import thor.vo.CarVO;
import thor.vo.MemberVO;
import thor.vo.PointVO;
import thor.vo.RentVO;

public class URentDAO {
	private URentDAO() {}
	private static URentDAO inst = new URentDAO();
	public static URentDAO getInstance() {
		return inst;
	}
	
	//car테이블에 있는 모든 차량의 정보를 arraylist에 저장
	public ArrayList<CarVO> rentcarLists(){
		String sql				= "select * from car";
		Connection conn			= null;
		PreparedStatement pstmt = null;
		ResultSet rs			= null;
		
		ArrayList<CarVO> carList = new ArrayList<>();
		CarVO carVO = null;
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				carVO = new CarVO();
				
				carVO.setC_url(rs.getString("c_url"));
				carVO.setC_name(rs.getString("c_name"));
				carVO.setC_fuel(rs.getInt("c_fuel"));
				carVO.setC_cost(rs.getInt("c_cost"));
				
				carList.add(carVO);
				
			}
		}catch (SQLException se) {se.printStackTrace();}
		finally { DBManager.close(conn, pstmt, rs);}
		
		return carList;
	}
	
	//한 차량의 정보를 저장
	public CarVO rentCar(String car_n) {
		String sql				= "select * from car where c_name=?";
		Connection conn 		= null;
		PreparedStatement pstmt = null;
		ResultSet rs 			= null;
		
		CarVO carVO 			= null;
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, car_n);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				carVO = new CarVO();
				
				carVO.setC_name(rs.getString("c_name"));
				carVO.setC_company(rs.getString("c_company"));
				carVO.setC_fuel(rs.getInt("c_fuel"));
				carVO.setC_cost(rs.getInt("c_cost"));
				carVO.setC_url(rs.getString("c_url"));			
			}			
		}catch (SQLException se) {se.printStackTrace();}
		finally {DBManager.close (conn, pstmt, rs);}
	
		return carVO;
	}
	
	//point테이블에 있는 포인트 저장 arraylist
	public ArrayList<PointVO> userPointL(String id){
		String sql 				= "select * from point where p_ref_Mid=? order by p_days desc";
		Connection conn         = null;
		PreparedStatement pstmt = null;
		ResultSet rs            = null;
		
		ArrayList<PointVO> pList= new ArrayList<PointVO>();
		PointVO pVO				= null;
		
		try {
			conn  = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs    = pstmt.executeQuery();
			
			while (rs.next()) {
				pVO	= new PointVO();
				
				pVO.setP_ref_Mid(rs.getString("p_ref_Mid"));
				pVO.setP_days(rs.getString("p_days"));
				pVO.setP_changep(rs.getInt("p_changep"));
				pVO.setP_remainp(rs.getInt("p_remainp"));
				pVO.setP_content(rs.getString("p_content"));
				
				pList.add(pVO);
			}
			
		}catch ( SQLException se ) { se.printStackTrace(); }
		finally { DBManager.close(conn, pstmt, rs); }
			
		return pList;
				
	}
	
	//해당 아이디 포인트 출력
	public int pCheck(String id) {
		String sql 				= "select * from member where m_id=?";
		Connection conn 		= null;
		PreparedStatement pstmt = null;
		ResultSet rs 			= null;
		MemberVO mVO				= null;
		int nowpoint			= 0;
		
		try {
			mVO	= new MemberVO();
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			System.out.println("bbdaopoint="+mVO.getM_point());

			if (rs.next()) {
				
				mVO.setM_point(rs.getInt("m_point"));
				System.out.println("daopoint="+mVO.getM_point());
				nowpoint = mVO.getM_point();
			}		
			
		}catch (SQLException se) {se.printStackTrace();}
		finally {DBManager.close (conn, pstmt, rs);}
	
		
		
		return nowpoint;
		
	}
	
	//포인트 변동 내용 추가하기 (if, 결제버튼을 눌렀을시 - 포인트 사용)
	//String days, int change, String id -> PointVo pVO 에 저장해서 가져온다.
	public int addPointContent (PointVO pVO ) {
		String sql 				= "insert into point (p_ref_Mid, p_days, p_changep, p_content, p_remainp) values (?, ?, ?, ?, ?)";
		Connection conn         = null;
		PreparedStatement pstmt = null;
		
		ArrayList<PointVO> pList = userPointL(pVO.getP_ref_Mid());	//해당 아이디의 포인트 리스트 가져온다
		
		int result = 0;
//		int change = pVO.getP_changep() -(pVO.getP_changep()*2);
		int remain = pList.get(0).getP_remainp() + pVO.getP_changep();
		String content = "use for payment";
		
//		pVO.setP_changep(change);
		pVO.setP_remainp(remain);
		pVO.setP_content(content);
		
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
				samePoint(pVO.getP_ref_Mid(), pVO.getP_remainp());
			
		}catch ( SQLException se ) { se.printStackTrace(); }
		finally { DBManager.close(conn, pstmt, null); }
		
		return result;
	
	}
	//포인트테이블과 멤버테이블의 정보 교환
	private void samePoint (String id, int point) {
		String sql 				= "update member set m_point=? where m_id=?";
		Connection conn         = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, point);
			pstmt.setString(2, id);
			pstmt.executeUpdate();
			
		} catch ( SQLException se ) { se.printStackTrace(); }
		finally { DBManager.close(conn, pstmt, null); }
	}
	
	//rent 테이블 arrayList
	public ArrayList<RentVO> rentL (String id){
		String sql 				= "select * from rent where r_ref_Mid=? order by r_num desc";
		Connection conn         = null;
		PreparedStatement pstmt = null;
		ResultSet rs            = null;
		
		ArrayList<RentVO> rList = new ArrayList<RentVO>() ;
		RentVO rVO				= null;
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				rVO = new RentVO();
				
				rVO.setR_ref_Mid(rs.getString("r_ref_Mid"));
				rVO.setR_name(rs.getString("r_name"));
				rVO.setR_startDays(rs.getString("r_startDays"));
				rVO.setR_endDays(rs.getString("r_endDays"));
				rVO.setR_status(rs.getInt("r_status"));
				rVO.setR_carUrl(rs.getString("r_carUrl"));
				
				rList.add(rVO);
			}
						
		}catch ( SQLException se ) { se.printStackTrace(); }
		finally { DBManager.close(conn, pstmt, rs); }
				
		return rList;
	}
	
	//rent테이블에 새로운 렌트 정보를 추가한다.
	//id, name(차종), startDays, endDays, carUrl에 대한 정보를 받아온다.
	public int addRent(RentVO rVO) {
		String sql	=	"insert into rent (r_ref_Mid, r_name, r_startdays, r_enddays, r_status, r_carurl) values ( ? ,? ,?, ?, ?, ?)";
		Connection conn         = null;
		PreparedStatement pstmt = null;
		
		int result		= 0;
		int state		= 0;
		
		rVO.setR_status(state);
		rVO.setR_carUrl(null);
		
		try {		
			
			conn  = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, rVO.getR_ref_Mid());
			pstmt.setString(2, rVO.getR_name());
			pstmt.setString(3, rVO.getR_startDays());
			pstmt.setString(4, rVO.getR_endDays());
			pstmt.setInt(5, rVO.getR_status());
			pstmt.setString(6, rVO.getR_carUrl());
		

			result = pstmt.executeUpdate();
			
			}catch ( SQLException se ) { se.printStackTrace(); }
		finally { DBManager.close(conn, pstmt, null); }
		
		return result;
		
		
	}
	
}

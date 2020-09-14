package thor.admin.car.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import thor.db.DBManager;
import thor.vo.CarVO;

public class CarDAO {
	private CarDAO() { }
	private static CarDAO inst = new CarDAO();
	public static CarDAO getInstance() {
		return inst;
	}
	
	
	public ArrayList<CarVO> manageCar() {
		String sql              = "select * from car";
		Connection conn         = null;
		PreparedStatement pstmt = null;
		ResultSet rs            = null;
		CarVO cVo               = null;
		
		ArrayList<CarVO> car = new ArrayList<CarVO>();
		
		try {
			conn  = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs    = pstmt.executeQuery();
			
			while ( rs.next() ) {
				cVo = new CarVO();
				cVo.setC_name(rs.getString("c_name"));
				cVo.setC_company(rs.getString("c_company"));
				cVo.setC_fuel(rs.getInt("c_fuel"));
				cVo.setC_cost(rs.getInt("c_cost"));
				cVo.setC_url(rs.getString("c_url"));
				car.add(cVo);
			}
			
			
		} catch ( SQLException se ) { se.printStackTrace(); }
		finally { DBManager.close(conn, pstmt, rs); }
		
		return car;
	}
	
	
	
	public int insertCar(CarVO cVo) {
		String sql 				= "insert into car values (?, ?, ?, ?, ?)";
		Connection conn 		= null;
		PreparedStatement pstmt = null;
		
		int result = 0;
		
		try {
			conn  = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cVo.getC_name());
			pstmt.setString(2, cVo.getC_company());
			pstmt.setInt(3, cVo.getC_fuel());
			pstmt.setInt(4, cVo.getC_cost());
			pstmt.setString(5, cVo.getC_url());
			result = pstmt.executeUpdate();
		} catch ( SQLException se ) { se.printStackTrace(); }
		finally { DBManager.close(conn, pstmt, null); }
		
		return result;
	}
		
	
	
	public CarVO updateCar(String c_name) {
		String sql   		    = "select * from car where c_name=?";
		Connection conn 	    = null;
		PreparedStatement pstmt = null;
		ResultSet rs 		    = null;
		CarVO cVo				= null;
		
		try {
			conn  = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, c_name);
			rs    = pstmt.executeQuery();
			
			if( rs.next() )
				cVo = new CarVO();
				cVo.setC_name(rs.getString("c_name"));
				cVo.setC_company(rs.getString("c_company"));
				cVo.setC_fuel(rs.getInt("c_fuel"));
				cVo.setC_cost(rs.getInt("c_cost"));
				cVo.setC_url(rs.getString("c_url"));
		} catch ( SQLException se ) { se.printStackTrace(); }
		finally { DBManager.close(conn, pstmt, rs); }
		
		return cVo;
	}
	

	
	public int updateOKCar(CarVO cVo, String oldName) {
		String sql              = "update car set c_name=?, c_company=?, c_fuel=?, c_cost=?, c_url=? where c_name=?";
		Connection conn         = null;
		PreparedStatement pstmt = null;
		int result              = 0;
		
		try {
			conn   = DBManager.getConnection();
			pstmt  = conn.prepareStatement(sql);
			pstmt.setString(1, cVo.getC_name());
			pstmt.setString(2, cVo.getC_company());
			pstmt.setInt(3, cVo.getC_fuel());
			pstmt.setInt(4, cVo.getC_cost());
			pstmt.setString(5, cVo.getC_url());
			pstmt.setString(6, oldName);
			result = pstmt.executeUpdate();
		} catch ( SQLException se ) { se.printStackTrace(); }
		finally { DBManager.close(conn, pstmt, null); }
		
		return result;
	}
	
	
	
	
	public int deleteCar(String c_name) {
		String sql = "delete from car where c_name=?";
		Connection conn = null;
		PreparedStatement pstmt = null; 
		int result = 0;
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, c_name);
			result = pstmt.executeUpdate();
				
		} catch ( SQLException se ) { se.printStackTrace(); }
		finally { DBManager.close(conn, pstmt, null); }
		
		return result;
	}
}


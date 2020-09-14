package thor.admin.rent.svc;

import java.util.ArrayList;

import thor.admin.rent.dao.RentDAO;
import thor.vo.RentVO;

public class ShowRentListService {
	public ArrayList<RentVO> selectAllRent(int startRow, int endRow, String searchType, String searchTerm) {
		RentDAO rentDAO        = RentDAO.getInstance();
		ArrayList<RentVO> list = new ArrayList<RentVO>();
		
		list = rentDAO.selectAllRent(startRow, endRow, searchType, searchTerm);
		return list;
	}
	
	public int pageList(String searchType, String searchTerm) {
		RentDAO rentDAO = RentDAO.getInstance();
		int result      = 0;
		
		result = rentDAO.pageList(searchType, searchTerm);
		return result;
	}
}

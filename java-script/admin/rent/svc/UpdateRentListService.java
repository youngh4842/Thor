package thor.admin.rent.svc;

import thor.admin.rent.dao.RentDAO;

public class UpdateRentListService {
	public int approveRent(int r_num) {
		RentDAO rentDAO = RentDAO.getInstance();
		int result      = 0;
		
		result = rentDAO.approveRent(r_num);
		return result;
	}
	
	public int denyRent(int r_num) {
		RentDAO rentDAO = RentDAO.getInstance();
		int result      = 0;
		
		result = rentDAO.denyRent(r_num);
		return result;
	}
}

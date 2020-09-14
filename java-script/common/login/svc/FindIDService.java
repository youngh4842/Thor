package thor.common.login.svc;

import thor.common.login.dao.LoginDAO;

public class FindIDService {
	
	public int findIDCheck(String name, String phone) {
		LoginDAO findIDDAO = LoginDAO.getInstance();
		int result 			= findIDDAO.findIDCheck(name, phone);
		
		return result;
	}
	
	public String findID (String name, String phone) {
		LoginDAO findIDDAO = LoginDAO.getInstance();
		String findid      	= findIDDAO.findID(name, phone);
		
		return findid;
	}

}

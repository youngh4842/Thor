package thor.common.login.svc;

import thor.common.login.dao.LoginDAO;

public class DuplicateService {
	public int duplicateCheck(String id) {
		LoginDAO loginDAO = LoginDAO.getInstance();
		int result        = loginDAO.duplicateCheck(id);
		
		return result;
	}
}

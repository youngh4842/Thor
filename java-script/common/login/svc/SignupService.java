package thor.common.login.svc;

import thor.common.login.dao.LoginDAO;
import thor.vo.MemberVO;



public class SignupService {
	public int signupMember(MemberVO mVo) {
		LoginDAO loginDAO = LoginDAO.getInstance();	
		int result        = loginDAO.signupMember(mVo);
		
		return result;
	}
}

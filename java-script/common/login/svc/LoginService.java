package thor.common.login.svc;

import thor.common.login.dao.LoginDAO;
import thor.vo.MemberVO;


public class LoginService {
	public int loginCheck(String id, String pw) {
		LoginDAO loginDAO = LoginDAO.getInstance();
		int result        = loginDAO.loginCheck(id, pw);
		
		return result;
	}
	
	public MemberVO login(String id, String pw) {
		LoginDAO loginDAO = LoginDAO.getInstance();
		MemberVO mVo      = loginDAO.login(id, pw);
		
		return mVo;
	}
	
	public MemberVO pwFind(String id, String name, String phone) {
		LoginDAO loginDAO = LoginDAO.getInstance();
		MemberVO mVo      = loginDAO.findPw(id, name, phone);
		
		return mVo;
	}
}

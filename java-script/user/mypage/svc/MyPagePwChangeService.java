package thor.user.mypage.svc;

import thor.user.mypage.dao.MyPageDAO;

public class MyPagePwChangeService {
	public int pwChange(String m_id, String newPw, String oldPw) {
		MyPageDAO mpDAO = MyPageDAO.getInstance();
		int result      = mpDAO.pwChange(m_id, newPw, oldPw);
		
		return result;
	}
}

package thor.user.mypage.svc;

import thor.user.mypage.dao.MyPageDAO;

public class MyPageDeleteService {
	public int deleteMember(String m_id) {
		MyPageDAO mpDAO = MyPageDAO.getInstance();
		int result      = mpDAO.deleteMember(m_id);
		
		return result;
	}
}

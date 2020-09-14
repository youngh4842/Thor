package thor.user.mypage.svc;

import thor.user.mypage.dao.MyPageDAO;
import thor.vo.MemberVO;

public class MyPageUpdateService {
	public int updateMember(MemberVO mVo) {
		MyPageDAO mpDAO = MyPageDAO.getInstance();
		int result      = mpDAO.updateMember(mVo);
		
		return result;
	}
}

package thor.user.mypage.svc;

import java.util.ArrayList;

import thor.user.mypage.dao.MyPageDAO;
import thor.vo.MemberVO;
import thor.vo.PointVO;

public class MyPageViewService {
	public MemberVO selectMember(String m_id) {
		MyPageDAO mpDAO = MyPageDAO.getInstance();
		MemberVO mVo    = mpDAO.selectMember(m_id);
		
		return mVo;
	}
	
	public ArrayList<PointVO> selectPoint(String m_id) {
		MyPageDAO mpDAO         = MyPageDAO.getInstance();
		ArrayList<PointVO> list = mpDAO.selectPoint(m_id);
		
		return list;
	}
}

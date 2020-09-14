package thor.admin.member.svc;

import java.util.ArrayList;

import thor.admin.member.dao.UserPDAO;
import thor.vo.PointVO;

public class UserPService {
	
	public ArrayList<PointVO> userPointL(String id){
		
		UserPDAO userPDAO = UserPDAO.getInstance();
		ArrayList<PointVO> pList = userPDAO.userPointL(id);
		
		return pList;
	}
	
	public int addPoint(PointVO pVO) {
		
		UserPDAO userPDAO = UserPDAO.getInstance();
		int result		= userPDAO.addPoint(pVO);
		
		return result;
	}
}

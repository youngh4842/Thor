package thor.admin.member.svc;

import java.util.ArrayList;

import thor.admin.member.dao.UserMDAO;
import thor.vo.MemberVO;

public class UserMService {
	
	public ArrayList<MemberVO> userManage(){
		
		UserMDAO userMDAO 			= UserMDAO.getInstance();
		ArrayList<MemberVO> member 	= userMDAO.userManage();
		
		return member;
		
	}
	
	public ArrayList<MemberVO> userSelect(String filter, String find){
		UserMDAO userMDAO 			= UserMDAO.getInstance();
		ArrayList<MemberVO> member 	= userMDAO.userSelect(filter, find);
		
		return member;
	}
	
	public MemberVO userCheck(String id) {
		UserMDAO userMDAO			= UserMDAO.getInstance();
		MemberVO mVO				= userMDAO.userCheck(id);
		
		return mVO;
	}

	public int userDelete(String id) {
		UserMDAO userMDAO		= UserMDAO.getInstance();
		int result				= userMDAO.userDelete(id);
		
		return result;
	}
}

package thor.admin.member.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import thor.admin.member.svc.UserMService;
import thor.admin.member.svc.UserPService;
import thor.admin.rent.action.Action;
import thor.vo.ActionForward;
import thor.vo.MemberVO;
import thor.vo.PointVO;

public class UserManageAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		//해당 아이디에 해당하는 member테이블의 정보 출력
		UserMService userMService 	= new UserMService();
		MemberVO mVO				= null;		
		
		//해당 아이디에 해당하는 point테이블의 정보 출력
		UserPService userPService 	= new UserPService();
		ArrayList<PointVO> pList	= null;	
		
		ActionForward forward 		= new ActionForward();
		
		String id = request.getParameter("m_id");
		mVO = userMService.userCheck(id);		
		pList = userPService.userPointL(id);		
		
		request.setAttribute("meminfo", mVO);
		request.setAttribute("pointinfo", pList);
		
		forward.setUrl("userManage.jsp");
		
		return forward;

	}
}
//해당 아이디애 내한 상세정보를 가져온다.
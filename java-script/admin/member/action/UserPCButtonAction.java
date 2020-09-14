package thor.admin.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import thor.admin.member.svc.UserMService;
import thor.admin.rent.action.Action;
import thor.vo.ActionForward;
import thor.vo.MemberVO;

public class UserPCButtonAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		ActionForward forward 	= new ActionForward();
	
		UserMService userMService 	= new UserMService();
		MemberVO mVO				= null;	
		
		String id = request.getParameter("m_id");	
		mVO = userMService.userCheck(id);		
		request.setAttribute("meminfo", mVO);
		
		forward.setUrl("userPChange.jsp");
		
		return forward;
	}
}

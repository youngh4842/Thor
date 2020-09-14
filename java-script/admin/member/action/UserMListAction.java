package thor.admin.member.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import thor.admin.member.svc.UserMService;
import thor.admin.rent.action.Action;
import thor.vo.ActionForward;
import thor.vo.MemberVO;

public class UserMListAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		UserMService userMService 	= new UserMService();
		ArrayList<MemberVO> member	= userMService.userManage();		
		
		ActionForward forward 		= new ActionForward();
		
		request.setAttribute("mem", member);
		
		forward.setUrl("userMList.jsp");
		
		return forward;

	}
}

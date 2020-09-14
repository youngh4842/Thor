package thor.admin.member.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import thor.admin.member.svc.UserMService;
import thor.admin.rent.action.Action;
import thor.vo.ActionForward;
import thor.vo.MemberVO;

public class UserMFilterAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		String filter 				= request.getParameter("filter");
		String find 				= request.getParameter("find");
		UserMService userMservice 	= new UserMService();
		ActionForward forward 		= new ActionForward();	
		ArrayList<MemberVO> member	= null;
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		if (filter.equals("m_id")) {
			member = userMservice.userSelect(filter, find);
			forward.setUrl("userMList.jsp");
			if ( member.size() == 0 ) {
				forward = null;
				out.println("<script>");
				out.println("alert('해당 아이디가 없습니다!');");
				out.println("history.back();");
				out.println("</script>");
			}
		}
		else if (filter.equals("m_name")) {
			member = userMservice.userSelect(filter, find);
			forward.setUrl("userMList.jsp");
			if ( member.size() == 0 ) {
				forward = null;
				out.println("<script>");
				out.println("alert('해당 이름이 없습니다!');");
				out.println("history.back();");
				out.println("</script>");
			}
		}
		else if (filter.equals("m_phone")) {
			member = userMservice.userSelect(filter, find);
			forward.setUrl("userMList.jsp");
			if ( member.size() == 0 ) {
				forward = null;
				out.println("<script>");
				out.println("alert('해당 전화번호가 없습니다!');");
				out.println("history.back();");
				out.println("</script>");
			}
		}
		else if (filter.equals("select")) {
			member = userMservice.userManage();
			forward.setUrl("userMList.jsp");
		}
			
		request.setAttribute("mem", member);
			
		return forward;

	}
}

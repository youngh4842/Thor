package thor.user.mypage.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import thor.user.mypage.svc.MyPageViewService;
import thor.user.rent.action.Action;
import thor.vo.ActionForward;
import thor.vo.PointVO;

public class MyPagePointViewAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward     = null;
		ArrayList<PointVO> list   = null;
		MyPageViewService viewSvc = new MyPageViewService();
		HttpSession session       = request.getSession();
		String m_id               = (String)session.getAttribute("m_id");
	
		list = viewSvc.selectPoint(m_id);
		
		if ( list == null ) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			out.println("<script>");
			out.println("alert('로그인이 필요합니다.');");
			out.println("location.href='login.jsp';");
			out.println("</script>");
		}
		else {
			forward = new ActionForward();
			forward.setUrl("point.jsp");
			forward.setRedirect(false);
			request.setAttribute("point", list);
		}
		
		return forward;
	}
}

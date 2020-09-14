package thor.common.login.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import thor.vo.ActionForward;

public class LogoutAction implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out       = response.getWriter();
		HttpSession session   = request.getSession();
		ActionForward forward = null;
		
		session.invalidate();
		out.println("<script>");
		out.println("alert('로그아웃 되었습니다.');");
		out.println("location.href='main.jsp'");
		out.println("</script>");
		
		
		return forward;
	}
}

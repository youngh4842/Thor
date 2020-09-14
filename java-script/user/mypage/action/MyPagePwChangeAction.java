package thor.user.mypage.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import thor.user.mypage.svc.MyPagePwChangeService;
import thor.user.rent.action.Action;
import thor.vo.ActionForward;

public class MyPagePwChangeAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward       = null;
		MyPagePwChangeService pwSvc = new MyPagePwChangeService();
		HttpSession session         = request.getSession();
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out             = response.getWriter();
		String m_id                 = (String)session.getAttribute("m_id");
		String oldPw                = request.getParameter("oldPw");
		String newPw                = request.getParameter("newPw1");
		int result                  = pwSvc.pwChange(m_id, newPw, oldPw);
		
		if ( m_id == null ) {
			out.println("<script>");
			out.println("alert('로그인이 필요합니다.');");
			out.println("location.href='login.jsp';");
			out.println("</script>");
		}
		if ( result == 1 ) {
			out.println("<script>");
			out.println("alert('비밀번호 변경이 완료되었습니다');");
			out.println("location.href='changePassword.jsp';");
			out.println("</script>");
		}
		else if ( result == -1 ) {
			out.println("<script>");
			out.println("alert('현재 비밀번호가 일치하지 않습니다');");
			out.println("history.back();");
			out.println("</script>");
		}
		else {
			out.println("<script>");
			out.println("alert('비밀번호 변경에 실패하셨습니다');");
			out.println("history.back();");
			out.println("</script>");
		}
		
		return forward;
	}
}

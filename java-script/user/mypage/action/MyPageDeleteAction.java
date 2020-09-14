package thor.user.mypage.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import thor.user.rent.action.Action;
import thor.user.mypage.svc.MyPageDeleteService;
import thor.vo.ActionForward;

public class MyPageDeleteAction implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward      = null;
		MyPageDeleteService delSvc = new MyPageDeleteService();
		HttpSession session        = request.getSession();
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out            = response.getWriter();
		String m_id                = request.getParameter("m_id");
		int result                 = 0;
		
		if ( "root".equals(m_id) ) {
			out.println("<script>");
			out.println("alert('관리자 계정은 삭제할 수 없습니다.');");
			out.println("history.back();");
			out.println("</script>");
			
			return forward;
		}
		
		session.invalidate();
		result = delSvc.deleteMember(m_id);
		if ( result == 0 ) {
			out.println("<script>");
			out.println("alert('로그인이 필요합니다.');");
			out.println("location.href='login.jsp';");
			out.println("</script>");
		}
		else {
			out.println("<script>");
			out.println("alert('회원탈퇴가 완료되었습니다.');");
			out.println("location.href='main.jsp';");
			out.println("</script>");
		}
			
		return forward;
	}
}

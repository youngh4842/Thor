package thor.common.login.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import thor.common.login.svc.LoginService;
import thor.vo.ActionForward;
import thor.vo.MemberVO;

public class LoginAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id             = request.getParameter("id");
		String pw             = request.getParameter("pw");
		LoginService loginSvc = new LoginService();
		int result            = loginSvc.loginCheck(id, pw);
		MemberVO mVo          = new MemberVO();
		ActionForward forward = null;
		
		if ( result == 1 ) {
			HttpSession session = request.getSession();
			forward             = new ActionForward();
			
			mVo = loginSvc.login(id, pw);
			session.setAttribute("m_id", mVo.getM_id());
			session.setAttribute("m_name", mVo.getM_name());
			session.setAttribute("m_email", mVo.getM_email());
			session.setAttribute("m_phone", mVo.getM_phone());
			session.setAttribute("m_admin", mVo.getM_admin());
			
			forward.setUrl("main.jsp");
			forward.setRedirect(true);
		}
		else if ( result == 0 ) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			out.println("<script>");
			out.println("alert('비밀번호가 일치하지 않습니다!');");
			out.println("history.back();");
			out.println("</script>");
		}
		else if ( result == -1 ) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			out.println("<script>");
			out.println("alert('존재하지 않는 아이디입니다!');");
			out.println("history.back();");
			out.println("</script>");
		}
		
		return forward;
	}
}

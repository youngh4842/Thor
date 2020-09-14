package thor.common.login.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import thor.common.login.svc.SignupService;
import thor.vo.ActionForward;
import thor.vo.MemberVO;

public class SignupAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		MemberVO mVo        	= new MemberVO();	
		response.setContentType("text/html; charset=UTF-8"); 
		PrintWriter out			= response.getWriter();
		
		mVo.setM_id(request.getParameter("id"));
		mVo.setM_pw(request.getParameter("pw"));
		mVo.setM_name(request.getParameter("name"));
		mVo.setM_phone(request.getParameter("phone"));
		mVo.setM_email(request.getParameter("email"));
		
		SignupService signupSvc = new SignupService();
		int result          	= signupSvc.signupMember(mVo);
		ActionForward forward	= new ActionForward();
		
		if ( result > 0 ) {
			out.println("<script>");
			out.println("alert('회원가입이 완료되었습니다!');");
			out.println("location.href='login.jsp';");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('회원가입에 실패하였습니다!');");
			out.println("history.back();");
			out.println("</script>");
		}
		
		return forward;
	}
}

package thor.common.login.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import thor.common.login.svc.DuplicateService;
import thor.vo.ActionForward;

public class DuplicateAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id					  = request.getParameter("id");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out               = response.getWriter();
		DuplicateService duplicateSvc = new DuplicateService();
		int result                    = duplicateSvc.duplicateCheck(id);
		ActionForward forward		  = new ActionForward();
	
		if(result == 1) {
			out.println("<script>");
			out.println("alert('사용 중인 아이디입니다!')");
			out.println("history.back();");
			out.println("</script>");
		}
		else {
			out.println("<script>");
			out.println("alert('사용 가능한 아이디입니다!');");
			out.println("history.back();");
			out.println("</script>");
		}
		return forward;
	}
}
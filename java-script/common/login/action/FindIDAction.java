package thor.common.login.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import thor.common.login.svc.FindIDService;
import thor.vo.ActionForward;

public class FindIDAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String name 			= request.getParameter("name");
		String phone 			= request.getParameter("phone");
		FindIDService findIDSvc = new FindIDService();
		int result 				= findIDSvc.findIDCheck(name, phone);
		String findid			= findIDSvc.findID(name, phone); 
		
		ActionForward forward = new ActionForward();
		
		if (result == 1) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			out.println("<script>");
			out.println("alert('아이디는 " + findid + " 입니다.');");
			out.println("location.href='login.jsp';");
			out.println("</script>");
			
		}
		else if(result == 0) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			out.println("<script>");
			out.println("alert('전화번호가 일치하지 않습니다.');");
			out.println("history.back();");
			out.println("</script>");
			
		}
		else if(result == -1) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			out.println("<script>");
			out.println("alert('모든 정보가 일치하지 않습니다.');");
			out.println("history.back();");
			out.println("</script>");
		}
		
		return forward;
	}
}

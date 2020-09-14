package thor.admin.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import thor.admin.member.svc.UserMService;
import thor.admin.rent.action.Action;
import thor.vo.ActionForward;

public class UserMDeleteAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
	
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		UserMService userMService 	= new UserMService();
		ActionForward forward 		= new ActionForward();			
		
		String id 		= request.getParameter("m_id");
		int result 		= userMService.userDelete(id);
		int stop		= 0;
		
		if (result == 1) {
			
			forward.setUrl("UserManage.admin");
			
			if (stop ==0 ) {
				forward =null;
				out.println("<script>");
				out.println("alert('회원이 정보가 삭제되었습니다.');");
				out.println("location.href='UserManagerList.admin';");
				out.println("</script>");
			}
	
		}
		else {
			forward.setUrl("UserManagerList.admin");
			
			if (stop == 0) {
				forward =null;
				out.println("<script>");
				out.println("alert('회원이 정보가 삭제되지 않았습니다.');");
				out.println("history.back();");
				out.println("</script>");
			}
		}
		
		return forward;
	}
}

package thor.admin.rent.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import thor.admin.rent.svc.UpdateRentListService;
import thor.vo.ActionForward;

public class DenyRentListAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward         = new ActionForward();
		UpdateRentListService rentSvc = new UpdateRentListService();
		response.setContentType("text/html; charset=UTF-8"); 
		PrintWriter out               = response.getWriter();
		String[] temp                 = request.getParameterValues("select");
		
		if ( temp == null ) {
			forward = null;
			out.println("<script>");
			out.println("alert('차량을 선택해주세요');");
			out.println("history.back();");
			out.println("</script>");
		}
		else {
			int[] r_num = new int[temp.length];
			
			for ( int i = 0; i < r_num.length; i++ ) {
				r_num[i] = Integer.parseInt(temp[i]);
				rentSvc.denyRent(r_num[i]);
			}
			
			forward.setUrl("rentlist.admin");
			forward.setRedirect(true);
		}
		
		return forward;
	}
}

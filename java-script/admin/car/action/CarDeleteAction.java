package thor.admin.car.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import thor.admin.car.svc.CarDeleteService;
import thor.admin.rent.action.Action;
import thor.vo.ActionForward;

public class CarDeleteAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		response.setContentType("text/html; charset=UTF-8"); 
		PrintWriter out			= response.getWriter();
		
		CarDeleteService CarDeleteSvc = new CarDeleteService();
		String c_name                 = request.getParameter("car");
		int result					  = CarDeleteSvc.deleteCar(c_name);
		ActionForward forward 		  = null;
		
		if( result > 0 ) {
			out.println("<script>");
			out.println("alert('삭제가 완료되었습니다.');");
			out.println("location.href='carManage.admin';");
			out.println("</script>");
		}
		else {
			out.println("<script>");
			out.println("alert('삭제에 실패하였습니다.');");
			out.println("history.back();");
			out.println("</script>");
		}
		
		
		return forward;
	}
}

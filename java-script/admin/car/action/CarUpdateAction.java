package thor.admin.car.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import thor.admin.car.svc.CarUpdateService;
import thor.admin.rent.action.Action;
import thor.vo.ActionForward;
import thor.vo.CarVO;

public class CarUpdateAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		 CarUpdateService CarUpdateSvc = new CarUpdateService();
		 String c_name                 = request.getParameter("car");
		 CarVO cVo					   = CarUpdateSvc.updateCar(c_name);
		 ActionForward forward 		   = new ActionForward();
		 
		 request.setAttribute("cVo", cVo);
		 forward.setUrl("carUpdate.jsp");
		 forward.setRedirect(false);
		
		 return forward;
	}
	
}

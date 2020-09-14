package thor.admin.car.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import thor.admin.car.svc.CarManageService;
import thor.admin.rent.action.Action;
import thor.vo.ActionForward;
import thor.vo.CarVO;

public class CarManageAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		CarManageService CarManageSvc = new CarManageService();
		ArrayList<CarVO> car          = CarManageSvc.manageCar();
		ActionForward forward         = new ActionForward();
		
		request.setAttribute("car",car);
		
		forward.setUrl("carList.jsp");
		
		return forward;		
	}
}

package thor.user.rent.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import thor.user.rent.svc.URentService;
import thor.vo.ActionForward;
import thor.vo.CarVO;

public class RentSelectAction implements Action{
	@Override
	public ActionForward execute (HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		URentService urentsvc	= new URentService();
		ArrayList<CarVO> cList	= urentsvc.rentcarList();
				
		ActionForward forward = new ActionForward();
		
		request.setAttribute("carList", cList);
		
		forward.setUrl("rentselect.jsp");
		
		return forward;
	}
}

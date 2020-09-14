package thor.user.rent.action;

import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import thor.user.rent.svc.URentService;
import thor.vo.ActionForward;
import thor.vo.CarVO;
import thor.vo.PointVO;

public class Rent_ingAction implements Action{
	@Override
	public ActionForward execute (HttpServletRequest request, HttpServletResponse response) throws Exception{
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		ActionForward forward = new ActionForward();
		URentService urentS	= new URentService();
		CarVO	carVO		= new CarVO();
		PointVO pVO			= new PointVO();
		//이전 페이지에서 가져오는거
		String car_n = request.getParameter("cSelected");
		String id		= request.getParameter("m_id");
		System.out.println(car_n + "&" + id);
		
		//빌릴 자동차에 대한 정보 가져옴
		carVO = urentS.carinfo(car_n);
		request.setAttribute ("carinfo", carVO);	
		System.out.println(carVO.getC_name());
		
		//현재 포인트 출력
		int n_point = urentS.pCheck(id);
		request.setAttribute("n_point", n_point);
		System.out.println(n_point);
		
		
		forward.setUrl("rent_ing.jsp");
		forward.setRedirect(false);
		
		return forward;
	}
}
	

package thor.user.rent.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import thor.user.rent.svc.URentService;
import thor.vo.ActionForward;
import thor.vo.CarVO;
import thor.vo.PointVO;
import thor.vo.RentVO;

public class Rent_endAction implements Action {
	@Override
	public ActionForward execute (HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session = request.getSession();
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		ActionForward forward 	= new ActionForward();
		URentService urentS		= new URentService();
		RentVO rVO				= new RentVO();
		PointVO pVO				= new PointVO();
		CarVO cVO				= new CarVO();
		
		String car_n 	= 	request.getParameter("cSelected");
		System.out.println("end = " + car_n);
		String id		= (String)session.getAttribute("m_id");
		System.out.println("end = " + id);
		String sdays 	= request.getParameter("sy")+"-"+request.getParameter("sm")+"-"+request.getParameter("sd");
		String edays 	= request.getParameter("ey")+"-"+request.getParameter("em")+"-"+request.getParameter("ed");
		System.out.println("end = " + sdays + "+" + edays);
		int changep		= Integer.parseInt(request.getParameter("changep")) ;
//		if (	(Integer.parseInt(request.getParameter("changep")) ) >0) {
//			changep = Integer.parseInt(request.getParameter("changep")) ;
//		}
		
		System.out.println("end = " + changep);
		changep = changep - (changep * 2);
		pVO.setP_days(sdays);
		pVO.setP_changep(changep);
		pVO.setP_ref_Mid(id);
		
		int p_result = urentS.addPContent(pVO);
		
		cVO = urentS.carinfo(car_n);
		
		rVO.setR_ref_Mid(id);
		rVO.setR_name(car_n);
		rVO.setR_startDays(sdays);
		rVO.setR_endDays(edays);
		rVO.setR_carUrl(cVO.getC_url());
		
		int r_result = urentS.addRent(rVO);		
		
		if ((p_result > 0) && (r_result > 0)) {
			forward	=	null;
			out.println("<script>");
			out.println("alert('렌트에 성공하였습니다.');");
			out.println("location.href='UserRentSelect.user?m_id=" + id + "';");
			out.println("</script>");
			
		}
		
		
		return forward;
	}
}

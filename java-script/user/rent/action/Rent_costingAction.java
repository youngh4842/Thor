package thor.user.rent.action;

import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import thor.user.rent.svc.URentService;
import thor.vo.ActionForward;
import thor.vo.CarVO;
import thor.vo.PointVO;

public class Rent_costingAction implements Action{
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
		HttpSession session = request.getSession();
		String id		= (String)session.getAttribute("m_id");
		System.out.println(car_n + "&" + id);
		//날짜 받아 오기
		String sdays = request.getParameter("sy")+"-"+request.getParameter("sm")+"-"+request.getParameter("sd");
		String edays = request.getParameter("ey")+"-"+request.getParameter("em")+"-"+request.getParameter("ed");
		System.out.println(sdays + edays);
		
		int days = useDays(sdays, edays);	//입력한 날짜의 수 계산
		System.out.println(days);
		
		
		//빌릴 자동차에 대한 정보 가져옴
		carVO = urentS.carinfo(car_n);
		request.setAttribute ("carinfo", carVO);		
		
		//현재 포인트 출력
		int n_point = urentS.pCheck(id);
		request.setAttribute("n_point", n_point);
		
		request.setAttribute("sy", request.getParameter("sy"));
		request.setAttribute("sm", request.getParameter("sm"));
		request.setAttribute("sd", request.getParameter("sd"));
		request.setAttribute("ey", request.getParameter("ey"));
		request.setAttribute("em", request.getParameter("em"));
		request.setAttribute("ed", request.getParameter("ed"));
		request.setAttribute("changep", request.getParameter("changep"));
		
		//포인트 바꾸기
		int changep = 0;
		if (	(Integer.parseInt(request.getParameter("changep")) ) >0) {
			changep = Integer.parseInt(request.getParameter("changep")) ;
		}
		changep = changep - (changep * 2);
		
		System.out.println("changepfin = " + changep);
		pVO.setP_days(sdays);
		pVO.setP_changep(changep);
		pVO.setP_ref_Mid(id);
		
//		int p_result = urentS.addPContent(pVO);
//		
//		if (p_result > 0) {
//			forward.setUrl("rent_ing.jsp");
//			forward.setRedirect(false);
//		}
		forward.setUrl("rent_end.jsp");
		forward.setRedirect(false);
		
		//최종 결제 금액
		System.out.println(days*carVO.getC_cost() + "ff");
		
		int cost = (days * carVO.getC_cost()) + changep ;
		request.setAttribute("fincost", cost);
		
		return forward;
	}
	
	public int useDays (String start, String end) {
		int days = 0;	//결과
		
		String s_year = start.substring(0, 4);
		String e_year = end.substring(0, 4);
		String s_month = start.substring(5, 7);
		String e_month = end.substring(5, 7);
		String s_day = start.substring(8, 10);
		String e_day = end.substring(8, 10);
		
		int s_y = Integer.parseInt(s_year);
		int e_y = Integer.parseInt(e_year);
		int s_m = Integer.parseInt(s_month);
		int e_m = Integer.parseInt(e_month);
		int s_d = Integer.parseInt(s_day);
		int e_d = Integer.parseInt(e_day);
		
		long sd, ed ;
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		
		c1.set(s_y, s_m - 1, s_d);
		c2.set(e_y, e_m - 1, e_d);
		
		sd = c1.getTime().getTime();
		ed = c2.getTime().getTime();
		
		days = (int)((ed - sd) / (1000 * 60 *60 * 24));		
		
		return days;
	}
}

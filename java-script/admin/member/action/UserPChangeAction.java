package thor.admin.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import thor.admin.member.svc.UserMService;
import thor.admin.member.svc.UserPService;
import thor.admin.rent.action.Action;
import thor.vo.ActionForward;
import thor.vo.MemberVO;
import thor.vo.PointVO;

public class UserPChangeAction implements Action{
	@Override
	public ActionForward execute (HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward 	= new ActionForward();
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		UserPService userPService	= new UserPService();
		UserMService userMService 	= new UserMService();
		MemberVO mVO				= null;	
		
	
		
		String id = request.getParameter("m_id");	
		mVO = userMService.userCheck(id);		
		request.setAttribute("meminfo", mVO);
		
		PointVO pVO = new PointVO();
		
		pVO.setP_days(request.getParameter("days"));
		pVO.setP_changep(Integer.parseInt(request.getParameter("change")));
		pVO.setP_content(request.getParameter("content"));
		pVO.setP_ref_Mid(id);
		
		int result = userPService.addPoint(pVO);
		
		if ( result >0 ) {
			forward = null;
			out.println("<script>");
			out.println("alert('정보가 추가 되었습니다.');");
			out.println("location.href='UserManage.admin?m_id=" + mVO.getM_id() + "';");
			out.println("</script>");
		}
		return forward;
	}
}

package thor.user.mypage.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import thor.user.mypage.svc.MyPageViewService;
import thor.user.rent.action.Action;
import thor.vo.ActionForward;
import thor.vo.MemberVO;

public class MyPageUpdateAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward     = null;
		MyPageViewService viewSvc = new MyPageViewService();
		HttpSession session       = request.getSession();
		String m_id               = (String)session.getAttribute("m_id");
		MemberVO mVo              = viewSvc.selectMember(m_id);
		
		if ( mVo == null ) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			out.println("<script>");
			out.println("alert('로그인이 필요합니다.');");
			out.println("location.href='login.jsp';");
			out.println("</script>");
		}
		else {
			request.setAttribute("mVo", mVo);
			forward = new ActionForward();
			forward.setUrl("changeInfo.jsp");
			forward.setRedirect(false);
		}
		
		return forward;
	}
}

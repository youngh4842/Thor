package thor.user.mypage.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import thor.user.mypage.svc.MyPageViewService;
import thor.user.rent.action.Action;
import thor.vo.ActionForward;
import thor.vo.MemberVO;

public class MyPageViewAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session       = request.getSession();
		String m_id               = (String)session.getAttribute("m_id");
		ActionForward forward     = new ActionForward();
		MyPageViewService viewSvc = new MyPageViewService();
		MemberVO mVo              = null;
		
		
		mVo = viewSvc.selectMember(m_id);
		if ( mVo == null ) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			forward         = null;
			
			out.println("<script>");
			out.println("alert('로그인이 필요합니다.');");
			out.println("location.href='login.jsp';");
			out.println("</script>");
		}
		else {
			request.setAttribute("mVo", mVo);
			forward.setUrl("checkInfo.jsp");
			forward.setRedirect(false);
		}
		
		
		return forward;
	}
}

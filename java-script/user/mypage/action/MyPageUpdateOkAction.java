package thor.user.mypage.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import thor.user.mypage.svc.MyPageUpdateService;
import thor.user.rent.action.Action;
import thor.vo.ActionForward;
import thor.vo.MemberVO;

public class MyPageUpdateOkAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward         = null;
		MyPageUpdateService updateSvc = new MyPageUpdateService();
		MemberVO mVo                  = new MemberVO();
		HttpSession session           = request.getSession();
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out               = response.getWriter();
		String m_id                   = (String)session.getAttribute("m_id");
		String m_name                 = request.getParameter("name");
		String m_email                = request.getParameter("email");
		String m_phone                = request.getParameter("phone");
		int result                    = 0;
		
		mVo.setM_id(m_id);
		mVo.setM_name(m_name);
		mVo.setM_email(m_email);
		mVo.setM_phone(m_phone);
		result = updateSvc.updateMember(mVo);
		
		if ( result == 0 ) {
			out.println("<script>");
			out.println("alert('수정에 실패하셨습니다');");
			out.println("history.back();");
			out.println("</script>");
		}
		else {
			out.println("<script>");
			out.println("alert('수정이 완료되었습니다');");
			out.println("location.href='mypage_update.user';");
			out.println("</script>");
		}
		
		return forward;
	}
}

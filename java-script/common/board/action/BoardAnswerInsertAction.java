package thor.common.board.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import thor.common.board.svc.BoardInsertService;
import thor.common.login.action.Action;
import thor.vo.ActionForward;
import thor.vo.AnswerVO;

public class BoardAnswerInsertAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward     = null;
		BoardInsertService insSvc = new BoardInsertService();
		HttpSession session       = request.getSession();
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out           = response.getWriter();
		AnswerVO aVo              = new AnswerVO();
		int a_qnum                = Integer.parseInt(request.getParameter("a_qnum"));
		String title              = request.getParameter("title");
		String content            = request.getParameter("content");
		String id                 = (String)session.getAttribute("m_id");
		int result                = 0;
		
		if ( id == null ) {
			out.println("<script>");
			out.println("alert('로그인이 필요합니다.');");
			out.println("location.href='login.jsp';");
			out.println("</script>");
			
			return forward;
		}
		
		aVo.setA_qnum(a_qnum);
		aVo.setA_title(title);
		aVo.setA_content(content);
		aVo.setA_name("관리자");
		result = insSvc.insertAnswer(aVo);
		
		if ( result == 0 ) {
			out.println("<script>");
			out.println("alert('등록에 실패하셨습니다');");
			out.println("history.back();");
			out.println("</script>");
		}
		else {
			out.println("<script>");
			out.println("alert('등록이 완료되었습니다');");
			out.println("location.href='boardlist.common';");
			out.println("</script>");
		}
			
		return forward;
	}
}

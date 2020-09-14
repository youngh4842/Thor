package thor.common.board.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import thor.common.board.svc.BoardInsertService;
import thor.common.login.action.Action;
import thor.vo.ActionForward;
import thor.vo.QuestionVO;

public class BoardQuestionInsertAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward     = null;
		BoardInsertService insSvc = new BoardInsertService();
		HttpSession session       = request.getSession();
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out           = response.getWriter();
		QuestionVO qVo            = new QuestionVO();
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
		
		
		qVo.setQ_title(title);
		qVo.setQ_content(content);
		qVo.setQ_ref_Mid(id);
		result = insSvc.insertQuestion(qVo);
		
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

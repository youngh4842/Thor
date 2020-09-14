package thor.common.board.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import thor.common.board.svc.BoardUpdateService;
import thor.common.login.action.Action;
import thor.vo.ActionForward;
import thor.vo.QuestionVO;

public class BoardQuestionUpdateOkAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward        = null;
		BoardUpdateService updateSvc = new BoardUpdateService();
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out              = response.getWriter();
		QuestionVO qVo               = new QuestionVO();
		int result                   = 0;
		
		qVo.setQ_num(Integer.parseInt(request.getParameter("q_num")));
		qVo.setQ_title(request.getParameter("title"));
		qVo.setQ_content(request.getParameter("content"));
		result = updateSvc.updateQuestion(qVo);
		
		if ( result == 0 ) {
			out.println("<script>");
			out.println("alert('수정에 실패하셨습니다.');");
			out.println("history.back();");
			out.println("</script>");
		}
		else {
			out.println("<script>");
			out.println("alert('수정되었습니다.');");
			out.println("location.href='boardlist.common';");
			out.println("</script>");
		}
		
		return forward;
	}
}

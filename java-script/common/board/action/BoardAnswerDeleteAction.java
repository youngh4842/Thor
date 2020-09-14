package thor.common.board.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import thor.common.board.svc.BoardDeleteService;
import thor.common.login.action.Action;
import thor.vo.ActionForward;

public class BoardAnswerDeleteAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward     = null;
		BoardDeleteService delSvc = new BoardDeleteService();
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out           = response.getWriter();
		int a_num                 = Integer.parseInt(request.getParameter("a_num"));
		int result                = 0;
		
		result = delSvc.deleteAnswer(a_num);
		if ( result == 0 ) {
			out.println("<script>");
			out.println("alert('삭제에 실패하셨습니다');");
			out.println("history.back();");
			out.println("</script>");
		}
		else {
			out.println("<script>");
			out.println("alert('삭제되었습니다.');");
			out.println("location.href='boardlist.common';");
			out.println("</script>");
		}
		
		return forward;
	}
}

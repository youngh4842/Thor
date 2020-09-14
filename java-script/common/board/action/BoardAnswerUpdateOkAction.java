package thor.common.board.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import thor.common.board.svc.BoardUpdateService;
import thor.common.login.action.Action;
import thor.vo.ActionForward;
import thor.vo.AnswerVO;

public class BoardAnswerUpdateOkAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward        = null;
		BoardUpdateService updateSvc = new BoardUpdateService();
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out              = response.getWriter();
		AnswerVO aVo                 = new AnswerVO();
		int result                   = 0;
		
		aVo.setA_num(Integer.parseInt(request.getParameter("a_num")));
		aVo.setA_title(request.getParameter("title"));
		aVo.setA_content(request.getParameter("content"));
		result = updateSvc.updateAnswer(aVo);
		
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

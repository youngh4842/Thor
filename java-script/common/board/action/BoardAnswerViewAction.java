package thor.common.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import thor.common.board.svc.BoardViewService;
import thor.common.login.action.Action;
import thor.vo.ActionForward;
import thor.vo.AnswerVO;

public class BoardAnswerViewAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward    = new ActionForward();
		BoardViewService viewSvc = new BoardViewService();
		AnswerVO aVo             = new AnswerVO();
		int a_num                = Integer.parseInt(request.getParameter("a_num"));
		
		aVo = viewSvc.viewAnswer(a_num);
		request.setAttribute("aVo", aVo);
		
		forward.setUrl("boardView.jsp");
		forward.setRedirect(false);
		return forward;
	}
}

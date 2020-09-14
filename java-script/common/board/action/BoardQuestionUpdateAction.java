package thor.common.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import thor.common.board.svc.BoardViewService;
import thor.common.login.action.Action;
import thor.vo.ActionForward;
import thor.vo.QuestionVO;

public class BoardQuestionUpdateAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward    = new ActionForward();
		BoardViewService viewSvc = new BoardViewService();
		QuestionVO qVo           = new QuestionVO();
		int q_num                = Integer.parseInt(request.getParameter("q_num"));
		
		qVo = viewSvc.viewQuestion(q_num);
		request.setAttribute("qVo", qVo);
		
		forward.setUrl("boardQUpdate.jsp");
		forward.setRedirect(false);
		return forward;
	}
}

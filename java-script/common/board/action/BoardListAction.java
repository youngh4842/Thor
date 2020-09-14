package thor.common.board.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import thor.common.board.svc.BoardListViewService;
import thor.common.login.action.Action;
import thor.vo.ActionForward;
import thor.vo.AnswerVO;
import thor.vo.QuestionVO;

public class BoardListAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward        = new ActionForward();
		BoardListViewService viewSvc = new BoardListViewService();
		ArrayList<QuestionVO> qList  = null;
		ArrayList<AnswerVO> aList    = null;
		String searchType            = request.getParameter("searchType");
		String searchTerm            = request.getParameter("searchTerm");
		
		
		// 페이지 리스트
		// 전체 행의 개수
		int totalRow     = viewSvc.countQuestion(searchType, searchTerm) + viewSvc.countAnswer(searchType, searchTerm);
		int rowPerPage   = 15;												// 페이지 당 행의 개수
		int totalPage    = (totalRow - 1) / (rowPerPage + 1);				// 전체 페이지 개수
		if ( totalPage == 0 )
			totalPage = 1;
		int cPage        = 1;												// 현재 페이지 번호
		String chPage    = request.getParameter("page");					// 바뀔 페이지 번호
		if ( chPage != null )
			cPage = Integer.parseInt(chPage);
		int pagePerGroup = 10;												// 그룹 당 보여줄 페이지 개수
		int startPage    = (cPage - 1) / pagePerGroup * pagePerGroup + 1;	// 시작 페이지 번호
		int endPage      = startPage + pagePerGroup - 1;					// 끝 페이지 번호
		if ( endPage > totalPage )											// 만약 endPage가 totalPage보다 크다면 endPage에 totalPage 대입
			endPage = totalPage;
		int startRow     = (cPage - 1) * rowPerPage + 1;					// 시작 행 번호
		int endRow       = startRow + rowPerPage - 1;						// 끝 행 번호
		
		qList = viewSvc.selectQuestion(startRow, endRow, searchType, searchTerm);
		aList = viewSvc.selectAnswer();
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("page", cPage);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("qList", qList);
		request.setAttribute("aList", aList);
		
		forward.setUrl("boardList.jsp");
		forward.setRedirect(false);
		return forward;
	}
}

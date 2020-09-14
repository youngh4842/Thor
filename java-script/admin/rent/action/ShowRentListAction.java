package thor.admin.rent.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import thor.admin.rent.svc.ShowRentListService;
import thor.vo.ActionForward;
import thor.vo.RentVO;

public class ShowRentListAction implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward       = new ActionForward();
		ArrayList<RentVO> list      = new ArrayList<RentVO>();
		ShowRentListService rentSvc = new ShowRentListService();
		String searchType           = request.getParameter("searchType");
		String searchTerm           = request.getParameter("searchTerm");
		

		if ( "r_status".equals(searchType) && "승인".equals(searchTerm) )			// 검색타입 == 상태 and 검색어 == 승인일 때 
			searchTerm = "1";
		else if ( "r_status".equals(searchType) && "미승인".equals(searchTerm) )	// 검색타입 == 상태 and 검색어 == 미승인일 때
			searchTerm = "0";
		else if ( "r_status".equals(searchType) && "사용 중".equals(searchTerm) )	// 검색타입 == 상태 and 검색어 == 사용 중일 때
			searchTerm = "-1";

		// 검색타입이 목록("")이 아닐 때 and 검색어가 공백일 때
		// 경고창 출력 후 이전 페이지로 돌려보냄
		if ( !"".equals(searchType) && "".equals(searchTerm) ) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			forward         = null;
			
			out.println("<script>");
			out.println("alert('검색어를 입력해주세요');");
			out.println("history.back();");
			out.println("</script>");
			return forward;
		}
			

		// 페이지 리스트
		int totalRow     = rentSvc.pageList(searchType, searchTerm);		// 전체 행의 개수
		int rowPerPage   = 5;												// 페이지 당 행의 개수
		int totalPage    = (totalRow - 1) / (rowPerPage + 1);				// 전체 페이지 개수
		if ( totalPage == 0 )
			totalPage = 1;
		int cPage        = 1;												// 현재 페이지 번호
		String chPage    = request.getParameter("page");					// 바뀔 페이지 번호
		if ( chPage != null )
			cPage = Integer.parseInt(chPage);
		int pagePerGroup = 5;												// 그룹 당 보여줄 페이지 개수
		int startPage    = (cPage - 1) / pagePerGroup * pagePerGroup + 1;	// 시작 페이지 번호
		int endPage      = startPage + pagePerGroup - 1;					// 끝 페이지 번호
		if ( endPage > totalPage )											// 만약 endPage가 totalPage보다 크다면 endPage에 totalPage 대입
			endPage = totalPage;
		int startRow     = (cPage - 1) * rowPerPage + 1;					// 시작 행 번호
		int endRow       = startRow + rowPerPage - 1;						// 끝 행 번호
		
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("page", cPage);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		
		list = rentSvc.selectAllRent(startRow, endRow, searchType, searchTerm);
		request.setAttribute("rentList", list);
		
		forward.setUrl("rentRequestList.jsp");
		forward.setRedirect(false);
		
		return forward;
	}
}

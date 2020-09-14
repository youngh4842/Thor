package thor.common.board.svc;

import thor.common.board.dao.BoardDAO;

public class BoardDeleteService {
	public int deleteQuestion(int q_num) {
		BoardDAO bDAO = BoardDAO.getInstance();
		int result    = bDAO.deleteQuestion(q_num);
		
		return result;
	}
	
	public int deleteAnswer(int a_num) {
		BoardDAO bDAO = BoardDAO.getInstance();
		int result    = bDAO.deleteAnswer(a_num);
		
		return result;
	}
}

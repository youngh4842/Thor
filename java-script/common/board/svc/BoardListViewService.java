package thor.common.board.svc;

import java.util.ArrayList;

import thor.common.board.dao.BoardDAO;
import thor.vo.AnswerVO;
import thor.vo.QuestionVO;

public class BoardListViewService {
	public ArrayList<QuestionVO> selectQuestion(int startRow, int endRow, String searchType, String searchTerm) {
		BoardDAO bDAO              = BoardDAO.getInstance();
		ArrayList<QuestionVO> list = bDAO.selectQuestion(startRow, endRow, searchType, searchTerm);
		
		return list;
	}
	
	public ArrayList<AnswerVO> selectAnswer() {
		BoardDAO bDAO            = BoardDAO.getInstance();
		ArrayList<AnswerVO> list = bDAO.selectAnswer();
		
		return list;
	}
	
	public int countQuestion(String searchType, String searchTerm) {
		BoardDAO bDAO            = BoardDAO.getInstance();
		int result               = bDAO.countQuestion(searchType, searchTerm);
		
		return result;
	}
	
	public int countAnswer(String searchType, String searchTerm) {
		BoardDAO bDAO            = BoardDAO.getInstance();
		int result               = bDAO.countAnswer(searchType, searchTerm);
		
		return result; 
	}
}

package thor.common.board.svc;

import thor.common.board.dao.BoardDAO;
import thor.vo.AnswerVO;
import thor.vo.QuestionVO;

public class BoardInsertService {
	public int insertQuestion(QuestionVO qVo) {
		BoardDAO bDAO = BoardDAO.getInstance();
		int result    = bDAO.insertQuestion(qVo);
		
		return result;
	}
	
	public int insertAnswer(AnswerVO aVo) {
		BoardDAO bDAO = BoardDAO.getInstance();
		int result    = bDAO.insertAnswer(aVo);
		
		return result;
	}
}

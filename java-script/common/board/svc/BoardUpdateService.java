package thor.common.board.svc;

import thor.common.board.dao.BoardDAO;
import thor.vo.AnswerVO;
import thor.vo.QuestionVO;

public class BoardUpdateService {
	public int updateQuestion(QuestionVO qVo) {
		BoardDAO bDAO = BoardDAO.getInstance();
		int result    = bDAO.updateQuestion(qVo);
		
		return result;
	}
	
	public int updateAnswer(AnswerVO aVo) {
		BoardDAO bDAO = BoardDAO.getInstance();
		int result    = bDAO.updateAnswer(aVo);
		
		return result;
	}
}

package thor.common.board.svc;

import thor.common.board.dao.BoardDAO;
import thor.vo.AnswerVO;
import thor.vo.QuestionVO;

public class BoardViewService {
	public QuestionVO viewQuestion(int q_num) {
		BoardDAO bDAO  = BoardDAO.getInstance();
		QuestionVO qVo = bDAO.viewQuestion(q_num);
		
		return qVo;
	}
	
	public AnswerVO viewAnswer(int a_num) {
		BoardDAO bDAO  = BoardDAO.getInstance();
		AnswerVO aVo = bDAO.viewAnswer(a_num);
		
		return aVo;
	}
}

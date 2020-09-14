package thor.common.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import thor.common.board.action.*;
import thor.common.login.action.*;
import thor.vo.ActionForward;

@WebServlet("*.common")
public class CommonFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CommonFrontController() {
        super();
    }

    
    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String requestURI     = request.getRequestURI();
    	String contextPath    = request.getContextPath();
    	String command        = requestURI.substring(contextPath.length());
    	Action action         = null;
    	ActionForward forward = null;
    	
    	if ( command.equals("/login.common") ) {
    		action = new LoginAction();
    		try {
    			forward = action.execute(request, response);
    		} catch ( Exception e ) { e.printStackTrace(); }
    	}
    	else if ( command.equals("/findpw.common") ) {
    		action = new FindPwAction();
    		try {
    			forward = action.execute(request, response);
    		} catch ( Exception e ) { e.printStackTrace(); }
    	}
    	else if ( command.equals("/logout.common") ) {
    		action = new LogoutAction();
    		try {
    			forward = action.execute(request, response);
    		} catch ( Exception e ) { e.printStackTrace(); }
    	}
    	else if ( command.equals("/signup.common") ) {
    		action = new SignupAction();
    		try {
    			forward = action.execute(request, response);
    		} catch ( Exception e ) { e.printStackTrace(); }
    	}
    	else if ( command.equals("/duplicate.common") ) {
    		action = new DuplicateAction();
    		try {
    			forward = action.execute(request, response);
    		} catch ( Exception e ) { e.printStackTrace(); }
    	}
    	else if ( command.equals("/findID.common") ) {
    		action = new FindIDAction();
    		try {
    			forward = action.execute(request, response);
    		} catch ( Exception e ) { e.printStackTrace(); }
    	}
    	else if ( command.equals("/boardlist.common") ) {
    		action = new BoardListAction();
    		try {
    			forward = action.execute(request, response);
    		} catch ( Exception e ) { e.printStackTrace(); }
    	}
    	else if ( command.equals("/boardQWrite.common") ) {
    		action = new BoardQuestionInsertAction();
    		try {
    			forward = action.execute(request, response);
    		} catch ( Exception e ) { e.printStackTrace(); }
    	}
    	else if ( command.equals("/boardAWrite.common") ) {
    		action = new BoardAnswerInsertAction();
    		try {
    			forward = action.execute(request, response);
    		} catch ( Exception e ) { e.printStackTrace(); }
    	}
    	else if ( command.equals("/boardQview.common") ) {
    		action = new BoardQuestionViewAction();
    		try {
    			forward = action.execute(request, response);
    		} catch ( Exception e ) { e.printStackTrace(); }
    	}
    	else if ( command.equals("/boardAview.common") ) {
    		action = new BoardAnswerViewAction();
    		try {
    			forward = action.execute(request, response);
    		} catch ( Exception e ) { e.printStackTrace(); }
    	}
    	else if ( command.equals("/boardQUpdate.common") ) {
    		action = new BoardQuestionUpdateAction();
    		try {
    			forward = action.execute(request, response);
    		} catch ( Exception e ) { e.printStackTrace(); }
    	}
    	else if ( command.equals("/boardAUpdate.common") ) {
    		action = new BoardAnswerUpdateAction();
    		try {
    			forward = action.execute(request, response);
    		} catch ( Exception e ) { e.printStackTrace(); }
    	}
    	else if ( command.equals("/boardQUpdateOk.common") ) {
    		action = new BoardQuestionUpdateOkAction();
    		try {
    			forward = action.execute(request, response);
    		} catch ( Exception e ) { e.printStackTrace(); }
    	}
    	else if ( command.equals("/boardAUpdateOk.common") ) {
    		action = new BoardAnswerUpdateOkAction();
    		try {
    			forward = action.execute(request, response);
    		} catch ( Exception e ) { e.printStackTrace(); }
    	}
    	else if ( command.equals("/boardQDelete.common") ) {
    		action = new BoardQuestionDeleteAction();
    		try {
    			forward = action.execute(request, response);
    		} catch ( Exception e ) { e.printStackTrace(); }
    	}
    	else if ( command.equals("/boardADelete.common") ) {
    		action = new BoardAnswerDeleteAction();
    		try {
    			forward = action.execute(request, response);
    		} catch ( Exception e ) { e.printStackTrace(); }
    	}
    	
    	
    	if ( forward != null ) {
    		if ( forward.isRedirect() ) {
    			response.sendRedirect(forward.getUrl());
    		} else {
    			RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getUrl());
    			dispatcher.forward(request, response);
    		}
    	}
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doProcess(request, response);
	}

}

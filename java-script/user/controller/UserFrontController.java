package thor.user.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import thor.user.mypage.action.MyPageDeleteAction;
import thor.user.mypage.action.MyPagePointViewAction;
import thor.user.mypage.action.MyPagePwChangeAction;
import thor.user.mypage.action.MyPageUpdateAction;
import thor.user.mypage.action.MyPageUpdateOkAction;
import thor.user.mypage.action.MyPageViewAction;
import thor.user.rent.action.Action;
import thor.vo.ActionForward;

@WebServlet("*.user")
public class UserFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserFrontController() {
        super();
    }

    
    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String requestURI     = request.getRequestURI();
    	String contextPath    = request.getContextPath();
    	String command        = requestURI.substring(contextPath.length());
    	Action action         = null;
    	ActionForward forward = null;
    	
    	if ( command.equals("/mypage_view.user") ) {
    		action = new MyPageViewAction();
    		try {
    			forward = action.execute(request, response);
    		} catch ( Exception e ) { e.printStackTrace(); }
    	}
    	else if ( command.equals("/mypage_delete.user") ) {
    		action = new MyPageDeleteAction();
    		try {
    			forward = action.execute(request, response);
    		} catch ( Exception e ) { e.printStackTrace(); }
    	}
    	else if ( command.equals("/mypage_update.user") ) {
    		action = new MyPageUpdateAction();
    		try {
    			forward = action.execute(request, response);
    		} catch ( Exception e ) { e.printStackTrace(); }
    	}
    	else if ( command.equals("/mypage_update_ok.user") ) {
    		action = new MyPageUpdateOkAction();
    		try {
    			forward = action.execute(request, response);
    		} catch ( Exception e ) { e.printStackTrace(); }
    	}
    	else if ( command.equals("/mypage_changepw.user") ) {
    		action = new MyPagePwChangeAction();
    		try {
    			forward = action.execute(request, response);
    		} catch ( Exception e ) { e.printStackTrace(); }
    	}
    	else if ( command.equals("/mypage_point.user") ) {
    		action = new MyPagePointViewAction();
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

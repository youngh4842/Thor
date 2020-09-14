package thor.admin.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import thor.admin.car.action.CarDeleteAction;
import thor.admin.car.action.CarInsertAction;
import thor.admin.car.action.CarManageAction;
import thor.admin.car.action.CarUpdateAction;
import thor.admin.car.action.CarUpdateOKAction;
import thor.admin.member.action.UserMDeleteAction;
import thor.admin.member.action.UserMFilterAction;
import thor.admin.member.action.UserMListAction;
import thor.admin.member.action.UserManageAction;
import thor.admin.member.action.UserPCButtonAction;
import thor.admin.member.action.UserPChangeAction;
import thor.admin.rent.action.Action;
import thor.admin.rent.action.ApproveRentListAction;
import thor.admin.rent.action.DenyRentListAction;
import thor.admin.rent.action.ShowRentListAction;
import thor.vo.ActionForward;

@WebServlet("*.admin")
public class AdminFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminFrontController() {
        super();
    }

    
    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String requestURI     = request.getRequestURI();
    	String contextPath    = request.getContextPath();
    	String command        = requestURI.substring(contextPath.length());
    	Action action         = null;
    	ActionForward forward = null;
    	
    	if ( command.equals("/rentlist.admin") ) {
    		action = new ShowRentListAction();
    		try {
    			forward = action.execute(request, response);
    		} catch ( Exception e ) { e.printStackTrace(); }
    	}
    	else if ( command.equals("/approverent.admin") ) {
    		action = new ApproveRentListAction();
    		try {
    			forward = action.execute(request, response);
    		} catch ( Exception e ) { e.printStackTrace(); }
    	}
    	else if ( command.equals("/denyrent.admin") ) {
    		action = new DenyRentListAction();
    		try {
    			forward = action.execute(request, response);
    		} catch ( Exception e ) { e.printStackTrace(); }
    	}
    	else if ( command.equals("/UserManagerList.admin") ) {
    		action = new UserMListAction();
    		try {
    			forward = action.execute(request, response);
    		} catch ( Exception e ) { e.printStackTrace(); }
    	}
    	else if ( command.equals("/UserManage.admin") ) {
    		action = new UserManageAction();
    		try {
    			forward = action.execute(request, response);
    		} catch ( Exception e ) { e.printStackTrace(); }
    	}
    	else if ( command.equals("/Searchfilter.admin") ) {
    		action = new UserMFilterAction();
    		try {
    			forward = action.execute(request, response);
    		} catch ( Exception e ) { e.printStackTrace(); }
    	}
    	else if ( command.equals("/UserDelete.admin") ) {
    		action = new UserMDeleteAction();
    		try {
    			forward = action.execute(request, response);
    		} catch ( Exception e ) { e.printStackTrace(); }
    	}
    	
    	else if ( command.equals("/UserPointChange.admin") ) {
    		action = new UserPChangeAction();
    		try {
    			forward = action.execute(request, response);
    		} catch ( Exception e ) { e.printStackTrace(); }
    	}
    	else if ( command.equals("/UserPCButton.admin") ) {
    		action = new UserPCButtonAction();
    		try {
    			forward = action.execute(request, response);
    		} catch ( Exception e ) { e.printStackTrace(); }
    	}
    	else if ( command.equals("/carManage.admin") ) {
    		action = new CarManageAction();
    		try {
    			forward = action.execute(request, response);
    		} catch ( Exception e ) { e.printStackTrace(); }
    	}
    	else if ( command.equals("/carInsert.admin") ) {
    		action = new CarInsertAction();
    		try {
    			forward = action.execute(request, response);
    		} catch ( Exception e ) { e.printStackTrace(); }
    	}
    	else if ( command.equals("/carUpdate.admin") ) {
    		action = new CarUpdateAction();
    		try {
    			forward = action.execute(request, response);
    		} catch ( Exception e ) { e.printStackTrace(); }
    	}
    	else if ( command.equals("/carUpdateOK.admin") ) {
    		action = new CarUpdateOKAction();
    		try {
    			forward = action.execute(request, response);
    		} catch ( Exception e ) { e.printStackTrace(); }
    	}
    	else if ( command.equals("/carDelete.admin") ) {
    		action = new CarDeleteAction();
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

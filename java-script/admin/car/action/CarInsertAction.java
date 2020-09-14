package thor.admin.car.action;

import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import thor.admin.car.svc.CarInsertService;
import thor.admin.rent.action.Action;
import thor.vo.ActionForward;
import thor.vo.CarVO;

public class CarInsertAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		response.setContentType("text/html; charset=UTF-8"); 
		PrintWriter out			= response.getWriter();
	
		String realFolder       = "";
	
		String saveFolder = "/upload";
		String encType    = "UTF-8";
		int maxSize       = 5*1024*1024; 
		
		ServletContext context = request.getServletContext();
		realFolder             = context.getRealPath(saveFolder);
		
		MultipartRequest multi = new MultipartRequest(request,
													  realFolder,
													  maxSize,
													  encType,
													  new DefaultFileRenamePolicy());
		String url = multi.getFilesystemName("url");
		
		CarVO cVo = new CarVO();
		
		cVo.setC_name(multi.getParameter("name"));
		cVo.setC_company(multi.getParameter("company"));
		cVo.setC_cost(Integer.parseInt(multi.getParameter("cost")));
		cVo.setC_fuel(Integer.parseInt(multi.getParameter("fuel")));
		cVo.setC_url(url);
			
		CarInsertService insertSvc = new CarInsertService();
		int result 				   = insertSvc.insertCar(cVo);
		ActionForward forward	   = null;
		
		
		if ( result > 0 ) {
			out.println("<script>");
			out.println("alert('차량 등록이 완료 되었습니다.');");
			out.println("location.href='carManage.admin';");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('차량 등록에 실패 하였습니다.');");
			out.println("history.back();");
			out.println("</script>");
		}
		
		
		return forward;
	}
}

package thor.common.login.action;

import java.io.PrintWriter;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import thor.common.login.svc.LoginService;
import thor.vo.ActionForward;
import thor.vo.MemberVO;

public class FindPwAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id             = request.getParameter("id");
		String name           = request.getParameter("name");
		String phone          = request.getParameter("phone1") + request.getParameter("phone2") + request.getParameter("phone3");
		LoginService loginSvc = new LoginService();
		MemberVO mVo          = loginSvc.pwFind(id, name, phone); 
		ActionForward forward = new ActionForward();
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out       = response.getWriter();
		
		if ( mVo != null ) {
			String sender   = "kim950210";									// 보내는 이
			String receiver = mVo.getM_email();								// 받는 이
			String subject  = "비밀번호 안내 메일입니다.";							// 제목
			String content  = "귀하의 비밀번호는 " + mVo.getM_pw() + " 입니다.";	// 본문
			
			try {
				Properties props = System.getProperties();
				props.put("mail.smtp.starttls.enable", "true");
				props.put("mail.smtp.host", "smtp.gmail.com");
				props.put("mail.smtp.auth", "true");
				props.put("mail.smtp.port", "587");		// SMTP 포트 번호
				Authenticator auth = new Authenticator() {
					public PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(sender, "egsijidayjpxrejm");	// Google 앱 비밀번호
					}
				};
				Session s                = Session.getDefaultInstance(props, auth);
				Message message          = new MimeMessage(s);
				Address sender_address   = new InternetAddress(sender);
				Address receiver_address = new InternetAddress(receiver);
				message.setHeader("content-type", "text/html; charset=UTF-8");
				message.setFrom(sender_address);
				message.addRecipient(Message.RecipientType.TO, receiver_address);
				message.setSubject(subject);
				message.setContent(content, "text/html; charset=UTF-8");
				message.setSentDate(new java.util.Date());
				Transport.send(message);
				
				forward.setUrl("login.jsp");
				forward.setRedirect(true);
				
			} catch ( Exception e ) {
				out.println("<script>");
				out.println("alert('메일 발송에 실패하였습니다.');");
				out.println("history.back();");
				out.println("</script>");
				e.printStackTrace();
			}
		} else {
			out.println("<script>");
			out.println("alert('계정 정보가 일치하지 않습니다!');");
			out.println("history.back();");
			out.println("</script>");
		}
		
		return forward;
	}
}

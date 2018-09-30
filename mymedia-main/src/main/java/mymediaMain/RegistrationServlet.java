package mymediaMain;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import org.mymedia.database.entities.User;
import org.mymedia.database.dao.UserDataBase;

@Slf4j
@WebServlet(urlPatterns = "/registration")
public class RegistrationServlet extends HttpServlet{
	private static final UserDataBase USER_DB = UserDataBase.getDataBase();
	@Override
	public void init() {
		   
		   try {
	            USER_DB.connectDB();
	            log.trace("Connect to database!");    
	            
	        } catch (Exception e) {
	        	log.error("" + e);
	        }
	}
		@Override
		public void doGet(HttpServletRequest request, HttpServletResponse response)
	      throws ServletException, IOException{
			
		request.getRequestDispatcher("WEB-INF/views/registration.jsp").forward(request, response);
		
	   }
	
	   @Override
	   protected void doPost(HttpServletRequest request, HttpServletResponse response)
			      throws ServletException, IOException {
		   User user = USER_DB.getUser(request.getParameter("username"));
		   if(user != null) {
			   request.getRequestDispatcher("WEB-INF/views/registration.jsp").forward(request, response);
		   }else {
			   user = new User(request.getParameter("username"),
					           request.getParameter("password"),
					           request.getParameter("email"),
					           request.getParameter("fullname"));
			   try {
				   USER_DB.save(user);
				   request.getRequestDispatcher("WEB-INF/views/login.jsp").forward(request, response);
			   } catch (IllegalStateException | IllegalArgumentException e) {
					log.error("" + e);
				} catch (Exception e) {
					log.error("" + e);
				}    
		   }
		   
		    
		   
	   }
	   
	   @Override
	   public void destroy() {
           USER_DB.disconnectDB();
	   }


}

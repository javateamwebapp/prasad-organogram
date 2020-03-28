package organogram.hhcl.service;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import organogram.hhcl.db.ConnectionpoolFilter;
import organogram.hhcl.db.GetDbData;

/**
 * Servlet implementation class WelcomeServlet
 */
public class WelcomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	final Logger logger = Logger.getLogger(WelcomeServlet.class);


	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WelcomeServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
		String className = this.getClass().getName();
		session.setAttribute("HttpServletRequest", "HttpServletRequest");
        RequestDispatcher rd =request.getRequestDispatcher("welcome.jsp");
		ResultSet Res=null;
		long start = System.currentTimeMillis();

		//* Bundile Data fetching //

		ServletContext context=session.getServletContext();
		String Message=null;
		ResourceBundle bundle=null;
    	try{
    		//ctx=arg0.getSession().getServletContext();
    		System.out.println("className" +className);
			bundle = ResourceBundle.getBundle("MessageBundle");
			Message=bundle.getString("WelcomeServlet.Fy_Year");
		}catch(Exception err) {
			err.printStackTrace();
		}
   	
		System.out.println("Bundle Data :: "+Message);
		
		logger.info("SampleConnection - welcome " +Message );


		Connection con=null;
		try {
			try {
				con = ConnectionpoolFilter.Call();
			} catch (PropertyVetoException e) {
				e.printStackTrace();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			logger.error(e1.getLocalizedMessage());
			e1.printStackTrace();
		}

		request.setAttribute("message", "Unable to Connect Database  ...!");
		try {
			String SQL = "SELECT 1 from dual";
			Res=(ResultSet)GetDbData.FetchData(SQL, "WelcomeServlet", Res ,con);
			if(Res.next()){

				System.out.println("Fetch Data --> ::" +Res.getString(1));
				 request.setAttribute("message", "Database connected successfully ...!");

			}
		}catch(Exception err){
			System.out.println("Error At WelcomeServlet" +err);
			logger.error(err);

		}finally{

			try {
				ConnectionpoolFilter.close(con, Res,"WelcomeServlet",false);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	


		long end = System.currentTimeMillis();
		float sec = (end - start) / 1000F;
	    logger.info("WelcomeServlet execution time in Seconds ::"+ sec);
	    request.setAttribute("Excecutiontime", sec +" Sec");

	    rd.forward(request, response);
		//doGet(request, response);
	}

}

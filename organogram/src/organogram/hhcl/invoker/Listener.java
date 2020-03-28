package organogram.hhcl.invoker;
import java.util.ResourceBundle;

import javax.servlet.*;
 
public class Listener implements ServletContextListener {
 
	
	ServletContext ctx=null;  
    public void contextDestroyed(ServletContextEvent arg0) {
        System.out.println(" Organogram Server stopped");
    }
 
    public void contextInitialized(ServletContextEvent arg0) {
    	
    	
    	
        System.out.println("Organogram Server started");       
    }
}
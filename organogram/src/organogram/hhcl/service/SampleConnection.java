package organogram.hhcl.service;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import organogram.hhcl.db.*;
/*
 * @Javateam HHCL
 * This Client class makes use of DBUtil.getDataSource() method to take connection from 
 * Hikari connection pool With MYSQL Database.
 */
public class SampleConnection extends ConnectionpoolFilter{
 	public static void main(String[] args) throws SQLException {
 		final Logger logger = Logger.getLogger(SampleConnection.class);
		logger.info("SampleConnection - welcome " );
		ResultSet rs=null;
		 long start = System.currentTimeMillis();
		 logger.info("**welcome page ....!**");
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
			
			
		try {
				Statement st = con.createStatement();
			String SQL = "SELECT 1 from dual";
			rs = st.executeQuery(SQL);
			logger.debug("This is connection : " + con);
			logger.warn("This is warn : " + SQL);
			if (rs.next()) {
				System.out.println("Testing Connection Successfully...........! "+rs.getInt(1));
			}else {
				System.out.println("Testing Connection faild........! ");
			}
		} catch (Exception e) {
			logger.error("SampleConnection - connection error:  "+e );
			e.printStackTrace();
		}finally{
			
			try {
				ConnectionpoolFilter.close(con, rs,"SampleConnection",false);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		long end = System.currentTimeMillis();
		float sec = (end - start) / 1000F;
	    logger.info("SampleConnection execution time in Seconds ::"+ sec);
		
		
		
	}
}
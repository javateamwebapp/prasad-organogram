package organogram.hhcl.service;
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
public class SampleConnection {
 	public static void main(String[] args) throws SQLException {
 		final Logger logger = Logger.getLogger(SampleConnection.class);
		logger.info("SampleConnection - welcome " );
		try (Connection connection = DBUtil.getDataSource().getConnection(); 
				Statement st = connection.createStatement()) {
			String SQL = "SELECT 1 from dual";
			ResultSet rs = st.executeQuery(SQL);
			logger.debug("This is connection : " + connection);
			logger.warn("This is warn : " + SQL);
			if (rs.next()) {
				System.out.println("Testing Connection Successfully...........! "+rs.getInt(1));
			}else {
				System.out.println("Testing Connection faild........! ");
			}
		} catch (Exception e) {
			logger.error("SampleConnection - connection error:  "+e );
			e.printStackTrace();
		}
	}
}
package organogram.hhcl.db;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionpoolFilter{
 public static synchronized Connection Call() throws IOException, SQLException, PropertyVetoException{
		Connection conn=null;
		conn=DBUtil.getDataSource().getConnection();
	    return conn;
 }

 public static synchronized Connection close(Connection conn,ResultSet res,PreparedStatement ptst,String NameOfClass,boolean commitflag) throws SQLException{
	 
	 if(commitflag){
	 try{
		 conn.commit();
	 }catch(Exception err){
		 err.printStackTrace();
	 }
	 }
	 if(conn!=null){
		  conn.close();
	  }
	  if(res!=null){
		  res.close();
	  }
	  if(ptst!=null){
		  ptst.close();
	  }
	 
	 return conn;
 }
 
public static synchronized Connection close(Connection conn,ResultSet res,String NameOfClass,boolean commitflag) throws SQLException{
	 
	if(commitflag){
		 try{
			 conn.commit();
		 }catch(Exception err){
			 err.printStackTrace();
		 }
		 }
	
	 if(conn!=null){
		  conn.close();
	  }
	  if(res!=null){
		  res.close();
	  }
	 return conn;
 }
 
}

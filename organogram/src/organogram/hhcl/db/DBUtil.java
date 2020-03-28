package organogram.hhcl.db;
import java.io.FileInputStream;
import java.util.Properties;
import javax.sql.DataSource;
import com.zaxxer.hikari.HikariDataSource;
/*
 * @Javatem  HHCL
 * Utility class which is responsible to get JDBC connection object using 
 * DataSource connection pool With MYSQL Database.
 */
@SuppressWarnings("unused")
public class DBUtil {
	private static final String DB_USERNAME="db.username";
	private static final String DB_PASSWORD="db.password";
	private static final String DB_URL ="db.url";
	private static final String DB_DRIVER_CLASS="driver.class.name";
	
	private static Properties properties = null;
	private static HikariDataSource dataSource=null;
	static String Path=System.getProperty("user.dir");
	
	static{
		try {
			properties = new Properties();
			properties.load(new FileInputStream(Path+"//database.properties"));
			dataSource = new HikariDataSource();
			dataSource.setDriverClassName(properties.getProperty(DB_DRIVER_CLASS));
			dataSource.setJdbcUrl(properties.getProperty(DB_URL));
			dataSource.setUsername(properties.getProperty(DB_USERNAME));
			dataSource.setPassword(properties.getProperty(DB_PASSWORD));
			dataSource.setMinimumIdle(10);
			dataSource.setMaximumPoolSize(20);//The maximum number of connections, idle or busy, that can be present in the pool.
			dataSource.setAutoCommit(false);
			dataSource.setLoginTimeout(3);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static DataSource getDataSource(){
		return dataSource;
	}
}
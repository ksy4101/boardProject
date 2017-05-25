package conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//오라클 접속하는 커넥션이에여
public class DBConn {
	public static Connection getConnection() throws SQLException{
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		String url = "jdbc:oracle:thin:@192.168.40.59:1521:xe";
		String user = "asd1234";
		String password = "java1234";
		return DriverManager.getConnection(url,user,password);
		
	}

}

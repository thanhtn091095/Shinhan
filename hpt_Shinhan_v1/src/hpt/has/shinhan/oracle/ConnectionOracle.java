package hpt.has.shinhan.oracle;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionOracle {
	
	static String jdbcClassName = "oracle.jdbc.driver.OracleDriver";
	static String host = "10.148.50.4";
	static String port = "1521";
	static String data = "DEVPDB.prudential.com";
	static String user = "pru_esb";
	static String password = "123456";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CreateConnection(host, port, data, user, password, jdbcClassName);
	}

	public static Connection CreateConnection(String host, String port, String data, String username, String password, String driver) {
	
		Connection con = null;
		try {
			
			Class.forName(driver); 
			
			String conectionString = "jdbc:oracle:thin:@" + host + ":" + port + ":" + data;
			
			con = DriverManager.getConnection(  
					conectionString, username, password);
			
			if(con != null) {
				
				System.out.println("hpt.has.shinhan.oracle.ConnectionOracle.CreateConnection.Infor Connection oracle db successful");
			}
			
			else {
				System.out.println("hpt.has.shinhan.oracle.ConnectionOracle.CreateConnection.Infor Connection oracle db fail");
			}
			
		}
		catch(Exception e) {
			System.out.println("hpt.has.shinhan.oracle.ConnectionOracle.CreateConnection.Error: " + e);
		}
		return con;
	}
	
	public static void CloseConnection(Connection con) {
		
		try 
		{
			con.close();
		}
		catch(Exception ex) {
			System.out.println("hpt.has.shinhan.oracle.ConnectionOracle.CloseConnection.Error CloseConnection: " + ex);
		}
	}
}

package hpt.has.shinhan.db2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import com.ibm.mm.sdk.common.DKException;
import com.ibm.mm.sdk.server.DKDatastoreICM;

public class ConnectionDB2 {
	
	static String jdbcClassName="com.ibm.db2.jcc.DB2Driver";
	static String url="jdbc:db2://10.148.133.11:50000/ICMNLSDB";
	static String user="db2inst1";
	static String password="cfc4you";
	
	static String datastore = "icmnlsdb";
	static String connectionString = "JDBCDRIVER=com.ibm.db2.jcc.DB2Driver;JDBCURL=jdbc:db2://128.235.142.159:50000/icmnlsdb;SQLUID=icmconct;SCHEMA=ICMADMIN";
	static String appUser = "finnone_cm";
	static String appPwd = "";
	static String config = "ICMENVFILE=(C:\\Users\\cfchpd\\Desktop\\Get doc from ibmcm\\cmbicmenv_pro.ini)";
	
	public static void main(String[] args)
	{
		
		//ConnectDB2(jdbcClassName, url, user, password);
		System.out.println("sample jdbcClassName = 'com.ibm.db2.jcc.DB2Driver'");
		System.out.println("sample url = 'jdbc:db2://10.148.133.11:50000/ICMNLSDB'");
		System.out.println("sample user = 'db2inst1'");
		System.out.println("sample password = 'cfc4you'");
		System.out.println("Ready for a new command");
		Scanner scanner = new Scanner(System.in);
		System.out.print("jdbcClassName = ");
		String _jdbcClassName = scanner.nextLine();
		System.out.print("url = ");
		String _url = scanner.nextLine();
		System.out.print("user = ");
		String _user = scanner.nextLine();
		System.out.print("password = ");
		String _password = scanner.nextLine();
		ConnectDB2(_jdbcClassName, _url, _user, _password);
	}

	public static Connection ConnectDB2(String class_name, String url, String user, String pass) {
        Connection connection = null;
        try {
            //Load class into memory
            Class.forName(class_name);
            //Establish connection
            connection = DriverManager.getConnection(url, user, pass);
 
        } 
        catch (ClassNotFoundException e) 
        {
        	System.out.println("hpt.has.shinhan.db2.ConnectionDB2.ConnectDB2.Error ConnectDB2: " + e);
        } 
        catch (SQLException e) 
        {
        	System.out.println("hpt.has.shinhan.db2.ConnectionDB2.ConnectDB2.Error ConnectDB2: " + e);
        }
        finally
        {
            if(connection != null){
            	System.out.println("hpt.has.shinhan.db2.ConnectionDB2.ConnectDB2.Info Connected successfully");
            }
        }
        return connection;
	}
	
	public static void CloseBD2(Connection con) {
		
		try 
		{
			con.close();
		}
		catch(Exception ex) {
			System.out.println("hpt.has.shinhan.db2.ConnectionDB2.CloseBD2.Error CloseBD2: " + ex);
		}
	}
	
	public static DKDatastoreICM connect(String datastore, String username, String password, String connectionString, String configFile ) throws DKException, Exception {
        DKDatastoreICM dsICM = new DKDatastoreICM(configFile);
        dsICM.connect(datastore, username, password, connectionString);
        
        return dsICM;
	}

}

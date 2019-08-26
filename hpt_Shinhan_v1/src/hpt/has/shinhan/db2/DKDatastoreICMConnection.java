package hpt.has.shinhan.db2;

import com.ibm.mm.sdk.common.DKException;
import com.ibm.mm.sdk.server.DKDatastoreICM;

public class DKDatastoreICMConnection {
	
	static String url="jdbc:db2://10.148.133.11:50000/ICMNLSDB";
	static String user="db2inst1";
	static String password="cfc4you";
	
	static String datastore = "icmnlsdb";
	static String connectionString = "JDBCDRIVER=com.ibm.db2.jcc.DB2Driver;JDBCURL=jdbc:db2://10.148.133.11:50000/icmnlsdb;SQLUID=icmconct;SCHEMA=ICMADMIN";
	static String appUser = "finnone_cm";
	static String appPwd = "";
	static String config = "ICMENVFILE=(C:\\Users\\cfchpd\\Desktop\\Get doc from ibmcm\\cmbicmenv_pro.ini)";
	static String config_2 = "";

	public static void main(String[] args) throws DKException, Exception {
		// TODO Auto-generated method stub
		connect(datastore, appUser, appPwd, connectionString, config);
	}
	
	public static DKDatastoreICM connect(String datastore, String username, String password, String connectionString, String configFile ) throws DKException, Exception {
        DKDatastoreICM dsICM = null;
        try
        {
		    dsICM = new DKDatastoreICM(configFile);
		    dsICM.connect(datastore, username, password, connectionString);
		    System.out.println("DKDatastore connect successfully");
        } catch (Exception e) {
        	System.out.println("error " + e);
        } 
        return dsICM;
	}

}

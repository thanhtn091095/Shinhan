package hpt.has.shinhan.filenet;

import java.sql.Connection;
import java.util.List;

import hpt.has.shinhan.oracle.ConnectionOracle;
import hpt.has.shinhan.oracle.QueryOracle;
import hpt.has.shinhan.thread.DivideList;
import hpt.has.shinhan.thread.DivideTime;
import hpt.has.shinhan.thread.SegmentMigrate;
import hpt.has.shinhan.thread.SegmentMigrateMain;
import hpt.has.shinhan.thread.SegmentQuery;
import hpt.has.shinhan.thread.Timespan;

public class Migration {
	
	static String uri = "http://10.148.50.62:9080/wsi/FNCEWS40MTOM/";
	static String username = "srvsvfcibmcm";
	static String password = "Shinhan01";
	static String optionalJAASStanzaName = "FileNetP8WSI";
	static String objectName = "Object01";
	static String root = "/SVFCRoot/CORE";	
	static String doc_class = "SHFDoc";
	static String fol_class = "SHFFolder";
	
	static String url_file = "http://10.148.133.11:8080";
	
	static String jdbcClassName1 = "oracle.jdbc.driver.OracleDriver";
	static String host = "10.148.50.4";
	static String port = "1521";
	static String data = "DEVPDB.prudential.com";
	static String user = "ibmcm";
	static String pass = "123456";
	
	static String jdbcClassName2 = "com.ibm.db2.jcc.DB2Driver";
	static String url_log="jdbc:db2://10.148.50.61:55000/";
	static String CPEDB = "CPEDB";	
	static String userlog = "db2ecm1";
	static String passlog = "Shinhan01";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Handler2(uri, username, password, optionalJAASStanzaName, objectName, doc_class, root, fol_class, host, port, data, user, pass, jdbcClassName1, url_file, url_log + CPEDB, userlog, passlog, jdbcClassName2, 4, "30-06-2019", "31-07-2019", 0, "App_Migration_29082019_01");
		
	}
	
	public static void Handler(String _url_filenet, String _user_filenet, String _pass_filenet, String _optional_filenet, String _obj_filenet, String _class_doc, String _root_filenet, String _fol_filenet, String _url_oracle, String _port_oracle, String _data_name, String _user_oracle, String _pass_oracle, String _driver_oracle, String _url_get_file, String _url_DB2_Log, String _user_log, String _pass_log, String _driver_db2,  int _volume, String from, String to, int rownum) {
		
		try {
			
			String currentDirectory = System.getProperty("user.dir");
			
			Connection conPro = ConnectionOracle.CreateConnection(_url_oracle, _port_oracle, _data_name, _user_oracle, _pass_oracle, _driver_oracle);
			
			String sqlInfo = QueryOracle.QueryStringOracle1(from, to, rownum);
			
			List<MetaData> lstPro = QueryOracle.ExcuteQuerySelect(conPro, sqlInfo);
			
			List<List<MetaData>> lst = DivideList.distributeList2(lstPro, _volume);
			
			for(int i = 0; i < lst.size(); i++) {				
				SegmentMigrate seg = new SegmentMigrate(lst.get(i), _url_filenet, _user_filenet, _pass_filenet, _optional_filenet, _obj_filenet, _class_doc, _root_filenet, _fol_filenet, _url_get_file, _driver_db2, _url_DB2_Log, _user_log, _pass_log, currentDirectory, (i + 1));				
				seg.start();
			}			
			ConnectionOracle.CloseConnection(conPro);
		}
		catch(Exception ex) {
			System.out.println("hpt.has.shinhan.filenet.Migration.Handler.Error Handler: " + ex);
		}
	}
	
	public static void Handler1(String _url_filenet, String _user_filenet, String _pass_filenet, String _optional_filenet, String _obj_filenet, String _class_doc, String _root_filenet, String _fol_filenet, String _url_oracle, String _port_oracle, String _data_name, String _user_oracle, String _pass_oracle, String _driver_oracle, String _url_get_file, String _url_DB2_Log, String _user_log, String _pass_log, String _driver_db2,  int _volume, String from, String to, int rownum, String appName) {
		
		try {
			
			String currentDirectory = System.getProperty("user.dir");
			
			Connection conPro = ConnectionOracle.CreateConnection(_url_oracle, _port_oracle, _data_name, _user_oracle, _pass_oracle, _driver_oracle);
			
			String sqlInfo = QueryOracle.QueryStringOracle1(from, to, rownum);
			
			List<MetaData> lstPro = QueryOracle.ExcuteQuerySelect(conPro, sqlInfo);
			
			List<List<MetaData>> lst = DivideList.distributeList2(lstPro, _volume);
			
			for(int i = 0; i < lst.size(); i++) {				
				SegmentMigrateMain seg = new SegmentMigrateMain(lst.get(i), _url_filenet, _user_filenet, _pass_filenet, _optional_filenet, _obj_filenet, _class_doc, _root_filenet, _fol_filenet, _url_get_file, _driver_db2, _url_DB2_Log, _user_log, _pass_log, _url_oracle, _port_oracle, _data_name, _user_oracle, _pass_oracle, _driver_oracle, currentDirectory, appName, (i + 1));				
				seg.start();
			}			
			ConnectionOracle.CloseConnection(conPro);
		}
		catch(Exception ex) {
			System.out.println("hpt.has.shinhan.filenet.Migration.Handler1.Error Handler: " + ex);
		}
	}
	
	public static void Handler2(String _url_filenet, String _user_filenet, String _pass_filenet, String _optional_filenet, String _obj_filenet, String _class_doc, String _root_filenet, String _fol_filenet, String _url_oracle, String _port_oracle, String _data_name, String _user_oracle, String _pass_oracle, String _driver_oracle, String _url_get_file, String _url_DB2_Log, String _user_log, String _pass_log, String _driver_db2,  int _volume, String _from, String _to, int _rownum, String _appName) {
		
		try {	
			
			List<Timespan> dateList = DivideTime.divideTime(_from, _to, _volume);
			
			for(int i = 0; i < dateList.size(); i++) {				
				SegmentQuery seg = new SegmentQuery(_url_filenet, _user_filenet, _pass_filenet, _optional_filenet, _obj_filenet, _class_doc, _root_filenet, _fol_filenet, _url_oracle, _port_oracle, _data_name, _user_oracle, _pass_oracle, _driver_oracle, _url_get_file, _url_DB2_Log, _user_log, _pass_log, _driver_db2, _volume, dateList.get(i).startDate, dateList.get(i).endDate, _rownum, _appName);
				seg.start();
			}
		}
		catch(Exception ex) {
			System.out.println("hpt.has.shinhan.filenet.Migration.Handler2.Error Handler2: " + ex);
		}
	}
}

package hpt.has.shinhan.thread;

import java.sql.Connection;
import java.util.List;

import hpt.has.shinhan.filenet.MetaData;
import hpt.has.shinhan.oracle.ConnectionOracle;
import hpt.has.shinhan.oracle.QueryOracle;

public class SegmentQuery extends Thread{

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	String uri;
	String username;
	String password;
	String optionalJAASStanzaName;
	String objectName;
	String root;	
	String doc_class;
	String fol_class;
	
	String url_file;
	
	String jdbcClassName1;
	String host_oracle;
	String port_oracle;
	String data_oracle;
	String user_oracle;
	String pass_oracle;
	
	String jdbcClassName2;
	String url_log;	
	String userlog;
	String passlog;
	
	String appName;
	int thread;
	int rownum;
	
	String from;
	String to;
	
	@Override
	public void run() {		
		Handler();
	}
	
	public SegmentQuery(String _url_filenet, String _user_filenet, String _pass_filenet, String _optional_filenet, String _obj_filenet, String _class_doc, String _root_filenet, String _fol_filenet, String _url_oracle, String _port_oracle, String _data_name, String _user_oracle, String _pass_oracle, String _driver_oracle, String _url_get_file, String _url_DB2_Log, String _user_log, String _pass_log, String _driver_db2,  int _volume, String _from, String _to, int _rownum, String _appName) {
		
		this.uri = _url_filenet;
		this.username = _user_filenet;
		this.password = _pass_filenet;
		this.optionalJAASStanzaName = _optional_filenet;
		this.objectName = _obj_filenet;
		this.root = _root_filenet;	
		this.doc_class = _class_doc;
		this.fol_class = _fol_filenet;
		
		this.url_file = _url_get_file;
		
		this.jdbcClassName1 = _driver_oracle;
		this.host_oracle = _url_oracle;
		this.port_oracle= _port_oracle;
		this.data_oracle = _data_name;
		this.user_oracle = _user_oracle;
		this.pass_oracle = _pass_oracle;
		
		this.jdbcClassName2 = _driver_db2;
		this.url_log = _url_DB2_Log;
		this.userlog = _user_log;
		this.passlog = _pass_log;
		
		this.appName = _appName;
		this.thread = _volume;
		this.rownum = _rownum;
		
		this.from = _from;
		this.to = _to;
		
	}
	
	public void Handler() {		
		try {
			
			String currentDirectory = System.getProperty("user.dir");
			
			Connection conPro = ConnectionOracle.CreateConnection(host_oracle, port_oracle, data_oracle, user_oracle, pass_oracle, jdbcClassName1);
			
			String sqlInfo = QueryOracle.QueryStringOracle1(from, to, rownum);
			
			List<MetaData> lstPro = QueryOracle.ExcuteQuerySelect(conPro, sqlInfo);
			
			List<List<MetaData>> lst = DivideList.distributeList2(lstPro, thread);
			
			for(int i = 0; i < lst.size(); i++) {				
				SegmentMigrateMain seg = new SegmentMigrateMain(lst.get(i), uri, username, password, optionalJAASStanzaName, objectName, doc_class, root, fol_class, url_file, jdbcClassName2, url_log, userlog, passlog, host_oracle, port_oracle, data_oracle, user_oracle, pass_oracle, jdbcClassName1, currentDirectory, appName, (i + 1));				
				seg.start();
			}			
			ConnectionOracle.CloseConnection(conPro);
		}
		catch(Exception ex) {
			System.out.println("hpt.has.shinhan.thread.SegmentQuery.Handler.Error Handler: " + ex);
		}
	}

}

package hpt.has.shinhan.oracle;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import hpt.has.shinhan.filenet.MetaData;

public class QueryOracle {
	
	static String driver = "oracle.jdbc.driver.OracleDriver";
	static String host = "10.148.50.4";
	static String port = "1521";
	static String data = "DEVPDB.prudential.com";
	static String username = "pru_esb";
	static String password = "123456";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection con = ConnectionOracle.CreateConnection(host, port, data, username, password, driver);
		
		String sql = QueryOracle.QueryStringOracle("24-SEP-07", "05-FEB-18");
		
		System.out.println("hpt.has.shinhan.oracle.QueryOracle.main.Error: " + sql);
		
		ExcuteQuerySelect(con, sql);
		
	}

	public static String QueryStringOracle(String from, String to) {
		String sql = "SELECT boxid, appfrmno, apprefno, cif, create_dt, custid, custname, docdesc, docid, docrefid, filename, filepath, item, loanagrno, mimetype, scanneddate, update_dt, uploadchannel FROM icm_document WHERE";
		try {
			sql += " (create_dt >= '" + from + "' AND create_dt <= '" + to + "')";
			
			sql += " and ROWNUM <= 5";
		}
		catch(Exception e) {
			System.out.println("hpt.has.shinhan.oracle.QueryOracle.QueryStringOracle.Error: " + e);
		}
		return sql;
	}
	
	public static String QueryStringOracle1(String from, String to, int rownum) {
		
		String sql = "SELECT boxid, appfrmno, apprefno, cif, create_dt, custid, custname, docdesc, docid, docrefid, filename, filepath, item, loanagrno, mimetype, scanneddate, update_dt, uploadchannel FROM icm_document WHERE";
		try {
			
			sql += " (create_dt >= to_date('" + from + "', 'DD-MM-YYYY') AND create_dt <= to_date('" + to + "', 'DD-MM-YYYY'))";
			
			if(rownum >= 0) {
				
				sql += " and ROWNUM <= " + rownum + "";
			}
			
			System.out.println("hpt.has.shinhan.oracle.QueryOracle.QueryStringOracle.Info: " + sql);
		}
		catch(Exception e) {
			System.out.println("hpt.has.shinhan.oracle.QueryOracle.QueryStringOracle.Error: " + e);
		}
		return sql;
	}
	
	public static List<MetaData> ExcuteQuerySelect(Connection con, String sql){
		List<MetaData> lst = new ArrayList<MetaData>();
		
		try {
			
			Statement st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			ResultSet rset = st.executeQuery(sql);
			
			while(rset.next()) {
				
				System.out.println("hpt.has.shinhan.oracle.QueryOracle.ExcuteQuerySelect.Info DOCREFID: " + rset.getString("DOCREFID"));
				
				MetaData m = new MetaData(rset.getString("DOCREFID"), rset.getString("APPFRMNO"), rset.getString("LOANAGRNO"), rset.getString("DOCDESC"), rset.getString("CUSTID"), rset.getString("CUSTNAME"), rset.getDate("SCANNEDDATE"), rset.getString("BOXID"), rset.getString("MIMETYPE"), rset.getString("FILEPATH"), rset.getString("CIF"), rset.getString("DOCID"), rset.getString("ITEM"), rset.getString("UPLOADCHANNEL"), rset.getString("APPREFNO"), rset.getString("FILENAME"), rset.getDate("CREATE_DT"), rset.getDate("UPDATE_DT"));
				m.setList();
				
				lst.add(m);
			}
		}
		catch(Exception e) {
			System.out.println("hpt.has.shinhan.oracle.QueryOracle.ExcuteQuerySelect.Error: " + e);
		}
		
		return lst;
	}
}

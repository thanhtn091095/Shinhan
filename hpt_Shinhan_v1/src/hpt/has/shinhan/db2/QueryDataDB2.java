package hpt.has.shinhan.db2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import hpt.has.shinhan.filenet.PropertiesTemplate;
import hpt.has.shinhan.oracle.StructureFolder;

public class QueryDataDB2 {
	
	static String jdbcClassName="com.ibm.db2.jcc.DB2Driver";
//	static String url="jdbc:db2://10.148.133.11:50000/";
	static String url="jdbc:db2://10.148.50.61:55000/";
	static String ICMNLSDB = "ICMNLSDB";
	static String RMDB = "RMDB";
	static String CPEDB = "CPEDB";
//	static String user="db2inst1";
//	static String password="cfc4you";
//	
	static String user="db2ecm1";
	static String password="Shinhan01";
	
	static String query = "select a.targetitemid as Item," + 
			"	t.itemid DocRefID," + 
			"	t.LASTCHANGEDTS," + 
			"	t.ATTR0000001033 as ScannedDate," + 
			"	t.ATTR0000001031 as AppFrmNo," + 
			"	t.ATTR0000001032 as agreementno," + 
			"	t.ATTR0000001024 as DocID," + 
			"	t.ATTR0000001028 as DocDesc," + 
			"	t.ATTR0000001029 as BoxID," + 
			"	t.ATTR0000001025 as CustID," + 
			"	t.ATTR0000001026 as CustName" + 
			"	from icmadmin.icmstri001001 as a," + 
			"	(	SELECT  itemid, LASTCHANGEDTS,ATTR0000001033, ATTR0000001031, ATTR0000001032, ATTR0000001024, ATTR0000001028, ATTR0000001029, ATTR0000001025, ATTR0000001026 FROM ICMADMIN.ICMUT01054001" + 
			"	UNION	all SELECT  itemid, LASTCHANGEDTS,ATTR0000001033, ATTR0000001031, ATTR0000001032, ATTR0000001024, ATTR0000001028, ATTR0000001029, ATTR0000001025, ATTR0000001026 FROM ICMADMIN.ICMUT01062001" + 
			"	UNION	all SELECT  itemid, LASTCHANGEDTS,ATTR0000001033, ATTR0000001031, ATTR0000001032, ATTR0000001024, ATTR0000001028, ATTR0000001029, ATTR0000001025, ATTR0000001026 FROM ICMADMIN.ICMUT01070001" + 
			"	UNION	all SELECT  itemid, LASTCHANGEDTS,ATTR0000001033, ATTR0000001031, ATTR0000001032, ATTR0000001024, ATTR0000001028, ATTR0000001029, ATTR0000001025, ATTR0000001026 FROM ICMADMIN.ICMUT01074001" + 
			"	UNION	all SELECT  itemid, LASTCHANGEDTS,ATTR0000001033, ATTR0000001031, ATTR0000001032, ATTR0000001024, ATTR0000001028, ATTR0000001029, ATTR0000001025, ATTR0000001026 FROM ICMADMIN.ICMUT01078001" + 
			"	UNION	all SELECT  itemid, LASTCHANGEDTS,ATTR0000001033, ATTR0000001031, ATTR0000001032, ATTR0000001024, ATTR0000001028, ATTR0000001029, ATTR0000001025, ATTR0000001026 FROM ICMADMIN.ICMUT01080001" + 
			"	) as t" + 
//			"	where a.sourceitemid = t.itemid and t.itemid in ('A1001001A08A15B61824A80686', 'A1001001A08A15B61824D80440', 'A1001001A08A15B61824F98907', 'A1001001A08A15B61824H89800')";
//			"	where a.sourceitemid = t.itemid and t.itemid in ('822A8A964205418EAF0106059AFF8A0D', '4384FC2FEA1E47EBBB59BFD68DED00DE', 'A1001001A19G24B14437B88460', 'A1001001A08A15B61824H89800')";
			"	where a.sourceitemid = t.itemid and t.ATTR0000001033 >= '2019-01-01' fetch first 10 rows only ";
			
	static String sql = "select obj_itemid as itemid," + 
			"	obj_mimetype as mimetype," + 
			"	obj_filename as filename," + 
			"	obj_orgfilename as orgfilename," + 
			"	obj_path as path," + 
			"	obj_size as size," + 
			"	obj_volumeid as volumeid," + 
			"	b.vol_mountpoint as mountpoint" + 
			"	from rmadmin.rmobjects a join rmadmin.rmvolumes b" + 
			"	on a.obj_volumeid=b.vol_volumeid " + 
			"	where obj_libraryid = 1 " + 
			"	and obj_version = 1 " + 
			"	and obj_itemid in ('A1001001A18L05B75012C80974')" + 
			"	and obj_collectionid = 1";
	
	public static void main(String[] args)
	{
		
		
		
		Connection con1 = ConnectionDB2.ConnectDB2(jdbcClassName, url + CPEDB, user, password);
		
//		Connection con1 = ConnectionDB2.ConnectDB2(jdbcClassName, url + RMDB, user, password);
////		
//		List<String> lst = ExcuteQueryPathDB2(con1, sql);
////		
//		System.out.println("hpt.has.shinhan.db2.QueryDataDB2.main.Info ExcuteQueryDB2: " + lst.get(0));
//		System.out.println("hpt.has.shinhan.db2.QueryDataDB2.main.Info ExcuteQueryDB2: " + lst.get(1));
//		System.out.println("hpt.has.shinhan.db2.QueryDataDB2.main.Info ExcuteQueryDB2: " + lst.get(2));
		
		
//		Connection con = ConnectionDB2.ConnectDB2(jdbcClassName, url + ICMNLSDB, user, password);
		
//		List<PropertiesTemplate> lst = ExcuteQueryProDB2(con, query);
//		
//		for(int i = 0; i < lst.size(); i ++) {
//			
//			System.out.println("hpt.has.shinhan.db2.QueryDataDB2.main.Info ExcuteQueryDB2: " + lst.get(i).Item);
//			
//			String str = QueryStringPath(lst.get(i).Item);
//			
//			List<String> path = ExcuteQueryPathDB2(con1, str);
//			
//			System.out.println("hpt.has.shinhan.db2.QueryDataDB2.main.Info ExcuteQueryDB2: " + path.get(1));
//		}
		
		String id = GenerateUniqueIDUsingGUID.generateUniqueID();
		String id1 = GenerateUniqueIDUsingGUID.generateUniqueID();
		
		String str = "123'1'";
		
		String query1 = QueryStringInsertInput(id, "123", "123", "123", "123", 123, 1, 1, str.replace("'", ""));
		
		String query2 = QueryStringInsertOutput(id1, "123", id, true);
		
		System.out.println("hpt.has.shinhan.db2.QueryDataDB2.main.Info query1: " + query1);
		System.out.println("hpt.has.shinhan.db2.QueryDataDB2.main.Info query2: " + query2);
		
		Boolean result1 = ExcuteQueryInsert(con1, query1);
		
		Boolean result2 = ExcuteQueryInsert(con1, query2);
		
		System.out.println("hpt.has.shinhan.db2.QueryDataDB2.main.Info ExcuteQueryInsert: " + result1);
		
		System.out.println("hpt.has.shinhan.db2.QueryDataDB2.main.Info ExcuteQueryInsert: " + result2);
		
//		ConnectionDB2.CloseBD2(con);
		ConnectionDB2.CloseBD2(con1);
		
		
	}

	public static void ExcuteQueryInfoDB2(Connection con, String query) {
		
		try {
			
			Statement sqlStatement = con.createStatement();
			
			ResultSet resSet = sqlStatement.executeQuery(query);
			
			while(resSet.next()) {
				
				System.out.println("hpt.has.shinhan.db2.QueryDataDB2.ExcuteQueryDB2.Info ExcuteQueryDB2: " + resSet.getString("CustID"));
			}		
			
		}
		catch(Exception ex) {
			
			System.out.println("hpt.has.shinhan.db2.QueryDataDB2.ExcuteQueryDB2.Error ExcuteQueryDB2: " + ex);
		}
	}
	
	public static List<PropertiesTemplate> ExcuteQueryProDB2Ver1(Connection con, String query, Connection conPath) {
		
		List<PropertiesTemplate> lst = new ArrayList<>();;
		
		try {
			
			Statement sqlStatement = con.createStatement();
			
			ResultSet resSet = sqlStatement.executeQuery(query);
			
			while(resSet.next()) {
				
				System.out.println("hpt.has.shinhan.db2.QueryDataDB2.ExcuteQueryProDB2.Info ExcuteQueryProDB2: " + resSet.getString("DocRefID"));
				
				String sql = QueryStringPath(resSet.getString("Item"));
				
				List<String> lstPath = ExcuteQueryPathDB2(conPath, sql);
				
				String path = "";
				String minetype = "";
				String docname = "";
				
				if(lstPath != null && lstPath.size() >= 3) {
					
					path = lstPath.get(0);
					minetype = lstPath.get(1);
					docname = lstPath.get(2);
				}
				
				PropertiesTemplate p = new PropertiesTemplate(resSet.getString("AppFrmNo"), "", "", resSet.getString("CustName"), resSet.getString("CustID"), resSet.getString("DocID"), resSet.getString("DocDesc"), docname, resSet.getString("DocRefID"), "", path, minetype, "", resSet.getString("Item"), resSet.getDate("ScannedDate"), new Date());
			
				PropertiesTemplate.setListPro(p);
				
				lst.add(p);
			}		
			
		}
		catch(Exception ex) {
			
			System.out.println("hpt.has.shinhan.db2.QueryDataDB2.ExcuteQueryProDB2.Error ExcuteQueryProDB2: " + ex);
		}
		return lst;
	}
	
	public static List<PropertiesTemplate> ExcuteQueryProDB2Ver2(Connection con, String query, Connection conPath) {
		
		List<PropertiesTemplate> lst = new ArrayList<>();;
		
		try {
			
			Statement sqlStatement = con.createStatement();
			
			ResultSet resSet = sqlStatement.executeQuery(query);
			
			while(resSet.next()) {
				
				System.out.println("hpt.has.shinhan.db2.QueryDataDB2.ExcuteQueryProDB2.Info ExcuteQueryProDB2: " + resSet.getString("DocRefID"));
				
				PropertiesTemplate p = new PropertiesTemplate(resSet.getString("AppFrmNo"), "", "", resSet.getString("CustName"), resSet.getString("CustID"), resSet.getString("DocID"), resSet.getString("DocDesc"), "", resSet.getString("DocRefID"), "", "", "", "", resSet.getString("Item"), resSet.getDate("ScannedDate"), new Date());
			
				PropertiesTemplate.setListPro(p);
				
				lst.add(p);
			}
			
			for(int i = 0; i < lst.size(); i ++) {
				
				
				String sql = QueryStringPath(lst.get(i).Item);
				
				List<String> lstPath = ExcuteQueryPathDB2(conPath, sql);
				
				String path = "";
				String minetype = "";
				String docname = "";
				
				if(lstPath != null && lstPath.size() >= 3) {
					
					path = lstPath.get(0);
					minetype = lstPath.get(1);
					docname = lstPath.get(2);
				}
				
				PropertiesTemplate.setProperty(lst.get(i), "FilePath", path);
				PropertiesTemplate.setProperty(lst.get(i), "MimeType", minetype);
				PropertiesTemplate.setProperty(lst.get(i), "FileName", docname);
			}
			
		}
		catch(Exception ex) {
			
			System.out.println("hpt.has.shinhan.db2.QueryDataDB2.ExcuteQueryProDB2.Error ExcuteQueryProDB2: " + ex);
		}
		return lst;
	}
	
	public static List<PropertiesTemplate> ExcuteQueryProDB2(Connection con, String query) {
		
		List<PropertiesTemplate> lst = new ArrayList<>();;
		
		try {
			
			Statement sqlStatement = con.createStatement();
			
			ResultSet resSet = sqlStatement.executeQuery(query);
			
			while(resSet.next()) {
				
				System.out.println("hpt.has.shinhan.db2.QueryDataDB2.ExcuteQueryProDB2.Info ExcuteQueryProDB2: " + resSet.getString("DocRefID"));
				
				PropertiesTemplate p = new PropertiesTemplate(resSet.getString("AppFrmNo"), "", "", resSet.getString("CustName"), resSet.getString("CustID"), resSet.getString("DocID"), resSet.getString("DocDesc"), "", resSet.getString("DocRefID"), "", "", "", "", resSet.getString("Item"), resSet.getDate("ScannedDate"), new Date());
			
				PropertiesTemplate.setListPro(p);
				
				lst.add(p);
			}			
		}
		catch(Exception ex) {
			
			System.out.println("hpt.has.shinhan.db2.QueryDataDB2.ExcuteQueryProDB2.Error ExcuteQueryProDB2: " + ex);
		}
		return lst;
	}
	
	public static List<String> ExcuteQueryPathDB2(Connection con, String query) {
		
		List<String> lst = new ArrayList<String>();	
		try {
			
			Statement sqlStatement = con.createStatement();
			
			ResultSet resSet = sqlStatement.executeQuery(query);		

			while(resSet.next()) {
				
				String fpath = resSet.getString("path");
				System.out.println("hpt.has.shinhan.db2.QueryDataDB2.ExcuteQueryPathDB2.Info ExcuteQueryPathDB2: " + resSet.getString("itemid"));	
				if (fpath != null && fpath != "" && fpath.length() == 1) {
					fpath = "0" + fpath;
				}
				String filePath = resSet.getString("mountpoint") + "/lbosdata/00001" + "/" + fpath + "/" + resSet.getString("filename");
				
				System.out.println("hpt.has.shinhan.db2.QueryDataDB2.ExcuteQueryPathDB2.Info ExcuteQueryPathDB2: " + filePath);
				
				lst.add(filePath);
				
				String mimetype = resSet.getString("mimetype");
				
				lst.add(mimetype);
				
				String filename = resSet.getString("filename");
				
				lst.add(filename);
			}	
			
		}
		catch(Exception ex) {
			System.out.println("hpt.has.shinhan.db2.QueryDataDB2.ExcuteQueryPathDB2.Error ExcuteQueryPathDB2: " + ex);
		}
		return lst;
	}
	
	public static String QueryStringPath(String itemId) {
		
		String sql = "select obj_itemid as itemid," + 
				"	obj_mimetype as mimetype," + 
				"	obj_filename as filename," + 
				"	obj_orgfilename as orgfilename," + 
				"	obj_path as path," + 
				"	obj_size as size," + 
				"	obj_volumeid as volumeid," + 
				"	b.vol_mountpoint as mountpoint" + 
				"	from rmadmin.rmobjects a, rmadmin.rmvolumes b" + 
				"	where a.obj_volumeid=b.vol_volumeid " + 
				"	and obj_libraryid = 1 " + 
				"	and obj_version = 1 " + 
				"	and obj_collectionid = 1";
		
		try {
			if(itemId != null && itemId != "") {
				sql = sql +  " and obj_itemid = '" + itemId + "'";
			}
		}
		catch(Exception ex) {
			System.out.println("hpt.has.shinhan.db2.QueryDataDB2.QueryStringPath.Error QueryStringPath: " + ex);
		}
		return sql;
	}
	
	public static String QueryStringInfo1(List<String> lst) {
		
		String sql = "select a.targetitemid as Item," + 
				"	t.itemid DocRefID," + 
				"	t.LASTCHANGEDTS," + 
				"	t.ATTR0000001033 as ScannedDate," + 
				"	t.ATTR0000001031 as AppFrmNo," + 
				"	t.ATTR0000001032 as agreementno," + 
				"	t.ATTR0000001024 as DocID," + 
				"	t.ATTR0000001028 as DocDesc," + 
				"	t.ATTR0000001029 as BoxID," + 
				"	t.ATTR0000001025 as CustID," + 
				"	t.ATTR0000001026 as CustName" + 
				"	from icmadmin.icmstri001001 as a," + 
				"	(	SELECT  itemid, LASTCHANGEDTS,ATTR0000001033, ATTR0000001031, ATTR0000001032, ATTR0000001024, ATTR0000001028, ATTR0000001029, ATTR0000001025, ATTR0000001026 FROM ICMADMIN.ICMUT01054001" + 
				"	UNION	all SELECT  itemid, LASTCHANGEDTS,ATTR0000001033, ATTR0000001031, ATTR0000001032, ATTR0000001024, ATTR0000001028, ATTR0000001029, ATTR0000001025, ATTR0000001026 FROM ICMADMIN.ICMUT01062001" + 
				"	UNION	all SELECT  itemid, LASTCHANGEDTS,ATTR0000001033, ATTR0000001031, ATTR0000001032, ATTR0000001024, ATTR0000001028, ATTR0000001029, ATTR0000001025, ATTR0000001026 FROM ICMADMIN.ICMUT01070001" + 
				"	UNION	all SELECT  itemid, LASTCHANGEDTS,ATTR0000001033, ATTR0000001031, ATTR0000001032, ATTR0000001024, ATTR0000001028, ATTR0000001029, ATTR0000001025, ATTR0000001026 FROM ICMADMIN.ICMUT01074001" + 
				"	UNION	all SELECT  itemid, LASTCHANGEDTS,ATTR0000001033, ATTR0000001031, ATTR0000001032, ATTR0000001024, ATTR0000001028, ATTR0000001029, ATTR0000001025, ATTR0000001026 FROM ICMADMIN.ICMUT01078001" + 
				"	UNION	all SELECT  itemid, LASTCHANGEDTS,ATTR0000001033, ATTR0000001031, ATTR0000001032, ATTR0000001024, ATTR0000001028, ATTR0000001029, ATTR0000001025, ATTR0000001026 FROM ICMADMIN.ICMUT01080001" + 
				"	) as t" + 
				"	where a.sourceitemid = t.itemid";
		
		
		try {
			
			if(lst != null && lst.size() >= 0) {
				
				sql = sql + " and t.itemid in (";
				
				for(int i = 0; i < lst.size(); i++) {
					
					if(i != 0) {
						sql = sql + ", ";
					}
					sql = sql + "'" + lst.get(i) + "'";
				}
				
				sql = sql + ")";
			}		
			
		}
		catch(Exception ex) {
			System.out.println("hpt.has.shinhan.db2.QueryDataDB2.QueryStringInfo.Error QueryStringPath: " + ex);
		}
		return sql;
	}
	
	public static String QueryStringInfo2(String date1, String date2) {
		
		String sql = "select a.targetitemid as Item," + 
				"	t.itemid DocRefID," + 
				"	t.LASTCHANGEDTS," + 
				"	t.ATTR0000001033 as ScannedDate," + 
				"	t.ATTR0000001031 as AppFrmNo," + 
				"	t.ATTR0000001032 as agreementno," + 
				"	t.ATTR0000001024 as DocID," + 
				"	t.ATTR0000001028 as DocDesc," + 
				"	t.ATTR0000001029 as BoxID," + 
				"	t.ATTR0000001025 as CustID," + 
				"	t.ATTR0000001026 as CustName" + 
				"	from icmadmin.icmstri001001 as a," + 
				"	(	SELECT  itemid, LASTCHANGEDTS,ATTR0000001033, ATTR0000001031, ATTR0000001032, ATTR0000001024, ATTR0000001028, ATTR0000001029, ATTR0000001025, ATTR0000001026 FROM ICMADMIN.ICMUT01054001" + 
				"	UNION	all SELECT  itemid, LASTCHANGEDTS,ATTR0000001033, ATTR0000001031, ATTR0000001032, ATTR0000001024, ATTR0000001028, ATTR0000001029, ATTR0000001025, ATTR0000001026 FROM ICMADMIN.ICMUT01062001" + 
				"	UNION	all SELECT  itemid, LASTCHANGEDTS,ATTR0000001033, ATTR0000001031, ATTR0000001032, ATTR0000001024, ATTR0000001028, ATTR0000001029, ATTR0000001025, ATTR0000001026 FROM ICMADMIN.ICMUT01070001" + 
				"	UNION	all SELECT  itemid, LASTCHANGEDTS,ATTR0000001033, ATTR0000001031, ATTR0000001032, ATTR0000001024, ATTR0000001028, ATTR0000001029, ATTR0000001025, ATTR0000001026 FROM ICMADMIN.ICMUT01074001" + 
				"	UNION	all SELECT  itemid, LASTCHANGEDTS,ATTR0000001033, ATTR0000001031, ATTR0000001032, ATTR0000001024, ATTR0000001028, ATTR0000001029, ATTR0000001025, ATTR0000001026 FROM ICMADMIN.ICMUT01078001" + 
				"	UNION	all SELECT  itemid, LASTCHANGEDTS,ATTR0000001033, ATTR0000001031, ATTR0000001032, ATTR0000001024, ATTR0000001028, ATTR0000001029, ATTR0000001025, ATTR0000001026 FROM ICMADMIN.ICMUT01080001" + 
				"	) as t" + 
				"	where a.sourceitemid = t.itemid";
				
		
		
		try {
			

			sql += " and (t.ATTR0000001033 >= '" + date1 + "'" + 
					" and t.ATTR0000001033 <= '" + date2 + "')";
			
			
			
			sql += " fetch first 2000 rows only";
			
		}
		catch(Exception ex) {
			System.out.println("hpt.has.shinhan.db2.QueryDataDB2.QueryStringInfo.Error QueryStringPath: " + ex);
		}
		return sql;
		
	}
	
	public static String QueryStringStructureFolder() {
		
		String sql = "SELECT id, docid, doc_type, filenet_folder, filenet_folder_id, stage FROM SVFC.STRUCTURE_FOLDER";			
		try {
			
			sql += " order by docid";
			
		}
		catch(Exception ex) {
			System.out.println("hpt.has.shinhan.db2.QueryDataDB2.QueryStringStructureFolder.Error QueryStringStructureFolder: " + ex);
		}
		return sql;
		
	}
	
	public static List<StructureFolder> ExcuteQueryStructureFolder(Connection con, String query) {
		
		List<StructureFolder> lst = new ArrayList<StructureFolder>();	
		try {
			
			Statement sqlStatement = con.createStatement();
			
			ResultSet resSet = sqlStatement.executeQuery(query);		

			while(resSet.next()) {				
				StructureFolder f = new StructureFolder(resSet.getInt("id"), resSet.getInt("docid"), resSet.getString("doc_type"), resSet.getString("filenet_folder"), resSet.getString("filenet_folder_id"), resSet.getString("stage"));
				lst.add(f);
			}				
		}
		catch(Exception ex) {
			System.out.println("hpt.has.shinhan.db2.QueryDataDB2.ExcuteQueryStructureFolder.Error ExcuteQueryStructureFolder: " + ex);
		}
		return lst;
	}
	
	public static String QueryStringInsertInput(String id, String item, String doc, String cust, String path, long size, int isExist, int isQueryPath, String query) {
		
		String sql = "INSERT INTO SVFC.INPUT_LOGS (ID, ITEM, DOCREFID, CUSTID, PATH, SIZE, IS_EXIST, CREATED_DATE, CREATED, IS_QUERY_PATH, SQL_PATH) VALUES";
		
		
		try {			

			sql += " ('" + id + "', '" + item + "', '" + doc + "', '" + cust + "', '" +  path + "', '" + size + "', " + isExist;
			
			sql += ", '" + Time.parseTimeToString(new Date()) + "', 'App_Migrate', " + isQueryPath + ", '" + query + "')";
			
		}
		catch(Exception ex) {
			System.out.println("hpt.has.shinhan.db2.QueryDataDB2.QueryStringInsertInput.Error QueryStringInsertInput: " + ex);
		}
		return sql;
		
	}
	
	public static String QueryStringInsertInput1(String id, String item, String doc, String cust, String path, long size, int isExist, String AppName, String Segment, String docId, Date doc_created, String message) {
		
		String sql = "INSERT INTO SVFC.INPUT_LOGS (ID, ITEM, DOCREFID, CUSTID, PATH, SIZE, IS_EXIST, CREATED_DATE, CREATED, SEGMENT, DOCID, DOC_DATE, EXCEPTION) VALUES";
		
		
		try {			

			sql += " ('" + id + "', '" + item + "', '" + doc + "', '" + cust + "', '" +  path + "', '" + size + "', " + isExist;
			
			sql += ", '" + Time.parseTimeToString(new Date()) + "', '" + AppName + "' , '" + Segment + "' , '" + docId + "' , '" + Time.parseTimeToString1(doc_created) + "' , '" + message + "')";
			
		}
		catch(Exception ex) {
			System.out.println("hpt.has.shinhan.db2.QueryDataDB2.QueryStringInsertInput1.Error QueryStringInsertInput1: " + ex);
		}
		return sql;
		
	}
	
	public static String QueryStringInsertOutput(String id, String ecm_id, String input_id, Boolean is_folder) {
		
		String sql = "INSERT INTO SVFC.OUTPUT_LOGS (ID, ECM_ID, IS_FOLDER, INPUT_ID, CREATED_DATE, CREATED) VALUES";
		
		
		try {
			
			int isFolder = 0;
			
			if(is_folder == true) {
				
				isFolder = 1;
			}

			sql += " ('" + id + "', '" + ecm_id + "', " + isFolder + ", '" + input_id + "'";
			
			sql += ", '" + Time.parseTimeToString(new Date()) + "', 'App_Migrate')";
			
		}
		catch(Exception ex) {
			System.out.println("hpt.has.shinhan.db2.QueryDataDB2.QueryStringInsertOutput.Error QueryStringInsertOutput: " + ex);
		}
		return sql;		
	}
	
	public static String QueryStringInsertOutput1(String id, String ecm_id, String input_id, Boolean is_folder, String AppName, String docrefid, String message, int isupdate) {
		
		String sql = "INSERT INTO SVFC.OUTPUT_LOGS (ID, ECM_ID, IS_FOLDER, INPUT_ID, CREATED_DATE, CREATED, DOCREFID, EXCEPTION, ISCACHE) VALUES";
		
		
		try {
			
			int isFolder = 0;
			
			if(is_folder == true) {
				
				isFolder = 1;
			}

			sql += " ('" + id + "', '" + ecm_id + "', " + isFolder + ", '" + input_id + "'";
			
			sql += ", '" + Time.parseTimeToString(new Date()) + "', '" + AppName + "', '" + docrefid + "', '" + message + "', " + isupdate + ")";
			
		}
		catch(Exception ex) {
			System.out.println("hpt.has.shinhan.db2.QueryDataDB2.QueryStringInsertOutput1.Error QueryStringInsertOutput1: " + ex);
		}
		return sql;		
	}
	
	public static Boolean ExcuteQueryInsert(Connection con, String query) {
		
		Boolean result = true;
		
		try {
			
			Statement sqlStatement = con.createStatement();
			
			sqlStatement.executeUpdate(query);		
			
		}
		catch(Exception ex) {
			
			System.out.println("hpt.has.shinhan.db2.QueryDataDB2.ExcuteQueryInsert.Error ExcuteQueryInsert: " + ex);
			result = false;
		}
		return result;
	}
}

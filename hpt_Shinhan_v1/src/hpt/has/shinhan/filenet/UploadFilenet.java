package hpt.has.shinhan.filenet;

import java.io.File;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.filenet.api.constants.AutoUniqueName;
import com.filenet.api.constants.DefineSecurityParentage;
import com.filenet.api.constants.RefreshMode;

import com.filenet.api.core.Document;

import com.filenet.api.core.Factory;
import com.filenet.api.core.Folder;
import com.filenet.api.core.ObjectStore;
import com.filenet.api.core.ReferentialContainmentRelationship;

import hpt.has.shinhan.base64.ConvertBase64;
import hpt.has.shinhan.db2.ConnectionDB2;
import hpt.has.shinhan.db2.QueryDataDB2;
import hpt.has.shinhan.file.DownloadFileURL;
import hpt.has.shinhan.file.WriteTxtFile;
import hpt.has.shinhan.thread.DivideList;
import hpt.has.shinhan.thread.SegmentUpload;

public class UploadFilenet {
	
	static String uri = "http://10.148.50.62:9080/wsi/FNCEWS40MTOM/";
	static String username = "srvsvfcibmcm";
	static String password = "Shinhan01";
	static String optionalJAASStanzaName = "FileNetP8WSI";
	static String objectName = "Object01";
	static String root = "/SVFCRoot";	
	static String doc_class = "SHFDoc";
	static String fol_class = "SHFFolder";
	
	static String doc_name = "thanhtn";
	
	//static String folder_input = "C:\\Users\\vendor5\\Desktop\\File upload";
	//static String input = "C:\\Users\\vendor5\\Desktop\\File upload\\input.txt";
	//static String output = "C:\\Users\\vendor5\\Desktop\\File upload\\output.txt";
	//static String saveDir = "C:/Users/vendor5/Desktop/File upload";
	
	
	
//	static String jdbcClassName = "com.ibm.db2.jcc.DB2Driver";
//	static String url = "jdbc:db2://10.148.133.11:50000/";
//	static String url_file = "http://10.148.133.11:8080";
//	static String ICMNLSDB = "ICMNLSDB";
//	static String RMDB = "RMDB";
//	static String user = "db2inst1";
//	static String pass = "cfc4you";
	
	static String jdbcClassName = "com.ibm.db2.jcc.DB2Driver";
	static String url = "jdbc:db2://10.148.133.11:50000/";
	static String url_file = "http://10.148.133.11:8080";
	static String ICMNLSDB = "ICMNLSDB";
	static String RMDB = "RMDB";
	static String user = "db2inst1";
	static String pass = "cfc4you";
	
	static String url_log="jdbc:db2://10.148.50.61:55000/";
	static String CPEDB = "CPEDB";
//	static String user="db2inst1";
//	static String password="cfc4you";
	
	static String userlog="db2ecm1";
	static String passlog="Shinhan01";
	
	static String target = ".\\target\\100089921.pdf";
	static PropertiesTemplate p = new PropertiesTemplate();
	
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
			"	where a.sourceitemid = t.itemid and t.itemid in ('A1001001A08A15B61824A80686', 'A1001001A08A15B61824D80440', 'A1001001A08A15B61824F98907', 'A1001001A08A15B61824H89800')";
	
	static String sql = "select obj_itemid as itemid," + 
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
	
	public static void main(String[] args)
	{
		
//		System.out.println("sample jdbcClassName = 'com.ibm.db2.jcc.DB2Driver'");
//		System.out.println("sample url = 'jdbc:db2://10.148.133.11:50000/'");
//		System.out.println("sample user = 'db2inst1'");
//		System.out.println("sample password = 'cfc4you'");
//		System.out.println("sample url_file = 'http://10.148.133.11:8080'");
//		System.out.println("sample user_file = 'db2inst1'");
//		System.out.println("sample password_file = 'cfc4you'");
//		System.out.println("Ready for a new command");
		@SuppressWarnings({ "unused", "resource" })
		Scanner scanner = new Scanner(System.in);
//		System.out.print("jdbcClassName = ");
//		String _jdbcClassName = scanner.nextLine();
//		System.out.print("url = ");
//		String _url = scanner.nextLine();
//		System.out.print("user = ");
//		String _user = scanner.nextLine();
//		System.out.print("password = ");
//		String _password = scanner.nextLine();
//		System.out.print("url_file = ");
//		String _url_file = scanner.nextLine();
//		System.out.print("user_file = ");
//		String _user_file = scanner.nextLine();
//		System.out.print("password_file = ");
//		String _password_file = scanner.nextLine();
		
//		long start = System.currentTimeMillis();
		
		Handler1(uri, username, password, optionalJAASStanzaName, objectName, doc_class, doc_name, root, fol_class, url + ICMNLSDB, url + RMDB, url_log + CPEDB, url_file, user, pass, userlog, passlog, jdbcClassName, user, pass, 10);
		
//	    long end = System.currentTimeMillis();
//
//	    NumberFormat formatter = new DecimalFormat("#0.00000");
//	    System.out.println("Execution time is " + formatter.format((end - start) / 1000d) + " seconds");
	    
	}
	
	public static void Handler1(String url_filenet, String user_filenet, String pass_filenet, String optional_filenet, String obj_filenet, String class_filenet, String doc_filenet, String root_filenet, String fol_filenet, String url_DB2_Pro, String url_DB2_Path, String url_DB2_Log, String url_file, String user_DB2, String pass_DB2, String user_log, String pass_log, String driver, String user_file, String pass_file, int volume) {
		
		try {
			
			String currentDirectory = System.getProperty("user.dir");
			
			Connection conPro = ConnectionDB2.ConnectDB2(driver, url_DB2_Pro, user_DB2, pass_DB2);
			
			Connection conPath = ConnectionDB2.ConnectDB2(driver, url_DB2_Path, user_file, pass_file);
			
			String sqlInfo = QueryDataDB2.QueryStringInfo2("2018-12-01", "2018-12-31");
			
			List<PropertiesTemplate> lstPro = QueryDataDB2.ExcuteQueryProDB2(conPro, sqlInfo);
			
			for(int i = 0; i < lstPro.size(); i ++) {			
				
				String sql = QueryDataDB2.QueryStringPath(lstPro.get(i).Item);
				
				List<String> lstPath = QueryDataDB2.ExcuteQueryPathDB2(conPath, sql);
				
				String path = "";
				String minetype = "";
				String docname = "";
				
				if(lstPath != null && lstPath.size() >= 3) {
					
					path = lstPath.get(0);
					minetype = lstPath.get(1);
					docname = lstPath.get(2);
				}
				
				PropertiesTemplate.setProperty(lstPro.get(i), "FilePath", path);
				PropertiesTemplate.setProperty(lstPro.get(i), "MimeType", minetype);
				PropertiesTemplate.setProperty(lstPro.get(i), "FileName", docname);
			}
			
			List<List<PropertiesTemplate>> lst = DivideList.distributeList2(lstPro, volume);
			
			for(int i = 0; i < lst.size(); i++) {
				
				SegmentUpload seg = new SegmentUpload(lst.get(i), url_filenet, user_filenet, pass_filenet, optional_filenet, obj_filenet, class_filenet, root_filenet, fol_filenet, url_file, driver, url_DB2_Log, user_log, pass_log, currentDirectory, (i + 1));
				
				seg.start();
			}
			
			ConnectionDB2.CloseBD2(conPro);
			ConnectionDB2.CloseBD2(conPath);
		}
		catch(Exception ex) {
			System.out.println("hpt.has.shinhan.filenet.UploadFilenet.Handler1.Error Handler1: " + ex);
		}
	}
	
	public static void Handler(String url_filenet, String user_filenet, String pass_filenet, String optional_filenet, String obj_filenet, String class_filenet, String doc_filenet, String root_filenet, String fol_filenet, String url_DB2_Pro, String url_DB2_Path, String url_file, String user_DB2, String pass_DB2, String driver, String user_file, String pass_file) {
		
		try {
			String currentDirectory = System.getProperty("user.dir");
			ObjectStore object_store = ConnectFilenet.OpenConnection(url_filenet, user_filenet, pass_filenet, optional_filenet, obj_filenet);
			
			Connection conPro = ConnectionDB2.ConnectDB2(driver, url_DB2_Pro, user_DB2, pass_DB2);
			
			Connection conPath = ConnectionDB2.ConnectDB2(driver, url_DB2_Path, user_file, pass_file);
			
			String sqlInfo = QueryDataDB2.QueryStringInfo2("2019-01-01", "2019-01-04");
			
			List<PropertiesTemplate> lstPro = QueryDataDB2.ExcuteQueryProDB2(conPro, sqlInfo);
			
			//OutputStreamWriter in = WriteTxtFile.OpenFileText(input);
			//OutputStreamWriter out = WriteTxtFile.OpenFileText(output);
			
			OutputStreamWriter in = WriteTxtFile.OpenFileText(currentDirectory + "\\input.txt");
			OutputStreamWriter out = WriteTxtFile.OpenFileText(currentDirectory + "\\output.txt");
			
			for(int i = 0; i < lstPro.size(); i++) {
				
				System.out.println("folder filename " + lstPro.get(i).CustID + " list size " + lstPro.size());
				CreateFolder.createFolder(object_store, root_filenet, lstPro.get(i).CustID, fol_filenet);		
				
				String sqlPath = QueryDataDB2.QueryStringPath(lstPro.get(i).Item);
				
				List<String> lstPath = QueryDataDB2.ExcuteQueryPathDB2(conPath, sqlPath);
				
				String path = "";
				String minetype = "";
				String docname = "";
				
				if(lstPath != null && lstPath.size() > 1) {
					
					path = lstPath.get(0);
					minetype = lstPath.get(1);
					docname = lstPath.get(2);
				}
				
				path = url_file + path;
				
				//String dir = DownloadFileURL.DownloadFile(path, saveDir);
				String dir = DownloadFileURL.DownloadFile(path, currentDirectory);
				
				int del = 1;
				
				File file = null;
				
				if(dir == null) {
					
					//path = "http://10.148.133.11:8080/data10/lbosdata/00001/27/L1.A1001001A19D27A00254E76139.V1";
					//dir = DownloadFileURL.DownloadFile(path, saveDir);
					path = url_file + "/data10/lbosdata/00001/27/L1.A1001001A19D27A00254E76139.V1";
					dir = DownloadFileURL.DownloadFile(path, currentDirectory);
					del = 0;
					
				}
				file = new File(dir);
				//String output = folder_input + "\\" + docname;
				String output = currentDirectory + "\\" + docname;
				String encodestring = ConvertBase64.encodeFileToBase64Binary(file);
				dir = ConvertBase64.writeBase64ToFile(encodestring, output, minetype);
				file.delete();
				file = new File(dir);
							
				//				
				
				WriteTxtFile.WriteFileText(in, (i + 1) + "-" +  lstPro.get(i).Item + " - " + lstPro.get(i).Doc_Ref_ID + " - " + lstPro.get(i).CustID + " - " + path + " - " + file.length() + " - " + del);
				WriteTxtFile.WriteFileText(in, "\n");
				
				String id = UploadDocument1(object_store, file, class_filenet, docname, root_filenet + "/" + lstPro.get(i).CustID, lstPro.get(i));
                
				WriteTxtFile.WriteFileText(out, (i + 1) + "-" +  id);
				WriteTxtFile.WriteFileText(out, "\n");
				
				file.delete();
				
			}	
			
			WriteTxtFile.CloseFileText(in);
			WriteTxtFile.CloseFileText(out);
			ConnectionDB2.CloseBD2(conPath);
			ConnectionDB2.CloseBD2(conPro);
			ConnectFilenet.CloseConnection();
			
			System.out.println("The current working directory is " + currentDirectory);
		}
		catch(Exception ex) {
			System.out.println("hpt.has.shinhan.filenet.UploadFilenet.Handler.Error Handler: " + ex);
		}
	}
	
	public static void UploadFileFromCM(String doc_class, String doc_name, List<PropertiesTemplate> lst, ObjectStore object_store, Connection con) {
        
		try{        	
        	
            for (int i = 0; i < lst.size(); i++) {        		
        		
            	CreateFolder.createFolder(object_store, root, lst.get(i).CustID, fol_class);
            	
 //           	String sql = QueryDataDB2.QueryStringPath(lst.get(i).Doc_Ref_ID);
            	
//            	String path = QueryDataDB2.ExcuteQueryPathDB2(con, sql);
//            	
//            	File file = DownloadFileURL.DownloadFileFromURL(path);
//            	
//                UploadDocument(object_store, file, doc_class, doc_name + "_" + file.getName(), root + "/" + lst.get(i).CustID, lst.get(i));
            }            
        }
		catch (Exception e) {
			// TODO: handle exception
	    	System.out.println("hpt.has.shinhan.filenet.UploadFilenet.UploadFileFromCM.Error UploadFileFromCM: " + e);
		}
		
	}

	public static void UploadFileInFolder(String folder_input, String doc_class, String doc_name, List<PropertiesTemplate> lst) {
        try{
        	
        	ObjectStore object_store = ConnectFilenet.OpenConnection(uri, username, password, optionalJAASStanzaName, objectName);         
		
            List<File> file_in_folder = ListFilesInFolder(folder_input);   
            
        	int i = 0;
            
            for (File file : file_in_folder) {        		
        		
            	CreateFolder.createFolder(object_store, root, lst.get(i).CustID, fol_class);
                UploadDocument(object_store, file, doc_class, doc_name + "_" + file.getName(), root + "/" + lst.get(i).CustID, lst.get(i));
                i++;
            }
            
            ConnectFilenet.CloseConnection();
        }
		catch (Exception e) {
			// TODO: handle exception
	    	System.out.println("hpt.has.shinhan.filenet.UploadFilenet.UploadFileInFolder.Error UploadFileInFolder: " + e);
		}
		
	}
	
	public static List<File> ListFilesInFolder(String path_folder) {
        List<File> out_list = new ArrayList<>();
        try{
            File folder = new File(path_folder);
            File[] listOfFiles = folder.listFiles();
            

            for (File file : listOfFiles) {
                if (file.isFile()) {
                    out_list.add(file);
                }
            }           
        }
        catch (Exception e) {
			// TODO: handle exception
	    	System.out.println("hpt.has.shinhan.filenet.UploadFilenet.ListFilesInFolder.Error ListFilesInFolder: " + e);
		}
        return out_list;		
	}
	
	public static void UploadDocument(ObjectStore object_store ,File file_input, String doc_class, String doc_name, String path, PropertiesTemplate p) {
		
        try{
        	
            Document doc_up = CEUtil.createDocWithContent(file_input, object_store, doc_name, doc_class);
            
            for(int i = 0; i < p.listPro.size(); i++) {
            	
            	String key = p.listPro.get(i);
            	if(key == "SCANNEDDATE" || key == "CREATE_DT") {
            		doc_up.getProperties().putValue(key, PropertiesTemplate.getPropertyProDate(p, key));
            	}
            	else {
            		doc_up.getProperties().putValue(key, PropertiesTemplate.getPropertyPro(p, key));
            	}
            }
            
            // Set Date Created of Document           
            doc_up.set_DateCreated(PropertiesTemplate.getPropertyProDate(p, "CREATE_DT"));
            
            doc_up.set_DateLastModified(PropertiesTemplate.getPropertyProDate(p, "CREATE_DT"));
            
            // Set Created By
            doc_up.set_Creator(PropertiesTemplate.getPropertyPro(p,"UPLOADCHANNEL"));
            
            doc_up.set_LastModifier(PropertiesTemplate.getPropertyPro(p,"UPLOADCHANNEL"));
            
            //doc_up.set_MimeType(arg0);
            
            CEUtil.checkinDoc(doc_up);
            
            Folder curent_folder = Factory.Folder.fetchInstance(object_store, path, null);
            ReferentialContainmentRelationship rr = curent_folder.file(doc_up, AutoUniqueName.AUTO_UNIQUE, doc_up.get_Name(), DefineSecurityParentage.DEFINE_SECURITY_PARENTAGE); 
            rr.save(RefreshMode.REFRESH);           
            
            rr.get_Id();
            
            System.out.println("hpt.has.shinhan.filenet.UploadFilenet.UploadDocument.Info Upload Document successful");
        }
        catch (Exception e) {
			// TODO: handle exception
	    	System.out.println("hpt.has.shinhan.filenet.UploadFilenet.UploadDocument.Error UploadDocument: " + e);
		}	
		
	}
	
	public static void UploadFile(File file, String doc_class, String doc_name, String path, PropertiesTemplate p) {
		try{
            ObjectStore object_store = ConnectFilenet.OpenConnection(uri, username, password, optionalJAASStanzaName, objectName);
            
            UploadDocument(object_store, file, doc_class, doc_name + "_" + file.getName(), path, p);
            
            ConnectFilenet.CloseConnection();
        }
		catch (Exception e) {
			// TODO: handle exception
	    	System.out.println("hpt.has.shinhan.filenet.UploadFilenet.UploadFile.Error UploadFile: " + e);
		}
	}
	
	public static void DownloadDocument(ObjectStore object_store, String path_in, String folder_out) {

        try{
            Document doc_down = CEUtil.fetchDocByPath(object_store, path_in);
		
            CEUtil.writeDocContentToFile(doc_down, folder_out);
            
            System.out.println("hpt.has.shinhan.filenet.UploadFilenet.DownloadDocument.Download Document successful");
        }
        catch (Exception e) {
			// TODO: handle exception
	    	System.out.println("hpt.has.shinhan.filenet.UploadFilenet.DownloadDocument.Error DownloadDocument: " + e);
		}		
	}

	public static String UploadDocument1(ObjectStore object_store ,File file_input, String doc_class, String doc_name, String path, PropertiesTemplate p) {
		
		String result = null;
        try{
        	
            Document doc_up = CEUtil.createDocWithContent(file_input, object_store, doc_name, doc_class);
            
            for(int i = 0; i < p.listPro.size(); i++) {
            	
            	String key = p.listPro.get(i);
            	if(key == "SCANNEDDATE" || key == "CREATE_DT") {
            		doc_up.getProperties().putValue(key, PropertiesTemplate.getPropertyProDate(p, key));
            	}
            	else {
            		doc_up.getProperties().putValue(key, PropertiesTemplate.getPropertyPro(p, key));
            	}
            }
            
            // Set Date Created of Document           
            doc_up.set_DateCreated(PropertiesTemplate.getPropertyProDate(p, "CREATE_DT"));
            
            doc_up.set_DateLastModified(PropertiesTemplate.getPropertyProDate(p, "CREATE_DT"));
            
            // Set Created By
            doc_up.set_Creator(PropertiesTemplate.getPropertyPro(p,"UPLOADCHANNEL"));
            
            doc_up.set_LastModifier(PropertiesTemplate.getPropertyPro(p,"UPLOADCHANNEL"));
            
            //doc_up.set_MimeType(contentType);
            
            CEUtil.checkinDoc(doc_up);
            
            Folder curent_folder = Factory.Folder.fetchInstance(object_store, path, null);
            ReferentialContainmentRelationship rr = curent_folder.file(doc_up, AutoUniqueName.AUTO_UNIQUE, doc_up.get_Name(), DefineSecurityParentage.DEFINE_SECURITY_PARENTAGE); 
            rr.save(RefreshMode.REFRESH);           
            
            //result = "" + rr.get_Id();
            
            result = "" + doc_up.get_Id().toString();
            
            System.out.println("hpt.has.shinhan.filenet.UploadFilenet.UploadDocument1.Info Upload Document successful");
        }
        catch (Exception e) {
			// TODO: handle exception
	    	System.out.println("hpt.has.shinhan.filenet.UploadFilenet.UploadDocument1.Error UploadDocument: " + e);
		}	
		return result;
	}
	
	public static String UploadDocument2(ObjectStore object_store ,File file_input, String doc_class, String doc_name, String path, MetaData p) {
		
		String result = null;
        try{
        	
            Document doc_up = CEUtil.createDocWithContent(file_input, object_store, doc_name, doc_class);
            
            for(int i = 0; i < p.getList().size(); i++) {
            	
            	String key = p.getList().get(i);
            	
            	if(key == "SCANNEDDATE" || key == "CREATE_DT" || key == "UPDATE_DT") {
            		
            		doc_up.getProperties().putValue(key, (Date) p.getProperty(key));
            	}
            	else {
            		
            		doc_up.getProperties().putValue(key, (String) p.getProperty(key));
            	}
            }
            
            // Set Date Created of Document           
            doc_up.set_DateCreated((Date) p.getProperty("CREATE_DT"));
            
            doc_up.set_DateLastModified((Date) p.getProperty("CREATE_DT"));
            
            // Set Created By
            doc_up.set_Creator((String) p.getProperty("UPLOADCHANNEL"));
            
            doc_up.set_LastModifier((String) p.getProperty("UPLOADCHANNEL"));
            
            //doc_up.set_MimeType(contentType);
            
            CEUtil.checkinDoc(doc_up);
            
            Folder curent_folder = Factory.Folder.fetchInstance(object_store, path, null);
            ReferentialContainmentRelationship rr = curent_folder.file(doc_up, AutoUniqueName.AUTO_UNIQUE, doc_up.get_Name(), DefineSecurityParentage.DEFINE_SECURITY_PARENTAGE); 
            rr.save(RefreshMode.REFRESH);           
            
            //result = "" + rr.get_Id();
            
            result = "" + doc_up.get_Id().toString();
            
            System.out.println("hpt.has.shinhan.filenet.UploadFilenet.UploadDocument2.Info Upload Document successful: " + result);
        }
        catch (Exception e) {
			// TODO: handle exception
	    	System.out.println("hpt.has.shinhan.filenet.UploadFilenet.UploadDocument2.Error UploadDocument2: " + e);
		}	
		return result;
	}
	
}



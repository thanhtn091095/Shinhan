package hpt.has.shinhan.thread;

import java.io.File;
import java.sql.Connection;
import java.util.Date;
import java.util.List;

import com.filenet.api.core.ObjectStore;
import hpt.has.shinhan.db2.ConnectionDB2;
import hpt.has.shinhan.db2.GenerateUniqueIDUsingGUID;
import hpt.has.shinhan.db2.QueryDataDB2;
import hpt.has.shinhan.file.DownloadFileURL;
import hpt.has.shinhan.filenet.ConnectFilenet;
import hpt.has.shinhan.filenet.CreateFolder;
import hpt.has.shinhan.filenet.MetaData;
import hpt.has.shinhan.filenet.UploadFilenet;
import hpt.has.shinhan.oracle.ConnectionOracle;
import hpt.has.shinhan.oracle.QueryOracle;
import hpt.has.shinhan.oracle.StructureFolder;

public class SegmentMigrateMain extends Thread {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	List<MetaData> LIST_META_DATA;
	
	String OBJECT_TYPE;
	String ROOT_FOLDER;
	String FOLDER_ID;
	String URL_FILENET;
	String CURRENT_DIRECTORY;
	String USER_NAME_FILENET;
	String PASS_WORD_FILENET;
	String OPTIONAL_FILENET;
	String OBJECT_STORE;
	
	String HOST_GET_FILE;
	
	String DRIVER_DB2;
	String URL_LOG;
	String USER_NAME_LOG;
	String PASS_WORD_LOG;
	
	String DRIVER_ORACLE;
	String HOST_ORACLE;
	String PORT_ORACLE;
	String DATA_NAME_ORACLE;
	String USER_NAME_ORACLE;
	String PASS_WORD_ORACLE;
	
	String APP_NAME;
	
	int VOLUME;
	
	public SegmentMigrateMain(List<MetaData> _lst_meta_data, String _url_filenet, String _user_filenet, String _pass_filenet, String _optional_filenet, String _obj_filenet, String _class_doc, String _root, String _folder, String _url_get_file, String _driver_db2, String _url_Log, String _user_log, String _pass_log, String _url_oracle, String _port_oracle, String _data_name, String _user_oracle, String _pass_oracle, String _driver_oracle, String _current_dir, String _app_name, int _volume) {
		
		this.LIST_META_DATA = _lst_meta_data;
		
		this.OBJECT_TYPE = _class_doc;
		this.ROOT_FOLDER = _root;
		this.FOLDER_ID = _folder;
		this.URL_FILENET = _url_filenet;
		
		this.HOST_GET_FILE = _url_get_file;		
		
		this.CURRENT_DIRECTORY = _current_dir;
		this.VOLUME = _volume;
		this.APP_NAME = _app_name;
		
		this.URL_FILENET = _url_filenet;
		this.USER_NAME_FILENET = _user_filenet;
		this.PASS_WORD_FILENET = _pass_filenet;
		this.OPTIONAL_FILENET = _optional_filenet;
		this.OBJECT_STORE = _obj_filenet;
		
		this.DRIVER_DB2 = _driver_db2;
		this.URL_LOG = _url_Log;
		this.USER_NAME_LOG = _user_log;
		this.PASS_WORD_LOG = _pass_log;
		
		this.DRIVER_ORACLE = _driver_oracle;
		this.HOST_ORACLE = _url_oracle;
		this.PORT_ORACLE = _port_oracle;
		this.DATA_NAME_ORACLE = _data_name;
		this.USER_NAME_ORACLE = _user_oracle;
		this.PASS_WORD_ORACLE = _pass_oracle;		
	}
	
	@Override
	public void run() {		
		UploadFileToFilenet();
	}
	
	public void UploadFileToFilenet() {		
		try {
			
			ObjectStore object_store = ConnectFilenet.OpenConnection(URL_FILENET, USER_NAME_FILENET, PASS_WORD_FILENET, OPTIONAL_FILENET, OBJECT_STORE);
			Connection conLog = ConnectionDB2.ConnectDB2(DRIVER_DB2, URL_LOG, USER_NAME_LOG, PASS_WORD_LOG);
			Connection conPro = ConnectionOracle.CreateConnection(HOST_ORACLE, PORT_ORACLE, DATA_NAME_ORACLE, USER_NAME_ORACLE, PASS_WORD_ORACLE, DRIVER_ORACLE);
			
			String queryStructure = QueryDataDB2.QueryStringStructureFolder();
			List<StructureFolder> lstStructureFolder = QueryDataDB2.ExcuteQueryStructureFolder(conLog, queryStructure);
			
			// TODO Auto-generated method stub
			for(int i = 0; i < LIST_META_DATA.size(); i++) {
				
//				Boolean create = CreateFolder.createFolder(object_store, ROOT_FOLDER, LIST_META_DATA.get(i).getProperty("CUSTID"), FOLDER_ID);	
				
				Boolean create = true;
				
				String folderName = StructureFolder.SearchFolderFromDocID(lstStructureFolder, 0, lstStructureFolder.size(), Integer.valueOf(LIST_META_DATA.get(i).getProperty("DOCID")));
				
				if(folderName == null || folderName == "") {
					
					folderName = "Others";
				}
				
				String pathFull = HOST_GET_FILE + LIST_META_DATA.get(i).getProperty("FILEPATH");
				
				String dir = DownloadFileURL.DownloadFile1(pathFull, CURRENT_DIRECTORY, LIST_META_DATA.get(i).getProperty("MIMETYPE"));
				
				int del = 1;
				
				File file = null;
				
				String input = GenerateUniqueIDUsingGUID.generateUniqueID();
				String out = GenerateUniqueIDUsingGUID.generateUniqueID();
				
				if(dir == null) {
					
					del = 0;
					create = false;
					
					String insert1 = QueryDataDB2.QueryStringInsertInput1(input, LIST_META_DATA.get(i).getProperty("ITEM"), LIST_META_DATA.get(i).getProperty("DOCREFID"), LIST_META_DATA.get(i).getProperty("CUSTID"), pathFull, 0, del, APP_NAME, VOLUME + "", LIST_META_DATA.get(i).getProperty("DOCID"), (Date) LIST_META_DATA.get(i).getProperty("CREATE_DT"), "");
					QueryDataDB2.ExcuteQueryInsert(conLog, insert1);
					
					Date d1 = (Date) LIST_META_DATA.get(i).getProperty("CREATE_DT");
					System.out.println("hpt.has.shinhan.thread.SegmentMigrate.UploadFileToFilenet.Infor CREATE_DT: " + d1.toString());
					
					System.out.println("hpt.has.shinhan.thread.SegmentMigrate.UploadFileToFilenet.Infor insert1: " + insert1);
					
					String insert2 = QueryDataDB2.QueryStringInsertOutput1(out, null, input, create, APP_NAME, LIST_META_DATA.get(i).getProperty("DOCREFID"), "", 0);					
					QueryDataDB2.ExcuteQueryInsert(conLog, insert2);
					
				}
				else {
										
					file = new File(dir);
					
					String insert1 = QueryDataDB2.QueryStringInsertInput1(input, LIST_META_DATA.get(i).getProperty("ITEM"), LIST_META_DATA.get(i).getProperty("DOCREFID"), LIST_META_DATA.get(i).getProperty("CUSTID"), pathFull, file.length(), del, APP_NAME, VOLUME + "", LIST_META_DATA.get(i).getProperty("DOCID"), (Date) LIST_META_DATA.get(i).getProperty("CREATE_DT"), "");
					QueryDataDB2.ExcuteQueryInsert(conLog, insert1);
					
					System.out.println("hpt.has.shinhan.thread.SegmentMigrate.UploadFileToFilenet.Infor insert1: " + insert1);
					
					Date d1 = (Date) LIST_META_DATA.get(i).getProperty("CREATE_DT");
					
					System.out.println("hpt.has.shinhan.thread.SegmentMigrate.UploadFileToFilenet.Infor CREATE_DT: " + d1.toString());
					
					String ecm_id = UploadFilenet.UploadDocument2(object_store, file, OBJECT_TYPE, LIST_META_DATA.get(i).getProperty("FILENAME"), ROOT_FOLDER + "/" + folderName.toUpperCase(), LIST_META_DATA.get(i));
		            
					Boolean isUpdate = false;
					
					if(ecm_id != null && ecm_id != "") {
						String updateString = QueryOracle.QueryStringUpdateOracle(LIST_META_DATA.get(i).getProperty("DOCREFID"), ecm_id);
						isUpdate = QueryOracle.ExcuteQueryUpdate(conPro, updateString);						
					}
					
					int intIsUpdate = 0;
					
					if(isUpdate == true) {
						intIsUpdate = 1;
					}
					
					String insert2 = QueryDataDB2.QueryStringInsertOutput1(out, ecm_id, input, create, APP_NAME, LIST_META_DATA.get(i).getProperty("DOCREFID"), "", intIsUpdate);					
					QueryDataDB2.ExcuteQueryInsert(conLog, insert2);
					
					file.delete();
				}				
				
			}
			ConnectFilenet.CloseConnection();
			ConnectionDB2.CloseBD2(conLog);		
			ConnectionOracle.CloseConnection(conPro);
		}
		catch(Exception e) {
			
			System.out.println("hpt.has.shinhan.thread.SegmentMigrate.UploadFileToFilenet.Error UploadFileToFilenet: " + e);
		}
	}
}

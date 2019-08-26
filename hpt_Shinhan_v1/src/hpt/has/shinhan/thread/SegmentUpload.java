package hpt.has.shinhan.thread;

import java.io.File;
import java.sql.Connection;
import java.util.List;

import com.filenet.api.core.ObjectStore;

import hpt.has.shinhan.base64.ConvertBase64;
import hpt.has.shinhan.db2.ConnectionDB2;
import hpt.has.shinhan.db2.GenerateUniqueIDUsingGUID;
import hpt.has.shinhan.db2.QueryDataDB2;
import hpt.has.shinhan.file.DownloadFileURL;
import hpt.has.shinhan.filenet.ConnectFilenet;
import hpt.has.shinhan.filenet.CreateFolder;
import hpt.has.shinhan.filenet.PropertiesTemplate;
import hpt.has.shinhan.filenet.UploadFilenet;

public class SegmentUpload extends Thread{
	
	List<PropertiesTemplate> _lstPro;
	String class_filenet;
	String root_filenet;
	String fol_filenet;
	String url_file;
	String currentDirectory;
	String url_filenet;
	String user_filenet;
	String pass_filenet;
	String optional_filenet;
	String obj_filenet;	
	String driver;
	String url_DB2_Log;
	String user_log;
	String pass_log;
	
	int index;
	
	public SegmentUpload(List<PropertiesTemplate> _lst, String _url_filenet, String _user_filenet, String _pass_filenet, String _optional_filenet, String _obj_filenet, String _class_Doc, String _root, String _folder, String _url, String _driver, String _url_DB2_Log, String _user_log, String _pass_log, String _currendir, int _id) {
		
		this._lstPro = _lst;
		
		this.class_filenet = _class_Doc;
		this.root_filenet = _root;
		this.fol_filenet = _folder;
		this.url_file = _url;
		this.currentDirectory = _currendir;
		this.index = _id;
		
		this.url_filenet = _url_filenet;
		this.user_filenet = _user_filenet;
		this.pass_filenet = _pass_filenet;
		this.optional_filenet = _optional_filenet;
		this.obj_filenet = _obj_filenet;
		
		this.driver = _driver;
		this.url_DB2_Log = _url_DB2_Log;
		this.user_log = _user_log;
		this.pass_log = _pass_log;
	}
	@Override
	public void run() {
		
		try {
			
			ObjectStore object_store = ConnectFilenet.OpenConnection(url_filenet, user_filenet, pass_filenet, optional_filenet, obj_filenet);
			
			Connection conLog = ConnectionDB2.ConnectDB2(driver, url_DB2_Log, user_log, pass_log);
			// TODO Auto-generated method stub
			for(int i = 0; i < _lstPro.size(); i++) {
				
				Boolean create = CreateFolder.createFolder(object_store, root_filenet, _lstPro.get(i).CustID, fol_filenet);	
				
				String pathFull = url_file + _lstPro.get(i).FilePath;
				String dir = DownloadFileURL.DownloadFile(pathFull, currentDirectory);
				
				int del = 1;
				
				File file = null;
				
				String input = GenerateUniqueIDUsingGUID.generateUniqueID();
				String out = GenerateUniqueIDUsingGUID.generateUniqueID();
				
				if(dir == null) {
					
					del = 0;					
					String insert1 = QueryDataDB2.QueryStringInsertInput(input, _lstPro.get(i).Item, _lstPro.get(i).Doc_Ref_ID, _lstPro.get(i).CustID, pathFull, 0, del, 0, "false");
					QueryDataDB2.ExcuteQueryInsert(conLog, insert1);					
					String insert2 = QueryDataDB2.QueryStringInsertOutput(out, null, input, create);					
					QueryDataDB2.ExcuteQueryInsert(conLog, insert2);
				}
				else {
					
					file = new File(dir);
					String output = currentDirectory + "\\" + _lstPro.get(i).FileName;
					String encodestring = ConvertBase64.encodeFileToBase64Binary(file);
					dir = ConvertBase64.writeBase64ToFile(encodestring, output, _lstPro.get(i).MimeType);
					file.delete();
					file = new File(dir);
					
					String insert1 = QueryDataDB2.QueryStringInsertInput(input, _lstPro.get(i).Item, _lstPro.get(i).Doc_Ref_ID, _lstPro.get(i).CustID, pathFull, file.length(), del, 3, "true");
					
					QueryDataDB2.ExcuteQueryInsert(conLog, insert1);
					
					String id = UploadFilenet.UploadDocument1(object_store, file, class_filenet, _lstPro.get(i).FileName, root_filenet + "/" + _lstPro.get(i).CustID, _lstPro.get(i));
		            
					String insert2 = QueryDataDB2.QueryStringInsertOutput(out, id, input, create);
					
					QueryDataDB2.ExcuteQueryInsert(conLog, insert2);
					
					file.delete();
				}				
				
			}
			ConnectFilenet.CloseConnection();
			ConnectionDB2.CloseBD2(conLog);
			//ConnectionDB2.CloseBD2(conPath);
			
		}
		catch(Exception e) {
			
			System.out.println("hpt.has.shinhan.thread.SegmentUpload.run.Error run: " + e);
		}
	}	
}

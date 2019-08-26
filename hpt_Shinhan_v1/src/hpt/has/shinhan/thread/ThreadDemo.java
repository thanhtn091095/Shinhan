package hpt.has.shinhan.thread;

import java.io.File;
import java.util.List;

import com.filenet.api.core.ObjectStore;

import hpt.has.shinhan.filenet.ConnectFilenet;
import hpt.has.shinhan.filenet.CreateFolder;
import hpt.has.shinhan.filenet.PropertiesTemplate;
import hpt.has.shinhan.filenet.UploadFilenet;

public class ThreadDemo extends Thread {
	
	String url_filenet;
	String user_filenet;
	String pass_filenet;
	String optional_filenet;
	String obj_filenet;
	String root_filenet;
	String fol_filenet;
	String class_filenet;
	String docname;
	List<PropertiesTemplate> _lstPro;
	

	public ThreadDemo(String url, String user, String pass, String optional, String object, String root, String folder, String classDoc, String nameDoc, List<PropertiesTemplate> lst) {
		this.url_filenet = url;
		this.user_filenet = user;
		this.pass_filenet = pass;
		this.optional_filenet = optional;
		this.obj_filenet = object;
		this.root_filenet = root;
		this.fol_filenet = folder;
		this.class_filenet = classDoc;
		this.docname = nameDoc;
		this._lstPro = lst;
	}

	@Override
	public void run() {
		
		ObjectStore object_store = ConnectFilenet.OpenConnection(url_filenet, user_filenet, pass_filenet, optional_filenet, obj_filenet);
		
		for(int i = 0; i < _lstPro.size(); i++) {
			
			Boolean bol = CreateFolder.createFolder(object_store, root_filenet, _lstPro.get(i).CustID, fol_filenet);
			
			File file = new File(".\\target\\100089921.pdf");
			
			String id = UploadFilenet.UploadDocument1(object_store, file, class_filenet, docname, root_filenet + "/" + _lstPro.get(i).CustID, _lstPro.get(i));
		}
		
		ConnectFilenet.CloseConnection();		
	}
	
	
}

package hpt.has.shinhan.filenet;

import java.util.Iterator;


import com.filenet.api.collection.RepositoryRowSet;
import com.filenet.api.constants.RefreshMode;
import com.filenet.api.core.Factory;
import com.filenet.api.core.Folder;
import com.filenet.api.core.ObjectStore;
import com.filenet.api.query.RepositoryRow;
import com.filenet.api.query.SearchSQL;
import com.filenet.api.query.SearchScope;
import com.filenet.api.util.Id;

public class CreateFolder {
	
	public static boolean createFolder(ObjectStore os, String fPath, String fName, String className)
    {
		try {
			
			Folder f = Factory.Folder.fetchInstance(os, fPath, null);
	        Folder nf = null;
	        if (className.equals(""))
	        {
	        	nf = Factory.Folder.createInstance(os, null);
	        }
	        else 
	        {  
	        	nf = Factory.Folder.createInstance(os, className);
	        }

	        SearchSQL sqlObject = new SearchSQL();
	        
			String queryFolder = "SELECT Id, FolderName, Name, PathName FROM " + className + " WHERE FolderName = '" + fName + "'";
			
			System.out.println("hpt.has.shinhan.filenet.CreateFolder.createFolder.Info: queryFolder: " + queryFolder);
			
			sqlObject.setQueryString(queryFolder);
			
			SearchScope search = new SearchScope(os);
			RepositoryRowSet myRows = search.fetchRows(sqlObject, null, null, null);	

			
			@SuppressWarnings("rawtypes")
			Iterator iter = myRows.iterator();
			
			Id Id = null;
			String pathParent = fPath + "/" + fName;
			
			while (iter.hasNext()) {		
				
				RepositoryRow row = (RepositoryRow) iter.next();
//				String Id = row.getProperties().get("Id").getStringValue();
				
				String path = row.getProperties().get("PathName").getStringValue();
				
				System.out.println("hpt.has.shinhan.filenet.CreateFolder.createFolder.Info: path: " + path);
				
				System.out.println("hpt.has.shinhan.filenet.CreateFolder.createFolder.Info: pathParent: " + pathParent);
//				
				if(path.equals(pathParent)) {
					Id = row.getProperties().get("Id").getIdValue();
					System.out.println("hpt.has.shinhan.filenet.CreateFolder.createFolder.Info: equals: " + true);
				}				
				
				System.out.println("hpt.has.shinhan.filenet.CreateFolder.createFolder.Info: Id: " + Id);
				
//				Id = row.getProperties().get("FolderName").getStringValue();
			}
			
			if(Id == null) {
				
				nf.set_Parent(f);
				nf.set_FolderName(fName);				
		        nf.save(RefreshMode.REFRESH);  
			}
			else {
				
				System.out.println("hpt.has.shinhan.filenet.CreateFolder.createFolder.Info: createFolder: Folder name: " + fName + " is exist");
				return false;
			}		      
			
		}
		catch(Exception ex) {
			
			System.out.println("hpt.has.shinhan.filenet.CreateFolder.createFolder.Error: createFolder: " + ex);			
			
			return false;
		}
		return true;
    }

	static String uri = "http://10.4.18.167:9080/wsi/FNCEWS40MTOM/";
	static String username = "p8admin";
	static String password = "Hpt123456";
	static String optionalJAASStanzaName = "FileNetP8WSI";
	static String objectName = "OBJ01";
	
//	static String uri = "http://10.148.50.62:9080/wsi/FNCEWS40MTOM/";
//	static String username = "srvsvfcibmcm";
//	static String password = "Shinhan01";
//	static String optionalJAASStanzaName = "FileNetP8WSI";
//	static String objectName = "Object01";
	
	public static void main(String[] args)
	{
	    
		ObjectStore object_store = ConnectFilenet.OpenConnection(uri, username, password, optionalJAASStanzaName, objectName);
	    
	    boolean result = createFolder(object_store, "/GeneraliPOC", "test01", "POCFolder");

	    System.out.println(result);
	    
	    //CloseConnection();
		ConnectFilenet.CloseConnection();
	}
}

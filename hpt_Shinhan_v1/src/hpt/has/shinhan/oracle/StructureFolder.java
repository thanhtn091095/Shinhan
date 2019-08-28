package hpt.has.shinhan.oracle;

import java.util.ArrayList;
import java.util.List;

public class StructureFolder {
	
	private Integer ID;
	private Integer DOCID;
	private String DOC_TYPE;
	private String FILENET_FOLDER;
	private String FILENET_FOLDER_ID;
	private String STAGE;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		List<StructureFolder> lst = new ArrayList<StructureFolder>();
		
		for(int i = 0; i < 100000; i ++) {
			
			StructureFolder f = new StructureFolder(i, i, i + "", i + "", i + "", i + "");
			
			lst.add(f);
		}
		
		String value = SearchFolderFromDocID(lst, 0, lst.size(), 933);
		
		String doc = "Thanh";
		
		System.out.println("hpt.has.shinhan.oracle.StructureFolder.main.Info: " + value);
		System.out.println("hpt.has.shinhan.oracle.StructureFolder.main.Info: " + doc.toUpperCase());
	}
	
	public StructureFolder() {
		
	}
	
	public StructureFolder(Integer _id, Integer _docid, String _doc_type, String _filenet_folder, String _filenet_folder_id, String _stage) {
		
		this.ID = _id;
		this.DOCID = _docid;
		this.DOC_TYPE = _doc_type;
		this.FILENET_FOLDER = _filenet_folder;
		this.FILENET_FOLDER_ID = _filenet_folder_id;
		this.STAGE = _stage;
	}

	public <M> void setProperty(M value, String key) 
	{
		 try {
			 
			 switch (key) {
	            case "ID":
	            	this.ID = (Integer) value;
	            	break;
	            case "DOCID":
	            	this.DOCID = (Integer) value;
	                break;
	            case "DOC_TYPE":
	            	this.DOC_TYPE = (String) value;
	                break;
	            case "FILENET_FOLDER":
	            	this.FILENET_FOLDER = (String) value;
	                break;
	            case "FILENET_FOLDER_ID":
	            	this.FILENET_FOLDER_ID = (String) value;
	                break;
	            case "STAGE":
	            	this.STAGE = (String) value;
	                break;
	        }
		 }
		 catch(Exception ex) {
			 
			 System.out.println("hpt.has.shinhan.oracle.StructureFolder.setProperty.Error: " + ex);
		 }
	}
	
	@SuppressWarnings("unchecked")
	public <M> M getProperty(String key) 
	{
		 try {
			 
			 switch (key) {
	            case "ID":
	            	return (M) this.ID;
	            case "DOCID":
	            	return (M) this.DOCID;
	            case "DOC_TYPE":
	            	return (M) this.DOC_TYPE;
	            case "FILENET_FOLDER":
	            	return (M) this.FILENET_FOLDER;
	            case "FILENET_FOLDER_ID":
	            	return (M) this.FILENET_FOLDER_ID;
	            case "STAGE":
	            	return (M) this.STAGE;	          
	            
	        }
		 }
		 catch(Exception ex) {
			 
			 System.out.println("hpt.has.shinhan.oracle.StructureFolder.getProperty.Error: " + ex);
		 }
		return null;
	}
	
	public static String SearchFolderFromDocID(List<StructureFolder> lstStructureFolder, int firstElement, int lastElement, Integer elementToSearch) {

		try {			
			if (lastElement >= firstElement) {
		    	
		        int mid = firstElement + (lastElement - firstElement) / 2;
		        
		        StructureFolder f = lstStructureFolder.get(mid);
		        
		        System.out.println("hpt.has.shinhan.oracle.StructureFolder.SearchFolderFromDocID.Info: DOCID: " + (Integer) f.getProperty("DOCID"));
		        System.out.println("hpt.has.shinhan.oracle.StructureFolder.SearchFolderFromDocID.Info: FILENET_FOLDER: " + (String) f.getProperty("FILENET_FOLDER"));

		        // if the middle element is our goal element, return its index
		        if ((boolean) f.getProperty("DOCID").equals(elementToSearch))
		            return (String) f.getProperty("FILENET_FOLDER");

		        // if the middle element is bigger than the goal element
		        // recursively call the method with narrowed data
		        if ((Integer) f.getProperty("DOCID") > elementToSearch) {
		        	return SearchFolderFromDocID(lstStructureFolder, firstElement, mid - 1, elementToSearch);
		        }
		        // else, recursively call the method with narrowed data
		        return SearchFolderFromDocID(lstStructureFolder, mid + 1, lastElement, elementToSearch);
		    }
		}
		catch(Exception e) {
			
			System.out.println("hpt.has.shinhan.oracle.StructureFolder.SearchFolderFromDocID.Error: " + e);
		}
	    // termination condition
	    return null;
	}
}

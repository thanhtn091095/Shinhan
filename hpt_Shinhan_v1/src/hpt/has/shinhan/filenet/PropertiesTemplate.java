package hpt.has.shinhan.filenet;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PropertiesTemplate {

	public List<String> list;
	
	public List<String> listPro;
	
	public String AppFrmNo;
	public String LoanAgrNo;
	public String Item;
	public String CIF;
	public String CustName;
	public String CustID;
	public String DocID;
	public String DocDesc;
	public Date ScannedDate;
	public String UploadChannel;
	public String BoxID;
	public String MimeType;
	public String FileName;
	public String Doc_Ref_ID;
	public String AppRefNo;
	public String FileContent;
	public String Attr1;
	public String FilePath;
	public Date CreateDT;
	
	public PropertiesTemplate() {
		
		
	}
	
	public PropertiesTemplate(String _AppFrmNo, String _LoanAgrNo, String _CIF, String _CustName, String _CustID, String _DocID, String _DocDesc, String _FileName, String _Doc_Ref_ID, String _AppRefNo, String _FilePath, String mineType, String _UploadChannel, String _Item, Date _ScannedDate, Date _CreateDT) {
		
		AppFrmNo = _AppFrmNo;
		AppRefNo = _AppRefNo;
		CIF = _CIF;
		CustID = _CustID;
		CustName = _CustName;
		Doc_Ref_ID = _Doc_Ref_ID;
		DocDesc = _DocDesc;		
		DocID = _DocID;
		FileName = _FileName;
		FilePath = _FilePath;
		LoanAgrNo = _LoanAgrNo;
		UploadChannel = _UploadChannel;
		Item = _Item;
		
		ScannedDate = _ScannedDate;
		CreateDT = _CreateDT;	
		
		MimeType = mineType;
		
	}
	
	public static void setList(PropertiesTemplate p) {
		
		p.list = new ArrayList<>();
		p.list.add("AppFrmNo");
		p.list.add("LoanAgrNo");
		p.list.add("Item");
		p.list.add("CIF");
		p.list.add("CustName");
		p.list.add("CustID");
		p.list.add("DocID");
		p.list.add("DocDesc");
		p.list.add("ScannedDate");
		p.list.add("UploadChannel");
		p.list.add("BoxID");
		p.list.add("MimeType");
		p.list.add("FileName");
		p.list.add("Doc_Ref_ID");
		p.list.add("AppRefNo");
		p.list.add("FileContent");
		p.list.add("Attr1");
	}
	
	public static void setListPro(PropertiesTemplate p) {
		
		p.listPro = new ArrayList<>();
		p.listPro.add("APPFRMNO");
		p.listPro.add("LOANAGRNO");
		p.listPro.add("DOCDESC");
		p.listPro.add("CUSTID");
		p.listPro.add("CUSTNAME");
		p.listPro.add("SCANNEDDATE");
		p.listPro.add("MIMETYPE");
		p.listPro.add("FILEPATH");
		p.listPro.add("CIF");
		p.listPro.add("DOCID");
		p.listPro.add("UPLOADCHANNEL");
		p.listPro.add("APPREFNO");
		p.listPro.add("FILENAME");
		p.listPro.add("DOCREFID");
		p.listPro.add("CREATE_DT");
	}
	
	public static void setProperty(PropertiesTemplate p, String key, String value) 
	{
		 try {
			 
			 switch (key) {
	            case "AppFrmNo":
	            	p.AppFrmNo = value;
	                break;
	            case "LoanAgrNo":
	            	p.LoanAgrNo = value;
	                break;
	            case "Item":
	            	p.Item = value;
	                break;
	            case "CIF":
	            	p.CIF = value;
	                break;
	            case "CustName":
	            	p.CustName = value;
	                break;
	            case "CustID":
	            	p.CustID = value;
	                break;
	            case "DocID":
	            	p.DocID = value;
	                break;
	            case "DocDesc":
	            	p.DocDesc = value;
	                break;
	            case "ScannedDate":
	            	p.ScannedDate = new Date();
	                break;
	            case "UploadChannel":
	            	p.UploadChannel = value;
	                break;
	            case "BoxID":
	            	p.BoxID = value;
	                break;
	            case "Doc_Ref_ID":
	            	p.Doc_Ref_ID = value;
	                break;
	            case "AppRefNo":
	            	p.AppRefNo = value;
	                break;
	            case "MimeType":
	            	p.MimeType = value;
	                break;
	            case "FileContent":
	            	p.FileContent = value;
	                break;
	            case "FileName":
	            	p.FileName = value;
	                break;
	            case "FilePath":
	            	p.FilePath = value;
	                break;
	            default:
	            	p.Attr1 = value;
	        }
		 }
		 catch(Exception ex) {
			 
			 System.out.println("hpt.has.shinhan.filenet.PropertiesTemplate.setProperty.Error: " + ex);
		 }
	}
	
	public static String getProperty(PropertiesTemplate p, String key) 
	{
		 try {
			 
			 switch (key) {
	            case "AppFrmNo":
	            	return p.AppFrmNo;
	            case "LoanAgrNo":
	            	return p.LoanAgrNo;
	            case "Item":
	            	return p.Item;
	            case "CIF":
	            	return p.CIF;
	            case "CustName":
	            	return p.CustName;
	            case "CustID":
	            	return p.CustID;
	            case "DocID":
	            	return p.DocID;
	            case "DocDesc":
	            	return p.DocDesc;
	            case "ScannedDate":
	            	return p.ScannedDate.toString();
	            case "UploadChannel":
	            	return p.UploadChannel;
	            case "BoxID":
	            	return p.BoxID;
	            case "Doc_Ref_ID":
	            	return p.Doc_Ref_ID;
	            case "AppRefNo":
	            	return p.AppRefNo;
	            case "MimeType":
	            	return p.MimeType;
	            case "FileContent":
	            	return p.FileContent;
	                
	            default:
	            	return p.Attr1;
	        }
		 }
		 catch(Exception ex) {
			 
			 System.out.println("hpt.has.shinhan.filenet.PropertiesTemplate.setProperty.Error: " + ex);
		 }
		 return "?";
	}
	
	public static String getPropertyPro(PropertiesTemplate p, String key) 
	{
		 try {
			 
			 switch (key) {
	            case "APPFRMNO":
	            	return p.AppFrmNo;
	            case "LOANAGRNO":
	            	return p.LoanAgrNo;
	            case "CIF":
	            	return p.CIF;
	            case "CUSTNAME":
	            	return p.CustName;
	            case "CUSTID":
	            	return p.CustID;
	            case "DOCID":
	            	return p.DocID;
	            case "DOCDESC":
	            	return p.DocDesc;
	            case "SCANNEDDATE":
	            	return p.ScannedDate.toString();
	            case "UPLOADCHANNEL":
	            	return p.UploadChannel;
	            case "DOCREFID":
	            	return p.Doc_Ref_ID;
	            case "APPREFNO":
	            	return p.AppRefNo;
	            case "FILENAME":
	            	return p.FileName;
	            case "FILEPATH":
	            	return p.FilePath;
	            case "CREATE_DT":
	            	return p.CreateDT.toString();    
	            default:
	            	return "?";
	        }
		 }
		 catch(Exception ex) {
			 
			 System.out.println("hpt.has.shinhan.filenet.PropertiesTemplate.getPropertyPro.Error: " + ex);
		 }
		 return "?";
	}
	
	public static Date getPropertyProDate(PropertiesTemplate p, String key) 
	{
		 try {
			 
			 switch (key) {
	            
	            case "SCANNEDDATE":
	            	return p.ScannedDate;	           
	            case "CREATE_DT":
	            	return p.CreateDT;    
	            default:
	            	return new Date();
	        }
		 }
		 catch(Exception ex) {
			 
			 System.out.println("hpt.has.shinhan.filenet.PropertiesTemplate.getPropertyPro.Error: " + ex);
		 }
		 return new Date();
	}

}

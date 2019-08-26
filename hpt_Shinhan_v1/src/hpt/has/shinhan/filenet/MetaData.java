package hpt.has.shinhan.filenet;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MetaData{
	
	String DOCREFID;
	String APPFRMNO;
	String LOANAGRNO;
	String DOCDESC;
	String CUSTID;
	String CUSTNAME;
	Date SCANNEDDATE;
	String BOXID;
	String MIMETYPE;
	String FILEPATH;
	String CIF;
	String DOCID;
	String ITEM;
	String UPLOADCHANNEL;
	String APPREFNO;
	String FILENAME;
	Date CREATE_DT;
	Date UPDATE_DT;
	
	List<String> LIST_META_DATA;
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		MetaData m  = new MetaData("123", "123", "123", "123", "123", "123", new Date(), "123", "123", "123", "123", "123", "123", "123", "123", "123", new Date(), new Date());
		
		System.out.println("hpt.has.shinhan.filenet.MetaData.main.Info DOCREFID: " + m.getProperty("DOCREFID"));
		System.out.println("hpt.has.shinhan.filenet.MetaData.main.Info SCANNEDDATE: " + m.getProperty("SCANNEDDATE"));
		
		m.setList();
		
		m.setProperty("456", "DOCREFID");
		m.setProperty(new Date(2019, 01, 01), "SCANNEDDATE");
		
		System.out.println("hpt.has.shinhan.filenet.MetaData.main.Info DOCREFID: " + m.getProperty("DOCREFID"));
		System.out.println("hpt.has.shinhan.filenet.MetaData.main.Info SCANNEDDATE: " + m.getProperty("SCANNEDDATE"));
	}
	public MetaData(){
		
	}
	
	public MetaData(String _DOCREFID, String _APPFRMNO, String _LOANAGRNO, String _DOCDESC, String _CUSTID, String _CUSTNAME, Date _SCANNEDDATE, String _BOXID, String _MIMETYPE, String _FILEPATH, String _CIF, String _DOCID, String _ITEM, String _UPLOADCHANNEL, String _APPREFNO, String _FILENAME, Date _CREATE_DT, Date _UPDATE_DT) {
		
		this.DOCREFID = _DOCREFID;
		this.APPFRMNO = _APPFRMNO;
		this.LOANAGRNO = _LOANAGRNO;
		this.DOCDESC = _DOCDESC;
		this.CUSTID = _CUSTID;
		this.CUSTNAME = _CUSTNAME;
		this.SCANNEDDATE = _SCANNEDDATE;
		this.BOXID = _BOXID;
		this.MIMETYPE = _MIMETYPE;
		this.FILEPATH = _FILEPATH;
		this.CIF = _CIF;
		this.DOCID = _DOCID;
		this.ITEM = _ITEM;
		this.UPLOADCHANNEL = _UPLOADCHANNEL;
		this.APPREFNO = _APPREFNO;
		this.FILENAME = _FILENAME;
		this.CREATE_DT = _CREATE_DT;
		this.UPDATE_DT = _UPDATE_DT;
	}

	public void setList() {
		
		this.LIST_META_DATA = new ArrayList<>();
		this.LIST_META_DATA.add("DOCREFID");
		this.LIST_META_DATA.add("APPFRMNO");
		this.LIST_META_DATA.add("LOANAGRNO");
		this.LIST_META_DATA.add("DOCDESC");
		this.LIST_META_DATA.add("CUSTID");
		this.LIST_META_DATA.add("CUSTNAME");
		this.LIST_META_DATA.add("SCANNEDDATE");
		this.LIST_META_DATA.add("BOXID");
		this.LIST_META_DATA.add("MIMETYPE");
		this.LIST_META_DATA.add("FILEPATH");
		this.LIST_META_DATA.add("CIF");
		this.LIST_META_DATA.add("DOCID");
		this.LIST_META_DATA.add("ITEM");
		this.LIST_META_DATA.add("UPLOADCHANNEL");
		this.LIST_META_DATA.add("APPREFNO");
		this.LIST_META_DATA.add("FILENAME");
		this.LIST_META_DATA.add("CREATE_DT");
		this.LIST_META_DATA.add("UPDATE_DT");
	}
	
	public List<String> getList(){
		
		return this.LIST_META_DATA;
	}
	
	public <M> void setProperty(M value, String key) 
	{
		 try {
			 
			 switch (key) {
	            case "DOCREFID":
	            	this.DOCREFID = (String) value;
	            	break;
	            case "APPFRMNO":
	            	this.APPFRMNO = (String) value;
	                break;
	            case "LOANAGRNO":
	            	this.LOANAGRNO = (String) value;
	                break;
	            case "DOCDESC":
	            	this.DOCDESC = (String) value;
	                break;
	            case "CUSTID":
	            	this.CUSTID = (String) value;
	                break;
	            case "CUSTNAME":
	            	this.CUSTNAME = (String) value;
	                break;
	            case "SCANNEDDATE":
	            	this.SCANNEDDATE = (Date) value;
	                break;
	            case "BOXID":
	            	this.BOXID = (String) value;
	                break;
	            case "MIMETYPE":
	            	this.MIMETYPE = (String) value;
	                break;
	            case "FILEPATH":
	            	this.FILEPATH = (String) value;
	                break;
	            case "CIF":
	            	this.CIF = (String) value;
	                break;
	            case "DOCID":
	            	this.DOCID = (String) value;
	                break;
	            case "ITEM":
	            	this.ITEM = (String) value;
	                break;
	            case "UPLOADCHANNEL":
	            	this.UPLOADCHANNEL = (String) value;
	                break;
	            case "APPREFNO":
	            	this.APPREFNO = (String) value;
	                break;
	            case "FILENAME":
	            	this.FILENAME = (String) value;
	                break;
	            case "CREATE_DT":
	            	this.CREATE_DT = (Date) value;
	                break;
	            default:
	            	this.UPDATE_DT = (Date) value;
	        }
		 }
		 catch(Exception ex) {
			 
			 System.out.println("hpt.has.shinhan.filenet.MetaData.setProperty.Error: " + ex);
		 }
	}
	
	public <M> M getProperty(String key) 
	{
		 try {
			 
			 switch (key) {
	            case "DOCREFID":
	            	return (M) this.DOCREFID;
	            case "APPFRMNO":
	            	return (M) this.APPFRMNO;
	            case "LOANAGRNO":
	            	return (M) this.LOANAGRNO;
	            case "DOCDESC":
	            	return (M) this.DOCDESC;
	            case "CUSTID":
	            	return (M) this.CUSTID;
	            case "CUSTNAME":
	            	return (M) this.CUSTNAME;	                
	            case "SCANNEDDATE":
	            	return (M) this.SCANNEDDATE;	                
	            case "BOXID":
	            	return (M) this.BOXID;	                
	            case "MIMETYPE":
	            	return (M) this.MIMETYPE;	                
	            case "FILEPATH":
	            	return (M) this.FILEPATH;	                
	            case "CIF":
	            	return (M) this.CIF;	                
	            case "DOCID":
	            	return (M) this.DOCID;	                
	            case "ITEM":
	            	return (M) this.ITEM;	                
	            case "UPLOADCHANNEL":
	            	return (M) this.UPLOADCHANNEL;	                
	            case "APPREFNO":
	            	return (M) this.APPREFNO;	                
	            case "FILENAME":
	            	return (M) this.FILENAME;	                
	            case "CREATE_DT":
	            	return (M) this.CREATE_DT;	                
	            default:
	            	return (M) this.UPDATE_DT;
	        }
		 }
		 catch(Exception ex) {
			 
			 System.out.println("hpt.has.shinhan.filenet.MetaData.setProperty.Error: " + ex);
		 }
		return null;
	}
}

package hpt.has.shinhan.thread;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import hpt.has.shinhan.filenet.PropertiesTemplate;

public class ThreadTest {
	
	static String uri = "http://10.4.18.167:9080/wsi/FNCEWS40MTOM/";
	static String username = "p8admin";
	static String password = "Hpt123456";
	static String optionalJAASStanzaName = "FileNetP8WSI";
	static String objectName = "OBJ01";
	static String root = "/GeneraliPOC";	
	static String doc_class = "SHFDoc";
	static String fol_class = "SHFFolder";
	
	static String doc_name = "thanhtn";

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		List<PropertiesTemplate> lst = new ArrayList<>();
		
		PropertiesTemplate p = new PropertiesTemplate("123456", "", "", "ThanhTN", "3122990821", "71", "MY MAP", "", "1234567890", "", "", "", "", "123", new Date(), new Date());
	
		PropertiesTemplate.setListPro(p);
		
		for(int i = 0; i < 1000; i++) {
			
			lst.add(p);
		}
		
		//int size = lst.size();
		
		List<PropertiesTemplate> first = new ArrayList<>(lst.subList(0, 250));
		List<PropertiesTemplate> second = new ArrayList<>(lst.subList(250, 500));
		List<PropertiesTemplate> third = new ArrayList<>(lst.subList(500, 750));
		List<PropertiesTemplate> four = new ArrayList<>(lst.subList(750, 1000));
		
		ThreadDemo t1 = new ThreadDemo(uri, username, password, optionalJAASStanzaName, objectName, root, fol_class, doc_class, doc_name, first);
		
		t1.start();
		
		ThreadDemo t2 = new ThreadDemo(uri, username, password, optionalJAASStanzaName, objectName, root, fol_class, doc_class, doc_name, second);
		
		t2.start();
		
		ThreadDemo t3 = new ThreadDemo(uri, username, password, optionalJAASStanzaName, objectName, root, fol_class, doc_class, doc_name, third);
		
		t3.start();
		
		ThreadDemo t4 = new ThreadDemo(uri, username, password, optionalJAASStanzaName, objectName, root, fol_class, doc_class, doc_name, four);
		
		t4.start();
		
	}

}

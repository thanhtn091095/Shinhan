package hpt.has.shinhan.api.soap;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Iterator;
import java.util.List;

import javax.xml.soap.AttachmentPart;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.Node;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;

import org.w3c.dom.Attr;

public class ServiceRequest {
	static String NAMESPACE = "http://docs.oasis-open.org/ns/cmis/messaging/200908/";
	static String NAMESPACEEV = "http://schemas.xmlsoap.org/soap/envelope/";
	static String NAMESPACE1 = "http://docs.oasis-open.org/ns/cmis/core/200908/";
	
	static String username_shinhan = "srvsvfcibmcm";
	static String password_shinhan = "Shinhan01";
	static String action_version_shinhan = "http://10.148.50.62:9080/fncmis/VersioningService";
	static String action_query_shinhan = "http://10.148.50.62:9080/fncmis/DiscoveryService";
	static String action_shinhan = "http://10.148.50.62:9080/fncmis/ObjectService";
	static String urlWS_shinhan = "http://10.148.50.62:9080/fncmis/wsdl";
	static String objectId_shinhan = "{406C8E6C-0100-C2E5-8D37-553FD0584448}";
	static String objectId_query_shinhan = "{00FA606C-0000-C31E-8AB3-973F2E782436}";
	static String classId_shinhan = "{80FC4A6C-0000-C61C-BEDB-E66E9CC00CEF}";
	static String repositoryId_shinhan = "Object01";
	static String classFolder_shinhan = "SHFFolder";
	static String folderId_shinhan = "{407B606C-0000-C51D-AD42-281414A483CA}";
	
	static String uri = "http://10.4.18.167:9080/wsi/FNCEWS40MTOM/";
	static String username = "p8admin";
	static String password = "Hpt123456";
	static String classId = "{304B4B6C-0000-C016-A6EB-46235AFBAB79}";
	
	public static SOAPMessage createQuerySoapMessage(String repositoryID, String query)
	{
		try {
			MessageFactory messageFactory = MessageFactory.newInstance();
	        SOAPMessage soapMessage = messageFactory.createMessage();
	        SOAPPart soapPart = soapMessage.getSOAPPart();
	        
	        SOAPEnvelope envelope = soapPart.getEnvelope();
	        envelope.addNamespaceDeclaration("ns", NAMESPACE);
	        //envelope.addNamespaceDeclaration("soapenv", NAMESPACEEV);
	        
	        String authorization = Base64.getEncoder().encodeToString((username_shinhan+":"+password_shinhan).getBytes());
	        MimeHeaders hd = soapMessage.getMimeHeaders();
	        hd.addHeader("Authorization", "Basic " + authorization);
	        //hd.addHeader("SOAPAction", action);
	        
	        // SOAP Body
	        SOAPBody soapBody = envelope.getBody();
	        
	        //SOAPBodyElement getContentStream = soapBody.addBodyElement(envelope.createQName("getContentStream", "ns"));
	        SOAPElement queryOperation = soapBody.addChildElement("query", "ns");
	            SOAPElement repositoryId = queryOperation.addChildElement("repositoryId", "ns");
	            	repositoryId.addTextNode(repositoryID);
	            SOAPElement statement = queryOperation.addChildElement("statement", "ns");
	            	statement.addTextNode(query);
			soapMessage.saveChanges();
			
			return soapMessage;
		} catch (SOAPException e) {
			System.out.println("error " + e);
		}
		return null;
	}
	
	public static SOAPMessage createGetContentStreamSoapMessage(String repositoryID, String objectID)
	{
		try {
			MessageFactory messageFactory = MessageFactory.newInstance();
	        SOAPMessage soapMessage = messageFactory.createMessage();
	        SOAPPart soapPart = soapMessage.getSOAPPart();
	        
	        SOAPEnvelope envelope = soapPart.getEnvelope();
	        envelope.addNamespaceDeclaration("ns", NAMESPACE);
	        //envelope.addNamespaceDeclaration("soapenv", NAMESPACEEV);
	        
	        String authorization = Base64.getEncoder().encodeToString((username_shinhan+":"+password_shinhan).getBytes());
	        MimeHeaders hd = soapMessage.getMimeHeaders();
	        hd.addHeader("Authorization", "Basic " + authorization);
	        //hd.addHeader("SOAPAction", action);
	        
	        // SOAP Body
	        SOAPBody soapBody = envelope.getBody();
	        
	        //SOAPBodyElement getContentStream = soapBody.addBodyElement(envelope.createQName("getContentStream", "ns"));
	        SOAPElement getContentStream = soapBody.addChildElement("getContentStream", "ns");
            SOAPElement repositoryId = getContentStream.addChildElement("repositoryId", "ns");
            	repositoryId.addTextNode(repositoryID);
            SOAPElement objectId = getContentStream.addChildElement("objectId", "ns");
            	objectId.addTextNode(objectID);
		    soapMessage.saveChanges();
			
			return soapMessage;
		} catch (SOAPException e) {
			System.out.println("error " + e);
		}
		return null;
	}
	
	public static SOAPMessage createUploadDocumentSoapMessage(String repositoryID, String objectID, String folderID, String name, File uploadFile)
	{
		try {
			MessageFactory messageFactory = MessageFactory.newInstance();
	        SOAPMessage soapMessage = messageFactory.createMessage();
	        SOAPPart soapPart = soapMessage.getSOAPPart();
	        
	        SOAPEnvelope envelope = soapPart.getEnvelope();
	        envelope.addNamespaceDeclaration("ns", NAMESPACE);
	        envelope.addNamespaceDeclaration("ns1", NAMESPACE1);
	        //envelope.addNamespaceDeclaration("soapenv", NAMESPACEEV);
	        
	        String authorization = Base64.getEncoder().encodeToString((username_shinhan+":"+password_shinhan).getBytes());
	        MimeHeaders hd = soapMessage.getMimeHeaders();
	        hd.addHeader("Authorization", "Basic " + authorization);
	        //hd.addHeader("Enable MTOM", "true");
	        
	        // SOAP Body
	        SOAPBody soapBody = envelope.getBody();

	        SOAPElement createDocument = soapBody.addChildElement("createDocument", "ns");
	            SOAPElement repositoryId = createDocument.addChildElement("repositoryId", "ns");
	            	repositoryId.addTextNode(repositoryID);
	            SOAPElement properties = createDocument.addChildElement("properties", "ns");
	            	SOAPElement propertyId = properties.addChildElement("propertyId", "ns1");
	            		propertyId.setAttribute("propertyDefinitionId", "cmis:objectTypeId");
	            			SOAPElement value = propertyId.addChildElement("value", "ns1");
	            				value.addTextNode(objectID);
	            	SOAPElement propertyString = properties.addChildElement("propertyString", "ns1");
	            		propertyString.setAttribute("propertyDefinitionId", "cmis:name");
	    	            	SOAPElement value1 = propertyString.addChildElement("value", "ns1");
	    	            		value1.addTextNode(name);
	    	    if(folderID != null)
	    	    {
	    	    	SOAPElement folderId = createDocument.addChildElement("folderId", "ns");
	            	folderId.addTextNode(folderID);
	    	    }
	            if(uploadFile != null)
	            {
	            	//String extension = Files.probeContentType(uploadFile.toPath());
	            	
	            	String extension = "application/pdf";
	            	long size = Files.size(uploadFile.toPath());
	            	System.out.println("extension " + extension);
	            	System.out.println("size " + size);
	            	SOAPElement contentStream = createDocument.addChildElement("contentStream", "ns");
	            	SOAPElement length = contentStream.addChildElement("length", "ns");
	            		length.addTextNode(size + "");
	            	SOAPElement mimeType = contentStream.addChildElement("mimeType", "ns");
	            		mimeType.addTextNode(extension);
	            	SOAPElement filename = contentStream.addChildElement("filename", "ns");
	            		filename.addTextNode(uploadFile.getName());
	            	SOAPElement stream = contentStream.addChildElement("stream", "ns");
	            		//stream.addTextNode("cid:439587033251");
	            		
	            		
            		//uploadFile = new File("C:\\Users\\duyl\\Documents\\upload.pdf");
            		
            		String testBase64 = ConvertBase64.encodeFileToBase64Binary(uploadFile);
            		stream.addTextNode(testBase64);
	            }
            soapMessage.saveChanges();
            return soapMessage;
		}
		catch(Exception ex) {
			System.out.println("error " + ex);
		}
		return null;
	}
	
	public static SOAPMessage createUpdatePropertiesSoapMessage(String repositoryID, String objectID, List<FilenetProperty> propertyList)
	{
		//Binary, Boolean, Date Time, Float, ID, Integer, Object, String
		try {
			MessageFactory messageFactory = MessageFactory.newInstance();
	        SOAPMessage soapMessage = messageFactory.createMessage();
	        SOAPPart soapPart = soapMessage.getSOAPPart();
	        
	        SOAPEnvelope envelope = soapPart.getEnvelope();
	        envelope.addNamespaceDeclaration("ns", NAMESPACE);
	        envelope.addNamespaceDeclaration("ns1", NAMESPACE1);
	        //envelope.addNamespaceDeclaration("soapenv", NAMESPACEEV);
	        
	        String authorization = Base64.getEncoder().encodeToString((username_shinhan+":"+password_shinhan).getBytes());
	        MimeHeaders hd = soapMessage.getMimeHeaders();
	        hd.addHeader("Authorization", "Basic " + authorization);
	        //hd.addHeader("Enable MTOM", "true");
	        
	        // SOAP Body
	        SOAPBody soapBody = envelope.getBody();

	        SOAPElement updateProperties = soapBody.addChildElement("updateProperties", "ns");
	            SOAPElement repositoryIdElement = updateProperties.addChildElement("repositoryId", "ns");
	            	repositoryIdElement.addTextNode(repositoryID);
	            SOAPElement objectIdElement = updateProperties.addChildElement("objectId", "ns");
	            	objectIdElement.addTextNode(objectID);
	            SOAPElement properties = updateProperties.addChildElement("properties", "ns");
	            	for (FilenetProperty filenetProperty : propertyList) {
	            		SOAPElement propertyId = properties.addChildElement(filenetProperty.getDataType(), "ns1");
	            		propertyId.setAttribute("propertyDefinitionId", filenetProperty.getDefinitionID());
	            			SOAPElement value = propertyId.addChildElement("value", "ns1");
	            				value.addTextNode(filenetProperty.getValue());
					}
	            	
	    	    
            soapMessage.saveChanges();
            return soapMessage;
		}
		catch(Exception ex) {
			System.out.println("error " + ex);
		}
		return null;
	}
	
	public static SOAPMessage createCheckinSoapMessage(String repositoryID, String objectID, Boolean isMajor, List<FilenetProperty> propertyList, File uploadFile)
	{
		try {
			MessageFactory messageFactory = MessageFactory.newInstance();
	        SOAPMessage soapMessage = messageFactory.createMessage();
	        SOAPPart soapPart = soapMessage.getSOAPPart();
	        
	        SOAPEnvelope envelope = soapPart.getEnvelope();
	        envelope.addNamespaceDeclaration("ns", NAMESPACE);
	        envelope.addNamespaceDeclaration("ns1", NAMESPACE1);
	        //envelope.addNamespaceDeclaration("soapenv", NAMESPACEEV);
	        
	        String authorization = Base64.getEncoder().encodeToString((username_shinhan +":"+password_shinhan).getBytes());
	        MimeHeaders hd = soapMessage.getMimeHeaders();
	        hd.addHeader("Authorization", "Basic " + authorization);
	        //hd.addHeader("Enable MTOM", "true");
	        
	        // SOAP Body
	        SOAPBody soapBody = envelope.getBody();

	        SOAPElement checkInDocument = soapBody.addChildElement("checkIn", "ns");
	            SOAPElement repositoryIdElement = checkInDocument.addChildElement("repositoryId", "ns");
	            repositoryIdElement.addTextNode(repositoryID);
	            SOAPElement objectIdElement = checkInDocument.addChildElement("objectId", "ns");
	            objectIdElement.addTextNode(objectID);
	            
	            String majorValue = isMajor ? "true" : "false";
	            SOAPElement major = checkInDocument.addChildElement("major", "ns");
	            major.addTextNode(majorValue);
	            
	            if(propertyList.size() > 0)
	            {
		            SOAPElement properties = checkInDocument.addChildElement("properties", "ns");
		            for (FilenetProperty filenetProperty : propertyList) {
		            	SOAPElement propertyId = properties.addChildElement(filenetProperty.getDataType(), "ns1");
	            		propertyId.setAttribute("propertyDefinitionId", filenetProperty.getDefinitionID());
	            			SOAPElement value = propertyId.addChildElement("value", "ns1");
	            				value.addTextNode(filenetProperty.getValue());
					}
	            }
	    	    
	            if(uploadFile != null)
	            {
	            	String extension = Files.probeContentType(uploadFile.toPath());
	            	
	            	long size = Files.size(uploadFile.toPath());
	            	
	            	SOAPElement contentStream = checkInDocument.addChildElement("contentStream", "ns");
	            	SOAPElement length = contentStream.addChildElement("length", "ns");
	            		length.addTextNode(size + "");
	            	SOAPElement mimeType = contentStream.addChildElement("mimeType", "ns");
	            		mimeType.addTextNode(extension);
	            	SOAPElement filename = contentStream.addChildElement("filename", "ns");
	            		filename.addTextNode(uploadFile.getName());
	            	SOAPElement stream = contentStream.addChildElement("stream", "ns");
	            		//stream.addTextNode("cid:439587033251");
	            		
	            		
            		//uploadFile = new File("C:\\Users\\duyl\\Documents\\upload.pdf");
            		
            		String testBase64 = ConvertBase64.encodeFileToBase64Binary(uploadFile);
            		stream.addTextNode(testBase64);
	            }
            soapMessage.saveChanges();
            return soapMessage;
		}
		catch(Exception ex) {
			System.out.println("error " + ex);
		}
		return null;
	}
	
	public static SOAPMessage createCheckOutSoapMessage(String repositoryID, String objectID)
	{
		try {
			MessageFactory messageFactory = MessageFactory.newInstance();
	        SOAPMessage soapMessage = messageFactory.createMessage();
	        SOAPPart soapPart = soapMessage.getSOAPPart();
	        
	        SOAPEnvelope envelope = soapPart.getEnvelope();
	        envelope.addNamespaceDeclaration("ns", NAMESPACE);
	        //envelope.addNamespaceDeclaration("soapenv", NAMESPACEEV);
	        
	        String authorization = Base64.getEncoder().encodeToString((username_shinhan+":"+password_shinhan).getBytes());
	        MimeHeaders hd = soapMessage.getMimeHeaders();
	        hd.addHeader("Authorization", "Basic " + authorization);
	        //hd.addHeader("SOAPAction", action);
	        
	        // SOAP Body
	        SOAPBody soapBody = envelope.getBody();
	        
	        //SOAPBodyElement getContentStream = soapBody.addBodyElement(envelope.createQName("getContentStream", "ns"));
	        SOAPElement checkOut = soapBody.addChildElement("checkOut", "ns");
            SOAPElement repositoryIdElement = checkOut.addChildElement("repositoryId", "ns");
            	repositoryIdElement.addTextNode(repositoryID);
            SOAPElement objectId = checkOut.addChildElement("objectId", "ns");
            	objectId.addTextNode(objectID);
            soapMessage.saveChanges();
			
			return soapMessage;
		} catch (SOAPException e) {
			System.out.println("error " + e);
		}
		return null;
	}
	
	public static SOAPMessage createCancelCheckOutSoapMessage(String repositoryID, String objectID)
	{
		try {
			MessageFactory messageFactory = MessageFactory.newInstance();
	        SOAPMessage soapMessage = messageFactory.createMessage();
	        SOAPPart soapPart = soapMessage.getSOAPPart();
	        
	        SOAPEnvelope envelope = soapPart.getEnvelope();
	        envelope.addNamespaceDeclaration("ns", NAMESPACE);
	        //envelope.addNamespaceDeclaration("soapenv", NAMESPACEEV);
	        
	        String authorization = Base64.getEncoder().encodeToString((username_shinhan+":"+password_shinhan).getBytes());
	        MimeHeaders hd = soapMessage.getMimeHeaders();
	        hd.addHeader("Authorization", "Basic " + authorization);
	        //hd.addHeader("SOAPAction", action);
	        
	        // SOAP Body
	        SOAPBody soapBody = envelope.getBody();
	        
	        //SOAPBodyElement getContentStream = soapBody.addBodyElement(envelope.createQName("getContentStream", "ns"));
	        SOAPElement cancelCheckOut = soapBody.addChildElement("cancelCheckOut", "ns");
            SOAPElement repositoryIdElement = cancelCheckOut.addChildElement("repositoryId", "ns");
            	repositoryIdElement.addTextNode(repositoryID);
            SOAPElement objectId = cancelCheckOut.addChildElement("objectId", "ns");
            	objectId.addTextNode(objectID);
            soapMessage.saveChanges();
			
			return soapMessage;
		} catch (SOAPException e) {
			System.out.println("error " + e);
		}
		return null;
	}
	
	public static SOAPMessage createCreateFolderSoapMessage(String repositoryID, String folderID, String objectTypeID, String name, String nationalID, String appFrmNo, String docID)
	{
		try {
			MessageFactory messageFactory = MessageFactory.newInstance();
	        SOAPMessage soapMessage = messageFactory.createMessage();
	        SOAPPart soapPart = soapMessage.getSOAPPart();
	        
	        SOAPEnvelope envelope = soapPart.getEnvelope();
	        envelope.addNamespaceDeclaration("ns", NAMESPACE);
	        envelope.addNamespaceDeclaration("ns1", NAMESPACE1);
	        //envelope.addNamespaceDeclaration("soapenv", NAMESPACEEV);
	        
	        String authorization = Base64.getEncoder().encodeToString((username_shinhan+":"+password_shinhan).getBytes());
	        MimeHeaders hd = soapMessage.getMimeHeaders();
	        hd.addHeader("Authorization", "Basic " + authorization);
	        //hd.addHeader("Enable MTOM", "true");
	        
	        // SOAP Body
	        SOAPBody soapBody = envelope.getBody();

	        SOAPElement createFolder = soapBody.addChildElement("createFolder", "ns");
	            SOAPElement repositoryIdElement = createFolder.addChildElement("repositoryId", "ns");
	            	repositoryIdElement.addTextNode(repositoryID);
	            SOAPElement properties = createFolder.addChildElement("properties", "ns");
	            	SOAPElement propertyId = properties.addChildElement("propertyId", "ns1");
	            		propertyId.setAttribute("propertyDefinitionId", "cmis:objectTypeId");
	            			SOAPElement value = propertyId.addChildElement("value", "ns1");
	            				value.addTextNode(objectTypeID);
	            	SOAPElement propertyStringName = properties.addChildElement("propertyString", "ns1");
	            		propertyStringName.setAttribute("propertyDefinitionId", "cmis:name");
	    	            	SOAPElement valueName = propertyStringName.addChildElement("value", "ns1");
	    	            		valueName.addTextNode(name);
            		SOAPElement propertyStringCustID = properties.addChildElement("propertyString", "ns1");
	            		propertyStringCustID.setAttribute("propertyDefinitionId", "CUSTID");
	    	            	SOAPElement valueCustID = propertyStringCustID.addChildElement("value", "ns1");
	    	            		valueCustID.addTextNode(nationalID);
            		SOAPElement propertyStringAppFrmNo = properties.addChildElement("propertyString", "ns1");
            		propertyStringAppFrmNo.setAttribute("propertyDefinitionId", "APPFRMNO");
	    	            	SOAPElement valueAppFrmNo = propertyStringAppFrmNo.addChildElement("value", "ns1");
	    	            		valueAppFrmNo.addTextNode(appFrmNo);
            		SOAPElement propertyStringDocID = properties.addChildElement("propertyString", "ns1");
            		propertyStringDocID.setAttribute("propertyDefinitionId", "DOCID");
	    	            	SOAPElement valueDocID = propertyStringDocID.addChildElement("value", "ns1");
	    	            	valueDocID.addTextNode(docID);
        		SOAPElement folderId = createFolder.addChildElement("folderId", "ns");
            	folderId.addTextNode(folderID);
	            
            soapMessage.saveChanges();
            return soapMessage;
		}
		catch(Exception ex) {
			System.out.println("error " + ex);
		}
		return null;
	}
	
	// Create SOAP Connection
	public static SOAPConnection createSoapConnection()
	{
		try {
			SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
			SOAPConnection soapConnection = soapConnectionFactory.createConnection();
			return soapConnection;
		} catch (SOAPException e){
			System.out.println("error connection " + e);
		}
		return null;
	}
	
	//checkout document then checkin new version
	public static SOAPMessage callVersionDocument(String repositoryId, String objectId, boolean isMajor, List<FilenetProperty> propertyList, File fileUpload) throws SOAPException
	{
		SOAPMessage soapCheckOutMessage = createCheckOutSoapMessage(repositoryId, objectId);
        SOAPMessage soapCheckInMessage = createCheckinSoapMessage(repositoryId, objectId, isMajor, propertyList, fileUpload);
        
        // Create SOAP Connection
        SOAPConnection soapConnection = createSoapConnection();
        // Send SOAP Message to SOAP Server
        SOAPMessage checkOutResponse = soapConnection.call(soapCheckOutMessage, action_version_shinhan);
        
        try {
        	SOAPMessage checkInResponse = soapConnection.call(soapCheckInMessage, action_version_shinhan);
        	SOAPBody soapBodyResponse = checkInResponse.getSOAPBody();
    		Iterator<Node> iterator = soapBodyResponse.getChildElements();
            SOAPElement child_1 = (SOAPElement)iterator.next();
            if( child_1.getLocalName().equals(new String("Fault")))
            {
            	SOAPMessage cancelCheckOutMessage = createCancelCheckOutSoapMessage(repositoryId, objectId);
            	SOAPMessage cancelCheckOutResponse = soapConnection.call(cancelCheckOutMessage, action_version_shinhan);
            	System.out.println("checkIn return Fault");
            }
        	soapConnection.close(); 
        	return checkInResponse;
        } catch (SOAPException e) {
        	System.out.println("error checkIn document " + e);
        	SOAPMessage cancelCheckOutMessage = createCancelCheckOutSoapMessage(repositoryId, objectId);
        	soapConnection.call(cancelCheckOutMessage, action_version_shinhan);
        	soapConnection.close(); 
        	return null;
        }
        
	}
	
	public static String getFolderIDFromName(String name, String classFolder)
	{
		try {
			SOAPMessage query = createQuerySoapMessage(repositoryId_shinhan, "SELECT cmis:objectId FROM " + classFolder + " WHERE Name = '" + name + "'");
			// Create SOAP Connection
	        SOAPConnection soapConnection = createSoapConnection();
	        
	        SOAPMessage response = soapConnection.call(query, action_query_shinhan);
	        SOAPBody soapBodyResponse = response.getSOAPBody();
    		Iterator<Node> iterator = soapBodyResponse.getChildElements();
            SOAPElement child_1 = (SOAPElement)iterator.next();
            if( child_1.getLocalName().equals(new String("Fault")))
            {
            	return "fault query";
            } else {
            	SOAPElement child_2 = (SOAPElement)child_1.getChildElements().next();
            	SOAPElement child_3 = (SOAPElement)child_2.getChildElements().next();
            	//ID found
            	if(child_3.getLocalName().equals(new String("objects")))
            	{
            		SOAPElement properties = (SOAPElement)child_3.getChildElements().next();
            		if(properties.getLocalName().equals(new String("properties")) )
            		{
	            		//SOAPElement folder = (SOAPElement)properties.getChildElements().next();
	            		SOAPElement propertyID = (SOAPElement)properties.getChildElements().next();
	            		SOAPElement value = (SOAPElement)propertyID.getChildElements().next();
	            		
	            		return  value.getValue().substring(4);
            		} else return "ID not found";
            	} else {
            		return "ID not found";
            	}
            }
	        
		} catch(SOAPException e) {
			return "error" + e;
		}
	}
	
	public static String getFolderIDFromNationalID(String custID, String classFolder)
	{
		try {
			SOAPMessage query = createQuerySoapMessage(repositoryId_shinhan, "SELECT cmis:objectId FROM " + classFolder + " WHERE CUSTID = '" + custID + "'");
			// Create SOAP Connection
	        SOAPConnection soapConnection = createSoapConnection();
	        
	        SOAPMessage response = soapConnection.call(query, action_query_shinhan);
	        SOAPBody soapBodyResponse = response.getSOAPBody();
    		Iterator<Node> iterator = soapBodyResponse.getChildElements();
            SOAPElement child_1 = (SOAPElement)iterator.next();
            if( child_1.getLocalName().equals(new String("Fault")))
            {
            	return "fault query";
            } else {
            	SOAPElement child_2 = (SOAPElement)child_1.getChildElements().next();
            	SOAPElement child_3 = (SOAPElement)child_2.getChildElements().next();
            	//ID found
            	if(child_3.getLocalName().equals(new String("objects")))
            	{
            		SOAPElement properties = (SOAPElement)child_3.getChildElements().next();
            		if(properties.getLocalName().equals(new String("properties")) )
            		{
	            		//SOAPElement folder = (SOAPElement)properties.getChildElements().next();
	            		SOAPElement propertyID = (SOAPElement)properties.getChildElements().next();
	            		SOAPElement value = (SOAPElement)propertyID.getChildElements().next();
	            		
	            		return  value.getValue().substring(4);
            		} else return "ID not found";
            	} else {
            		return "ID not found";
            	}
            }
	        
		} catch(SOAPException e) {
			return "error" + e;
		}
	}
	
}

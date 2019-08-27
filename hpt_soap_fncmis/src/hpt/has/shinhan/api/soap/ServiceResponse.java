package hpt.has.shinhan.api.soap;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.soap.AttachmentPart;
import javax.xml.soap.Node;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import org.w3c.dom.Attr;

public class ServiceResponse {
	//parse xml response getContentStream operation
	public static byte[] parseGetContentStreamResponse(SOAPMessage response) throws SOAPException
	{
		//parse xml response
		SOAPBody soapBodyResponse = response.getSOAPBody();
		Iterator<Node> iterator = soapBodyResponse.getChildElements();
        SOAPElement child_1 = (SOAPElement)iterator.next();
        SOAPElement child_2 = (SOAPElement)child_1.getChildElements().next();
        System.out.println(child_2.getElementName().getLocalName());
        Iterator<Node> contentStreamIterator = child_2.getChildElements();
        SOAPElement length = (SOAPElement)contentStreamIterator.next();
        System.out.println("length " + length.getValue());
        SOAPElement mimeType = (SOAPElement)contentStreamIterator.next();
        System.out.println("mimeType " + mimeType.getValue());
        SOAPElement fileName = (SOAPElement)contentStreamIterator.next();
        System.out.println("fileName " + fileName.getValue());
		
        //get file
		Iterator<AttachmentPart> attachments = response.getAttachments();
		if (attachments.hasNext()) {
			AttachmentPart att = attachments.next();
			System.out.println(att.getRawContentBytes());
			System.out.println("size " + att.getRawContentBytes().length);
			return att.getRawContentBytes();
		}
		
		return null;
	}
	
	//parse xml response createDocument operation
	public static void parseCreateDocumentResponse(SOAPMessage response) throws SOAPException
	{
		//parse xml response
		SOAPBody soapBodyResponse = response.getSOAPBody();
		Iterator<Node> iterator = soapBodyResponse.getChildElements();
        SOAPElement child_1 = (SOAPElement)iterator.next();
        
        System.out.println(child_1.getElementName().getLocalName());
        Iterator<Node> createDocumentResponseIterator = child_1.getChildElements();
        SOAPElement objectID = (SOAPElement)createDocumentResponseIterator.next();
        System.out.println("objectID " + objectID.getValue());
	}
	
	//parse xml response updateProperties operation
	public static void parseUpdatePropertiesResponse(SOAPMessage response) throws SOAPException
	{
		//parse xml response
		SOAPBody soapBodyResponse = response.getSOAPBody();
		Iterator<Node> iterator = soapBodyResponse.getChildElements();
        SOAPElement updateProperties = (SOAPElement)iterator.next();
        //SOAPElement updateProperties = (SOAPElement)child_1.getChildElements().next();
        
        Iterator<Node> updatePropertiesIterator = updateProperties.getChildElements();
        SOAPElement objectID = (SOAPElement)updatePropertiesIterator.next();
        System.out.println("ObjectID " + objectID.getValue());
        SOAPElement changeToken = (SOAPElement)updatePropertiesIterator.next();
        System.out.println("changeToken " + changeToken.getValue());
        
	}
	
	public static boolean checkSoapResponse(SOAPMessage response)
	{
		try {
			SOAPBody body = response.getSOAPBody();
			Iterator<Node> bodyChilds = body.getChildElements();
			SOAPElement isFaultNode = (SOAPElement) bodyChilds.next();
			if(isFaultNode.getLocalName().equals(new String("Fault")))
				return true;
			else return false;
		} catch (SOAPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean checkSoapFaultResponse(SOAPMessage response)
	{
		try {
			SOAPBody body = response.getSOAPBody();
			Iterator<Node> bodyChilds = body.getChildElements();
			SOAPElement isFaultNode = (SOAPElement) bodyChilds.next();
			System.out.println(isFaultNode.getLocalName());
			if(isFaultNode.getLocalName().equals(new String("Fault")))
				return true;
			else return false;
		} catch (SOAPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public static SoapFault getSoapFaultResponse(SOAPMessage response) throws SOAPException
	{
		SOAPBody body = response.getSOAPBody();
		Iterator<Node> bodyElements = body.getChildElements();
		SOAPElement faultNode = (SOAPElement) bodyElements.next();
		Iterator<Node> faultChildElements = faultNode.getChildElements();
		faultChildElements.next();
		faultChildElements.next();
		SOAPElement detailNode = (SOAPElement)((SOAPElement)faultChildElements.next()).getNextSibling();
		Iterator<Node> detailChildElements = detailNode.getChildElements();
		SOAPElement type = (SOAPElement) detailChildElements.next();
		SOAPElement code = (SOAPElement) detailChildElements.next();
		SOAPElement message = (SOAPElement) detailChildElements.next();
		SoapFault fault = new SoapFault(type.getValue(), code.getValue(), message.getValue());
		
		return fault;
	}
	
	public static List<FilenetObject> getSoapQueryResponse(SOAPMessage response) throws SOAPException {
		List<FilenetObject> objectList = new ArrayList<FilenetObject>();
		
		SOAPBody body = response.getSOAPBody();
		SOAPElement mainChildNode = (SOAPElement)body.getChildElements().next();
		
		Iterator<Node> objectsElements = ((SOAPElement)mainChildNode.getChildElements().next()).getChildElements();
		while(objectsElements.hasNext())
		{
			SOAPElement objElement = (SOAPElement)objectsElements.next();
			
			Iterator<Node> objProperty = objElement.getChildElements();
			
			while(objProperty.hasNext())
			{
				String guid = "";
				List<FilenetProperty> properties = new ArrayList<FilenetProperty>();
				
				Node objPropertyNode = objProperty.next();
				if(objPropertyNode.getNodeType() != 1) break;
				
				//SOAPElement objPropertyElement = (SOAPElement) objProperty.next();
				SOAPElement objPropertyElement = (SOAPElement) objPropertyNode;
				
				if(objPropertyElement.getLocalName().equals(new String("properties")))
				{
					Iterator<Node> iterator = objPropertyElement.getChildElements();
					while(iterator.hasNext())
					{
						SOAPElement propertyElement = (SOAPElement) (iterator.next());
						
						if(!propertyElement.hasChildNodes())
						{
							Attr defIDElement = propertyElement.getAttributeNode("propertyDefinitionId");
							Attr localNameElement = propertyElement.getAttributeNode("localName");
							Attr displayNameElement = propertyElement.getAttributeNode("displayName");
							Attr queryNameElement = propertyElement.getAttributeNode("queryName");
							FilenetProperty property = new FilenetProperty(defIDElement.getValue(), propertyElement.getLocalName(), "", localNameElement.getValue(), displayNameElement.getValue(), queryNameElement.getValue());
							properties.add(property);
						}
						else {
							if(propertyElement.getLocalName().equals(new String("GUID")))	//object's ID
							{
								//SOAPElement valueElement = (SOAPElement) (propertyElement.getChildElements().next());
								guid = propertyElement.getValue();
							}
							else {	//object's property
								
								SOAPElement properiesElement = (SOAPElement) (propertyElement.getChildElements().next());
								SOAPElement propertyValueElement =  (SOAPElement) (propertyElement.getChildElements().next());
								Attr defIDElement = propertyElement.getAttributeNode("propertyDefinitionId");
								Attr localNameElement = propertyElement.getAttributeNode("localName");
								Attr displayNameElement = propertyElement.getAttributeNode("displayName");
								Attr queryNameElement = propertyElement.getAttributeNode("queryName");
								
								FilenetProperty property = new FilenetProperty(defIDElement.getValue(), properiesElement.getLocalName(), propertyValueElement.getValue(), localNameElement.getValue(), displayNameElement.getValue(), queryNameElement.getValue());
								properties.add(property);
							}
						}
					}
					
					objectList.add(new FilenetObject(guid, properties));
				}
				
			}
			
		}
		
		return objectList;
	}
	
	public static String getSoapCreateFolderResponse(SOAPMessage response)
	{
		try {
			SOAPBody body = response.getSOAPBody();
			SOAPElement createFolderResponse = (SOAPElement)body.getChildElements().next();
			SOAPElement objIDElement = (SOAPElement)createFolderResponse.getChildElements().next();
			return objIDElement.getValue();
		} catch (SOAPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}

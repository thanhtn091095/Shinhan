package hpt.has.shinhan.api.soap;

import java.util.List;

class FilenetProperty {
	private String value;
	private String dataType;
	private String definitionID;
	private String localName;
	private String displayName;
	private String queryName;
	
	public String getValue()
	{
		return value;
	}
	
	public String getDataType()
	{
		return dataType;
	}
	
	public String getDefinitionID()
	{
		return definitionID;
	}
	
	public String getLocalName()
	{
		return localName;
	}
	
	public String getDisplayName()
	{
		return displayName;
	}
	
	public String getQueryName()
	{
		return queryName;
	}
	
	public FilenetProperty(String definitionID, String dataType, String value)
	{
		this.definitionID = definitionID;
		this.dataType = dataType;
		this.value = value;
	}
	
	public FilenetProperty(String definitionID, String dataType, String value, String localName, String displayName, String queryName)
	{
		this.definitionID = definitionID;
		this.dataType = dataType;
		this.value = value;
		this.localName = localName;
		this.displayName = displayName;
		this.queryName = queryName;
	}

}

class FilenetObject {
	private String GUID;
	private List<FilenetProperty> properties;
	
	public String getGUID()
	{
		return GUID;
	}
	
	public List<FilenetProperty> getProperties() {
		return properties;
	}
	
	public FilenetObject(String GUID, List<FilenetProperty> properties)
	{
		this.GUID = GUID;
		this.properties = properties;
	}
}

class ContentStream {
	private String length;
	private String mimeType;
	private String fileName;
	private byte[] content;
	
	public String getLength() {
		return length;
	}
	
	public String getMimeType() {
		return mimeType;
	}
	
	public String getFileName() {
		return fileName;
	}
	
	public byte[] getContent() {
		return content;
	}
	
	public ContentStream (String length, String mimeType, String fileName, byte[] content) {
		this.length = length;
		this.mimeType = mimeType;
		this.fileName = fileName;
		this.content = content;
	}
}

class SoapFault {
	private String type;
	private String code;
	private String message;
	
	public String getType() {
		return type;
	}
	
	public String getCode() {
		return code;
	}
	
	public String getMessage() {
		return message;
	}
	
	public SoapFault(String type, String code, String message)
	{
		this.type = type;
		this.code = code;
		this.message = message;
	}
}

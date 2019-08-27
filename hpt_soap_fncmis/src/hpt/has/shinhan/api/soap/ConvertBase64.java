package hpt.has.shinhan.api.soap;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Base64;

public class ConvertBase64 {
	//Base64 -> OutputStream
	public static OutputStream writeBase64ToOutputStream(String base64String) throws IOException
	{
		OutputStream out = new ByteArrayOutputStream();
		try {
			byte[] dataBytes = Base64.getDecoder().decode(base64String);
			InputStream inputStream = new ByteArrayInputStream(dataBytes);
			int read;
            byte[] bytes = new byte[1024];

            while ((read = inputStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return out;
	}
	
	//convert OutputStream to file, test Base64 -> OutputStream function
	public static void writeOutputStreamToFile(OutputStream outputStream, String fileName) throws IOException
	{
		try {
			OutputStream out = new FileOutputStream(fileName);
			
			byte[] dataBytes = ((ByteArrayOutputStream)outputStream).toByteArray();
			int read;
            byte[] bytes = new byte[1024];
            InputStream inputStream = new ByteArrayInputStream(dataBytes);
            while ((read = inputStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//Base64 -> File
	public static void writeBase64ToFile(String base64String, String outputFile, String mimeType) throws IOException
	{
		try{
			String extension = "";
			switch (mimeType) {
				case "application/pdf":
					extension = ".pdf";
					break;
				case "image/jpeg":
					extension = ".jpg";
					break;
				case "image/tiff":
					extension = ".tif";
					break;
				default:
					break;
			}
			outputFile += extension;
			OutputStream out = new FileOutputStream(outputFile);
			byte[] dataBytes = Base64.getDecoder().decode(base64String);
			InputStream inputStream = new ByteArrayInputStream(dataBytes);
			int read;
            byte[] bytes = new byte[1024];

            while ((read = inputStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            out.close();
    	} catch (IOException e) {
    	    e.printStackTrace();
    	}
	}
	
	// byte[] -> File
	public static void writeByteArrayToFile(byte[] dataBytes, String outputFile, String mimeType) throws IOException
	{
		try{
			String extension = "";
			switch (mimeType) {
				case "application/pdf":
					extension = ".pdf";
					break;
				case "image/jpeg":
					extension = ".jpg";
					break;
				case "image/tiff":
					extension = ".tif";
					break;
				default:
					break;
			}
			outputFile += extension;
			OutputStream out = new FileOutputStream(outputFile);
    	    out.write(dataBytes);
    	    out.close();
    	} catch (IOException e) {
    	    e.printStackTrace();
    	}
	}
	
	// InputStream -> File
    public static void writeInputStreamToFile(InputStream inputStream, String outputFile, String mimeType) 
		throws IOException {

        try {

        	String extension = "";
			switch (mimeType) {
				case "application/pdf":
					extension = ".pdf";
					break;
				case "image/jpeg":
					extension = ".jpg";
					break;
				case "image/tiff":
					extension = ".tif";
					break;
				default:
					break;
			}
			outputFile += extension;
			
			FileOutputStream outputStream = new FileOutputStream(outputFile);
            int read;
            byte[] bytes = new byte[1024];

            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
            outputStream.close();
			// commons-io
            //IOUtils.copy(inputStream, outputStream);

        } catch (IOException e) {
        	e.printStackTrace();
        }
        
    }
    
    public static String encodeFileToBase64Binary(File file){
        String encodedfile = null;
        try {
            FileInputStream fileInputStreamReader = new FileInputStream(file);
            byte[] bytes = new byte[(int)file.length()];
            fileInputStreamReader.read(bytes);
            encodedfile = Base64.getEncoder().encodeToString(bytes);
            fileInputStreamReader.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            System.out.println("file not found ");
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
        	System.out.println("IOException ");
            e.printStackTrace();
        }

        return encodedfile;
    }
    
    public static void deleteFile(File file)
    {
    	if(file.exists())
    		file.delete();
    }
}

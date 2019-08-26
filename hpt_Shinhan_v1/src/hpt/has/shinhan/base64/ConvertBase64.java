package hpt.has.shinhan.base64;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Base64;

public class ConvertBase64 {

	static String samplePDFFileName = ".\\target\\L1.A1001001A18L15B11920F52635.V1";
	public static void main(String[] args) {
		File f = new File(samplePDFFileName);
		String output = ".\\target\\test04";
		String encodestring = ConvertBase64.encodeFileToBase64Binary(f);
		System.out.println(encodestring);
		try {
			//ConvertBase64.writeByteToPDFFile(decodedPDF, "C:\\Users\\duyl\\Documents\\MyPDF.pdf");
			writeBase64ToFile(encodestring, output, "image/tiff");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//Base64 -> File
	public static String writeBase64ToFile(String base64String, String outputFile, String mimeType) throws IOException
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
    	    out.write(dataBytes);
    	    out.close();
    	} catch (IOException e) {
    		System.out.println("hpt.has.shinhan.base64.ConvertBase64.writeBase64ToFile.Error: " + e);
    	}
		return outputFile;
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
    		System.out.println("hpt.has.shinhan.base64.ConvertBase64.writeByteArrayToFile.Error: " + e);
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
        	System.out.println("hpt.has.shinhan.base64.ConvertBase64.writeInputStreamToFile.Error: " + e);
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
        	System.out.println("hpt.has.shinhan.base64.ConvertBase64.encodeFileToBase64Binary.Error: " + e);
        } catch (IOException e) {
            // TODO Auto-generated catch block
        	System.out.println("hpt.has.shinhan.base64.ConvertBase64.encodeFileToBase64Binary.Error: " + e);
        }

        return encodedfile;
    }
    
    public static void deleteFile(File file)
    {
    	if(file.exists())
    		file.delete();
    }
}

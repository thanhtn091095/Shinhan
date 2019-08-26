package hpt.has.shinhan.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadFileURL {
	
	static String url = "http://10.148.133.11:8080";
	
	private static final int BUFFER_SIZE = 4096;
	
	public static void main(String[] args)
	{
		
		//DownloadFileFromURL(url);
		
		String fileURL = "http://10.148.133.11:8080/data119/lbosdata/00001/154/L1.A1001001A18L15B11920F52635.V1";
        String saveDir = System.getProperty("user.dir");
        try {
            String path = DownloadFile(fileURL, saveDir);
            
            System.out.println("hpt.has.shinhan.file.DownloadFileURL.main.Info path: " + path);
            
            File f = new File(path);
            System.out.println("hpt.has.shinhan.file.DownloadFileURL.main.Info length: " + f.length());
            //f.delete();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
	}

	
	public static File DownloadFileFromURL(String url) {
		
		File f = null;
		
		try {
			
			URL link = new URL(url);
			f = new File(link.getFile());
			
			if(f != null && f.isFile()) {
				System.out.println("hpt.has.shinhan.file.DownloadFileURL.DownloadFileURL.Info DownloadFileURL: successful");
			}
			else {				
				System.out.println("hpt.has.shinhan.file.DownloadFileURL.DownloadFileURL.Info DownloadFileURL: fail");
			}
		}
		catch(Exception ex) {
			
			System.out.println("hpt.has.shinhan.file.DownloadFileURL.DownloadFileURL.Error DownloadFileURL: " + ex);
		}
		
		return f;
	}
	
	 public static String DownloadFile(String fileURL, String saveDir)
	     
			 throws IOException {
		 
		 String result = null;
		 try {
			 
		        URL url = new URL(fileURL);
		        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
		        int responseCode = httpConn.getResponseCode();
		 
		        // always check HTTP response code first
		        if (responseCode == HttpURLConnection.HTTP_OK && httpConn.getContentLength() > 0) {
		        	
		            String fileName = "";
		            String disposition = httpConn.getHeaderField("Content-Disposition");
		            String contentType = httpConn.getContentType();
		            int contentLength = httpConn.getContentLength();
		 
		            if (disposition != null) {
		                // extracts file name from header field
		                int index = disposition.indexOf("filename=");
		                if (index > 0) {
		                    fileName = disposition.substring(index + 10,
		                            disposition.length() - 1);
		                }
		            } else {
		                // extracts file name from URL
		                fileName = fileURL.substring(fileURL.lastIndexOf("/") + 1,
		                        fileURL.length());
		            }
		 
		            System.out.println("hpt.has.shinhan.file.DownloadFileURL.downloadFile.Info Content-Type = " + contentType);
		            System.out.println("hpt.has.shinhan.file.DownloadFileURL.downloadFile.Info Content-Disposition = " + disposition);
		            System.out.println("hpt.has.shinhan.file.DownloadFileURL.downloadFile.Info Content-Length = " + contentLength);
		            System.out.println("hpt.has.shinhan.file.DownloadFileURL.downloadFile.Info fileName = " + fileName);
		 
		            // opens input stream from the HTTP connection
		            InputStream inputStream = httpConn.getInputStream();
		            String saveFilePath = saveDir + File.separator + fileName;
		            
		            result = saveFilePath;
		             
		            // opens an output stream to save into file
		            FileOutputStream outputStream = new FileOutputStream(saveFilePath);
		 
		            int bytesRead = -1;
		            byte[] buffer = new byte[BUFFER_SIZE];
		            while ((bytesRead = inputStream.read(buffer)) != -1) {
		                outputStream.write(buffer, 0, bytesRead);
		            }
		 
		            outputStream.close();
		            inputStream.close();
		 
		            System.out.println("hpt.has.shinhan.file.DownloadFileURL.downloadFile.Info File downloaded");
		        } 
		        else {
		            System.out.println("hpt.has.shinhan.file.DownloadFileURL.downloadFile.Info No file to download. Server replied HTTP code: " + responseCode);
		        }
		        httpConn.disconnect();
		        
		         
		 	}
		 	catch(Exception e) {
		 		System.out.println("hpt.has.shinhan.file.DownloadFileURL.downloadFile.Error downloadFile: " + e);
		 	}
		 	return result;
	    }
}

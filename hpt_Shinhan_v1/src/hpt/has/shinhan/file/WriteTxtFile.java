package hpt.has.shinhan.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class WriteTxtFile {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		OutputStreamWriter osw = OpenFileText(".\\target\\thanhtn.txt");
		
		WriteFileText(osw, "1");
		WriteFileText(osw, "\n");
		WriteFileText(osw, "1");
		WriteFileText(osw, "12");
		WriteFileText(osw, "1");
		WriteFileText(osw, "1");
		
		CloseFileText(osw);
	}
	
	public static OutputStreamWriter OpenFileText(String path) {
		OutputStreamWriter osw = null;
		try 
		{
			  
			File fout = new File(path);
			FileOutputStream fos = new FileOutputStream(fout);
			osw = new OutputStreamWriter(fos);
			//osw.close();
		} 
		catch (Exception e) {
			  // File not found
			System.out.println("hpt.has.shinhan.file.WriteTxtFile.OpenFileText.Error OpenFileText: " + e);
		}
		
		return osw;
	}
	
	public static void WriteFileText(OutputStreamWriter osw, String text) {
		
		try 
		{
			osw.write(text);
		} 
		catch (FileNotFoundException e) {
			  // File not found
			  e.printStackTrace();System.out.println("hpt.has.shinhan.file.WriteTxtFile.WriteFileText.Error FileNotFoundException: " + e);
		} 
		catch (IOException e) {
			  // Error when writing to the file
			 System.out.println("hpt.has.shinhan.file.WriteTxtFile.WriteFileText.Error IOException: " + e);
			
		}
	}
	
	public static void CloseFileText(OutputStreamWriter osw) {
		
		try 
		{
			osw.close();
		} 
		catch (FileNotFoundException e) {
			  // File not found
			  e.printStackTrace();System.out.println("hpt.has.shinhan.file.WriteTxtFile.CloseFileText.Error FileNotFoundException: " + e);
		} 
		catch (IOException e) {
			  // Error when writing to the file
			 System.out.println("hpt.has.shinhan.file.WriteTxtFile.CloseFileText.Error IOException: " + e);
			
		}
	}

}

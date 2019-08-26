package hpt.has.shinhan.db2;

import java.util.UUID;

public class GenerateUniqueIDUsingGUID {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Unique ID : "+generateUniqueID());
	}

	public static String generateUniqueID() {
		String randomUUID = "";
		try {
			UUID uuid = UUID.randomUUID();
			randomUUID = uuid.toString();
		} 
		catch (Exception e) {
			System.out.println("hpt.has.shinhan.db2.GenerateUniqueIDUsingGUID.generateUniqueID.Error generateUniqueID: " + e);
		}
		return randomUUID;
	}
}

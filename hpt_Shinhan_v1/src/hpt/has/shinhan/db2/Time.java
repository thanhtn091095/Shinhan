package hpt.has.shinhan.db2;

import java.util.Date;
import java.util.UUID;

public class Time {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String result = parseTimeToString(new Date());
		System.out.println("hpt.has.shinhan.db2.Time.main.Info parseTimeToString: " + result);
		
	}

	public static String parseTimeToString(Date date) {

		String result = "";
		try {

			String temp = date.toString();
			
			String[] str = temp.split(" ");
			
			String[] mon =  {"None", "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
			
			int j = 0;
			
			for(int i = 0; i < mon.length; i++) {
				
				if(mon[i].equals(str[1])) {
					j = i;
					break;
				}
			}
			
			if(j < 10) {
				result = str[5] + "-0" + j + "-" + str[2] + " " + str[3] + ".000";
			}
			else {
				result = str[5] + "-" + j + "-" + str[2] + " " + str[3] + ".000";
			}
			
			
		} 
		catch (Exception e) {
			System.out.println("hpt.has.shinhan.db2.Time.parseTimeToString.Error parseTimeToString: " + e);
		}
		return result;
	}
}

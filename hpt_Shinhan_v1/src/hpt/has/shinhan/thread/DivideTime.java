package hpt.has.shinhan.thread;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class DivideTime {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String date1 = "31-12-2018";
		String date2 = "31-03-2019";
		List<Timespan> dateList = DivideTime.divideTime(date1, date2, 6);
		for(int i=0;i<dateList.size();i++)
		{
			System.out.println("Division " + (i+1));
			System.out.println("start: " + dateList.get(i).startDate);
			System.out.println("end: " + dateList.get(i).endDate);
			System.out.println();
		}
	}
	
	public static List<Timespan> divideTime(String startDate, String endDate, int divideNumber)
	{
		List<Timespan> dateList = new ArrayList<Timespan>();
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		
		try {
			Date _startDate = format.parse(startDate);
			Date _endDate = format.parse(endDate);
			long diff = _endDate.getTime() - _startDate.getTime();
			//System.out.println("Days: " + TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
			
			long daysDiff = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
			long daysRemain = daysDiff;
			long length = daysDiff / divideNumber;
			for(int i=0;i<divideNumber;i++)
			{
				daysRemain = daysDiff - length*i;
				if(i == (divideNumber - 1))
				{
					daysRemain = daysDiff - length * i;
					length = daysRemain;
				}
//				System.out.println("length " + length);
				long startDateTime = _startDate.getTime();
				long endDateTime = _startDate.getTime() + TimeUnit.MILLISECONDS.convert(length, TimeUnit.DAYS);
				
				Date dividedStartDate = new Date(startDateTime);
				Date dividedEndDate = new Date(endDateTime);
				LocalDate localStartDate = dividedStartDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				LocalDate localEndDate = dividedEndDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				
				Timespan dividedTimespan = new Timespan();
				dividedTimespan.startDate = String.format("%02d", localStartDate.getDayOfMonth())  + "-" + String.format("%02d", localStartDate.getMonthValue()) + "-" + String.format("%04d", localStartDate.getYear());
				dividedTimespan.endDate = String.format("%02d", localEndDate.getDayOfMonth())  + "-" + String.format("%02d", localEndDate.getMonthValue()) + "-" + String.format("%04d", localEndDate.getYear());
				dateList.add(dividedTimespan);
				_startDate = format.parse(dividedTimespan.endDate);
				
			}
		} catch (Exception e) {
			System.out.println("hpt.has.shinhan.thread.DivideTime.divideTime.Error divideTime: " + e);
		}
		
		return dateList;
	}

}

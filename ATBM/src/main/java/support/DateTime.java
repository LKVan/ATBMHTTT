package support;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.api.Date;

public class DateTime {
	
	//Ham convert chuoi sang chuoi ngay
	public static Date formatDate(String date) {
		String[] splitDate = formartDateString(date).split("-");
		
		
		int year = Integer.parseInt(splitDate[0]);
		int month = Integer.parseInt(splitDate[1]);
		int day = Integer.parseInt(splitDate[2]);
        
		Date dateTime = new Date(day, month, year);
        return dateTime;
	}
	
	//Ham lay ra chuoi ngay trong chuoi ki tu
	public static String formartDateString(String date) {
		Pattern pattern = Pattern.compile("\\d{4}[-]\\d{2}[-]\\d{2}");
        Matcher matcher = pattern.matcher(date);
         
        String result ="";
        matcher.find();
        result = date.substring(matcher.start(),matcher.end());
        
        return result;
	}
	
	public static void main(String[] args) {
		
		System.out.println(formatDate("2023-04-19T23:59:59Z"));
	}

}

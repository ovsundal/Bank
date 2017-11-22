package test;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class testdate {

	public static void main(String[] args) {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		LocalDate ld = LocalDate.of( 2017 , 11 , 10 );
		Date date = Date.from(ld.atStartOfDay(ZoneId.systemDefault()).toInstant());
		
		System.out.println(date.toString());
	}
}

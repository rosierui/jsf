package org.moonwave;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

public class Test {
	public static void main(String[] argv) {
		//Calendar calendar = new GregorianCalendar();
		Calendar calendar = Calendar.getInstance();
		TimeZone timeZone = calendar.getTimeZone();

		System.out.println(timeZone);
		System.out.println(timeZone.getID());
		System.out.println(timeZone.getDisplayName());
		System.out.println(timeZone.getDSTSavings());
		String[] availableTZs = timeZone.getAvailableIDs();
		List<String> tzs = Arrays.asList(availableTZs);
		Collections.sort(tzs);
		for (String tz : tzs) {
			System.out.println(tz);
		}
	}
}

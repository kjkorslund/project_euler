package problems.p19;

import java.util.Calendar;

public class P19 {
	/*
	 * You are given the following information, but you may prefer to do some
	 * research for yourself.
	 * 
	 * 1 Jan 1900 was a Monday.
	 * 
	 * Thirty days has September, April, June and November. All the rest have
	 * thirty-one, Saving February alone, Which has twenty-eight, rain or shine.
	 * And on leap years, twenty-nine.
	 * 
	 * A leap year occurs on any year evenly divisible by 4, but not on a
	 * century unless it is divisible by 400.
	 * 
	 * How many Sundays fell on the first of the month during the twentieth
	 * century (1 Jan 1901 to 31 Dec 2000)?
	 */
	
	public static void main(String[] args) {
		Calendar c = Calendar.getInstance();
		c.clear();
		c.set(Calendar.YEAR, 1901);
		while(c.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) c.add(Calendar.DATE, 1);
		
		int count = 0;
		while(c.get(Calendar.YEAR) < 2001) {
			if (c.get(Calendar.DAY_OF_MONTH) == 1) count++;
			c.add(Calendar.DATE,7);
		}
		System.out.println(count);
	}
}

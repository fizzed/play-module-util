package com.fizzed.play.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import play.Configuration;
import play.Play;

public class PlayUtil {

	/** Jul 4, 2013 */
	public static final DateTimeFormatter FORMAT_MMM_d_YYYY_FORMAT = DateTimeFormat.forPattern("MMM d, YYYY").withZone(DateTimeZone.UTC);
	/** Jul */
	public static final DateTimeFormatter FORMAT_MMM = DateTimeFormat.forPattern("MMM").withZone(DateTimeZone.UTC);
	/** 07 */
	public static final DateTimeFormatter FORMAT_dd = DateTimeFormat.forPattern("dd").withZone(DateTimeZone.UTC);
	/** April 2013 */
	public static final DateTimeFormatter FORMAT_MMMM_YYYY = DateTimeFormat.forPattern("MMMM YYYY").withZone(DateTimeZone.UTC);
	/** 2013-04 for April 2013 */
	public static final DateTimeFormatter FORMAT_YYYY_MM = DateTimeFormat.forPattern("YYYY-MM").withZone(DateTimeZone.UTC);
	/** 4:29 PM */
	public static final DateTimeFormatter FORMAT_h_mm_a = DateTimeFormat.forPattern("h:mm a").withZone(DateTimeZone.UTC);
	
	public static int getCurrentYear() {
		return new DateTime(DateTimeZone.UTC).getYear();
	}
	
	/** April 2013 */
	public static String longMonthYear(DateTime dt) {
		return FORMAT_MMMM_YYYY.print(dt);
	}
	
	/** Apr 7, 2013 */
	public static String mediumDate(DateTime dt) {
		return FORMAT_MMM_d_YYYY_FORMAT.print(dt);
	}
	
	/** Apr */
	public static String shortMonthName(DateTime dt) {
		return FORMAT_MMM.print(dt);
	}
	
	/** 07 as in July 7, 2013 (leading zero included) */
	public static String paddedDay(DateTime dt) {
		return FORMAT_dd.print(dt);
	}
	
	/** 07 as in July 7, 2013 (leading zero included) */
	public static String paddedDay(Integer day) {
		if (day.intValue() < 10) {
    		return "0" + day;
    	} else {
    		return day.toString();
    	}
	}
	
	/** 4:29 PM */
	public static String twelveHourTime(DateTime dt) {
		return FORMAT_h_mm_a.print(dt);
	}

	public static String getStringChecked(Configuration config, String key, String appendedExceptionMessage) {
		String v = config.getString(key);
        if (v == null) { throw config.reportError(key, key + " not found in application.conf" + (appendedExceptionMessage == null ? "" : " " + appendedExceptionMessage), null); }
        return v;
	}
	
	public static long getMillisecondsChecked(Configuration config, String key, String appendedExceptionMessage) {
		Long v = config.getMilliseconds(key);
		if (v == null) { throw config.reportError(key, key + " not found in application.conf" + (appendedExceptionMessage == null ? "" : " " + appendedExceptionMessage), null); }
		return v;
	}
	
	public static String resourceToString(String name) throws IOException {
		InputStream in = Play.application().resourceAsStream(name);
		
		if (in == null) {
			return null;
		}
		
	    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
	    StringBuilder out = new StringBuilder();
	    String line;
	    while ((line = reader.readLine()) != null) {
	        out.append(line);
	        out.append("\n");
	    }
	    
	    return out.toString();
	}
	
}

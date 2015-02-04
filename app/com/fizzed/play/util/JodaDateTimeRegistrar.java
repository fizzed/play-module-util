package com.fizzed.play.util;

import java.text.ParseException;
import java.util.Locale;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import play.data.format.Formatters;

public class JodaDateTimeRegistrar {

	public static void register() {
		Formatters.register(DateTime.class, new Formatters.AnnotationFormatter<JodaDateTime,DateTime>() {
	        @Override
	        public DateTime parse(JodaDateTime annotation, String input, Locale locale) throws ParseException {
	            if (input == null || input.trim().isEmpty())
	                return null;
	
	            DateTimeZone zone = DateTimeZone.UTC;
	            if (!annotation.zone().isEmpty()) {
	            	zone = DateTimeZone.forID(annotation.zone());
	            }
	            
	            if (annotation.format().isEmpty())
	                return new DateTime(Long.parseLong(input));
	            else
	                return DateTimeFormat.forPattern(annotation.format()).withLocale(locale).withZone(zone).parseDateTime(input);
	        }
	
	        @Override
	        public String print(JodaDateTime annotation, DateTime time, Locale locale) {
	            if (time == null)
	                return null;
	            
	            DateTimeZone zone = DateTimeZone.UTC;
	            if (!annotation.zone().isEmpty()) {
	            	zone = DateTimeZone.forID(annotation.zone());
	            }
	
	            if (annotation.format().isEmpty())
	                return time.getMillis() + "";
	            else
	                return time.withZone(zone).toString(annotation.format(), locale);
	        }
		});
	}
	
}

/*
 *  Copyright 2001-2005 Stephen Colebourne
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.joda.time;

import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import junit.framework.TestCase;

/**
 */
public class TempTest extends TestCase {

	public static void main(String[] args) throws Exception {
	    
	    DateTimeZone zone = DateTimeZone.forID("Africa/Windhoek");
	    System.out.println(zone);
	    
	    for (int i = 0; i < 400; i++) {
            LocalDate date = new LocalDate(1988, 1, 1).plusMonths(i);
            DateTime dt = date.toDateTimeAtStartOfDay(zone);
            System.out.println(dt + " " + zone.getName(dt.getMillis()));
        }
	    
	    
//        TimeZone.setDefault(TimeZone.getTimeZone("EST"));
//        Date date = new Date(2016 - 1900, 1 - 1, 1);
//        LocalDate localDate = new LocalDate(date);
//        assertEquals(new LocalDate(2016, 1, 1), localDate);
//
//        TimeZone.setDefault(TimeZone.getTimeZone("EET"));// Eastern European Time GMT+2
//
//        Date date1 = new Date(2016 - 1900, 1 - 1, 1);
//        LocalDate localDate1 = LocalDate.fromDateFields(date1);
//
//        System.out.println(date1.toString());
//        System.out.println(DateTimeZone.getDefault());
//        System.out.println(localDate1);
//        assertEquals(new LocalDate(2016, 1, 1), localDate1);
	    
//	    String format = "xxxx-ww";
//        String date = "2017-01";
//        
//        DateTimeZone.setDefault(DateTimeZone.UTC);
//
//        // Parse with DateTimeFormat#parseDateTime
//        DateTimeFormatter dtf = DateTimeFormat.forPattern(format).withLocale(Locale.ENGLISH).withZone(DateTimeZone.UTC);
//        DateTime date1 = dtf.parseDateTime(date).toDateTime(DateTimeZone.UTC);
//        System.out.println("Joda 1 => " + date1); // ==> print "2017-01-02T00:00:00.000Z"
//
//        // Parse with DateTimeFormat#parseInto
//        MutableDateTime date2 = new MutableDateTime(0, DateTimeZone.UTC);
//        dtf.parseInto(date2, date, 0);
//        System.out.println("Joda 2 => " + date2); // ==> #FAIL: print "2016-01-10T00:00:00.000Z"
//        System.out.println("Joda 2 => " + date2.getDayOfWeek());
        
//	  //set timezone for Turkey
//	    TimeZone encodingTZ = TimeZone.getTimeZone("Asia/Istanbul");
//	    DateTimeZone dtzTurkey = DateTimeZone.forTimeZone(encodingTZ);
//	                        
//	    TimeZone.setDefault(encodingTZ);
//	    DateTimeZone.setDefault(dtzTurkey);
//	                        
//	    int day = 1;
//	    int month = 1;
//	    int year = 2018;
//	    int hour = 23;
//	    int minute = 59;
//	    int second = 59;
//	                        
//	    Date dtTest = new Date(year-1900, month-1, day, hour, minute, second); // => Mon Jan 01 23:59:59 EET 2018
//	    int checkHour = dtTest.getHours(); // => 23
//	    int checkMinute = dtTest.getMinutes(); // => 59
//	    int checkSecond = dtTest.getSeconds(); // => 59
//	    System.out.println(dtTest);
//        System.out.println(dtTest.getTime());
//        System.out.println(new org.joda.time.DateTime(2018, 1, 1, 23, 59, 59, dtzTurkey).getMillis());
//	    
//	    LocalDate localDateFromDate = new LocalDate(dtTest); // => 2018-01-02 ?!
//        System.out.println(localDateFromDate);
//	                        
//	    Calendar calTest = Calendar.getInstance(encodingTZ);
//	    calTest.setTime(dtTest);
//        System.out.println(calTest);
//        System.out.println(calTest.getTimeInMillis());
//	    LocalDate localDateFromCal = new LocalDate(calTest); // => 2018-01-02 ?!
//        System.out.println(localDateFromCal);
//	                        
//	    Calendar calManuallySet = Calendar.getInstance(encodingTZ);
//	    calManuallySet.set(year, month-1, day, hour, minute, second);
//	    calManuallySet.set(Calendar.MILLISECOND, 0);
//	                        
//	    LocalDate localDateFromCalManuallySet = new LocalDate(calManuallySet); // => 2018-01-02 ?!
//        System.out.println(localDateFromCalManuallySet);
//
//	    org.joda.time.DateTime jdtDateTime = new org.joda.time.DateTime(calTest); // => 2018-01-02T00:59:59.000+03:00 ?!
//	    LocalDate localDateFromJodaDateTime = jdtDateTime.toLocalDate(); // => 2018-01-02 ?!
//        System.out.println(localDateFromJodaDateTime);
//	                        
//	    org.joda.time.DateTime dateTimeFromCalManual = new org.joda.time.DateTime(calManuallySet); // => 2018-01-02T00:59:59.000+03:00 ?!
//	    LocalDate localDateFromDateTimeFromCalManual = dateTimeFromCalManual.toLocalDate(); // => 2018-01-02 ?!                 
//        System.out.println(localDateFromDateTimeFromCalManual);
	    
////        System.setProperty("org.joda.time.DateTimeZone.Provider", DefaultZoneInfoProvider.class.getName());
//	    
//	    DefaultZoneInfoProvider prov = new DefaultZoneInfoProvider();
//	    ClassLoader cl = TempTest.class.getClassLoader();
//        DateTimeZone normal = DateTimeZone.forID("Europe/Paris");
//        DateTimeZone special = prov.getZone("Europe/Paris");
//        if (normal.equals(special)) {
//            System.out.println("TRUE");
//        }
//        assertEquals(DateTimeZone.getAvailableIDs(), prov.getAvailableIDs());
//        for (String id : DateTimeZone.getAvailableIDs()) {
//            System.out.println(id);
//            if (!DateTimeZone.forID(id).equals(prov.getZone(id))) {
//                System.out.println("!!! " + id);
//            }
/////            assertEquals(DateTimeZone.forID(id), prov.getZone(id));
//        }
//        System.out.println(normal.getName(1111));
//        System.out.println(cl);
//        System.out.println(cl.getParent());
//        System.out.println(cl.getParent().getParent());
//        System.out.println(TimeZone.class.getClassLoader());
//	    
//	    System.out.println(new DateTime(606866400000L, DateTimeZone.UTC));
////        System.out.println(new DateTime(606880800000L, DateTimeZone.UTC));
//	    
//	    TimeZone.setDefault(TimeZone.getTimeZone("America/Caracas"));
	    
//	    LocalDateTime ldt = new LocalDateTime(-62135596800000L);
//	    System.out.println(ldt);
//	    System.out.println(ldt.getLocalMillis());
	    
	    
//	    System.out.println(Stream.of(Locale.getISOLanguages()).collect(Collectors.joining("\n")));
//	    Locale loc = new Locale("ar", "EG");
//	    TimeZone.setDefault(TimeZone.getTimeZone("GMT-0800"));
//	    Locale.setDefault(loc);
//	    for (Locale loc1 : Locale.getAvailableLocales()) {
//	        System.out.println(loc1.getDisplayName());
//	        System.out.println(loc1.getDisplayLanguage());
//	        System.out.println(loc1.getDisplayCountry());
//	        TimeZone timeZone = TimeZone.getTimeZone("GMT-0800");
//	        System.out.println(timeZone);
//	        DateTimeZone dateTimeZone = DateTimeZone.forTimeZone(timeZone);
//	        System.out.println(dateTimeZone);
//            System.out.println();
//        }
    }

}

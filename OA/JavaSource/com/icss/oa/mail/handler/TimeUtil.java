// Decompiled Using: FrontEnd Plus v2.03 and the JAD Engine
// Available From: http://www.reflections.ath.cx
// Decompiler options: packimports(3) 
// Source File Name:   TimeUtil.java

package com.icss.oa.mail.handler;

import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtil
{

    public TimeUtil()
    {
    }

    public static String getCurrentDate(String format)
    {
        return (new SimpleDateFormat(format)).format(new Date());
    }

    public static String getCurrentWeek()
    {
        Calendar cal = Calendar.getInstance();
        Date today = new Date();
        cal.setTime(today);
        switch(cal.get(7) - 1)
        {
        case 1: // '\001'
            return "\u661F\u671F\u4E00";

        case 2: // '\002'
            return "\u661F\u671F\u4E8C";

        case 3: // '\003'
            return "\u661F\u671F\u4E09";

        case 4: // '\004'
            return "\u661F\u671F\u56DB";

        case 5: // '\005'
            return "\u661F\u671F\u4E94";

        case 6: // '\006'
            return "\u661F\u671F\u516D";

        case 7: // '\007'
            return "\u661F\u671F\u65E5";
        }
        return "";
    }

    public static void main(String args[])
    {
        System.out.println(getCurrentDate("yyyy\u5E74MM\u6708dd\u65E5"));
        System.out.println(getCurrentWeek());
    }
}
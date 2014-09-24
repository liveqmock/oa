/**
 * 
 * @Project:      DPT
 * @Title:        DateUtils.java
 * @Package:      com.icss.fsop.cms.common.util
 * @Description:  ����ʱ�丨��������
 * @author:       lichao lichao@chinasofti.com
 * @date:         Jul 24, 2011 9:47:42 PM
 * @Copyright:    2011 www.chinasofti.com Inc. All rights reserved. 
 * @version:      V1.0
 */
package com.icss.oa.sync.ids;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Random;
import java.util.StringTokenizer;

/**
 * 
 * @ClassName: DateUtils
 * @Description: ����ʱ�丨��������
 * @author: lichao lichao@chinasofti.com
 * @date: Jul 24, 2011 9:47:42 PM
 * 
 */
public class DateUtils
{
    private static SimpleDateFormat dateFormat =
        new SimpleDateFormat("yyyy-MM-dd");
    
    private static SimpleDateFormat customerFormat =
        new SimpleDateFormat("yyyyMMdd");
    
    private static final String SIMPLE_DATE_FORMAT = "yyyy-MM-dd";
    
    private static final int SIMPLE_DATE_FORMAT_LENGTH =
        SIMPLE_DATE_FORMAT.length();
    
    private static final String SIMPLE_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    
    public static final String PATTERN_YYYYMMDD = "yyyy-MM-dd";
    
    public static final String PATTERN_YYYYMMDDHHMM = "yyyy-MM-dd HH:mm";
    
    public static final String PATTERN_YYYYMMDDHHMMSS = "yyyy-MM-dd HH:mm:ss";
    
    public static final String MONDAY = "����һ";
    
    public static final String TUESDAY = "���ڶ�";
    
    public static final String WEDNESDAY = "������";
    
    public static final String THURSDAY = "������";
    
    public static final String FRIDAY = "������";
    
    public static final String SATURDAY = "������";
    
    public static final String SUNDAY = "������";
    
    // ��õ�ǰ���ڵ��ַ�����ʽ.
    public static final String getCurrentStringDate(String pattern)
    {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        return formatter.format(currentTime);
    }
    
    public static final String getCurrentStringDateYMDHMS()
    {
        return getCurrentStringDate(PATTERN_YYYYMMDDHHMMSS);
    }
    
    public static final String getCurrentStringDateYMDHM()
    {
        return getCurrentStringDate(PATTERN_YYYYMMDDHHMM);
    }
    
    public static final String getCurrentStringDateYMD()
    {
        return getCurrentStringDate(PATTERN_YYYYMMDD);
    }
    
    public static final boolean isDate(String strDate, String pattern)
    {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        formatter.setLenient(false);
        ParsePosition pos = new ParsePosition(0);
        try
        {
            formatter.parse(strDate, pos);
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }
    
    public static String date2Str(Date date)
    {
        try
        {
            return dateFormat.format(date);
        }
        catch (Exception ex)
        {
            return "";
        }
    }
    
    public static String date2Str(Date date, String pattern)
    {
        try
        {
            customerFormat.applyPattern(pattern);
            return customerFormat.format(date);
        }
        catch (Exception ex)
        {
            return "";
        }
    }
    
    public static Date str2Date(String str)
    {
        try
        {
            return dateFormat.parse(str);
        }
        catch (Exception ex)
        {
            return null;
        }
    }
    
    public static Date str2Date(String str, String pattern)
    {
        try
        {
            SimpleDateFormat dateFormat1 = new SimpleDateFormat(pattern);
            return dateFormat1.parse(str);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
    }
    
    public static Date getLastMonthLastDate()
    {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        int max = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        calendar.set(Calendar.DAY_OF_MONTH, max);
        return calendar.getTime();
    }
    
    /**
     * �ַ���ת��Ϊ��ͨ������
     * 
     * @param str ���ڸ�ʽSimple_Date_Format��Simple_DateTime_Format
     * @return java.util.Date
     */
    public static java.util.Date strToSysDate(String str)
    {
        if (null != str && str.length() > 0)
        {
            try
            {
                if (str.length() <= SIMPLE_DATE_FORMAT_LENGTH)
                { // ֻ�������ڡ�
                    return (new SimpleDateFormat(SIMPLE_DATE_FORMAT))
                        .parse(str);
                }
                else
                { // ��������ʱ��
                    return (new SimpleDateFormat(SIMPLE_DATETIME_FORMAT))
                        .parse(str);
                }
            }
            catch (ParseException error)
            {
                return null;
            }
        }
        else
        {
            return null;
        }
        
    }
    
    /**
     * �ַ���ת��Ϊsql������
     * 
     * @param str String
     * @return java.sql.Date
     */
    public static java.sql.Date strToSqlDate(String str)
    {
        if (strToSysDate(str) == null || str.length() < 1)
        {
            return null;
        }
        else
        {
            return new java.sql.Date(strToSysDate(str).getTime());
        }
    }
    
    /**
     * sql������ת��Ϊ��ʱ����ַ���
     * 
     * @param dDate java.sql.Date
     * @return String "yyyy-MM-dd HH:mm:ss";
     */
    public static String toDateTimeStr(java.sql.Date dDate)
    {
        if (dDate == null)
        {
            return null;
        }
        else
        {
            return (new SimpleDateFormat(SIMPLE_DATETIME_FORMAT)).format(dDate);
        }
    }
    
    /**
     * ��ͨ������ת��Ϊ��ʱ����ַ���
     * 
     * @param dDate java.util.Date
     * @return String "yyyy-MM-dd HH:mm:ss";
     */
    public static String toDateTimeStr(java.util.Date dDate)
    {
        if (dDate == null)
        {
            return null;
        }
        else
        {
            return (new SimpleDateFormat(SIMPLE_DATETIME_FORMAT)).format(dDate);
        }
    }
    
    /**
     * sql������ת��Ϊ����ʱ����ַ���
     * 
     * @param d java.sql.Date
     * @return String "yyyy-MM-dd"
     */
    public static String toDateStr(java.sql.Date d)
    {
        if (d == null)
        {
            return null;
        }
        else
        {
            return (new SimpleDateFormat(SIMPLE_DATE_FORMAT)).format(d);
        }
    }
    
    /**
     * ��ͨ������ת��Ϊ����ʱ����ַ���
     * 
     * @param d java.util.Date
     * @return String String "yyyy-MM-dd"
     */
    public static String toDateStr(java.util.Date d)
    {
        if (d == null)
        {
            return null;
        }
        else
        {
            return (new SimpleDateFormat(SIMPLE_DATE_FORMAT)).format(d);
        }
    }
    
    /**
     * ��õ�ʱ��ʱ��
     * 
     * @return java.sql.Date
     */
    public static java.sql.Date getCurrentDate()
    {
        return new java.sql.Date(new java.util.Date().getTime());
    }
    
    /**
     * ��õ�ǰ�����ں�ʱ�䣨������
     * 
     * @return java.util.Date
     */
    public static java.util.Date getCurrentDateTime()
    {
        return Calendar.getInstance().getTime();
    }
    
    /**
     * ��util�͵������͵�����ת��Ϊsql�͵���������
     * 
     * @param date java.util.Date
     * @return java.sql.Date
     */
    public static java.sql.Date utilToSql(java.util.Date date)
    {
        return new java.sql.Date(date.getTime());
    }
    
    /**
     * ��sql�͵������͵�����ת��Ϊutil�͵���������
     * 
     * @param date java.sql.Date
     * @return java.util.Date
     */
    public static java.util.Date sqlToUtil(java.sql.Date date)
    {
        return new java.util.Date(date.getTime());
    }
    
    /**
     * �����ں�ʱ�临�ϣ���ϣ�������
     * 
     * @param date java.sql.Date
     * @param time java.sql.Time
     * @return java.sql.Date
     */
    public static java.sql.Date compositeDateTime(java.sql.Date date,
        java.sql.Time time)
    {
        if (null == date || null == time)
        {
            return null;
        }
        Calendar calDate = new GregorianCalendar();
        calDate.setTimeInMillis(date.getTime());
        Calendar calTime = new GregorianCalendar();
        calTime.setTimeInMillis(time.getTime());
        Calendar calCompositeDateTime = new GregorianCalendar();
        int iYear = calDate.get(Calendar.YEAR);
        int iMonth = calDate.get(Calendar.MONTH);
        int iDay = calDate.get(Calendar.DATE);
        int iHour = calTime.get(Calendar.HOUR_OF_DAY);
        int iMin = calTime.get(Calendar.MINUTE);
        int iSec = calTime.get(Calendar.SECOND);
        int iMSec = calTime.get(Calendar.MILLISECOND);
        calCompositeDateTime.set(iYear, iMonth, iDay, iHour, iMin, iSec);
        calCompositeDateTime.set(Calendar.MILLISECOND, iMSec);
        return utilToSql(calCompositeDateTime.getTime());
    }
    
    /**
     * �����ַ����͵�����������
     * 
     * @param strDate �����ڣ�
     * @return ����������ڶ���
     */
    public static java.util.Date parseDate(String strDate)
    {
        long r = 0;
        try
        {
            StringTokenizer token = new StringTokenizer(strDate, " ");
            String date = token.nextToken();
            Date now = java.sql.Date.valueOf(date);
            r = now.getTime();
            try
            {
                String time = token.nextToken();
                StringTokenizer tkTime = new StringTokenizer(time, ":");
                r += Integer.parseInt(tkTime.nextToken()) * 60 * 60 * 1000;
                r += Integer.parseInt(tkTime.nextToken()) * 60 * 1000;
                r += Integer.parseInt(tkTime.nextToken()) * 1000;
            }
            catch (Exception ex)
            {
                r = now.getTime();
            }
        }
        catch (Exception ex)
        {
            return new Date();
        }
        return new Date(r);
    }
    
    public static String fullTimeNoFormat()
    {
        return fullTimeNoFormat(new Date());
    }
    
    public static String fullTimeNoFormat(long date)
    {
        return fullTimeNoFormat(new Date(date));
    }
    
    public static String shortDateForChina(long date)
    {
        return shortDateForChina(new Date(date));
    }
    
    /**
     * �������ݸ�ʽ��
     * 
     * @param date
     * @return yyyy �� MM �� dd ��
     */
    public static String shortDateForChina(Date date)
    {
        String r = "";
        SimpleDateFormat formater = new SimpleDateFormat("yyyy �� MM �� dd ��");
        try
        {
            r = formater.format(date);
        }
        catch (Exception ex)
        {
            r = formater.format(new Date());
        }
        return r;
    }
    
    /**
     * ���������ݶ����ʽ��Ϊ�ַ�����
     * 
     * @param date java.util.Date
     * @return ��ʽ�����ڣ�yyyyMMddHHmmss��
     */
    public static String fullTimeNoFormat(java.util.Date date)
    {
        String r = "";
        SimpleDateFormat formater = new SimpleDateFormat("yyyyMMddHHmmss");
        try
        {
            r = formater.format(date);
        }
        catch (Exception ex)
        {
            r = formater.format(new Date());
        }
        return r;
    }
    
    /**
     * ���������ݶ����ʽ��Ϊ�̶��ַ�����ʽ��
     * 
     * @param date
     * @return ��ʽ�����ڣ�yyyy-MM-dd HH:mm:ss
     */
    public static String fullTime(java.util.Date date)
    {
        String r = "";
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try
        {
            r = formater.format(date);
        }
        catch (Exception ex)
        {
            r = formater.format(new Date());
        }
        return r;
    }
    
    /**
     * ����ǰ�������ݶ����ʽ��Ϊ�̶��ַ�����ʽ��
     * 
     * @return ��ʽ�����ڣ�yyyy-MM-dd HH:mm:ss
     */
    public static String fullTime()
    {
        return fullTime(new Date());
    }
    
    /**
     * ��long�������ݶ����ʽ��Ϊ�̶��ַ�����ʽ��
     * 
     * @param long
     * @return ��ʽ�����ڣ�yyyy-MM-dd HH:mm:ss
     */
    public static String fullTime(long date)
    {
        return fullTime(new Date(date));
    }
    
    /**
     * ���������ݶ����ʽ��Ϊ�̶��ַ�����ʽ��
     * 
     * @param date
     * @return ��ʽ�����ڣ�yyyy-MM-dd
     */
    public static String shortDate(Date date)
    {
        String r = "";
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
        try
        {
            r = formater.format(date);
        }
        catch (Exception ex)
        {
            r = formater.format(new Date());
        }
        return r;
    }
    
    /**
     * ����ǰ�������ݶ����ʽ��Ϊ�̶��ַ�����ʽ��
     * 
     * @return ��ʽ�����ڣ�yyyy-MM-dd
     */
    public static String shortDate()
    {
        return shortDate(new Date());
    }
    
    /**
     * ��long���ݶ����ʽ��Ϊ�̶��ַ�����ʽ��
     * 
     * @param long
     * @return ��ʽ�����ڣ�yyyy-MM-dd
     */
    public static String shortDate(long date)
    {
        return shortDate(new Date(date));
    }
    
    /**
     * ���������ݶ����ʽ��Ϊ�̶��ַ�����ʽ��
     * 
     * @param date
     * @return ��ʽ�����ڣ�HH:mm:ss
     */
    public static String shortTime(Date date)
    {
        String r = "";
        SimpleDateFormat formater = new SimpleDateFormat("HH:mm:ss");
        try
        {
            r = formater.format(date);
        }
        catch (Exception ex)
        {
            r = formater.format(new Date());
        }
        return r;
    }
    
    /**
     * ����ǰ�������ݶ����ʽ��Ϊ�̶��ַ�����ʽ��
     * 
     * @return ��ʽ�����ڣ�HH:mm:ss
     */
    public static String shortTime()
    {
        return shortTime(new Date());
    }
    
    /**
     * ��long�������ݶ����ʽ��Ϊ�̶��ַ�����ʽ��
     * 
     * @param long
     * @return ��ʽ�����ڣ�HH:mm:ss
     */
    public static String shortTime(long date)
    {
        return shortTime(new Date(date));
    }
    
    /**
     * ���ĳ���µĵ�һ��0ʱ0��0���ʱ��
     */
    public static java.util.Date getFirstDateOfMonth(int year, int month)
    {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        
        return cal.getTime();
    }
    
    /**
     * ��ȡĳ���µ�ĳһ���0ʱ0��0���ʱ��
     */
    public static java.util.Calendar getFirstTimeOfDay(int year, int month,
        int day)
    {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DAY_OF_MONTH, day);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        
        return cal;
    }
    
    /**
     * ��java.util.Date ת��Ϊjava.util.Calendar
     * 
     * @param date java.util.Date date
     * @return java.util.Calendar
     */
    public static java.util.Calendar dateToCalendar(java.util.Date date)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }
    
    /**
     * ��ȡ����ʱ��
     * 
     * @return ����ʱ������ yyyy-MM-dd HH:mm:ss
     */
    public static Date getNowDate()
    {
        Date currentTime = new Date();
        SimpleDateFormat formatter =
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        ParsePosition pos = new ParsePosition(8);
        Date currentTime_2 = formatter.parse(dateString, pos);
        return currentTime_2;
    }
    
    /**
     * ��ȡ����ʱ��
     * 
     * @return���ض�ʱ���ʽ yyyy-MM-dd
     */
    public static Date getNowDateShort()
    {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(currentTime);
        ParsePosition pos = new ParsePosition(8);
        Date currentTime_2 = formatter.parse(dateString, pos);
        return currentTime_2;
    }
    
    /**
     * ��ȡ����ʱ��
     * 
     * @return�����ַ�����ʽ yyyy-MM-dd HH:mm:ss
     */
    public static String getStringDate()
    {
        Date currentTime = new Date();
        SimpleDateFormat formatter =
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }
    
    /**
     * ��ȡ����ʱ��
     * 
     * @return ���ض�ʱ���ַ�����ʽyyyy-MM-dd
     */
    public static String getStringDateShort()
    {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(currentTime);
        return dateString;
    }
    
    public static String getStringDate(String format)
    {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        String dateString = formatter.format(currentTime);
        return dateString;
    }
    
    /**
     * ��ȡʱ�� Сʱ:��;�� HH:mm:ss
     */
    public static String getTimeShort()
    {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        Date currentTime = new Date();
        String dateString = formatter.format(currentTime);
        return dateString;
    }
    
    /**
     * ����ʱ���ʽ�ַ���ת��Ϊʱ�� yyyy-MM-dd HH:mm:ss
     */
    public static Date strToDateLong(String strDate)
    {
        SimpleDateFormat formatter =
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }
    
    /**
     * ����ʱ���ʽʱ��ת��Ϊ�ַ��� yyyy-MM-dd HH:mm:ss
     * 
     * @param dateDate
     * @return
     */
    public static String dateToStrLong(java.util.Date dateDate)
    {
        SimpleDateFormat formatter =
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(dateDate);
        return dateString;
    }
    
    /**
     * ����ʱ���ʽʱ��ת��Ϊ�ַ��� yyyy-MM-dd
     */
    public static String dateToStr(java.util.Date dateDate)
    {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(dateDate);
        return dateString;
    }
    
    /**
     * ����ʱ���ʽ�ַ���ת��Ϊʱ�� yyyy-MM-dd
     * 
     * @param strDate
     * @return
     */
    public static Date strToDate(String strDate)
    {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }
    
    /**
     * �õ�����ʱ��
     * 
     * @return
     */
    public static Date getNow()
    {
        Date currentTime = new Date();
        return currentTime;
    }
    
    /**
     * ��ȡһ�����е����һ��
     * 
     * @param day
     * @return
     */
    public static Date getLastDate(long day)
    {
        Date date = new Date();
        long date_3_hm = date.getTime() - 3600000 * 34 * day;
        Date date_3_hm_date = new Date(date_3_hm);
        return date_3_hm_date;
    }
    
    /**
     * �õ�����ʱ��
     * 
     * @return �ַ��� yyyyMMdd HHmmss
     */
    public static String getStringToday()
    {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd HHmmss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }
    
    /**
     * �õ�����Сʱ
     */
    public static String getHour()
    {
        Date currentTime = new Date();
        SimpleDateFormat formatter =
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        String hour;
        hour = dateString.substring(11, 13);
        return hour;
    }
    
    /**
     * �õ����ڷ���
     * 
     * @return
     */
    public static String getTime()
    {
        Date currentTime = new Date();
        SimpleDateFormat formatter =
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        String min;
        min = dateString.substring(14, 16);
        return min;
    }
    
    /**
     * �����û������ʱ���ʾ��ʽ�����ص�ǰʱ��ĸ�ʽ �����yyyyMMdd��ע����ĸy���ܴ�д��
     * 
     * @param sformat yyyyMMddhhmmss
     * @return
     */
    public static String getUserDate(String sformat)
    {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(sformat);
        String dateString = formatter.format(currentTime);
        return dateString;
    }
    
    /**
     * �����û������ʱ���ʾ��ʽ�����ص�ǰʱ��ĸ�ʽ �����yyyyMMdd��ע����ĸy���ܴ�д��
     * 
     * @param sformat yyyyMMddhhmmss
     * @return
     */
    public static String getUserDate(String strDate, String sformat)
    {
        
        Date currentTime = strToDateLong(strDate);
        SimpleDateFormat formatter = new SimpleDateFormat(sformat);
        String dateString = formatter.format(currentTime);
        return dateString;
    }
    
    /**
     * ����Сʱʱ���Ĳ�ֵ,���뱣֤����ʱ�䶼��"HH:MM"�ĸ�ʽ�������ַ��͵ķ���
     */
    public static String getTwoHour(String st1, String st2)
    {
        String[] kk;
        String[] jj;
        kk = st1.split(":");
        jj = st2.split(":");
        if (Integer.parseInt(kk[0]) < Integer.parseInt(jj[0]))
        {
            return "0";
        }
        else
        {
            double y =
                Double.parseDouble(kk[0]) + Double.parseDouble(kk[1]) / 60;
            double u =
                Double.parseDouble(jj[0]) + Double.parseDouble(jj[1]) / 60;
            if ((y - u) > 0)
            {
                return y - u + "";
            }
            else
            {
                return "0";
            }
            
        }
    }
    
    /**
     * �õ��������ڼ�ļ������
     */
    public static String getTwoDay(String sj1, String sj2)
    {
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
        long day = 0;
        try
        {
            java.util.Date date = myFormatter.parse(sj1);
            java.util.Date mydate = myFormatter.parse(sj2);
            day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
        }
        catch (Exception e)
        {
            return "";
        }
        return day + "";
    }
    
    /**
     * ʱ��ǰ�ƻ���Ʒ���,����JJ��ʾ����.
     */
    public static String getPreTime(String sj1, String jj)
    {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String mydate1 = "";
        try
        {
            Date date1 = format.parse(sj1);
            long time = (date1.getTime() / 1000) + Long.parseLong(jj) * 60;
            date1.setTime(time * 1000);
            mydate1 = format.format(date1);
        }
        catch (Exception e)
        {
            return "";
        }
        return mydate1;
    }
    
    /**
     * �õ�һ��ʱ���Ӻ��ǰ�Ƽ����ʱ��,nowdateΪʱ��,delayΪǰ�ƻ���ӵ�����
     */
    public static String getNextDay(String nowdate, String delay)
    {
        String mdate = "";
        try
        {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date d = strToDate(nowdate);
            long myTime =
                (d.getTime() / 1000) + Long.parseLong(delay) * 24 * 60 * 60;
            d.setTime(myTime * 1000);
            mdate = format.format(d);
            return mdate;
        }
        catch (Exception e)
        {
            return mdate;
        }
    }
    
    /**
     * �õ�һ��ʱ���Ӻ��ǰ�Ƽ����ʱ��,nowdateΪʱ��,delayΪǰ�ƻ���ӵ�����
     */
    public static String getNextDay(String nowdate, String pattern, String delay)
    {
        String mdate = "";
        try
        {
            SimpleDateFormat format = new SimpleDateFormat(pattern);
            Date d = str2Date(nowdate, pattern);
            long myTime =
                (d.getTime() / 1000) + Long.parseLong(delay) * 24 * 60 * 60;
            d.setTime(myTime * 1000);
            mdate = format.format(d);
            return mdate;
        }
        catch (Exception e)
        {
            return mdate;
        }
    }
    
    /**
     * �ж��Ƿ�����
     * 
     * @param ddate
     * @return
     */
    public static boolean isLeapYear(String ddate)
    {
        /*
         * 1.��400���������꣬
         * 
         * ���� 2.���ܱ�4������������ 3.�ܱ�4����ͬʱ���ܱ�100������������
         * 
         * 4.�ܱ�4����ͬʱ�ܱ�100������������
         */
        Date d = strToDate(ddate);
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(d);
        int year = gc.get(Calendar.YEAR);
        if ((year % 400) == 0)
        {
            return true;
        }
        else if ((year % 4) == 0)
        {
            if ((year % 100) == 0)
            {
                return false;
            }
            else
            {
                return true;
            }
        }
        else
        {
            return false;
        }
    }
    
    /**
     * ��������ʱ���ʽ 26 Apr 2006
     * 
     * @param str
     * @return
     */
    public static String getEDate(String str)
    {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(str, pos);
        String j = strtodate.toString();
        String[] k = j.split(" ");
        return k[2] + k[1].toUpperCase() + k[5].substring(2, 4);
    }
    
    /**
     * ��ȡһ���µ����һ��
     * 
     * @param dat
     * @return
     */
    public static String getEndDateOfMonth(String dat)
    {
        String str = dat.substring(0, 8);
        String month = dat.substring(5, 7);
        int mon = Integer.parseInt(month);
        if (mon == 1 || mon == 3 || mon == 5 || mon == 7 || mon == 8
            || mon == 10 || mon == 12)
        {
            str += "31";
        }
        else if (mon == 4 || mon == 6 || mon == 9 || mon == 11)
        {
            str += "30";
        }
        else
        {
            if (isLeapYear(dat))
            {
                str += "29";
            }
            else
            {
                str += "28";
            }
        }
        return str;
    }
    
    /**
     * �ж϶���ʱ���Ƿ���ͬһ����
     * 
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isSameWeekDates(Date date1, Date date2)
    {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);
        int subYear = cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR);
        if (0 == subYear)
        {
            if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2
                .get(Calendar.WEEK_OF_YEAR))
            {
                return true;
            }
        }
        else if (1 == subYear && 11 == cal2.get(Calendar.MONTH))
        {
            // ���12�µ����һ�ܺ�������һ�ܵĻ������һ�ܼ���������ĵ�һ��
            if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2
                .get(Calendar.WEEK_OF_YEAR))
            {
                return true;
            }
        }
        else if (-1 == subYear && 11 == cal1.get(Calendar.MONTH))
        {
            if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2
                .get(Calendar.WEEK_OF_YEAR))
            {
                return true;
            }
        }
        return false;
    }
    
    /**
     * ����������,���õ���ǰʱ�����ڵ�����ǵڼ���
     * 
     * @return
     */
    public static String getSeqWeek()
    {
        Calendar c = Calendar.getInstance(Locale.CHINA);
        String week = Integer.toString(c.get(Calendar.WEEK_OF_YEAR));
        if (week.length() == 1)
        {
            week = "0" + week;
        }
        String year = Integer.toString(c.get(Calendar.YEAR));
        return year + week;
    }
    
    /**
     * ���һ���������ڵ��ܵ����ڼ������ڣ���Ҫ�ҳ�2002��2��3�������ܵ�����һ�Ǽ���
     * 
     * @param sdate
     * @param num
     * @return
     */
    public static String getWeek(String sdate, String num)
    {
        // ��ת��Ϊʱ��
        Date dd = strToDate(sdate);
        Calendar c = Calendar.getInstance();
        c.setTime(dd);
        // ��������һ���ڵ�����
        if (num.equals("1"))
        {
            c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        }
        // �������ڶ����ڵ�����
        else if (num.equals("2"))
        {
            c.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
        }
        // �������������ڵ�����
        else if (num.equals("3"))
        {
            c.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
        }
        // �������������ڵ�����
        else if (num.equals("4"))
        {
            c.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
        }
        // �������������ڵ�����
        else if (num.equals("5"))
        {
            c.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
        }
        // �������������ڵ�����
        else if (num.equals("6"))
        {
            c.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
        }
        // �������������ڵ�����
        else if (num.equals("0"))
        {
            c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        }
        return new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
    }
    
    /**
     * ����һ�����ڣ����������ڼ����ַ���
     * 
     * @param sdate
     * @return
     */
    public static String getWeek(String sdate)
    {
        // ��ת��Ϊʱ��
        Date date = strToDate(sdate);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        // int hour=c.get(Calendar.DAY_OF_WEEK);
        // hour�д�ľ������ڼ��ˣ��䷶Χ 1~7
        // 1=������ 7=����������������
        return new SimpleDateFormat("EEEE").format(c.getTime());
    }
    
    public static String getWeekStr(String sdate)
    {
        String str = "";
        str = getWeek(sdate);
        if ("1".equals(str))
        {
            str = "������";
        }
        else if ("2".equals(str))
        {
            str = "����һ";
        }
        else if ("3".equals(str))
        {
            str = "���ڶ�";
        }
        else if ("4".equals(str))
        {
            str = "������";
        }
        else if ("5".equals(str))
        {
            str = "������";
        }
        else if ("6".equals(str))
        {
            str = "������";
        }
        else if ("7".equals(str))
        {
            str = "������";
        }
        return str;
    }
    
    /**
     * ����ʱ��֮�������
     * 
     * @param date1
     * @param date2
     * @return
     */
    public static long getDays(String date1, String date2)
    {
        if (date1 == null || date1.equals(""))
        {
            return 0;
        }
        
        if (date2 == null || date2.equals(""))
        {
            return 0;
        }
        
        // ת��Ϊ��׼ʱ��
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = null;
        java.util.Date mydate = null;
        long day = 0L;
        try
        {
            date = myFormatter.parse(date1);
            mydate = myFormatter.parse(date2);
            day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
        }
        catch (Exception e)
        {
            return day;
        }
        return day;
    }
    
    /**
     * �γ����µ����� ��
     * 
     * ���ݴ����һ��ʱ�䷵��һ���ṹ ������ ����һ ���ڶ� ������ ������ ������ ������
     * 
     * �����ǵ��µĸ���ʱ�� �˺������ظ�������һ�����������ڵ�����
     * 
     * @param sdate
     * @return
     */
    public static String getNowMonth(String sdate)
    {
        // ȡ��ʱ�������µ�һ��
        sdate = sdate.substring(0, 8) + "01";
        // �õ�����µ�1�������ڼ�
        Date date = strToDate(sdate);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int u = c.get(Calendar.DAY_OF_WEEK);
        String newday = getNextDay(sdate, (1 - u) + "");
        return newday;
    }
    
    /**
     * ȡ�����ݿ����� ���ɸ�ʽΪyyyymmddhhmmss+kλ�����
     * 
     * @param k ��ʾ��ȡ��λ������������Լ���
     */
    
    public static String getNo(int k)
    {
        return getUserDate("yyyyMMddhhmmss") + getRandom(k);
    }
    
    /**
     * ����һ�������
     * 
     * @param i
     * @return
     */
    public static String getRandom(int i)
    {
        Random jjj = new Random();
        if (i == 0)
        {
            return "";
        }
        StringBuffer jj = new StringBuffer();
        jj.append("");
        for (int k = 0; k < i; k++)
        {
            jj.append(jjj.nextInt(9));
        }
        return jj.toString();
    }
    
    public static boolean rightDate(String date)
    {
        SimpleDateFormat sdf = null;
        if (date == null)
        {
            return false;
        }
        if (date.length() > 10)
        {
            sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        }
        else
        {
            sdf = new SimpleDateFormat("yyyy-MM-dd");
        }
        try
        {
            sdf.parse(date);
        }
        catch (ParseException pe)
        {
            return false;
        }
        return true;
    }
    
    /**
     * ���ָ�����ڵ�ǰһ��
     * 
     * @param specifiedDay
     * @param patten
     * @param num
     * @return
     * @throws Exception
     */
    public static String getSpecifiedDayBefore(String specifiedDay,
        String patten, int num)
    {
        Calendar c = Calendar.getInstance();
        Date date = null;
        try
        {
            date = new SimpleDateFormat(patten).parse(specifiedDay);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day + num);
        
        String dayBefore = new SimpleDateFormat(patten).format(c.getTime());
        return dayBefore;
    }
    
    /**
     * ���ָ�����ڵĺ�һ��
     * 
     * @param specifiedDay
     * @param patten
     * @param num
     * @return
     */
    public static String getSpecifiedDayAfter(String specifiedDay,
        String patten, int num)
    {
        Calendar c = Calendar.getInstance();
        Date date = null;
        try
        {
            date = new SimpleDateFormat(patten).parse(specifiedDay);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day + num);
        
        String dayAfter = new SimpleDateFormat(patten).format(c.getTime());
        return dayAfter;
    }
    
    public static void main(String[] args)
    {
    }
}

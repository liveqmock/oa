package com.icss.regulation.handler ;

import java.util.regex.Pattern;

public class Html2Text{
public static String Html2Text(String inputString){
     String htmlStr = inputString; //��html��ǩ���ַ���
     String textStr ="";
     java.util.regex.Pattern p_script;
     java.util.regex.Matcher m_script;
     java.util.regex.Pattern p_style;
     java.util.regex.Matcher m_style;
     java.util.regex.Pattern p_html;
     java.util.regex.Matcher m_html;

    try{
          String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; //����script���������ʽ{��<script[^>]*?>[\\s\\S]*?<\\/script> }
          String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; //����style���������ʽ{��<style[^>]*?>[\\s\\S]*?<\\/style> }
          String regEx_html = "<[^>]+>"; //����HTML��ǩ���������ʽ

          p_script = Pattern.compile(regEx_script,Pattern.CASE_INSENSITIVE);
          m_script = p_script.matcher(htmlStr);
          htmlStr = m_script.replaceAll(""); //����script��ǩ

          p_style = Pattern.compile(regEx_style,Pattern.CASE_INSENSITIVE);
          m_style = p_style.matcher(htmlStr);
          htmlStr = m_style.replaceAll(""); //����style��ǩ

          p_html = Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE);
          m_html = p_html.matcher(htmlStr);
          htmlStr = m_html.replaceAll(""); //����html��ǩ

          textStr = htmlStr;
     }catch(Exception e){
          e.printStackTrace();
     }
     return textStr;//�����ı��ַ���
 }   
}
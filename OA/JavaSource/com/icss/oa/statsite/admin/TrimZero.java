package com.icss.oa.statsite.admin;

public class TrimZero{
	
private String str=null;	
private String str1=null;
public TrimZero(){
	}

public String getStr(){
	return this.str1;}

public void setStr(String str){
	this.str = str;
	this.trimzero();
	}

public void trimzero(){
	
	//System.out.println("str99999999999999999    " +this.str);
	boolean flag =false;
	int num = this.str.lastIndexOf("0");
	if(this.str.charAt(0)!='0'){num=0;flag =true;}
	//System.out.println("num888888888888888888    " +num);
	if(num!=-1&&num!=2){
		this.str1 = this.str.substring(num+1);
	}
	if(num==2){
		this.str1="0";}
	if(num==0&&flag){
		this.str1 = this.str.substring(num);}
	if(this.str.charAt(0)=='0'&&this.str.charAt(2)=='0'&&this.str.charAt(1)!='0'){this.str1 = this.str.substring(1);}
	
	//System.out.println("str888888888888888888    " +this.str1);
}


}
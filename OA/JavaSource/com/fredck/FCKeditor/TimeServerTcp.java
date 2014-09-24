package com.fredck.FCKeditor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
public class TimeServerTcp{
    public static void main(String[] args){
        
        ServerSocket ss=null;
        Socket socket=null;
        PrintWriter pw=null;

        try{
        
            ss=new ServerSocket(2122);
            while(true){
            	socket=ss.accept();
//            	InputStreamReader isr = new InputStreamReader(System.in);
//            	BufferedReader br= new BufferedReader(isr);
//            	String input = br.readLine();
//            	if("exit".equals(input)){
//            		break;
//            	}
            	IOObject ob = new IOObject();
            	OutputStream os=socket.getOutputStream();
            	ObjectOutputStream obj = new ObjectOutputStream(os);
            	obj.writeObject(new IOObject());
            	// pw=new PrintWriter(os,true);//zidong flush
            	//Date date=new Date();    
            	//pw.println(date.toString());
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
            if(pw!=null)pw.close();
            if(ss!=null)ss.close();
            if(socket!=null)socket.close();
            }catch(Exception e){e.printStackTrace();}
            
        }
        
    }
}


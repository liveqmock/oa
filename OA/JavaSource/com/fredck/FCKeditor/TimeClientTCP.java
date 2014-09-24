package com.fredck.FCKeditor;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;

public class TimeClientTCP{
    public static void main(String[] args){
        Socket socket=null;
        BufferedReader br=null;

        try{
            socket=new Socket("localhost",2122);
            InputStream is=socket.getInputStream();
            System.out.println(is.available());
            
            byte[] b=new byte[10000];
            for(int i=is.read(b);i!=-1;){
            	System.out.println(i);
            }
            //System.out.println();
           // OutputStream os=socket.getOutputStream();
            ObjectInputStream ob=new ObjectInputStream(is);
            IOObject ioo=(IOObject)ob.readObject();
            //InputStreamReader isr=new InputStreamReader(is);            
            //br=new BufferedReader(isr);
            //String data=br.readLine();
            FileOutputStream sys=new FileOutputStream("o.txt");
            FileReader fis = new FileReader("o.txt");
            BufferedReader br1=new BufferedReader(fis);
            
            char c[]= new char[100];
            int i=br1.read(c);
            System.out.println("c"+c);
            PrintStream ps= new PrintStream(sys);
            System.setOut(ps);
            System.out.println(br1.readLine()+ioo.toString());
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
            if(br!=null)br.close();
            if(socket!=null)socket.close();
            }catch(Exception e){e.printStackTrace();}
        }
    }


}


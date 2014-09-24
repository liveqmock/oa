package com.icss.oa.sms.util;

import java.util.Vector;

public class SMSSend {
	
	public void toSend(String phonenums,String text,String id) throws Exception {

		String phone[]=phonenums.split(",");
		Vector<String> message = new Vector<String>();
		int length=text.length();
		if(length<70){
			//字数少于70的时候不切分
			for(int i=0;i<phone.length;i++){
			XmlFile xml=new XmlFile();
			xml.toWrite(phone[i],text,id);
			xml.toSave();
			xml.toSend();
			xml.toDelete();
		}			
		}
		else{
			//字数大于70
			int intTS=0;
			if (length%65==0){
				intTS=length/65;				
			}				
			else{
				intTS=length/65+1;
			}
			for(int i=1;i<intTS;i++){
				String m = text.substring((i-1)*65,i*65)+"["+i+"/"+intTS+"]";
				message.add(m);
			}
			String m = text.substring((intTS-1)*65)+"["+intTS+"/"+intTS+"]";
			message.add(m);
			
			for (int j=message.size()-1;j>-1;j--) {
	        	String content =(String) message.elementAt(j);
	            for(int i=0;i<phone.length;i++){
	    			XmlFile xml=new XmlFile();
	    			xml.toWrite(phone[i],content,id);
	    			xml.toSave();
	    			xml.toSend();
	    			xml.toDelete();
	    		}
	        }
				
		}
        
	}

}



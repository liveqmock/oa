package com.icss.oa.sync.ids;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.ibm.mq.MQC;
import com.ibm.mq.MQEnvironment;
import com.ibm.mq.MQException;
import com.ibm.mq.MQGetMessageOptions;
import com.ibm.mq.MQMessage;
import com.ibm.mq.MQQueue;
import com.ibm.mq.MQQueueManager;
public class MQReceiver implements Runnable {
	   
	//private static final int MQ_CCSID = 819;
	public void run(){ 
		MQQueueManager mqQueueManager = null;
		MQQueue mqQueue = null;
		try{
			String MqServiceFlag = readValue("MqService");
			if(MqServiceFlag==null){
				MqServiceFlag = "";
			} 
			if(MqServiceFlag.equals("ON")){
				String MQ_MANAGER = readValue("Mq_Manager");    
				String MQ_HOST_NAME = readValue("Mq_Host_Name");
				String MQ_CHANNEL = readValue("Mq_Channel");   
				String MQ_QUEUE_NAME = readValue("Mq_Queue_Name"); 
				int MQ_PROT = new Integer(readValue("Mq_Port")).intValue();
				
				MQEnvironment.addConnectionPoolToken();
				MQEnvironment.hostname = MQ_HOST_NAME;
				MQEnvironment.channel = MQ_CHANNEL;
				MQEnvironment.port =MQ_PROT;
				MQEnvironment.properties.put(MQC.TRANSPORT_PROPERTY,MQC.TRANSPORT_MQSERIES);
				//MQEnvironment.CCSID = MQ_CCSID;
				mqQueueManager = new MQQueueManager(MQ_MANAGER);
				int receiveOptions = MQC.MQOO_INPUT_SHARED| MQC.MQOO_FAIL_IF_QUIESCING;
				mqQueue = mqQueueManager.accessQueue(MQ_QUEUE_NAME,receiveOptions, null, null, null);
				MQGetMessageOptions mqGetMessageOptions = new MQGetMessageOptions();
				mqGetMessageOptions.options = mqGetMessageOptions.options+ MQC.MQGMO_SYNCPOINT + MQC.MQGMO_WAIT+ MQC.MQGMO_FAIL_IF_QUIESCING; 
				mqGetMessageOptions.waitInterval = MQC.MQWI_UNLIMITED;
				// mqGetMessageOptions.waitInterval = 1000 * 10;
				while(true){
						MQMessage mqMessage = new MQMessage();
						mqQueue.get(mqMessage, mqGetMessageOptions);
						int index = mqMessage.getMessageLength();
						byte[] buffer = new byte[index];
						//mqMessage.readFully(buffer, 0, index);
						mqMessage.readFully(buffer);
						
						String message = new String(buffer,"UTF-8");
						
						message = message.substring(message.indexOf("{"),message.length());
						
						 
						System.out.println("-----------从IBM MQ获得消息22222222："+message);
						syncHandler synchandler = new syncHandler();
				   	    synchandler.syncData(message);
						
						mqQueueManager.commit(); 
				}
			    
			}
		}catch(MQException e){
			e.printStackTrace();
		}catch(Exception e1){
			e1.printStackTrace();
		}finally{
			if(mqQueue!=null){
				try{ 
					mqQueue.close();
				}catch(MQException e){
					e.printStackTrace();
				}
			}
			if(mqQueueManager != null){
				try{
					mqQueueManager.close();
				}catch(MQException e){
					e.printStackTrace();
				}
			}
		}
	}
	
	
	public static void main(String arg[]){
		MQReceiver client = new MQReceiver();
		Thread mqClientThread = new Thread(client);
		mqClientThread.start();
	}
	
	
	private String readValue(String key) {
		Properties props = new Properties();
		InputStream in = null;
		try {
			in = this.getClass().getResourceAsStream("/Mq.properties");
			props.load(in);

			String value = props.getProperty(key);
			return value;
		} catch (Exception e) {
			System.out.println("############## 取得 Mq.properties 出错");
			e.printStackTrace();
			return null;
		}finally{
				try {
					if(in!=null)
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
	
}


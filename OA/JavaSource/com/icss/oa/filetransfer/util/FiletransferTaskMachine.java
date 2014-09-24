package com.icss.oa.filetransfer.util;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

public class FiletransferTaskMachine {
	
	List tasklist = new ArrayList();

	private static FiletransferTaskMachine machine = null; 
	
	public static FiletransferTaskMachine getInstance(){
		if(machine == null){
			machine = new FiletransferTaskMachine();
			return machine;
		}else{
			return machine;
		}
	}
	
	public ReadRecordBackup createTask(){
		return new ReadRecordBackup();
	}
	
	public static void main(String[] args) {
		// TODO 自动生成方法存根

	}

}

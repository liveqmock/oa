/*
 * �������� 2005-9-12
 *
 * �����������ļ�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
package test.MailPath;

import java.sql.Connection;
import java.util.ArrayList;

import org.redflaglinux.user.usermanage;

/**
 * @author Sunchuanting
 *
 * ��������������ע�͵�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
public class Mailpath {

	public static void main(String[] args) {
		
		Connection conn = getConnetion.getDBConn();
		usermanage user1 = getConnetion.getMailConn();
		
		Mailhandler handler = new Mailhandler(conn);
		
		String [] mail_path = getConnetion.getAllMailList();
		
		System.out.println("mail_path.size() ="+mail_path.length);
		
		for (int i = 0; i < mail_path.length; i++) {
		    
//		    if(i==10){
//		        break;
//		    }
		    System.out.println("i ="+i);
			String user = mail_path[i];
			String path = null;
			System.out.println("user ="+user);
			boolean flag = handler.isExitMailPaht(user);
			
			if(!flag){
			    System.out.println("no this userid!!");
				path = getConnetion.getPath_user(user);
				handler.InsertMailPath(user,path);
			}
		}
		
		for (int i = 0; i < mail_path.length; i++) {
		    String user = mail_path[i];
//		    handler.DelMailPath(user);
//		    System.out.println("user !="+user);
//		    handler.UpdateMailPath(user,getConnetion.getPath_user(user));
            
        }
		
		System.out.println("over !");
		
		getConnetion.CloseConnection();
		getConnetion.CloseMailConnection();
		
	}
}

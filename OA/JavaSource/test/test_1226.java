/*
 * �������� 2005-12-26
 *
 * �����������ļ�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
package test;

import org.redflaglinux.user.usermanage;

/**
 * @author Administrator
 *
 * ��������������ע�͵�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
public class test_1226 {

	//	����һ��mail������������
	private static usermanage user = null;

	public static void main(String[] args) {

		//		��ʼ������,����ȫ�����ʼ��û�ID

		user = new usermanage();

		try {
			user.connect(
				"192.9.100.26",
				389,
				"cn=root,o=redflag.com,c=CH",
				"simple",
				"redflag.com");

			//					1��ȡ��ȫ����mail�û��ķ���������
			String Array_user[] = user.ListAlluserByUnit("xinhua.com", "");
			String path ;

			long start_time = System.currentTimeMillis();
			System.out.println("---start time " + start_time);

			for (int i = 0; i < Array_user.length; i++) {
				if(i==100){ 
					//break;
				}
				path = user.searchvalue("cn="+Array_user[i]+",ou=xinhua.com,o=redflag.com,c=CH",Array_user[i],"mailboxpath");
				System.out.println("path ============================="+path);
			}

			long end_time = System.currentTimeMillis();

			System.out.println("---end time " + end_time);
			System.out.println("---totel time " + (end_time - start_time));
			System.out.println("---Array_user.length=========== " + (Array_user.length));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}

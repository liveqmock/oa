package com.icss.oa.hr.admin;
import java.io.IOException;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.GetMethod;

/**
 *
 * @author domando
 */
public class test {

    private static String LOGON_SITE = "10.102.1.41";

    /**
     * ���Ӷ˿�
     */
    private static int LOGON_PORT = 81;

    private static HttpClient client = null;

    public static void main (String args[]) {
        //�½�һ�����Ӷ���
        client = new HttpClient();
        //���ӵ�news.163.com
        client.getHostConfiguration().setHost(LOGON_SITE, LOGON_PORT, "http");
        //����CookiePolicy�����ﲻ��Ҫ
        client.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);
        //����������ŵ���ҳ
        GetMethod authGet = new GetMethod("/hrweb/hr_index.aspx");
        //�������ӣ������жϷ���ֵ�Ƿ�ɹ�
        //���巵��ֵ�������˼�������ĵ��в鵽
        try {
			if (client.executeMethod(authGet) == 200) {
//            System.out.println(authGet.getResponseBodyAsString() ) ;
			   System.out.println(authGet.getResponseBodyAsString());
			}
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
//�����ʹ�ӡ����163����������ҳ��ȫ��html���롣
//�������Ϳ��Ը�����Ҫ��htmlparser����ҳ��ȡֵ�ˡ�
//    public static void getNews(String inputHtml) throws ParserException {
//        //���������Ĵ����������ת��
//        Parser parserMbMsg = Parser.createParser(inputHtml, "gb3212");
//        //�����г�������ȡ����
//        NodeFilter ahrefFilter = new TagNameFilter("a");
//        //ת����list����
//        NodeList nodeListMbMsg = parserMbMsg.extractAllNodesThatMatch(ahrefFilter);
//       
//        for (int i = 0;i<nodeListMbMsg.size() ; i++) {
//            System.out.println("-----------------------------"+i);
//            System.out.println(nodeListMbMsg.elementAt( i));//��ӡ��ÿһ��Ԫ��
//            //��ӡ�����г����ӵ�����
//            LinkTag link = (LinkTag) nodeListMbMsg.elementAt(i);
//            System.out.println(link.getLinkText() );
//            //System.out.println(link.getLink() );//��ӡ�����е����ӵ�ַ
//        }
    
}
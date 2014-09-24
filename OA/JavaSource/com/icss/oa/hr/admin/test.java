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
     * 连接端口
     */
    private static int LOGON_PORT = 81;

    private static HttpClient client = null;

    public static void main (String args[]) {
        //新建一个连接对象
        client = new HttpClient();
        //连接到news.163.com
        client.getHostConfiguration().setHost(LOGON_SITE, LOGON_PORT, "http");
        //设置CookiePolicy在这里不需要
        client.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);
        //进入国际新闻的网页
        GetMethod authGet = new GetMethod("/hrweb/hr_index.aspx");
        //进行连接，并且判断返回值是否成功
        //具体返回值代表的意思可以在文档中查到
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
//这样就打印出了163国际新闻网页的全部html代码。
//接下来就可以根据需要用htmlparser进行页面取值了。
//    public static void getNews(String inputHtml) throws ParserException {
//        //将传进来的代码进行类型转换
//        Parser parserMbMsg = Parser.createParser(inputHtml, "gb3212");
//        //将所有超链接提取出来
//        NodeFilter ahrefFilter = new TagNameFilter("a");
//        //转换成list类型
//        NodeList nodeListMbMsg = parserMbMsg.extractAllNodesThatMatch(ahrefFilter);
//       
//        for (int i = 0;i<nodeListMbMsg.size() ; i++) {
//            System.out.println("-----------------------------"+i);
//            System.out.println(nodeListMbMsg.elementAt( i));//打印出每一个元素
//            //打印出所有超链接的内容
//            LinkTag link = (LinkTag) nodeListMbMsg.elementAt(i);
//            System.out.println(link.getLinkText() );
//            //System.out.println(link.getLink() );//打印出所有的链接地址
//        }
    
}
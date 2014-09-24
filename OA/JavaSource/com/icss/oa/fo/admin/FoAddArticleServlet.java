/*
 * Created on 2004-5-14
 */
package com.icss.oa.fo.admin;



import java.io.IOException;


import java.sql.Connection;



import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;

import com.icss.oa.util.CurrentUser;
import com.icss.oa.fo.handler.FoLiuHandler;
import com.icss.oa.fo.vo.FoArticalVO;


public class FoAddArticleServlet extends ServletBase {

    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	System.out.println("����FoAddArticleServlet*********");
    	String planPlanid=request.getParameter("planPlanid");//ȡ�ô��Ӹ�������ļƻ�ID
    	System.out.println("planPlanid="+planPlanid);
    	Integer i_planPlanid=planPlanid==null?new Integer(-1):Integer.valueOf(planPlanid);
    	
    	Connection conn = null;
 
    	//ȡ����FoAddArticle.jspҳ������ӵĸø������Ϣ
        String artTitle = request.getParameter("artTitle");
        String artContent = request.getParameter("artContent");
        String artWriter = request.getParameter("artWriter");
        String artUploadusr = request.getParameter("artUploadusr");
        String artUploadtime = request.getParameter("artUploadtime");
        Integer artVotenum = ("").equals(request.getParameter("artVotenum"))?new Integer(-1):Integer.valueOf(request.getParameter("artVotenum"));
        String artVotescore = request.getParameter("artVotescore");
        String artPlace = request.getParameter("artPlace");
        String artTotalnum = request.getParameter("artTotalnum");
        String artTypeid = request.getParameter("artTypeid");
        String artTypename = request.getParameter("artTypename");
        String artOutvotenum = request.getParameter("artOutvotenum");
        String artInvotenum = request.getParameter("artInvotenum");
        String artWordnum = request.getParameter("artWordnum");
        String artPubtime = request.getParameter("artPubtime");
        String artIstoolong = request.getParameter("artIstoolong");
        String artUsenum = request.getParameter("artUsenum");
        String artStatnum = request.getParameter("artStatnum");
        String artMemo = request.getParameter("artMemo");
        String artIsentry = request.getParameter("artIsentry");
        String artIsconsult = request.getParameter("artIsconsult");
        
        System.out.println("�����Ѿ�ȫ��ȡ��");
        System.out.println("artTitle="+artTitle);
        System.out.println("artUploadusr="+artUploadusr);
        System.out.println("artVotenum="+artVotenum.toString());
        
        
        
        
        try {
        	
        	 //��ʼ���õ�������
        	
        	conn = this.getConnection(Globals.DATASOURCEJNDI);
            FoLiuHandler handler=new FoLiuHandler(conn);
            
            
            //д����Ϣ��
            Long CurrentTime = new Long(System.currentTimeMillis());
             
            FoArticalVO artvo=new FoArticalVO();//��ʼ��һ��FoArticalVO,��������ø����Ϣ
            //ΪVO��ֵ
            artvo.setPlanPlanid(i_planPlanid);
            artvo.setArtTitle(artTitle);
            artvo.setArtContent(artContent);
            artvo.setArtWriter(artWriter);
            artvo.setArtUploadusr(artUploadusr);
            artvo.setArtUploadtime(artUploadtime);
            artvo.setArtVotenum(artVotenum);
            artvo.setArtVotescore(artVotescore);
            artvo.setArtPlace(artPlace);
            artvo.setArtTotalnum(artTotalnum);
            artvo.setArtTypeid(artTypeid);
            artvo.setArtTypename(artTypename);
            artvo.setArtOutvotenum(artOutvotenum);
            artvo.setArtInvotenum(artInvotenum);
            artvo.setArtWordnum(artWordnum);
            artvo.setArtPubtime(artPubtime);
            artvo.setArtIstoolong(artIstoolong);
            artvo.setArtUsenum(artUsenum);
            artvo.setArtStatnum(artStatnum);
            artvo.setArtMemo(artMemo);
            artvo.setArtIsentry(artIsentry);
            artvo.setArtIsconsult(artIsconsult);
            
            handler.addArticle(artvo);//��Ӹ��
            
               
            
            this.forward(request, response, "/servlet/FoPlanArticalListServlet?planPlanid="+planPlanid);
        } catch (Exception e) {
            e.printStackTrace();
            
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        }

    }
}

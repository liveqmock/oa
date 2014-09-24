/*
 * Created on 2004-5-14
 */
package com.icss.oa.fo.admin;



import java.io.IOException;


import java.sql.Connection;



import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;


import com.icss.oa.fo.handler.FoLiuHandler;
import com.icss.oa.fo.vo.FoArticalVO;


public class FoUpdateArticleServlet extends ServletBase {

    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	 
    	System.out.println("进入FoUpdateArticleServlet");
    	System.out.println("artId="+request.getParameter("artId"));
       	Connection conn = null;
        //获得待更新信息内容
    	Integer artId=request.getParameter("artId")==null?new Integer(-1):Integer.valueOf(request.getParameter("artId"));
    	String artTitle=request.getParameter("artTitle");
    	String  artContent = request.getParameter("artContent");
    	String artWriter=request.getParameter("artWriter");
    	String artUploadusr=request.getParameter("artUploadusr");
    	String artUploadtime=request.getParameter("artUploadtime");
    	Integer artVotenum=request.getParameter("artVotenum").equals("")?new Integer(-1):Integer.valueOf(request.getParameter("artVotenum"));
    	String artVotescore=request.getParameter("artVotescore");
    	String artPlace=request.getParameter("artPlace");
    	String artTotalnum=request.getParameter("artTotalnum");
    	String artTypeid=request.getParameter("artTypeid");
    	String artTypename=request.getParameter("artTypename");
    	String artOutvotenum=request.getParameter("artOutvotenum");
    	String artInvotenum=request.getParameter("artInvotenum");
    	String artWordnum=request.getParameter("artWordnum");
    	String artPubtime=request.getParameter("artPubtime");
    	String artIstoolong=request.getParameter("artIstoolong");
    	String artUsenum=request.getParameter("artUsenum");
    	String artStatnum=request.getParameter("artStatnum");
    	String artMemo=request.getParameter("artMemo");
    	String artIsentry=request.getParameter("artIsentry");
    	String artIsconsult=request.getParameter("artIsconsult");
    	
    	
    	
    	System.out.println("artId="+artId.toString());
    	System.out.println("artUploadusr="+artUploadusr);
    	System.out.println("artTitle="+artTitle);
    	System.out.println("artWordnum="+artWordnum);
    	System.out.println("artVotescore="+artVotescore.toString());
    	System.out.println("artPubtime="+artPubtime);
        try {
        	
            //初始化用到的数据
        	
        	

            conn = this.getConnection(Globals.DATASOURCEJNDI);
            FoLiuHandler handler=new FoLiuHandler(conn);
            
            
            //写入信息表
           
            FoArticalVO foarticlevo=new FoArticalVO();
            foarticlevo=handler.getArticleVo(artId);
            String planPlanid = foarticlevo.getPlanPlanid().toString();
            foarticlevo.setArtId(artId);
            foarticlevo.setArtTitle(artTitle);
            foarticlevo.setArtContent(artContent);
            foarticlevo.setArtWriter(artWriter);
            foarticlevo.setArtUploadusr(artUploadusr);
            foarticlevo.setArtUploadtime(artUploadtime);
            foarticlevo.setArtVotenum(artVotenum);
            foarticlevo.setArtVotescore(artVotescore);
            foarticlevo.setArtPlace(artPlace);
            foarticlevo.setArtTotalnum(artTotalnum);
            foarticlevo.setArtTypeid(artTypeid);
            foarticlevo.setArtTypename(artTypename);
            foarticlevo.setArtOutvotenum(artOutvotenum);
            foarticlevo.setArtInvotenum(artInvotenum);
            foarticlevo.setArtWordnum(artWordnum);
            foarticlevo.setArtPubtime(artPubtime);
            foarticlevo.setArtIstoolong(artIstoolong);
            foarticlevo.setArtUsenum(artUsenum);
            foarticlevo.setArtStatnum(artStatnum);
            foarticlevo.setArtMemo(artMemo);
            foarticlevo.setArtIsentry(artIsentry);
            foarticlevo.setArtIsconsult(artIsconsult);
            
            
            handler.updateArticle(foarticlevo);
                    
           
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

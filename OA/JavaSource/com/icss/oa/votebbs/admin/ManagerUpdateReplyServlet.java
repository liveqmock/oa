/*
 * Created on 2004-5-14
 */
package com.icss.oa.votebbs.admin;



import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


import java.sql.Connection;



import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;




import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.FileUtil;
import com.icss.j2ee.util.Globals;


import com.icss.oa.votebbs.handler.AttachFileBean;
import com.icss.oa.votebbs.handler.BbsVoteHandler;
import com.icss.oa.votebbs.handler.OptionUploadBean;
import com.icss.oa.votebbs.vo.BbsMainarticleVO;
import com.icss.oa.votebbs.vo.BbsOptionsVO;
import com.icss.oa.votebbs.vo.BbsReplyVO;
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sdk.framework.Context;

public class ManagerUpdateReplyServlet extends ServletBase {

    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	   	
    	Connection conn = null;
        //获得信息内容


		// 保存发送文件信息状态
    	String mainid = request.getParameter("mainid")==null?"-1":request.getParameter("mainid");
    	String title = request.getParameter("title");
    	String	replyid=request.getParameter("replyid")==null?"-1":request.getParameter("replyid");
        String content = request.getParameter("content");
        
        try {
        	

            conn = this.getConnection(Globals.DATASOURCEJNDI);
            BbsVoteHandler handler=new BbsVoteHandler(conn);
            
           //写入信息表先取出
            BbsReplyVO vo=new BbsReplyVO();
            vo=handler.getMangerReplyVO(replyid);
            vo.setReTitle(title);
            vo.setReContext(content);
           
            handler.updateMangagerReply(vo);
            
            response.sendRedirect("ReplyManagerListServlet?mainid="+mainid);

//            this.forward(request, response, "/servlet/ArticleOptionListServlet?mainid="+mainid);
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

/*
 * Created on 2004-5-14
 */
package com.icss.oa.log.admin;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.log.handler.AttachFileBean;
import com.icss.oa.log.handler.LogHandler;
import com.icss.oa.log.handler.SendFileBean;
import com.icss.oa.log.vo.LogAccessoryVO;
import com.icss.oa.log.vo.LogMsgVO;
import com.icss.oa.log.vo.LogReplyVO;
import com.icss.oa.util.CurrentUser;

public class AddLogReplyServlet extends ServletBase {

    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	
    	Connection conn = null;
        //获得信息内容
    	Integer sysid=request.getParameter("sysid")==null?new Integer(-1):new Integer(request.getParameter("sysid"));
    	Integer logid=request.getParameter("logid")==null?new Integer(-1):new Integer(request.getParameter("logid"));
        
    	String replytitle = request.getParameter("replytitle");
        String replycontent = request.getParameter("replycontent");
        
        try {
        	
            //初始化用到的数据
        	
        	
            CurrentUser user = new CurrentUser();
            String currentUserId = user.getId();
            conn = this.getConnection(Globals.DATASOURCEJNDI);
            LogHandler handler=new LogHandler(conn);
            LogReplyVO replyvo=new LogReplyVO();
            
            //写入信息表
            Long CurrentTime = new Long(System.currentTimeMillis());
            replyvo.setLogId(logid);
            replyvo.setReplyTitle(replytitle);
            replyvo.setReplyTime(CurrentTime.toString());
            replyvo.setReplyContent(replycontent);
            replyvo.setReplyPerson(currentUserId);
            
            handler.addReply(replyvo);
            //写入附件表
            
            this.forward(request, response, "/servlet/LogDetailServlet?sysid="+sysid+"&logid="+logid);
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

package com.icss.oa.filetransfer.util;

import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sdk.framework.Context;
import com.icss.resourceone.sdk.framework.EntityException;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import org.redflaglinux.services.dir.dirmanage;

public class MailNeedFlushFilter
    implements Filter
{

    public MailNeedFlushFilter()
    {
    }

    public void init(FilterConfig filterconfig)
        throws ServletException
    {
    }

    public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
        throws IOException, ServletException
    {
        try
        {
            Context ctx = Context.getInstance();
            UserInfo user = ctx.getCurrentLoginInfo();
            dirmanage mailhandler = MailhandlerFactory.getHandler(user.getUserID());
            HttpServletRequest hsr = (HttpServletRequest)arg0;
            String flushed = (String)hsr.getSession().getAttribute("flushed");
            if(flushed == null && mailhandler.needFlush())
            {
                mailhandler.flush();
                hsr.getSession().setAttribute("flushed", "1");
            }
        }
        catch(EntityException e)
        {
            e.printStackTrace();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        arg2.doFilter(arg0, arg1);
    }

    public void destroy()
    {
    }
}
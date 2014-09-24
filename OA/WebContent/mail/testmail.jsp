 <%@ page contentType="text/html; charset=GBK" %>
<%@ page import="java.util.List"%>
<%@ page import="com.icss.oa.util.PropertyManager"%>
<%@ page import="org.redflaglinux.ldap.LdapException"%>
<%@ page import="org.redflaglinux.services.dir.dirmanage"%>
<%@ page import="com.icss.oa.filetransfer.util.MailhandlerFactory"%>
<%@ page import="com.icss.resourceone.common.logininfo.UserInfo"%>
<%@ page import="com.icss.oa.filetransfer.handler.FiletransferSetHandler"%>
<%@ page import="com.icss.resourceone.sdk.framework.Context"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="com.icss.j2ee.servlet.ServletBase"%>
<%@ page import="com.icss.j2ee.util.Globals"%>
<%@ page import="com.icss.oa.filetransfer.admin.SendFile1Servlet"%>
<%@ page import="com.icss.oa.util.CommUtil"%>
<%@ page import="com.icss.j2ee.dao.DAOFactory"%>
<%@ page import="com.icss.j2ee.dao.DAOException"%>
<%@ page import="com.icss.resourceone.user.dao.PersonOrgSearchDAO"%>
<%@ page import="com.icss.oa.sync.vo.PersonTempVO"%>
<%@ page import="com.icss.oa.sync.vo.PersonVO"%>
<%@ page import="com.icss.resourceone.org.dao.OrgDAO"%>
<%@ page import="com.icss.resourceone.org.model.OrgVO"%>

<%@ page import="com.icss.resourceone.user.dao.PersonOrgDAO"%>
<%@ page import="com.icss.resourceone.user.dao.PersonAccountDAO"%>
<%@ page import="com.icss.resourceone.user.dao.PersonDAO"%>
 <%@ page import="com.icss.oa.address.handler.SysOrgpersonHandler"%>
 <%@ page import=" com.icss.oa.address.vo.SysOrgpersonVO"%>
  <%@ page import="com.icss.resourceone.user.model.PersonAccountVO"%>
 
<%!

   public PersonAccountVO getPersonAccount(String personuuid,Connection conn){
		
		PersonAccountDAO dao = new PersonAccountDAO();
		dao.setPersonuuid(personuuid);

		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(dao);

		try {
			List list = factory.find(new PersonAccountVO());
			if (list != null && list.size() > 0) {
				PersonAccountVO vo = (PersonAccountVO) list.get(0);
				return vo;
			}
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
		
	}
	
 %>


<%
  dirmanage mailhandler = null;
  Context ctx = Context.getInstance();
   
  Connection conn = null;
  
  try{
      javax.naming.Context ctx1=new javax.naming.InitialContext();
      javax.sql.DataSource ds=null;
      ds=(javax.sql.DataSource)ctx1.lookup("jdbc/ROEEE");
      conn =ds.getConnection();
       
  
        DAOFactory factory=new DAOFactory(conn);
		OrgDAO orgdao=new OrgDAO();
        orgdao.setCnname("°ì¹«ÊÒ");
        orgdao.setParentorguuid("4161cca6-fc35648aa5-97fe05e58eef24f3370b74f3cc7c23fc");
        factory.setDAO(orgdao);
        OrgVO orgvo=new OrgVO();
        List orglist1=factory.find(orgvo);
           if (orglist1 != null && orglist1.size() > 0) {
            orgvo=(OrgVO)orglist1.get(0);
           }
           else
           {
           }
           
          PersonOrgDAO personlistsearchdao=new PersonOrgDAO();
		personlistsearchdao.setOrguuid(orgvo.getOrguuid());
		factory.setDAO(personlistsearchdao);
		List orglist=factory.find();
           
         out.println("uuid:"+orgvo.getOrguuid()+"\n");
		   StringBuffer temp=new StringBuffer();
	       String officeuserid=null;
	        PersonAccountVO paccountvo=new PersonAccountVO();
	        int count=0;
	     for(int i=0;i<orglist.size();i++)
				  {
					 temp.delete(0,temp.length());
					 officeuserid=((PersonOrgDAO)orglist.get(i)).getPersonuuid();
					 paccountvo=getPersonAccount(officeuserid,conn);
					 
					 if("0".equals(paccountvo.getDeltag()))
					 {
					 officeuserid=paccountvo.getUserid();
					 temp.append(officeuserid);
					 temp.append("@oa.xinhua.net");
					 out.println(temp.toString()+"<br>");
					 count++;
					 }
					 
				  }
				  out.println("orglistsize"+count);
		
		
 }catch(Exception e)
  {
    out.println("fandy:"+e);
  }
  finally{
    conn.close();
  } 
  
		
%>
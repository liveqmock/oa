//FrontEnd Plus GUI for JAD
//DeCompiled : SysPersonDAO.class

package com.icss.oa.phonebook.dao;

import com.icss.j2ee.dao.DAO;
import com.icss.j2ee.dao.DAOException;
import java.sql.Connection;

public class SysPersonDAO extends DAO
{

 private String personuuid;
 private String userid;
 private String cnname;

 public String getPersonuuid()
 {
     return personuuid;
 }

 public void setPersonuuid(String _personuuid)
 {
     firePropertyChange("personuuid", personuuid, _personuuid);
     personuuid = _personuuid;
 }

 public String getUserid()
 {
     return userid;
 }

 public void setUserid(String _userid)
 {
     firePropertyChange("userid", userid, _userid);
     userid = _userid;
 }

 public String getCnname()
 {
     return cnname;
 }

 public void setCnname(String _cnname)
 {
     firePropertyChange("cnname", cnname, _cnname);
     cnname = _cnname;
 }

 protected void setupFields()
     throws DAOException
 {
     addField("personuuid", "PERSONUUID");
     addField("userid", "USERID");
     addField("cnname", "CNNAME");
     setTableName("SYS_PERSON");
 }

 public SysPersonDAO(Connection conn)
 {
     super(conn);
 }

 public SysPersonDAO()
 {
 }
}




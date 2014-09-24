/*
 * Created on 2005-1-14
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.icss.oa.phonebook.vo;

import com.icss.j2ee.vo.ValueObject;

/**
 * @author firecoral
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SysPersonVO extends ValueObject{
    private String personuuid;
    private String userid;
    private String cnname;

    public SysPersonVO()
    {
    }

    public String getPersonuuid()
    {
        return personuuid;
    }

    public void setPersonuuid(String _personuuid)
    {
        personuuid = _personuuid;
    }

    public String getUserid()
    {
        return userid;
    }

    public void setUserid(String _userid)
    {
        userid = _userid;
    }

    public String getCnname()
    {
        return cnname;
    }

    public void setCnname(String _cnname)
    {
        cnname = _cnname;
    }

}

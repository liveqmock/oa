package com.icss.oa.tq.vo;

import com.icss.j2ee.vo.ValueObject;

public class TqRopersonVO extends ValueObject
{
	 public String personuuid;
	 public Integer tqid;
	  public String getPersonuuid()
	    {
	        return personuuid;
	    }
	    public void setPersonuuid(String _personuuid)
	    {
	        personuuid = _personuuid;
	    }
	    public Integer getTqid()
	    {
	        return tqid;
	    }
	    public void setTqid(Integer _tqid)
	    {
	    	tqid = _tqid;
	    }
}

package com.icss.oa.bbs.vo;

import java.sql.Timestamp;

import com.icss.j2ee.vo.ValueObject;

public class BbsOutVO extends ValueObject {

    private String cnname;
    private String personuuid;
    private Timestamp time;

    public java.lang.String getCnname(){
        return cnname;
    }
    public void setCnname(java.lang.String _cnname){ 
    	cnname = _cnname;
    }
    public java.lang.String getPersonuuid(){
        return personuuid;
    }
    public void setPersonuuid(java.lang.String _personuuid){ 
    	personuuid = _personuuid;
    }
    public java.sql.Timestamp getTime(){
        return time;
    }
    public void setTime(java.sql.Timestamp _time){ 
        time = _time;
    }
    public String toString(){
        String str="  [TYPE=<java.lang.String>	 FIELD=<CNNAME>	 VALUE=<"+cnname+">"
        +"  [TYPE=<java.lang.String>	 FIELD=<PERSONUUID>	 VALUE=<"+personuuid+">"
        +"  [TYPE=<java.sql.Timestamp>	 FIELD=<TIME>	 VALUE=<"+time+">";
        return str;
    }
}
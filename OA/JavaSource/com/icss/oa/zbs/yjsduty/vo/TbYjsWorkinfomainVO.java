/*
 * 创建日期 2010-12-10
 *
 * 更改所生成文件模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
package com.icss.oa.zbs.yjsduty.vo;

/**
 * @author 刘培
 *
 * 更改所生成类型注释的模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
import java.sql.Timestamp;

import com.icss.j2ee.vo.ValueObject;

public class TbYjsWorkinfomainVO extends ValueObject {

    private Integer wimId;
    private Timestamp witDate;
    private String witClass;
    private String witLeader;
    private String witSecret;
    private String witCreater;
    private Timestamp witCreatetime;
	private String lastEditer;
	private String lastIP;
	private String flag;

    public String getLastEditer() {
		return lastEditer;
	}
	public void setLastEditer(String lastEditer) {
		this.lastEditer = lastEditer;
	}
	public String getLastIP() {
		return lastIP;
	}
	public void setLastIP(String lastIP) {
		this.lastIP = lastIP;
	}
    public java.lang.Integer getWimId(){
        return wimId;
    }
    public void setWimId(java.lang.Integer _wimId){ 
        wimId = _wimId;
    }
    public java.sql.Timestamp getWitDate(){
        return witDate;
    }
    public void setWitDate(java.sql.Timestamp _witDate){ 
        witDate = _witDate;
    }
    public java.lang.String getWitClass(){
        return witClass;
    }
    public void setWitClass(java.lang.String _witClass){ 
        witClass = _witClass;
    }
    public java.lang.String getWitLeader(){
        return witLeader;
    }
    public void setWitLeader(java.lang.String _witLeader){ 
        witLeader = _witLeader;
    }
    public java.lang.String getWitSecret(){
        return witSecret;
    }
    public void setWitSecret(java.lang.String _witSecret){ 
        witSecret = _witSecret;
    }
    public java.lang.String getWitCreater(){
        return witCreater;
    } 
    public void setWitCreater(java.lang.String _witCreater){ 
        witCreater = _witCreater;
    } 
    public java.sql.Timestamp getWitCreatetime(){
        return witCreatetime;
    }
    public void setWitCreatetime(java.sql.Timestamp _witCreatetime){ 
        witCreatetime = _witCreatetime;
    }
    public java.lang.String getFlag(){
        return flag;
    }
    public void setFlag(java.lang.String _flag){ 
        flag = _flag;
    }
    public String toString(){
        String str="  [TYPE=<java.lang.Integer>	 FIELD=<wimId>	 VALUE=<"+wimId+">"
        +"  [TYPE=<java.sql.Timestamp>	 FIELD=<witDate>	 VALUE=<"+witDate+">"
        +"  [TYPE=<java.lang.String>	 FIELD=<witClass>	 VALUE=<"+witClass+">"
        +"  [TYPE=<java.lang.String>	 FIELD=<witLeader>	 VALUE=<"+witLeader+">"
        +"  [TYPE=<java.lang.String>	 FIELD=<witSecret>	 VALUE=<"+witSecret+">"
        +"  [TYPE=<java.lang.String>	 FIELD=<witCreater>	 VALUE=<"+witCreater+">"
        +"  [TYPE=<java.sql.Timestamp>	 FIELD=<witCreatetime>	 VALUE=<"+witCreatetime+">"
        +"  [TYPE=<java.sql.String>	 FIELD=<lastEditor>	 VALUE=<"+lastEditer+">"
        +"  [TYPE=<java.sql.String>	 FIELD=<lastIP>	 VALUE=<"+lastIP+">"
        +"  [TYPE=<java.sql.String>	 FIELD=<flag>	 VALUE=<"+flag+">"
        +"";
        return str;
    }
}
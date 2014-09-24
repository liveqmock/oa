/*
 * 创建日期 2010-12-10
 *
 * 更改所生成文件模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
package com.icss.oa.zbs.xtsduty.vo;

/**
 * @author 刘培
 *
 * 更改所生成类型注释的模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
import java.sql.Timestamp;

import com.icss.j2ee.vo.ValueObject;

public class TbXtsWorkinfoVO extends ValueObject {

    private Integer wiId;
    private Integer wimId;
    private Integer witId;
    private String witContent;
    private String witCreater;
    private Timestamp witCreatetime;
    private String witModifyer;
    private Timestamp witModifytime;

    public java.lang.Integer getWiId(){
        return wiId;
    }
    public void setWiId(java.lang.Integer _wiId){ 
        wiId = _wiId;
    }
    public java.lang.Integer getWimId(){
        return wimId;
    }
    public void setWimId(java.lang.Integer _wimId){ 
        wimId = _wimId;
    }
    public java.lang.Integer getWitId(){
        return witId;
    }
    public void setWitId(java.lang.Integer _witId){ 
        witId = _witId;
    }
    public java.lang.String getWitContent(){
        return witContent;
    }
    public void setWitContent(java.lang.String _witContent){ 
        witContent = _witContent;
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
    public java.lang.String getWitModifyer(){
        return witModifyer;
    }
    public void setWitModifyer(java.lang.String _witModifyer){ 
        witModifyer = _witModifyer;
    }
    public java.sql.Timestamp getWitModifytime(){
        return witModifytime;
    }
    public void setWitModifytime(java.sql.Timestamp _witModifytime){ 
        witModifytime = _witModifytime;
    }
    public String toString(){
        String str="  [TYPE=<java.lang.Integer>	 FIELD=<wiId>	 VALUE=<"+wiId+">"
        +"  [TYPE=<java.lang.Integer>	 FIELD=<wimId>	 VALUE=<"+wimId+">"
        +"  [TYPE=<java.lang.Integer>	 FIELD=<witId>	 VALUE=<"+witId+">"
        +"  [TYPE=<java.lang.String>	 FIELD=<witContent>	 VALUE=<"+witContent+">"
        +"  [TYPE=<java.lang.String>	 FIELD=<witCreater>	 VALUE=<"+witCreater+">"
        +"  [TYPE=<java.sql.Timestamp>	 FIELD=<witCreatetime>	 VALUE=<"+witCreatetime+">"
        +"  [TYPE=<java.lang.String>	 FIELD=<witModifyer>	 VALUE=<"+witModifyer+">"
        +"  [TYPE=<java.sql.Timestamp>	 FIELD=<witModifytime>	 VALUE=<"+witModifytime+">"
        +"";
        return str;
    }
}
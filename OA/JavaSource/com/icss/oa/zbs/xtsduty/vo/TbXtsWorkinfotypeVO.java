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

import com.icss.j2ee.vo.ValueObject;

public class TbXtsWorkinfotypeVO extends ValueObject {

    private Integer witId;
    private String witName;
    private String witMemo;
    private Integer witIndex;
    private String witType;

    public java.lang.Integer getWitId(){
        return witId;
    }
    public void setWitId(java.lang.Integer _witId){ 
        witId = _witId;
    }
    public java.lang.String getWitName(){
        return witName;
    }
    public void setWitName(java.lang.String _witName){ 
        witName = _witName;
    }
    public java.lang.String getWitMemo(){
        return witMemo;
    }
    public void setWitMemo(java.lang.String _witMemo){ 
        witMemo = _witMemo;
    }
    public java.lang.Integer getWitIndex(){
        return witIndex;
    }
    public void setWitIndex(java.lang.Integer _witIndex){ 
        witIndex = _witIndex;
    }
    public java.lang.String getWitType(){
        return witType;
    }
    public void setWitType(java.lang.String _witType){ 
        witType = _witType;
    }
    public String toString(){
        String str="  [TYPE=<java.lang.Integer>	 FIELD=<witId>	 VALUE=<"+witId+">"
        +"  [TYPE=<java.lang.String>	 FIELD=<witName>	 VALUE=<"+witName+">"
        +"  [TYPE=<java.lang.String>	 FIELD=<witMemo>	 VALUE=<"+witMemo+">"
        +"  [TYPE=<java.lang.Integer>	 FIELD=<witIndex>	 VALUE=<"+witIndex+">"
        +"  [TYPE=<java.lang.String>	 FIELD=<witType>	 VALUE=<"+witType+">"
        +"";
        return str;
    }
}
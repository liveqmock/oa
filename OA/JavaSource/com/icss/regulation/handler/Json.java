package com.icss.regulation.handler;

import java.util.ArrayList;

public class Json {

    public static void main(String[] args) {

    }
    
    public String singleInfo="";
    protected boolean _success=true;
    protected String _error="";
    protected ArrayList arrData=new ArrayList();
    protected ArrayList dataItem=new ArrayList();
    public String getError() {
        return _error;
    }
    public void setError(String error) {
        if(!error.equals(""))this._success=false;
        this._error = error;
    }
    public boolean getSuccess() {
        return _success;
    }
    public void setSuccess(boolean success) {
        if(success) this._error="";
        this._success = success;
    }
    
    public Json()
    {
        
    }
    
    public void reSet()
    {
        arrData.clear();
        dataItem.clear();
    }
    
    public void addItem(String name,String _value)
    {
        dataItem.add(name);
        dataItem.add(_value);
    }
    
    //一个数组添加完毕，一个新的数组开始
    public void addItemOk()
    {
        arrData.add(dataItem);
        dataItem=new ArrayList();
    }
    
    public String ToString()
    {
        StringBuilder sb=new StringBuilder();
        sb.append("{");
        sb.append("\"datas\":");
        sb.append("[");
        int ad=arrData.size();
        for(int i=0;i<ad;i++)
        {
            ArrayList arr=(ArrayList)(arrData.get(i));
            sb.append("{");
            int t=arr.size();
            for(int j=0;j<t;j+=2)
            {
                if(j==t) break;
                sb.append("\"");
                sb.append(arr.get(j).toString());
                sb.append("\"");
                sb.append(":");
                sb.append("\"");
                sb.append(arr.get(j+1).toString());
                sb.append("\"");
                if(j<t-2) sb.append(",");
            }
            sb.append("}");
            if(i<ad-1) sb.append(",");
        }
        sb.append("]");
        sb.append("}");
        return sb.toString();
    }
    
 }


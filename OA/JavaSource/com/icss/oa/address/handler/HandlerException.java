package com.icss.oa.address.handler;

import com.icss.j2ee.util.ExceptionBase;

public class HandlerException extends ExceptionBase {
  public HandlerException()
  {
  }

public HandlerException(String msg){
  super(msg);
}

public HandlerException(Throwable t){
  super(t);
}

}


package com.icss.popuporgtree.handler;

import com.icss.j2ee.util.ExceptionBase;

/**
 * @author lichg
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
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

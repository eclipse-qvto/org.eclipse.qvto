/*******************************************************************************
 * Copyright (c) 2007 Borland Software Corporation
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Borland Software Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.m2m.internal.qvt.oml.evaluator;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
* Represents a runtime (unchecked) exception possibly thrown during QVT code execution.
*/
public class QvtRuntimeException extends RuntimeException {

	private static final long serialVersionUID = -8903219155434276631L;
	
	private List<QVTStackTraceElement> fQVTStackTrace;
	
	
	public QvtRuntimeException() {
		super();
	}	
	
	public QvtRuntimeException(String message) {
		super(message);
	}
		
	public QvtRuntimeException(Throwable cause) {
		super(cause);
	}

	public QvtRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}	
		
    public void printQvtStackTrace(PrintWriter pw) {
       synchronized (pw) {
            pw.println(this);
            int counter = 0;
            for(QVTStackTraceElement trace : getQvtStackTrace()) {
            	if(counter++ > 0) {
            		pw.println();
            	}
            	pw.print("\tat " + trace); //$NON-NLS-1$
            }
       }
    }
	
	public List<QVTStackTraceElement> getQvtStackTrace() {		
		if(fQVTStackTrace != null) {
			return Collections.unmodifiableList(fQVTStackTrace);
		}
		return Collections.emptyList();
	}
	
	public void setStackQvtTrace(List<QVTStackTraceElement> stackTrace) {
		if(fQVTStackTrace != null) {
			throw new IllegalStateException("Can't reassign stack elements");
		}
		
		fQVTStackTrace = null;
		if(stackTrace != null) {
			fQVTStackTrace = new ArrayList<QVTStackTraceElement>(stackTrace);
		}
	}		
}

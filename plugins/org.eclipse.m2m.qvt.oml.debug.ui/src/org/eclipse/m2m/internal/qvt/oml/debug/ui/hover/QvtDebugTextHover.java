/*******************************************************************************
 * Copyright (c) 2026 S. Steudle and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     Christopher Gerking - initial API and implementation
 *     Steffen Steudle - issue #1138
 *******************************************************************************/
package org.eclipse.m2m.internal.qvt.oml.debug.ui.hover;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IStackFrame;
import org.eclipse.debug.core.model.IVariable;
import org.eclipse.debug.ui.DebugUITools;
import org.eclipse.jface.text.IInformationControlCreator;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.m2m.internal.qvt.oml.debug.ui.QVTODebugUIPlugin;
import org.eclipse.m2m.internal.qvt.oml.editor.ui.hovers.QvtBasicTextHover;
import org.eclipse.m2m.qvt.oml.debug.core.QVTOStackFrame;
import org.eclipse.m2m.qvt.oml.debug.core.QVTOVariable;
import org.eclipse.ocl.cst.CSTNode;
import org.eclipse.ocl.cst.SimpleNameCS;

public class QvtDebugTextHover extends QvtBasicTextHover<IVariable> {
		
	@Override
	public String getInfoText(IVariable info) {
		if (info != null ) {
			try {			
				return info.getValue().getValueString();
			}
			catch(DebugException e) {
				QVTODebugUIPlugin.log(e.getStatus());
			}
		};
		return null;
	}
	
	protected IVariable getElementInfo(CSTNode element, ITextViewer textViewer, IRegion region) {
		IAdaptable debugContext = DebugUITools.getDebugContext();
		if (debugContext == null) {
			return null;
		}

		if (!(element instanceof SimpleNameCS)) {
			return null;
		}
		
		SimpleNameCS simpleName = (SimpleNameCS) element;

		QVTOStackFrame stackFrame = debugContext.getAdapter(QVTOStackFrame.class);

		try {
			return getQVTOVariableFor(simpleName, stackFrame);
		} catch (DebugException e) {
			QVTODebugUIPlugin.log(e.getStatus());
			return null;
		}
	}
		
	private static IVariable getQVTOVariableFor(SimpleNameCS simpleNameCS, QVTOStackFrame startingStackFrame) throws DebugException {
		for (IStackFrame stackFrame : startingStackFrame.getThread().getStackFrames()) {
			for (IVariable variable : stackFrame.getVariables()) {
				IVariable qvtoVariable = tryGetQVTOVariableFor(simpleNameCS, variable);

				if (qvtoVariable != null) {
					return qvtoVariable;
				}

				if (variable.getName().equals("result")) { //$NON-NLS-1$
					for (IVariable resultVariable : variable.getValue().getVariables()) {
						IVariable resultQvtoVariable = tryGetQVTOVariableFor(simpleNameCS, resultVariable);

						if (resultQvtoVariable != null) {
							return resultQvtoVariable;
						}
					}
				}
			}
		}

		return null;
	}

	private static IVariable tryGetQVTOVariableFor(SimpleNameCS simpleNameCS, IVariable variable) {
		if (!(variable instanceof QVTOVariable)) {
			return null;
		}
		
		try {
			if (variable.getName().equals(simpleNameCS.getValue())) {
				return variable;
			}
		}
		catch(DebugException e) {
			QVTODebugUIPlugin.log(e.getStatus());
		}

		return null;
	}
	
	@Override
	@SuppressWarnings("restriction")
	public IInformationControlCreator getHoverControlCreator() {
		return new org.eclipse.debug.internal.ui.hover.ExpressionInformationControlCreator();
	}
}

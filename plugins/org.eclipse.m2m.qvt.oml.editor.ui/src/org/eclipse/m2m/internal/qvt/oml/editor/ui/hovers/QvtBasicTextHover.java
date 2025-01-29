/*******************************************************************************
 * Copyright (c) 2026 C. Gerking and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     Christopher Gerking - initial API and implementation
 *******************************************************************************/
package org.eclipse.m2m.internal.qvt.oml.editor.ui.hovers;

import java.util.List;

import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextHover;
import org.eclipse.jface.text.ITextHoverExtension;
import org.eclipse.jface.text.ITextHoverExtension2;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.Region;
import org.eclipse.m2m.internal.qvt.oml.compiler.CompiledUnit;
import org.eclipse.m2m.internal.qvt.oml.editor.ui.Activator;
import org.eclipse.m2m.internal.qvt.oml.editor.ui.CSTHelper;
import org.eclipse.m2m.internal.qvt.oml.editor.ui.QvtDocumentProvider;
import org.eclipse.ocl.cst.CSTNode;

public abstract class QvtBasicTextHover<Info> implements ITextHover, ITextHoverExtension, ITextHoverExtension2 {
	
	private QvtDocumentProvider myDocumentProvider;
		
	protected void setDocumentProvider(QvtDocumentProvider documentProvider) {
		myDocumentProvider = documentProvider;
	}
	
	protected QvtDocumentProvider getDocumentProvider() {
		return myDocumentProvider;
	}
	public IRegion getHoverRegion(final ITextViewer textViewer, final int offset) {
		return new Region(offset, 0);
	}
	
	public String getHoverInfo(final ITextViewer textViewer, final IRegion hoverRegion) {
		Info info = getHoverInfo2(textViewer, hoverRegion);
		return getInfoText(info);
	}
	
	protected abstract String getInfoText(Info info);
	
	private boolean checkCompiledUnit(final CompiledUnit unit) {
		return unit != null && unit.getUnitCST() != null;
	}
	
	public Info getHoverInfo2(final ITextViewer textViewer, final IRegion hoverRegion) {
		if (checkCompiledUnit(getDocumentProvider().getCompiledModule()) && 
				textViewer != null && textViewer.getDocument() != null) {
			
			CSTNode rootCS = getDocumentProvider().getCompiledModule().getUnitCST();
			List<CSTNode> elements = CSTHelper.selectTargetedElements(rootCS, hoverRegion);
			if(!elements.isEmpty()) {
				try {
					return getElementsInfo(elements, textViewer, hoverRegion);
				} catch (Exception e) {
					Activator.log(e);
				}
			}
		}
		return null;
	}
	
	protected Info getElementsInfo(final List<CSTNode> elements, ITextViewer textViewer, IRegion hoverRegion) {
		for (CSTNode nextElement : elements) {
			Info info = getElementInfo(nextElement, textViewer, hoverRegion);
			if (info != null) {
				return info;
			}	
		}
		return null;
	}
	
	protected abstract Info getElementInfo(CSTNode element, ITextViewer textViewer, IRegion region);
	
}
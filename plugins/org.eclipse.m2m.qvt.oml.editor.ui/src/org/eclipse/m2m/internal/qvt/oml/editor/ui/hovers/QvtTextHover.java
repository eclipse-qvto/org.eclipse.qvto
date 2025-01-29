/*******************************************************************************
 * Copyright (c) 2007, 2026 Borland Software Corporation and others.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *     Borland Software Corporation - initial API and implementation
 *     Steffen Steudle - issue #1138
 *     Christopher Gerking - issue #1138
 *******************************************************************************/
package org.eclipse.m2m.internal.qvt.oml.editor.ui.hovers;


import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.text.IInformationControlCreator;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextHover;
import org.eclipse.jface.text.ITextHoverExtension;
import org.eclipse.jface.text.ITextHoverExtension2;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.m2m.internal.qvt.oml.editor.ui.Activator;
import org.eclipse.m2m.internal.qvt.oml.editor.ui.QvtDocumentProvider;


public class QvtTextHover implements ITextHover, ITextHoverExtension, ITextHoverExtension2 {
	
	private static final String EXTENSION_POINT_ID = "editorTextHovers"; //$NON-NLS-1$
	private static final String ELEMENT_TEXT_HOVER = "textHover"; //$NON-NLS-1$
	private static final String ATTRIBUTE_CLASS = "class"; //$NON-NLS-1$
	
	private final List<QvtBasicTextHover<?>> myChildTextHovers;
	
	private final QvtBasicTextHover<String> myDefaultTextHover;
	private QvtBasicTextHover<?> myCurrentTextHover;
	
	public QvtTextHover(final QvtDocumentProvider documentProvider) {
		myDefaultTextHover = new QvtDefaultTextHover(documentProvider);
		myChildTextHovers = new ArrayList<QvtBasicTextHover<?>>(createTextHovers(documentProvider));
		myChildTextHovers.add(myDefaultTextHover);
	}
	
	@Override
	public IRegion getHoverRegion(final ITextViewer textViewer, final int offset) {
		return myDefaultTextHover.getHoverRegion(textViewer, offset);
	}
	
	@Override
	public Object getHoverInfo2(final ITextViewer textViewer, final IRegion hoverRegion) {
		return retrieveInfoFromChildren(
			new Informant<Object>() {
				@Override
				public <Info> Object inform(QvtBasicTextHover<Info> hover) {
					return hover.getHoverInfo2(textViewer, hoverRegion);
				};
			}
		);
	}
	
	@Override
	public String getHoverInfo(final ITextViewer textViewer, final IRegion hoverRegion) {
		return retrieveInfoFromChildren(
			new Informant<String>() {
				@Override
				public <Info> String inform(QvtBasicTextHover<Info> hover) {
					return hover.getHoverInfo(textViewer, hoverRegion);
				};
			}
		);
	}
	
	private interface Informant<FinalForm> {
		<OriginalForm> FinalForm inform(QvtBasicTextHover<OriginalForm> hover);
	}
	private <Info> Info retrieveInfoFromChildren(Informant<Info> informant) {
		myCurrentTextHover = null;
		
		for (QvtBasicTextHover<?> childHover : myChildTextHovers) {
			Info info = informant.inform(childHover);
			if (info != null) {
				myCurrentTextHover = childHover;
				return info;
			}
		}
		
		return null;
	}
	
	@Override
	public IInformationControlCreator getHoverControlCreator() {
		if (myCurrentTextHover == null) {
			return null;
		}
		return myCurrentTextHover.getHoverControlCreator();
	}
	
	private static List<QvtBasicTextHover<?>> createTextHovers(final QvtDocumentProvider documentProvider) {
		List<QvtBasicTextHover<?>> results = new ArrayList<QvtBasicTextHover<?>>();
		
		IConfigurationElement[] configurationElements = 
				Platform.getExtensionRegistry().getConfigurationElementsFor(Activator.PLUGIN_ID, EXTENSION_POINT_ID);
		
		for (IConfigurationElement element : configurationElements) {
			if (!ELEMENT_TEXT_HOVER.equals(element.getName())) {
				continue;
			}
			
			try {
				Object instance = element.createExecutableExtension(ATTRIBUTE_CLASS);
				if (instance instanceof QvtBasicTextHover<?>) {
					QvtBasicTextHover<?> hover = (QvtBasicTextHover<?>) instance;
					hover.setDocumentProvider(documentProvider);
					results.add(hover);
				}
			} catch (CoreException e) {
				Activator.log(e.getStatus());
			}
		}
		
		return results;
	}
}

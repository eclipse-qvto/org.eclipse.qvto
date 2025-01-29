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
package org.eclipse.m2m.internal.qvt.oml.editor.ui.hovers;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jface.text.DefaultInformationControl;
import org.eclipse.jface.text.IInformationControl;
import org.eclipse.jface.text.IInformationControlCreator;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.Position;
import org.eclipse.jface.text.source.Annotation;
import org.eclipse.jface.text.source.IAnnotationModel;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.m2m.internal.qvt.oml.editor.ui.QvtDocumentProvider;
import org.eclipse.ocl.cst.CSTNode;
import org.eclipse.swt.widgets.Shell;


public class QvtDefaultTextHover extends QvtBasicTextHover<String> {
	
	private final static List<IElementInfoProvider> DEFAULT_ELEMENT_INFO_PROVIDERS = Arrays.asList(
			new OperationCallInfoProvider(),
			new PropertyCallInfoProvider(),
			new VariableExpressionInfoProvider(),
			new PatternPropertyExpressionInfoProvider(),
			new PathNameInfoProvider(),
			new ModuleImportInfoProvider(),
			new ResolveInMappingInfoProvider(),
			new ModelTypeInfoProvider()
	);
	
	private final List<IElementInfoProvider> elementInfoProviders;
	
	public QvtDefaultTextHover(final QvtDocumentProvider documentProvider) {
		setDocumentProvider(documentProvider);
		elementInfoProviders = new ArrayList<IElementInfoProvider>(DEFAULT_ELEMENT_INFO_PROVIDERS);
	}
	
	public String getHoverInfo2(final ITextViewer textViewer, final IRegion hoverRegion) {
		Annotation annotation = getAnnotation(textViewer, hoverRegion.getOffset());
		if (annotation != null) {
			return annotation.getText();
		}
		
		return super.getHoverInfo2(textViewer, hoverRegion);
	}
	
	@Override
	protected String getInfoText(String info) {
		return info;
	}
	
	public Annotation getAnnotation(final ITextViewer textViewer, final int offset) {
		if (textViewer instanceof ISourceViewer) {
			final IAnnotationModel annotationModel = ((ISourceViewer) textViewer).getAnnotationModel();
			if (annotationModel == null) {
				return null;
			}
			ArrayList<Annotation> annotations = new ArrayList<Annotation>();
			for (Iterator<?> iter = annotationModel.getAnnotationIterator(); iter.hasNext();) {
				Annotation annotation = (Annotation)iter.next();
				if (annotation.isPersistent() && !annotation.isMarkedDeleted()) {
					Position position = annotationModel.getPosition(annotation);
					if (position != null && position.includes(offset)) {
						annotations.add(annotation);
					}
				}
			}
			if (!annotations.isEmpty()) {
				Collections.sort(annotations, new Comparator<Annotation>() {
					public int compare(final Annotation o1, final Annotation o2) {
						Position p1 = annotationModel.getPosition(o1);
						Position p2 = annotationModel.getPosition(o2);
						return p1.getLength() - p2.getLength();
					}
				});
				return (Annotation) annotations.get(0);
			}
		}
		return null;
	}
	
	protected String getElementInfo(CSTNode element, ITextViewer textViewer, IRegion region) {
		for (IElementInfoProvider provider : elementInfoProviders) {
			try {
				String info = provider.getElementInfo(element, textViewer, region);
				if (info != null) {
					return info;
				}
			} catch (NullPointerException e) {
				// ignore
			}
		}
		return null;
	}
	
	@Override
	public IInformationControlCreator getHoverControlCreator() {
		return new IInformationControlCreator() {
			@Override
			public IInformationControl createInformationControl(Shell parent) {
				return new DefaultInformationControl(parent, (String) null);
			}
		};
	}
}

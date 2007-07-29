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
package org.eclipse.m2m.internal.qvt.oml.runtime.ui.trace.presentation;

import org.eclipse.emf.ecore.EObject;

/**
 * @author Aleksandr Igdalov
 */
public class EObjectNode extends Node {
    public EObjectNode(Object parent, EObject object) {
        super(parent, object);
    }
    
    public EObject getObject() {
        return (EObject) super.getObject();
    }
}
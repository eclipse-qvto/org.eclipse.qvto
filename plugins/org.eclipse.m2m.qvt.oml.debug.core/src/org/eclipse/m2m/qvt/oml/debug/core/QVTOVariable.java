/*******************************************************************************
 * Copyright (c) 2009, 2026 R.Dvorak and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     Radek Dvorak - initial API and implementation
 *     Steffen Steudle - issue #915
 *******************************************************************************/
package org.eclipse.m2m.qvt.oml.debug.core;

import java.util.Arrays;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IIndexedValue;
import org.eclipse.debug.core.model.IValue;
import org.eclipse.debug.core.model.IVariable;
import org.eclipse.m2m.qvt.oml.debug.core.vm.VMVariable;
import org.eclipse.m2m.qvt.oml.debug.core.vm.Value;

public class QVTOVariable extends QVTODebugElement implements IVariable {

	/**
	 * @since 2.11
	 */
	private static class QVTOCollectionValue extends QVTOValue implements IIndexedValue {

		QVTOCollectionValue(IQVTODebugTarget debugTarget, VMVariable vmVar, long frameID) {
			super(debugTarget, vmVar, frameID);
		}

		@Override
		public IVariable getVariable(int offset) throws DebugException {
			return getVariables()[offset];
		}

		@Override
		public IVariable[] getVariables(int offset, int length) throws DebugException {
			return Arrays.copyOfRange(getVariables(), offset, offset + length);
		}

		@Override
		public int getSize() throws DebugException {
			return getVariables().length;
		}

		@Override
		public int getInitialOffset() {
			return 0;
		}
	}


	final VMVariable vmVar;
	private final long fFrameID;
	protected IValue fValue;


	QVTOVariable(IQVTODebugTarget debugTarget, final VMVariable vmVar, long frameID) {
		super(debugTarget);

		this.vmVar = vmVar;
		this.fFrameID = frameID;
	}

	public boolean isModelParameter() {
		return vmVar.kind == VMVariable.MODEL_PARAMETER;
	}

	public boolean isLocalVariable() {
		return vmVar.kind == VMVariable.LOCAL;
	}

	public boolean isCollectionElement() {
		return vmVar.kind == VMVariable.COLLECTION_ELEMENT;
	}

	public boolean isPredefinedVariable() {
		return vmVar.kind == VMVariable.PREDEFINED_VAR;
	}

	public boolean isIntermProperty() {
		return vmVar.kind == VMVariable.INTERM_PROPERTY;
	}

	public boolean isAttribute() {
		return vmVar.kind == VMVariable.ATTRIBUTE;
	}

	public boolean isReference() {
		return vmVar.kind == VMVariable.REFERENCE;
	}

	@Override
	public IValue getValue() throws DebugException {
		if (fValue == null) {
			if (vmVar.type.kind == Value.Type.COLLECTION) {
				fValue = new QVTOCollectionValue(getQVTODebugTarget(), vmVar, fFrameID);
			} else {
				fValue = new QVTOValue(getQVTODebugTarget(), vmVar, fFrameID);
			}
		}
		return fValue;
	}

	@Override
	public String getName() throws DebugException {
		return vmVar.name;
	}

	@Override
	public String getReferenceTypeName() throws DebugException {
		return this.vmVar.type.declaringType;
	}

	@Override
	public boolean hasValueChanged() throws DebugException {
		return false;
	}

	@Override
	public boolean supportsValueModification() {
		return false;
	}

	@Override
	public void setValue(String expression) throws DebugException {
	}

	@Override
	public void setValue(IValue value) throws DebugException {
	}

	@Override
	public boolean verifyValue(String expression) throws DebugException {
		return false;
	}

	@Override
	public boolean verifyValue(IValue value) throws DebugException {
		return false;
	}
}
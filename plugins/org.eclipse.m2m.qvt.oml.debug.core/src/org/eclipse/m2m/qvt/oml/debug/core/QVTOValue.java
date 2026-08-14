/*******************************************************************************
 * Copyright (c) 2009, 2018 R.Dvorak and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     Radek Dvorak - initial API and implementation
 *******************************************************************************/
package org.eclipse.m2m.qvt.oml.debug.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IValue;
import org.eclipse.debug.core.model.IVariable;
import org.eclipse.emf.common.util.URI;
import org.eclipse.m2m.internal.qvt.oml.stdlib.AbstractQVTStdlib;
import org.eclipse.m2m.qvt.oml.debug.core.vm.VMVariable;
import org.eclipse.m2m.qvt.oml.debug.core.vm.Value;
import org.eclipse.m2m.qvt.oml.debug.core.vm.protocol.VMDetailRequest;
import org.eclipse.m2m.qvt.oml.debug.core.vm.protocol.VMDetailResponse;
import org.eclipse.m2m.qvt.oml.debug.core.vm.protocol.VMResponse;
import org.eclipse.m2m.qvt.oml.debug.core.vm.protocol.VMVariableRequest;
import org.eclipse.m2m.qvt.oml.debug.core.vm.protocol.VMVariableResponse;

public class QVTOValue extends QVTODebugElement implements IValue {
	final VMVariable vmVar;
	private Value vmValue;
	private long frameID;

	QVTOValue(IQVTODebugTarget debugTarget, VMVariable vmVar, long frameID) {
		super(debugTarget);
		this.vmVar = vmVar;
		this.vmValue = vmVar.value;
		this.frameID = frameID;
	}

	@Override
	public boolean hasVariables() throws DebugException {
		return vmValue != null && vmValue.hasVariables;
	}

	@Override
	public IVariable[] getVariables() throws DebugException {
		List<VMVariable> variables = requestVariables();
		List<IVariable> result = new ArrayList<IVariable>();

		for (VMVariable nextVar : variables) {
			result.add(new QVTOVariable(getQVTODebugTarget(), nextVar, this.frameID));
		}

		return result.toArray(new IVariable[result.size()]);
	}

	@Override
	public String getValueString() throws DebugException {
		return (vmValue != null) ? String.valueOf(vmValue.value) : AbstractQVTStdlib.NULL_NAME;
	}

	@Override
	public String getReferenceTypeName() throws DebugException {
		return this.vmVar.type.actualType;
	}

    public String computeDetail() throws DebugException {
    	URI varURI = URI.createURI(getVariableURIForVMRequest());
    	VMDetailRequest request = new VMDetailRequest(varURI);

    	VMResponse response = getQVTODebugTarget().sendRequest(request);
    	if(response instanceof VMDetailResponse) {
    		VMDetailResponse detailResponse = (VMDetailResponse) response;
    		return detailResponse.getDetail();
    	}
    	return ""; //$NON-NLS-1$
    }

    private String getVariableURIForVMRequest() {
		String variableURI = vmVar.name;
		if(!vmVar.isRootVariable()) {
			variableURI = vmVar.variableURI;
		}
		return variableURI;
    }

	List<VMVariable> requestVariables() throws DebugException {
		String variableURI = getVariableURIForVMRequest();

		VMVariableRequest request = new VMVariableRequest(
				frameID, variableURI, true);

		VMResponse response = getQVTODebugTarget().sendRequest(request);

		if(response instanceof VMVariableResponse) {
			VMVariableResponse variableResponse = (VMVariableResponse) response;
			if(variableResponse.childVariables != null) {
				return Arrays.asList(variableResponse.childVariables);
			}
		}

		return Collections.emptyList();
	}

	@Override
	public boolean isAllocated() throws DebugException {
		return true;
	}

}
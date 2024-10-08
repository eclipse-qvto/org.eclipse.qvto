/*******************************************************************************
 * Copyright (c) 2022 Christopher Gerking and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     Christopher Gerking - initial API and implementation
 *******************************************************************************/
package org.eclipse.m2m.internal.qvt.oml.project.builder;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.m2m.internal.qvt.oml.project.QVTOProjectPlugin;
import org.eclipse.m2m.internal.qvt.oml.project.QvtProjectUtil;
import org.eclipse.m2m.internal.qvt.oml.runtime.project.ProjectDependencyTracker;

public class WorkspaceDependencyTracker extends ProjectDependencyTracker {

	@Override
	public Set<IProject> getReferencedProjects(IProject project, boolean recursive) {
				
		Set<IProject> result = new HashSet<IProject>();
		Queue<IProject> candidates = new LinkedList<IProject>(Collections.singleton(project));
				
		while (!candidates.isEmpty()) {
			IProject candidate = candidates.poll();	
				
			if (candidate != null && candidate.isAccessible()) {	
				try {
					IProject[] referencedProjects = candidate.getReferencedProjects();
									
					for (IProject referencedProject : referencedProjects) {
						if(!referencedProject.isOpen() || QvtProjectUtil.isQvtProject(referencedProject)) {
							boolean alreadyProcessed = referencedProject == project || !result.add(referencedProject);
							if(recursive && !alreadyProcessed) {
								candidates.offer(referencedProject);
							}
						}					
					}
				}
				catch(CoreException e) {
					QVTOProjectPlugin.log(e.getStatus());
				}
			}
		}
		
		return result;
	}
}

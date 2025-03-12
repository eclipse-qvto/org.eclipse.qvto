/*******************************************************************************
 * Copyright (c) 2009, 2025 Borland Software Corporation and others.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *   
 * Contributors:
 *     Borland Software Corporation - initial API and implementation
 *     Christopher Gerking - issue #1135
 *******************************************************************************/
package org.eclipse.m2m.tests.qvt.oml.compile;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.m2m.tests.qvt.oml.TestProject;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({PlatformPluginUnitResolverTest.class, UnitResolverFactoryTest.class, QVTOCompilerTest.class, URIUnitResolverTest.class})
public class AllCompileTests {
		
	@AfterClass
	public static void tearDownAfterClass() throws CoreException {
		TestProject testProject = TestProject.getExistingProject(UnitResolverFactoryTest.DEPLOYED_PROJECT_NAME);		
		if (testProject != null) testProject.delete();
	}
}

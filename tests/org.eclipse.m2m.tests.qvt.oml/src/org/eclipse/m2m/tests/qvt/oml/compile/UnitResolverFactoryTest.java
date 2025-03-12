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
 *     Christopher Gerking - bugs 394188, 537041, issue #1135
 *******************************************************************************/
package org.eclipse.m2m.tests.qvt.oml.compile;

import java.io.File;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.m2m.internal.qvt.oml.common.MdaException;
import org.eclipse.m2m.internal.qvt.oml.common.io.FileUtil;
import org.eclipse.m2m.internal.qvt.oml.compiler.CompiledUnit;
import org.eclipse.m2m.internal.qvt.oml.compiler.QVTOCompiler;
import org.eclipse.m2m.internal.qvt.oml.compiler.UnitProxy;
import org.eclipse.m2m.internal.qvt.oml.compiler.UnitResolverFactory;
import org.eclipse.m2m.internal.qvt.oml.project.QVTOProjectPlugin;
import org.eclipse.m2m.tests.qvt.oml.TestProject;
import org.eclipse.m2m.tests.qvt.oml.util.TestUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

public class UnitResolverFactoryTest extends TestCase {

	private TestProject myProject;
	static final String DEPLOYED_PROJECT_NAME = "deployed"; //$NON-NLS-1$

	public UnitResolverFactoryTest(String testName) {
		super(testName);
	}

	@Override
	@Before
	protected void setUp() throws Exception {
		String srcFolder = DEPLOYED_PROJECT_NAME;
		myProject = TestProject.getExistingProject(srcFolder);
		if(myProject == null) {
			myProject = new TestProject(srcFolder, new String[] { QVTOProjectPlugin.NATURE_ID }, 0);
			String srcContainer = myProject.getQVTSourceContainer().getProjectRelativePath().toString();
			copyData(srcContainer, srcFolder);
		}
	}

	@Override
	@After
	protected void tearDown() throws Exception {
		File destinationFolder = getDestinationFolder();
		
		//FIXME: folder never exists in this test, so teardown is a no-op instruction
		if (destinationFolder.exists()) {
			FileUtil.delete(destinationFolder);
		}
	}

	@Test
	public void testAccessByWSPath() throws Exception {
		String unitPath = myProject.getProject().getFullPath().append(new Path("/transformations/a/T1.qvto")).toString();
		URI uri = URI.createURI(unitPath, false);
		UnitProxy unit = UnitResolverFactory.Registry.INSTANCE.getUnit(uri);
		assertResolvedCompiledUnit(unit);

		assertEquals("a", unit.getNamespace());
		assertEquals("T1", unit.getName());
		assertEquals(URI.createPlatformResourceURI(unitPath.toString(), false),
				unit.getURI());
		assertNotNull(unit);
	}

	@Test
	public void testAccessByPlatformResourceURI() throws Exception {
		String unitPath = myProject.getProject().getFullPath().append(new Path("/transformations/a/T1.qvto")).toString();
		URI uri = URI.createPlatformResourceURI(unitPath, false);
		UnitProxy unit = UnitResolverFactory.Registry.INSTANCE.getUnit(uri);
		assertResolvedCompiledUnit(unit);

		assertEquals("a", unit.getNamespace());
		assertEquals("T1", unit.getName());
		assertEquals(URI.createPlatformResourceURI(unitPath.toString(), false),
				unit.getURI());
		assertNotNull(unit);
	}

	@Test
	public void testAccessByPlatformPluginURI() throws Exception {
		URI uri = URI.createPlatformPluginURI("/org.eclipse.m2m.tests.qvt.oml/deployed/a/T1.qvto", false);
		UnitProxy unit = UnitResolverFactory.Registry.INSTANCE.getUnit(uri);
		assertResolvedCompiledUnit(unit);

		// 'deployed' part of the namespace as there is no knowledge about source container
		assertEquals("deployed.a", unit.getNamespace());
		assertEquals("T1", unit.getName());
		assertEquals(uri, unit.getURI());
		assertNotNull(unit);
	}

	@Test
	public void testAccessByDeployedIDURI() throws Exception {
		URI uri = URI.createURI("apiTestData.exec3_withImport.exec3_withImport", false); //$NON-NLS-1$
		UnitProxy unit = UnitResolverFactory.Registry.INSTANCE.getUnit(uri);
		assertResolvedCompiledUnit(unit, true);
	}

	@Test
	public void testAccessToUnresolvedURI() throws Exception {
		URI uri = URI.createPlatformPluginURI("/org.eclipse.m2m.tests.qvt.oml/deployed/a/T1_NeverFindMe.qvto", false);
		UnitProxy unit = UnitResolverFactory.Registry.INSTANCE.getUnit(uri);
		assertNull("Must not be resolved", unit); //$NON-NLS-1$
	}


	File getDestinationFolder() {
		return new File(myProject.getProject().getLocation().toString() + "/" + DEPLOYED_PROJECT_NAME); //$NON-NLS-1$
	}

	private void copyData(String destPath, String srcPath) throws Exception {
		File sourceFolder = TestUtil.getPluginRelativeFolder(srcPath);
		File destFolder = new File(myProject.getProject().getLocation().toString() + "/" + destPath); //$NON-NLS-1$
		destFolder.mkdirs();
		FileUtil.copyFolder(sourceFolder, destFolder);
		myProject.getProject().refreshLocal(IResource.DEPTH_INFINITE, null);
	}

	private void assertResolvedCompiledUnit(UnitProxy unit) {
		assertResolvedCompiledUnit(unit, false);
	}

	private void assertResolvedCompiledUnit(UnitProxy unitProxy, boolean errorsOnly) {
		assertNotNull("Unit must be resolved", unitProxy); //$NON-NLS-1$
		QVTOCompiler compiler = new QVTOCompiler();
		try {
			CompiledUnit compiledUnit = compiler.compile(unitProxy, null, (IProgressMonitor)null);
			assertEquals(unitProxy.getName(), compiledUnit.getName());
			if(errorsOnly) {
				assertTrue("Unit must not have compilation errors", compiledUnit.getErrors().isEmpty()); //$NON-NLS-1$
			} else {
				assertTrue("Unit must not have compilation problems", compiledUnit.getProblems().isEmpty()); //$NON-NLS-1$
			}
		} catch (MdaException e) {
			fail(e.getLocalizedMessage());
		}
	}

}

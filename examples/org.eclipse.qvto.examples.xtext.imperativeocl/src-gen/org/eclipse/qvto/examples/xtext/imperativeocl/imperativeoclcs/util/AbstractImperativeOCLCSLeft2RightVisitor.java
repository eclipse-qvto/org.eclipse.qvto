/**
 * <copyright>
 * 
 * </copyright>
 *
 * This code is auto-generated
 * from: src-gen/org/eclipse/qvto/examples/xtext/imperativeocl/ImperativeOCL.genmodel
 * by: org.eclipse.ocl.examples.build.acceleo.GenerateVisitor
 * defined by: org.eclipse.ocl.examples.build.acceleo.generateVisitors.mtl
 * invoked by: org.eclipse.ocl.examples.build.utilities.*
 * from: org.eclipse.ocl.examples.build.*.mwe2
 *
 * Do not edit it.
 *
 * $Id$
 */
package	org.eclipse.qvto.examples.xtext.imperativeocl.imperativeoclcs.util;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.xtext.base.cs2as.CS2PivotConversion;
import org.eclipse.ocl.examples.xtext.essentialocl.cs2as.EssentialOCLLeft2RightVisitor;
import org.eclipse.ocl.examples.pivot.Element;
/**
 * An AbstractImperativeOCLCSLeft2RightVisitor provides a default implementation for each
 * visitXxx method that delegates to the visitYyy method of the first
 * super class, (or transitively its first super class first super class
 * until a non-interface super-class is found). In the absence of any
 * suitable first super class, the method delegates to visiting().
 */
public abstract class AbstractImperativeOCLCSLeft2RightVisitor
	extends EssentialOCLLeft2RightVisitor
	implements ImperativeOCLCSVisitor<Element>
{
	/**
	 * Initializes me with an initial value for my result.
	 * 
	 * @param context my initial result value
	 */
	protected AbstractImperativeOCLCSLeft2RightVisitor(@NonNull CS2PivotConversion context) {
		super(context);
	}	

	public @Nullable Element visitDictLiteralExpCS(@NonNull org.eclipse.qvto.examples.xtext.imperativeocl.imperativeoclcs.DictLiteralExpCS csElement) {
		return visitExpCS(csElement);
	}

	public @Nullable Element visitDictLiteralPartCS(@NonNull org.eclipse.qvto.examples.xtext.imperativeocl.imperativeoclcs.DictLiteralPartCS csElement) {
		return visitElementCS(csElement);
	}

	public @Nullable Element visitDictTypeCS(@NonNull org.eclipse.qvto.examples.xtext.imperativeocl.imperativeoclcs.DictTypeCS csElement) {
		return visitTypedRefCS(csElement);
	}

	public @Nullable Element visitListLiteralExpCS(@NonNull org.eclipse.qvto.examples.xtext.imperativeocl.imperativeoclcs.ListLiteralExpCS csElement) {
		return visitExpCS(csElement);
	}

	public @Nullable Element visitListTypeCS(@NonNull org.eclipse.qvto.examples.xtext.imperativeocl.imperativeoclcs.ListTypeCS csElement) {
		return visitTypedRefCS(csElement);
	}

	public @Nullable Element visitReturnExpCS(@NonNull org.eclipse.qvto.examples.xtext.imperativeocl.imperativeoclcs.ReturnExpCS csElement) {
		return visitExpCS(csElement);
	}
}

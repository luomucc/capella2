//Generated with EGF 1.5.0.v20170706-0846
package org.polarsys.capella.core.edit;

import org.eclipse.egf.common.helper.*;
import java.util.*;
import org.eclipse.emf.ecore.*;
import org.eclipse.egf.model.pattern.*;
import org.eclipse.egf.pattern.execution.*;
import org.eclipse.egf.pattern.query.*;

public class ItemProviderinsert extends org.eclipse.egf.emf.pattern.edit.call.ItemProvider.ItemProviderinsert {
	protected static String nl;

	public static synchronized ItemProviderinsert create(String lineSeparator) {
		nl = lineSeparator;
		ItemProviderinsert result = new ItemProviderinsert();
		nl = null;
		return result;
	}

	public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
	protected final String TEXT_1 = "\t// begin-capella-code" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL
			+ "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
	protected final String TEXT_2 = NL + "\t@Override";
	protected final String TEXT_3 = NL + "\tprotected ";
	protected final String TEXT_4 = " createInitializeCopyCommand(";
	protected final String TEXT_5 = " domain, ";
	protected final String TEXT_6 = " owner, ";
	protected final String TEXT_7 = " helper) {" + NL + "\t\treturn new ";
	protected final String TEXT_8 = "(domain, owner, helper);" + NL + "\t}" + NL + "\t// end-capella-code";
	protected final String TEXT_9 = NL;
	protected final String TEXT_10 = NL;

	public ItemProviderinsert() {
		//Here is the constructor
		StringBuffer stringBuffer = new StringBuffer();

		// add initialisation of the pattern variables (declaration has been already done).

	}

	public String generate(Object argument) throws Exception {
		final StringBuffer stringBuffer = new StringBuffer();

		InternalPatternContext ctx = (InternalPatternContext) argument;
		Map<String, String> queryCtx = null;
		IQuery.ParameterDescription paramDesc = null;
		Node.Container currentNode = ctx.getNode();

		List<Object> genClassList = null;
		//this pattern can only be called by another (i.e. it's not an entry point in execution)
		List<Object> genPackageList = null;
		//this pattern can only be called by another (i.e. it's not an entry point in execution)
		List<Object> genModelList = null;
		//this pattern can only be called by another (i.e. it's not an entry point in execution)
		List<Object> _ListList = null;
		//this pattern can only be called by another (i.e. it's not an entry point in execution)

		for (Object genClassParameter : genClassList) {
			for (Object genPackageParameter : genPackageList) {
				for (Object genModelParameter : genModelList) {
					for (Object _ListParameter : _ListList) {

						this.genClass = (org.eclipse.emf.codegen.ecore.genmodel.GenClass) genClassParameter;
						this.genPackage = (org.eclipse.emf.codegen.ecore.genmodel.GenPackage) genPackageParameter;
						this.genModel = (org.eclipse.emf.codegen.ecore.genmodel.GenModel) genModelParameter;
						this._List = (java.lang.String) _ListParameter;

						if (preCondition(ctx)) {
							ctx.setNode(new Node.Container(currentNode, getClass()));
							orchestration(ctx);
						}

					}
				}
			}
		}
		ctx.setNode(currentNode);
		if (ctx.useReporter()) {
			ctx.getReporter().executionFinished(OutputManager.computeExecutionOutput(ctx), ctx);
		}

		stringBuffer.append(TEXT_9);
		stringBuffer.append(TEXT_10);
		return stringBuffer.toString();
	}

	public String orchestration(PatternContext ctx) throws Exception {
		InternalPatternContext ictx = (InternalPatternContext) ctx;

		super.orchestration(new SuperOrchestrationContext(ictx));

		if (ictx.useReporter()) {
			Map<String, Object> parameterValues = new HashMap<String, Object>();
			parameterValues.put("genClass", this.genClass);
			parameterValues.put("genPackage", this.genPackage);
			parameterValues.put("genModel", this.genModel);
			parameterValues.put("_List", this._List);
			String outputWithCallBack = OutputManager.computeLoopOutput(ictx);
			String loop = OutputManager.computeLoopOutputWithoutCallback(ictx);
			ictx.getReporter().loopFinished(loop, outputWithCallBack, ictx, parameterValues);
		}
		return null;
	}

	public Map<String, Object> getParameters() {
		final Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("genClass", this.genClass);
		parameters.put("genPackage", this.genPackage);
		parameters.put("genModel", this.genModel);
		parameters.put("_List", this._List);
		return parameters;
	}

	protected void method_doGenerate(final StringBuffer stringBuffer, final PatternContext ctx) throws Exception {

		stringBuffer.append(TEXT_1);
		if (genModel.useClassOverrideAnnotation()) {
			stringBuffer.append(TEXT_2);
		}
		stringBuffer.append(TEXT_3);
		stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.command.Command"));
		stringBuffer.append(TEXT_4);
		stringBuffer.append(genModel.getImportedName("org.eclipse.emf.edit.domain.EditingDomain"));
		stringBuffer.append(TEXT_5);
		stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EObject"));
		stringBuffer.append(TEXT_6);
		stringBuffer.append(genModel.getImportedName("org.eclipse.emf.edit.command.CopyCommand.Helper"));
		stringBuffer.append(TEXT_7);
		stringBuffer.append(
				genModel.getImportedName("org.polarsys.capella.common.model.copypaste.SharedInitializeCopyCommand"));
		stringBuffer.append(TEXT_8);
		InternalPatternContext ictx = (InternalPatternContext) ctx;
		new Node.DataLeaf(ictx.getNode(), getClass(), "doGenerate", stringBuffer.toString());
	}
}
//Generated with EGF 1.5.0.v20170706-0846
package org.polarsys.capella.common.tig.model;

import org.eclipse.egf.common.helper.*;
import java.util.*;
import org.eclipse.emf.ecore.*;
import org.eclipse.egf.model.pattern.*;
import org.eclipse.egf.pattern.execution.*;
import org.eclipse.egf.pattern.query.*;

public class ClassgetGenFeatureTODOoverride
		extends org.eclipse.egf.emf.pattern.model.call.Class.ClassgetGenFeatureTODOoverride {
	protected static String nl;

	public static synchronized ClassgetGenFeatureTODOoverride create(String lineSeparator) {
		nl = lineSeparator;
		ClassgetGenFeatureTODOoverride result = new ClassgetGenFeatureTODOoverride();
		nl = null;
		return result;
	}

	public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
	protected final String TEXT_1 = "\t\t";
	protected final String TEXT_2 = " eFeature = ";
	protected final String TEXT_3 = ";" + NL + "\t";
	protected final String TEXT_4 = NL + "\tif (";
	protected final String TEXT_5 = " == null) { ";
	protected final String TEXT_6 = NL + "\t\t";
	protected final String TEXT_7 = " eFeature = (";
	protected final String TEXT_8 = ");" + NL + "\t\t";
	protected final String TEXT_9 = NL + "\t\t";
	protected final String TEXT_10 = " helper = OCL_ENV.createOCLHelper();" + NL + "\t\thelper.setAttributeContext(";
	protected final String TEXT_11 = ", eFeature);" + NL + "\t\t" + NL + "\t\t";
	protected final String TEXT_12 = " ocl = eFeature.getEAnnotation(OCL_ANNOTATION_SOURCE);" + NL
			+ "\t\tString derive = ocl.getDetails().get(\"derive\");";
	protected final String TEXT_13 = NL + NL + "\t\ttry {" + NL + "\t\t\t";
	protected final String TEXT_14 = " = helper.createQuery(derive);" + NL + "\t\t} catch (";
	protected final String TEXT_15 = " e) {" + NL
			+ "\t\t\tthrow new UnsupportedOperationException(e.getLocalizedMessage());" + NL + "\t\t}" + NL + "\t}" + NL
			+ "" + NL + "\t";
	protected final String TEXT_16 = "<";
	protected final String TEXT_17 = ", ?, ?> query = OCL_ENV.createQuery(";
	protected final String TEXT_18 = ");" + NL + "\tObject result = query.evaluate(this);" + NL + "" + NL + "\t";
	protected final String TEXT_19 = NL + "\ttry {" + NL + "\t  @SuppressWarnings(\"unchecked\")" + NL + "\t\t";
	protected final String TEXT_20 = "<";
	protected final String TEXT_21 = "> resultAsList = (";
	protected final String TEXT_22 = "<";
	protected final String TEXT_23 = ">) result;" + NL + "\t\treturn new ";
	protected final String TEXT_24 = ".UnmodifiableEList<";
	protected final String TEXT_25 = ">(this, eFeature, resultAsList.size(), resultAsList.toArray());" + NL
			+ "\t} catch (ClassCastException exception) {" + NL + "\t  exception.printStackTrace();" + NL
			+ "\t  return org.eclipse.emf.common.util.ECollections.emptyEList();" + NL + "\t}" + NL + "\t";
	protected final String TEXT_26 = NL + "\t\treturn ((";
	protected final String TEXT_27 = ") result).";
	protected final String TEXT_28 = "();" + NL + "\t";
	protected final String TEXT_29 = NL + "\ttry {" + NL + "\t\treturn (";
	protected final String TEXT_30 = ") result;" + NL + "\t} catch (ClassCastException exception) {" + NL
			+ "\t  exception.printStackTrace();" + NL + "\t  return null;" + NL + "\t}" + NL + "\t";
	protected final String TEXT_31 = NL + "    Object result = null;" + NL
			+ "    // Helper that can get value for current feature.";
	protected final String TEXT_32 = NL + "    ";
	protected final String TEXT_33 = " helper = null;" + NL
			+ "    // If current object is adaptable, ask it to get its IHelper." + NL + "    if (this instanceof ";
	protected final String TEXT_34 = ") {" + NL
			+ "    \thelper = (IHelper) ((IAdaptable) this).getAdapter(IHelper.class);" + NL + "    }" + NL
			+ "    if (null == helper) {" + NL + "      // No helper found yet." + NL
			+ "      // Ask the platform to get the adapter 'IHelper.class' for current object.";
	protected final String TEXT_35 = NL + "      ";
	protected final String TEXT_36 = " adapterManager = ";
	protected final String TEXT_37 = ".getAdapterManager();" + NL
			+ "      helper = (IHelper) adapterManager.getAdapter(this, IHelper.class);" + NL + "    }" + NL
			+ "    if (null == helper) {";
	protected final String TEXT_38 = NL + "      ";
	protected final String TEXT_39 = " package_l = eClass().getEPackage();" + NL
			+ "      // Get the root package of the owner package." + NL
			+ "      EPackage rootPackage = org.polarsys.capella.common.mdsofa.common.helper.EcoreHelper.getRootPackage(package_l);"
			+ NL
			+ "      throw new org.polarsys.capella.common.model.helpers.HelperNotFoundException(\"No helper retrieved for nsURI \" + rootPackage.getNsURI()); ";
	protected final String TEXT_40 = NL + "    } " + NL + "    // A helper is found, let's use it. ";
	protected final String TEXT_41 = NL + "    ";
	protected final String TEXT_42 = " annotation = ";
	protected final String TEXT_43 = ".getEAnnotation(org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);"
			+ NL + "    result = helper.getValue(this, ";
	protected final String TEXT_44 = ", annotation);" + NL + "\t\t";
	protected final String TEXT_45 = NL + "\t\ttry {" + NL + "\t\t@SuppressWarnings(\"unchecked\")" + NL + "\t\t";
	protected final String TEXT_46 = "<";
	protected final String TEXT_47 = "> resultAsList = (";
	protected final String TEXT_48 = "<";
	protected final String TEXT_49 = ">) result;" + NL + "\t\treturn new ";
	protected final String TEXT_50 = ".UnmodifiableEList<";
	protected final String TEXT_51 = ">(this, ";
	protected final String TEXT_52 = ", resultAsList.size(), resultAsList.toArray());" + NL
			+ "\t\t} catch (ClassCastException exception) {" + NL + "\t  \texception.printStackTrace();" + NL
			+ "\t  \treturn org.eclipse.emf.common.util.ECollections.emptyEList();" + NL + "\t  }" + NL + "\t\t";
	protected final String TEXT_53 = NL + "\t\t\treturn ((";
	protected final String TEXT_54 = ") result).";
	protected final String TEXT_55 = "();" + NL + "\t\t";
	protected final String TEXT_56 = NL + "\t\ttry {" + NL + "\t\t\treturn (";
	protected final String TEXT_57 = ") result;" + NL + "\t  } catch (ClassCastException exception) {" + NL
			+ "\t     exception.printStackTrace();" + NL + "\t    return null;" + NL + "\t  }" + NL + "\t\t";
	protected final String TEXT_58 = NL + "\t\t// TODO: implement this method to return the '";
	protected final String TEXT_59 = "' ";
	protected final String TEXT_60 = NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL
			+ "\t\t\t";
	protected final String TEXT_61 = NL
			+ "\t\t// The list is expected to implement org.eclipse.emf.ecore.util.InternalEList and org.eclipse.emf.ecore.EStructuralFeature.Setting"
			+ NL + "\t\t// so it's likely that an appropriate subclass of org.eclipse.emf.ecore.util.";
	protected final String TEXT_62 = "EcoreEMap";
	protected final String TEXT_63 = "BasicFeatureMap";
	protected final String TEXT_64 = "EcoreEList";
	protected final String TEXT_65 = " should be used." + NL + "\t\t\t";
	protected final String TEXT_66 = NL + "\t\tthrow new UnsupportedOperationException();";
	protected final String TEXT_67 = NL + "\t\t// TODO: implement this method to return the '";
	protected final String TEXT_68 = "' ";
	protected final String TEXT_69 = NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL
			+ "\t\t\t";
	protected final String TEXT_70 = NL
			+ "\t\t// The list is expected to implement org.eclipse.emf.ecore.util.InternalEList and org.eclipse.emf.ecore.EStructuralFeature.Setting"
			+ NL + "\t\t// so it's likely that an appropriate subclass of org.eclipse.emf.ecore.util.";
	protected final String TEXT_71 = "EcoreEMap";
	protected final String TEXT_72 = "BasicFeatureMap";
	protected final String TEXT_73 = "EcoreEList";
	protected final String TEXT_74 = " should be used." + NL + "\t\t\t";
	protected final String TEXT_75 = NL + "\t\tthrow new UnsupportedOperationException();";
	protected final String TEXT_76 = NL;
	protected final String TEXT_77 = NL;

	public ClassgetGenFeatureTODOoverride() {
		//Here is the constructor
		StringBuffer stringBuffer = new StringBuffer();

		// add initialisation of the pattern variables (declaration has been already done).
		oclNsURI = org.polarsys.capella.common.model.helpers.IModelConstants.OCL_ANNOTATION_SOURCE;

	}

	public String generate(Object argument) throws Exception {
		final StringBuffer stringBuffer = new StringBuffer();

		InternalPatternContext ctx = (InternalPatternContext) argument;
		Map<String, String> queryCtx = null;
		IQuery.ParameterDescription paramDesc = null;
		Node.Container currentNode = ctx.getNode();

		List<Object> genFeatureList = null;
		//this pattern can only be called by another (i.e. it's not an entry point in execution)
		List<Object> genClassList = null;
		//this pattern can only be called by another (i.e. it's not an entry point in execution)
		List<Object> genPackageList = null;
		//this pattern can only be called by another (i.e. it's not an entry point in execution)
		List<Object> genModelList = null;
		//this pattern can only be called by another (i.e. it's not an entry point in execution)
		List<Object> isJDK50List = null;
		//this pattern can only be called by another (i.e. it's not an entry point in execution)
		List<Object> isInterfaceList = null;
		//this pattern can only be called by another (i.e. it's not an entry point in execution)
		List<Object> isImplementationList = null;
		//this pattern can only be called by another (i.e. it's not an entry point in execution)
		List<Object> isGWTList = null;
		//this pattern can only be called by another (i.e. it's not an entry point in execution)
		List<Object> publicStaticFinalFlagList = null;
		//this pattern can only be called by another (i.e. it's not an entry point in execution)
		List<Object> singleWildcardList = null;
		//this pattern can only be called by another (i.e. it's not an entry point in execution)
		List<Object> negativeOffsetCorrectionList = null;
		//this pattern can only be called by another (i.e. it's not an entry point in execution)
		List<Object> positiveOffsetCorrectionList = null;
		//this pattern can only be called by another (i.e. it's not an entry point in execution)
		List<Object> negativeOperationOffsetCorrectionList = null;
		//this pattern can only be called by another (i.e. it's not an entry point in execution)
		List<Object> positiveOperationOffsetCorrectionList = null;
		//this pattern can only be called by another (i.e. it's not an entry point in execution)

		for (Object genFeatureParameter : genFeatureList) {
			for (Object genClassParameter : genClassList) {
				for (Object genPackageParameter : genPackageList) {
					for (Object genModelParameter : genModelList) {
						for (Object isJDK50Parameter : isJDK50List) {
							for (Object isInterfaceParameter : isInterfaceList) {
								for (Object isImplementationParameter : isImplementationList) {
									for (Object isGWTParameter : isGWTList) {
										for (Object publicStaticFinalFlagParameter : publicStaticFinalFlagList) {
											for (Object singleWildcardParameter : singleWildcardList) {
												for (Object negativeOffsetCorrectionParameter : negativeOffsetCorrectionList) {
													for (Object positiveOffsetCorrectionParameter : positiveOffsetCorrectionList) {
														for (Object negativeOperationOffsetCorrectionParameter : negativeOperationOffsetCorrectionList) {
															for (Object positiveOperationOffsetCorrectionParameter : positiveOperationOffsetCorrectionList) {

																this.genFeature = (org.eclipse.emf.codegen.ecore.genmodel.GenFeature) genFeatureParameter;
																this.genClass = (org.eclipse.emf.codegen.ecore.genmodel.GenClass) genClassParameter;
																this.genPackage = (org.eclipse.emf.codegen.ecore.genmodel.GenPackage) genPackageParameter;
																this.genModel = (org.eclipse.emf.codegen.ecore.genmodel.GenModel) genModelParameter;
																this.isJDK50 = (java.lang.Boolean) isJDK50Parameter;
																this.isInterface = (java.lang.Boolean) isInterfaceParameter;
																this.isImplementation = (java.lang.Boolean) isImplementationParameter;
																this.isGWT = (java.lang.Boolean) isGWTParameter;
																this.publicStaticFinalFlag = (java.lang.String) publicStaticFinalFlagParameter;
																this.singleWildcard = (java.lang.String) singleWildcardParameter;
																this.negativeOffsetCorrection = (java.lang.String) negativeOffsetCorrectionParameter;
																this.positiveOffsetCorrection = (java.lang.String) positiveOffsetCorrectionParameter;
																this.negativeOperationOffsetCorrection = (java.lang.String) negativeOperationOffsetCorrectionParameter;
																this.positiveOperationOffsetCorrection = (java.lang.String) positiveOperationOffsetCorrectionParameter;

																if (preCondition(ctx)) {
																	ctx.setNode(new Node.Container(currentNode,
																			getClass()));
																	orchestration(ctx);
																}

															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		ctx.setNode(currentNode);
		if (ctx.useReporter()) {
			ctx.getReporter().executionFinished(OutputManager.computeExecutionOutput(ctx), ctx);
		}

		stringBuffer.append(TEXT_76);
		stringBuffer.append(TEXT_77);
		return stringBuffer.toString();
	}

	public String orchestration(PatternContext ctx) throws Exception {
		InternalPatternContext ictx = (InternalPatternContext) ctx;

		super.orchestration(new SuperOrchestrationContext(ictx));

		if (ictx.useReporter()) {
			Map<String, Object> parameterValues = new HashMap<String, Object>();
			parameterValues.put("genFeature", this.genFeature);
			parameterValues.put("genClass", this.genClass);
			parameterValues.put("genPackage", this.genPackage);
			parameterValues.put("genModel", this.genModel);
			parameterValues.put("isJDK50", this.isJDK50);
			parameterValues.put("isInterface", this.isInterface);
			parameterValues.put("isImplementation", this.isImplementation);
			parameterValues.put("isGWT", this.isGWT);
			parameterValues.put("publicStaticFinalFlag", this.publicStaticFinalFlag);
			parameterValues.put("singleWildcard", this.singleWildcard);
			parameterValues.put("negativeOffsetCorrection", this.negativeOffsetCorrection);
			parameterValues.put("positiveOffsetCorrection", this.positiveOffsetCorrection);
			parameterValues.put("negativeOperationOffsetCorrection", this.negativeOperationOffsetCorrection);
			parameterValues.put("positiveOperationOffsetCorrection", this.positiveOperationOffsetCorrection);
			String outputWithCallBack = OutputManager.computeLoopOutput(ictx);
			String loop = OutputManager.computeLoopOutputWithoutCallback(ictx);
			ictx.getReporter().loopFinished(loop, outputWithCallBack, ictx, parameterValues);
		}
		return null;
	}

	protected java.lang.String oclNsURI = null;

	public void set_oclNsURI(java.lang.String object) {
		this.oclNsURI = object;
	}

	public Map<String, Object> getParameters() {
		final Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("genFeature", this.genFeature);
		parameters.put("genClass", this.genClass);
		parameters.put("genPackage", this.genPackage);
		parameters.put("genModel", this.genModel);
		parameters.put("isJDK50", this.isJDK50);
		parameters.put("isInterface", this.isInterface);
		parameters.put("isImplementation", this.isImplementation);
		parameters.put("isGWT", this.isGWT);
		parameters.put("publicStaticFinalFlag", this.publicStaticFinalFlag);
		parameters.put("singleWildcard", this.singleWildcard);
		parameters.put("negativeOffsetCorrection", this.negativeOffsetCorrection);
		parameters.put("positiveOffsetCorrection", this.positiveOffsetCorrection);
		parameters.put("negativeOperationOffsetCorrection", this.negativeOperationOffsetCorrection);
		parameters.put("positiveOperationOffsetCorrection", this.positiveOperationOffsetCorrection);
		return parameters;
	}

	protected void method_doGenerate(final StringBuffer stringBuffer, final PatternContext ctx) throws Exception {

		String derive = null;
		EStructuralFeature eFeature = genFeature.getEcoreFeature();
		if (genFeature.isDerived() && genFeature.isVolatile() && !genFeature.isChangeable()
				&& genFeature.getEcoreFeature().isTransient()) { // Check if current feature is a derived property.
			EAnnotation ocl = eFeature.getEAnnotation(oclNsURI);
			if (ocl != null) {
				derive = ocl.getDetails().get("derive");
			}
			if (derive != null) {
				final String expr = genFeature.getSafeName() + "DeriveOCL";
				if (genFeature.isListType()) { // we will need the feature to create the EcoreEList 
					stringBuffer.append(TEXT_1);
					stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EStructuralFeature"));
					stringBuffer.append(TEXT_2);
					stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
					stringBuffer.append(TEXT_3);
				}
				stringBuffer.append(TEXT_4);
				stringBuffer.append(expr);
				stringBuffer.append(TEXT_5);
				if (!genFeature.isListType()) { // declare locally for OCL initialization only 
					stringBuffer.append(TEXT_6);
					stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EStructuralFeature"));
					stringBuffer.append(TEXT_7);
					stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
					stringBuffer.append(TEXT_8);
				}
				stringBuffer.append(TEXT_9);
				stringBuffer.append(genModel.getImportedName("org.eclipse.ocl.ecore.OCL.Helper"));
				stringBuffer.append(TEXT_10);
				stringBuffer.append(genFeature.getGenClass().getQualifiedClassifierAccessor());
				stringBuffer.append(TEXT_11);
				stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EAnnotation"));
				stringBuffer.append(TEXT_12);
				stringBuffer.append(genModel.getNonNLS());
				stringBuffer.append(TEXT_13);
				stringBuffer.append(expr);
				stringBuffer.append(TEXT_14);
				stringBuffer.append(genModel.getImportedName("org.eclipse.ocl.ParserException"));
				stringBuffer.append(TEXT_15);
				stringBuffer.append(genModel.getImportedName("org.eclipse.ocl.Query"));
				stringBuffer.append(TEXT_16);
				stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EClassifier"));
				stringBuffer.append(TEXT_17);
				stringBuffer.append(expr);
				stringBuffer.append(TEXT_18);
				if (genFeature.isListType()) {
					stringBuffer.append(TEXT_19);
					stringBuffer.append(genModel.getImportedName("java.util.Collection"));
					stringBuffer.append(TEXT_20);
					stringBuffer.append(genFeature.getListItemType());
					stringBuffer.append(TEXT_21);
					stringBuffer.append(genModel.getImportedName("java.util.Collection"));
					stringBuffer.append(TEXT_22);
					stringBuffer.append(genFeature.getListItemType());
					stringBuffer.append(TEXT_23);
					stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.EcoreEList"));
					stringBuffer.append(TEXT_24);
					stringBuffer.append(genFeature.getListItemType());
					stringBuffer.append(TEXT_25);
				} else if (genFeature.isPrimitiveType()) {
					stringBuffer.append(TEXT_26);
					stringBuffer.append(genFeature.getObjectType());
					stringBuffer.append(TEXT_27);
					stringBuffer.append(genFeature.getPrimitiveValueFunction());
					stringBuffer.append(TEXT_28);
				} else {
					stringBuffer.append(TEXT_29);
					stringBuffer.append(genFeature.getImportedType());
					stringBuffer.append(TEXT_30);
				}
			} else {
				// Check if the feature has the helper annotation.
				EAnnotation annotationHelper = eFeature.getEAnnotation(
						org.polarsys.capella.common.model.helpers.IModelConstants.HELPER_ANNOTATION_SOURCE);
				if (null != annotationHelper) {
					// Generate The helper framework code.
					stringBuffer.append(TEXT_31);
					stringBuffer.append(TEXT_32);
					stringBuffer.append(genModel.getImportedName("org.polarsys.capella.common.model.helpers.IHelper"));
					stringBuffer.append(TEXT_33);
					stringBuffer.append(genModel.getImportedName("org.eclipse.core.runtime.IAdaptable"));
					stringBuffer.append(TEXT_34);
					stringBuffer.append(TEXT_35);
					stringBuffer.append(genModel.getImportedName("org.eclipse.core.runtime.IAdapterManager"));
					stringBuffer.append(TEXT_36);
					stringBuffer.append(genModel.getImportedName("org.eclipse.core.runtime.Platform"));
					stringBuffer.append(TEXT_37);
					stringBuffer.append(TEXT_38);
					stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EPackage"));
					stringBuffer.append(TEXT_39);
					stringBuffer.append(genModel.getNonNLS());
					stringBuffer.append(TEXT_40);
					stringBuffer.append(TEXT_41);
					stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EAnnotation"));
					stringBuffer.append(TEXT_42);
					stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
					stringBuffer.append(TEXT_43);
					stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
					stringBuffer.append(TEXT_44);
					if (genFeature.isListType()) {
						stringBuffer.append(TEXT_45);
						stringBuffer.append(genModel.getImportedName("java.util.Collection"));
						stringBuffer.append(TEXT_46);
						stringBuffer.append(genFeature.getListItemType());
						stringBuffer.append(TEXT_47);
						stringBuffer.append(genModel.getImportedName("java.util.Collection"));
						stringBuffer.append(TEXT_48);
						stringBuffer.append(genFeature.getListItemType());
						stringBuffer.append(TEXT_49);
						stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.EcoreEList"));
						stringBuffer.append(TEXT_50);
						stringBuffer.append(genFeature.getListItemType());
						stringBuffer.append(TEXT_51);
						stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
						stringBuffer.append(TEXT_52);
					} else if (genFeature.isPrimitiveType()) {
						stringBuffer.append(TEXT_53);
						stringBuffer.append(genFeature.getObjectType());
						stringBuffer.append(TEXT_54);
						stringBuffer.append(genFeature.getPrimitiveValueFunction());
						stringBuffer.append(TEXT_55);
					} else {
						stringBuffer.append(TEXT_56);
						stringBuffer.append(genFeature.getImportedType());
						stringBuffer.append(TEXT_57);
					}
				} else { // End of if (null != annotationHelper)
					stringBuffer.append(TEXT_58);
					stringBuffer.append(genFeature.getFormattedName());
					stringBuffer.append(TEXT_59);
					stringBuffer.append(genFeature.getFeatureKind());
					stringBuffer.append(TEXT_60);
					if (genFeature.isListType()) {
						stringBuffer.append(TEXT_61);
						if (genFeature.isMapType()) {
							stringBuffer.append(TEXT_62);
						} else if (genFeature.isFeatureMapType()) {
							stringBuffer.append(TEXT_63);
						} else {
							stringBuffer.append(TEXT_64);
						}
						stringBuffer.append(TEXT_65);
					}
					stringBuffer.append(TEXT_66);
				} // End of Unsupported exception. 
			} // End of else block where the helper annotation is checked.
		} // End of is a derived property.
		else {
			stringBuffer.append(TEXT_67);
			stringBuffer.append(genFeature.getFormattedName());
			stringBuffer.append(TEXT_68);
			stringBuffer.append(genFeature.getFeatureKind());
			stringBuffer.append(TEXT_69);
			if (genFeature.isListType()) {
				stringBuffer.append(TEXT_70);
				if (genFeature.isMapType()) {
					stringBuffer.append(TEXT_71);
				} else if (genFeature.isFeatureMapType()) {
					stringBuffer.append(TEXT_72);
				} else {
					stringBuffer.append(TEXT_73);
				}
				stringBuffer.append(TEXT_74);
			}
			stringBuffer.append(TEXT_75);
		}
		InternalPatternContext ictx = (InternalPatternContext) ctx;
		new Node.DataLeaf(ictx.getNode(), getClass(), "doGenerate", stringBuffer.toString());
	}
}
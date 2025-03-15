/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.model.ju.model;

import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.core.libraries.model.CapellaModel;
import org.polarsys.capella.test.framework.api.BasicTestCase;

public abstract class MiscModel extends BasicTestCase {

  public static final String PROJECT_MISCMODEL = "794e6786-9355-4ae4-87a6-2734ede475dd";
  public static final String PROJECT_MISCMODEL__PROGRESSSTATUS = "2e2b64ad-e3dd-43bd-a416-ed7fc082ada6";
  public static final String PROJECT_MISCMODEL__PROGRESSSTATUS__DRAFT = "1d5d2f88-22d1-44a5-bfea-768caaf77b55";
  public static final String PROJECT_MISCMODEL__PROGRESSSTATUS__TO_BE_REVIEWED = "471a4980-c0b7-47ba-8e5d-53425b0c7b1f";
  public static final String PROJECT_MISCMODEL__PROGRESSSTATUS__TO_BE_DISCUSSED = "15a4d25d-5c3c-4f5b-9cb6-93c37378c7fd";
  public static final String PROJECT_MISCMODEL__PROGRESSSTATUS__REWORK_NECESSARY = "83e17d3e-960e-43b1-a901-b79fa36b70e3";
  public static final String PROJECT_MISCMODEL__PROGRESSSTATUS__UNDER_REWORK = "4b11612f-396a-4e9f-a442-b7fc3cfbfb18";
  public static final String PROJECT_MISCMODEL__PROGRESSSTATUS__REVIEWED_OK = "c0727184-bc83-4c97-b0ed-861615570ccc";
  public static final String PROJECT_MISCMODEL__PROJECTAPPROACH = "48adfcca-50a9-415c-81f3-ce75b5420dd1";
  public static final String MISCMODEL = "b3f7e50c-ce73-45c0-ac2a-38365b7d0e91";
  public static final String OA = "c7daf8d7-abf1-4ba8-bbc5-6acc83d55cfe";
  public static final String OA__OPERATIONAL_ACTIVITIES = "75261e4b-971c-4964-b4e2-9a312bd6ae50";
  public static final String OA__OPERATIONAL_ACTIVITIES__ROOT_OA = "bdb29a96-066a-4feb-97df-8949a15a69e0";
  public static final String OA__OPERATIONAL_CAPABILITIES = "3756abbb-cad4-440c-b8cc-75361d76c913";
  public static final String OA__INTERFACES = "643f8f34-5333-492d-8a36-f742e4cc0466";
  public static final String OA__DATA = "f579edce-3fa3-4d6b-9540-6dfd16418db3";
  public static final String OA__OPERATIONAL_CONTEXT = "f6daaca6-fee7-401b-b77c-a3880f335fd1";
  public static final String OA__ROLES = "0eae0d46-5721-4476-a3d5-5dff228ec9ae";
  public static final String OA__OPERATIONAL_ENTITIES = "27bd9112-bc96-45f7-8790-122c82880b19";
  public static final String SA = "cfc9925e-c0f5-4b59-9abf-53e6a50cfc93";
  public static final String SA__SYSTEM_FUNCTIONS = "8bfeccd2-6f68-4c4b-9c94-3163f79b695b";
  public static final String SA__ROOT_SF = "c5bc9e6f-5878-4ce3-85ef-61d44ac0175c";
  public static final String SA__ROOT_SF__FC1 = "ce5d3e25-87ec-4cc2-9856-d7f12589af79";
  public static final String SA__ROOT_SF__FC1__FCI_TO_FE1 = "ab1d4f35-35f9-4471-be38-71b21969ed1e";
  public static final String SA__ROOT_SF__FC1__FCI_TO_SF2 = "f13b00dc-afdb-4600-a310-4535586ad818";
  public static final String SA__ROOT_SF__FC1__FCI_TO_SF1 = "baab713c-eb15-4c03-b981-8598b9a1ffda";
  public static final String SA__ROOT_SF__SF1 = "e543b7f1-618e-45a3-b73c-4876f14e72e0";
  public static final String SA__ROOT_SF__SF1__FOP11 = "56c5a094-fab8-4b9f-8478-15cf9e6c02d8";
  public static final String SA__ROOT_SF__SF2 = "326c0742-f560-422e-af2e-ab40186c7484";
  public static final String SA__ROOT_SF__SF2__FIP21 = "488250fb-7633-418c-8c4c-65a87babe303";
  public static final String SA__ROOT_SF__FUNCTION_REALIZATION_TO_ROOT_OPERATIONAL_ACTIVITY = "9937a791-bc8d-498e-b40d-7a6c972a97a2";
  public static final String SA__ROOT_SF__FE1 = "0b057675-ed0e-4a67-829d-2a7d92814fb8";
  public static final String SA__CAPABILITIES = "961d88d5-c338-4849-ae08-91294a130d42";
  public static final String SA__INTERFACES = "b8b67e89-956c-405f-88a0-1099e666a13b";
  public static final String SA__DATA = "b1b618e0-1d7b-4b76-8beb-fbb4beb21f79";
  public static final String SA__DATA__EI1 = "9e0e8188-0b84-49fa-8cd2-0c84cbe5b1f6";
  public static final String SA__SYSTEM_CONTEXT = "12add945-2e5f-44d5-a9ad-57ebd527222c";
  public static final String SA__SYSTEM_CONTEXT__PART_SYSTEM__SYSTEM = "b6cba2a7-2061-4900-975d-1fb8e64cdba6";
  public static final String SA__SYSTEM = "4630572f-7502-4263-a994-89ced76f21a8";
  public static final String SA__SYSTEM__STATEMACHINE_1 = "97900e4c-7b85-408c-96eb-bd3e63ab1d0b";
  public static final String SA__SYSTEM__STATEMACHINE_1__DEFAULT_REGION = "fc3926fb-2c44-4301-a73d-c3a1aaca5ce2";
  public static final String SA__SYSTEM__STATEMACHINE_1__DEFAULT_REGION__MODE_1 = "135aa60d-7795-4a71-a6ce-0af9b2a959a9";
  public static final String SA__SYSTEM__STATEMACHINE_1__DEFAULT_REGION__MODE_1__REGION = "d384b4e3-5acb-4523-ab8e-7b3621d230d6";
  public static final String SA__SYSTEM__STATEMACHINE_1__DEFAULT_REGION__MODE_2 = "a7fde950-553a-4766-b710-31a8a11c7bc4";
  public static final String SA__SYSTEM__STATEMACHINE_1__DEFAULT_REGION__MODE_2__REGION = "65bddce4-6db0-41b2-9f3d-d7e39f38c6a0";
  public static final String SA__SYSTEM__STATEMACHINE_1__DEFAULT_REGION___TO_MODE_2 = "da8600e2-5df6-4ac2-b784-cb70e49c9fa7";
  public static final String SA__SYSTEM__STATEMACHINE_1__DEFAULT_REGION___TO_MODE_2__CONSTRAINT = "be2b76aa-36bb-4243-861a-b968ad8133cb";
  public static final String SA__SYSTEM__STATEMACHINE_1__DEFAULT_REGION___TO_MODE_2__CONSTRAINT__OPAQUE_EXPRESSION = "5dee764a-4907-484e-a910-84d529b11a3f";
  public static final String SA__ACTORS = "0b6d6f19-fae4-4a23-aee3-8ac84d7a1e64";
  public static final String SA__MISSIONS = "35874327-16e7-4055-a9a2-c1c41599234d";
  public static final String SA__OPERATIONAL_ANALYSIS_REALIZATION_TO_OPERATIONAL_ANALYSIS = "6379ce8e-2b11-4ea4-bd2e-325317025e30";
  public static final String LA = "6423fb82-eda3-42be-a0ad-bb121fee03ce";
  public static final String LA__LOGICAL_FUNCTIONS = "36db5f49-cc83-49d1-95d0-69c24f9fa1ef";
  public static final String LA__ROOT_LF = "835edbff-fad4-4cfa-8574-82663347ad7d";
  public static final String LA__ROOT_LF__FUNCTION_REALIZATION_TO_ROOT_SYSTEM_FUNCTION = "c0d2471e-87c5-473a-a134-f8d850be47c2";
  public static final String LA__CAPABILITIES = "1bc6a58f-1544-4ad0-af2c-0f693b173448";
  public static final String LA__INTERFACES = "3a9cc4b5-92d9-41c7-9a74-5ca1fa7a6396";
  public static final String LA__DATA = "d2b56bbb-ee4d-4a6c-b1d2-419f9d57e91c";
  public static final String LA__LOGICAL_CONTEXT = "64c920b7-afe6-4d7f-b993-986ab11b1de1";
  public static final String LA__LOGICAL_CONTEXT__PART_LOGICAL_SYSTEM__LOGICAL_SYSTEM = "7d43c14e-5d52-4902-a1a7-6e6cfe451ac3";
  public static final String LA__LOGICAL_SYSTEM = "030e91dc-441e-4107-b0ea-0b08acaccf13";
  public static final String LA__SYSTEM_REALIZATION_TO_SYSTEM = "46c8ffc6-7028-441d-b48c-f394235c4823";
  public static final String LA__LOGICAL_ACTORS = "7e02489e-3d6b-44c0-954b-a8ddea0b2a5a";
  public static final String LA__SYSTEM_ANALYSIS_REALIZATION_TO_SYSTEM_ANALYSIS = "7a5bf2fa-7b10-4c4f-b993-e0b043c21f6b";
  public static final String PA = "93e72add-98f8-4f01-81d1-66b443275e2c";
  public static final String PA__PHYSICAL_FUNCTIONS = "433ac136-8085-441d-adfb-f1207ef04f80";
  public static final String PA__ROOT_PF = "82809bc0-4858-4da3-a239-7b7fb8532e6b";
  public static final String PA__ROOT_PF__FUNCTION_REALIZATION_TO_ROOT_LOGICAL_FUNCTION = "adfd41b8-41ba-4b21-ad1e-826d1c74f2b3";
  public static final String PA__CAPABILITIES = "69853e79-242a-4b12-b84e-adc088ee68a0";
  public static final String PA__INTERFACES = "9e6a0268-73b3-4d5d-beaa-8c7957883c78";
  public static final String PA__DATA = "4fa87bdb-4d64-4382-a1ff-e5d2b8e0237f";
  public static final String PA__PHYSICAL_CONTEXT = "3dbda056-db8d-40aa-b505-c0aa41c4f683";
  public static final String PA__PHYSICAL_CONTEXT__PART_PHYSICAL_SYSTEM__PHYSICAL_SYSTEM = "bf3c1440-1e14-497b-8327-13634e41af70";
  public static final String PA__PHYSICAL_SYSTEM = "6649e0be-1e19-433c-93df-47a8f8918750";
  public static final String PA__LOGICAL_COMPONENT_REALIZATION_TO_LOGICAL_SYSTEM = "a88f156f-f4b4-48df-9569-89b9312fbfaa";
  public static final String PA__PHYSICAL_ACTORS = "baa584da-3464-4150-b612-1cbb63169482";
  public static final String PA__LOGICAL_ARCHITECTURE_REALIZATION_TO_LOGICAL_ARCHITECTURE = "65800a74-3da3-46d9-ac2e-dd2af59a25d7";
  public static final String EPBS = "e4e1c7e9-9174-4c56-8375-65cecab211a1";
  public static final String EPBS__CAPABILITIES = "5eaa4a48-a354-41b4-bade-9c9958ee275d";
  public static final String EPBS__EPBS_CONTEXT = "cdb07a6b-0e1a-40dc-897a-fb891add8d88";
  public static final String EPBS__EPBS_CONTEXT__PART_SYSTEM__SYSTEM = "e2b66a7a-c705-4450-95f3-b440d01701c0";
  public static final String EPBS__SYSTEMCI_SYSTEM = "3f8ed798-46cd-4dd6-8469-568ecefad211";
  public static final String EPBS__SYSTEMCI_SYSTEM__PHYSICAL_ARTIFACT_REALIZATION_TO_PHYSICAL_SYSTEM = "50318467-6954-4a28-b8db-c440cb9116b1";
  public static final String EPBS__PHYSICAL_ARCHITECTURE_REALIZATION_TO_PHYSICAL_ARCHITECTURE = "74566334-ec61-48a7-9f47-dc7b298454fc";
  public static final String METADATA__QFEJOHNGEEA0DF0HNIJFUA = "null";
  public static final String METADATA__QFEJOHNGEEA0DF0HNIJFUA__VIEWPOINT_REFERENCE__QFFXWHNGEEA0DF0HNIJFUA = "null";

  
  public static final String MSM_DEFAULT_REGION = "[MSM] Default Region";


  public static String SA__SYSTEM__STATEMACHINE_1__DEFAULT_REGION__STATE_TRANSITION__CONSTRAINT = "be2b76aa-36bb-4243-861a-b968ad8133cb"; //$NON-NLS-1$
  public static String SA__SYSTEM__STATEMACHINE_1__DEFAULT_REGION__STATE_TRANSITION = "da8600e2-5df6-4ac2-b784-cb70e49c9fa7"; //$NON-NLS-1$

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("miscmodel");
  }
  
  protected CapellaModel getTestModel() {
    return super.getTestModel(getRequiredTestModels().get(0));
  }
}

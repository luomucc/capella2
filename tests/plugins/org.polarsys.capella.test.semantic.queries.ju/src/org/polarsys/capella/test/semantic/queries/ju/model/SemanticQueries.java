/*******************************************************************************
 * Copyright (c) 2017, 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales Global Services - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.semantic.queries.ju.model;

import java.util.Collections;
import java.util.List;

import org.polarsys.capella.test.semantic.queries.ju.AbstractSemanticQueryTestCase;

public abstract class SemanticQueries extends AbstractSemanticQueryTestCase {

	  public static final String PROJECT_SEMANTICQUERIES = "2cd4b848-b363-455f-a7c8-c5eb4079e32b";
	  public static final String PROJECT_SEMANTICQUERIES__LIBRARY_DEPENDENCIES = "786f4d49-5c42-4da5-8658-e506a110adf7";
	  public static final String PROJECT_SEMANTICQUERIES__PROGRESSSTATUS = "affce18f-848a-4676-9dd8-2fce1229f298";
	  public static final String PROJECT_SEMANTICQUERIES__PROGRESSSTATUS__DRAFT = "b6c14a40-2292-4932-97b8-b35f28391921";
	  public static final String PROJECT_SEMANTICQUERIES__PROGRESSSTATUS__TO_BE_REVIEWED = "53a46b3b-df5e-4285-9dbb-638724ba0712";
	  public static final String PROJECT_SEMANTICQUERIES__PROGRESSSTATUS__TO_BE_DISCUSSED = "9b8fdc1b-c98b-4891-8529-3c7ec6250dbf";
	  public static final String PROJECT_SEMANTICQUERIES__PROGRESSSTATUS__REWORK_NECESSARY = "dec07b11-9e00-4d00-8483-2e545b785496";
	  public static final String PROJECT_SEMANTICQUERIES__PROGRESSSTATUS__UNDER_REWORK = "2b7450bf-5b17-41a8-b718-1aa001a5122b";
	  public static final String PROJECT_SEMANTICQUERIES__PROGRESSSTATUS__REVIEWED_OK = "988c915c-67a5-4a80-96b6-374d67786afc";
	  public static final String PROJECT_SEMANTICQUERIES__PROJECTAPPROACH = "f03b3c96-464f-4e8c-875f-17046467b633";
	  public static final String SEMANTICQUERIES = "0252eb34-5a60-489b-83e5-d3c0d8ad9dcb";
	  public static final String OA = "8b52725c-19a4-43e0-8888-646a83f7b368";
	  public static final String OA__OPERATIONAL_ACTIVITIES = "c85abfa3-705f-42ac-8e06-315d4e635c81";
	  public static final String OA__OPERATIONAL_ACTIVITIES__ROOT_OA = "1ae5afea-602e-4197-bf87-29921a3923a6";
	  public static final String OA__OPERATIONAL_ACTIVITIES__ROOT_OA__MOTHER_ALLOC_ENTITY_OK = "4e089874-3256-4251-8ec0-3a85889b2da7";
	  public static final String OA__OPERATIONAL_ACTIVITIES__ROOT_OA__MOTHER_ALLOC_ENTITY_OK__SUBFN0 = "44d19186-a7fc-4d56-8035-01ff67aad4ce";
	  public static final String OA__OPERATIONAL_ACTIVITIES__ROOT_OA__MOTHER_ALLOC_ACTOR_OK = "2ea6c67e-b48a-46b8-988d-e03511af02a5";
	  public static final String OA__OPERATIONAL_ACTIVITIES__ROOT_OA__MOTHER_ALLOC_ACTOR_OK__SUBFN1 = "c1e4169a-258d-4040-a1ab-1bf6f4c2a9f7";
	  public static final String OA__OPERATIONAL_ACTIVITIES__ROOT_OA__MOTHER_CHILD_ENTITY_OK = "6bf76ceb-b01a-4af1-89b3-a21eb7a70342";
	  public static final String OA__OPERATIONAL_ACTIVITIES__ROOT_OA__MOTHER_CHILD_ENTITY_OK__OPERATIONALACTIVITY_3 = "d4b8468a-a713-46e9-b1bf-1e72c170cca1";
	  public static final String OA__OPERATIONAL_ACTIVITIES__ROOT_OA__MOTHER_CHILD_ENTITY_OK__OPERATIONALACTIVITY_4 = "0170ea97-e861-4377-8968-4d62c4b3a851";
	  public static final String OA__OPERATIONAL_ACTIVITIES__ROOT_OA__MOTHER_CHILD_ACTOR_OK = "86c4494e-2e87-4d82-afb0-30e03b50b08b";
	  public static final String OA__OPERATIONAL_ACTIVITIES__ROOT_OA__MOTHER_CHILD_ACTOR_OK__OPERATIONALACTIVITY_1 = "c510dc29-b091-445f-b8e7-57247bf5c033";
	  public static final String OA__OPERATIONAL_ACTIVITIES__ROOT_OA__MOTHER_CHILD_ACTOR_OK__OPERATIONALACTIVITY_2 = "51f3eff1-c6a0-443c-a6f7-371e330b4b16";
	  public static final String OA__OPERATIONAL_ACTIVITIES__ROOT_OA__MOTHER_ROLE_ACTOR_OK = "46130d45-9094-4966-a0ec-63421962b55f";
	  public static final String OA__OPERATIONAL_ACTIVITIES__ROOT_OA__MOTHER_ROLE_ACTOR_OK__OPERATIONALACTIVITY_5 = "39283dd3-b3df-447d-838b-755bcf9b76a9";
	  public static final String OA__OPERATIONAL_ACTIVITIES__ROOT_OA__MOTHER_ROLE_ACTOR_OK__OPERATIONALACTIVITY_6 = "c23afe02-5082-45d0-9d93-6da76b2db16f";
	  public static final String OA__OPERATIONAL_ACTIVITIES__ROOT_OA__MOTHER_ROLE_ENTITY_OK = "7b2be3a3-72e5-4fbc-b83c-d91bb92f0a5a";
	  public static final String OA__OPERATIONAL_ACTIVITIES__ROOT_OA__MOTHER_ROLE_ENTITY_OK__OPERATIONALACTIVITY_7 = "d3097f37-0b6d-426f-998a-4a9bea539a24";
	  public static final String OA__OPERATIONAL_ACTIVITIES__ROOT_OA__MOTHER_ROLE_ENTITY_OK__OPERATIONALACTIVITY_8 = "c1e06b35-5e2b-4be4-9bda-fb33becb7cf0";
	  public static final String OA__OPERATIONAL_CAPABILITIES = "eb25fbf6-e305-4e9f-8b84-921a07eb385e";
	  public static final String OA__INTERFACES = "47884688-6299-4e92-a91e-1e408833ad3d";
	  public static final String OA__DATA = "08e5291e-06d7-4aff-bf68-c21a91d075dc";
	  public static final String OA__OPERATIONAL_CONTEXT = "d792c8dd-91c8-451d-8646-f897cad97092";
	  public static final String OA__OPERATIONAL_CONTEXT__PART_ENTITY_1__ENTITY_1 = "a51895ed-a204-4009-8000-2d9ea8d9b5f6";
	  public static final String OA__OPERATIONAL_CONTEXT__PART_OPERATIONALACTOR_2__OPERATIONALACTOR_2 = "3622fbfd-ce01-4d7d-8086-14c1add10533";
	  public static final String OA__OPERATIONAL_CONTEXT__PART_ENTITY_3__ENTITY_3 = "b9d9ca03-303e-4c53-bebf-ae28644dd9f8";
	  public static final String OA__OPERATIONAL_CONTEXT__PART_OPERATIONALACTOR_4__OPERATIONALACTOR_4 = "7fdfc945-7dd9-4ed9-a2e3-276a0bc1bc3c";
	  public static final String OA__OPERATIONAL_CONTEXT__PART_ENTITY_5__ENTITY_5 = "41be5166-05b6-45b4-9c89-520c7e91bbd9";
	  public static final String OA__OPERATIONAL_CONTEXT__PART_OPERATIONALACTOR_6__OPERATIONALACTOR_6 = "d7657913-43e4-451f-82a0-5421e5c29809";
	  public static final String OA__ROLES = "2a0cd293-7315-48ed-85af-57c72ed5b105";
	  public static final String OA__ROLES__ROLE_1 = "828e21b6-3046-4367-b30c-7ac521f0487f";
	  public static final String OA__ROLES__ROLE_1__ACTIVITY_ALLOCATION_TO_OPERATIONALACTIVITY_7 = "b66a65bb-470f-425c-b95c-a25323f178d5";
	  public static final String OA__ROLES__ROLE_1__ACTIVITY_ALLOCATION_TO_OPERATIONALACTIVITY_8 = "552299f8-0a6d-4d78-a3ce-4af5760d8c8a";
	  public static final String OA__ROLES__ROLE_2 = "7111cf53-ac4e-406f-9ed5-9e45571c4835";
	  public static final String OA__ROLES__ROLE_2__ACTIVITY_ALLOCATION_TO_OPERATIONALACTIVITY_5 = "41d6bca7-8c77-4521-a3f0-3969d73b697d";
	  public static final String OA__ROLES__ROLE_2__ACTIVITY_ALLOCATION_TO_OPERATIONALACTIVITY_6 = "ab7831b9-5d2d-4fae-8b52-dde1a47125c3";
	  public static final String OA__OPERATIONAL_ENTITIES = "9c1c3e07-e7b8-4009-9ac1-524d23716d9f";
	  public static final String OA__OPERATIONAL_ENTITIES__ENTITY_1 = "28bfea18-a5fe-4c0d-86fb-085c7e764213";
	  public static final String OA__OPERATIONAL_ENTITIES__ENTITY_1__COMPONENT_FUNCTIONAL_ALLOCATION_TO_OPERATIONALACTIVITY_3 = "d2ee55ae-3990-4158-a8ef-6734c1fac6a3";
	  public static final String OA__OPERATIONAL_ENTITIES__ENTITY_1__COMPONENT_FUNCTIONAL_ALLOCATION_TO_OPERATIONALACTIVITY_4 = "882d5996-6b1d-40d7-b889-7609cf9b7e47";
	  public static final String OA__OPERATIONAL_ENTITIES__OPERATIONALACTOR_2 = "a25487a4-7b09-4706-83e6-f5a7b3720f39";
	  public static final String OA__OPERATIONAL_ENTITIES__OPERATIONALACTOR_2__COMPONENT_FUNCTIONAL_ALLOCATION_TO_OPERATIONALACTIVITY_1 = "fc41064e-5dc9-4108-b69f-3c1d6c23cd25";
	  public static final String OA__OPERATIONAL_ENTITIES__OPERATIONALACTOR_2__COMPONENT_FUNCTIONAL_ALLOCATION_TO_OPERATIONALACTIVITY_2 = "9ac9df25-891d-497d-8f38-65e3216cfe41";
	  public static final String OA__OPERATIONAL_ENTITIES__ENTITY_3 = "b6a0c402-e2f7-4dcf-b752-cc55bbc97042";
	  public static final String OA__OPERATIONAL_ENTITIES__ENTITY_3__ROLE_ALLOCATION_TO_ROLE_1 = "e62269cc-a106-4465-aade-389960407675";
	  public static final String OA__OPERATIONAL_ENTITIES__OPERATIONALACTOR_4 = "df8a3d2f-ee48-4b88-9089-6a7ebf1b44af";
	  public static final String OA__OPERATIONAL_ENTITIES__OPERATIONALACTOR_4__ROLE_ALLOCATION_TO_ROLE_2 = "e0254984-fefb-487a-9963-f1b8561cde89";
	  public static final String OA__OPERATIONAL_ENTITIES__ENTITY_5 = "c57bfbba-81bb-45bd-8076-459fa1ef48cb";
	  public static final String OA__OPERATIONAL_ENTITIES__ENTITY_5__COMPONENT_FUNCTIONAL_ALLOCATION_TO_MOTHER_ALLOC_ENTITY_OK = "816139d6-3917-48f0-8823-1c04c45f6649";
	  public static final String OA__OPERATIONAL_ENTITIES__OPERATIONALACTOR_6 = "3f98ced8-07bb-43bc-829a-07819a53debe";
	  public static final String OA__OPERATIONAL_ENTITIES__OPERATIONALACTOR_6__COMPONENT_FUNCTIONAL_ALLOCATION_TO_MOTHER_ALLOC_ACTOR_OK = "d9a0587f-0884-4696-bade-1e6f78a302e7";
	  public static final String SA = "32788d6f-df93-4444-ab9d-11a2b030227b";
	  public static final String SA__SYSTEM_FUNCTIONS = "16b7ff10-e490-4ecc-8e45-95cc46bb1e71";
	  public static final String SA__ROOT_SF = "564457a4-ab94-47ab-a8c8-fb27b9c1c15f";
	  public static final String SA__ROOT_SF__MOTHER_ALLOC_ACTOR_OK = "9546f7c2-3064-4cc2-ae67-1d0ef585c4b6";
	  public static final String SA__ROOT_SF__MOTHER_ALLOC_ACTOR_OK__FIP_1 = "eb1b60be-1323-4c25-9459-87892a4be2cb";
	  public static final String SA__ROOT_SF__MOTHER_ALLOC_ACTOR_OK__CHILD0 = "25127e1b-a464-4eb8-bd5e-8f9eec67a6e3";
	  public static final String SA__ROOT_SF__MOTHER_ACTOR_OK = "37d63daa-4674-46eb-958c-8990f79d10e0";
	  public static final String SA__ROOT_SF__MOTHER_ACTOR_OK__SYSTEMFUNCTION_1 = "cc2fb20c-1854-47ef-bd59-55803d2b8ebb";
	  public static final String SA__ROOT_SF__MOTHER_ACTOR_OK__SYSTEMFUNCTION_2 = "5bc30f27-7fd2-4305-ba53-cfd5907674a8";
	  public static final String SA__ROOT_SF__MOTHER_ALLOC_KO = "743d917c-a3c4-4e0c-834b-45be68077353";
	  public static final String SA__ROOT_SF__MOTHER_ALLOC_KO__SYSTEMFUNCTION_3 = "42528b2a-3779-4a0a-a1ce-e4cad13a48b8";
	  public static final String SA__ROOT_SF__MOTHER_ALLOC_KO__SYSTEMFUNCTION_3__FOP_1 = "99cf6d93-2e84-4ebf-8e96-aed7ebc6328b";
	  public static final String SA__ROOT_SF__MOTHER_ALLOC_KO__SYSTEMFUNCTION_4 = "52c75828-f8f4-4ae1-9256-f3c44270f3aa";
	  public static final String SA__ROOT_SF__FUNCTION_REALIZATION_TO_ROOT_OPERATIONAL_ACTIVITY = "54d60ce3-91f0-4c7f-87fd-c7cdc5033de1";
	  public static final String SA__ROOT_SF__FUNCTIONALEXCHANGE_1 = "3d9cd590-fc65-4519-b683-b20b85662218";
	  public static final String SA__CAPABILITIES = "055865bd-7993-4d80-adc5-819088a2b53b";
	  public static final String SA__INTERFACES = "fff68970-e2db-43e0-8e6f-ac7893f2fc05";
	  public static final String SA__DATA = "407931fd-f2a9-4a2d-8112-ce17622b59c3";
	  public static final String SA__DATA__PREDEFINED_TYPES = "5865f3d3-9549-4774-a468-5e94fd2c47f5";
	  public static final String SA__DATA__PREDEFINED_TYPES__BOOLEAN = "0b7b759b-5501-4131-b0f3-b3b6cc44b592";
	  public static final String SA__DATA__PREDEFINED_TYPES__BOOLEAN__LITERALS_TRUE = "ca69e128-9ce4-4b9d-ac78-b3c63036ea99";
	  public static final String SA__DATA__PREDEFINED_TYPES__BOOLEAN__LITERALS_FALSE = "3ee96985-ffe3-4532-a508-d8cd2f7b30ab";
	  public static final String SA__DATA__PREDEFINED_TYPES__BYTE = "d2d1a821-183e-40fb-910d-282946ba5475";
	  public static final String SA__DATA__PREDEFINED_TYPES__BYTE__MIN = "8562f520-d4a2-4c1e-b324-9407b0310132";
	  public static final String SA__DATA__PREDEFINED_TYPES__BYTE__MAX = "3aacaba2-b023-49d5-931b-06e65f6f9cd8";
	  public static final String SA__DATA__PREDEFINED_TYPES__CHAR = "30e37b05-6e39-42ba-8480-fa63c4b0e908";
	  public static final String SA__DATA__PREDEFINED_TYPES__CHAR__MINLENGTH = "3e34d965-28ac-4217-bc08-0bfb485b8cdf";
	  public static final String SA__DATA__PREDEFINED_TYPES__CHAR__MAXLENGTH = "eccd3181-1ae6-49db-b153-818b9e0141de";
	  public static final String SA__DATA__PREDEFINED_TYPES__DOUBLE = "042d2208-00d6-4931-8579-760f70efebf7";
	  public static final String SA__DATA__PREDEFINED_TYPES__FLOAT = "25c8805a-19f9-41a8-9717-68c78214a0db";
	  public static final String SA__DATA__PREDEFINED_TYPES__HEXADECIMAL = "4e6bb59f-e3f2-4549-93fe-52473fb83cdd";
	  public static final String SA__DATA__PREDEFINED_TYPES__HEXADECIMAL__MIN = "6056b7f0-34d2-4281-a609-b9747d9ee7cb";
	  public static final String SA__DATA__PREDEFINED_TYPES__HEXADECIMAL__MAX = "cacd1a01-4cf6-4101-8f7c-dee6c30036ee";
	  public static final String SA__DATA__PREDEFINED_TYPES__HEXADECIMAL__MAX__LEFTOPERAND = "d7f018dc-d40d-4f0d-ad06-ad164f1c1b06";
	  public static final String SA__DATA__PREDEFINED_TYPES__HEXADECIMAL__MAX__LEFTOPERAND__LEFTOPERAND = "08f828ef-dad6-46ed-9952-3f0b871966b1";
	  public static final String SA__DATA__PREDEFINED_TYPES__HEXADECIMAL__MAX__LEFTOPERAND__RIGHTOPERAND = "f87e5a4b-1225-4732-9b26-1ff7fa662ca3";
	  public static final String SA__DATA__PREDEFINED_TYPES__HEXADECIMAL__MAX__RIGHTOPERAND = "11a1cdce-c406-427a-a862-f97edb2761db";
	  public static final String SA__DATA__PREDEFINED_TYPES__INTEGER = "5b6bb609-fd7f-4401-b162-97b28298156a";
	  public static final String SA__DATA__PREDEFINED_TYPES__LONG = "f45a2bbc-41ae-45a1-9131-00e842a09663";
	  public static final String SA__DATA__PREDEFINED_TYPES__LONGLONG = "390e6c5c-4de9-4c04-aa60-cf95bd26965e";
	  public static final String SA__DATA__PREDEFINED_TYPES__SHORT = "a8bdb1b3-26b1-45f3-b52c-6c11a3cced95";
	  public static final String SA__DATA__PREDEFINED_TYPES__STRING = "8b2046e7-bade-4ef9-a69f-439021a6ef8e";
	  public static final String SA__DATA__PREDEFINED_TYPES__UNSIGNEDINTEGER = "6e11a98b-9f21-40f7-b215-487b3c1fcc1c";
	  public static final String SA__DATA__PREDEFINED_TYPES__UNSIGNEDINTEGER__MIN = "be584abb-2599-48bd-8df1-28fba74c92cc";
	  public static final String SA__DATA__PREDEFINED_TYPES__UNSIGNEDSHORT = "2cff3669-2727-4b7d-9adf-158146bdca10";
	  public static final String SA__DATA__PREDEFINED_TYPES__UNSIGNEDSHORT__MIN = "dbb5774c-2e5a-44c1-a16c-0a3ebde3d36b";
	  public static final String SA__DATA__PREDEFINED_TYPES__UNSIGNEDLONG = "569d90d5-4987-4668-a51e-660553b9709e";
	  public static final String SA__DATA__PREDEFINED_TYPES__UNSIGNEDLONG__MIN = "c66382e2-6c7b-470e-bfa8-a910bafe738e";
	  public static final String SA__DATA__PREDEFINED_TYPES__UNSIGNEDLONGLONG = "357c4eb3-8b5c-4f09-9434-581c72370f75";
	  public static final String SA__DATA__PREDEFINED_TYPES__UNSIGNEDLONGLONG__MIN = "7ac76e6c-2fbf-4384-8734-f3efc090cb79";
	  public static final String SA__SYSTEM_CONTEXT = "566ba375-cf2b-4eca-8f5e-b00e83a2ee53";
	  public static final String SA__SYSTEM_CONTEXT__PART_SYSTEM__SYSTEM = "6c73b240-71ee-40ee-a214-2bb963c8ab20";
	  public static final String SA__SYSTEM_CONTEXT__PART_A_1__A_1 = "768706bf-3776-43e0-a074-360140f7479e";
	  public static final String SA__SYSTEM_CONTEXT__PART_A_2__A_2 = "bc9d82c9-2980-4363-a551-2cc3f69daf0b";
	  public static final String SA__SYSTEM_CONTEXT__PART_A_3__A_3 = "7bfdf852-8a5b-4851-9980-00272830f425";
	  public static final String SA__SYSTEM_CONTEXT__PART_A_4__A_4 = "f9b13604-a565-49f8-a085-3221be047137";
	  public static final String SA__SYSTEM = "a3422c3a-1806-478a-9e52-f6d604d32b76";
	  public static final String SA__SYSTEM__SYSTEM_STATE_MACHINE = "3c66569f-203b-4dde-b75e-0e6b6ccf37a5";
	  public static final String SA__SYSTEM__SYSTEM_STATE_MACHINE__DEFAULT_REGION = "7e346ce2-5dc0-4bdd-8d03-4cc2892fe2a2";
	  public static final String SA__SYSTEM__SYSTEM_STATE_MACHINE__DEFAULT_REGION__STATE_1 = "66148fc9-1ddb-42bb-b2b0-cce827fdbf31";  //$NON-NLS-1$ 
	  public static final String SA__SYSTEM__SYSTEM_STATE_MACHINE__DEFAULT_REGION__STATE_1__REGION = "e196af96-0421-44a6-8b8d-88f469a568ce";  //$NON-NLS-1$ 
	  public static final String SA__SYSTEM__SYSTEM_STATE_MACHINE__DEFAULT_REGION__STATE_1__REGION_2 = "80ee32b6-f87d-4e34-abd5-c23651b5b96e";  //$NON-NLS-1$ 
	  public static final String SA__SYSTEM__SYSTEM_STATE_MACHINE__DEFAULT_REGION__STATE_1__REGION_2__ENTRYPOINT_1 = "69f74e41-228b-4229-b1fb-9191ebbf3444";  //$NON-NLS-1$ 
	  public static final String SA__SYSTEM__SYSTEM_STATE_MACHINE__DEFAULT_REGION__STATE_1__REGION_2__EXITPOINT_2 = "c459c5af-1acf-4710-a52d-d57b776c8cba";  //$NON-NLS-1$ 
	  public static final String SA__ACTORS = "62a37131-ca9b-427d-9eab-5436788fc8c4";
	  public static final String SA__ACTORS__A_1 = "830600e9-7509-49ee-8665-49015d0939b8";
	  public static final String SA__ACTORS__A_1__COMPONENT_FUNCTIONAL_ALLOCATION_TO_SYSTEMFUNCTION_1 = "6689f25d-8ca1-41cb-8467-0320fcf14f3d";
	  public static final String SA__ACTORS__A_1__COMPONENT_FUNCTIONAL_ALLOCATION_TO_SYSTEMFUNCTION_2 = "e7e98d2d-4440-4547-9dcf-71862a82e2b9";
	  public static final String SA__ACTORS__A_2 = "177734a0-58ea-4f2a-b420-583b7362b7c5";
	  public static final String SA__ACTORS__A_2__COMPONENT_FUNCTIONAL_ALLOCATION_TO_MOTHER_ALLOC_ACTOR_OK = "b55dba36-3570-4b71-b142-2b984723d98d";
	  public static final String SA__ACTORS__A_3 = "f4906242-e0a9-425a-b5df-654d347a016f";
	  public static final String SA__ACTORS__A_3__COMPONENT_FUNCTIONAL_ALLOCATION_TO_SYSTEMFUNCTION_3 = "4b84873d-f233-4f9d-8601-8b09f6ce160f";
	  public static final String SA__ACTORS__A_4 = "b11afa98-fe25-4641-b79a-92e90b3bca35";
	  public static final String SA__ACTORS__A_4__COMPONENT_FUNCTIONAL_ALLOCATION_TO_SYSTEMFUNCTION_4 = "462004c9-1605-41f4-b5b3-e62681d5069e";
	  public static final String SA__MISSIONS = "faf7bc77-7a68-4320-aa32-4e493dafb111";
	  public static final String SA__OPERATIONAL_ANALYSIS_REALIZATION_TO_OPERATIONAL_ANALYSIS = "db814ced-aef8-4ce0-a36a-71e5ec66bec7";
	  public static final String LA = "01328aff-38df-48ea-bddf-bf388b7e0bcb";
	  public static final String LA__PROPERTYVALUEPKG = "aee81c11-9865-4ca3-80ed-843f48c36586";
	  public static final String LA__PROPERTYVALUEPKG__BOOLEANPROPERTYVALUE = "6a119245-9324-4e6f-b380-76dcf22c9539";
	  public static final String LA__PROPERTYVALUEPKG__FLOATPROPERTYVALUE = "40b0f93b-6724-4da2-9bc8-48c4eb680585";
	  public static final String LA__PROPERTYVALUEPKG__PROPERTYVALUEGROUP = "287b862e-cfd3-492c-ad2d-b654d3152ddf";
	  public static final String LA__PROPERTYVALUEPKG__PROPERTYVALUEGROUP__STRINGPROPERTYVALUE_= "43e50bcb-5de3-4c59-919f-070015a68cf7";
	  public static final String LA__LOGICAL_FUNCTIONS = "7b1116f0-775f-4fb7-b190-e3020a4b8f55";
	  public static final String LA__ROOT_LF = "e6a86efd-1b45-416c-80e7-1e424788b0ec";
	  public static final String LA__ROOT_LF__LOGICALFUNCTION_1 = "3cb38702-d351-48a7-8e7f-27700bc83ff8";
	  public static final String LA__ROOT_LF__FUNCTION_REALIZATION_TO_ROOT_SYSTEM_FUNCTION = "e133ade4-c80f-421f-8fd2-a8a8e0fa6fa5";
	  public static final String LA__CAPABILITIES = "7f696210-08b4-492f-b9f4-26912aa5b5fd";
	  public static final String LA__INTERFACES = "afab6515-dea4-409b-9df4-78c073828d8d";
	  public static final String LA__DATA = "342b0406-5ec7-4db0-99b2-7fdadbd66d35";
	  public static final String LA__LOGICAL_CONTEXT = "00550f8b-a639-4384-afa6-522d3d9cfd64";
	  public static final String LA__LOGICAL_CONTEXT__PART_LOGICAL_SYSTEM__LOGICAL_SYSTEM = "873d5d36-8c0f-40ec-9db3-b1354dde3242";
	  public static final String LA__LOGICAL_CONTEXT__PART_LA_1__LA_1 = "492dbcf4-a890-41de-83f5-20062111d3aa";
	  public static final String LA__LOGICAL_SYSTEM = "096703b9-355f-4722-ac4b-e98d582fe114";
	  public static final String LA__SYSTEM_REALIZATION_TO_SYSTEM = "853e4c61-801a-48c9-891b-65a62f14f515";
	  public static final String LA__LOGICAL_ACTORS = "faff0481-1105-49e9-9daf-9f6df45ddae2";
	  public static final String LA__LOGICAL_ACTORS__LA_1 = "56afef9c-56e7-450c-912d-b9811275bf26";
	  public static final String LA__LOGICAL_ACTORS__LA_1__COMPONENT_FUNCTIONAL_ALLOCATION_TO_LOGICALFUNCTION_1 = "164051ff-7008-49fc-8ba0-286a23b8dae2";
	  public static final String LA__SYSTEM_ANALYSIS_REALIZATION_TO_SYSTEM_ANALYSIS = "198d7956-01f9-4acb-a7af-85be5958c0e3";
	  public static final String PA = "8f178984-e6cf-46de-870f-05dcdbaa0025";
	  public static final String PA__PHYSICAL_FUNCTIONS = "31d0ecd3-6548-4a0a-b86c-85b43638d4e9";
	  public static final String PA__ROOT_PF = "c2f9b98e-b412-48ac-9f85-024d3309db0f";
	  public static final String PA__ROOT_PF__FUNCTION_REALIZATION_TO_ROOT_LOGICAL_FUNCTION = "9d100c93-58b0-4be2-9251-d6aa829e7b85";
	  public static final String PA__CAPABILITIES = "8ed4e297-7f45-44fd-a68d-dff446ee79fa";
	  public static final String PA__INTERFACES = "ab24555d-7a3d-4a74-a406-048fa525355f";
	  public static final String PA__DATA = "fa0fafdf-fd21-491e-bf42-b4ea82f7570c";
	  public static final String PA__PHYSICAL_CONTEXT = "3e9310e4-a221-48be-95f6-f6735eb0b60e";
	  public static final String PA__PHYSICAL_CONTEXT__PART_PHYSICAL_SYSTEM__PHYSICAL_SYSTEM = "095b689f-42ae-4ac0-8757-a965fed981e0";
	  public static final String PA__PHYSICAL_SYSTEM = "9960e91c-faa7-4a99-8734-bd53ec6253da";
	  public static final String PA__LOGICAL_COMPONENT_REALIZATION_TO_LOGICAL_SYSTEM = "a1164362-8bdc-4519-be56-2cd0246bac58";
	  public static final String PA__PHYSICAL_ACTORS = "b9e0b16a-e088-4737-b427-dea0f3f46b81";
	  public static final String PA__LOGICAL_ARCHITECTURE_REALIZATION_TO_LOGICAL_ARCHITECTURE = "559504e6-1991-4eef-822f-e2348a55ab42";
	  public static final String EPBS = "1729bd4c-a4d2-48c2-ac5e-cf84172ae391";
	  public static final String EPBS__CAPABILITIES = "51981734-92ce-4076-8ef2-702a1097fc2b";
	  public static final String EPBS__EPBS_CONTEXT = "41f9648e-967b-4046-a54e-3fdbd6d7892e";
	  public static final String EPBS__EPBS_CONTEXT__PART_SYSTEM__SYSTEM = "b3b3f9c2-076f-49d4-88bd-18b45d0e021f";
	  public static final String EPBS__SYSTEMCI_SYSTEM = "70388259-e554-4623-b6c9-0c14852f9665";
	  public static final String EPBS__SYSTEMCI_SYSTEM__PHYSICAL_ARTIFACT_REALIZATION_TO_PHYSICAL_SYSTEM = "6ca9d1b5-e19f-4cc5-b8a2-3db8490aa223";
	  public static final String EPBS__PHYSICAL_ARCHITECTURE_REALIZATION_TO_PHYSICAL_ARCHITECTURE = "001e1adc-f9cb-4852-bcde-41a9670a51e0";
	  public static final String SA__CAPABILITIES__CAPABILITY_1 = "c25c6355-166e-4d53-ba1d-627b047d7f9b";  //$NON-NLS-1$ 
	  public static final String LA__ROOT_LF__FUNCTIONALCHAIN_1 = "776631b3-a5f6-4163-af86-4c4a11e2b2c0";  //$NON-NLS-1$ 
	  public static final String LA__ROOT_LF__LOGICALFUNCTION_2 = "b87a57f7-c590-4d10-8604-c233669ebcc7";  //$NON-NLS-1$ 
	  public static final String LA__CAPABILITIES__CAPABILITY_1 = "aed377e4-51e3-46ff-9efd-21172936af86";  //$NON-NLS-1$ 
	  public static final String LA__CAPABILITIES__CAPABILITY_1__FUNCTIONALCHAIN_1 = "437fc9bc-63f7-43fe-99f3-17e82f84b793";  //$NON-NLS-1$ 
	  public static final String PA__CAPABILITIES__CAPABILITY_1 = "a51c707d-0591-43e7-8d87-3b49882745d9";  //$NON-NLS-1$ 
	  public static final String LA__LOGICALCOMPONENT_1 = "e3c12d76-4afd-442e-96c5-d8b8be8cb0d9";  //$NON-NLS-1$ 
	  public static final String LA__LOGICAL_ACTORS__LA_2 = "37af6f3b-2a72-494f-928d-8ce375e1e957";  //$NON-NLS-1$ 
	  public static final String LA__INTERFACES__EI_LA_01 = "9dd91b9b-5880-4f25-a889-cd3998134f36";  //$NON-NLS-1$
	  public static final String SA__INTERFACES__EI_SA_01 = "4491adad-bad5-43cb-b37b-c285dccc2602";  //$NON-NLS-1$
	  public static final String SA__DATA__EI_SA_02 = "3581a5f9-10de-419e-b52c-0de9588d4941";  //$NON-NLS-1$
	  public static final String PA__INTERFACES__EI_PA_01 = "705e2d94-f38e-4ea6-bfad-a9d143a72da0";  //$NON-NLS-1$
	  public static final String PA__DATA__EI_PA_02 = "1bd1d591-0bea-4631-9dd9-30330ed53a5b";  //$NON-NLS-1$
	  
	@Override
	public List<String> getRequiredTestModels() {
		return Collections.singletonList("SemanticQueries");
	}
}

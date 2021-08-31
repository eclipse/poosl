/*******************************************************************************
 * Copyright (c) 2021 TNO/ESI
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    TNO/ESI - initial API and implementation
 *    Obeo - refactoring
 *******************************************************************************/
package org.eclipse.poosl.xtext.helpers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.poosl.AssignmentExpression;
import org.eclipse.poosl.ClusterClass;
import org.eclipse.poosl.DataClass;
import org.eclipse.poosl.DataMethod;
import org.eclipse.poosl.Declaration;
import org.eclipse.poosl.Instance;
import org.eclipse.poosl.MessageParameter;
import org.eclipse.poosl.NewExpression;
import org.eclipse.poosl.OutputVariable;
import org.eclipse.poosl.Poosl;
import org.eclipse.poosl.PortReference;
import org.eclipse.poosl.ProcessClass;
import org.eclipse.poosl.ProcessMethodCall;
import org.eclipse.poosl.ReceiveStatement;
import org.eclipse.poosl.SendStatement;
import org.eclipse.poosl.Variable;
import org.eclipse.poosl.VariableExpression;
import org.eclipse.poosl.xtext.custom.PooslCache;
import org.eclipse.poosl.xtext.custom.PooslCacheEntry;
import org.eclipse.poosl.xtext.custom.PooslMessageType;
import org.eclipse.poosl.xtext.descriptions.PooslDataClassDescription;
import org.eclipse.poosl.xtext.descriptions.PooslProcessClassDescription;
import org.eclipse.poosl.xtext.importing.ImportingHelper;
import org.eclipse.poosl.xtext.importing.PooslImportUriGlobalScopeProvider;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.resource.IResourceServiceProvider;
import org.eclipse.xtext.scoping.IGlobalScopeProvider;
import org.eclipse.xtext.scoping.IScope;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * The HelperFunctions.
 * 
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 *
 */
public final class HelperFunctions {

    // === Special data classes =======

    public static final String CLASS_NAME_OBJECT = "Object"; //$NON-NLS-1$

    public static final String CLASS_NAME_ARRAY = "Array"; //$NON-NLS-1$

    public static final String CLASS_NAME_BOOLEAN = "Boolean"; //$NON-NLS-1$

    public static final String CLASS_NAME_CHAR = "Char"; //$NON-NLS-1$

    public static final String CLASS_NAME_FLOAT = "Float"; //$NON-NLS-1$

    public static final String CLASS_NAME_INTEGER = "Integer"; //$NON-NLS-1$

    public static final String CLASS_NAME_NIL = "Nil"; //$NON-NLS-1$

    public static final String CLASS_NAME_REAL = "Real"; //$NON-NLS-1$

    public static final String CLASS_NAME_STRING = "String"; //$NON-NLS-1$

    // default basic classes
    public static final String CLASS_NAME_FILEIN = "FileIn"; //$NON-NLS-1$

    public static final String CLASS_NAME_FILEOUT = "FileOut"; //$NON-NLS-1$

    public static final String CLASS_NAME_RANDOMGENERATOR = "RandomGenerator"; //$NON-NLS-1$

    public static final String CLASS_NAME_SOCKET = "Socket"; //$NON-NLS-1$

    public static final String CLASS_NAME_CONSOLE = "Console"; //$NON-NLS-1$

    public static final String CLASS_NAME_OBSERVER = "Observer"; //$NON-NLS-1$

    public static final List<String> PRIMITIVE_DATA_CLASSES = Collections
            .unmodifiableList(Arrays.asList(CLASS_NAME_BOOLEAN, CLASS_NAME_CHAR, CLASS_NAME_FLOAT, CLASS_NAME_INTEGER, CLASS_NAME_NIL, CLASS_NAME_REAL));

    public static final List<String> PERMANENT_DATA_CLASSES = Collections.unmodifiableList(
            Arrays.asList(CLASS_NAME_OBJECT, CLASS_NAME_ARRAY, CLASS_NAME_BOOLEAN, CLASS_NAME_CHAR, CLASS_NAME_FLOAT, CLASS_NAME_INTEGER, CLASS_NAME_NIL, CLASS_NAME_REAL, CLASS_NAME_STRING));

    public static final List<String> DEFAULT_DATA_CLASSES = Collections
            .unmodifiableList(Arrays.asList(CLASS_NAME_OBJECT, CLASS_NAME_ARRAY, CLASS_NAME_BOOLEAN, CLASS_NAME_CHAR, CLASS_NAME_FLOAT, CLASS_NAME_INTEGER, CLASS_NAME_NIL, CLASS_NAME_REAL,
                    CLASS_NAME_STRING, CLASS_NAME_FILEIN, CLASS_NAME_FILEOUT, CLASS_NAME_RANDOMGENERATOR, CLASS_NAME_SOCKET, CLASS_NAME_CONSOLE, CLASS_NAME_OBSERVER));

    // === GetName =======

    private static final Function<IEObjectDescription, String> GET_NAME = new Function<IEObjectDescription, String>() {
        @Override
        public String apply(IEObjectDescription descr) {
            return getName(descr);
        }
    };

    // === IGlobalScopeProvider =======

    private static PooslImportUriGlobalScopeProvider globalScopeProvider;

    private HelperFunctions() {
        throw new IllegalStateException("Utility class");
    }

    // === IGlobalScopeProvider =======

    // Getting the IGlobalScopeProvider from a new injector will create a new
    // additional provider. Injectors should only be created ones, in this
    // instance xtext creates the injector.
    // http://koehnlein.blogspot.nl/2012/11/xtext-tip-how-do-i-get-guice-injector.html
    private static PooslImportUriGlobalScopeProvider getGlobalScopeProvider(Resource resource) {
        if (globalScopeProvider == null) {
            // FIXME This static cache is dangerous
            globalScopeProvider = (PooslImportUriGlobalScopeProvider) IResourceServiceProvider.Registry.INSTANCE.getResourceServiceProvider(resource.getURI()).get(IGlobalScopeProvider.class);
        }
        return globalScopeProvider;
    }

    public static IScope getGlobalScope(Resource resource, EReference reference, Predicate<IEObjectDescription> filter) {
        return getGlobalScopeProvider(resource).getScope(resource, reference, filter);
    }

    public static IScope getGlobalScope(Resource resource, EClass eClass, Predicate<IEObjectDescription> filter) {
        return getGlobalScopeProvider(resource).getScope(resource, eClass, filter);
    }

    // === Special data classes =======

    // === GetName =======

    public static Iterable<String> getNames(Iterable<IEObjectDescription> descriptions) {
        return Iterables.transform(descriptions, GET_NAME);
    }

    public static String getName(IEObjectDescription descr) {
        return descr.getName().getFirstSegment();
    }

    public static QualifiedName toQualifiedNameName(String name) {
        return QualifiedName.create(name);
    }

    // === GetContainer =======

    public static Poosl getContainingPoosl(EObject obj) {
        return ImportingHelper.toPoosl(obj.eResource());
    }

    public static DataClass getContainingDataClass(EObject object) {
        return EcoreUtil2.getContainerOfType(object, DataClass.class);
    }

    public static ProcessClass getContainingProcessClass(EObject object) {
        return EcoreUtil2.getContainerOfType(object, ProcessClass.class);
    }

    // === InstantiableClasses =======

    public static ClusterClass getSystem(Poosl model) {
        for (ClusterClass clusterClass : model.getClusterClasses()) {
            if (clusterClass.getName() == null) {
                return clusterClass;
            }
        }
        return null;
    }

    public static boolean isClusterInstance(Instance instance) {
        Resource resource = instance.eResource();
        String className = instance.getClassDefinition();
        return PooslCache.get(resource).getClusterClass(className) != null;
    }

    public static IEObjectDescription getInstantiableClass(Resource resource, String cClassName) {
        PooslCacheEntry pooslCacheEntry = PooslCache.get(resource);
        IEObjectDescription result = pooslCacheEntry.getProcessClass(cClassName);
        if (result != null) {
            return result;
        }
        return pooslCacheEntry.getClusterClass(cClassName);
    }

    public static Iterable<IEObjectDescription> getAllRelevantInstantiableClasses(Resource resource) {
        PooslCacheEntry pooslCacheEntry = PooslCache.get(resource);
        return Iterables.concat(pooslCacheEntry.getAllRelevantProcessClasses(), pooslCacheEntry.getAllRelevantClusterClasses());
    }

    // === CorrectedDataClassExtends =======

    public static String getCorrectedDataClassExtendsAsString(IEObjectDescription dClass) {
        String className = getName(dClass);
        if (CLASS_NAME_OBJECT.equals(className)) {
            return null;
        } else {
            String superClass = PooslDataClassDescription.getSuperClass(dClass);
            if (superClass != null) {
                return superClass;
            } else {
                return CLASS_NAME_OBJECT;
            }
        }
    }

    public static String getCorrectedDataClassExtendsAsString(Resource resource, String className) {
        if (CLASS_NAME_OBJECT.equals(className)) {
            return null;
        } else {
            IEObjectDescription dClass = PooslCache.get(resource).getDataClassMap().get(className);
            String superClass = PooslDataClassDescription.getSuperClass(dClass);
            if (superClass != null) {
                return superClass;
            } else {
                return CLASS_NAME_OBJECT;
            }
        }
    }

    public static String getCorrectedDataClassExtendsAsString(DataClass dClass) {
        if (CLASS_NAME_OBJECT.equals(dClass.getName())) {
            return null;
        } else {
            String superClass = dClass.getSuperClass();
            if (superClass != null) {
                return superClass;
            } else {
                return CLASS_NAME_OBJECT;
            }
        }
    }

    // === DataClass =======

    public static Iterable<String> computeDataSuperClasses(Resource resource, String dClass) {
        return getNames(PooslCache.get(resource).getDataAncestors(dClass));
    }

    public static List<String> computeDataClassChain(Resource resource, String dClass) {
        List<String> superClasses = Lists.newArrayList(computeDataSuperClasses(resource, dClass));
        superClasses.add(0, dClass);
        return superClasses;
    }

    public static boolean isReflexiveAncestorData(Resource resource, String ancestor, String dClass) {
        return ancestor.equals(dClass) || Iterables.contains(computeDataSuperClasses(resource, dClass), ancestor);
    }

    public static boolean isReflexiveAncestorData(DataClass ancestor, DataClass dClass) {
        Resource resource = ancestor.eResource();
        return isReflexiveAncestorData(resource, ancestor.getName(), dClass.getName());
    }

    // === ProcessClass =======

    public static Iterable<String> computeProcessSuperClasses(Resource resource, String processName) {
        return getNames(PooslCache.get(resource).getProcessAncestors(processName));
    }

    public static List<String> computeProcessClassChain(Resource resource, String pClass) {
        List<String> superClasses = Lists.newArrayList(computeProcessSuperClasses(resource, pClass));
        superClasses.add(0, pClass);
        return superClasses;
    }

    public static boolean isReflexiveAncestorProcess(Resource resource, String ancestor, String pClass) {
        return ancestor.equals(pClass) || Iterables.contains(computeProcessSuperClasses(resource, pClass), ancestor);
    }

    public static boolean isReflexiveAncestorProcess(ProcessClass ancestor, ProcessClass pClass) {
        Resource resource = ancestor.eResource();
        return isReflexiveAncestorProcess(resource, ancestor.getName(), pClass.getName());
    }

    // === GetUsed =======

    public static Set<String> getUsedMessages(ProcessClass pClass, PooslMessageType messageType) {
        Resource resource = pClass.eResource();
        Set<String> usedMessages = new HashSet<>();
        List<IEObjectDescription> pClasses = PooslCache.get(resource).getProcessReflexiveChildren(pClass.getName());
        for (IEObjectDescription pDescr : pClasses) {
            List<String> pMessages = messageType.equals(PooslMessageType.RECEIVE) ? PooslProcessClassDescription.getUsedReceiveStatements(pDescr)
                    : PooslProcessClassDescription.getUsedSendStatements(pDescr);
            usedMessages.addAll(pMessages);
        }
        return usedMessages;
    }

    public static Set<URI> getLocalUsedURIs(Resource resource) {
        Set<URI> localURIs = new HashSet<>();
        String uri = resource.getURI().toString();
        HelperFunctions.getLocalUsedURIs(localURIs, ImportingHelper.toPoosl(resource));
        for (Iterator<URI> iterator = localURIs.iterator(); iterator.hasNext(); /**/) {
            if (!iterator.next().toString().startsWith(uri)) {
                iterator.remove();
            }
        }
        return localURIs;
    }

    private static void getLocalUsedURIs(final Set<URI> usedURIs, final EObject sourceCandidate) {

        addPooslReference(sourceCandidate.eResource(), sourceCandidate, usedURIs);

        for (EReference ref : sourceCandidate.eClass().getEAllReferences()) {

            if (sourceCandidate.eIsSet(ref)) {
                if (ref.isContainment()) {
                    Object content = sourceCandidate.eGet(ref, false);

                    if (ref.isMany()) {
                        @SuppressWarnings("unchecked")
                        InternalEList<EObject> contentList = (InternalEList<EObject>) content;
                        for (int i = 0; i < contentList.size(); ++i) {
                            EObject childElement = contentList.basicGet(i);
                            if (!childElement.eIsProxy()) {
                                getLocalUsedURIs(usedURIs, childElement);
                            }
                        }
                    } else {
                        EObject childElement = (EObject) content;
                        if (!childElement.eIsProxy()) {
                            getLocalUsedURIs(usedURIs, childElement);
                        }
                    }

                } else if (!ref.isContainer()) {
                    Object value = sourceCandidate.eGet(ref, false);
                    if (ref.isMany()) {
                        @SuppressWarnings("unchecked")
                        InternalEList<EObject> values = (InternalEList<EObject>) value;
                        for (int i = 0; i < values.size(); ++i) {
                            EObject instanceOrProxy = toValidInstanceOrNull(sourceCandidate.eResource(), values.basicGet(i));
                            if (instanceOrProxy != null) {
                                URI refURI = EcoreUtil2.getPlatformResourceOrNormalizedURI(instanceOrProxy);
                                usedURIs.add(refURI);
                            }
                        }
                    } else {
                        EObject instanceOrProxy = toValidInstanceOrNull(sourceCandidate.eResource(), (EObject) value);
                        if (instanceOrProxy != null) {
                            URI refURI = EcoreUtil2.getPlatformResourceOrNormalizedURI(instanceOrProxy);
                            usedURIs.add(refURI);
                        }
                    }
                }
            }
        }
    }

    // TOOD code duplication with PooslReferenceFinder?

    private static void addPooslReference(Resource localResource, EObject sourceCandidate, Set<URI> usedURIs) {

        // --- Message References -------

        if (sourceCandidate instanceof SendStatement) {
            SendStatement statement = (SendStatement) sourceCandidate;
            validateAndAccept(localResource, usedURIs, PooslReferenceHelper.getSendSignatureDescription(statement));
        } else if (sourceCandidate instanceof ReceiveStatement) {
            ReceiveStatement statement = (ReceiveStatement) sourceCandidate;
            validateAndAccept(localResource, usedURIs, PooslReferenceHelper.getReceiveSignatureDescription(statement));

            // --- Process Method References -------

        } else if (sourceCandidate instanceof ProcessMethodCall) {
            ProcessMethodCall call = (ProcessMethodCall) sourceCandidate;
            validateAndAccept(localResource, usedURIs, PooslReferenceHelper.getProcessMethodDescription(call));

            // --- Port References -------

        } else if (sourceCandidate instanceof PortReference) {
            PortReference port = (PortReference) sourceCandidate;
            validateAndAccept(localResource, usedURIs, PooslReferenceHelper.getPortDescription(port));

            // --- Class References -------

        } else if (sourceCandidate instanceof ProcessClass) {
            ProcessClass pClass = (ProcessClass) sourceCandidate;
            validateAndAccept(localResource, usedURIs, PooslReferenceHelper.getProcessClassDescription(pClass));
        } else if (sourceCandidate instanceof DataClass) {
            DataClass dClass = (DataClass) sourceCandidate;
            validateAndAccept(localResource, usedURIs, PooslReferenceHelper.getDataClassDescription(dClass));
        } else if (sourceCandidate instanceof Instance) {
            Instance instance = (Instance) sourceCandidate;
            validateAndAccept(localResource, usedURIs, PooslReferenceHelper.getInstantiableClassDescription(instance));
        } else if (sourceCandidate instanceof NewExpression) {
            NewExpression newExpr = (NewExpression) sourceCandidate;
            validateAndAccept(localResource, usedURIs, PooslReferenceHelper.getDataClassDescription(newExpr));
        } else if (sourceCandidate instanceof DataMethod) {
            DataMethod dataMethod = (DataMethod) sourceCandidate;
            validateAndAccept(localResource, usedURIs, PooslReferenceHelper.getDataClassDescription(dataMethod));
        } else if (sourceCandidate instanceof Declaration) {
            Declaration declaration = (Declaration) sourceCandidate;
            validateAndAccept(localResource, usedURIs, PooslReferenceHelper.getDataClassDescription(declaration));
        } else if (sourceCandidate instanceof MessageParameter) {
            MessageParameter msgParam = (MessageParameter) sourceCandidate;
            validateAndAccept(localResource, usedURIs, PooslReferenceHelper.getDataClassDescription(msgParam));

            // --- Variable References -------

        } else if (sourceCandidate instanceof AssignmentExpression) {
            AssignmentExpression assignExpr = (AssignmentExpression) sourceCandidate;
            validateAndAccept(localResource, usedURIs, PooslReferenceHelper.getVariableDescription(assignExpr));
        } else if (sourceCandidate instanceof VariableExpression) {
            VariableExpression varExpr = (VariableExpression) sourceCandidate;
            validateAndAccept(localResource, usedURIs, PooslReferenceHelper.getVariableDescription(varExpr));
        } else if (sourceCandidate instanceof OutputVariable) {
            OutputVariable outVar = (OutputVariable) sourceCandidate;
            validateAndAccept(localResource, usedURIs, PooslReferenceHelper.getVariableDescription(outVar));
        }
        // Instance Parameter does not need to be added because it doesn't
        // determine if the parameter is used inside the class.

    }

    private static void validateAndAccept(Resource localResource, Set<URI> usedURIs, IEObjectDescription descr) {
        if (descr != null) {
            EObject instance = toValidInstanceOrNull(localResource, descr.getEObjectOrProxy());
            if (instance != null) {
                URI refURI = EcoreUtil2.getPlatformResourceOrNormalizedURI(instance);
                usedURIs.add(refURI);
            }
        }
    }

    private static EObject toValidInstanceOrNull(Resource resource, EObject value) {
        return resolveInternalProxy(value, resource);
    }

    private static EObject resolveInternalProxy(EObject elementOrProxy, Resource resource) {
        if (elementOrProxy.eIsProxy() && ((InternalEObject) elementOrProxy).eProxyURI().trimFragment().equals(resource.getURI())) {
            return EcoreUtil.resolve(elementOrProxy, resource);
        } else {
            return elementOrProxy;
        }
    }

    // === Conversions =======

    public static List<Variable> declarationsToVariables(List<Declaration> declarations) {
        List<Variable> variables = new ArrayList<>();
        for (Declaration d : declarations) {
            variables.addAll(d.getVariables());
        }
        return variables;
    }

    public static int computeNumberOfVariables(List<Declaration> declarations) {
        int amount = 0;
        for (Declaration declaration : declarations) {
            amount += declaration.getVariables().size();
        }
        return amount;
    }
}

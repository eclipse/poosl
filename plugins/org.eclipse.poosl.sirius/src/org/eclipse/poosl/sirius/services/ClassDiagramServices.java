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
package org.eclipse.poosl.sirius.services;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.poosl.ClusterClass;
import org.eclipse.poosl.DataClass;
import org.eclipse.poosl.DataMethod;
import org.eclipse.poosl.Declaration;
import org.eclipse.poosl.Instance;
import org.eclipse.poosl.InstantiableClass;
import org.eclipse.poosl.Poosl;
import org.eclipse.poosl.PooslPackage.Literals;
import org.eclipse.poosl.ProcessClass;
import org.eclipse.poosl.ProcessMethod;
import org.eclipse.poosl.Variable;
import org.eclipse.poosl.sirius.helpers.NameHelper;
import org.eclipse.poosl.xtext.custom.FormattingHelper;
import org.eclipse.poosl.xtext.custom.PooslCache;
import org.eclipse.poosl.xtext.helpers.HelperFunctions;
import org.eclipse.poosl.xtext.helpers.PooslReferenceHelper;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNodeList;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.resource.IEObjectDescription;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * This class is used by the poosl.odesign file to have more advanced queries on
 * the model that are called by Acceleo3
 * expressions.
 * 
 * According to sirius documentation this class needs to be stateless and have a
 * constructor without any arguments.
 * There is no guarantee that the same instance will be used when a function is
 * called twice.
 * 
 * @author Koen Staal
 */
@SuppressWarnings("checkstyle:HideUtilityClassConstructor") // not applicable for ODesign Services
public class ClassDiagramServices {

    public String getInstanceNameClassDiagram(Instance instance) {
        StringBuilder name = new StringBuilder();
        FormattingHelper.formatInstance(name, instance);
        return name.toString();
    }

    public String getDataMethodLabel(DataMethod method) {
        StringBuilder name = new StringBuilder();
        FormattingHelper.formatDataMethod(name, method, false, false);
        return name.toString();
    }

    public String getProcessMethodLabel(ProcessMethod method) {
        StringBuilder name = new StringBuilder();
        FormattingHelper.formatProcessMethod(name, method, false, false);
        return name.toString();
    }

    public String getInstantiableDescription(InstantiableClass inst) {
        StringBuilder string = new StringBuilder("-"); //$NON-NLS-1$
        string.append(inst.getName());
        FormattingHelper.formatDeclarations(string, inst.getParameters(), false);
        return string.toString();
    }

    public List<DataClass> getAllDataClasses(final Poosl poosl) {
        return Lists.newArrayList(Iterables.transform(
                PooslCache.cleanGet(poosl.eResource()).getAllRelevantDataClasses(),
                new Function<IEObjectDescription, DataClass>() {
                    @Override
                    public DataClass apply(IEObjectDescription descr) {
                        EObject o = descr.getEObjectOrProxy();
                        if (o.eIsProxy())
                            o = EcoreUtil2.resolve(o, poosl);
                        return (DataClass) o;
                    }
                }));
    }

    public List<ProcessClass> getAllProcessClasses(final Poosl poosl) {
        return Lists.newArrayList(Iterables.transform(
                PooslCache.cleanGet(poosl.eResource()).getAllRelevantProcessClasses(),
                new Function<IEObjectDescription, ProcessClass>() {
                    @Override
                    public ProcessClass apply(IEObjectDescription descr) {
                        EObject o = descr.getEObjectOrProxy();
                        if (o.eIsProxy())
                            o = EcoreUtil2.resolve(o, poosl);
                        return (ProcessClass) o;
                    }
                }));
    }

    public List<ClusterClass> getAllClusterClasses(final Poosl poosl) {
        return Lists.newArrayList(Iterables.transform(
                PooslCache.cleanGet(poosl.eResource()).getAllRelevantClusterClasses(),
                new Function<IEObjectDescription, ClusterClass>() {
                    @Override
                    public ClusterClass apply(IEObjectDescription descr) {
                        EObject o = descr.getEObjectOrProxy();
                        if (o.eIsProxy())
                            o = EcoreUtil2.resolve(o, poosl);
                        return (ClusterClass) o;
                    }
                }));
    }

    public Boolean isImportedClass(EObject object) {
        if (object instanceof DSemanticDecorator) {
            DNodeList node = (DNodeList) object;
            if (node.getParentDiagram() instanceof DSemanticDecorator) {
                DSemanticDecorator diagram = (DSemanticDecorator) node.getParentDiagram();
                return diagram.getTarget().eResource().equals(node.getTarget().eResource());
            }
        }
        return false;
    }

    public Boolean isBasicClass(DataClass dClass) {
        return !HelperFunctions.DEFAULT_DATA_CLASSES.contains(dClass.getName());
    }

    public String getNumberOfInstances(DEdge object) {
        if (object.getTargetNode() instanceof DNodeList) {
            EObject targetN = ((DNodeList) object.getTargetNode()).getTarget();

            if (targetN instanceof InstantiableClass
                    && object.getTarget() instanceof ClusterClass) {
                InstantiableClass target = (InstantiableClass) targetN;
                ClusterClass source = (ClusterClass) object.getTarget();

                int nr = 0;
                for (Instance instance : source.getInstances()) {
                    if (instance.getClassDefinition().equals(target.getName())) {
                        nr++;
                    }
                }
                return String.valueOf(nr);
            }
        }
        return ""; //$NON-NLS-1$
    }

    public List<Variable> getVariables(DataClass dataclass) {
        return HelperFunctions.declarationsToVariables(dataclass.getInstanceVariables());
    }

    public List<Variable> getVariables(ProcessClass processclass) {
        return HelperFunctions.declarationsToVariables(processclass.getInstanceVariables());
    }

    public String getVariableDescription(Variable var) {
        StringBuilder string = new StringBuilder();
        FormattingHelper.formatVariable(string, var, false);
        return string.toString();
    }

    public List<Variable> getParameters(InstantiableClass inst) {
        return HelperFunctions.declarationsToVariables(inst.getParameters());
    }

    public List<EObject> getSystemInstances(ClusterClass arch) {
        List<EObject> list = new ArrayList<>();
        for (Instance instance : arch.getInstances()) {
            list.add(PooslReferenceHelper.getInstantiableClassEObject(instance));
        }
        return list;
    }

    public ClusterClass getSystemSpecification(Poosl poosl) {
        return HelperFunctions.getSystem(poosl);
    }

    public String getClassName(EObject obj) {
        if (obj instanceof ProcessClass) {
            return ((ProcessClass) obj).getName();
        } else if (obj instanceof DataClass) {
            return ((DataClass) obj).getName();
        } else if (obj instanceof ClusterClass) {
            ClusterClass cluster = (ClusterClass) obj;
            return (cluster.getName() != null) ? cluster.getName() : "System";
        }
        return "Undefined";
    }

    public boolean hasVariables(EObject object) {
        return partDataClass(object) || partProcessClass(object);
    }

    public boolean hasParameters(EObject object) {
        if (object instanceof InstantiableClass) {
            return ((InstantiableClass) object).getName() != null;
        } else if (object instanceof ProcessMethod) {
            return true;
        } else if (object instanceof Variable) {
            return object.eContainer().eContainer() instanceof InstantiableClass;
        }
        return false;
    }

    public boolean hasMethods(EObject object) {
        return partDataClass(object) || partProcessClass(object);
    }

    private boolean partDataClass(EObject object) {
        if (object instanceof ProcessClass || object instanceof ProcessMethod) {
            return true;
        } else if (object instanceof Variable) {
            return object.eContainer().eContainer() instanceof ProcessClass;
        }
        return false;
    }

    private boolean partProcessClass(EObject object) {
        if (object instanceof DataClass || object instanceof DataMethod) {
            return true;
        } else if (object instanceof Variable) {
            return object.eContainer().eContainer() instanceof DataClass;
        }
        return false;
    }

    public boolean isMethod(EObject object) {
        return (object instanceof ProcessMethod || object instanceof DataMethod)
                && !AbstractServices.isBundleResource(object);
    }

    public boolean isVariable(EObject object) {
        if (object instanceof Variable) {
            Declaration dec = (Declaration) object.eContainer();
            if (dec.eContainer() instanceof DataClass) {
                return true;
            } else if (dec.eContainer() instanceof ProcessClass) {
                return dec.eContainingFeature() == Literals.PROCESS_CLASS__INSTANCE_VARIABLES;
            }
        }
        return false;
    }

    public boolean isParameter(EObject object) {
        if (object instanceof Variable) {
            Declaration dec = (Declaration) object.eContainer();
            if (dec.eContainer() instanceof ClusterClass) {
                return true;
            } else if (dec.eContainer() instanceof ProcessClass) {
                return dec.eContainingFeature() == Literals.INSTANTIABLE_CLASS__PARAMETERS;
            }
        }
        return false;
    }

    public boolean canInherit(EObject object) {
        if (object instanceof DataClass) {
            return ((DataClass) object).getSuperClass() == null;
        } else if (object instanceof ProcessClass) {
            return ((ProcessClass) object).getSuperClass() == null;
        }
        return false;
    }

    public EObject getInheritance(EObject object) {
        IEObjectDescription description = null;
        if (object instanceof DataClass) {
            description = PooslCache.cleanGet(object.eResource())
                    .getDataClass(((DataClass) object).getSuperClass());
        } else if (object instanceof ProcessClass) {
            description = PooslCache.cleanGet(object.eResource())
                    .getProcessClass(((ProcessClass) object).getSuperClass());
        }
        if (description != null) {
            EObject referencedElement = description.getEObjectOrProxy();
            if (referencedElement.eIsProxy()) {
                referencedElement = EcoreUtil2.resolve(referencedElement, object);
            }
            return referencedElement;
        }
        return null;
    }

    public boolean canCreateSystem(EObject object) {
        if (object instanceof Poosl) {
            return HelperFunctions.getSystem((Poosl) object) == null;
        }
        return false;
    }

    public boolean canBeContained(EObject object) {
        return object instanceof InstantiableClass
                && ((InstantiableClass) object).getName() != null;
    }

    public static String getUniqueClusterName(EObject container, String original) {
        return NameHelper.getUniqueClusterName(AbstractServices.COPYOF + original, container);
    }

    public static String getUniqueProcessName(EObject container, String original) {
        return NameHelper.getUniqueProcessName(AbstractServices.COPYOF + original, container);
    }

    public static String getUniqueDataName(EObject container, String original) {
        return NameHelper.getUniqueDataName(AbstractServices.COPYOF + original, container);
    }
}

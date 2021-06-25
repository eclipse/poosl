package nl.esi.poosl.sirius.helpers;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.texteditor.ITextEditor;
import org.eclipse.xtext.nodemodel.ICompositeNode;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;

import nl.esi.poosl.ClusterClass;
import nl.esi.poosl.DataClass;
import nl.esi.poosl.DataMethodBinaryOperator;
import nl.esi.poosl.DataMethodNamed;
import nl.esi.poosl.DataMethodUnaryOperator;
import nl.esi.poosl.Declaration;
import nl.esi.poosl.Instance;
import nl.esi.poosl.OperatorBinary;
import nl.esi.poosl.OperatorUnary;
import nl.esi.poosl.Poosl;
import nl.esi.poosl.PooslPackage;
import nl.esi.poosl.PooslPackage.Literals;
import nl.esi.poosl.ProcessClass;
import nl.esi.poosl.ProcessMethod;
import nl.esi.poosl.Variable;
import nl.esi.poosl.xtext.helpers.HelperFunctions;
import nl.esi.poosl.xtext.helpers.PooslReferenceHelper;
import nl.esi.poosl.xtext.importing.ImportingHelper;

public class TextualEditorHelper {
	private static final Logger LOGGER = Logger.getLogger(TextualEditorHelper.class.getName());

	private TextualEditorHelper() {
		throw new IllegalStateException("Utility class");
	}

	public static boolean openTextualEditor(EObject sourceObject, boolean selectTarget) {
		return openTextualEditor(sourceObject, selectTarget, false);
	}

	/**
	 * Opens the texteditor and selects the corresponding EObject if needed
	 * 
	 * @param sourceObject The object to be shown in the texteditor
	 * @param selectTarget Set false if you dont want to search and highlight the
	 *                     eobject
	 * @return succeeded
	 */
	public static boolean openTextualEditor(EObject sourceObject, boolean selectTarget, boolean showInstance) {
		EObject correctedSourceObject;
		if (!showInstance && sourceObject instanceof Instance) {
			correctedSourceObject = PooslReferenceHelper.getInstantiableClassEObject((Instance) sourceObject);
		} else {
			correctedSourceObject = sourceObject;
		}

		try {
			IEditorPart openedEditor = getOpenedEditor(correctedSourceObject);
			if (openedEditor != null && selectTarget) {
				EObject freshSourceObject = findFreshEObject(correctedSourceObject);
				if (freshSourceObject instanceof ClusterClass && ((ClusterClass) freshSourceObject).getName() == null) {
					ICompositeNode nod = NodeModelUtils.findActualNodeFor(freshSourceObject);
					if (nod != null && openedEditor instanceof ITextEditor) {
						((ITextEditor) openedEditor).selectAndReveal(nod.getOffset(), 6);
					}
				} else {
					EAttribute feature = getFeatureDescription(freshSourceObject);
					if (feature != null) {
						List<INode> nodes = NodeModelUtils.findNodesForFeature(freshSourceObject, feature);
						if (!nodes.isEmpty() && openedEditor instanceof ITextEditor) {
							((ITextEditor) openedEditor).selectAndReveal(nodes.get(0).getOffset(),
									nodes.get(0).getLength());
						}
					}
				}
				return true;
			}
		} catch (PartInitException e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
		}
		return false;
	}

	private static EObject findFreshEObject(EObject old) {
		if (old instanceof Poosl) {
			Resource originalResource = old.eResource();
			URI uri = originalResource.getURI();

			ResourceSet resourceSet = new ResourceSetImpl();
			resourceSet.getPackageRegistry().put(nl.esi.poosl.PooslPackage.eINSTANCE.getNsURI(),
					nl.esi.poosl.PooslPackage.eINSTANCE);
			Resource resource = resourceSet.getResource(uri, true);

			return ImportingHelper.toPoosl(resource);
		}
		if (old instanceof DataClass) {
			String name = ((DataClass) old).getName();
			if (name != null) {
				Poosl poosl = (Poosl) findFreshEObject(old.eContainer());
				for (DataClass dClass : poosl.getDataClasses()) {
					if (dClass.getName().equals(name)) {
						return dClass;
					}
				}
			}
		}
		if (old instanceof ClusterClass) {
			String name = ((ClusterClass) old).getName();
			if (name != null) {
				Poosl poosl = (Poosl) findFreshEObject(old.eContainer());
				for (ClusterClass cClass : poosl.getClusterClasses()) {
					if (name.equals(cClass.getName())) {
						return cClass;
					}
				}
			} else {
				Poosl poosl = (Poosl) findFreshEObject(old.eContainer());
				return HelperFunctions.getSystem(poosl);
			}
		}
		if (old instanceof ProcessClass) {
			String name = ((ProcessClass) old).getName();
			if (name != null) {
				Poosl poosl = (Poosl) findFreshEObject(old.eContainer());
				for (ProcessClass pClass : poosl.getProcessClasses()) {
					if (name.equals(pClass.getName())) {
						return pClass;
					}
				}
			}
		}
		if (old instanceof ProcessMethod) {
			String name = ((ProcessMethod) old).getName();
			if (name != null) {
				ProcessClass processClass = (ProcessClass) findFreshEObject(old.eContainer());
				int numberOfInputs = HelperFunctions.declarationsToVariables(((ProcessMethod) old).getInputParameters())
						.size();
				int numberOfOutputs = HelperFunctions
						.declarationsToVariables(((ProcessMethod) old).getOutputParameters()).size();
				for (ProcessMethod pMethod : processClass.getMethods()) {
					if (name.equals(pMethod.getName()) && numberOfInputs == pMethod.getInputParameters().size()
							&& numberOfOutputs == pMethod.getOutputParameters().size()) {
						return pMethod;
					}
				}
			}
		}
		if (old instanceof DataMethodNamed) {
			String name = ((DataMethodNamed) old).getName();
			if (name != null) {
				int numberOfInputs = HelperFunctions.declarationsToVariables(((DataMethodNamed) old).getParameters())
						.size();
				DataClass dClass = (DataClass) findFreshEObject(old.eContainer());
				for (DataMethodNamed dMethod : dClass.getDataMethodsNamed()) {
					if (name.equals(dMethod.getName()) && numberOfInputs == dMethod.getParameters().size()) {
						return dMethod;
					}
				}
			}
		} else if (old instanceof DataMethodUnaryOperator) {
			OperatorUnary name = ((DataMethodUnaryOperator) old).getName();
			if (name != null) {
				int numberOfInputs = HelperFunctions.declarationsToVariables(((DataMethodNamed) old).getParameters())
						.size();
				DataClass dClass = (DataClass) findFreshEObject(old.eContainer());
				for (DataMethodUnaryOperator dMethod : dClass.getDataMethodsUnaryOperator()) {
					if (name.equals(dMethod.getName()) && numberOfInputs == dMethod.getParameters().size()) {
						return dMethod;
					}
				}
			}
		} else if (old instanceof DataMethodBinaryOperator) {
			OperatorBinary name = ((DataMethodBinaryOperator) old).getName();
			if (name != null) {
				int numberOfInputs = HelperFunctions.declarationsToVariables(((DataMethodNamed) old).getParameters())
						.size();
				DataClass dClass = (DataClass) findFreshEObject(old.eContainer());
				for (DataMethodBinaryOperator dMethod : dClass.getDataMethodsBinaryOperator()) {
					if (name.equals(dMethod.getName()) && numberOfInputs == dMethod.getParameters().size()) {
						return dMethod;
					}
				}
			}
		}
		if (old instanceof Variable) {
			String name = ((Variable) old).getName();
			if (name != null) {
				Declaration declaration = (Declaration) old.eContainer();
				EObject container = findFreshEObject(declaration.eContainer());
				if (container instanceof DataClass) {
					return findVariable(((DataClass) container).getInstanceVariables(), name);
				} else if (container instanceof ClusterClass) {
					return findVariable(((ClusterClass) container).getParameters(), name);
				} else if (container instanceof ProcessClass) {
					if (declaration.eContainingFeature() == Literals.PROCESS_CLASS__INSTANCE_VARIABLES) {
						return findVariable(((ProcessClass) container).getInstanceVariables(), name);
					} else {
						return findVariable(((ProcessClass) container).getParameters(), name);
					}
				}
				// can't click on variables that are part of methods
			}
		}
		if (old instanceof Instance) {
			String name = ((Instance) old).getName();
			if (name != null) {
				ClusterClass container = (ClusterClass) findFreshEObject(old.eContainer());
				for (Instance instance : container.getInstances()) {
					if (instance.getName().equals(name)) {
						return instance;
					}
				}
			}
		}
		return null;
	}

	private static Variable findVariable(List<Declaration> declarations, String name) {
		for (Declaration declaration : declarations) {
			for (Variable variable : declaration.getVariables()) {
				if (variable.getName().equals(name)) {
					return variable;
				}
			}
		}
		return null;
	}

	/**
	 * Open Text Editor which contains the given object
	 * 
	 * @param object The {@link EObject} to show
	 * @return The editor if available
	 * @throws PartInitException
	 */
	private static IEditorPart getOpenedEditor(EObject object) throws PartInitException {
		Resource sourceResource = object.eResource();

		URI eUri = sourceResource.getURI();
		IEditorPart openedEditor = null;
		if (eUri.isPlatformResource()) {
			String platformString = eUri.toPlatformString(true);
			IFile file = (IFile) ResourcesPlugin.getWorkspace().getRoot().findMember(platformString);

			openedEditor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
					.openEditor(new FileEditorInput(file), "nl.esi.poosl.xtext.Poosl");

		} else {
			Display.getDefault().asyncExec(new Runnable() {
				@Override
				public void run() {
					MessageDialog.openInformation(Display.getDefault().getActiveShell(), "Poosl Editor",
							"Can not open a Poosl editor for a file outside of the workspace.");
				}
			});
		}
		return openedEditor;
	}

	/**
	 * Find the corresponding name feature for the {@link EObject}
	 * 
	 * @param object
	 * @return The name literal of the EObject
	 */
	private static EAttribute getFeatureDescription(EObject object) {
		if (object instanceof ProcessMethod) {
			return PooslPackage.Literals.PROCESS_METHOD__NAME;
		} else if (object instanceof Variable) {
			return PooslPackage.Literals.VARIABLE__NAME;
		} else if (object instanceof DataMethodUnaryOperator) {
			return PooslPackage.Literals.DATA_METHOD_UNARY_OPERATOR__NAME;
		} else if (object instanceof DataMethodBinaryOperator) {
			return PooslPackage.Literals.DATA_METHOD_BINARY_OPERATOR__NAME;
		} else if (object instanceof DataMethodNamed) {
			return PooslPackage.Literals.DATA_METHOD_NAMED__NAME;
		} else {
			return PooslPackage.Literals.INSTANTIABLE_CLASS__NAME;
		}
	}
}

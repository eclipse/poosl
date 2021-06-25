package nl.esi.poosl.xtext.ui;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.ui.editor.hover.html.DefaultEObjectHoverProvider;

import nl.esi.poosl.Annotation;
import nl.esi.poosl.ClusterClass;
import nl.esi.poosl.DataClass;
import nl.esi.poosl.DataMethod;
import nl.esi.poosl.MessageSignature;
import nl.esi.poosl.PooslPackage.Literals;
import nl.esi.poosl.ProcessClass;
import nl.esi.poosl.ProcessMethod;
import nl.esi.poosl.Variable;

public class PooslHoverProvider extends DefaultEObjectHoverProvider {
	public static final String TEST_HELP_DOCUMENTATION = "\nSee Help(F1), \"Syntax of Test Primitives\" for more info.";
	public static final String TEST_DOCUMENTATION = "This annotation indicates that the data method must be executed as test by the test framework. "
			+ "By default the test succeeds if no exception occurs.";
	public static final String SKIP_DOCUMENTATION = "This annotation should be combined with \"@Test\", and is used to skip the test.";
	public static final String ERROR_DOCUMENTATION = "This annotation should be combined with \"@Test\", and modifies the default behavior such that the test succeeds if any exception occurs. "
			+ "A parameter can be added to indicate a specific expected exception, e.g., \"@Error(\"string\")\".";
	public static final String INIT_DOCUMENTATION = "This annotation indicates that the data method is an initialization method. "
			+ "If a data class has initialization methods, one of these initialization methods has to be called directly after creating an instance of the data clas (using the \"new\" construct). "
			+ "The \"@Init\" annotation is not inherited by subclasses."
			+ "\nSee Help(F1), \"Initialization Data Methods\" for more information.";
	public static final String SUPPRESSWARNINGS_DOCUMENTATION = "This annotation hides the warnings of the provided type in the following class, method, instance or channel. "
			+ "The warning types that can be passed are \"unused\", \"unconnected\", \"typecheck\" and \"return\". "
			+ "Multiple types can be provided with one annotation by seperating the types with a comma (e.g. @SuppressWarnings(\"unused\", \"typecheck\")."
			+ "\nSee Help(F1), \"Suppress Warnings\" for more information.";

	@Override
	protected String getFirstLine(EObject o) {
		String objectType;
		if (o instanceof ClusterClass) {
			objectType = "Cluster class";
		} else if (o instanceof DataClass) {
			objectType = "Data class";
		} else if (o instanceof DataMethod) {
			objectType = "Data method";
		} else if (o instanceof MessageSignature) {
			objectType = "Message";
		} else if (o instanceof ProcessClass) {
			objectType = "Process class";
		} else if (o instanceof ProcessMethod) {
			objectType = "Process method";
		} else if (o instanceof Variable
				&& o.eContainer().eContainingFeature() == Literals.DATA_CLASS__INSTANCE_VARIABLES) {
			objectType = "Class variable";
		} else if (o instanceof Variable && o.eContainer().eContainingFeature() == Literals.DATA_METHOD__PARAMETERS) {
			objectType = "Input parameter";
		} else if (o instanceof Variable
				&& o.eContainer().eContainingFeature() == Literals.DATA_METHOD__LOCAL_VARIABLES) {
			objectType = "Local variable";
		} else if (o instanceof Variable
				&& o.eContainer().eContainingFeature() == Literals.INSTANTIABLE_CLASS__PARAMETERS) {
			objectType = "Class parameter";
		} else if (o instanceof Variable
				&& o.eContainer().eContainingFeature() == Literals.PROCESS_CLASS__INSTANCE_VARIABLES) {
			objectType = "Class variable";
		} else if (o instanceof Variable
				&& o.eContainer().eContainingFeature() == Literals.PROCESS_METHOD__INPUT_PARAMETERS) {
			objectType = "Input parameter";
		} else if (o instanceof Variable
				&& o.eContainer().eContainingFeature() == Literals.PROCESS_METHOD__OUTPUT_PARAMETERS) {
			objectType = "Output parameter";
		} else if (o instanceof Variable
				&& o.eContainer().eContainingFeature() == Literals.PROCESS_METHOD__LOCAL_VARIABLES) {
			objectType = "Local variable";
		} else {
			objectType = o.eClass().getName();
		}

		String label = getLabel(o);
		return objectType + ((label != null) ? " <b>" + label + "</b>" : "");
	}

	@Override
	protected String getDocumentation(EObject o) {
		if (o instanceof Annotation) {
			String annotation = ((Annotation) o).getName();
			if (annotation.equalsIgnoreCase("test")) {
				return TEST_DOCUMENTATION + TEST_HELP_DOCUMENTATION;
			} else if (annotation.equalsIgnoreCase("error")) {
				return ERROR_DOCUMENTATION + TEST_HELP_DOCUMENTATION;
			} else if (annotation.equalsIgnoreCase("skip")) {
				return SKIP_DOCUMENTATION + TEST_HELP_DOCUMENTATION;
			} else if (annotation.equalsIgnoreCase("init")) {
				return INIT_DOCUMENTATION;
			} else if (annotation.equalsIgnoreCase("suppresswarnings")) {
				return SUPPRESSWARNINGS_DOCUMENTATION;
			}
		}
		return super.getDocumentation(o);
	}
}

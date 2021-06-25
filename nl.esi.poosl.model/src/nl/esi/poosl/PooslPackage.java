/**
 */
package nl.esi.poosl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see nl.esi.poosl.PooslFactory
 * @model kind="package"
 * @generated
 */
public interface PooslPackage extends EPackage
{
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "poosl";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://poosl.esi.nl/poosl/3.0.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "poosl";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	PooslPackage eINSTANCE = nl.esi.poosl.impl.PooslPackageImpl.init();

	/**
	 * The meta object id for the '{@link nl.esi.poosl.impl.PooslImpl <em>Poosl</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see nl.esi.poosl.impl.PooslImpl
	 * @see nl.esi.poosl.impl.PooslPackageImpl#getPoosl()
	 * @generated
	 */
	int POOSL = 0;

	/**
	 * The feature id for the '<em><b>Imports</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POOSL__IMPORTS = 0;

	/**
	 * The feature id for the '<em><b>Data Classes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POOSL__DATA_CLASSES = 1;

	/**
	 * The feature id for the '<em><b>Process Classes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POOSL__PROCESS_CLASSES = 2;

	/**
	 * The feature id for the '<em><b>Cluster Classes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POOSL__CLUSTER_CLASSES = 3;

	/**
	 * The feature id for the '<em><b>Import Libs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POOSL__IMPORT_LIBS = 4;

	/**
	 * The number of structural features of the '<em>Poosl</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POOSL_FEATURE_COUNT = 5;

	/**
	 * The meta object id for the '{@link nl.esi.poosl.impl.ImportImpl <em>Import</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see nl.esi.poosl.impl.ImportImpl
	 * @see nl.esi.poosl.impl.PooslPackageImpl#getImport()
	 * @generated
	 */
	int IMPORT = 1;

	/**
	 * The feature id for the '<em><b>Import URI</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORT__IMPORT_URI = 0;

	/**
	 * The number of structural features of the '<em>Import</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORT_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link nl.esi.poosl.impl.AnnotableImpl <em>Annotable</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see nl.esi.poosl.impl.AnnotableImpl
	 * @see nl.esi.poosl.impl.PooslPackageImpl#getAnnotable()
	 * @generated
	 */
	int ANNOTABLE = 67;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANNOTABLE__ANNOTATIONS = 0;

	/**
	 * The number of structural features of the '<em>Annotable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANNOTABLE_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link nl.esi.poosl.impl.ChannelImpl <em>Channel</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see nl.esi.poosl.impl.ChannelImpl
	 * @see nl.esi.poosl.impl.PooslPackageImpl#getChannel()
	 * @generated
	 */
	int CHANNEL = 2;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHANNEL__ANNOTATIONS = ANNOTABLE__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>External Port</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHANNEL__EXTERNAL_PORT = ANNOTABLE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Instance Ports</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHANNEL__INSTANCE_PORTS = ANNOTABLE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Channel</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHANNEL_FEATURE_COUNT = ANNOTABLE_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link nl.esi.poosl.impl.InstancePortImpl <em>Instance Port</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see nl.esi.poosl.impl.InstancePortImpl
	 * @see nl.esi.poosl.impl.PooslPackageImpl#getInstancePort()
	 * @generated
	 */
	int INSTANCE_PORT = 3;

	/**
	 * The feature id for the '<em><b>Instance</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTANCE_PORT__INSTANCE = 0;

	/**
	 * The feature id for the '<em><b>Port</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTANCE_PORT__PORT = 1;

	/**
	 * The number of structural features of the '<em>Instance Port</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTANCE_PORT_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link nl.esi.poosl.impl.InstanceImpl <em>Instance</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see nl.esi.poosl.impl.InstanceImpl
	 * @see nl.esi.poosl.impl.PooslPackageImpl#getInstance()
	 * @generated
	 */
	int INSTANCE = 4;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTANCE__ANNOTATIONS = ANNOTABLE__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTANCE__NAME = ANNOTABLE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Instance Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTANCE__INSTANCE_PARAMETERS = ANNOTABLE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Class Definition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTANCE__CLASS_DEFINITION = ANNOTABLE_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Instance</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTANCE_FEATURE_COUNT = ANNOTABLE_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link nl.esi.poosl.impl.InstanceParameterImpl <em>Instance Parameter</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see nl.esi.poosl.impl.InstanceParameterImpl
	 * @see nl.esi.poosl.impl.PooslPackageImpl#getInstanceParameter()
	 * @generated
	 */
	int INSTANCE_PARAMETER = 5;

	/**
	 * The feature id for the '<em><b>Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTANCE_PARAMETER__EXPRESSION = 0;

	/**
	 * The feature id for the '<em><b>Parameter</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTANCE_PARAMETER__PARAMETER = 1;

	/**
	 * The number of structural features of the '<em>Instance Parameter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTANCE_PARAMETER_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link nl.esi.poosl.impl.InstantiableClassImpl <em>Instantiable Class</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see nl.esi.poosl.impl.InstantiableClassImpl
	 * @see nl.esi.poosl.impl.PooslPackageImpl#getInstantiableClass()
	 * @generated
	 */
	int INSTANTIABLE_CLASS = 6;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTANTIABLE_CLASS__ANNOTATIONS = ANNOTABLE__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTANTIABLE_CLASS__NAME = ANNOTABLE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTANTIABLE_CLASS__PARAMETERS = ANNOTABLE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Ports</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTANTIABLE_CLASS__PORTS = ANNOTABLE_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Instantiable Class</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTANTIABLE_CLASS_FEATURE_COUNT = ANNOTABLE_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link nl.esi.poosl.impl.PortImpl <em>Port</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see nl.esi.poosl.impl.PortImpl
	 * @see nl.esi.poosl.impl.PooslPackageImpl#getPort()
	 * @generated
	 */
	int PORT = 7;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT__NAME = 0;

	/**
	 * The number of structural features of the '<em>Port</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link nl.esi.poosl.impl.DeclarationImpl <em>Declaration</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see nl.esi.poosl.impl.DeclarationImpl
	 * @see nl.esi.poosl.impl.PooslPackageImpl#getDeclaration()
	 * @generated
	 */
	int DECLARATION = 8;

	/**
	 * The feature id for the '<em><b>Variables</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECLARATION__VARIABLES = 0;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECLARATION__TYPE = 1;

	/**
	 * The number of structural features of the '<em>Declaration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECLARATION_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link nl.esi.poosl.impl.VariableImpl <em>Variable</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see nl.esi.poosl.impl.VariableImpl
	 * @see nl.esi.poosl.impl.PooslPackageImpl#getVariable()
	 * @generated
	 */
	int VARIABLE = 9;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE__NAME = 0;

	/**
	 * The number of structural features of the '<em>Variable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link nl.esi.poosl.impl.DataClassImpl <em>Data Class</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see nl.esi.poosl.impl.DataClassImpl
	 * @see nl.esi.poosl.impl.PooslPackageImpl#getDataClass()
	 * @generated
	 */
	int DATA_CLASS = 10;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_CLASS__ANNOTATIONS = ANNOTABLE__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_CLASS__NAME = ANNOTABLE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Native</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_CLASS__NATIVE = ANNOTABLE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Instance Variables</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_CLASS__INSTANCE_VARIABLES = ANNOTABLE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Data Methods Named</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_CLASS__DATA_METHODS_NAMED = ANNOTABLE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Data Methods Unary Operator</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_CLASS__DATA_METHODS_UNARY_OPERATOR = ANNOTABLE_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Data Methods Binary Operator</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_CLASS__DATA_METHODS_BINARY_OPERATOR = ANNOTABLE_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Super Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_CLASS__SUPER_CLASS = ANNOTABLE_FEATURE_COUNT + 6;

	/**
	 * The number of structural features of the '<em>Data Class</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_CLASS_FEATURE_COUNT = ANNOTABLE_FEATURE_COUNT + 7;

	/**
	 * The meta object id for the '{@link nl.esi.poosl.impl.DataMethodImpl <em>Data Method</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see nl.esi.poosl.impl.DataMethodImpl
	 * @see nl.esi.poosl.impl.PooslPackageImpl#getDataMethod()
	 * @generated
	 */
	int DATA_METHOD = 11;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_METHOD__ANNOTATIONS = ANNOTABLE__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Native</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_METHOD__NATIVE = ANNOTABLE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_METHOD__PARAMETERS = ANNOTABLE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Local Variables</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_METHOD__LOCAL_VARIABLES = ANNOTABLE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Body</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_METHOD__BODY = ANNOTABLE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Return Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_METHOD__RETURN_TYPE = ANNOTABLE_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Data Method</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_METHOD_FEATURE_COUNT = ANNOTABLE_FEATURE_COUNT + 5;

	/**
	 * The meta object id for the '{@link nl.esi.poosl.impl.DataMethodNamedImpl <em>Data Method Named</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see nl.esi.poosl.impl.DataMethodNamedImpl
	 * @see nl.esi.poosl.impl.PooslPackageImpl#getDataMethodNamed()
	 * @generated
	 */
	int DATA_METHOD_NAMED = 12;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_METHOD_NAMED__ANNOTATIONS = DATA_METHOD__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Native</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_METHOD_NAMED__NATIVE = DATA_METHOD__NATIVE;

	/**
	 * The feature id for the '<em><b>Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_METHOD_NAMED__PARAMETERS = DATA_METHOD__PARAMETERS;

	/**
	 * The feature id for the '<em><b>Local Variables</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_METHOD_NAMED__LOCAL_VARIABLES = DATA_METHOD__LOCAL_VARIABLES;

	/**
	 * The feature id for the '<em><b>Body</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_METHOD_NAMED__BODY = DATA_METHOD__BODY;

	/**
	 * The feature id for the '<em><b>Return Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_METHOD_NAMED__RETURN_TYPE = DATA_METHOD__RETURN_TYPE;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_METHOD_NAMED__NAME = DATA_METHOD_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Data Method Named</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_METHOD_NAMED_FEATURE_COUNT = DATA_METHOD_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link nl.esi.poosl.impl.DataMethodUnaryOperatorImpl <em>Data Method Unary Operator</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see nl.esi.poosl.impl.DataMethodUnaryOperatorImpl
	 * @see nl.esi.poosl.impl.PooslPackageImpl#getDataMethodUnaryOperator()
	 * @generated
	 */
	int DATA_METHOD_UNARY_OPERATOR = 13;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_METHOD_UNARY_OPERATOR__ANNOTATIONS = DATA_METHOD__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Native</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_METHOD_UNARY_OPERATOR__NATIVE = DATA_METHOD__NATIVE;

	/**
	 * The feature id for the '<em><b>Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_METHOD_UNARY_OPERATOR__PARAMETERS = DATA_METHOD__PARAMETERS;

	/**
	 * The feature id for the '<em><b>Local Variables</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_METHOD_UNARY_OPERATOR__LOCAL_VARIABLES = DATA_METHOD__LOCAL_VARIABLES;

	/**
	 * The feature id for the '<em><b>Body</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_METHOD_UNARY_OPERATOR__BODY = DATA_METHOD__BODY;

	/**
	 * The feature id for the '<em><b>Return Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_METHOD_UNARY_OPERATOR__RETURN_TYPE = DATA_METHOD__RETURN_TYPE;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_METHOD_UNARY_OPERATOR__NAME = DATA_METHOD_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Data Method Unary Operator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_METHOD_UNARY_OPERATOR_FEATURE_COUNT = DATA_METHOD_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link nl.esi.poosl.impl.DataMethodBinaryOperatorImpl <em>Data Method Binary Operator</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see nl.esi.poosl.impl.DataMethodBinaryOperatorImpl
	 * @see nl.esi.poosl.impl.PooslPackageImpl#getDataMethodBinaryOperator()
	 * @generated
	 */
	int DATA_METHOD_BINARY_OPERATOR = 14;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_METHOD_BINARY_OPERATOR__ANNOTATIONS = DATA_METHOD__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Native</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_METHOD_BINARY_OPERATOR__NATIVE = DATA_METHOD__NATIVE;

	/**
	 * The feature id for the '<em><b>Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_METHOD_BINARY_OPERATOR__PARAMETERS = DATA_METHOD__PARAMETERS;

	/**
	 * The feature id for the '<em><b>Local Variables</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_METHOD_BINARY_OPERATOR__LOCAL_VARIABLES = DATA_METHOD__LOCAL_VARIABLES;

	/**
	 * The feature id for the '<em><b>Body</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_METHOD_BINARY_OPERATOR__BODY = DATA_METHOD__BODY;

	/**
	 * The feature id for the '<em><b>Return Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_METHOD_BINARY_OPERATOR__RETURN_TYPE = DATA_METHOD__RETURN_TYPE;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_METHOD_BINARY_OPERATOR__NAME = DATA_METHOD_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Data Method Binary Operator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_METHOD_BINARY_OPERATOR_FEATURE_COUNT = DATA_METHOD_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link nl.esi.poosl.impl.ExpressionImpl <em>Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see nl.esi.poosl.impl.ExpressionImpl
	 * @see nl.esi.poosl.impl.PooslPackageImpl#getExpression()
	 * @generated
	 */
	int EXPRESSION = 15;

	/**
	 * The number of structural features of the '<em>Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPRESSION_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link nl.esi.poosl.impl.ExpressionSequenceImpl <em>Expression Sequence</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see nl.esi.poosl.impl.ExpressionSequenceImpl
	 * @see nl.esi.poosl.impl.PooslPackageImpl#getExpressionSequence()
	 * @generated
	 */
	int EXPRESSION_SEQUENCE = 16;

	/**
	 * The feature id for the '<em><b>Expressions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPRESSION_SEQUENCE__EXPRESSIONS = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Expression Sequence</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPRESSION_SEQUENCE_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link nl.esi.poosl.impl.ExpressionSequenceRoundBracketImpl <em>Expression Sequence Round Bracket</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see nl.esi.poosl.impl.ExpressionSequenceRoundBracketImpl
	 * @see nl.esi.poosl.impl.PooslPackageImpl#getExpressionSequenceRoundBracket()
	 * @generated
	 */
	int EXPRESSION_SEQUENCE_ROUND_BRACKET = 17;

	/**
	 * The feature id for the '<em><b>Expressions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPRESSION_SEQUENCE_ROUND_BRACKET__EXPRESSIONS = EXPRESSION_SEQUENCE__EXPRESSIONS;

	/**
	 * The number of structural features of the '<em>Expression Sequence Round Bracket</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPRESSION_SEQUENCE_ROUND_BRACKET_FEATURE_COUNT = EXPRESSION_SEQUENCE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link nl.esi.poosl.impl.AssignmentExpressionImpl <em>Assignment Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see nl.esi.poosl.impl.AssignmentExpressionImpl
	 * @see nl.esi.poosl.impl.PooslPackageImpl#getAssignmentExpression()
	 * @generated
	 */
	int ASSIGNMENT_EXPRESSION = 18;

	/**
	 * The feature id for the '<em><b>Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSIGNMENT_EXPRESSION__EXPRESSION = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Variable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSIGNMENT_EXPRESSION__VARIABLE = EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Assignment Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSIGNMENT_EXPRESSION_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link nl.esi.poosl.impl.CurrentTimeExpressionImpl <em>Current Time Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see nl.esi.poosl.impl.CurrentTimeExpressionImpl
	 * @see nl.esi.poosl.impl.PooslPackageImpl#getCurrentTimeExpression()
	 * @generated
	 */
	int CURRENT_TIME_EXPRESSION = 19;

	/**
	 * The number of structural features of the '<em>Current Time Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CURRENT_TIME_EXPRESSION_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link nl.esi.poosl.impl.DataMethodCallExpressionImpl <em>Data Method Call Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see nl.esi.poosl.impl.DataMethodCallExpressionImpl
	 * @see nl.esi.poosl.impl.PooslPackageImpl#getDataMethodCallExpression()
	 * @generated
	 */
	int DATA_METHOD_CALL_EXPRESSION = 20;

	/**
	 * The feature id for the '<em><b>Target</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_METHOD_CALL_EXPRESSION__TARGET = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>On Super Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_METHOD_CALL_EXPRESSION__ON_SUPER_CLASS = EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_METHOD_CALL_EXPRESSION__NAME = EXPRESSION_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Arguments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_METHOD_CALL_EXPRESSION__ARGUMENTS = EXPRESSION_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Data Method Call Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_METHOD_CALL_EXPRESSION_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link nl.esi.poosl.impl.IfExpressionImpl <em>If Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see nl.esi.poosl.impl.IfExpressionImpl
	 * @see nl.esi.poosl.impl.PooslPackageImpl#getIfExpression()
	 * @generated
	 */
	int IF_EXPRESSION = 21;

	/**
	 * The feature id for the '<em><b>Condition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IF_EXPRESSION__CONDITION = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Then Clause</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IF_EXPRESSION__THEN_CLAUSE = EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Else Clause</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IF_EXPRESSION__ELSE_CLAUSE = EXPRESSION_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>If Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IF_EXPRESSION_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link nl.esi.poosl.impl.SwitchExpressionImpl <em>Switch Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see nl.esi.poosl.impl.SwitchExpressionImpl
	 * @see nl.esi.poosl.impl.PooslPackageImpl#getSwitchExpression()
	 * @generated
	 */
	int SWITCH_EXPRESSION = 22;

	/**
	 * The feature id for the '<em><b>Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWITCH_EXPRESSION__EXPRESSION = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Cases</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWITCH_EXPRESSION__CASES = EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Default Body</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWITCH_EXPRESSION__DEFAULT_BODY = EXPRESSION_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Switch Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWITCH_EXPRESSION_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link nl.esi.poosl.impl.SwitchExpressionCaseImpl <em>Switch Expression Case</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see nl.esi.poosl.impl.SwitchExpressionCaseImpl
	 * @see nl.esi.poosl.impl.PooslPackageImpl#getSwitchExpressionCase()
	 * @generated
	 */
	int SWITCH_EXPRESSION_CASE = 23;

	/**
	 * The feature id for the '<em><b>Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWITCH_EXPRESSION_CASE__VALUE = 0;

	/**
	 * The feature id for the '<em><b>Body</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWITCH_EXPRESSION_CASE__BODY = 1;

	/**
	 * The number of structural features of the '<em>Switch Expression Case</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWITCH_EXPRESSION_CASE_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link nl.esi.poosl.impl.NewExpressionImpl <em>New Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see nl.esi.poosl.impl.NewExpressionImpl
	 * @see nl.esi.poosl.impl.PooslPackageImpl#getNewExpression()
	 * @generated
	 */
	int NEW_EXPRESSION = 24;

	/**
	 * The feature id for the '<em><b>Data Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NEW_EXPRESSION__DATA_CLASS = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>New Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NEW_EXPRESSION_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link nl.esi.poosl.impl.ReturnExpressionImpl <em>Return Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see nl.esi.poosl.impl.ReturnExpressionImpl
	 * @see nl.esi.poosl.impl.PooslPackageImpl#getReturnExpression()
	 * @generated
	 */
	int RETURN_EXPRESSION = 25;

	/**
	 * The feature id for the '<em><b>Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RETURN_EXPRESSION__EXPRESSION = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Return Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RETURN_EXPRESSION_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link nl.esi.poosl.impl.SelfExpressionImpl <em>Self Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see nl.esi.poosl.impl.SelfExpressionImpl
	 * @see nl.esi.poosl.impl.PooslPackageImpl#getSelfExpression()
	 * @generated
	 */
	int SELF_EXPRESSION = 26;

	/**
	 * The number of structural features of the '<em>Self Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SELF_EXPRESSION_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link nl.esi.poosl.impl.VariableExpressionImpl <em>Variable Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see nl.esi.poosl.impl.VariableExpressionImpl
	 * @see nl.esi.poosl.impl.PooslPackageImpl#getVariableExpression()
	 * @generated
	 */
	int VARIABLE_EXPRESSION = 27;

	/**
	 * The feature id for the '<em><b>Variable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_EXPRESSION__VARIABLE = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Variable Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_EXPRESSION_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link nl.esi.poosl.impl.WhileExpressionImpl <em>While Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see nl.esi.poosl.impl.WhileExpressionImpl
	 * @see nl.esi.poosl.impl.PooslPackageImpl#getWhileExpression()
	 * @generated
	 */
	int WHILE_EXPRESSION = 28;

	/**
	 * The feature id for the '<em><b>Condition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WHILE_EXPRESSION__CONDITION = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Body</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WHILE_EXPRESSION__BODY = EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>While Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WHILE_EXPRESSION_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link nl.esi.poosl.impl.BinaryOperatorExpressionImpl <em>Binary Operator Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see nl.esi.poosl.impl.BinaryOperatorExpressionImpl
	 * @see nl.esi.poosl.impl.PooslPackageImpl#getBinaryOperatorExpression()
	 * @generated
	 */
	int BINARY_OPERATOR_EXPRESSION = 29;

	/**
	 * The feature id for the '<em><b>Left Operand</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINARY_OPERATOR_EXPRESSION__LEFT_OPERAND = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINARY_OPERATOR_EXPRESSION__NAME = EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Right Operand</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINARY_OPERATOR_EXPRESSION__RIGHT_OPERAND = EXPRESSION_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Binary Operator Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINARY_OPERATOR_EXPRESSION_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link nl.esi.poosl.impl.UnaryOperatorExpressionImpl <em>Unary Operator Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see nl.esi.poosl.impl.UnaryOperatorExpressionImpl
	 * @see nl.esi.poosl.impl.PooslPackageImpl#getUnaryOperatorExpression()
	 * @generated
	 */
	int UNARY_OPERATOR_EXPRESSION = 30;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNARY_OPERATOR_EXPRESSION__NAME = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Operand</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNARY_OPERATOR_EXPRESSION__OPERAND = EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Unary Operator Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNARY_OPERATOR_EXPRESSION_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link nl.esi.poosl.impl.BooleanConstantImpl <em>Boolean Constant</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see nl.esi.poosl.impl.BooleanConstantImpl
	 * @see nl.esi.poosl.impl.PooslPackageImpl#getBooleanConstant()
	 * @generated
	 */
	int BOOLEAN_CONSTANT = 31;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_CONSTANT__VALUE = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Boolean Constant</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_CONSTANT_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link nl.esi.poosl.impl.CharacterConstantImpl <em>Character Constant</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see nl.esi.poosl.impl.CharacterConstantImpl
	 * @see nl.esi.poosl.impl.PooslPackageImpl#getCharacterConstant()
	 * @generated
	 */
	int CHARACTER_CONSTANT = 32;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHARACTER_CONSTANT__VALUE = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Character Constant</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHARACTER_CONSTANT_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link nl.esi.poosl.impl.IntegerConstantImpl <em>Integer Constant</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see nl.esi.poosl.impl.IntegerConstantImpl
	 * @see nl.esi.poosl.impl.PooslPackageImpl#getIntegerConstant()
	 * @generated
	 */
	int INTEGER_CONSTANT = 33;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_CONSTANT__VALUE = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Integer Constant</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_CONSTANT_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link nl.esi.poosl.impl.NilConstantImpl <em>Nil Constant</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see nl.esi.poosl.impl.NilConstantImpl
	 * @see nl.esi.poosl.impl.PooslPackageImpl#getNilConstant()
	 * @generated
	 */
	int NIL_CONSTANT = 34;

	/**
	 * The number of structural features of the '<em>Nil Constant</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NIL_CONSTANT_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link nl.esi.poosl.impl.RealConstantImpl <em>Real Constant</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see nl.esi.poosl.impl.RealConstantImpl
	 * @see nl.esi.poosl.impl.PooslPackageImpl#getRealConstant()
	 * @generated
	 */
	int REAL_CONSTANT = 35;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REAL_CONSTANT__VALUE = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Real Constant</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REAL_CONSTANT_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link nl.esi.poosl.impl.FloatConstantImpl <em>Float Constant</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see nl.esi.poosl.impl.FloatConstantImpl
	 * @see nl.esi.poosl.impl.PooslPackageImpl#getFloatConstant()
	 * @generated
	 */
	int FLOAT_CONSTANT = 36;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOAT_CONSTANT__VALUE = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Float Constant</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOAT_CONSTANT_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link nl.esi.poosl.impl.StringConstantImpl <em>String Constant</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see nl.esi.poosl.impl.StringConstantImpl
	 * @see nl.esi.poosl.impl.PooslPackageImpl#getStringConstant()
	 * @generated
	 */
	int STRING_CONSTANT = 37;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_CONSTANT__VALUE = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>String Constant</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_CONSTANT_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link nl.esi.poosl.impl.EnvironmentConstantImpl <em>Environment Constant</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see nl.esi.poosl.impl.EnvironmentConstantImpl
	 * @see nl.esi.poosl.impl.PooslPackageImpl#getEnvironmentConstant()
	 * @generated
	 */
	int ENVIRONMENT_CONSTANT = 38;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENVIRONMENT_CONSTANT__VALUE = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Environment Constant</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENVIRONMENT_CONSTANT_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link nl.esi.poosl.impl.ProcessClassImpl <em>Process Class</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see nl.esi.poosl.impl.ProcessClassImpl
	 * @see nl.esi.poosl.impl.PooslPackageImpl#getProcessClass()
	 * @generated
	 */
	int PROCESS_CLASS = 39;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_CLASS__ANNOTATIONS = INSTANTIABLE_CLASS__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_CLASS__NAME = INSTANTIABLE_CLASS__NAME;

	/**
	 * The feature id for the '<em><b>Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_CLASS__PARAMETERS = INSTANTIABLE_CLASS__PARAMETERS;

	/**
	 * The feature id for the '<em><b>Ports</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_CLASS__PORTS = INSTANTIABLE_CLASS__PORTS;

	/**
	 * The feature id for the '<em><b>Receive Messages</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_CLASS__RECEIVE_MESSAGES = INSTANTIABLE_CLASS_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Send Messages</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_CLASS__SEND_MESSAGES = INSTANTIABLE_CLASS_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Instance Variables</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_CLASS__INSTANCE_VARIABLES = INSTANTIABLE_CLASS_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Methods</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_CLASS__METHODS = INSTANTIABLE_CLASS_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Initial Method Call</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_CLASS__INITIAL_METHOD_CALL = INSTANTIABLE_CLASS_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Super Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_CLASS__SUPER_CLASS = INSTANTIABLE_CLASS_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>Process Class</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_CLASS_FEATURE_COUNT = INSTANTIABLE_CLASS_FEATURE_COUNT + 6;

	/**
	 * The meta object id for the '{@link nl.esi.poosl.impl.MessageSignatureImpl <em>Message Signature</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see nl.esi.poosl.impl.MessageSignatureImpl
	 * @see nl.esi.poosl.impl.PooslPackageImpl#getMessageSignature()
	 * @generated
	 */
	int MESSAGE_SIGNATURE = 40;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MESSAGE_SIGNATURE__NAME = 0;

	/**
	 * The feature id for the '<em><b>Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MESSAGE_SIGNATURE__PARAMETERS = 1;

	/**
	 * The feature id for the '<em><b>Port</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MESSAGE_SIGNATURE__PORT = 2;

	/**
	 * The number of structural features of the '<em>Message Signature</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MESSAGE_SIGNATURE_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link nl.esi.poosl.impl.MessageParameterImpl <em>Message Parameter</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see nl.esi.poosl.impl.MessageParameterImpl
	 * @see nl.esi.poosl.impl.PooslPackageImpl#getMessageParameter()
	 * @generated
	 */
	int MESSAGE_PARAMETER = 41;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MESSAGE_PARAMETER__TYPE = 0;

	/**
	 * The number of structural features of the '<em>Message Parameter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MESSAGE_PARAMETER_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link nl.esi.poosl.impl.ProcessMethodImpl <em>Process Method</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see nl.esi.poosl.impl.ProcessMethodImpl
	 * @see nl.esi.poosl.impl.PooslPackageImpl#getProcessMethod()
	 * @generated
	 */
	int PROCESS_METHOD = 42;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_METHOD__ANNOTATIONS = ANNOTABLE__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_METHOD__NAME = ANNOTABLE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Input Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_METHOD__INPUT_PARAMETERS = ANNOTABLE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Output Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_METHOD__OUTPUT_PARAMETERS = ANNOTABLE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Local Variables</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_METHOD__LOCAL_VARIABLES = ANNOTABLE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Body</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_METHOD__BODY = ANNOTABLE_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Process Method</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_METHOD_FEATURE_COUNT = ANNOTABLE_FEATURE_COUNT + 5;

	/**
	 * The meta object id for the '{@link nl.esi.poosl.impl.StatementImpl <em>Statement</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see nl.esi.poosl.impl.StatementImpl
	 * @see nl.esi.poosl.impl.PooslPackageImpl#getStatement()
	 * @generated
	 */
	int STATEMENT = 43;

	/**
	 * The number of structural features of the '<em>Statement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATEMENT_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link nl.esi.poosl.impl.StatementSequenceImpl <em>Statement Sequence</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see nl.esi.poosl.impl.StatementSequenceImpl
	 * @see nl.esi.poosl.impl.PooslPackageImpl#getStatementSequence()
	 * @generated
	 */
	int STATEMENT_SEQUENCE = 44;

	/**
	 * The feature id for the '<em><b>Statements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATEMENT_SEQUENCE__STATEMENTS = STATEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Statement Sequence</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATEMENT_SEQUENCE_FEATURE_COUNT = STATEMENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link nl.esi.poosl.impl.StatementSequenceRoundBracketImpl <em>Statement Sequence Round Bracket</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see nl.esi.poosl.impl.StatementSequenceRoundBracketImpl
	 * @see nl.esi.poosl.impl.PooslPackageImpl#getStatementSequenceRoundBracket()
	 * @generated
	 */
	int STATEMENT_SEQUENCE_ROUND_BRACKET = 45;

	/**
	 * The feature id for the '<em><b>Statements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATEMENT_SEQUENCE_ROUND_BRACKET__STATEMENTS = STATEMENT_SEQUENCE__STATEMENTS;

	/**
	 * The number of structural features of the '<em>Statement Sequence Round Bracket</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATEMENT_SEQUENCE_ROUND_BRACKET_FEATURE_COUNT = STATEMENT_SEQUENCE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link nl.esi.poosl.impl.AbortStatementImpl <em>Abort Statement</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see nl.esi.poosl.impl.AbortStatementImpl
	 * @see nl.esi.poosl.impl.PooslPackageImpl#getAbortStatement()
	 * @generated
	 */
	int ABORT_STATEMENT = 46;

	/**
	 * The feature id for the '<em><b>Normal Clause</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABORT_STATEMENT__NORMAL_CLAUSE = STATEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Aborting Clause</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABORT_STATEMENT__ABORTING_CLAUSE = STATEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Abort Statement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABORT_STATEMENT_FEATURE_COUNT = STATEMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link nl.esi.poosl.impl.DelayStatementImpl <em>Delay Statement</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see nl.esi.poosl.impl.DelayStatementImpl
	 * @see nl.esi.poosl.impl.PooslPackageImpl#getDelayStatement()
	 * @generated
	 */
	int DELAY_STATEMENT = 47;

	/**
	 * The feature id for the '<em><b>Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELAY_STATEMENT__EXPRESSION = STATEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Delay Statement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELAY_STATEMENT_FEATURE_COUNT = STATEMENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link nl.esi.poosl.impl.ExpressionStatementImpl <em>Expression Statement</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see nl.esi.poosl.impl.ExpressionStatementImpl
	 * @see nl.esi.poosl.impl.PooslPackageImpl#getExpressionStatement()
	 * @generated
	 */
	int EXPRESSION_STATEMENT = 48;

	/**
	 * The feature id for the '<em><b>Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPRESSION_STATEMENT__EXPRESSION = STATEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Expression Statement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPRESSION_STATEMENT_FEATURE_COUNT = STATEMENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link nl.esi.poosl.impl.GuardedStatementImpl <em>Guarded Statement</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see nl.esi.poosl.impl.GuardedStatementImpl
	 * @see nl.esi.poosl.impl.PooslPackageImpl#getGuardedStatement()
	 * @generated
	 */
	int GUARDED_STATEMENT = 49;

	/**
	 * The feature id for the '<em><b>Guard</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUARDED_STATEMENT__GUARD = STATEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Statement</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUARDED_STATEMENT__STATEMENT = STATEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Guarded Statement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUARDED_STATEMENT_FEATURE_COUNT = STATEMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link nl.esi.poosl.impl.IfStatementImpl <em>If Statement</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see nl.esi.poosl.impl.IfStatementImpl
	 * @see nl.esi.poosl.impl.PooslPackageImpl#getIfStatement()
	 * @generated
	 */
	int IF_STATEMENT = 50;

	/**
	 * The feature id for the '<em><b>Condition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IF_STATEMENT__CONDITION = STATEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Then Clause</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IF_STATEMENT__THEN_CLAUSE = STATEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Else Clause</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IF_STATEMENT__ELSE_CLAUSE = STATEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>If Statement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IF_STATEMENT_FEATURE_COUNT = STATEMENT_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link nl.esi.poosl.impl.SwitchStatementImpl <em>Switch Statement</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see nl.esi.poosl.impl.SwitchStatementImpl
	 * @see nl.esi.poosl.impl.PooslPackageImpl#getSwitchStatement()
	 * @generated
	 */
	int SWITCH_STATEMENT = 51;

	/**
	 * The feature id for the '<em><b>Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWITCH_STATEMENT__EXPRESSION = STATEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Cases</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWITCH_STATEMENT__CASES = STATEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Default Body</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWITCH_STATEMENT__DEFAULT_BODY = STATEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Switch Statement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWITCH_STATEMENT_FEATURE_COUNT = STATEMENT_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link nl.esi.poosl.impl.SwitchStatementCaseImpl <em>Switch Statement Case</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see nl.esi.poosl.impl.SwitchStatementCaseImpl
	 * @see nl.esi.poosl.impl.PooslPackageImpl#getSwitchStatementCase()
	 * @generated
	 */
	int SWITCH_STATEMENT_CASE = 52;

	/**
	 * The feature id for the '<em><b>Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWITCH_STATEMENT_CASE__VALUE = 0;

	/**
	 * The feature id for the '<em><b>Body</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWITCH_STATEMENT_CASE__BODY = 1;

	/**
	 * The number of structural features of the '<em>Switch Statement Case</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWITCH_STATEMENT_CASE_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link nl.esi.poosl.impl.InterruptStatementImpl <em>Interrupt Statement</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see nl.esi.poosl.impl.InterruptStatementImpl
	 * @see nl.esi.poosl.impl.PooslPackageImpl#getInterruptStatement()
	 * @generated
	 */
	int INTERRUPT_STATEMENT = 53;

	/**
	 * The feature id for the '<em><b>Normal Clause</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERRUPT_STATEMENT__NORMAL_CLAUSE = STATEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Interrupting Clause</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERRUPT_STATEMENT__INTERRUPTING_CLAUSE = STATEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Interrupt Statement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERRUPT_STATEMENT_FEATURE_COUNT = STATEMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link nl.esi.poosl.impl.ParStatementImpl <em>Par Statement</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see nl.esi.poosl.impl.ParStatementImpl
	 * @see nl.esi.poosl.impl.PooslPackageImpl#getParStatement()
	 * @generated
	 */
	int PAR_STATEMENT = 54;

	/**
	 * The feature id for the '<em><b>Clauses</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAR_STATEMENT__CLAUSES = STATEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Par Statement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAR_STATEMENT_FEATURE_COUNT = STATEMENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link nl.esi.poosl.impl.ProcessMethodCallImpl <em>Process Method Call</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see nl.esi.poosl.impl.ProcessMethodCallImpl
	 * @see nl.esi.poosl.impl.PooslPackageImpl#getProcessMethodCall()
	 * @generated
	 */
	int PROCESS_METHOD_CALL = 55;

	/**
	 * The feature id for the '<em><b>Input Arguments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_METHOD_CALL__INPUT_ARGUMENTS = STATEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Output Variables</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_METHOD_CALL__OUTPUT_VARIABLES = STATEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Method</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_METHOD_CALL__METHOD = STATEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Process Method Call</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_METHOD_CALL_FEATURE_COUNT = STATEMENT_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link nl.esi.poosl.impl.ReceiveStatementImpl <em>Receive Statement</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see nl.esi.poosl.impl.ReceiveStatementImpl
	 * @see nl.esi.poosl.impl.PooslPackageImpl#getReceiveStatement()
	 * @generated
	 */
	int RECEIVE_STATEMENT = 56;

	/**
	 * The feature id for the '<em><b>Port Descriptor</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECEIVE_STATEMENT__PORT_DESCRIPTOR = STATEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECEIVE_STATEMENT__NAME = STATEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Variables</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECEIVE_STATEMENT__VARIABLES = STATEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Reception Condition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECEIVE_STATEMENT__RECEPTION_CONDITION = STATEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Post Communication Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECEIVE_STATEMENT__POST_COMMUNICATION_EXPRESSION = STATEMENT_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Receive Statement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECEIVE_STATEMENT_FEATURE_COUNT = STATEMENT_FEATURE_COUNT + 5;

	/**
	 * The meta object id for the '{@link nl.esi.poosl.impl.SendStatementImpl <em>Send Statement</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see nl.esi.poosl.impl.SendStatementImpl
	 * @see nl.esi.poosl.impl.PooslPackageImpl#getSendStatement()
	 * @generated
	 */
	int SEND_STATEMENT = 57;

	/**
	 * The feature id for the '<em><b>Port Descriptor</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEND_STATEMENT__PORT_DESCRIPTOR = STATEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEND_STATEMENT__NAME = STATEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Arguments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEND_STATEMENT__ARGUMENTS = STATEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Post Communication Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEND_STATEMENT__POST_COMMUNICATION_EXPRESSION = STATEMENT_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Send Statement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEND_STATEMENT_FEATURE_COUNT = STATEMENT_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link nl.esi.poosl.impl.SelStatementImpl <em>Sel Statement</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see nl.esi.poosl.impl.SelStatementImpl
	 * @see nl.esi.poosl.impl.PooslPackageImpl#getSelStatement()
	 * @generated
	 */
	int SEL_STATEMENT = 58;

	/**
	 * The feature id for the '<em><b>Clauses</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEL_STATEMENT__CLAUSES = STATEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Sel Statement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEL_STATEMENT_FEATURE_COUNT = STATEMENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link nl.esi.poosl.impl.SkipStatementImpl <em>Skip Statement</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see nl.esi.poosl.impl.SkipStatementImpl
	 * @see nl.esi.poosl.impl.PooslPackageImpl#getSkipStatement()
	 * @generated
	 */
	int SKIP_STATEMENT = 59;

	/**
	 * The number of structural features of the '<em>Skip Statement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SKIP_STATEMENT_FEATURE_COUNT = STATEMENT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link nl.esi.poosl.impl.WhileStatementImpl <em>While Statement</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see nl.esi.poosl.impl.WhileStatementImpl
	 * @see nl.esi.poosl.impl.PooslPackageImpl#getWhileStatement()
	 * @generated
	 */
	int WHILE_STATEMENT = 60;

	/**
	 * The feature id for the '<em><b>Condition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WHILE_STATEMENT__CONDITION = STATEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Body</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WHILE_STATEMENT__BODY = STATEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>While Statement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WHILE_STATEMENT_FEATURE_COUNT = STATEMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link nl.esi.poosl.impl.PortDescriptorImpl <em>Port Descriptor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see nl.esi.poosl.impl.PortDescriptorImpl
	 * @see nl.esi.poosl.impl.PooslPackageImpl#getPortDescriptor()
	 * @generated
	 */
	int PORT_DESCRIPTOR = 61;

	/**
	 * The number of structural features of the '<em>Port Descriptor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_DESCRIPTOR_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link nl.esi.poosl.impl.PortExpressionImpl <em>Port Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see nl.esi.poosl.impl.PortExpressionImpl
	 * @see nl.esi.poosl.impl.PooslPackageImpl#getPortExpression()
	 * @generated
	 */
	int PORT_EXPRESSION = 62;

	/**
	 * The feature id for the '<em><b>Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_EXPRESSION__EXPRESSION = PORT_DESCRIPTOR_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Port Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_EXPRESSION_FEATURE_COUNT = PORT_DESCRIPTOR_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link nl.esi.poosl.impl.PortReferenceImpl <em>Port Reference</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see nl.esi.poosl.impl.PortReferenceImpl
	 * @see nl.esi.poosl.impl.PooslPackageImpl#getPortReference()
	 * @generated
	 */
	int PORT_REFERENCE = 63;

	/**
	 * The feature id for the '<em><b>Port</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_REFERENCE__PORT = PORT_DESCRIPTOR_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Port Reference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_REFERENCE_FEATURE_COUNT = PORT_DESCRIPTOR_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link nl.esi.poosl.impl.ClusterClassImpl <em>Cluster Class</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see nl.esi.poosl.impl.ClusterClassImpl
	 * @see nl.esi.poosl.impl.PooslPackageImpl#getClusterClass()
	 * @generated
	 */
	int CLUSTER_CLASS = 64;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLUSTER_CLASS__ANNOTATIONS = INSTANTIABLE_CLASS__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLUSTER_CLASS__NAME = INSTANTIABLE_CLASS__NAME;

	/**
	 * The feature id for the '<em><b>Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLUSTER_CLASS__PARAMETERS = INSTANTIABLE_CLASS__PARAMETERS;

	/**
	 * The feature id for the '<em><b>Ports</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLUSTER_CLASS__PORTS = INSTANTIABLE_CLASS__PORTS;

	/**
	 * The feature id for the '<em><b>Channels</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLUSTER_CLASS__CHANNELS = INSTANTIABLE_CLASS_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Instances</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLUSTER_CLASS__INSTANCES = INSTANTIABLE_CLASS_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Cluster Class</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLUSTER_CLASS_FEATURE_COUNT = INSTANTIABLE_CLASS_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link nl.esi.poosl.impl.OutputVariableImpl <em>Output Variable</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see nl.esi.poosl.impl.OutputVariableImpl
	 * @see nl.esi.poosl.impl.PooslPackageImpl#getOutputVariable()
	 * @generated
	 */
	int OUTPUT_VARIABLE = 65;

	/**
	 * The feature id for the '<em><b>Variable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT_VARIABLE__VARIABLE = 0;

	/**
	 * The number of structural features of the '<em>Output Variable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT_VARIABLE_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link nl.esi.poosl.impl.AnnotationImpl <em>Annotation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see nl.esi.poosl.impl.AnnotationImpl
	 * @see nl.esi.poosl.impl.PooslPackageImpl#getAnnotation()
	 * @generated
	 */
	int ANNOTATION = 66;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANNOTATION__NAME = 0;

	/**
	 * The feature id for the '<em><b>Arguments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANNOTATION__ARGUMENTS = 1;

	/**
	 * The number of structural features of the '<em>Annotation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANNOTATION_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link nl.esi.poosl.OperatorUnary <em>Operator Unary</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see nl.esi.poosl.OperatorUnary
	 * @see nl.esi.poosl.impl.PooslPackageImpl#getOperatorUnary()
	 * @generated
	 */
	int OPERATOR_UNARY = 68;

	/**
	 * The meta object id for the '{@link nl.esi.poosl.OperatorBinary <em>Operator Binary</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see nl.esi.poosl.OperatorBinary
	 * @see nl.esi.poosl.impl.PooslPackageImpl#getOperatorBinary()
	 * @generated
	 */
	int OPERATOR_BINARY = 69;


	/**
	 * Returns the meta object for class '{@link nl.esi.poosl.Poosl <em>Poosl</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Poosl</em>'.
	 * @see nl.esi.poosl.Poosl
	 * @generated
	 */
	EClass getPoosl();

	/**
	 * Returns the meta object for the containment reference list '{@link nl.esi.poosl.Poosl#getImports <em>Imports</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Imports</em>'.
	 * @see nl.esi.poosl.Poosl#getImports()
	 * @see #getPoosl()
	 * @generated
	 */
	EReference getPoosl_Imports();

	/**
	 * Returns the meta object for the containment reference list '{@link nl.esi.poosl.Poosl#getDataClasses <em>Data Classes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Data Classes</em>'.
	 * @see nl.esi.poosl.Poosl#getDataClasses()
	 * @see #getPoosl()
	 * @generated
	 */
	EReference getPoosl_DataClasses();

	/**
	 * Returns the meta object for the containment reference list '{@link nl.esi.poosl.Poosl#getProcessClasses <em>Process Classes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Process Classes</em>'.
	 * @see nl.esi.poosl.Poosl#getProcessClasses()
	 * @see #getPoosl()
	 * @generated
	 */
	EReference getPoosl_ProcessClasses();

	/**
	 * Returns the meta object for the containment reference list '{@link nl.esi.poosl.Poosl#getClusterClasses <em>Cluster Classes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Cluster Classes</em>'.
	 * @see nl.esi.poosl.Poosl#getClusterClasses()
	 * @see #getPoosl()
	 * @generated
	 */
	EReference getPoosl_ClusterClasses();

	/**
	 * Returns the meta object for the containment reference list '{@link nl.esi.poosl.Poosl#getImportLibs <em>Import Libs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Import Libs</em>'.
	 * @see nl.esi.poosl.Poosl#getImportLibs()
	 * @see #getPoosl()
	 * @generated
	 */
	EReference getPoosl_ImportLibs();

	/**
	 * Returns the meta object for class '{@link nl.esi.poosl.Import <em>Import</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Import</em>'.
	 * @see nl.esi.poosl.Import
	 * @generated
	 */
	EClass getImport();

	/**
	 * Returns the meta object for the attribute '{@link nl.esi.poosl.Import#getImportURI <em>Import URI</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Import URI</em>'.
	 * @see nl.esi.poosl.Import#getImportURI()
	 * @see #getImport()
	 * @generated
	 */
	EAttribute getImport_ImportURI();

	/**
	 * Returns the meta object for class '{@link nl.esi.poosl.Channel <em>Channel</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Channel</em>'.
	 * @see nl.esi.poosl.Channel
	 * @generated
	 */
	EClass getChannel();

	/**
	 * Returns the meta object for the reference '{@link nl.esi.poosl.Channel#getExternalPort <em>External Port</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>External Port</em>'.
	 * @see nl.esi.poosl.Channel#getExternalPort()
	 * @see #getChannel()
	 * @generated
	 */
	EReference getChannel_ExternalPort();

	/**
	 * Returns the meta object for the containment reference list '{@link nl.esi.poosl.Channel#getInstancePorts <em>Instance Ports</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Instance Ports</em>'.
	 * @see nl.esi.poosl.Channel#getInstancePorts()
	 * @see #getChannel()
	 * @generated
	 */
	EReference getChannel_InstancePorts();

	/**
	 * Returns the meta object for class '{@link nl.esi.poosl.InstancePort <em>Instance Port</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Instance Port</em>'.
	 * @see nl.esi.poosl.InstancePort
	 * @generated
	 */
	EClass getInstancePort();

	/**
	 * Returns the meta object for the reference '{@link nl.esi.poosl.InstancePort#getInstance <em>Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Instance</em>'.
	 * @see nl.esi.poosl.InstancePort#getInstance()
	 * @see #getInstancePort()
	 * @generated
	 */
	EReference getInstancePort_Instance();

	/**
	 * Returns the meta object for the containment reference '{@link nl.esi.poosl.InstancePort#getPort <em>Port</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Port</em>'.
	 * @see nl.esi.poosl.InstancePort#getPort()
	 * @see #getInstancePort()
	 * @generated
	 */
	EReference getInstancePort_Port();

	/**
	 * Returns the meta object for class '{@link nl.esi.poosl.Instance <em>Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Instance</em>'.
	 * @see nl.esi.poosl.Instance
	 * @generated
	 */
	EClass getInstance();

	/**
	 * Returns the meta object for the attribute '{@link nl.esi.poosl.Instance#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see nl.esi.poosl.Instance#getName()
	 * @see #getInstance()
	 * @generated
	 */
	EAttribute getInstance_Name();

	/**
	 * Returns the meta object for the containment reference list '{@link nl.esi.poosl.Instance#getInstanceParameters <em>Instance Parameters</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Instance Parameters</em>'.
	 * @see nl.esi.poosl.Instance#getInstanceParameters()
	 * @see #getInstance()
	 * @generated
	 */
	EReference getInstance_InstanceParameters();

	/**
	 * Returns the meta object for the attribute '{@link nl.esi.poosl.Instance#getClassDefinition <em>Class Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Class Definition</em>'.
	 * @see nl.esi.poosl.Instance#getClassDefinition()
	 * @see #getInstance()
	 * @generated
	 */
	EAttribute getInstance_ClassDefinition();

	/**
	 * Returns the meta object for class '{@link nl.esi.poosl.InstanceParameter <em>Instance Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Instance Parameter</em>'.
	 * @see nl.esi.poosl.InstanceParameter
	 * @generated
	 */
	EClass getInstanceParameter();

	/**
	 * Returns the meta object for the containment reference '{@link nl.esi.poosl.InstanceParameter#getExpression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Expression</em>'.
	 * @see nl.esi.poosl.InstanceParameter#getExpression()
	 * @see #getInstanceParameter()
	 * @generated
	 */
	EReference getInstanceParameter_Expression();

	/**
	 * Returns the meta object for the attribute '{@link nl.esi.poosl.InstanceParameter#getParameter <em>Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Parameter</em>'.
	 * @see nl.esi.poosl.InstanceParameter#getParameter()
	 * @see #getInstanceParameter()
	 * @generated
	 */
	EAttribute getInstanceParameter_Parameter();

	/**
	 * Returns the meta object for class '{@link nl.esi.poosl.InstantiableClass <em>Instantiable Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Instantiable Class</em>'.
	 * @see nl.esi.poosl.InstantiableClass
	 * @generated
	 */
	EClass getInstantiableClass();

	/**
	 * Returns the meta object for the attribute '{@link nl.esi.poosl.InstantiableClass#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see nl.esi.poosl.InstantiableClass#getName()
	 * @see #getInstantiableClass()
	 * @generated
	 */
	EAttribute getInstantiableClass_Name();

	/**
	 * Returns the meta object for the containment reference list '{@link nl.esi.poosl.InstantiableClass#getParameters <em>Parameters</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Parameters</em>'.
	 * @see nl.esi.poosl.InstantiableClass#getParameters()
	 * @see #getInstantiableClass()
	 * @generated
	 */
	EReference getInstantiableClass_Parameters();

	/**
	 * Returns the meta object for the containment reference list '{@link nl.esi.poosl.InstantiableClass#getPorts <em>Ports</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Ports</em>'.
	 * @see nl.esi.poosl.InstantiableClass#getPorts()
	 * @see #getInstantiableClass()
	 * @generated
	 */
	EReference getInstantiableClass_Ports();

	/**
	 * Returns the meta object for class '{@link nl.esi.poosl.Port <em>Port</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Port</em>'.
	 * @see nl.esi.poosl.Port
	 * @generated
	 */
	EClass getPort();

	/**
	 * Returns the meta object for the attribute '{@link nl.esi.poosl.Port#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see nl.esi.poosl.Port#getName()
	 * @see #getPort()
	 * @generated
	 */
	EAttribute getPort_Name();

	/**
	 * Returns the meta object for class '{@link nl.esi.poosl.Declaration <em>Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Declaration</em>'.
	 * @see nl.esi.poosl.Declaration
	 * @generated
	 */
	EClass getDeclaration();

	/**
	 * Returns the meta object for the containment reference list '{@link nl.esi.poosl.Declaration#getVariables <em>Variables</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Variables</em>'.
	 * @see nl.esi.poosl.Declaration#getVariables()
	 * @see #getDeclaration()
	 * @generated
	 */
	EReference getDeclaration_Variables();

	/**
	 * Returns the meta object for the attribute '{@link nl.esi.poosl.Declaration#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see nl.esi.poosl.Declaration#getType()
	 * @see #getDeclaration()
	 * @generated
	 */
	EAttribute getDeclaration_Type();

	/**
	 * Returns the meta object for class '{@link nl.esi.poosl.Variable <em>Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Variable</em>'.
	 * @see nl.esi.poosl.Variable
	 * @generated
	 */
	EClass getVariable();

	/**
	 * Returns the meta object for the attribute '{@link nl.esi.poosl.Variable#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see nl.esi.poosl.Variable#getName()
	 * @see #getVariable()
	 * @generated
	 */
	EAttribute getVariable_Name();

	/**
	 * Returns the meta object for class '{@link nl.esi.poosl.DataClass <em>Data Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Data Class</em>'.
	 * @see nl.esi.poosl.DataClass
	 * @generated
	 */
	EClass getDataClass();

	/**
	 * Returns the meta object for the attribute '{@link nl.esi.poosl.DataClass#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see nl.esi.poosl.DataClass#getName()
	 * @see #getDataClass()
	 * @generated
	 */
	EAttribute getDataClass_Name();

	/**
	 * Returns the meta object for the attribute '{@link nl.esi.poosl.DataClass#isNative <em>Native</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Native</em>'.
	 * @see nl.esi.poosl.DataClass#isNative()
	 * @see #getDataClass()
	 * @generated
	 */
	EAttribute getDataClass_Native();

	/**
	 * Returns the meta object for the containment reference list '{@link nl.esi.poosl.DataClass#getInstanceVariables <em>Instance Variables</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Instance Variables</em>'.
	 * @see nl.esi.poosl.DataClass#getInstanceVariables()
	 * @see #getDataClass()
	 * @generated
	 */
	EReference getDataClass_InstanceVariables();

	/**
	 * Returns the meta object for the containment reference list '{@link nl.esi.poosl.DataClass#getDataMethodsNamed <em>Data Methods Named</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Data Methods Named</em>'.
	 * @see nl.esi.poosl.DataClass#getDataMethodsNamed()
	 * @see #getDataClass()
	 * @generated
	 */
	EReference getDataClass_DataMethodsNamed();

	/**
	 * Returns the meta object for the containment reference list '{@link nl.esi.poosl.DataClass#getDataMethodsUnaryOperator <em>Data Methods Unary Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Data Methods Unary Operator</em>'.
	 * @see nl.esi.poosl.DataClass#getDataMethodsUnaryOperator()
	 * @see #getDataClass()
	 * @generated
	 */
	EReference getDataClass_DataMethodsUnaryOperator();

	/**
	 * Returns the meta object for the containment reference list '{@link nl.esi.poosl.DataClass#getDataMethodsBinaryOperator <em>Data Methods Binary Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Data Methods Binary Operator</em>'.
	 * @see nl.esi.poosl.DataClass#getDataMethodsBinaryOperator()
	 * @see #getDataClass()
	 * @generated
	 */
	EReference getDataClass_DataMethodsBinaryOperator();

	/**
	 * Returns the meta object for the attribute '{@link nl.esi.poosl.DataClass#getSuperClass <em>Super Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Super Class</em>'.
	 * @see nl.esi.poosl.DataClass#getSuperClass()
	 * @see #getDataClass()
	 * @generated
	 */
	EAttribute getDataClass_SuperClass();

	/**
	 * Returns the meta object for class '{@link nl.esi.poosl.DataMethod <em>Data Method</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Data Method</em>'.
	 * @see nl.esi.poosl.DataMethod
	 * @generated
	 */
	EClass getDataMethod();

	/**
	 * Returns the meta object for the attribute '{@link nl.esi.poosl.DataMethod#isNative <em>Native</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Native</em>'.
	 * @see nl.esi.poosl.DataMethod#isNative()
	 * @see #getDataMethod()
	 * @generated
	 */
	EAttribute getDataMethod_Native();

	/**
	 * Returns the meta object for the containment reference list '{@link nl.esi.poosl.DataMethod#getParameters <em>Parameters</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Parameters</em>'.
	 * @see nl.esi.poosl.DataMethod#getParameters()
	 * @see #getDataMethod()
	 * @generated
	 */
	EReference getDataMethod_Parameters();

	/**
	 * Returns the meta object for the containment reference list '{@link nl.esi.poosl.DataMethod#getLocalVariables <em>Local Variables</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Local Variables</em>'.
	 * @see nl.esi.poosl.DataMethod#getLocalVariables()
	 * @see #getDataMethod()
	 * @generated
	 */
	EReference getDataMethod_LocalVariables();

	/**
	 * Returns the meta object for the containment reference '{@link nl.esi.poosl.DataMethod#getBody <em>Body</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Body</em>'.
	 * @see nl.esi.poosl.DataMethod#getBody()
	 * @see #getDataMethod()
	 * @generated
	 */
	EReference getDataMethod_Body();

	/**
	 * Returns the meta object for the attribute '{@link nl.esi.poosl.DataMethod#getReturnType <em>Return Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Return Type</em>'.
	 * @see nl.esi.poosl.DataMethod#getReturnType()
	 * @see #getDataMethod()
	 * @generated
	 */
	EAttribute getDataMethod_ReturnType();

	/**
	 * Returns the meta object for class '{@link nl.esi.poosl.DataMethodNamed <em>Data Method Named</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Data Method Named</em>'.
	 * @see nl.esi.poosl.DataMethodNamed
	 * @generated
	 */
	EClass getDataMethodNamed();

	/**
	 * Returns the meta object for the attribute '{@link nl.esi.poosl.DataMethodNamed#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see nl.esi.poosl.DataMethodNamed#getName()
	 * @see #getDataMethodNamed()
	 * @generated
	 */
	EAttribute getDataMethodNamed_Name();

	/**
	 * Returns the meta object for class '{@link nl.esi.poosl.DataMethodUnaryOperator <em>Data Method Unary Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Data Method Unary Operator</em>'.
	 * @see nl.esi.poosl.DataMethodUnaryOperator
	 * @generated
	 */
	EClass getDataMethodUnaryOperator();

	/**
	 * Returns the meta object for the attribute '{@link nl.esi.poosl.DataMethodUnaryOperator#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see nl.esi.poosl.DataMethodUnaryOperator#getName()
	 * @see #getDataMethodUnaryOperator()
	 * @generated
	 */
	EAttribute getDataMethodUnaryOperator_Name();

	/**
	 * Returns the meta object for class '{@link nl.esi.poosl.DataMethodBinaryOperator <em>Data Method Binary Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Data Method Binary Operator</em>'.
	 * @see nl.esi.poosl.DataMethodBinaryOperator
	 * @generated
	 */
	EClass getDataMethodBinaryOperator();

	/**
	 * Returns the meta object for the attribute '{@link nl.esi.poosl.DataMethodBinaryOperator#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see nl.esi.poosl.DataMethodBinaryOperator#getName()
	 * @see #getDataMethodBinaryOperator()
	 * @generated
	 */
	EAttribute getDataMethodBinaryOperator_Name();

	/**
	 * Returns the meta object for class '{@link nl.esi.poosl.Expression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Expression</em>'.
	 * @see nl.esi.poosl.Expression
	 * @generated
	 */
	EClass getExpression();

	/**
	 * Returns the meta object for class '{@link nl.esi.poosl.ExpressionSequence <em>Expression Sequence</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Expression Sequence</em>'.
	 * @see nl.esi.poosl.ExpressionSequence
	 * @generated
	 */
	EClass getExpressionSequence();

	/**
	 * Returns the meta object for the containment reference list '{@link nl.esi.poosl.ExpressionSequence#getExpressions <em>Expressions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Expressions</em>'.
	 * @see nl.esi.poosl.ExpressionSequence#getExpressions()
	 * @see #getExpressionSequence()
	 * @generated
	 */
	EReference getExpressionSequence_Expressions();

	/**
	 * Returns the meta object for class '{@link nl.esi.poosl.ExpressionSequenceRoundBracket <em>Expression Sequence Round Bracket</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Expression Sequence Round Bracket</em>'.
	 * @see nl.esi.poosl.ExpressionSequenceRoundBracket
	 * @generated
	 */
	EClass getExpressionSequenceRoundBracket();

	/**
	 * Returns the meta object for class '{@link nl.esi.poosl.AssignmentExpression <em>Assignment Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Assignment Expression</em>'.
	 * @see nl.esi.poosl.AssignmentExpression
	 * @generated
	 */
	EClass getAssignmentExpression();

	/**
	 * Returns the meta object for the containment reference '{@link nl.esi.poosl.AssignmentExpression#getExpression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Expression</em>'.
	 * @see nl.esi.poosl.AssignmentExpression#getExpression()
	 * @see #getAssignmentExpression()
	 * @generated
	 */
	EReference getAssignmentExpression_Expression();

	/**
	 * Returns the meta object for the attribute '{@link nl.esi.poosl.AssignmentExpression#getVariable <em>Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Variable</em>'.
	 * @see nl.esi.poosl.AssignmentExpression#getVariable()
	 * @see #getAssignmentExpression()
	 * @generated
	 */
	EAttribute getAssignmentExpression_Variable();

	/**
	 * Returns the meta object for class '{@link nl.esi.poosl.CurrentTimeExpression <em>Current Time Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Current Time Expression</em>'.
	 * @see nl.esi.poosl.CurrentTimeExpression
	 * @generated
	 */
	EClass getCurrentTimeExpression();

	/**
	 * Returns the meta object for class '{@link nl.esi.poosl.DataMethodCallExpression <em>Data Method Call Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Data Method Call Expression</em>'.
	 * @see nl.esi.poosl.DataMethodCallExpression
	 * @generated
	 */
	EClass getDataMethodCallExpression();

	/**
	 * Returns the meta object for the containment reference '{@link nl.esi.poosl.DataMethodCallExpression#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Target</em>'.
	 * @see nl.esi.poosl.DataMethodCallExpression#getTarget()
	 * @see #getDataMethodCallExpression()
	 * @generated
	 */
	EReference getDataMethodCallExpression_Target();

	/**
	 * Returns the meta object for the attribute '{@link nl.esi.poosl.DataMethodCallExpression#isOnSuperClass <em>On Super Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>On Super Class</em>'.
	 * @see nl.esi.poosl.DataMethodCallExpression#isOnSuperClass()
	 * @see #getDataMethodCallExpression()
	 * @generated
	 */
	EAttribute getDataMethodCallExpression_OnSuperClass();

	/**
	 * Returns the meta object for the attribute '{@link nl.esi.poosl.DataMethodCallExpression#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see nl.esi.poosl.DataMethodCallExpression#getName()
	 * @see #getDataMethodCallExpression()
	 * @generated
	 */
	EAttribute getDataMethodCallExpression_Name();

	/**
	 * Returns the meta object for the containment reference list '{@link nl.esi.poosl.DataMethodCallExpression#getArguments <em>Arguments</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Arguments</em>'.
	 * @see nl.esi.poosl.DataMethodCallExpression#getArguments()
	 * @see #getDataMethodCallExpression()
	 * @generated
	 */
	EReference getDataMethodCallExpression_Arguments();

	/**
	 * Returns the meta object for class '{@link nl.esi.poosl.IfExpression <em>If Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>If Expression</em>'.
	 * @see nl.esi.poosl.IfExpression
	 * @generated
	 */
	EClass getIfExpression();

	/**
	 * Returns the meta object for the containment reference '{@link nl.esi.poosl.IfExpression#getCondition <em>Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Condition</em>'.
	 * @see nl.esi.poosl.IfExpression#getCondition()
	 * @see #getIfExpression()
	 * @generated
	 */
	EReference getIfExpression_Condition();

	/**
	 * Returns the meta object for the containment reference '{@link nl.esi.poosl.IfExpression#getThenClause <em>Then Clause</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Then Clause</em>'.
	 * @see nl.esi.poosl.IfExpression#getThenClause()
	 * @see #getIfExpression()
	 * @generated
	 */
	EReference getIfExpression_ThenClause();

	/**
	 * Returns the meta object for the containment reference '{@link nl.esi.poosl.IfExpression#getElseClause <em>Else Clause</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Else Clause</em>'.
	 * @see nl.esi.poosl.IfExpression#getElseClause()
	 * @see #getIfExpression()
	 * @generated
	 */
	EReference getIfExpression_ElseClause();

	/**
	 * Returns the meta object for class '{@link nl.esi.poosl.SwitchExpression <em>Switch Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Switch Expression</em>'.
	 * @see nl.esi.poosl.SwitchExpression
	 * @generated
	 */
	EClass getSwitchExpression();

	/**
	 * Returns the meta object for the containment reference '{@link nl.esi.poosl.SwitchExpression#getExpression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Expression</em>'.
	 * @see nl.esi.poosl.SwitchExpression#getExpression()
	 * @see #getSwitchExpression()
	 * @generated
	 */
	EReference getSwitchExpression_Expression();

	/**
	 * Returns the meta object for the containment reference list '{@link nl.esi.poosl.SwitchExpression#getCases <em>Cases</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Cases</em>'.
	 * @see nl.esi.poosl.SwitchExpression#getCases()
	 * @see #getSwitchExpression()
	 * @generated
	 */
	EReference getSwitchExpression_Cases();

	/**
	 * Returns the meta object for the containment reference '{@link nl.esi.poosl.SwitchExpression#getDefaultBody <em>Default Body</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Default Body</em>'.
	 * @see nl.esi.poosl.SwitchExpression#getDefaultBody()
	 * @see #getSwitchExpression()
	 * @generated
	 */
	EReference getSwitchExpression_DefaultBody();

	/**
	 * Returns the meta object for class '{@link nl.esi.poosl.SwitchExpressionCase <em>Switch Expression Case</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Switch Expression Case</em>'.
	 * @see nl.esi.poosl.SwitchExpressionCase
	 * @generated
	 */
	EClass getSwitchExpressionCase();

	/**
	 * Returns the meta object for the containment reference '{@link nl.esi.poosl.SwitchExpressionCase#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Value</em>'.
	 * @see nl.esi.poosl.SwitchExpressionCase#getValue()
	 * @see #getSwitchExpressionCase()
	 * @generated
	 */
	EReference getSwitchExpressionCase_Value();

	/**
	 * Returns the meta object for the containment reference '{@link nl.esi.poosl.SwitchExpressionCase#getBody <em>Body</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Body</em>'.
	 * @see nl.esi.poosl.SwitchExpressionCase#getBody()
	 * @see #getSwitchExpressionCase()
	 * @generated
	 */
	EReference getSwitchExpressionCase_Body();

	/**
	 * Returns the meta object for class '{@link nl.esi.poosl.NewExpression <em>New Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>New Expression</em>'.
	 * @see nl.esi.poosl.NewExpression
	 * @generated
	 */
	EClass getNewExpression();

	/**
	 * Returns the meta object for the attribute '{@link nl.esi.poosl.NewExpression#getDataClass <em>Data Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Data Class</em>'.
	 * @see nl.esi.poosl.NewExpression#getDataClass()
	 * @see #getNewExpression()
	 * @generated
	 */
	EAttribute getNewExpression_DataClass();

	/**
	 * Returns the meta object for class '{@link nl.esi.poosl.ReturnExpression <em>Return Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Return Expression</em>'.
	 * @see nl.esi.poosl.ReturnExpression
	 * @generated
	 */
	EClass getReturnExpression();

	/**
	 * Returns the meta object for the containment reference '{@link nl.esi.poosl.ReturnExpression#getExpression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Expression</em>'.
	 * @see nl.esi.poosl.ReturnExpression#getExpression()
	 * @see #getReturnExpression()
	 * @generated
	 */
	EReference getReturnExpression_Expression();

	/**
	 * Returns the meta object for class '{@link nl.esi.poosl.SelfExpression <em>Self Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Self Expression</em>'.
	 * @see nl.esi.poosl.SelfExpression
	 * @generated
	 */
	EClass getSelfExpression();

	/**
	 * Returns the meta object for class '{@link nl.esi.poosl.VariableExpression <em>Variable Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Variable Expression</em>'.
	 * @see nl.esi.poosl.VariableExpression
	 * @generated
	 */
	EClass getVariableExpression();

	/**
	 * Returns the meta object for the attribute '{@link nl.esi.poosl.VariableExpression#getVariable <em>Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Variable</em>'.
	 * @see nl.esi.poosl.VariableExpression#getVariable()
	 * @see #getVariableExpression()
	 * @generated
	 */
	EAttribute getVariableExpression_Variable();

	/**
	 * Returns the meta object for class '{@link nl.esi.poosl.WhileExpression <em>While Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>While Expression</em>'.
	 * @see nl.esi.poosl.WhileExpression
	 * @generated
	 */
	EClass getWhileExpression();

	/**
	 * Returns the meta object for the containment reference '{@link nl.esi.poosl.WhileExpression#getCondition <em>Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Condition</em>'.
	 * @see nl.esi.poosl.WhileExpression#getCondition()
	 * @see #getWhileExpression()
	 * @generated
	 */
	EReference getWhileExpression_Condition();

	/**
	 * Returns the meta object for the containment reference '{@link nl.esi.poosl.WhileExpression#getBody <em>Body</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Body</em>'.
	 * @see nl.esi.poosl.WhileExpression#getBody()
	 * @see #getWhileExpression()
	 * @generated
	 */
	EReference getWhileExpression_Body();

	/**
	 * Returns the meta object for class '{@link nl.esi.poosl.BinaryOperatorExpression <em>Binary Operator Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Binary Operator Expression</em>'.
	 * @see nl.esi.poosl.BinaryOperatorExpression
	 * @generated
	 */
	EClass getBinaryOperatorExpression();

	/**
	 * Returns the meta object for the containment reference '{@link nl.esi.poosl.BinaryOperatorExpression#getLeftOperand <em>Left Operand</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Left Operand</em>'.
	 * @see nl.esi.poosl.BinaryOperatorExpression#getLeftOperand()
	 * @see #getBinaryOperatorExpression()
	 * @generated
	 */
	EReference getBinaryOperatorExpression_LeftOperand();

	/**
	 * Returns the meta object for the attribute '{@link nl.esi.poosl.BinaryOperatorExpression#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see nl.esi.poosl.BinaryOperatorExpression#getName()
	 * @see #getBinaryOperatorExpression()
	 * @generated
	 */
	EAttribute getBinaryOperatorExpression_Name();

	/**
	 * Returns the meta object for the containment reference '{@link nl.esi.poosl.BinaryOperatorExpression#getRightOperand <em>Right Operand</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Right Operand</em>'.
	 * @see nl.esi.poosl.BinaryOperatorExpression#getRightOperand()
	 * @see #getBinaryOperatorExpression()
	 * @generated
	 */
	EReference getBinaryOperatorExpression_RightOperand();

	/**
	 * Returns the meta object for class '{@link nl.esi.poosl.UnaryOperatorExpression <em>Unary Operator Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Unary Operator Expression</em>'.
	 * @see nl.esi.poosl.UnaryOperatorExpression
	 * @generated
	 */
	EClass getUnaryOperatorExpression();

	/**
	 * Returns the meta object for the attribute '{@link nl.esi.poosl.UnaryOperatorExpression#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see nl.esi.poosl.UnaryOperatorExpression#getName()
	 * @see #getUnaryOperatorExpression()
	 * @generated
	 */
	EAttribute getUnaryOperatorExpression_Name();

	/**
	 * Returns the meta object for the containment reference '{@link nl.esi.poosl.UnaryOperatorExpression#getOperand <em>Operand</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Operand</em>'.
	 * @see nl.esi.poosl.UnaryOperatorExpression#getOperand()
	 * @see #getUnaryOperatorExpression()
	 * @generated
	 */
	EReference getUnaryOperatorExpression_Operand();

	/**
	 * Returns the meta object for class '{@link nl.esi.poosl.BooleanConstant <em>Boolean Constant</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Boolean Constant</em>'.
	 * @see nl.esi.poosl.BooleanConstant
	 * @generated
	 */
	EClass getBooleanConstant();

	/**
	 * Returns the meta object for the attribute '{@link nl.esi.poosl.BooleanConstant#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see nl.esi.poosl.BooleanConstant#getValue()
	 * @see #getBooleanConstant()
	 * @generated
	 */
	EAttribute getBooleanConstant_Value();

	/**
	 * Returns the meta object for class '{@link nl.esi.poosl.CharacterConstant <em>Character Constant</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Character Constant</em>'.
	 * @see nl.esi.poosl.CharacterConstant
	 * @generated
	 */
	EClass getCharacterConstant();

	/**
	 * Returns the meta object for the attribute '{@link nl.esi.poosl.CharacterConstant#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see nl.esi.poosl.CharacterConstant#getValue()
	 * @see #getCharacterConstant()
	 * @generated
	 */
	EAttribute getCharacterConstant_Value();

	/**
	 * Returns the meta object for class '{@link nl.esi.poosl.IntegerConstant <em>Integer Constant</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Integer Constant</em>'.
	 * @see nl.esi.poosl.IntegerConstant
	 * @generated
	 */
	EClass getIntegerConstant();

	/**
	 * Returns the meta object for the attribute '{@link nl.esi.poosl.IntegerConstant#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see nl.esi.poosl.IntegerConstant#getValue()
	 * @see #getIntegerConstant()
	 * @generated
	 */
	EAttribute getIntegerConstant_Value();

	/**
	 * Returns the meta object for class '{@link nl.esi.poosl.NilConstant <em>Nil Constant</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Nil Constant</em>'.
	 * @see nl.esi.poosl.NilConstant
	 * @generated
	 */
	EClass getNilConstant();

	/**
	 * Returns the meta object for class '{@link nl.esi.poosl.RealConstant <em>Real Constant</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Real Constant</em>'.
	 * @see nl.esi.poosl.RealConstant
	 * @generated
	 */
	EClass getRealConstant();

	/**
	 * Returns the meta object for the attribute '{@link nl.esi.poosl.RealConstant#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see nl.esi.poosl.RealConstant#getValue()
	 * @see #getRealConstant()
	 * @generated
	 */
	EAttribute getRealConstant_Value();

	/**
	 * Returns the meta object for class '{@link nl.esi.poosl.FloatConstant <em>Float Constant</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Float Constant</em>'.
	 * @see nl.esi.poosl.FloatConstant
	 * @generated
	 */
	EClass getFloatConstant();

	/**
	 * Returns the meta object for the attribute '{@link nl.esi.poosl.FloatConstant#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see nl.esi.poosl.FloatConstant#getValue()
	 * @see #getFloatConstant()
	 * @generated
	 */
	EAttribute getFloatConstant_Value();

	/**
	 * Returns the meta object for class '{@link nl.esi.poosl.StringConstant <em>String Constant</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>String Constant</em>'.
	 * @see nl.esi.poosl.StringConstant
	 * @generated
	 */
	EClass getStringConstant();

	/**
	 * Returns the meta object for the attribute '{@link nl.esi.poosl.StringConstant#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see nl.esi.poosl.StringConstant#getValue()
	 * @see #getStringConstant()
	 * @generated
	 */
	EAttribute getStringConstant_Value();

	/**
	 * Returns the meta object for class '{@link nl.esi.poosl.EnvironmentConstant <em>Environment Constant</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Environment Constant</em>'.
	 * @see nl.esi.poosl.EnvironmentConstant
	 * @generated
	 */
	EClass getEnvironmentConstant();

	/**
	 * Returns the meta object for the attribute '{@link nl.esi.poosl.EnvironmentConstant#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see nl.esi.poosl.EnvironmentConstant#getValue()
	 * @see #getEnvironmentConstant()
	 * @generated
	 */
	EAttribute getEnvironmentConstant_Value();

	/**
	 * Returns the meta object for class '{@link nl.esi.poosl.ProcessClass <em>Process Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Process Class</em>'.
	 * @see nl.esi.poosl.ProcessClass
	 * @generated
	 */
	EClass getProcessClass();

	/**
	 * Returns the meta object for the containment reference list '{@link nl.esi.poosl.ProcessClass#getReceiveMessages <em>Receive Messages</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Receive Messages</em>'.
	 * @see nl.esi.poosl.ProcessClass#getReceiveMessages()
	 * @see #getProcessClass()
	 * @generated
	 */
	EReference getProcessClass_ReceiveMessages();

	/**
	 * Returns the meta object for the containment reference list '{@link nl.esi.poosl.ProcessClass#getSendMessages <em>Send Messages</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Send Messages</em>'.
	 * @see nl.esi.poosl.ProcessClass#getSendMessages()
	 * @see #getProcessClass()
	 * @generated
	 */
	EReference getProcessClass_SendMessages();

	/**
	 * Returns the meta object for the containment reference list '{@link nl.esi.poosl.ProcessClass#getInstanceVariables <em>Instance Variables</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Instance Variables</em>'.
	 * @see nl.esi.poosl.ProcessClass#getInstanceVariables()
	 * @see #getProcessClass()
	 * @generated
	 */
	EReference getProcessClass_InstanceVariables();

	/**
	 * Returns the meta object for the containment reference list '{@link nl.esi.poosl.ProcessClass#getMethods <em>Methods</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Methods</em>'.
	 * @see nl.esi.poosl.ProcessClass#getMethods()
	 * @see #getProcessClass()
	 * @generated
	 */
	EReference getProcessClass_Methods();

	/**
	 * Returns the meta object for the containment reference '{@link nl.esi.poosl.ProcessClass#getInitialMethodCall <em>Initial Method Call</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Initial Method Call</em>'.
	 * @see nl.esi.poosl.ProcessClass#getInitialMethodCall()
	 * @see #getProcessClass()
	 * @generated
	 */
	EReference getProcessClass_InitialMethodCall();

	/**
	 * Returns the meta object for the attribute '{@link nl.esi.poosl.ProcessClass#getSuperClass <em>Super Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Super Class</em>'.
	 * @see nl.esi.poosl.ProcessClass#getSuperClass()
	 * @see #getProcessClass()
	 * @generated
	 */
	EAttribute getProcessClass_SuperClass();

	/**
	 * Returns the meta object for class '{@link nl.esi.poosl.MessageSignature <em>Message Signature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Message Signature</em>'.
	 * @see nl.esi.poosl.MessageSignature
	 * @generated
	 */
	EClass getMessageSignature();

	/**
	 * Returns the meta object for the attribute '{@link nl.esi.poosl.MessageSignature#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see nl.esi.poosl.MessageSignature#getName()
	 * @see #getMessageSignature()
	 * @generated
	 */
	EAttribute getMessageSignature_Name();

	/**
	 * Returns the meta object for the containment reference list '{@link nl.esi.poosl.MessageSignature#getParameters <em>Parameters</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Parameters</em>'.
	 * @see nl.esi.poosl.MessageSignature#getParameters()
	 * @see #getMessageSignature()
	 * @generated
	 */
	EReference getMessageSignature_Parameters();

	/**
	 * Returns the meta object for the containment reference '{@link nl.esi.poosl.MessageSignature#getPort <em>Port</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Port</em>'.
	 * @see nl.esi.poosl.MessageSignature#getPort()
	 * @see #getMessageSignature()
	 * @generated
	 */
	EReference getMessageSignature_Port();

	/**
	 * Returns the meta object for class '{@link nl.esi.poosl.MessageParameter <em>Message Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Message Parameter</em>'.
	 * @see nl.esi.poosl.MessageParameter
	 * @generated
	 */
	EClass getMessageParameter();

	/**
	 * Returns the meta object for the attribute '{@link nl.esi.poosl.MessageParameter#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see nl.esi.poosl.MessageParameter#getType()
	 * @see #getMessageParameter()
	 * @generated
	 */
	EAttribute getMessageParameter_Type();

	/**
	 * Returns the meta object for class '{@link nl.esi.poosl.ProcessMethod <em>Process Method</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Process Method</em>'.
	 * @see nl.esi.poosl.ProcessMethod
	 * @generated
	 */
	EClass getProcessMethod();

	/**
	 * Returns the meta object for the attribute '{@link nl.esi.poosl.ProcessMethod#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see nl.esi.poosl.ProcessMethod#getName()
	 * @see #getProcessMethod()
	 * @generated
	 */
	EAttribute getProcessMethod_Name();

	/**
	 * Returns the meta object for the containment reference list '{@link nl.esi.poosl.ProcessMethod#getInputParameters <em>Input Parameters</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Input Parameters</em>'.
	 * @see nl.esi.poosl.ProcessMethod#getInputParameters()
	 * @see #getProcessMethod()
	 * @generated
	 */
	EReference getProcessMethod_InputParameters();

	/**
	 * Returns the meta object for the containment reference list '{@link nl.esi.poosl.ProcessMethod#getOutputParameters <em>Output Parameters</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Output Parameters</em>'.
	 * @see nl.esi.poosl.ProcessMethod#getOutputParameters()
	 * @see #getProcessMethod()
	 * @generated
	 */
	EReference getProcessMethod_OutputParameters();

	/**
	 * Returns the meta object for the containment reference list '{@link nl.esi.poosl.ProcessMethod#getLocalVariables <em>Local Variables</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Local Variables</em>'.
	 * @see nl.esi.poosl.ProcessMethod#getLocalVariables()
	 * @see #getProcessMethod()
	 * @generated
	 */
	EReference getProcessMethod_LocalVariables();

	/**
	 * Returns the meta object for the containment reference '{@link nl.esi.poosl.ProcessMethod#getBody <em>Body</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Body</em>'.
	 * @see nl.esi.poosl.ProcessMethod#getBody()
	 * @see #getProcessMethod()
	 * @generated
	 */
	EReference getProcessMethod_Body();

	/**
	 * Returns the meta object for class '{@link nl.esi.poosl.Statement <em>Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Statement</em>'.
	 * @see nl.esi.poosl.Statement
	 * @generated
	 */
	EClass getStatement();

	/**
	 * Returns the meta object for class '{@link nl.esi.poosl.StatementSequence <em>Statement Sequence</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Statement Sequence</em>'.
	 * @see nl.esi.poosl.StatementSequence
	 * @generated
	 */
	EClass getStatementSequence();

	/**
	 * Returns the meta object for the containment reference list '{@link nl.esi.poosl.StatementSequence#getStatements <em>Statements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Statements</em>'.
	 * @see nl.esi.poosl.StatementSequence#getStatements()
	 * @see #getStatementSequence()
	 * @generated
	 */
	EReference getStatementSequence_Statements();

	/**
	 * Returns the meta object for class '{@link nl.esi.poosl.StatementSequenceRoundBracket <em>Statement Sequence Round Bracket</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Statement Sequence Round Bracket</em>'.
	 * @see nl.esi.poosl.StatementSequenceRoundBracket
	 * @generated
	 */
	EClass getStatementSequenceRoundBracket();

	/**
	 * Returns the meta object for class '{@link nl.esi.poosl.AbortStatement <em>Abort Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Abort Statement</em>'.
	 * @see nl.esi.poosl.AbortStatement
	 * @generated
	 */
	EClass getAbortStatement();

	/**
	 * Returns the meta object for the containment reference '{@link nl.esi.poosl.AbortStatement#getNormalClause <em>Normal Clause</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Normal Clause</em>'.
	 * @see nl.esi.poosl.AbortStatement#getNormalClause()
	 * @see #getAbortStatement()
	 * @generated
	 */
	EReference getAbortStatement_NormalClause();

	/**
	 * Returns the meta object for the containment reference '{@link nl.esi.poosl.AbortStatement#getAbortingClause <em>Aborting Clause</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Aborting Clause</em>'.
	 * @see nl.esi.poosl.AbortStatement#getAbortingClause()
	 * @see #getAbortStatement()
	 * @generated
	 */
	EReference getAbortStatement_AbortingClause();

	/**
	 * Returns the meta object for class '{@link nl.esi.poosl.DelayStatement <em>Delay Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Delay Statement</em>'.
	 * @see nl.esi.poosl.DelayStatement
	 * @generated
	 */
	EClass getDelayStatement();

	/**
	 * Returns the meta object for the containment reference '{@link nl.esi.poosl.DelayStatement#getExpression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Expression</em>'.
	 * @see nl.esi.poosl.DelayStatement#getExpression()
	 * @see #getDelayStatement()
	 * @generated
	 */
	EReference getDelayStatement_Expression();

	/**
	 * Returns the meta object for class '{@link nl.esi.poosl.ExpressionStatement <em>Expression Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Expression Statement</em>'.
	 * @see nl.esi.poosl.ExpressionStatement
	 * @generated
	 */
	EClass getExpressionStatement();

	/**
	 * Returns the meta object for the containment reference '{@link nl.esi.poosl.ExpressionStatement#getExpression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Expression</em>'.
	 * @see nl.esi.poosl.ExpressionStatement#getExpression()
	 * @see #getExpressionStatement()
	 * @generated
	 */
	EReference getExpressionStatement_Expression();

	/**
	 * Returns the meta object for class '{@link nl.esi.poosl.GuardedStatement <em>Guarded Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Guarded Statement</em>'.
	 * @see nl.esi.poosl.GuardedStatement
	 * @generated
	 */
	EClass getGuardedStatement();

	/**
	 * Returns the meta object for the containment reference '{@link nl.esi.poosl.GuardedStatement#getGuard <em>Guard</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Guard</em>'.
	 * @see nl.esi.poosl.GuardedStatement#getGuard()
	 * @see #getGuardedStatement()
	 * @generated
	 */
	EReference getGuardedStatement_Guard();

	/**
	 * Returns the meta object for the containment reference '{@link nl.esi.poosl.GuardedStatement#getStatement <em>Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Statement</em>'.
	 * @see nl.esi.poosl.GuardedStatement#getStatement()
	 * @see #getGuardedStatement()
	 * @generated
	 */
	EReference getGuardedStatement_Statement();

	/**
	 * Returns the meta object for class '{@link nl.esi.poosl.IfStatement <em>If Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>If Statement</em>'.
	 * @see nl.esi.poosl.IfStatement
	 * @generated
	 */
	EClass getIfStatement();

	/**
	 * Returns the meta object for the containment reference '{@link nl.esi.poosl.IfStatement#getCondition <em>Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Condition</em>'.
	 * @see nl.esi.poosl.IfStatement#getCondition()
	 * @see #getIfStatement()
	 * @generated
	 */
	EReference getIfStatement_Condition();

	/**
	 * Returns the meta object for the containment reference '{@link nl.esi.poosl.IfStatement#getThenClause <em>Then Clause</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Then Clause</em>'.
	 * @see nl.esi.poosl.IfStatement#getThenClause()
	 * @see #getIfStatement()
	 * @generated
	 */
	EReference getIfStatement_ThenClause();

	/**
	 * Returns the meta object for the containment reference '{@link nl.esi.poosl.IfStatement#getElseClause <em>Else Clause</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Else Clause</em>'.
	 * @see nl.esi.poosl.IfStatement#getElseClause()
	 * @see #getIfStatement()
	 * @generated
	 */
	EReference getIfStatement_ElseClause();

	/**
	 * Returns the meta object for class '{@link nl.esi.poosl.SwitchStatement <em>Switch Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Switch Statement</em>'.
	 * @see nl.esi.poosl.SwitchStatement
	 * @generated
	 */
	EClass getSwitchStatement();

	/**
	 * Returns the meta object for the containment reference '{@link nl.esi.poosl.SwitchStatement#getExpression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Expression</em>'.
	 * @see nl.esi.poosl.SwitchStatement#getExpression()
	 * @see #getSwitchStatement()
	 * @generated
	 */
	EReference getSwitchStatement_Expression();

	/**
	 * Returns the meta object for the containment reference list '{@link nl.esi.poosl.SwitchStatement#getCases <em>Cases</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Cases</em>'.
	 * @see nl.esi.poosl.SwitchStatement#getCases()
	 * @see #getSwitchStatement()
	 * @generated
	 */
	EReference getSwitchStatement_Cases();

	/**
	 * Returns the meta object for the containment reference '{@link nl.esi.poosl.SwitchStatement#getDefaultBody <em>Default Body</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Default Body</em>'.
	 * @see nl.esi.poosl.SwitchStatement#getDefaultBody()
	 * @see #getSwitchStatement()
	 * @generated
	 */
	EReference getSwitchStatement_DefaultBody();

	/**
	 * Returns the meta object for class '{@link nl.esi.poosl.SwitchStatementCase <em>Switch Statement Case</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Switch Statement Case</em>'.
	 * @see nl.esi.poosl.SwitchStatementCase
	 * @generated
	 */
	EClass getSwitchStatementCase();

	/**
	 * Returns the meta object for the containment reference '{@link nl.esi.poosl.SwitchStatementCase#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Value</em>'.
	 * @see nl.esi.poosl.SwitchStatementCase#getValue()
	 * @see #getSwitchStatementCase()
	 * @generated
	 */
	EReference getSwitchStatementCase_Value();

	/**
	 * Returns the meta object for the containment reference '{@link nl.esi.poosl.SwitchStatementCase#getBody <em>Body</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Body</em>'.
	 * @see nl.esi.poosl.SwitchStatementCase#getBody()
	 * @see #getSwitchStatementCase()
	 * @generated
	 */
	EReference getSwitchStatementCase_Body();

	/**
	 * Returns the meta object for class '{@link nl.esi.poosl.InterruptStatement <em>Interrupt Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Interrupt Statement</em>'.
	 * @see nl.esi.poosl.InterruptStatement
	 * @generated
	 */
	EClass getInterruptStatement();

	/**
	 * Returns the meta object for the containment reference '{@link nl.esi.poosl.InterruptStatement#getNormalClause <em>Normal Clause</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Normal Clause</em>'.
	 * @see nl.esi.poosl.InterruptStatement#getNormalClause()
	 * @see #getInterruptStatement()
	 * @generated
	 */
	EReference getInterruptStatement_NormalClause();

	/**
	 * Returns the meta object for the containment reference '{@link nl.esi.poosl.InterruptStatement#getInterruptingClause <em>Interrupting Clause</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Interrupting Clause</em>'.
	 * @see nl.esi.poosl.InterruptStatement#getInterruptingClause()
	 * @see #getInterruptStatement()
	 * @generated
	 */
	EReference getInterruptStatement_InterruptingClause();

	/**
	 * Returns the meta object for class '{@link nl.esi.poosl.ParStatement <em>Par Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Par Statement</em>'.
	 * @see nl.esi.poosl.ParStatement
	 * @generated
	 */
	EClass getParStatement();

	/**
	 * Returns the meta object for the containment reference list '{@link nl.esi.poosl.ParStatement#getClauses <em>Clauses</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Clauses</em>'.
	 * @see nl.esi.poosl.ParStatement#getClauses()
	 * @see #getParStatement()
	 * @generated
	 */
	EReference getParStatement_Clauses();

	/**
	 * Returns the meta object for class '{@link nl.esi.poosl.ProcessMethodCall <em>Process Method Call</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Process Method Call</em>'.
	 * @see nl.esi.poosl.ProcessMethodCall
	 * @generated
	 */
	EClass getProcessMethodCall();

	/**
	 * Returns the meta object for the containment reference list '{@link nl.esi.poosl.ProcessMethodCall#getInputArguments <em>Input Arguments</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Input Arguments</em>'.
	 * @see nl.esi.poosl.ProcessMethodCall#getInputArguments()
	 * @see #getProcessMethodCall()
	 * @generated
	 */
	EReference getProcessMethodCall_InputArguments();

	/**
	 * Returns the meta object for the containment reference list '{@link nl.esi.poosl.ProcessMethodCall#getOutputVariables <em>Output Variables</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Output Variables</em>'.
	 * @see nl.esi.poosl.ProcessMethodCall#getOutputVariables()
	 * @see #getProcessMethodCall()
	 * @generated
	 */
	EReference getProcessMethodCall_OutputVariables();

	/**
	 * Returns the meta object for the attribute '{@link nl.esi.poosl.ProcessMethodCall#getMethod <em>Method</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Method</em>'.
	 * @see nl.esi.poosl.ProcessMethodCall#getMethod()
	 * @see #getProcessMethodCall()
	 * @generated
	 */
	EAttribute getProcessMethodCall_Method();

	/**
	 * Returns the meta object for class '{@link nl.esi.poosl.ReceiveStatement <em>Receive Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Receive Statement</em>'.
	 * @see nl.esi.poosl.ReceiveStatement
	 * @generated
	 */
	EClass getReceiveStatement();

	/**
	 * Returns the meta object for the containment reference '{@link nl.esi.poosl.ReceiveStatement#getPortDescriptor <em>Port Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Port Descriptor</em>'.
	 * @see nl.esi.poosl.ReceiveStatement#getPortDescriptor()
	 * @see #getReceiveStatement()
	 * @generated
	 */
	EReference getReceiveStatement_PortDescriptor();

	/**
	 * Returns the meta object for the attribute '{@link nl.esi.poosl.ReceiveStatement#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see nl.esi.poosl.ReceiveStatement#getName()
	 * @see #getReceiveStatement()
	 * @generated
	 */
	EAttribute getReceiveStatement_Name();

	/**
	 * Returns the meta object for the containment reference list '{@link nl.esi.poosl.ReceiveStatement#getVariables <em>Variables</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Variables</em>'.
	 * @see nl.esi.poosl.ReceiveStatement#getVariables()
	 * @see #getReceiveStatement()
	 * @generated
	 */
	EReference getReceiveStatement_Variables();

	/**
	 * Returns the meta object for the containment reference '{@link nl.esi.poosl.ReceiveStatement#getReceptionCondition <em>Reception Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Reception Condition</em>'.
	 * @see nl.esi.poosl.ReceiveStatement#getReceptionCondition()
	 * @see #getReceiveStatement()
	 * @generated
	 */
	EReference getReceiveStatement_ReceptionCondition();

	/**
	 * Returns the meta object for the containment reference '{@link nl.esi.poosl.ReceiveStatement#getPostCommunicationExpression <em>Post Communication Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Post Communication Expression</em>'.
	 * @see nl.esi.poosl.ReceiveStatement#getPostCommunicationExpression()
	 * @see #getReceiveStatement()
	 * @generated
	 */
	EReference getReceiveStatement_PostCommunicationExpression();

	/**
	 * Returns the meta object for class '{@link nl.esi.poosl.SendStatement <em>Send Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Send Statement</em>'.
	 * @see nl.esi.poosl.SendStatement
	 * @generated
	 */
	EClass getSendStatement();

	/**
	 * Returns the meta object for the containment reference '{@link nl.esi.poosl.SendStatement#getPortDescriptor <em>Port Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Port Descriptor</em>'.
	 * @see nl.esi.poosl.SendStatement#getPortDescriptor()
	 * @see #getSendStatement()
	 * @generated
	 */
	EReference getSendStatement_PortDescriptor();

	/**
	 * Returns the meta object for the attribute '{@link nl.esi.poosl.SendStatement#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see nl.esi.poosl.SendStatement#getName()
	 * @see #getSendStatement()
	 * @generated
	 */
	EAttribute getSendStatement_Name();

	/**
	 * Returns the meta object for the containment reference list '{@link nl.esi.poosl.SendStatement#getArguments <em>Arguments</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Arguments</em>'.
	 * @see nl.esi.poosl.SendStatement#getArguments()
	 * @see #getSendStatement()
	 * @generated
	 */
	EReference getSendStatement_Arguments();

	/**
	 * Returns the meta object for the containment reference '{@link nl.esi.poosl.SendStatement#getPostCommunicationExpression <em>Post Communication Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Post Communication Expression</em>'.
	 * @see nl.esi.poosl.SendStatement#getPostCommunicationExpression()
	 * @see #getSendStatement()
	 * @generated
	 */
	EReference getSendStatement_PostCommunicationExpression();

	/**
	 * Returns the meta object for class '{@link nl.esi.poosl.SelStatement <em>Sel Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sel Statement</em>'.
	 * @see nl.esi.poosl.SelStatement
	 * @generated
	 */
	EClass getSelStatement();

	/**
	 * Returns the meta object for the containment reference list '{@link nl.esi.poosl.SelStatement#getClauses <em>Clauses</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Clauses</em>'.
	 * @see nl.esi.poosl.SelStatement#getClauses()
	 * @see #getSelStatement()
	 * @generated
	 */
	EReference getSelStatement_Clauses();

	/**
	 * Returns the meta object for class '{@link nl.esi.poosl.SkipStatement <em>Skip Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Skip Statement</em>'.
	 * @see nl.esi.poosl.SkipStatement
	 * @generated
	 */
	EClass getSkipStatement();

	/**
	 * Returns the meta object for class '{@link nl.esi.poosl.WhileStatement <em>While Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>While Statement</em>'.
	 * @see nl.esi.poosl.WhileStatement
	 * @generated
	 */
	EClass getWhileStatement();

	/**
	 * Returns the meta object for the containment reference '{@link nl.esi.poosl.WhileStatement#getCondition <em>Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Condition</em>'.
	 * @see nl.esi.poosl.WhileStatement#getCondition()
	 * @see #getWhileStatement()
	 * @generated
	 */
	EReference getWhileStatement_Condition();

	/**
	 * Returns the meta object for the containment reference '{@link nl.esi.poosl.WhileStatement#getBody <em>Body</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Body</em>'.
	 * @see nl.esi.poosl.WhileStatement#getBody()
	 * @see #getWhileStatement()
	 * @generated
	 */
	EReference getWhileStatement_Body();

	/**
	 * Returns the meta object for class '{@link nl.esi.poosl.PortDescriptor <em>Port Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Port Descriptor</em>'.
	 * @see nl.esi.poosl.PortDescriptor
	 * @generated
	 */
	EClass getPortDescriptor();

	/**
	 * Returns the meta object for class '{@link nl.esi.poosl.PortExpression <em>Port Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Port Expression</em>'.
	 * @see nl.esi.poosl.PortExpression
	 * @generated
	 */
	EClass getPortExpression();

	/**
	 * Returns the meta object for the containment reference '{@link nl.esi.poosl.PortExpression#getExpression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Expression</em>'.
	 * @see nl.esi.poosl.PortExpression#getExpression()
	 * @see #getPortExpression()
	 * @generated
	 */
	EReference getPortExpression_Expression();

	/**
	 * Returns the meta object for class '{@link nl.esi.poosl.PortReference <em>Port Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Port Reference</em>'.
	 * @see nl.esi.poosl.PortReference
	 * @generated
	 */
	EClass getPortReference();

	/**
	 * Returns the meta object for the attribute '{@link nl.esi.poosl.PortReference#getPort <em>Port</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Port</em>'.
	 * @see nl.esi.poosl.PortReference#getPort()
	 * @see #getPortReference()
	 * @generated
	 */
	EAttribute getPortReference_Port();

	/**
	 * Returns the meta object for class '{@link nl.esi.poosl.ClusterClass <em>Cluster Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Cluster Class</em>'.
	 * @see nl.esi.poosl.ClusterClass
	 * @generated
	 */
	EClass getClusterClass();

	/**
	 * Returns the meta object for the containment reference list '{@link nl.esi.poosl.ClusterClass#getChannels <em>Channels</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Channels</em>'.
	 * @see nl.esi.poosl.ClusterClass#getChannels()
	 * @see #getClusterClass()
	 * @generated
	 */
	EReference getClusterClass_Channels();

	/**
	 * Returns the meta object for the containment reference list '{@link nl.esi.poosl.ClusterClass#getInstances <em>Instances</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Instances</em>'.
	 * @see nl.esi.poosl.ClusterClass#getInstances()
	 * @see #getClusterClass()
	 * @generated
	 */
	EReference getClusterClass_Instances();

	/**
	 * Returns the meta object for class '{@link nl.esi.poosl.OutputVariable <em>Output Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Output Variable</em>'.
	 * @see nl.esi.poosl.OutputVariable
	 * @generated
	 */
	EClass getOutputVariable();

	/**
	 * Returns the meta object for the attribute '{@link nl.esi.poosl.OutputVariable#getVariable <em>Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Variable</em>'.
	 * @see nl.esi.poosl.OutputVariable#getVariable()
	 * @see #getOutputVariable()
	 * @generated
	 */
	EAttribute getOutputVariable_Variable();

	/**
	 * Returns the meta object for class '{@link nl.esi.poosl.Annotation <em>Annotation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Annotation</em>'.
	 * @see nl.esi.poosl.Annotation
	 * @generated
	 */
	EClass getAnnotation();

	/**
	 * Returns the meta object for the attribute '{@link nl.esi.poosl.Annotation#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see nl.esi.poosl.Annotation#getName()
	 * @see #getAnnotation()
	 * @generated
	 */
	EAttribute getAnnotation_Name();

	/**
	 * Returns the meta object for the containment reference list '{@link nl.esi.poosl.Annotation#getArguments <em>Arguments</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Arguments</em>'.
	 * @see nl.esi.poosl.Annotation#getArguments()
	 * @see #getAnnotation()
	 * @generated
	 */
	EReference getAnnotation_Arguments();

	/**
	 * Returns the meta object for class '{@link nl.esi.poosl.Annotable <em>Annotable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Annotable</em>'.
	 * @see nl.esi.poosl.Annotable
	 * @generated
	 */
	EClass getAnnotable();

	/**
	 * Returns the meta object for the containment reference list '{@link nl.esi.poosl.Annotable#getAnnotations <em>Annotations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Annotations</em>'.
	 * @see nl.esi.poosl.Annotable#getAnnotations()
	 * @see #getAnnotable()
	 * @generated
	 */
	EReference getAnnotable_Annotations();

	/**
	 * Returns the meta object for enum '{@link nl.esi.poosl.OperatorUnary <em>Operator Unary</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Operator Unary</em>'.
	 * @see nl.esi.poosl.OperatorUnary
	 * @generated
	 */
	EEnum getOperatorUnary();

	/**
	 * Returns the meta object for enum '{@link nl.esi.poosl.OperatorBinary <em>Operator Binary</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Operator Binary</em>'.
	 * @see nl.esi.poosl.OperatorBinary
	 * @generated
	 */
	EEnum getOperatorBinary();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	PooslFactory getPooslFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals
	{
		/**
		 * The meta object literal for the '{@link nl.esi.poosl.impl.PooslImpl <em>Poosl</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see nl.esi.poosl.impl.PooslImpl
		 * @see nl.esi.poosl.impl.PooslPackageImpl#getPoosl()
		 * @generated
		 */
		EClass POOSL = eINSTANCE.getPoosl();

		/**
		 * The meta object literal for the '<em><b>Imports</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference POOSL__IMPORTS = eINSTANCE.getPoosl_Imports();

		/**
		 * The meta object literal for the '<em><b>Data Classes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference POOSL__DATA_CLASSES = eINSTANCE.getPoosl_DataClasses();

		/**
		 * The meta object literal for the '<em><b>Process Classes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference POOSL__PROCESS_CLASSES = eINSTANCE.getPoosl_ProcessClasses();

		/**
		 * The meta object literal for the '<em><b>Cluster Classes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference POOSL__CLUSTER_CLASSES = eINSTANCE.getPoosl_ClusterClasses();

		/**
		 * The meta object literal for the '<em><b>Import Libs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference POOSL__IMPORT_LIBS = eINSTANCE.getPoosl_ImportLibs();

		/**
		 * The meta object literal for the '{@link nl.esi.poosl.impl.ImportImpl <em>Import</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see nl.esi.poosl.impl.ImportImpl
		 * @see nl.esi.poosl.impl.PooslPackageImpl#getImport()
		 * @generated
		 */
		EClass IMPORT = eINSTANCE.getImport();

		/**
		 * The meta object literal for the '<em><b>Import URI</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IMPORT__IMPORT_URI = eINSTANCE.getImport_ImportURI();

		/**
		 * The meta object literal for the '{@link nl.esi.poosl.impl.ChannelImpl <em>Channel</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see nl.esi.poosl.impl.ChannelImpl
		 * @see nl.esi.poosl.impl.PooslPackageImpl#getChannel()
		 * @generated
		 */
		EClass CHANNEL = eINSTANCE.getChannel();

		/**
		 * The meta object literal for the '<em><b>External Port</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CHANNEL__EXTERNAL_PORT = eINSTANCE.getChannel_ExternalPort();

		/**
		 * The meta object literal for the '<em><b>Instance Ports</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CHANNEL__INSTANCE_PORTS = eINSTANCE.getChannel_InstancePorts();

		/**
		 * The meta object literal for the '{@link nl.esi.poosl.impl.InstancePortImpl <em>Instance Port</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see nl.esi.poosl.impl.InstancePortImpl
		 * @see nl.esi.poosl.impl.PooslPackageImpl#getInstancePort()
		 * @generated
		 */
		EClass INSTANCE_PORT = eINSTANCE.getInstancePort();

		/**
		 * The meta object literal for the '<em><b>Instance</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INSTANCE_PORT__INSTANCE = eINSTANCE.getInstancePort_Instance();

		/**
		 * The meta object literal for the '<em><b>Port</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INSTANCE_PORT__PORT = eINSTANCE.getInstancePort_Port();

		/**
		 * The meta object literal for the '{@link nl.esi.poosl.impl.InstanceImpl <em>Instance</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see nl.esi.poosl.impl.InstanceImpl
		 * @see nl.esi.poosl.impl.PooslPackageImpl#getInstance()
		 * @generated
		 */
		EClass INSTANCE = eINSTANCE.getInstance();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INSTANCE__NAME = eINSTANCE.getInstance_Name();

		/**
		 * The meta object literal for the '<em><b>Instance Parameters</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INSTANCE__INSTANCE_PARAMETERS = eINSTANCE.getInstance_InstanceParameters();

		/**
		 * The meta object literal for the '<em><b>Class Definition</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INSTANCE__CLASS_DEFINITION = eINSTANCE.getInstance_ClassDefinition();

		/**
		 * The meta object literal for the '{@link nl.esi.poosl.impl.InstanceParameterImpl <em>Instance Parameter</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see nl.esi.poosl.impl.InstanceParameterImpl
		 * @see nl.esi.poosl.impl.PooslPackageImpl#getInstanceParameter()
		 * @generated
		 */
		EClass INSTANCE_PARAMETER = eINSTANCE.getInstanceParameter();

		/**
		 * The meta object literal for the '<em><b>Expression</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INSTANCE_PARAMETER__EXPRESSION = eINSTANCE.getInstanceParameter_Expression();

		/**
		 * The meta object literal for the '<em><b>Parameter</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INSTANCE_PARAMETER__PARAMETER = eINSTANCE.getInstanceParameter_Parameter();

		/**
		 * The meta object literal for the '{@link nl.esi.poosl.impl.InstantiableClassImpl <em>Instantiable Class</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see nl.esi.poosl.impl.InstantiableClassImpl
		 * @see nl.esi.poosl.impl.PooslPackageImpl#getInstantiableClass()
		 * @generated
		 */
		EClass INSTANTIABLE_CLASS = eINSTANCE.getInstantiableClass();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INSTANTIABLE_CLASS__NAME = eINSTANCE.getInstantiableClass_Name();

		/**
		 * The meta object literal for the '<em><b>Parameters</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INSTANTIABLE_CLASS__PARAMETERS = eINSTANCE.getInstantiableClass_Parameters();

		/**
		 * The meta object literal for the '<em><b>Ports</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INSTANTIABLE_CLASS__PORTS = eINSTANCE.getInstantiableClass_Ports();

		/**
		 * The meta object literal for the '{@link nl.esi.poosl.impl.PortImpl <em>Port</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see nl.esi.poosl.impl.PortImpl
		 * @see nl.esi.poosl.impl.PooslPackageImpl#getPort()
		 * @generated
		 */
		EClass PORT = eINSTANCE.getPort();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PORT__NAME = eINSTANCE.getPort_Name();

		/**
		 * The meta object literal for the '{@link nl.esi.poosl.impl.DeclarationImpl <em>Declaration</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see nl.esi.poosl.impl.DeclarationImpl
		 * @see nl.esi.poosl.impl.PooslPackageImpl#getDeclaration()
		 * @generated
		 */
		EClass DECLARATION = eINSTANCE.getDeclaration();

		/**
		 * The meta object literal for the '<em><b>Variables</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DECLARATION__VARIABLES = eINSTANCE.getDeclaration_Variables();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DECLARATION__TYPE = eINSTANCE.getDeclaration_Type();

		/**
		 * The meta object literal for the '{@link nl.esi.poosl.impl.VariableImpl <em>Variable</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see nl.esi.poosl.impl.VariableImpl
		 * @see nl.esi.poosl.impl.PooslPackageImpl#getVariable()
		 * @generated
		 */
		EClass VARIABLE = eINSTANCE.getVariable();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VARIABLE__NAME = eINSTANCE.getVariable_Name();

		/**
		 * The meta object literal for the '{@link nl.esi.poosl.impl.DataClassImpl <em>Data Class</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see nl.esi.poosl.impl.DataClassImpl
		 * @see nl.esi.poosl.impl.PooslPackageImpl#getDataClass()
		 * @generated
		 */
		EClass DATA_CLASS = eINSTANCE.getDataClass();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATA_CLASS__NAME = eINSTANCE.getDataClass_Name();

		/**
		 * The meta object literal for the '<em><b>Native</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATA_CLASS__NATIVE = eINSTANCE.getDataClass_Native();

		/**
		 * The meta object literal for the '<em><b>Instance Variables</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DATA_CLASS__INSTANCE_VARIABLES = eINSTANCE.getDataClass_InstanceVariables();

		/**
		 * The meta object literal for the '<em><b>Data Methods Named</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DATA_CLASS__DATA_METHODS_NAMED = eINSTANCE.getDataClass_DataMethodsNamed();

		/**
		 * The meta object literal for the '<em><b>Data Methods Unary Operator</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DATA_CLASS__DATA_METHODS_UNARY_OPERATOR = eINSTANCE.getDataClass_DataMethodsUnaryOperator();

		/**
		 * The meta object literal for the '<em><b>Data Methods Binary Operator</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DATA_CLASS__DATA_METHODS_BINARY_OPERATOR = eINSTANCE.getDataClass_DataMethodsBinaryOperator();

		/**
		 * The meta object literal for the '<em><b>Super Class</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATA_CLASS__SUPER_CLASS = eINSTANCE.getDataClass_SuperClass();

		/**
		 * The meta object literal for the '{@link nl.esi.poosl.impl.DataMethodImpl <em>Data Method</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see nl.esi.poosl.impl.DataMethodImpl
		 * @see nl.esi.poosl.impl.PooslPackageImpl#getDataMethod()
		 * @generated
		 */
		EClass DATA_METHOD = eINSTANCE.getDataMethod();

		/**
		 * The meta object literal for the '<em><b>Native</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATA_METHOD__NATIVE = eINSTANCE.getDataMethod_Native();

		/**
		 * The meta object literal for the '<em><b>Parameters</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DATA_METHOD__PARAMETERS = eINSTANCE.getDataMethod_Parameters();

		/**
		 * The meta object literal for the '<em><b>Local Variables</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DATA_METHOD__LOCAL_VARIABLES = eINSTANCE.getDataMethod_LocalVariables();

		/**
		 * The meta object literal for the '<em><b>Body</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DATA_METHOD__BODY = eINSTANCE.getDataMethod_Body();

		/**
		 * The meta object literal for the '<em><b>Return Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATA_METHOD__RETURN_TYPE = eINSTANCE.getDataMethod_ReturnType();

		/**
		 * The meta object literal for the '{@link nl.esi.poosl.impl.DataMethodNamedImpl <em>Data Method Named</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see nl.esi.poosl.impl.DataMethodNamedImpl
		 * @see nl.esi.poosl.impl.PooslPackageImpl#getDataMethodNamed()
		 * @generated
		 */
		EClass DATA_METHOD_NAMED = eINSTANCE.getDataMethodNamed();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATA_METHOD_NAMED__NAME = eINSTANCE.getDataMethodNamed_Name();

		/**
		 * The meta object literal for the '{@link nl.esi.poosl.impl.DataMethodUnaryOperatorImpl <em>Data Method Unary Operator</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see nl.esi.poosl.impl.DataMethodUnaryOperatorImpl
		 * @see nl.esi.poosl.impl.PooslPackageImpl#getDataMethodUnaryOperator()
		 * @generated
		 */
		EClass DATA_METHOD_UNARY_OPERATOR = eINSTANCE.getDataMethodUnaryOperator();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATA_METHOD_UNARY_OPERATOR__NAME = eINSTANCE.getDataMethodUnaryOperator_Name();

		/**
		 * The meta object literal for the '{@link nl.esi.poosl.impl.DataMethodBinaryOperatorImpl <em>Data Method Binary Operator</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see nl.esi.poosl.impl.DataMethodBinaryOperatorImpl
		 * @see nl.esi.poosl.impl.PooslPackageImpl#getDataMethodBinaryOperator()
		 * @generated
		 */
		EClass DATA_METHOD_BINARY_OPERATOR = eINSTANCE.getDataMethodBinaryOperator();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATA_METHOD_BINARY_OPERATOR__NAME = eINSTANCE.getDataMethodBinaryOperator_Name();

		/**
		 * The meta object literal for the '{@link nl.esi.poosl.impl.ExpressionImpl <em>Expression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see nl.esi.poosl.impl.ExpressionImpl
		 * @see nl.esi.poosl.impl.PooslPackageImpl#getExpression()
		 * @generated
		 */
		EClass EXPRESSION = eINSTANCE.getExpression();

		/**
		 * The meta object literal for the '{@link nl.esi.poosl.impl.ExpressionSequenceImpl <em>Expression Sequence</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see nl.esi.poosl.impl.ExpressionSequenceImpl
		 * @see nl.esi.poosl.impl.PooslPackageImpl#getExpressionSequence()
		 * @generated
		 */
		EClass EXPRESSION_SEQUENCE = eINSTANCE.getExpressionSequence();

		/**
		 * The meta object literal for the '<em><b>Expressions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXPRESSION_SEQUENCE__EXPRESSIONS = eINSTANCE.getExpressionSequence_Expressions();

		/**
		 * The meta object literal for the '{@link nl.esi.poosl.impl.ExpressionSequenceRoundBracketImpl <em>Expression Sequence Round Bracket</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see nl.esi.poosl.impl.ExpressionSequenceRoundBracketImpl
		 * @see nl.esi.poosl.impl.PooslPackageImpl#getExpressionSequenceRoundBracket()
		 * @generated
		 */
		EClass EXPRESSION_SEQUENCE_ROUND_BRACKET = eINSTANCE.getExpressionSequenceRoundBracket();

		/**
		 * The meta object literal for the '{@link nl.esi.poosl.impl.AssignmentExpressionImpl <em>Assignment Expression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see nl.esi.poosl.impl.AssignmentExpressionImpl
		 * @see nl.esi.poosl.impl.PooslPackageImpl#getAssignmentExpression()
		 * @generated
		 */
		EClass ASSIGNMENT_EXPRESSION = eINSTANCE.getAssignmentExpression();

		/**
		 * The meta object literal for the '<em><b>Expression</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ASSIGNMENT_EXPRESSION__EXPRESSION = eINSTANCE.getAssignmentExpression_Expression();

		/**
		 * The meta object literal for the '<em><b>Variable</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ASSIGNMENT_EXPRESSION__VARIABLE = eINSTANCE.getAssignmentExpression_Variable();

		/**
		 * The meta object literal for the '{@link nl.esi.poosl.impl.CurrentTimeExpressionImpl <em>Current Time Expression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see nl.esi.poosl.impl.CurrentTimeExpressionImpl
		 * @see nl.esi.poosl.impl.PooslPackageImpl#getCurrentTimeExpression()
		 * @generated
		 */
		EClass CURRENT_TIME_EXPRESSION = eINSTANCE.getCurrentTimeExpression();

		/**
		 * The meta object literal for the '{@link nl.esi.poosl.impl.DataMethodCallExpressionImpl <em>Data Method Call Expression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see nl.esi.poosl.impl.DataMethodCallExpressionImpl
		 * @see nl.esi.poosl.impl.PooslPackageImpl#getDataMethodCallExpression()
		 * @generated
		 */
		EClass DATA_METHOD_CALL_EXPRESSION = eINSTANCE.getDataMethodCallExpression();

		/**
		 * The meta object literal for the '<em><b>Target</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DATA_METHOD_CALL_EXPRESSION__TARGET = eINSTANCE.getDataMethodCallExpression_Target();

		/**
		 * The meta object literal for the '<em><b>On Super Class</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATA_METHOD_CALL_EXPRESSION__ON_SUPER_CLASS = eINSTANCE.getDataMethodCallExpression_OnSuperClass();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATA_METHOD_CALL_EXPRESSION__NAME = eINSTANCE.getDataMethodCallExpression_Name();

		/**
		 * The meta object literal for the '<em><b>Arguments</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DATA_METHOD_CALL_EXPRESSION__ARGUMENTS = eINSTANCE.getDataMethodCallExpression_Arguments();

		/**
		 * The meta object literal for the '{@link nl.esi.poosl.impl.IfExpressionImpl <em>If Expression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see nl.esi.poosl.impl.IfExpressionImpl
		 * @see nl.esi.poosl.impl.PooslPackageImpl#getIfExpression()
		 * @generated
		 */
		EClass IF_EXPRESSION = eINSTANCE.getIfExpression();

		/**
		 * The meta object literal for the '<em><b>Condition</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IF_EXPRESSION__CONDITION = eINSTANCE.getIfExpression_Condition();

		/**
		 * The meta object literal for the '<em><b>Then Clause</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IF_EXPRESSION__THEN_CLAUSE = eINSTANCE.getIfExpression_ThenClause();

		/**
		 * The meta object literal for the '<em><b>Else Clause</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IF_EXPRESSION__ELSE_CLAUSE = eINSTANCE.getIfExpression_ElseClause();

		/**
		 * The meta object literal for the '{@link nl.esi.poosl.impl.SwitchExpressionImpl <em>Switch Expression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see nl.esi.poosl.impl.SwitchExpressionImpl
		 * @see nl.esi.poosl.impl.PooslPackageImpl#getSwitchExpression()
		 * @generated
		 */
		EClass SWITCH_EXPRESSION = eINSTANCE.getSwitchExpression();

		/**
		 * The meta object literal for the '<em><b>Expression</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SWITCH_EXPRESSION__EXPRESSION = eINSTANCE.getSwitchExpression_Expression();

		/**
		 * The meta object literal for the '<em><b>Cases</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SWITCH_EXPRESSION__CASES = eINSTANCE.getSwitchExpression_Cases();

		/**
		 * The meta object literal for the '<em><b>Default Body</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SWITCH_EXPRESSION__DEFAULT_BODY = eINSTANCE.getSwitchExpression_DefaultBody();

		/**
		 * The meta object literal for the '{@link nl.esi.poosl.impl.SwitchExpressionCaseImpl <em>Switch Expression Case</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see nl.esi.poosl.impl.SwitchExpressionCaseImpl
		 * @see nl.esi.poosl.impl.PooslPackageImpl#getSwitchExpressionCase()
		 * @generated
		 */
		EClass SWITCH_EXPRESSION_CASE = eINSTANCE.getSwitchExpressionCase();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SWITCH_EXPRESSION_CASE__VALUE = eINSTANCE.getSwitchExpressionCase_Value();

		/**
		 * The meta object literal for the '<em><b>Body</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SWITCH_EXPRESSION_CASE__BODY = eINSTANCE.getSwitchExpressionCase_Body();

		/**
		 * The meta object literal for the '{@link nl.esi.poosl.impl.NewExpressionImpl <em>New Expression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see nl.esi.poosl.impl.NewExpressionImpl
		 * @see nl.esi.poosl.impl.PooslPackageImpl#getNewExpression()
		 * @generated
		 */
		EClass NEW_EXPRESSION = eINSTANCE.getNewExpression();

		/**
		 * The meta object literal for the '<em><b>Data Class</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NEW_EXPRESSION__DATA_CLASS = eINSTANCE.getNewExpression_DataClass();

		/**
		 * The meta object literal for the '{@link nl.esi.poosl.impl.ReturnExpressionImpl <em>Return Expression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see nl.esi.poosl.impl.ReturnExpressionImpl
		 * @see nl.esi.poosl.impl.PooslPackageImpl#getReturnExpression()
		 * @generated
		 */
		EClass RETURN_EXPRESSION = eINSTANCE.getReturnExpression();

		/**
		 * The meta object literal for the '<em><b>Expression</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RETURN_EXPRESSION__EXPRESSION = eINSTANCE.getReturnExpression_Expression();

		/**
		 * The meta object literal for the '{@link nl.esi.poosl.impl.SelfExpressionImpl <em>Self Expression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see nl.esi.poosl.impl.SelfExpressionImpl
		 * @see nl.esi.poosl.impl.PooslPackageImpl#getSelfExpression()
		 * @generated
		 */
		EClass SELF_EXPRESSION = eINSTANCE.getSelfExpression();

		/**
		 * The meta object literal for the '{@link nl.esi.poosl.impl.VariableExpressionImpl <em>Variable Expression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see nl.esi.poosl.impl.VariableExpressionImpl
		 * @see nl.esi.poosl.impl.PooslPackageImpl#getVariableExpression()
		 * @generated
		 */
		EClass VARIABLE_EXPRESSION = eINSTANCE.getVariableExpression();

		/**
		 * The meta object literal for the '<em><b>Variable</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VARIABLE_EXPRESSION__VARIABLE = eINSTANCE.getVariableExpression_Variable();

		/**
		 * The meta object literal for the '{@link nl.esi.poosl.impl.WhileExpressionImpl <em>While Expression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see nl.esi.poosl.impl.WhileExpressionImpl
		 * @see nl.esi.poosl.impl.PooslPackageImpl#getWhileExpression()
		 * @generated
		 */
		EClass WHILE_EXPRESSION = eINSTANCE.getWhileExpression();

		/**
		 * The meta object literal for the '<em><b>Condition</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WHILE_EXPRESSION__CONDITION = eINSTANCE.getWhileExpression_Condition();

		/**
		 * The meta object literal for the '<em><b>Body</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WHILE_EXPRESSION__BODY = eINSTANCE.getWhileExpression_Body();

		/**
		 * The meta object literal for the '{@link nl.esi.poosl.impl.BinaryOperatorExpressionImpl <em>Binary Operator Expression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see nl.esi.poosl.impl.BinaryOperatorExpressionImpl
		 * @see nl.esi.poosl.impl.PooslPackageImpl#getBinaryOperatorExpression()
		 * @generated
		 */
		EClass BINARY_OPERATOR_EXPRESSION = eINSTANCE.getBinaryOperatorExpression();

		/**
		 * The meta object literal for the '<em><b>Left Operand</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BINARY_OPERATOR_EXPRESSION__LEFT_OPERAND = eINSTANCE.getBinaryOperatorExpression_LeftOperand();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BINARY_OPERATOR_EXPRESSION__NAME = eINSTANCE.getBinaryOperatorExpression_Name();

		/**
		 * The meta object literal for the '<em><b>Right Operand</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BINARY_OPERATOR_EXPRESSION__RIGHT_OPERAND = eINSTANCE.getBinaryOperatorExpression_RightOperand();

		/**
		 * The meta object literal for the '{@link nl.esi.poosl.impl.UnaryOperatorExpressionImpl <em>Unary Operator Expression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see nl.esi.poosl.impl.UnaryOperatorExpressionImpl
		 * @see nl.esi.poosl.impl.PooslPackageImpl#getUnaryOperatorExpression()
		 * @generated
		 */
		EClass UNARY_OPERATOR_EXPRESSION = eINSTANCE.getUnaryOperatorExpression();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute UNARY_OPERATOR_EXPRESSION__NAME = eINSTANCE.getUnaryOperatorExpression_Name();

		/**
		 * The meta object literal for the '<em><b>Operand</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference UNARY_OPERATOR_EXPRESSION__OPERAND = eINSTANCE.getUnaryOperatorExpression_Operand();

		/**
		 * The meta object literal for the '{@link nl.esi.poosl.impl.BooleanConstantImpl <em>Boolean Constant</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see nl.esi.poosl.impl.BooleanConstantImpl
		 * @see nl.esi.poosl.impl.PooslPackageImpl#getBooleanConstant()
		 * @generated
		 */
		EClass BOOLEAN_CONSTANT = eINSTANCE.getBooleanConstant();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BOOLEAN_CONSTANT__VALUE = eINSTANCE.getBooleanConstant_Value();

		/**
		 * The meta object literal for the '{@link nl.esi.poosl.impl.CharacterConstantImpl <em>Character Constant</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see nl.esi.poosl.impl.CharacterConstantImpl
		 * @see nl.esi.poosl.impl.PooslPackageImpl#getCharacterConstant()
		 * @generated
		 */
		EClass CHARACTER_CONSTANT = eINSTANCE.getCharacterConstant();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CHARACTER_CONSTANT__VALUE = eINSTANCE.getCharacterConstant_Value();

		/**
		 * The meta object literal for the '{@link nl.esi.poosl.impl.IntegerConstantImpl <em>Integer Constant</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see nl.esi.poosl.impl.IntegerConstantImpl
		 * @see nl.esi.poosl.impl.PooslPackageImpl#getIntegerConstant()
		 * @generated
		 */
		EClass INTEGER_CONSTANT = eINSTANCE.getIntegerConstant();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INTEGER_CONSTANT__VALUE = eINSTANCE.getIntegerConstant_Value();

		/**
		 * The meta object literal for the '{@link nl.esi.poosl.impl.NilConstantImpl <em>Nil Constant</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see nl.esi.poosl.impl.NilConstantImpl
		 * @see nl.esi.poosl.impl.PooslPackageImpl#getNilConstant()
		 * @generated
		 */
		EClass NIL_CONSTANT = eINSTANCE.getNilConstant();

		/**
		 * The meta object literal for the '{@link nl.esi.poosl.impl.RealConstantImpl <em>Real Constant</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see nl.esi.poosl.impl.RealConstantImpl
		 * @see nl.esi.poosl.impl.PooslPackageImpl#getRealConstant()
		 * @generated
		 */
		EClass REAL_CONSTANT = eINSTANCE.getRealConstant();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REAL_CONSTANT__VALUE = eINSTANCE.getRealConstant_Value();

		/**
		 * The meta object literal for the '{@link nl.esi.poosl.impl.FloatConstantImpl <em>Float Constant</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see nl.esi.poosl.impl.FloatConstantImpl
		 * @see nl.esi.poosl.impl.PooslPackageImpl#getFloatConstant()
		 * @generated
		 */
		EClass FLOAT_CONSTANT = eINSTANCE.getFloatConstant();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FLOAT_CONSTANT__VALUE = eINSTANCE.getFloatConstant_Value();

		/**
		 * The meta object literal for the '{@link nl.esi.poosl.impl.StringConstantImpl <em>String Constant</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see nl.esi.poosl.impl.StringConstantImpl
		 * @see nl.esi.poosl.impl.PooslPackageImpl#getStringConstant()
		 * @generated
		 */
		EClass STRING_CONSTANT = eINSTANCE.getStringConstant();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STRING_CONSTANT__VALUE = eINSTANCE.getStringConstant_Value();

		/**
		 * The meta object literal for the '{@link nl.esi.poosl.impl.EnvironmentConstantImpl <em>Environment Constant</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see nl.esi.poosl.impl.EnvironmentConstantImpl
		 * @see nl.esi.poosl.impl.PooslPackageImpl#getEnvironmentConstant()
		 * @generated
		 */
		EClass ENVIRONMENT_CONSTANT = eINSTANCE.getEnvironmentConstant();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ENVIRONMENT_CONSTANT__VALUE = eINSTANCE.getEnvironmentConstant_Value();

		/**
		 * The meta object literal for the '{@link nl.esi.poosl.impl.ProcessClassImpl <em>Process Class</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see nl.esi.poosl.impl.ProcessClassImpl
		 * @see nl.esi.poosl.impl.PooslPackageImpl#getProcessClass()
		 * @generated
		 */
		EClass PROCESS_CLASS = eINSTANCE.getProcessClass();

		/**
		 * The meta object literal for the '<em><b>Receive Messages</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROCESS_CLASS__RECEIVE_MESSAGES = eINSTANCE.getProcessClass_ReceiveMessages();

		/**
		 * The meta object literal for the '<em><b>Send Messages</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROCESS_CLASS__SEND_MESSAGES = eINSTANCE.getProcessClass_SendMessages();

		/**
		 * The meta object literal for the '<em><b>Instance Variables</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROCESS_CLASS__INSTANCE_VARIABLES = eINSTANCE.getProcessClass_InstanceVariables();

		/**
		 * The meta object literal for the '<em><b>Methods</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROCESS_CLASS__METHODS = eINSTANCE.getProcessClass_Methods();

		/**
		 * The meta object literal for the '<em><b>Initial Method Call</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROCESS_CLASS__INITIAL_METHOD_CALL = eINSTANCE.getProcessClass_InitialMethodCall();

		/**
		 * The meta object literal for the '<em><b>Super Class</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROCESS_CLASS__SUPER_CLASS = eINSTANCE.getProcessClass_SuperClass();

		/**
		 * The meta object literal for the '{@link nl.esi.poosl.impl.MessageSignatureImpl <em>Message Signature</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see nl.esi.poosl.impl.MessageSignatureImpl
		 * @see nl.esi.poosl.impl.PooslPackageImpl#getMessageSignature()
		 * @generated
		 */
		EClass MESSAGE_SIGNATURE = eINSTANCE.getMessageSignature();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MESSAGE_SIGNATURE__NAME = eINSTANCE.getMessageSignature_Name();

		/**
		 * The meta object literal for the '<em><b>Parameters</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MESSAGE_SIGNATURE__PARAMETERS = eINSTANCE.getMessageSignature_Parameters();

		/**
		 * The meta object literal for the '<em><b>Port</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MESSAGE_SIGNATURE__PORT = eINSTANCE.getMessageSignature_Port();

		/**
		 * The meta object literal for the '{@link nl.esi.poosl.impl.MessageParameterImpl <em>Message Parameter</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see nl.esi.poosl.impl.MessageParameterImpl
		 * @see nl.esi.poosl.impl.PooslPackageImpl#getMessageParameter()
		 * @generated
		 */
		EClass MESSAGE_PARAMETER = eINSTANCE.getMessageParameter();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MESSAGE_PARAMETER__TYPE = eINSTANCE.getMessageParameter_Type();

		/**
		 * The meta object literal for the '{@link nl.esi.poosl.impl.ProcessMethodImpl <em>Process Method</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see nl.esi.poosl.impl.ProcessMethodImpl
		 * @see nl.esi.poosl.impl.PooslPackageImpl#getProcessMethod()
		 * @generated
		 */
		EClass PROCESS_METHOD = eINSTANCE.getProcessMethod();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROCESS_METHOD__NAME = eINSTANCE.getProcessMethod_Name();

		/**
		 * The meta object literal for the '<em><b>Input Parameters</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROCESS_METHOD__INPUT_PARAMETERS = eINSTANCE.getProcessMethod_InputParameters();

		/**
		 * The meta object literal for the '<em><b>Output Parameters</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROCESS_METHOD__OUTPUT_PARAMETERS = eINSTANCE.getProcessMethod_OutputParameters();

		/**
		 * The meta object literal for the '<em><b>Local Variables</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROCESS_METHOD__LOCAL_VARIABLES = eINSTANCE.getProcessMethod_LocalVariables();

		/**
		 * The meta object literal for the '<em><b>Body</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROCESS_METHOD__BODY = eINSTANCE.getProcessMethod_Body();

		/**
		 * The meta object literal for the '{@link nl.esi.poosl.impl.StatementImpl <em>Statement</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see nl.esi.poosl.impl.StatementImpl
		 * @see nl.esi.poosl.impl.PooslPackageImpl#getStatement()
		 * @generated
		 */
		EClass STATEMENT = eINSTANCE.getStatement();

		/**
		 * The meta object literal for the '{@link nl.esi.poosl.impl.StatementSequenceImpl <em>Statement Sequence</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see nl.esi.poosl.impl.StatementSequenceImpl
		 * @see nl.esi.poosl.impl.PooslPackageImpl#getStatementSequence()
		 * @generated
		 */
		EClass STATEMENT_SEQUENCE = eINSTANCE.getStatementSequence();

		/**
		 * The meta object literal for the '<em><b>Statements</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STATEMENT_SEQUENCE__STATEMENTS = eINSTANCE.getStatementSequence_Statements();

		/**
		 * The meta object literal for the '{@link nl.esi.poosl.impl.StatementSequenceRoundBracketImpl <em>Statement Sequence Round Bracket</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see nl.esi.poosl.impl.StatementSequenceRoundBracketImpl
		 * @see nl.esi.poosl.impl.PooslPackageImpl#getStatementSequenceRoundBracket()
		 * @generated
		 */
		EClass STATEMENT_SEQUENCE_ROUND_BRACKET = eINSTANCE.getStatementSequenceRoundBracket();

		/**
		 * The meta object literal for the '{@link nl.esi.poosl.impl.AbortStatementImpl <em>Abort Statement</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see nl.esi.poosl.impl.AbortStatementImpl
		 * @see nl.esi.poosl.impl.PooslPackageImpl#getAbortStatement()
		 * @generated
		 */
		EClass ABORT_STATEMENT = eINSTANCE.getAbortStatement();

		/**
		 * The meta object literal for the '<em><b>Normal Clause</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ABORT_STATEMENT__NORMAL_CLAUSE = eINSTANCE.getAbortStatement_NormalClause();

		/**
		 * The meta object literal for the '<em><b>Aborting Clause</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ABORT_STATEMENT__ABORTING_CLAUSE = eINSTANCE.getAbortStatement_AbortingClause();

		/**
		 * The meta object literal for the '{@link nl.esi.poosl.impl.DelayStatementImpl <em>Delay Statement</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see nl.esi.poosl.impl.DelayStatementImpl
		 * @see nl.esi.poosl.impl.PooslPackageImpl#getDelayStatement()
		 * @generated
		 */
		EClass DELAY_STATEMENT = eINSTANCE.getDelayStatement();

		/**
		 * The meta object literal for the '<em><b>Expression</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DELAY_STATEMENT__EXPRESSION = eINSTANCE.getDelayStatement_Expression();

		/**
		 * The meta object literal for the '{@link nl.esi.poosl.impl.ExpressionStatementImpl <em>Expression Statement</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see nl.esi.poosl.impl.ExpressionStatementImpl
		 * @see nl.esi.poosl.impl.PooslPackageImpl#getExpressionStatement()
		 * @generated
		 */
		EClass EXPRESSION_STATEMENT = eINSTANCE.getExpressionStatement();

		/**
		 * The meta object literal for the '<em><b>Expression</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXPRESSION_STATEMENT__EXPRESSION = eINSTANCE.getExpressionStatement_Expression();

		/**
		 * The meta object literal for the '{@link nl.esi.poosl.impl.GuardedStatementImpl <em>Guarded Statement</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see nl.esi.poosl.impl.GuardedStatementImpl
		 * @see nl.esi.poosl.impl.PooslPackageImpl#getGuardedStatement()
		 * @generated
		 */
		EClass GUARDED_STATEMENT = eINSTANCE.getGuardedStatement();

		/**
		 * The meta object literal for the '<em><b>Guard</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GUARDED_STATEMENT__GUARD = eINSTANCE.getGuardedStatement_Guard();

		/**
		 * The meta object literal for the '<em><b>Statement</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GUARDED_STATEMENT__STATEMENT = eINSTANCE.getGuardedStatement_Statement();

		/**
		 * The meta object literal for the '{@link nl.esi.poosl.impl.IfStatementImpl <em>If Statement</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see nl.esi.poosl.impl.IfStatementImpl
		 * @see nl.esi.poosl.impl.PooslPackageImpl#getIfStatement()
		 * @generated
		 */
		EClass IF_STATEMENT = eINSTANCE.getIfStatement();

		/**
		 * The meta object literal for the '<em><b>Condition</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IF_STATEMENT__CONDITION = eINSTANCE.getIfStatement_Condition();

		/**
		 * The meta object literal for the '<em><b>Then Clause</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IF_STATEMENT__THEN_CLAUSE = eINSTANCE.getIfStatement_ThenClause();

		/**
		 * The meta object literal for the '<em><b>Else Clause</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IF_STATEMENT__ELSE_CLAUSE = eINSTANCE.getIfStatement_ElseClause();

		/**
		 * The meta object literal for the '{@link nl.esi.poosl.impl.SwitchStatementImpl <em>Switch Statement</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see nl.esi.poosl.impl.SwitchStatementImpl
		 * @see nl.esi.poosl.impl.PooslPackageImpl#getSwitchStatement()
		 * @generated
		 */
		EClass SWITCH_STATEMENT = eINSTANCE.getSwitchStatement();

		/**
		 * The meta object literal for the '<em><b>Expression</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SWITCH_STATEMENT__EXPRESSION = eINSTANCE.getSwitchStatement_Expression();

		/**
		 * The meta object literal for the '<em><b>Cases</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SWITCH_STATEMENT__CASES = eINSTANCE.getSwitchStatement_Cases();

		/**
		 * The meta object literal for the '<em><b>Default Body</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SWITCH_STATEMENT__DEFAULT_BODY = eINSTANCE.getSwitchStatement_DefaultBody();

		/**
		 * The meta object literal for the '{@link nl.esi.poosl.impl.SwitchStatementCaseImpl <em>Switch Statement Case</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see nl.esi.poosl.impl.SwitchStatementCaseImpl
		 * @see nl.esi.poosl.impl.PooslPackageImpl#getSwitchStatementCase()
		 * @generated
		 */
		EClass SWITCH_STATEMENT_CASE = eINSTANCE.getSwitchStatementCase();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SWITCH_STATEMENT_CASE__VALUE = eINSTANCE.getSwitchStatementCase_Value();

		/**
		 * The meta object literal for the '<em><b>Body</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SWITCH_STATEMENT_CASE__BODY = eINSTANCE.getSwitchStatementCase_Body();

		/**
		 * The meta object literal for the '{@link nl.esi.poosl.impl.InterruptStatementImpl <em>Interrupt Statement</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see nl.esi.poosl.impl.InterruptStatementImpl
		 * @see nl.esi.poosl.impl.PooslPackageImpl#getInterruptStatement()
		 * @generated
		 */
		EClass INTERRUPT_STATEMENT = eINSTANCE.getInterruptStatement();

		/**
		 * The meta object literal for the '<em><b>Normal Clause</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INTERRUPT_STATEMENT__NORMAL_CLAUSE = eINSTANCE.getInterruptStatement_NormalClause();

		/**
		 * The meta object literal for the '<em><b>Interrupting Clause</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INTERRUPT_STATEMENT__INTERRUPTING_CLAUSE = eINSTANCE.getInterruptStatement_InterruptingClause();

		/**
		 * The meta object literal for the '{@link nl.esi.poosl.impl.ParStatementImpl <em>Par Statement</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see nl.esi.poosl.impl.ParStatementImpl
		 * @see nl.esi.poosl.impl.PooslPackageImpl#getParStatement()
		 * @generated
		 */
		EClass PAR_STATEMENT = eINSTANCE.getParStatement();

		/**
		 * The meta object literal for the '<em><b>Clauses</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PAR_STATEMENT__CLAUSES = eINSTANCE.getParStatement_Clauses();

		/**
		 * The meta object literal for the '{@link nl.esi.poosl.impl.ProcessMethodCallImpl <em>Process Method Call</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see nl.esi.poosl.impl.ProcessMethodCallImpl
		 * @see nl.esi.poosl.impl.PooslPackageImpl#getProcessMethodCall()
		 * @generated
		 */
		EClass PROCESS_METHOD_CALL = eINSTANCE.getProcessMethodCall();

		/**
		 * The meta object literal for the '<em><b>Input Arguments</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROCESS_METHOD_CALL__INPUT_ARGUMENTS = eINSTANCE.getProcessMethodCall_InputArguments();

		/**
		 * The meta object literal for the '<em><b>Output Variables</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROCESS_METHOD_CALL__OUTPUT_VARIABLES = eINSTANCE.getProcessMethodCall_OutputVariables();

		/**
		 * The meta object literal for the '<em><b>Method</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROCESS_METHOD_CALL__METHOD = eINSTANCE.getProcessMethodCall_Method();

		/**
		 * The meta object literal for the '{@link nl.esi.poosl.impl.ReceiveStatementImpl <em>Receive Statement</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see nl.esi.poosl.impl.ReceiveStatementImpl
		 * @see nl.esi.poosl.impl.PooslPackageImpl#getReceiveStatement()
		 * @generated
		 */
		EClass RECEIVE_STATEMENT = eINSTANCE.getReceiveStatement();

		/**
		 * The meta object literal for the '<em><b>Port Descriptor</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RECEIVE_STATEMENT__PORT_DESCRIPTOR = eINSTANCE.getReceiveStatement_PortDescriptor();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RECEIVE_STATEMENT__NAME = eINSTANCE.getReceiveStatement_Name();

		/**
		 * The meta object literal for the '<em><b>Variables</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RECEIVE_STATEMENT__VARIABLES = eINSTANCE.getReceiveStatement_Variables();

		/**
		 * The meta object literal for the '<em><b>Reception Condition</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RECEIVE_STATEMENT__RECEPTION_CONDITION = eINSTANCE.getReceiveStatement_ReceptionCondition();

		/**
		 * The meta object literal for the '<em><b>Post Communication Expression</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RECEIVE_STATEMENT__POST_COMMUNICATION_EXPRESSION = eINSTANCE.getReceiveStatement_PostCommunicationExpression();

		/**
		 * The meta object literal for the '{@link nl.esi.poosl.impl.SendStatementImpl <em>Send Statement</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see nl.esi.poosl.impl.SendStatementImpl
		 * @see nl.esi.poosl.impl.PooslPackageImpl#getSendStatement()
		 * @generated
		 */
		EClass SEND_STATEMENT = eINSTANCE.getSendStatement();

		/**
		 * The meta object literal for the '<em><b>Port Descriptor</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SEND_STATEMENT__PORT_DESCRIPTOR = eINSTANCE.getSendStatement_PortDescriptor();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SEND_STATEMENT__NAME = eINSTANCE.getSendStatement_Name();

		/**
		 * The meta object literal for the '<em><b>Arguments</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SEND_STATEMENT__ARGUMENTS = eINSTANCE.getSendStatement_Arguments();

		/**
		 * The meta object literal for the '<em><b>Post Communication Expression</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SEND_STATEMENT__POST_COMMUNICATION_EXPRESSION = eINSTANCE.getSendStatement_PostCommunicationExpression();

		/**
		 * The meta object literal for the '{@link nl.esi.poosl.impl.SelStatementImpl <em>Sel Statement</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see nl.esi.poosl.impl.SelStatementImpl
		 * @see nl.esi.poosl.impl.PooslPackageImpl#getSelStatement()
		 * @generated
		 */
		EClass SEL_STATEMENT = eINSTANCE.getSelStatement();

		/**
		 * The meta object literal for the '<em><b>Clauses</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SEL_STATEMENT__CLAUSES = eINSTANCE.getSelStatement_Clauses();

		/**
		 * The meta object literal for the '{@link nl.esi.poosl.impl.SkipStatementImpl <em>Skip Statement</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see nl.esi.poosl.impl.SkipStatementImpl
		 * @see nl.esi.poosl.impl.PooslPackageImpl#getSkipStatement()
		 * @generated
		 */
		EClass SKIP_STATEMENT = eINSTANCE.getSkipStatement();

		/**
		 * The meta object literal for the '{@link nl.esi.poosl.impl.WhileStatementImpl <em>While Statement</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see nl.esi.poosl.impl.WhileStatementImpl
		 * @see nl.esi.poosl.impl.PooslPackageImpl#getWhileStatement()
		 * @generated
		 */
		EClass WHILE_STATEMENT = eINSTANCE.getWhileStatement();

		/**
		 * The meta object literal for the '<em><b>Condition</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WHILE_STATEMENT__CONDITION = eINSTANCE.getWhileStatement_Condition();

		/**
		 * The meta object literal for the '<em><b>Body</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WHILE_STATEMENT__BODY = eINSTANCE.getWhileStatement_Body();

		/**
		 * The meta object literal for the '{@link nl.esi.poosl.impl.PortDescriptorImpl <em>Port Descriptor</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see nl.esi.poosl.impl.PortDescriptorImpl
		 * @see nl.esi.poosl.impl.PooslPackageImpl#getPortDescriptor()
		 * @generated
		 */
		EClass PORT_DESCRIPTOR = eINSTANCE.getPortDescriptor();

		/**
		 * The meta object literal for the '{@link nl.esi.poosl.impl.PortExpressionImpl <em>Port Expression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see nl.esi.poosl.impl.PortExpressionImpl
		 * @see nl.esi.poosl.impl.PooslPackageImpl#getPortExpression()
		 * @generated
		 */
		EClass PORT_EXPRESSION = eINSTANCE.getPortExpression();

		/**
		 * The meta object literal for the '<em><b>Expression</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PORT_EXPRESSION__EXPRESSION = eINSTANCE.getPortExpression_Expression();

		/**
		 * The meta object literal for the '{@link nl.esi.poosl.impl.PortReferenceImpl <em>Port Reference</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see nl.esi.poosl.impl.PortReferenceImpl
		 * @see nl.esi.poosl.impl.PooslPackageImpl#getPortReference()
		 * @generated
		 */
		EClass PORT_REFERENCE = eINSTANCE.getPortReference();

		/**
		 * The meta object literal for the '<em><b>Port</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PORT_REFERENCE__PORT = eINSTANCE.getPortReference_Port();

		/**
		 * The meta object literal for the '{@link nl.esi.poosl.impl.ClusterClassImpl <em>Cluster Class</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see nl.esi.poosl.impl.ClusterClassImpl
		 * @see nl.esi.poosl.impl.PooslPackageImpl#getClusterClass()
		 * @generated
		 */
		EClass CLUSTER_CLASS = eINSTANCE.getClusterClass();

		/**
		 * The meta object literal for the '<em><b>Channels</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CLUSTER_CLASS__CHANNELS = eINSTANCE.getClusterClass_Channels();

		/**
		 * The meta object literal for the '<em><b>Instances</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CLUSTER_CLASS__INSTANCES = eINSTANCE.getClusterClass_Instances();

		/**
		 * The meta object literal for the '{@link nl.esi.poosl.impl.OutputVariableImpl <em>Output Variable</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see nl.esi.poosl.impl.OutputVariableImpl
		 * @see nl.esi.poosl.impl.PooslPackageImpl#getOutputVariable()
		 * @generated
		 */
		EClass OUTPUT_VARIABLE = eINSTANCE.getOutputVariable();

		/**
		 * The meta object literal for the '<em><b>Variable</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute OUTPUT_VARIABLE__VARIABLE = eINSTANCE.getOutputVariable_Variable();

		/**
		 * The meta object literal for the '{@link nl.esi.poosl.impl.AnnotationImpl <em>Annotation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see nl.esi.poosl.impl.AnnotationImpl
		 * @see nl.esi.poosl.impl.PooslPackageImpl#getAnnotation()
		 * @generated
		 */
		EClass ANNOTATION = eINSTANCE.getAnnotation();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ANNOTATION__NAME = eINSTANCE.getAnnotation_Name();

		/**
		 * The meta object literal for the '<em><b>Arguments</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ANNOTATION__ARGUMENTS = eINSTANCE.getAnnotation_Arguments();

		/**
		 * The meta object literal for the '{@link nl.esi.poosl.impl.AnnotableImpl <em>Annotable</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see nl.esi.poosl.impl.AnnotableImpl
		 * @see nl.esi.poosl.impl.PooslPackageImpl#getAnnotable()
		 * @generated
		 */
		EClass ANNOTABLE = eINSTANCE.getAnnotable();

		/**
		 * The meta object literal for the '<em><b>Annotations</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ANNOTABLE__ANNOTATIONS = eINSTANCE.getAnnotable_Annotations();

		/**
		 * The meta object literal for the '{@link nl.esi.poosl.OperatorUnary <em>Operator Unary</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see nl.esi.poosl.OperatorUnary
		 * @see nl.esi.poosl.impl.PooslPackageImpl#getOperatorUnary()
		 * @generated
		 */
		EEnum OPERATOR_UNARY = eINSTANCE.getOperatorUnary();

		/**
		 * The meta object literal for the '{@link nl.esi.poosl.OperatorBinary <em>Operator Binary</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see nl.esi.poosl.OperatorBinary
		 * @see nl.esi.poosl.impl.PooslPackageImpl#getOperatorBinary()
		 * @generated
		 */
		EEnum OPERATOR_BINARY = eINSTANCE.getOperatorBinary();

	}

} //PooslPackage

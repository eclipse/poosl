/**
 */
package nl.esi.poosl.impl;

import nl.esi.poosl.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class PooslFactoryImpl extends EFactoryImpl implements PooslFactory
{
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static PooslFactory init()
	{
		try {
			PooslFactory thePooslFactory = (PooslFactory)EPackage.Registry.INSTANCE.getEFactory(PooslPackage.eNS_URI);
			if (thePooslFactory != null) {
				return thePooslFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new PooslFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PooslFactoryImpl()
	{
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass)
	{
		switch (eClass.getClassifierID()) {
			case PooslPackage.POOSL: return createPoosl();
			case PooslPackage.IMPORT: return createImport();
			case PooslPackage.CHANNEL: return createChannel();
			case PooslPackage.INSTANCE_PORT: return createInstancePort();
			case PooslPackage.INSTANCE: return createInstance();
			case PooslPackage.INSTANCE_PARAMETER: return createInstanceParameter();
			case PooslPackage.INSTANTIABLE_CLASS: return createInstantiableClass();
			case PooslPackage.PORT: return createPort();
			case PooslPackage.DECLARATION: return createDeclaration();
			case PooslPackage.VARIABLE: return createVariable();
			case PooslPackage.DATA_CLASS: return createDataClass();
			case PooslPackage.DATA_METHOD_NAMED: return createDataMethodNamed();
			case PooslPackage.DATA_METHOD_UNARY_OPERATOR: return createDataMethodUnaryOperator();
			case PooslPackage.DATA_METHOD_BINARY_OPERATOR: return createDataMethodBinaryOperator();
			case PooslPackage.EXPRESSION_SEQUENCE: return createExpressionSequence();
			case PooslPackage.EXPRESSION_SEQUENCE_ROUND_BRACKET: return createExpressionSequenceRoundBracket();
			case PooslPackage.ASSIGNMENT_EXPRESSION: return createAssignmentExpression();
			case PooslPackage.CURRENT_TIME_EXPRESSION: return createCurrentTimeExpression();
			case PooslPackage.DATA_METHOD_CALL_EXPRESSION: return createDataMethodCallExpression();
			case PooslPackage.IF_EXPRESSION: return createIfExpression();
			case PooslPackage.SWITCH_EXPRESSION: return createSwitchExpression();
			case PooslPackage.SWITCH_EXPRESSION_CASE: return createSwitchExpressionCase();
			case PooslPackage.NEW_EXPRESSION: return createNewExpression();
			case PooslPackage.RETURN_EXPRESSION: return createReturnExpression();
			case PooslPackage.SELF_EXPRESSION: return createSelfExpression();
			case PooslPackage.VARIABLE_EXPRESSION: return createVariableExpression();
			case PooslPackage.WHILE_EXPRESSION: return createWhileExpression();
			case PooslPackage.BINARY_OPERATOR_EXPRESSION: return createBinaryOperatorExpression();
			case PooslPackage.UNARY_OPERATOR_EXPRESSION: return createUnaryOperatorExpression();
			case PooslPackage.BOOLEAN_CONSTANT: return createBooleanConstant();
			case PooslPackage.CHARACTER_CONSTANT: return createCharacterConstant();
			case PooslPackage.INTEGER_CONSTANT: return createIntegerConstant();
			case PooslPackage.NIL_CONSTANT: return createNilConstant();
			case PooslPackage.REAL_CONSTANT: return createRealConstant();
			case PooslPackage.FLOAT_CONSTANT: return createFloatConstant();
			case PooslPackage.STRING_CONSTANT: return createStringConstant();
			case PooslPackage.ENVIRONMENT_CONSTANT: return createEnvironmentConstant();
			case PooslPackage.PROCESS_CLASS: return createProcessClass();
			case PooslPackage.MESSAGE_SIGNATURE: return createMessageSignature();
			case PooslPackage.MESSAGE_PARAMETER: return createMessageParameter();
			case PooslPackage.PROCESS_METHOD: return createProcessMethod();
			case PooslPackage.STATEMENT_SEQUENCE: return createStatementSequence();
			case PooslPackage.STATEMENT_SEQUENCE_ROUND_BRACKET: return createStatementSequenceRoundBracket();
			case PooslPackage.ABORT_STATEMENT: return createAbortStatement();
			case PooslPackage.DELAY_STATEMENT: return createDelayStatement();
			case PooslPackage.EXPRESSION_STATEMENT: return createExpressionStatement();
			case PooslPackage.GUARDED_STATEMENT: return createGuardedStatement();
			case PooslPackage.IF_STATEMENT: return createIfStatement();
			case PooslPackage.SWITCH_STATEMENT: return createSwitchStatement();
			case PooslPackage.SWITCH_STATEMENT_CASE: return createSwitchStatementCase();
			case PooslPackage.INTERRUPT_STATEMENT: return createInterruptStatement();
			case PooslPackage.PAR_STATEMENT: return createParStatement();
			case PooslPackage.PROCESS_METHOD_CALL: return createProcessMethodCall();
			case PooslPackage.RECEIVE_STATEMENT: return createReceiveStatement();
			case PooslPackage.SEND_STATEMENT: return createSendStatement();
			case PooslPackage.SEL_STATEMENT: return createSelStatement();
			case PooslPackage.SKIP_STATEMENT: return createSkipStatement();
			case PooslPackage.WHILE_STATEMENT: return createWhileStatement();
			case PooslPackage.PORT_EXPRESSION: return createPortExpression();
			case PooslPackage.PORT_REFERENCE: return createPortReference();
			case PooslPackage.CLUSTER_CLASS: return createClusterClass();
			case PooslPackage.OUTPUT_VARIABLE: return createOutputVariable();
			case PooslPackage.ANNOTATION: return createAnnotation();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue)
	{
		switch (eDataType.getClassifierID()) {
			case PooslPackage.OPERATOR_UNARY:
				return createOperatorUnaryFromString(eDataType, initialValue);
			case PooslPackage.OPERATOR_BINARY:
				return createOperatorBinaryFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue)
	{
		switch (eDataType.getClassifierID()) {
			case PooslPackage.OPERATOR_UNARY:
				return convertOperatorUnaryToString(eDataType, instanceValue);
			case PooslPackage.OPERATOR_BINARY:
				return convertOperatorBinaryToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Poosl createPoosl()
	{
		PooslImpl poosl = new PooslImpl();
		return poosl;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Import createImport()
	{
		ImportImpl import_ = new ImportImpl();
		return import_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Channel createChannel()
	{
		ChannelImpl channel = new ChannelImpl();
		return channel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public InstancePort createInstancePort()
	{
		InstancePortImpl instancePort = new InstancePortImpl();
		return instancePort;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Instance createInstance()
	{
		InstanceImpl instance = new InstanceImpl();
		return instance;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public InstanceParameter createInstanceParameter()
	{
		InstanceParameterImpl instanceParameter = new InstanceParameterImpl();
		return instanceParameter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public InstantiableClass createInstantiableClass()
	{
		InstantiableClassImpl instantiableClass = new InstantiableClassImpl();
		return instantiableClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Port createPort()
	{
		PortImpl port = new PortImpl();
		return port;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Declaration createDeclaration()
	{
		DeclarationImpl declaration = new DeclarationImpl();
		return declaration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Variable createVariable()
	{
		VariableImpl variable = new VariableImpl();
		return variable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public DataClass createDataClass()
	{
		DataClassImpl dataClass = new DataClassImpl();
		return dataClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public DataMethodNamed createDataMethodNamed()
	{
		DataMethodNamedImpl dataMethodNamed = new DataMethodNamedImpl();
		return dataMethodNamed;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public DataMethodUnaryOperator createDataMethodUnaryOperator()
	{
		DataMethodUnaryOperatorImpl dataMethodUnaryOperator = new DataMethodUnaryOperatorImpl();
		return dataMethodUnaryOperator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public DataMethodBinaryOperator createDataMethodBinaryOperator()
	{
		DataMethodBinaryOperatorImpl dataMethodBinaryOperator = new DataMethodBinaryOperatorImpl();
		return dataMethodBinaryOperator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ExpressionSequence createExpressionSequence()
	{
		ExpressionSequenceImpl expressionSequence = new ExpressionSequenceImpl();
		return expressionSequence;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ExpressionSequenceRoundBracket createExpressionSequenceRoundBracket()
	{
		ExpressionSequenceRoundBracketImpl expressionSequenceRoundBracket = new ExpressionSequenceRoundBracketImpl();
		return expressionSequenceRoundBracket;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public AssignmentExpression createAssignmentExpression()
	{
		AssignmentExpressionImpl assignmentExpression = new AssignmentExpressionImpl();
		return assignmentExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CurrentTimeExpression createCurrentTimeExpression()
	{
		CurrentTimeExpressionImpl currentTimeExpression = new CurrentTimeExpressionImpl();
		return currentTimeExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public DataMethodCallExpression createDataMethodCallExpression()
	{
		DataMethodCallExpressionImpl dataMethodCallExpression = new DataMethodCallExpressionImpl();
		return dataMethodCallExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public IfExpression createIfExpression()
	{
		IfExpressionImpl ifExpression = new IfExpressionImpl();
		return ifExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public SwitchExpression createSwitchExpression()
	{
		SwitchExpressionImpl switchExpression = new SwitchExpressionImpl();
		return switchExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public SwitchExpressionCase createSwitchExpressionCase()
	{
		SwitchExpressionCaseImpl switchExpressionCase = new SwitchExpressionCaseImpl();
		return switchExpressionCase;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NewExpression createNewExpression()
	{
		NewExpressionImpl newExpression = new NewExpressionImpl();
		return newExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ReturnExpression createReturnExpression()
	{
		ReturnExpressionImpl returnExpression = new ReturnExpressionImpl();
		return returnExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public SelfExpression createSelfExpression()
	{
		SelfExpressionImpl selfExpression = new SelfExpressionImpl();
		return selfExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public VariableExpression createVariableExpression()
	{
		VariableExpressionImpl variableExpression = new VariableExpressionImpl();
		return variableExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public WhileExpression createWhileExpression()
	{
		WhileExpressionImpl whileExpression = new WhileExpressionImpl();
		return whileExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public BinaryOperatorExpression createBinaryOperatorExpression()
	{
		BinaryOperatorExpressionImpl binaryOperatorExpression = new BinaryOperatorExpressionImpl();
		return binaryOperatorExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public UnaryOperatorExpression createUnaryOperatorExpression()
	{
		UnaryOperatorExpressionImpl unaryOperatorExpression = new UnaryOperatorExpressionImpl();
		return unaryOperatorExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public BooleanConstant createBooleanConstant()
	{
		BooleanConstantImpl booleanConstant = new BooleanConstantImpl();
		return booleanConstant;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CharacterConstant createCharacterConstant()
	{
		CharacterConstantImpl characterConstant = new CharacterConstantImpl();
		return characterConstant;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public IntegerConstant createIntegerConstant()
	{
		IntegerConstantImpl integerConstant = new IntegerConstantImpl();
		return integerConstant;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NilConstant createNilConstant()
	{
		NilConstantImpl nilConstant = new NilConstantImpl();
		return nilConstant;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public RealConstant createRealConstant()
	{
		RealConstantImpl realConstant = new RealConstantImpl();
		return realConstant;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public FloatConstant createFloatConstant() {
		FloatConstantImpl floatConstant = new FloatConstantImpl();
		return floatConstant;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public StringConstant createStringConstant()
	{
		StringConstantImpl stringConstant = new StringConstantImpl();
		return stringConstant;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EnvironmentConstant createEnvironmentConstant() {
		EnvironmentConstantImpl environmentConstant = new EnvironmentConstantImpl();
		return environmentConstant;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ProcessClass createProcessClass()
	{
		ProcessClassImpl processClass = new ProcessClassImpl();
		return processClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public MessageSignature createMessageSignature()
	{
		MessageSignatureImpl messageSignature = new MessageSignatureImpl();
		return messageSignature;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public MessageParameter createMessageParameter()
	{
		MessageParameterImpl messageParameter = new MessageParameterImpl();
		return messageParameter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ProcessMethod createProcessMethod()
	{
		ProcessMethodImpl processMethod = new ProcessMethodImpl();
		return processMethod;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public StatementSequence createStatementSequence()
	{
		StatementSequenceImpl statementSequence = new StatementSequenceImpl();
		return statementSequence;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public StatementSequenceRoundBracket createStatementSequenceRoundBracket()
	{
		StatementSequenceRoundBracketImpl statementSequenceRoundBracket = new StatementSequenceRoundBracketImpl();
		return statementSequenceRoundBracket;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public AbortStatement createAbortStatement()
	{
		AbortStatementImpl abortStatement = new AbortStatementImpl();
		return abortStatement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public DelayStatement createDelayStatement()
	{
		DelayStatementImpl delayStatement = new DelayStatementImpl();
		return delayStatement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ExpressionStatement createExpressionStatement()
	{
		ExpressionStatementImpl expressionStatement = new ExpressionStatementImpl();
		return expressionStatement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public GuardedStatement createGuardedStatement()
	{
		GuardedStatementImpl guardedStatement = new GuardedStatementImpl();
		return guardedStatement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public IfStatement createIfStatement()
	{
		IfStatementImpl ifStatement = new IfStatementImpl();
		return ifStatement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public SwitchStatement createSwitchStatement()
	{
		SwitchStatementImpl switchStatement = new SwitchStatementImpl();
		return switchStatement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public SwitchStatementCase createSwitchStatementCase()
	{
		SwitchStatementCaseImpl switchStatementCase = new SwitchStatementCaseImpl();
		return switchStatementCase;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public InterruptStatement createInterruptStatement()
	{
		InterruptStatementImpl interruptStatement = new InterruptStatementImpl();
		return interruptStatement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ParStatement createParStatement()
	{
		ParStatementImpl parStatement = new ParStatementImpl();
		return parStatement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ProcessMethodCall createProcessMethodCall()
	{
		ProcessMethodCallImpl processMethodCall = new ProcessMethodCallImpl();
		return processMethodCall;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ReceiveStatement createReceiveStatement()
	{
		ReceiveStatementImpl receiveStatement = new ReceiveStatementImpl();
		return receiveStatement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public SendStatement createSendStatement()
	{
		SendStatementImpl sendStatement = new SendStatementImpl();
		return sendStatement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public SelStatement createSelStatement()
	{
		SelStatementImpl selStatement = new SelStatementImpl();
		return selStatement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public SkipStatement createSkipStatement()
	{
		SkipStatementImpl skipStatement = new SkipStatementImpl();
		return skipStatement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public WhileStatement createWhileStatement()
	{
		WhileStatementImpl whileStatement = new WhileStatementImpl();
		return whileStatement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public PortExpression createPortExpression()
	{
		PortExpressionImpl portExpression = new PortExpressionImpl();
		return portExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public PortReference createPortReference()
	{
		PortReferenceImpl portReference = new PortReferenceImpl();
		return portReference;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ClusterClass createClusterClass()
	{
		ClusterClassImpl clusterClass = new ClusterClassImpl();
		return clusterClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public OutputVariable createOutputVariable()
	{
		OutputVariableImpl outputVariable = new OutputVariableImpl();
		return outputVariable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Annotation createAnnotation()
	{
		AnnotationImpl annotation = new AnnotationImpl();
		return annotation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OperatorUnary createOperatorUnaryFromString(EDataType eDataType, String initialValue)
	{
		OperatorUnary result = OperatorUnary.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertOperatorUnaryToString(EDataType eDataType, Object instanceValue)
	{
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OperatorBinary createOperatorBinaryFromString(EDataType eDataType, String initialValue)
	{
		OperatorBinary result = OperatorBinary.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertOperatorBinaryToString(EDataType eDataType, Object instanceValue)
	{
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public PooslPackage getPooslPackage()
	{
		return (PooslPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static PooslPackage getPackage()
	{
		return PooslPackage.eINSTANCE;
	}

} //PooslFactoryImpl

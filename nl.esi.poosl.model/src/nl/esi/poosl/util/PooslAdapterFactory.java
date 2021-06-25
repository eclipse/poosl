/**
 */
package nl.esi.poosl.util;

import nl.esi.poosl.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see nl.esi.poosl.PooslPackage
 * @generated
 */
public class PooslAdapterFactory extends AdapterFactoryImpl
{
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static PooslPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PooslAdapterFactory()
	{
		if (modelPackage == null) {
			modelPackage = PooslPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object)
	{
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PooslSwitch<Adapter> modelSwitch =
		new PooslSwitch<Adapter>() {
			@Override
			public Adapter casePoosl(Poosl object) {
				return createPooslAdapter();
			}
			@Override
			public Adapter caseImport(Import object) {
				return createImportAdapter();
			}
			@Override
			public Adapter caseChannel(Channel object) {
				return createChannelAdapter();
			}
			@Override
			public Adapter caseInstancePort(InstancePort object) {
				return createInstancePortAdapter();
			}
			@Override
			public Adapter caseInstance(Instance object) {
				return createInstanceAdapter();
			}
			@Override
			public Adapter caseInstanceParameter(InstanceParameter object) {
				return createInstanceParameterAdapter();
			}
			@Override
			public Adapter caseInstantiableClass(InstantiableClass object) {
				return createInstantiableClassAdapter();
			}
			@Override
			public Adapter casePort(Port object) {
				return createPortAdapter();
			}
			@Override
			public Adapter caseDeclaration(Declaration object) {
				return createDeclarationAdapter();
			}
			@Override
			public Adapter caseVariable(Variable object) {
				return createVariableAdapter();
			}
			@Override
			public Adapter caseDataClass(DataClass object) {
				return createDataClassAdapter();
			}
			@Override
			public Adapter caseDataMethod(DataMethod object) {
				return createDataMethodAdapter();
			}
			@Override
			public Adapter caseDataMethodNamed(DataMethodNamed object) {
				return createDataMethodNamedAdapter();
			}
			@Override
			public Adapter caseDataMethodUnaryOperator(DataMethodUnaryOperator object) {
				return createDataMethodUnaryOperatorAdapter();
			}
			@Override
			public Adapter caseDataMethodBinaryOperator(DataMethodBinaryOperator object) {
				return createDataMethodBinaryOperatorAdapter();
			}
			@Override
			public Adapter caseExpression(Expression object) {
				return createExpressionAdapter();
			}
			@Override
			public Adapter caseExpressionSequence(ExpressionSequence object) {
				return createExpressionSequenceAdapter();
			}
			@Override
			public Adapter caseExpressionSequenceRoundBracket(ExpressionSequenceRoundBracket object) {
				return createExpressionSequenceRoundBracketAdapter();
			}
			@Override
			public Adapter caseAssignmentExpression(AssignmentExpression object) {
				return createAssignmentExpressionAdapter();
			}
			@Override
			public Adapter caseCurrentTimeExpression(CurrentTimeExpression object) {
				return createCurrentTimeExpressionAdapter();
			}
			@Override
			public Adapter caseDataMethodCallExpression(DataMethodCallExpression object) {
				return createDataMethodCallExpressionAdapter();
			}
			@Override
			public Adapter caseIfExpression(IfExpression object) {
				return createIfExpressionAdapter();
			}
			@Override
			public Adapter caseSwitchExpression(SwitchExpression object) {
				return createSwitchExpressionAdapter();
			}
			@Override
			public Adapter caseSwitchExpressionCase(SwitchExpressionCase object) {
				return createSwitchExpressionCaseAdapter();
			}
			@Override
			public Adapter caseNewExpression(NewExpression object) {
				return createNewExpressionAdapter();
			}
			@Override
			public Adapter caseReturnExpression(ReturnExpression object) {
				return createReturnExpressionAdapter();
			}
			@Override
			public Adapter caseSelfExpression(SelfExpression object) {
				return createSelfExpressionAdapter();
			}
			@Override
			public Adapter caseVariableExpression(VariableExpression object) {
				return createVariableExpressionAdapter();
			}
			@Override
			public Adapter caseWhileExpression(WhileExpression object) {
				return createWhileExpressionAdapter();
			}
			@Override
			public Adapter caseBinaryOperatorExpression(BinaryOperatorExpression object) {
				return createBinaryOperatorExpressionAdapter();
			}
			@Override
			public Adapter caseUnaryOperatorExpression(UnaryOperatorExpression object) {
				return createUnaryOperatorExpressionAdapter();
			}
			@Override
			public Adapter caseBooleanConstant(BooleanConstant object) {
				return createBooleanConstantAdapter();
			}
			@Override
			public Adapter caseCharacterConstant(CharacterConstant object) {
				return createCharacterConstantAdapter();
			}
			@Override
			public Adapter caseIntegerConstant(IntegerConstant object) {
				return createIntegerConstantAdapter();
			}
			@Override
			public Adapter caseNilConstant(NilConstant object) {
				return createNilConstantAdapter();
			}
			@Override
			public Adapter caseRealConstant(RealConstant object) {
				return createRealConstantAdapter();
			}
			@Override
			public Adapter caseFloatConstant(FloatConstant object) {
				return createFloatConstantAdapter();
			}
			@Override
			public Adapter caseStringConstant(StringConstant object) {
				return createStringConstantAdapter();
			}
			@Override
			public Adapter caseEnvironmentConstant(EnvironmentConstant object) {
				return createEnvironmentConstantAdapter();
			}
			@Override
			public Adapter caseProcessClass(ProcessClass object) {
				return createProcessClassAdapter();
			}
			@Override
			public Adapter caseMessageSignature(MessageSignature object) {
				return createMessageSignatureAdapter();
			}
			@Override
			public Adapter caseMessageParameter(MessageParameter object) {
				return createMessageParameterAdapter();
			}
			@Override
			public Adapter caseProcessMethod(ProcessMethod object) {
				return createProcessMethodAdapter();
			}
			@Override
			public Adapter caseStatement(Statement object) {
				return createStatementAdapter();
			}
			@Override
			public Adapter caseStatementSequence(StatementSequence object) {
				return createStatementSequenceAdapter();
			}
			@Override
			public Adapter caseStatementSequenceRoundBracket(StatementSequenceRoundBracket object) {
				return createStatementSequenceRoundBracketAdapter();
			}
			@Override
			public Adapter caseAbortStatement(AbortStatement object) {
				return createAbortStatementAdapter();
			}
			@Override
			public Adapter caseDelayStatement(DelayStatement object) {
				return createDelayStatementAdapter();
			}
			@Override
			public Adapter caseExpressionStatement(ExpressionStatement object) {
				return createExpressionStatementAdapter();
			}
			@Override
			public Adapter caseGuardedStatement(GuardedStatement object) {
				return createGuardedStatementAdapter();
			}
			@Override
			public Adapter caseIfStatement(IfStatement object) {
				return createIfStatementAdapter();
			}
			@Override
			public Adapter caseSwitchStatement(SwitchStatement object) {
				return createSwitchStatementAdapter();
			}
			@Override
			public Adapter caseSwitchStatementCase(SwitchStatementCase object) {
				return createSwitchStatementCaseAdapter();
			}
			@Override
			public Adapter caseInterruptStatement(InterruptStatement object) {
				return createInterruptStatementAdapter();
			}
			@Override
			public Adapter caseParStatement(ParStatement object) {
				return createParStatementAdapter();
			}
			@Override
			public Adapter caseProcessMethodCall(ProcessMethodCall object) {
				return createProcessMethodCallAdapter();
			}
			@Override
			public Adapter caseReceiveStatement(ReceiveStatement object) {
				return createReceiveStatementAdapter();
			}
			@Override
			public Adapter caseSendStatement(SendStatement object) {
				return createSendStatementAdapter();
			}
			@Override
			public Adapter caseSelStatement(SelStatement object) {
				return createSelStatementAdapter();
			}
			@Override
			public Adapter caseSkipStatement(SkipStatement object) {
				return createSkipStatementAdapter();
			}
			@Override
			public Adapter caseWhileStatement(WhileStatement object) {
				return createWhileStatementAdapter();
			}
			@Override
			public Adapter casePortDescriptor(PortDescriptor object) {
				return createPortDescriptorAdapter();
			}
			@Override
			public Adapter casePortExpression(PortExpression object) {
				return createPortExpressionAdapter();
			}
			@Override
			public Adapter casePortReference(PortReference object) {
				return createPortReferenceAdapter();
			}
			@Override
			public Adapter caseClusterClass(ClusterClass object) {
				return createClusterClassAdapter();
			}
			@Override
			public Adapter caseOutputVariable(OutputVariable object) {
				return createOutputVariableAdapter();
			}
			@Override
			public Adapter caseAnnotation(Annotation object) {
				return createAnnotationAdapter();
			}
			@Override
			public Adapter caseAnnotable(Annotable object) {
				return createAnnotableAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target)
	{
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link nl.esi.poosl.Poosl <em>Poosl</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see nl.esi.poosl.Poosl
	 * @generated
	 */
	public Adapter createPooslAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link nl.esi.poosl.Import <em>Import</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see nl.esi.poosl.Import
	 * @generated
	 */
	public Adapter createImportAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link nl.esi.poosl.Channel <em>Channel</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see nl.esi.poosl.Channel
	 * @generated
	 */
	public Adapter createChannelAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link nl.esi.poosl.InstancePort <em>Instance Port</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see nl.esi.poosl.InstancePort
	 * @generated
	 */
	public Adapter createInstancePortAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link nl.esi.poosl.Instance <em>Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see nl.esi.poosl.Instance
	 * @generated
	 */
	public Adapter createInstanceAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link nl.esi.poosl.InstanceParameter <em>Instance Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see nl.esi.poosl.InstanceParameter
	 * @generated
	 */
	public Adapter createInstanceParameterAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link nl.esi.poosl.InstantiableClass <em>Instantiable Class</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see nl.esi.poosl.InstantiableClass
	 * @generated
	 */
	public Adapter createInstantiableClassAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link nl.esi.poosl.Port <em>Port</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see nl.esi.poosl.Port
	 * @generated
	 */
	public Adapter createPortAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link nl.esi.poosl.Declaration <em>Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see nl.esi.poosl.Declaration
	 * @generated
	 */
	public Adapter createDeclarationAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link nl.esi.poosl.Variable <em>Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see nl.esi.poosl.Variable
	 * @generated
	 */
	public Adapter createVariableAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link nl.esi.poosl.DataClass <em>Data Class</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see nl.esi.poosl.DataClass
	 * @generated
	 */
	public Adapter createDataClassAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link nl.esi.poosl.DataMethod <em>Data Method</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see nl.esi.poosl.DataMethod
	 * @generated
	 */
	public Adapter createDataMethodAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link nl.esi.poosl.DataMethodNamed <em>Data Method Named</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see nl.esi.poosl.DataMethodNamed
	 * @generated
	 */
	public Adapter createDataMethodNamedAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link nl.esi.poosl.DataMethodUnaryOperator <em>Data Method Unary Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see nl.esi.poosl.DataMethodUnaryOperator
	 * @generated
	 */
	public Adapter createDataMethodUnaryOperatorAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link nl.esi.poosl.DataMethodBinaryOperator <em>Data Method Binary Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see nl.esi.poosl.DataMethodBinaryOperator
	 * @generated
	 */
	public Adapter createDataMethodBinaryOperatorAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link nl.esi.poosl.Expression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see nl.esi.poosl.Expression
	 * @generated
	 */
	public Adapter createExpressionAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link nl.esi.poosl.ExpressionSequence <em>Expression Sequence</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see nl.esi.poosl.ExpressionSequence
	 * @generated
	 */
	public Adapter createExpressionSequenceAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link nl.esi.poosl.ExpressionSequenceRoundBracket <em>Expression Sequence Round Bracket</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see nl.esi.poosl.ExpressionSequenceRoundBracket
	 * @generated
	 */
	public Adapter createExpressionSequenceRoundBracketAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link nl.esi.poosl.AssignmentExpression <em>Assignment Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see nl.esi.poosl.AssignmentExpression
	 * @generated
	 */
	public Adapter createAssignmentExpressionAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link nl.esi.poosl.CurrentTimeExpression <em>Current Time Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see nl.esi.poosl.CurrentTimeExpression
	 * @generated
	 */
	public Adapter createCurrentTimeExpressionAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link nl.esi.poosl.DataMethodCallExpression <em>Data Method Call Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see nl.esi.poosl.DataMethodCallExpression
	 * @generated
	 */
	public Adapter createDataMethodCallExpressionAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link nl.esi.poosl.IfExpression <em>If Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see nl.esi.poosl.IfExpression
	 * @generated
	 */
	public Adapter createIfExpressionAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link nl.esi.poosl.SwitchExpression <em>Switch Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see nl.esi.poosl.SwitchExpression
	 * @generated
	 */
	public Adapter createSwitchExpressionAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link nl.esi.poosl.SwitchExpressionCase <em>Switch Expression Case</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see nl.esi.poosl.SwitchExpressionCase
	 * @generated
	 */
	public Adapter createSwitchExpressionCaseAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link nl.esi.poosl.NewExpression <em>New Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see nl.esi.poosl.NewExpression
	 * @generated
	 */
	public Adapter createNewExpressionAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link nl.esi.poosl.ReturnExpression <em>Return Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see nl.esi.poosl.ReturnExpression
	 * @generated
	 */
	public Adapter createReturnExpressionAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link nl.esi.poosl.SelfExpression <em>Self Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see nl.esi.poosl.SelfExpression
	 * @generated
	 */
	public Adapter createSelfExpressionAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link nl.esi.poosl.VariableExpression <em>Variable Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see nl.esi.poosl.VariableExpression
	 * @generated
	 */
	public Adapter createVariableExpressionAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link nl.esi.poosl.WhileExpression <em>While Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see nl.esi.poosl.WhileExpression
	 * @generated
	 */
	public Adapter createWhileExpressionAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link nl.esi.poosl.BinaryOperatorExpression <em>Binary Operator Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see nl.esi.poosl.BinaryOperatorExpression
	 * @generated
	 */
	public Adapter createBinaryOperatorExpressionAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link nl.esi.poosl.UnaryOperatorExpression <em>Unary Operator Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see nl.esi.poosl.UnaryOperatorExpression
	 * @generated
	 */
	public Adapter createUnaryOperatorExpressionAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link nl.esi.poosl.BooleanConstant <em>Boolean Constant</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see nl.esi.poosl.BooleanConstant
	 * @generated
	 */
	public Adapter createBooleanConstantAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link nl.esi.poosl.CharacterConstant <em>Character Constant</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see nl.esi.poosl.CharacterConstant
	 * @generated
	 */
	public Adapter createCharacterConstantAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link nl.esi.poosl.IntegerConstant <em>Integer Constant</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see nl.esi.poosl.IntegerConstant
	 * @generated
	 */
	public Adapter createIntegerConstantAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link nl.esi.poosl.NilConstant <em>Nil Constant</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see nl.esi.poosl.NilConstant
	 * @generated
	 */
	public Adapter createNilConstantAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link nl.esi.poosl.RealConstant <em>Real Constant</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see nl.esi.poosl.RealConstant
	 * @generated
	 */
	public Adapter createRealConstantAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link nl.esi.poosl.FloatConstant <em>Float Constant</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see nl.esi.poosl.FloatConstant
	 * @generated
	 */
	public Adapter createFloatConstantAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link nl.esi.poosl.StringConstant <em>String Constant</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see nl.esi.poosl.StringConstant
	 * @generated
	 */
	public Adapter createStringConstantAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link nl.esi.poosl.EnvironmentConstant <em>Environment Constant</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see nl.esi.poosl.EnvironmentConstant
	 * @generated
	 */
	public Adapter createEnvironmentConstantAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link nl.esi.poosl.ProcessClass <em>Process Class</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see nl.esi.poosl.ProcessClass
	 * @generated
	 */
	public Adapter createProcessClassAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link nl.esi.poosl.MessageSignature <em>Message Signature</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see nl.esi.poosl.MessageSignature
	 * @generated
	 */
	public Adapter createMessageSignatureAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link nl.esi.poosl.MessageParameter <em>Message Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see nl.esi.poosl.MessageParameter
	 * @generated
	 */
	public Adapter createMessageParameterAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link nl.esi.poosl.ProcessMethod <em>Process Method</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see nl.esi.poosl.ProcessMethod
	 * @generated
	 */
	public Adapter createProcessMethodAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link nl.esi.poosl.Statement <em>Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see nl.esi.poosl.Statement
	 * @generated
	 */
	public Adapter createStatementAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link nl.esi.poosl.StatementSequence <em>Statement Sequence</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see nl.esi.poosl.StatementSequence
	 * @generated
	 */
	public Adapter createStatementSequenceAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link nl.esi.poosl.StatementSequenceRoundBracket <em>Statement Sequence Round Bracket</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see nl.esi.poosl.StatementSequenceRoundBracket
	 * @generated
	 */
	public Adapter createStatementSequenceRoundBracketAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link nl.esi.poosl.AbortStatement <em>Abort Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see nl.esi.poosl.AbortStatement
	 * @generated
	 */
	public Adapter createAbortStatementAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link nl.esi.poosl.DelayStatement <em>Delay Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see nl.esi.poosl.DelayStatement
	 * @generated
	 */
	public Adapter createDelayStatementAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link nl.esi.poosl.ExpressionStatement <em>Expression Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see nl.esi.poosl.ExpressionStatement
	 * @generated
	 */
	public Adapter createExpressionStatementAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link nl.esi.poosl.GuardedStatement <em>Guarded Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see nl.esi.poosl.GuardedStatement
	 * @generated
	 */
	public Adapter createGuardedStatementAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link nl.esi.poosl.IfStatement <em>If Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see nl.esi.poosl.IfStatement
	 * @generated
	 */
	public Adapter createIfStatementAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link nl.esi.poosl.SwitchStatement <em>Switch Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see nl.esi.poosl.SwitchStatement
	 * @generated
	 */
	public Adapter createSwitchStatementAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link nl.esi.poosl.SwitchStatementCase <em>Switch Statement Case</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see nl.esi.poosl.SwitchStatementCase
	 * @generated
	 */
	public Adapter createSwitchStatementCaseAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link nl.esi.poosl.InterruptStatement <em>Interrupt Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see nl.esi.poosl.InterruptStatement
	 * @generated
	 */
	public Adapter createInterruptStatementAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link nl.esi.poosl.ParStatement <em>Par Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see nl.esi.poosl.ParStatement
	 * @generated
	 */
	public Adapter createParStatementAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link nl.esi.poosl.ProcessMethodCall <em>Process Method Call</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see nl.esi.poosl.ProcessMethodCall
	 * @generated
	 */
	public Adapter createProcessMethodCallAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link nl.esi.poosl.ReceiveStatement <em>Receive Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see nl.esi.poosl.ReceiveStatement
	 * @generated
	 */
	public Adapter createReceiveStatementAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link nl.esi.poosl.SendStatement <em>Send Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see nl.esi.poosl.SendStatement
	 * @generated
	 */
	public Adapter createSendStatementAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link nl.esi.poosl.SelStatement <em>Sel Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see nl.esi.poosl.SelStatement
	 * @generated
	 */
	public Adapter createSelStatementAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link nl.esi.poosl.SkipStatement <em>Skip Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see nl.esi.poosl.SkipStatement
	 * @generated
	 */
	public Adapter createSkipStatementAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link nl.esi.poosl.WhileStatement <em>While Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see nl.esi.poosl.WhileStatement
	 * @generated
	 */
	public Adapter createWhileStatementAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link nl.esi.poosl.PortDescriptor <em>Port Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see nl.esi.poosl.PortDescriptor
	 * @generated
	 */
	public Adapter createPortDescriptorAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link nl.esi.poosl.PortExpression <em>Port Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see nl.esi.poosl.PortExpression
	 * @generated
	 */
	public Adapter createPortExpressionAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link nl.esi.poosl.PortReference <em>Port Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see nl.esi.poosl.PortReference
	 * @generated
	 */
	public Adapter createPortReferenceAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link nl.esi.poosl.ClusterClass <em>Cluster Class</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see nl.esi.poosl.ClusterClass
	 * @generated
	 */
	public Adapter createClusterClassAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link nl.esi.poosl.OutputVariable <em>Output Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see nl.esi.poosl.OutputVariable
	 * @generated
	 */
	public Adapter createOutputVariableAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link nl.esi.poosl.Annotation <em>Annotation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see nl.esi.poosl.Annotation
	 * @generated
	 */
	public Adapter createAnnotationAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link nl.esi.poosl.Annotable <em>Annotable</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see nl.esi.poosl.Annotable
	 * @generated
	 */
	public Adapter createAnnotableAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter()
	{
		return null;
	}

} //PooslAdapterFactory

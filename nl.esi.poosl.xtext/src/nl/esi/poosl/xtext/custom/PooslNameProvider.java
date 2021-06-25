package nl.esi.poosl.xtext.custom;

import nl.esi.poosl.BinaryOperatorExpression;
import nl.esi.poosl.DataMethodBinaryOperator;
import nl.esi.poosl.DataMethodUnaryOperator;
import nl.esi.poosl.UnaryOperatorExpression;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.naming.IQualifiedNameConverter;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.naming.SimpleNameProvider;

import com.google.inject.Inject;

public class PooslNameProvider extends SimpleNameProvider {
	@Inject
	private IQualifiedNameConverter qualifiedNameConverter;

	@Override
	public QualifiedName getFullyQualifiedName(EObject obj) {
		if (obj instanceof DataMethodBinaryOperator) {
			String name = ((DataMethodBinaryOperator) obj).getName().getLiteral();
			return qualifiedNameConverter.toQualifiedName(name);
		} else if (obj instanceof DataMethodUnaryOperator) {
			String name = ((DataMethodUnaryOperator) obj).getName().getLiteral();
			return qualifiedNameConverter.toQualifiedName(name);
		} else if (obj instanceof UnaryOperatorExpression) {
			String name = ((UnaryOperatorExpression) obj).getName().getLiteral();
			return qualifiedNameConverter.toQualifiedName(name);
		} else if (obj instanceof BinaryOperatorExpression) {
			String name = ((BinaryOperatorExpression) obj).getName().getLiteral();
			return qualifiedNameConverter.toQualifiedName(name);
		}

		return super.getFullyQualifiedName(obj);
	}
}

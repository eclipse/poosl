package org.eclipse.poosl.xtext.ui.references;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.poosl.AssignmentExpression;
import org.eclipse.poosl.DataClass;
import org.eclipse.poosl.DataMethod;
import org.eclipse.poosl.Declaration;
import org.eclipse.poosl.Instance;
import org.eclipse.poosl.InstanceParameter;
import org.eclipse.poosl.MessageParameter;
import org.eclipse.poosl.NewExpression;
import org.eclipse.poosl.OutputVariable;
import org.eclipse.poosl.PortReference;
import org.eclipse.poosl.ProcessClass;
import org.eclipse.poosl.ProcessMethodCall;
import org.eclipse.poosl.ReceiveStatement;
import org.eclipse.poosl.SendStatement;
import org.eclipse.poosl.VariableExpression;
import org.eclipse.poosl.xtext.helpers.PooslReferenceHelper;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.findReferences.ReferenceFinder;
import org.eclipse.xtext.resource.IEObjectDescription;

import com.google.common.base.Predicate;

@SuppressWarnings("restriction")
public class PooslReferenceFinder extends ReferenceFinder {

    /*
     * Find local references in the file and accept them (non-Javadoc)
     * @see org.eclipse.xtext.findReferences.ReferenceFinder#
     * findLocalReferencesFromElement(com.google.common.base.Predicate, org.eclipse.emf.ecore.EObject,
     * org.eclipse.emf.ecore.resource.Resource, org.eclipse.xtext.findReferences.IReferenceFinder.Acceptor)
     */
    @Override
    protected void findLocalReferencesFromElement(Predicate<URI> targetURIs, EObject sourceCandidate, Resource localResource, Acceptor acceptor) {

        // --- Message References -------

        if (sourceCandidate instanceof SendStatement) {
            SendStatement statement = (SendStatement) sourceCandidate;
            validateAndAccept(targetURIs, sourceCandidate, localResource, acceptor, PooslReferenceHelper.getSendSignatureDescription(statement));
        } else if (sourceCandidate instanceof ReceiveStatement) {
            ReceiveStatement statement = (ReceiveStatement) sourceCandidate;
            validateAndAccept(targetURIs, sourceCandidate, localResource, acceptor, PooslReferenceHelper.getReceiveSignatureDescription(statement));

            // --- Process Method References -------

        } else if (sourceCandidate instanceof ProcessMethodCall) {
            ProcessMethodCall call = (ProcessMethodCall) sourceCandidate;
            validateAndAccept(targetURIs, sourceCandidate, localResource, acceptor, PooslReferenceHelper.getProcessMethodDescription(call));

            // --- Port References -------

        } else if (sourceCandidate instanceof PortReference) {
            PortReference port = (PortReference) sourceCandidate;
            validateAndAccept(targetURIs, sourceCandidate, localResource, acceptor, PooslReferenceHelper.getPortDescription(port));

            // --- Class References -------

        } else if (sourceCandidate instanceof ProcessClass) {
            ProcessClass pClass = (ProcessClass) sourceCandidate;
            validateAndAccept(targetURIs, sourceCandidate, localResource, acceptor, PooslReferenceHelper.getProcessClassDescription(pClass));
        } else if (sourceCandidate instanceof DataClass) {
            DataClass dClass = (DataClass) sourceCandidate;
            validateAndAccept(targetURIs, sourceCandidate, localResource, acceptor, PooslReferenceHelper.getDataClassDescription(dClass));
        } else if (sourceCandidate instanceof Instance) {
            Instance instance = (Instance) sourceCandidate;
            validateAndAccept(targetURIs, sourceCandidate, localResource, acceptor, PooslReferenceHelper.getInstantiableClassDescription(instance));
        } else if (sourceCandidate instanceof NewExpression) {
            NewExpression newExpr = (NewExpression) sourceCandidate;
            validateAndAccept(targetURIs, sourceCandidate, localResource, acceptor, PooslReferenceHelper.getDataClassDescription(newExpr));
        } else if (sourceCandidate instanceof DataMethod) {
            DataMethod dataMethod = (DataMethod) sourceCandidate;
            validateAndAccept(targetURIs, sourceCandidate, localResource, acceptor, PooslReferenceHelper.getDataClassDescription(dataMethod));
        } else if (sourceCandidate instanceof Declaration) {
            Declaration declaration = (Declaration) sourceCandidate;
            validateAndAccept(targetURIs, sourceCandidate, localResource, acceptor, PooslReferenceHelper.getDataClassDescription(declaration));
        } else if (sourceCandidate instanceof MessageParameter) {
            MessageParameter msgParam = (MessageParameter) sourceCandidate;
            validateAndAccept(targetURIs, sourceCandidate, localResource, acceptor, PooslReferenceHelper.getDataClassDescription(msgParam));

            // --- Variable References -------

        } else if (sourceCandidate instanceof AssignmentExpression) {
            AssignmentExpression assignExpr = (AssignmentExpression) sourceCandidate;
            validateAndAccept(targetURIs, sourceCandidate, localResource, acceptor, PooslReferenceHelper.getVariableDescription(assignExpr));
        } else if (sourceCandidate instanceof VariableExpression) {
            VariableExpression varExpr = (VariableExpression) sourceCandidate;
            validateAndAccept(targetURIs, sourceCandidate, localResource, acceptor, PooslReferenceHelper.getVariableDescription(varExpr));
        } else if (sourceCandidate instanceof InstanceParameter) {
            InstanceParameter iParam = (InstanceParameter) sourceCandidate;
            validateAndAccept(targetURIs, sourceCandidate, localResource, acceptor, PooslReferenceHelper.getVariableDescription(iParam));
        } else if (sourceCandidate instanceof OutputVariable) {
            OutputVariable outVar = (OutputVariable) sourceCandidate;
            validateAndAccept(targetURIs, sourceCandidate, localResource, acceptor, PooslReferenceHelper.getVariableDescription(outVar));
        }

        super.findLocalReferencesFromElement(targetURIs, sourceCandidate, localResource, acceptor);
    }

    private void validateAndAccept(Predicate<URI> targetURIs, EObject sourceCandidate, Resource localResource, Acceptor acceptor, IEObjectDescription descr) {
        if (descr != null) {
            EObject instance = toValidInstanceOrNull(localResource, targetURIs, descr.getEObjectOrProxy());
            if (instance != null) {
                URI refURI = EcoreUtil2.getPlatformResourceOrNormalizedURI(instance);
                if (targetURIs.apply(refURI)) {
                    URI sourceURI = EcoreUtil2.getPlatformResourceOrNormalizedURI(sourceCandidate);
                    acceptor.accept(sourceCandidate, sourceURI, null, -1, instance, refURI);
                }
            }
        }
    }

}

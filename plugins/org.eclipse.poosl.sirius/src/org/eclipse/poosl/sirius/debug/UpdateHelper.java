package org.eclipse.poosl.sirius.debug;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.poosl.Poosl;
import org.eclipse.poosl.sirius.helpers.GraphicalEditorHelper;
import org.eclipse.poosl.xtext.importing.ImportingHelper;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.DView;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

public final class UpdateHelper {
    private UpdateHelper() {
        throw new IllegalStateException("Utility class");
    }

    public static Shell getPooslShell() {
        IWorkbench pl = PlatformUI.getWorkbench();
        for (IWorkbenchWindow window : pl.getWorkbenchWindows()) {
            for (IWorkbenchPage page : window.getPages()) {
                if (page.findViewReference("org.eclipse.poosl.views.debugview") != null) { //$NON-NLS-1$
                    return window.getShell();
                }
            }
        }
        return null;
    }

    public static Map<Session, Set<DRepresentationDescriptor>> getLaunchRepresentations(Collection<String> launchIDs) {
        final Map<Session, Set<DRepresentationDescriptor>> session2Descriptors = new HashMap<>();
        for (final Session session : SessionManager.INSTANCE.getSessions()) {
            addRepresentations(launchIDs, session2Descriptors, session);
        }
        return session2Descriptors;
    }

    public static void addRepresentations(Collection<String> launchIDs, final Map<Session, Set<DRepresentationDescriptor>> session2Descriptors, final Session session) {
        Set<DRepresentationDescriptor> launchRepresentationsFromSession = getLaunchRepresentationsFromSession(launchIDs, session);
        if (!launchRepresentationsFromSession.isEmpty()) {
            session2Descriptors.put(session, launchRepresentationsFromSession);
        }
    }

    private static Set<DRepresentationDescriptor> getLaunchRepresentationsFromSession(Collection<String> launchIDs, final Session session) {
        Set<DRepresentationDescriptor> descriptors = new HashSet<>();
        for (DView dView : session.getOwnedViews()) {
            for (DRepresentationDescriptor descriptor : dView.getOwnedRepresentationDescriptors()) {
                if (GraphicalEditorHelper.isLaunchRepresentation(descriptor, launchIDs)) {
                    descriptors.add(descriptor);
                }
            }
        }
        return descriptors;
    }

    public static Set<DRepresentationDescriptor> getLaunchRepresentationsFromSession(String launchIDs, final Session session) {
        return getLaunchRepresentationsFromSession(Arrays.asList(launchIDs), session);
    }

    public static Poosl getEcoreModel(final String modelPath) {
        ResourceSet resourceSet = new ResourceSetImpl();
        resourceSet.getPackageRegistry().put(org.eclipse.poosl.PooslPackage.eINSTANCE.getNsURI(), org.eclipse.poosl.PooslPackage.eINSTANCE);
        URI uri = URI.createPlatformResourceURI(modelPath, true);
        Resource resource = resourceSet.getResource(uri, true);
        Poosl poosl = ImportingHelper.toPoosl(resource);
        EcoreUtil.resolveAll(poosl);
        return poosl;
    }
}

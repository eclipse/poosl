package org.eclipse.poosl.xtext;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.poosl.PooslPackage;

import com.google.inject.Injector;

/**
 * Initialization support for running Xtext languages without equinox extension registry.
 * <p>
 * Initiated by xtext.
 * </p>
 */
public class PooslStandaloneSetup extends PooslStandaloneSetupGenerated {

    public static void doSetup() {
        new PooslStandaloneSetup().createInjectorAndDoEMFRegistration();
    }

    @Override
    public void register(Injector injector) {
        if (!EPackage.Registry.INSTANCE.containsKey("http://www.esi.nl/comma/actions/Actions")) { //$NON-NLS-1$
            EPackage.Registry.INSTANCE.put("http://poosl.eclipse.org/poosl/3.0.0", PooslPackage.eINSTANCE); //$NON-NLS-1$
        }

        super.register(injector);
    }
}

package org.eclipse.poosl.provider;

import org.eclipse.emf.common.notify.AdapterFactory;

/**
 * Customization of instance port.
 */
public class InstancePortItemProviderCustom extends InstancePortItemProvider {

    public InstancePortItemProviderCustom(AdapterFactory adapterFactory) {
        super(adapterFactory);
    }
    
    @Override
    protected void addPortPropertyDescriptor(Object object) {
        // port is removed or virtual or not applicable for instance port ?
    }
    
    protected void addInstancePropertyDescriptor(Object object) {
        // instance is removed or virtual or not applicable for instance port ?
    }

}

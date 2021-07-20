/**
 * 
 */
package org.eclipse.poosl.provider;

import org.eclipse.emf.common.notify.Adapter;

/**
 * @author nperansin
 *
 */
public class PooslItemProviderAdapterFactoryCustom extends PooslItemProviderAdapterFactory {

    
    @Override
    public Adapter createChannelAdapter() {
        if (channelItemProvider == null)
        {
            channelItemProvider = new ChannelItemProviderCustom(this);
        }

        return channelItemProvider;    
        }
    
    @Override
    public Adapter createInstanceAdapter() {
        if (instanceItemProvider == null)
        {
            instanceItemProvider = new InstanceItemProviderCustom(this);
        }

        return instanceItemProvider;
    }
    
    @Override
    public Adapter createInstancePortAdapter() {
        if (instancePortItemProvider == null)
        {
            instancePortItemProvider = new InstancePortItemProviderCustom(this);
        }

        return instancePortItemProvider;
    }
}

package org.eclipse.poosl.provider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.poosl.ClusterClass;
import org.eclipse.poosl.PooslPackage;
import org.eclipse.poosl.Port;

/**
 * Customization of ChannelItemProvider
 * <p>
 * Name is conform to generation gap pattern of MWE2.
 * </p>
 * 
 * @author Obeo
 */
public class ChannelItemProviderCustom extends ChannelItemProvider {

    /**
     * Default constructor.
     * 
     * @param adapterFactory factory
     */
    public ChannelItemProviderCustom(AdapterFactory adapterFactory) {
        super(adapterFactory);
    }

    @Override
    protected void addExternalPortPropertyDescriptor(Object object) 
    {
        itemPropertyDescriptors.add
            (new ItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_Channel_externalPort_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_Channel_externalPort_feature", "_UI_Channel_type"),
                 PooslPackage.Literals.CHANNEL__EXTERNAL_PORT,
                 true,
                 false,
                 true,
                 null,
                 null,
                 null) {
                @Override
                public Collection<?> getChoiceOfValues(Object object) {
                    List<Port> list = new ArrayList<Port>();
                    if(object instanceof EObject){
                        EObject eObject = (EObject) object;
                        while ((eObject.eContainer() != null) && !(eObject instanceof ClusterClass)) {
                            eObject = eObject.eContainer();
                        }
                        if(eObject instanceof ClusterClass) {
                            return ((ClusterClass) eObject).getPorts();                         
                        }
                    }
                    return list;
                }
            });
    }
    
}

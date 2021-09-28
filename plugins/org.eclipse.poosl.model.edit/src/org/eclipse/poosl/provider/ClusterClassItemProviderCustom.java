package org.eclipse.poosl.provider;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.poosl.ClusterClass;

public class ClusterClassItemProviderCustom extends ClusterClassItemProvider {

    public ClusterClassItemProviderCustom(AdapterFactory adapterFactory) {
        super(adapterFactory);
    }

    @Override
    public Object getImage(Object object) {
        ClusterClass element = (ClusterClass) object;
        String image = element.getName() == null || element.getName().isEmpty() //
                ? "full/custo16/System.gif" //$NON-NLS-1$
                : "full/obj16/ClusterClass"; //$NON-NLS-1$

        return overlayImage(object, getResourceLocator().getImage(image));
    }

}

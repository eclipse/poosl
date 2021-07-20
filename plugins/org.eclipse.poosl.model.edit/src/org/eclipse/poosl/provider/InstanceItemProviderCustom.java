/**
 * 
 */
package org.eclipse.poosl.provider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.poosl.Instance;
import org.eclipse.poosl.InstantiableClass;
import org.eclipse.poosl.Poosl;
import org.eclipse.poosl.PooslPackage;
import org.eclipse.poosl.xtext.importing.ImportingHelper;

/**
 * Customization of InstanceItemProvider
 * <p>
 * Name is conform to generation gap pattern of MWE2.
 * </p>
 * 
 * @author Obeo
 */
public class InstanceItemProviderCustom extends InstanceItemProvider {

    /**
     * Default constructor.
     * 
     * @param adapterFactory factory
     */
    public InstanceItemProviderCustom(AdapterFactory adapterFactory) {
        super(adapterFactory);
    }
    
    protected void addClassDefinitionPropertyDescriptor(Object object) 
    {
        itemPropertyDescriptors.add
            (new ItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_Instance_classDefinition_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_Instance_classDefinition_feature", "_UI_Instance_type"),
                 PooslPackage.Literals.INSTANCE__CLASS_DEFINITION,
                 true,
                 false,
                 true,
                 null,
                 null,
                 null){
                
                @Override
                public Collection<?> getChoiceOfValues(Object object) {
                    List<InstantiableClass> scopedClasses = new ArrayList<InstantiableClass>();
                    if(object instanceof Instance){
                        Instance instance = (Instance) object;
                        List<Resource> resources = ImportingHelper.computeAllDependencies(instance.eResource());
                        for (Resource resource : resources) {
                            Poosl poosl = ImportingHelper.toPoosl(resource);
                            scopedClasses.addAll(poosl.getClusterClasses());
                            scopedClasses.addAll(poosl.getProcessClasses());
                        }
                    }
                    return scopedClasses;
                }
            });
    }

}

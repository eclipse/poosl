/*******************************************************************************
 * Copyright (c) 2021 TNO/ESI
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    TNO/ESI - initial API and implementation
 *    Obeo - refactoring
 *******************************************************************************/
package org.eclipse.poosl.sirius;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.poosl.sirius.debug.GraphicalDebugUpdater;
import org.eclipse.sirius.business.api.componentization.ViewpointRegistry;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle.
 * 
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 */
public class Activator extends AbstractUIPlugin {

    /** The plug-in ID. */
    public static final String PLUGIN_ID = "org.eclipse.poosl.sirius"; //$NON-NLS-1$

    /** Message updater. */
    public static final GraphicalDebugUpdater MESSAGEUPDATER = getUpdaterInstance();

    /** Path to Design file. */
    public static final String DESIGN_PATH = PLUGIN_ID + "/description/poosl.odesign"; //$NON-NLS-1$

    // The shared instance
    private static Activator plugin;

    // The registered viewpoints
    private static Set<Viewpoint> viewpoints;

    private static GraphicalDebugUpdater updaterInstance;

    private static GraphicalDebugUpdater getUpdaterInstance() {
        if (updaterInstance == null) {
            updaterInstance = new GraphicalDebugUpdater();
        }
        return updaterInstance;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework. BundleContext)
     */
    @Override
    public void start(BundleContext context) throws Exception {
        super.start(context);
        plugin = this;
        viewpoints = new HashSet<>();
        viewpoints.addAll(ViewpointRegistry.getInstance().registerFromPlugin(DESIGN_PATH));
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
     */
    @Override
    public void stop(BundleContext context) throws Exception {
        plugin = null;
        if (viewpoints != null) {
            for (final Viewpoint viewpoint : viewpoints) {
                ViewpointRegistry.getInstance().disposeFromPlugin(viewpoint);
            }
            viewpoints.clear();
            viewpoints = null;
        }

        super.stop(context);
    }

    /**
     * Returns the shared instance.
     * 
     * @return the shared instance
     */
    public static Activator getDefault() {
        return plugin;
    }
}

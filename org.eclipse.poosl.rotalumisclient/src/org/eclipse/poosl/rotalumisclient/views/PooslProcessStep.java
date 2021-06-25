package org.eclipse.poosl.rotalumisclient.views;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.debug.core.DebugException;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.poosl.rotalumisclient.Messages;
import org.eclipse.poosl.rotalumisclient.debug.PooslDebugTarget;
import org.eclipse.poosl.rotalumisclient.debug.PooslThread;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

public class PooslProcessStep {
    private static final Logger LOGGER = Logger.getLogger(PooslProcessStep.class.getName());

    private PooslProcessStep() {
        throw new IllegalStateException("Utility class");
    }

    public static void doProcessStep(PooslThread thread) {
        if (thread != null && thread.getDebugTarget() instanceof PooslDebugTarget) {
            PooslDebugTarget pTarget = (PooslDebugTarget) thread.getDebugTarget();
            try {
                pTarget.processStep(thread.getName());
            } catch (DebugException e) {
                LOGGER.log(Level.WARNING, "Could not get thread name.", e.getCause());
            }
        }
    }

    public static void addMenuItemProcessStep(final Menu menu, final PooslThread thread) {
        MenuItem menuProcessStep = new MenuItem(menu, SWT.NONE);
        menuProcessStep.setText(Messages.ACTION_MENU_PROCESS_STEP);

        try {
            ImageDescriptor stepIcon = ImageDescriptor.createFromURL(new URL("platform:/plugin/org.eclipse.poosl.rotalumisclient/icons/icon_process_step.png"));
            Image image = stepIcon.createImage();
            menuProcessStep.setImage(image);
        } catch (MalformedURLException e) {
            LOGGER.log(Level.FINE, "Could not find step icon");
        }

        menuProcessStep.addSelectionListener(new SelectionListener() {
            @Override
            public void widgetDefaultSelected(SelectionEvent e) {
                widgetSelected(e);
            }

            @Override
            public void widgetSelected(SelectionEvent arg0) {
                doProcessStep(thread);
            }
        });
    }

    public static final IDoubleClickListener doubleClickListener = new IDoubleClickListener() {
        @Override
        public void doubleClick(DoubleClickEvent event) {
            Object obj = ((TreeSelection) event.getSelection()).getFirstElement();
            if (obj instanceof PooslThread) {
                doProcessStep((PooslThread) obj);
            }
        }
    };
}

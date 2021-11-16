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
package org.eclipse.poosl.rotalumisclient.views.debugview;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.debug.core.model.IThread;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.StyledCellLabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.StyledString.Styler;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.poosl.generatedxmlclasses.TTransition;
import org.eclipse.poosl.rotalumisclient.debug.PooslDebugTarget;
import org.eclipse.poosl.rotalumisclient.debug.PooslThread;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.TextStyle;
import org.eclipse.swt.widgets.Display;

/**
 * This is the Label Provider for the {@link PooslDebugView}.
 * 
 * @author Koen Staal
 *
 */
public class PooslDebugLabelProvider extends StyledCellLabelProvider {
    private static final Logger LOGGER = Logger.getLogger(PooslDebugLabelProvider.class.getName());

    private static final Color EXECUTABLE_COLOR = Display.getDefault()
            .getSystemColor(SWT.COLOR_RED);

    private static final Color EXECUTABLE_DELAY_COLOR = Display.getDefault()
            .getSystemColor(SWT.COLOR_BLUE);

    private static final String TERMINATED = "<terminated> "; //$NON-NLS-1$

    private Image threadRunImage;

    private Image threadSuspendedImage;

    private Image threadTerminatedImage;

    private Image debugTargetRunImage;

    private Image debugTargetSuspendedImage;

    private Image debugTargetTerminatedImage;

    private Styler executableColorStyler = new Styler() {
        @Override
        public void applyStyles(TextStyle textStyle) {
            textStyle.foreground = EXECUTABLE_COLOR;
        }
    };

    private Styler executableDelayColorStyler = new Styler() {
        @Override
        public void applyStyles(TextStyle textStyle) {
            textStyle.foreground = EXECUTABLE_DELAY_COLOR;
        }
    };

    public PooslDebugLabelProvider() {
        super();
        try {
            threadRunImage = ImageDescriptor.createFromURL(new URL(
                    "platform:/plugin/org.eclipse.debug.ui/icons/full/obj16/thread_obj.png")) //$NON-NLS-1$
                    .createImage();
            threadSuspendedImage = ImageDescriptor.createFromURL(new URL(
                    "platform:/plugin/org.eclipse.debug.ui/icons/full/obj16/threads_obj.png")) //$NON-NLS-1$
                    .createImage();
            threadTerminatedImage = ImageDescriptor.createFromURL(new URL(
                    "platform:/plugin/org.eclipse.debug.ui/icons/full/obj16/threads_obj.png")) //$NON-NLS-1$
                    .createImage();
            debugTargetRunImage = ImageDescriptor.createFromURL(new URL(
                    "platform:/plugin/org.eclipse.debug.ui/icons/full/obj16/debugt_obj.png")) //$NON-NLS-1$
                    .createImage();
            debugTargetSuspendedImage = ImageDescriptor.createFromURL(new URL(
                    "platform:/plugin/org.eclipse.debug.ui/icons/full/obj16/debugts_obj.png")) //$NON-NLS-1$
                    .createImage();
            debugTargetTerminatedImage = ImageDescriptor.createFromURL(new URL(
                    "platform:/plugin/org.eclipse.debug.ui/icons/full/obj16/debugtt_obj.png")) //$NON-NLS-1$
                    .createImage();
        } catch (MalformedURLException e) {
            LOGGER.log(Level.WARNING, e.getMessage(), e);
        }
    }

    @Override
    public void dispose() {
        threadRunImage.dispose();
        threadSuspendedImage.dispose();
        threadTerminatedImage.dispose();
        debugTargetRunImage.dispose();
        debugTargetSuspendedImage.dispose();
        debugTargetTerminatedImage.dispose();
    }

    @Override
    public void update(ViewerCell cell) {
        Object element = cell.getElement();
        StyledString styledString = new StyledString();
        try {
            styledString = getStyledString(element);
        } catch (DebugException e) {
            LOGGER.log(Level.WARNING, e.getMessage(), e);
        }
        cell.setText(styledString.toString());
        cell.setStyleRanges(styledString.getStyleRanges());
        cell.setImage(getImage(element));
        super.update(cell);
    }

    private StyledString getStyledString(Object element) throws DebugException {
        if (element instanceof PooslThread) {
            PooslDebugTarget debugTarget = (PooslDebugTarget) ((PooslThread) element)
                    .getDebugTarget();
            List<TTransition> transitions = debugTarget.getPossibleTransitions();
            TransitionType hasTransition = hasPossibleTransition(transitions,
                    ((PooslThread) element).getName());
            if (!hasTransition.equals(TransitionType.NONE)) {
                if (hasTransition.equals(TransitionType.DELAY)) {
                    return new StyledString(getText(element), executableDelayColorStyler);
                } else {
                    return new StyledString(getText(element), executableColorStyler);
                }
            }
        } else if (element instanceof PooslDebugTreeItem) {
            PooslDebugTarget debugTarget = (PooslDebugTarget) ((PooslDebugTreeItem) element)
                    .getDebugTarget();
            List<TTransition> transitions = debugTarget.getPossibleTransitions();
            for (IThread iThread : ((PooslDebugTreeItem) element).getThreads()) {
                TransitionType hasTransition = hasPossibleTransition(transitions,
                        iThread.getName());
                if (!hasTransition.equals(TransitionType.NONE)) {
                    if (hasTransition.equals(TransitionType.DELAY)) {
                        return new StyledString(getText(element), executableDelayColorStyler);
                    } else {
                        return new StyledString(getText(element), executableColorStyler);
                    }
                }
            }
        }
        return new StyledString(getText(element));
    }

    public Image getImage(Object element) {
        if (element instanceof IDebugTarget) {
            if (((IDebugTarget) element).isTerminated()) {
                return debugTargetTerminatedImage;
            } else if (((IDebugTarget) element).isSuspended()) {
                return debugTargetSuspendedImage;
            } else {
                return debugTargetRunImage;
            }
        } else if (element instanceof PooslDebugTreeItem) {
            PooslDebugTreeItem treeitem = (PooslDebugTreeItem) element;

            for (IThread iThread : treeitem.getThreads()) {
                if (iThread.isSuspended()) {
                    return threadSuspendedImage;
                } else if (iThread.isTerminated()) {
                    return threadTerminatedImage;
                }
            }

            return threadRunImage;
        } else if (element instanceof IThread) {
            IThread thread = (IThread) element;
            if (thread.isTerminated()) {
                return threadTerminatedImage;
            } else if (thread.isSuspended()) {
                return threadSuspendedImage;
            } else {
                return threadRunImage;
            }
        }
        return null;
    }

    public String getText(Object element) {
        if (element instanceof PooslDebugTarget) {
            try {
                PooslDebugTarget target = (PooslDebugTarget) element;
                String text = ""; //$NON-NLS-1$
                if (target.isTerminated()) {
                    text += TERMINATED;
                }

                text += target.getName();
                if (!target.isTerminated() && !target.getSimulatedTime().isEmpty()) {
                    text += " [Simulated time: " + target.getSimulatedTime() + "]";
                }
                return text;
            } catch (DebugException e) {
                LOGGER.log(Level.WARNING, e.getMessage(), e);
            }
        } else if (element instanceof PooslThread) {
            try {
                IThread thread = (PooslThread) element;
                String threadName = thread.getName()
                        .substring(thread.getName().lastIndexOf('/') + 1);

                if (((PooslThread) element).isTerminated()) {
                    return TERMINATED + threadName;
                } else {
                    return threadName;
                }
            } catch (DebugException e) {
                LOGGER.log(Level.WARNING, e.getMessage(), e);
            }
        } else if (element instanceof PooslDebugTreeItem) {
            PooslDebugTreeItem treeitem = (PooslDebugTreeItem) element;
            for (IThread iThread : treeitem.getThreads()) {
                if (iThread.isTerminated()) {
                    return TERMINATED + treeitem.getName();
                }
            }
            return treeitem.getName();
        }
        return element.toString();
    }

    public enum TransitionType {
        NONE, NORMAL, COMM_SEND, COMM_RECV, DELAY
    }

    public TransitionType hasPossibleTransition(
            List<TTransition> possibleTransitions, String processPath) {
        for (TTransition transition : possibleTransitions) {
            if (transition.getProcessStep() != null) {
                if (transition.getProcessStep().getProcessPath().equals(processPath)) {
                    return TransitionType.NORMAL;
                }
            } else if (transition.getCommunication() != null) {
                if (transition.getCommunication().getSender().getProcessPath()
                        .equals(processPath)) {
                    return TransitionType.COMM_SEND;
                } else if (transition.getCommunication().getReceiver().getProcessPath()
                        .equals(processPath)) {
                    return TransitionType.COMM_RECV;
                }
            } else if (transition.getDelay() != null
                    && transition.getDelay().getProcessPath().equals(processPath)) {
                return TransitionType.DELAY;
            }
        }
        return TransitionType.NONE;
    }
}

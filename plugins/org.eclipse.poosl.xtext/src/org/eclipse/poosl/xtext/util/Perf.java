/* ************************************************************************** *
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
 * ************************************************************************* */
package org.eclipse.poosl.xtext.util;

import java.io.PrintStream;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Supplier;

/**
 * Utility class to log some performance.
 *
 * @author nperansin
 */
public class Perf {

    /** Default tracker: Do not leave permanently reference on it. */
    @Deprecated
    public static final Perf DEFAULT = new Perf(true);

    private static final DateTimeFormatter DATE_FORMATER = DateTimeFormatter.ofPattern("HH:mm:ss"); //$NON-NLS-1$

    private static final PrintStream LOG_OUTPUT = Boolean.getBoolean("perf.highlight") //$NON-NLS-1$
        ? System.err : System.out;

    private final boolean enable;

    private final String header;

    /**
     * Default constructor.
     *
     * @param enable
     *     of logging
     */
    public Perf(boolean enable) {
        this(null, enable);
    }

    /**
     * Default constructor.
     *
     * @param header
     *     of messages
     * @param enable
     *     of logging
     */
    public Perf(String header, boolean enable) {
        this.header = header != null ? " [" + header + "] " : " "; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        this.enable = enable;
    }

    /**
     * Traces in log a message.
     *
     * @param message
     *     to log
     * @return the date of the trace
     */
    public LocalDateTime trace(String message) {
        return traceDuration(message, null);
    }

    /**
     * Traces duration of a task in log a message.
     *
     * @param message
     *     to log
     * @param start
     *     of task
     * @return date of the trace (null if not active)
     */
    protected LocalDateTime traceDuration(String message, LocalDateTime start) {
        if (!enable) {
            return null;
        }

        LocalDateTime end = LocalDateTime.now();
        String duration = ""; //$NON-NLS-1$ 
        if (start != null) {
            float seconds = Duration.between(start, end).toMillis() / 1000f;
            duration = " (" + (seconds < 60 //$NON-NLS-1$ 
                ? seconds + "s" //$NON-NLS-1$ 
                : seconds / 60f + "min") //$NON-NLS-1$ 
                    + ")"; //$NON-NLS-1$ 
        }
        LOG_OUTPUT.println(end.format(DATE_FORMATER) + header + message + duration);
        return end;
    }

    /**
     * Traces duration of a task in log a message.
     *
     * @param message
     *     to log
     * @param task
     *     to perform
     */
    public void benchmark(String message, Runnable task) {
        if (enable) {
            benchmark(message, () -> {
                task.run();
                return null;
            });
        } else {
            task.run();
        }
    }

    /**
     * Traces duration of a task in log a message.
     *
     * @param <R>
     *     returned type
     * @param message
     *     to log
     * @param task
     *     to perform
     * @return result of the task
     */
    public <R> R benchmark(String message, Supplier<R> task) {
        LocalDateTime start = trace("{begin:" + message + "}"); //$NON-NLS-1$ //$NON-NLS-2$
        R result = task.get();
        traceDuration("{end:" + message + "}", start); //$NON-NLS-1$ //$NON-NLS-2$
        return result;
    }

}

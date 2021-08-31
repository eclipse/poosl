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
package test;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * The ModelCreator.
 * 
 * @author <a href="mailto:arjan.mooij@tno.nl">Arjan Mooij</a>
 *
 */
public class ModelCreator {

	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		PrintWriter writer = new PrintWriter("D:\\asml\\testmodel_100.poosl", "UTF-8");
		writer.println("import \"api/basic.poosl\" " + "process class someName\n" + "instance variables\n"
				+ "port interface\n portA\n" + "message interface\n portA!blub\n" + "initial method call\n"
				+ "someMethod()()\n" + "instance methods\n" + "someMethod()()\n" + "skip.\n"
				+ "someMethod(bla: Integer)()\n" + "skip.\n" + "someMethod(bla: Integer)()\n" + "skip.\n");
		writer.println("system channels ");
		writer.println();
		for (int i = 0; i < 100; i++) {
			writer.println("instance someInstance" + i + ": someName() connections");
		}
		writer.close();
	}

}

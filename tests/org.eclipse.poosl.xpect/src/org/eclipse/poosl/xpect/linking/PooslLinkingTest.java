package org.eclipse.poosl.xpect.linking;

import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.xpect.runner.XpectRunner;
import org.xpect.runner.XpectTestFiles;
import org.xpect.xtext.lib.tests.LinkingTest;

@RunWith(XpectRunner.class)
@XpectTestFiles(fileExtensions = "xt")

@Ignore
@Deprecated  // Disabled scoping and linking test, no longer uses default xtext reference
public class PooslLinkingTest extends LinkingTest {

}

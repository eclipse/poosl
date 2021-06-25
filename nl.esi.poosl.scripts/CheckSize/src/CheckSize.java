import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Consumer;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import nl.esi.poosl.xtext.PooslStandaloneSetup;

public class CheckSize {
	
	
	final private static String workspace = "C:\\Developer\\TNO\\Poosl\\Trunk\\";

	final private static String sourcePath = workspace + "nl.esi.poosl.examples\\models-basic\\ProducerConsumer"; // 136
//	final private static String sourcePath = workspace + "nl.esi.poosl.examples\\models-complex\\SoCInterconnects\\Bus"; // 827
//	final private static String sourcePath = workspace + "nl.esi.poosl.examples\\models-complex\\SoCInterconnects\\Mesh"; // 1483
//	final private static String sourcePath = workspace + "nl.esi.poosl.examples\\models-complex\\MPSoC"; // 5.809 [65.005 bytes]

//	final private static String sourcePath = workspace + "wings-simple\\models"; // 21.274 (of which 3.359 IntegerConstants = 16%) (of which 3.188 DatMethodCallExpressions = 15%) [167.687 bytes]
//	final private static String sourcePath = workspace + "wings-xxl\\models"; // 326.946 (of which 122.590 IntegerConstants = 37%) (of which 65.351 DataMethodCallExpressions = 20%) [2.748.206 bytes]
//	final private static String sourcePath = workspace + "wings-flexray\\models"; // 2.646.453 (of which 1.098.062 IntegerConstants = 41%) (of which 557.051 DataMethodCallExpressions = 21%s) [20.339.057 bytes]

	public static void main(String[] args) {
		new PooslStandaloneSetup().createInjectorAndDoEMFRegistration();
		ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet.getPackageRegistry().put(nl.esi.poosl.PooslPackage.eINSTANCE.getNsURI(),
				nl.esi.poosl.PooslPackage.eINSTANCE);

		System.out.println("Folder " + sourcePath);
		File[] files = new File(sourcePath).listFiles();

		final Map<String, Integer> accu = new HashMap<>();
		maxDepth = 0;
		for (File fInput : files) {
			if (!fInput.isDirectory()) {
				System.out.println("* " + fInput);
				URI uri = URI.createFileURI(fInput.getAbsolutePath());
				Resource resource = resourceSet.getResource(uri, true);
				TreeIterator<EObject> iterator = resource.getAllContents();

				Consumer<EObject> action = new Consumer<EObject>() {

					@Override
					public void accept(EObject x) {
						String strClass = x.getClass().toString();
						if (!accu.containsKey(strClass)) {
							accu.put(strClass, 0);
						}
						accu.put(strClass, accu.get(strClass) + 1);

						int depth = computeDepth(x);
						maxDepth = Math.max(maxDepth, depth);
						
					}
				};
					
				iterator.forEachRemaining(action);
			}
		}

		System.out.println();
		System.out.println("Class counts:");
		int sum = 0;
		for (Entry<String, Integer> entry : accu.entrySet()) {
			String strClass = entry.getKey();
			int count = entry.getValue();
			System.out.println("* " + strClass + " : " + count);
			sum += count;
		}
		System.out.println();
		System.out.println("Total count: " + sum);
		System.out.println("Maximum depth: " + maxDepth);
	}

	static int maxDepth; // not ideal...

	private static int computeDepth(EObject x) {
		int depth = 0;
		while (x != null) {
			depth++;
			x = x.eContainer();
		}
		return depth;
	}
}

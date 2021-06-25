package nl.esi.poosl.legacysupport.poosl2xml;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import nl.esi.poosl.generatedxmlclasses.TInstanceType;
import nl.esi.poosl.impl.GuardedStatementImpl;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;

public class PooslModelInformation {
	private Integer currentIdentifier = 0;
	private String modelPath = "";
	private String xmlPath = "";
	private Map<SourceMapping, Integer> modelToIdentifierMapping;
	private Map<Integer, SourceMapping> identifierToModelMapping;
	private Map<String, TInstanceType> instances;

	private Map<String, String> instancePortMap;
	private List<URI> files;

	public void setInstances(Map<String, TInstanceType> instances) {
		this.instances = instances;
	}

	public Map<String, TInstanceType> getInstances() {
		if (instances == null) {
			instances = new LinkedHashMap<>();
		}
		return instances;
	}

	public String getModelPath() {
		return modelPath;
	}

	public void setModelPath(String modelPath) {
		this.modelPath = modelPath;
		this.xmlPath = createXMLPath();
	}

	public String getXmlPath() {
		return xmlPath;
	}

	public Map<SourceMapping, Integer> getModelToIdentifierMapping() {
		if (modelToIdentifierMapping == null) {
			modelToIdentifierMapping = new HashMap<>();
		}
		return modelToIdentifierMapping;
	}

	public Map<Integer, SourceMapping> getIdentifierToModelMapping() {
		if (identifierToModelMapping == null) {
			identifierToModelMapping = new HashMap<>();
		}
		return identifierToModelMapping;
	}

	public Integer addMapping(EObject object) {
		return addMapping(object, true);
	}

	/**
	 * This function will add the given model element to the mapping and give a
	 * unique identifier to it.
	 * 
	 * @param object The model element to add to the mapping
	 * @return the identifier given to the input object
	 */
	public Integer addMapping(EObject originalObject, Boolean breakpointSupported) {
		currentIdentifier++;
		String tokenText = "";
		EObject correctedObject;
		if (originalObject instanceof GuardedStatementImpl) {
			correctedObject = ((GuardedStatementImpl) originalObject).getGuard();
			tokenText = "[" + NodeModelUtils.getTokenText(NodeModelUtils.getNode(correctedObject)) + "]";
		} else {
			correctedObject = originalObject;
			tokenText = NodeModelUtils.getTokenText(NodeModelUtils.getNode(correctedObject));
		}
		tokenText = tokenText.replace("\t", " ");
		tokenText = tokenText.replace("\r", " ");
		tokenText = tokenText.replace("\n", " ");
		// Remove double spaces and replace them by single ones
		while (tokenText.contains("  ")) {
			tokenText = tokenText.replace("  ", " ");
		}
		tokenText = tokenText.trim();

		INode node = NodeModelUtils.getNode(correctedObject);
		SourceMapping sourceMapping = new SourceMapping(correctedObject.eResource().getURI().toFileString(), node.getStartLine(),
				node.getOffset(), node.getLength(), tokenText, breakpointSupported);
		getIdentifierToModelMapping().put(currentIdentifier, sourceMapping);
		getModelToIdentifierMapping().put(sourceMapping, currentIdentifier);
		return currentIdentifier;
	}

	private String createXMLPath() {
		StringBuilder stringBuilder = new StringBuilder(modelPath);
		stringBuilder.replace(stringBuilder.lastIndexOf(File.separator), stringBuilder.lastIndexOf(File.separator),
				File.separator + "simulator");
		stringBuilder.replace(stringBuilder.lastIndexOf("."), stringBuilder.length(), ".xml");
		return stringBuilder.toString();
	}

	public Map<String, String> getInstancePortMap() {
		return instancePortMap;
	}

	public void setInstancePortMap(Map<String, String> instancePortMap) {
		this.instancePortMap = instancePortMap;
	}

	public class SourceMapping {
		private String filePath;
		private int lineNumber;
		private int offset;
		private int length;
		private String sourceText;
		private Boolean breakpointSupported;

		public SourceMapping(String filePath, int lineNumber, int offset, int length, String sourceText,
				Boolean breakpointSupported) {
			this.filePath = filePath;
			this.lineNumber = lineNumber;
			this.offset = offset;
			this.length = length;
			this.sourceText = sourceText;
			this.breakpointSupported = breakpointSupported;
		}

		public String getFilePath() {
			return filePath;
		}

		public int getLineNumber() {
			return lineNumber;
		}

		public int getOffset() {
			return offset;
		}

		public int getLength() {
			return length;
		}

		public String getSourceText() {
			return sourceText;
		}

		public Boolean isBreakPointSupported() {
			return breakpointSupported;
		}
	}

	public void setFiles(List<URI> filesToTransform) {
		this.files = filesToTransform;
	}

	public List<URI> getFiles() {
		return files;
	}
}

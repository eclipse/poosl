package nl.esi.poosl.rotalumisclient.sourcemapping;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Logger;

import javax.xml.bind.JAXBElement;

import nl.esi.poosl.generatedxmlclasses.TExecutiontree;
import nl.esi.poosl.generatedxmlclasses.TExecutiontreeBase;
import nl.esi.poosl.generatedxmlclasses.TExecutiontreeMessageReceive;
import nl.esi.poosl.generatedxmlclasses.TExecutiontreeMessageSend;
import nl.esi.poosl.generatedxmlclasses.TGetPositionResponse;
import nl.esi.poosl.generatedxmlclasses.TSourcePosition;
import nl.esi.poosl.rotalumisclient.Client;

public class PooslSourceMap {
	private final Client client;
	private final Map<BigInteger, String> handle2Files;

	private final Map<Integer, PooslSourceMapping> sourceMappings = new HashMap<>();
	private final Map<Integer, PooslSourceMappingListener> requestedMappings = new HashMap<>();

	public static final PooslSourceMapping EMPTY_MAPPING = new PooslSourceMapping("", -1, -1, -1, -1, -1, "...");
	private static final Logger LOGGER = Logger.getLogger(PooslSourceMap.class.getName());

	public PooslSourceMap(Client client, Map<String, BigInteger> filesToHandle) {
		this.client = client;
		this.handle2Files = convertToHandleFiles(filesToHandle);
		LOGGER.info("PooslSourceMap created.");
	}

	public void getSourceMapping(Integer handle, PooslSourceMappingListener listener) {
		PooslSourceMapping sourceMapping = sourceMappings.get(handle);
		if (sourceMapping != null) {
			listener.requestedSourceMapping(sourceMapping);
		} else {
			requestSourceMapping(handle, listener);
		}
	}

	private void requestSourceMapping(Integer handle, PooslSourceMappingListener listener) {
		requestedMappings.put(handle, listener);
		client.getSourceMapping(handle);
	}

	public boolean containsSourceMapping(Integer handle) {
		return sourceMappings.containsKey(handle);
	}

	public void reponseSourceMapping(TGetPositionResponse response) {
		int handle = response.getStmtHandle();
		PooslSourceMappingListener listener = requestedMappings.remove(handle);
		PooslSourceMapping mapping = convertResponseToSourceMap(response, handle);
		sourceMappings.put(handle, mapping);
		if (listener != null) {
			listener.returnSourceMapping(mapping);
		}
	}

	private PooslSourceMapping convertResponseToSourceMap(TGetPositionResponse response, int handle) {
		TSourcePosition begin = response.getBegin();
		TSourcePosition end = response.getEnd();

		int bLine = begin.getLine().intValue();
		int eLine = end.getLine().intValue();
		int bColumn = begin.getColumn().intValue();
		int eColumn = end.getColumn().intValue();

		int bOffset = begin.getOffset().intValue();
		int eOffset = end.getOffset().intValue();

		String filePath = handle2Files.get(begin.getFile());

		return createSourceMapping(handle, filePath, bLine, bColumn, bOffset, eLine, eColumn, eOffset);
	}

	/**
	 * Returns the text from the file on the specified line and offset. Rotalumis
	 * can only provide the source location and not the text
	 * 
	 * @param filePath
	 * @param lineNumber
	 * @param offset
	 * @param length
	 * @return PooslSourceMapping or EMPTY_MAPPING when anything fails
	 * @throws FileNotFoundException
	 */
	private PooslSourceMapping createSourceMapping(int handle, String filePath, int bLine, int bColumn, int bOffset,
			int eLine, int eColumn, int eOffset) {

		String text = "";
		if (bLine == 0 && bColumn == 0 && bOffset == 0 && eLine == 0 && eColumn == 0 && eOffset == 0) {
			return EMPTY_MAPPING;
		} else if (filePath != null && bLine != -1 && bColumn != -1 && bOffset != -1 && eLine != -1 && eColumn != -1
				&& eOffset != -1) {
			try {
				BufferedReader reader = new BufferedReader(new FileReader(filePath));
				try {
					int length = eOffset - bOffset;
					reader.skip(bOffset);
					char[] buff = new char[length];
					reader.read(buff);
					text = new String(buff);
				} catch (IOException e1) {
					Logger.getGlobal().severe("PooslSourceMap: Could not get the text: " + filePath);
				} finally {
					try {
						reader.close();
					} catch (IOException e) {
						Logger.getGlobal()
								.severe("PooslSourceMap: Could not close reader for lookup in file: " + filePath);
					}
				}
			} catch (FileNotFoundException e1) {
				Logger.getGlobal().severe("PooslSourceMap: Could not find file: " + filePath);
				return EMPTY_MAPPING;
			}
		} else {
			Logger.getGlobal().warning("PooslSourceMap: Returned location is not valid, for handle:" + handle);
			return EMPTY_MAPPING;
		}
		return new PooslSourceMapping(filePath, bLine, bColumn, text.length(), bOffset, eOffset, text);
	}

	public Map<BigInteger, String> convertToHandleFiles(Map<String, BigInteger> filesToHandle) {
		HashMap<BigInteger, String> handleToFiles = new HashMap<>();
		String filePrefix = "file:";
		for (Entry<String, BigInteger> entry : filesToHandle.entrySet()) {
			String filePath = entry.getKey();
			if (filePath.startsWith(filePrefix)) {
				filePath = filePath.substring(filePrefix.length());
			}
			handleToFiles.put(entry.getValue(), filePath);
		}
		return handleToFiles;
	}

	public void dispose() {
		requestedMappings.clear();
	}

	public void checkExecutionTreeForExternalMapping(TExecutiontree executionTree) {
		for (JAXBElement<? extends TExecutiontreeBase> element : executionTree.getSequentialOrMethodCallOrParallel()) {
			TExecutiontreeBase val = element.getValue();
			int stmtHandle = val.getStmtHandle();
			if (val instanceof TExecutiontreeMessageReceive) {
				TExecutiontreeMessageReceive message = (TExecutiontreeMessageReceive) val;
				requestMappingWithBackup(stmtHandle, message.getPort() + "?" + message.getMessage());
			} else if (val instanceof TExecutiontreeMessageSend) {
				TExecutiontreeMessageSend message = (TExecutiontreeMessageSend) val;
				requestMappingWithBackup(stmtHandle, message.getPort() + "!" + message.getMessage());
			}
		}
	}

	private void requestMappingWithBackup(final int stmtHandle, final String backupText) {
		getSourceMapping(stmtHandle, new PooslSourceMappingListener(false) {
			@Override
			public void requestedSourceMapping(PooslSourceMapping mapping) {
				if (mapping == EMPTY_MAPPING) {
					sourceMappings.put(stmtHandle, new PooslSourceMapping("", -1, -1, -1, -1, -1, backupText));
				}
			}
		});
	}
}

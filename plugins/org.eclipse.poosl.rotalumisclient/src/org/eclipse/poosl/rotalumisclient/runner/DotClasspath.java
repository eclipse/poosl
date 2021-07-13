
package org.eclipse.poosl.rotalumisclient.runner;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import com.google.common.collect.Lists;

public class DotClasspath {

    private class Handler extends DefaultHandler {
        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            if ("classpathentry".equals(qName)) { //$NON-NLS-1$
                String kind = attributes.getValue("kind"); //$NON-NLS-1$
                String path = attributes.getValue("path"); //$NON-NLS-1$
                if ("src".equals(kind)) //$NON-NLS-1$
                    sources.add(path);
                else if ("output".equals(kind)) //$NON-NLS-1$
                    output = path;
            }
        }
    }

    private String output;

    private List<String> sources = Lists.newArrayList();

    public DotClasspath(File file) {
        parse(file);
    }

    public String getOutput() {
        return output;
    }

    public List<String> getSources() {
        return sources;
    }

    private void parse(File file) {
        try {
            SAXParserFactory parserFactory = SAXParserFactory.newInstance();
            SAXParser parser = parserFactory.newSAXParser();
            XMLReader reader = parser.getXMLReader();
            reader.setContentHandler(new Handler());
            parse(file, reader);
        } catch (SAXException | ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    private static void parse(File file, XMLReader reader) throws SAXException {
        try (InputStream in = new FileInputStream(file)) {
            reader.parse(new InputSource(in));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

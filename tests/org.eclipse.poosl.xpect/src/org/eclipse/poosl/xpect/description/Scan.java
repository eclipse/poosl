package org.eclipse.poosl.xpect.description;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.URI;
import org.xpect.setup.XpectSetupComponent;
import org.xpect.xtext.lib.setup.FileSetupContext;
import org.xpect.xtext.lib.setup.workspace.IResourceFactory;
import org.xpect.xtext.lib.setup.workspace.Workspace.Instance;
import org.xpect.xtext.lib.util.IFileUtil;
import org.xpect.xtext.lib.util.URIUtil;

@XpectSetupComponent
public class Scan implements IResourceFactory<IResource, IContainer> {

    private final static String EXTENSION_TOKEN = ".";
    
    // private boolean recursive = false;
    private String extension;
    
    private String from;
    
    public Scan() {
        this(null, null); // any file from context
    }
    
    public Scan(String from, String extension) {
        setFrom(from);
        setExtension(extension);
    }

    
    public void setExtension(String value) {
        extension = value != null && !value.startsWith(EXTENSION_TOKEN) 
            ? EXTENSION_TOKEN + value
            : value;
    }
    
    
    public void setFrom(String value) {
        this.from = value;
    }

    @Override
    public IResource create(FileSetupContext ctx, IContainer container, Instance instance) throws IOException, CoreException {
        URI thisUri = ctx.getXpectFileURI();
        // ctx.getXpectFile()(expectedType, annotations)
        if (!thisUri.isFile()) {
            throw new IllegalStateException("Context not supported: " + thisUri);
        }
        Path thisPath = Path.of(thisUri.toFileString());
        Path src = thisPath.getParent();
        if (from != null && !from.isEmpty()) {
            src = src.resolve(from);
        }
        for (Path child : Files.list(src) // To improve: option for recursive
                .filter(it -> !thisPath.equals(it) && isApplicable(it))
                .collect(Collectors.toList())) { // IOException inhibit forEach usage
            
            IFileUtil.create(container, 
                    URIUtil.createLocalURI(src.relativize(child).toString()), 
                    Files.newInputStream(child));
        }
        
        return null; // in fact, it is not used ??
    }

    private boolean isApplicable(Path file) {
        return (extension == null || file.getFileName().toString().endsWith(extension)) 
                && !Files.isDirectory(file);
    }
    
    
}

package org.eclipse.m2m.internal.qvt.oml.compiler;

import java.util.HashMap;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.m2m.internal.qvt.oml.emf.util.EmfUtil;

@SuppressWarnings("serial")
public class PlatformNamespaceUriResourceMap extends HashMap<URI, Resource> {
	
	private EPackage.Registry registry;
	private URIConverter uriConverter;
	private boolean isInitialized= false;
	
	public PlatformNamespaceUriResourceMap(ResourceSet resourceSet) {
		this(resourceSet.getPackageRegistry(), resourceSet.getURIConverter());
	}
	
	public PlatformNamespaceUriResourceMap(EPackage.Registry registry, URIConverter uriConverter) {
		this.registry = registry;
		this.uriConverter = uriConverter;
	}
	
	private void initialize() {
		uriConverter.getURIMap().putAll(EcorePlugin.computePlatformURIMap(false));
		isInitialized = true;
	}
	
	public Resource get(Object key) {
		Resource resource = super.get(key);
		
		if (resource == null && key instanceof URI) {
			URI uri = (URI) key;
			
			if (uri.isPlatform() && !URIConverter.INSTANCE.exists(uri, null)) {
								
				if (!isInitialized) {
					initialize();
				}
				
				URI normalizedUri = uriConverter.normalize(uri);
				
				if (uriConverter.exists(normalizedUri, null)) {											
					Resource packageResource = null;
					try {
						packageResource = EmfUtil.loadResource(normalizedUri);
						EPackage rootPackage = EmfUtil.getFirstEPackageContent(packageResource);
							
						if (rootPackage != null) {
							EPackage ePackage = registry.getEPackage(rootPackage.getNsURI());
																		
							if (ePackage != null) {
								resource = ePackage.eResource();
								
								if (resource != null) {
									put(uri, resource);
								}
							}
						}
					}
					catch (Throwable t) {}
					finally {
						if (packageResource != null) {
							packageResource.unload();
						}
					}
				}
			}
		}				
		
		return resource;
	}

}

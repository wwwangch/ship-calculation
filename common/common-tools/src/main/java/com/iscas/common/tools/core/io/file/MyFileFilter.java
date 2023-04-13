
package com.iscas.common.tools.core.io.file;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Hashtable;


/**
 * @author admin
 */
@SuppressWarnings({"rawtypes", "unused"})
public class MyFileFilter implements FileFilter {

    private Hashtable filters;
    private String description = null;
    private String fullDescription = null;
    private boolean useExtensionsInDescription = true;

    public MyFileFilter() {
        this.filters = new Hashtable();
    }

    public MyFileFilter(String extension) {
        this(extension, null);
    }

    public MyFileFilter(String extension, String description) {
        this();
        if (extension != null) {
            addExtension(extension);
        }
        if (description != null) {
            setDescription(description);
        }
    }

    public MyFileFilter(String[] filters) {
        this(filters, null);
    }

    public MyFileFilter(String[] filters, String description) {
        this();
        Arrays.stream(filters).forEach(this::addExtension);
        if (description != null) {
            setDescription(description);
        }
    }

    @Override
    public boolean accept(File f) {
        if (f != null) {
            if (f.isDirectory()) {
                return true;
            }
            String extension = getExtension(f);
            return extension != null && filters.get(getExtension(f)) != null;
        }
        return false;
    }

    public String getExtension(File f) {
        if (f != null) {
            String filename = f.getName();
            int i = filename.lastIndexOf('.');
            if (i > 0 && i < filename.length() - 1) {
                return filename.substring(i + 1).toLowerCase();
            }
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public void addExtension(String extension) {
        if (filters == null) {
            filters = new Hashtable(5);
        }
        filters.put(extension.toLowerCase(), this);
        fullDescription = null;
    }

    @SuppressWarnings("StringConcatenationInLoop")
    public String getDescription() {
        if (fullDescription == null) {
            if (description == null || isExtensionListInDescription()) {
                fullDescription = description == null ? "(" : description + " (";
                // build the description from the extension list
                Enumeration extensions = filters.keys();
                if (extensions != null) {
                    fullDescription += "." + extensions.nextElement();
                    while (extensions.hasMoreElements()) {
                        fullDescription += ", ." + extensions.nextElement();
                    }
                }
                fullDescription += ")";
            } else {
                fullDescription = description;
            }
        }
        return fullDescription;
    }

    public void setDescription(String description) {
        this.description = description;
        fullDescription = null;
    }

    public void setExtensionListInDescription(boolean b) {
        useExtensionsInDescription = b;
        fullDescription = null;
    }

    public boolean isExtensionListInDescription() {
        return useExtensionsInDescription;
    }
}

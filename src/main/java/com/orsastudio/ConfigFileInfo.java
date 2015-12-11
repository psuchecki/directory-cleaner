package com.orsastudio;

import org.w3c.dom.Element;

import java.util.ArrayList;
import java.util.List;

public class ConfigFileInfo {
    public static final String URL_TAG_NAME = "url";
    public static final String FILE_TAG_NAME = "file";
    public static final String DIRECTORY_TAG_NAME = "directory";

    private String remoteUrl;
    private List<String> fileNames = new ArrayList<>();
    private List<String> dirNames = new ArrayList<>();

    public void addElement(Element element) {
        String elementName = element.getTagName();
        switch (elementName) {
            case URL_TAG_NAME:
                remoteUrl = element.getTextContent();
                break;
            case FILE_TAG_NAME:
                fileNames.add(element.getTextContent());
                break;
            case DIRECTORY_TAG_NAME:
                dirNames.add(element.getTextContent());
                break;
        }
    }

    public String getRemoteUrl() {
        return remoteUrl;
    }

    public List<String> getFileNames() {
        return fileNames;
    }

    public List<String> getDirNames() {
        return dirNames;
    }
}

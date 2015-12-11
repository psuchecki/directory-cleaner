package com.orsastudio;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Cleaner {
    public static final int ONE_HOUR = 3600000;

    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
        RemoteFileChecker remoteFileChecker = new RemoteFileChecker();
        ContentRemover contentRemover = new ContentRemover();

        while (true) {
            ConfigFileInfo configFileInfo = new ConfigurationFileParser().parseConfigFile();
            boolean remoteFileExists = remoteFileChecker.checkIfRemoteFileExists(configFileInfo.getRemoteUrl());
            if (remoteFileExists) {
                contentRemover.remove(configFileInfo);
                try {
                    Thread.sleep(ONE_HOUR);
                } catch (InterruptedException e) {
                }
            }
        }
    }
}

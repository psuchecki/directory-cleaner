package com.orsastudio;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Cleaner {
    public static final int ONE_HOUR = 3600000;

    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
        ConfigFileInfo configFileInfo = new ConfigurationFileParser().parseConfigFile();
        RemoteFileChecker remoteFileChecker = new RemoteFileChecker();

        while (true) {
            boolean remoteFileExists = remoteFileChecker.checkIfRemoteFileExists(configFileInfo.getRemoteUrl());
            if (remoteFileExists) {
                new ContentRemover().remove(configFileInfo);
                try {
                    Thread.sleep(ONE_HOUR);
                } catch (InterruptedException e) {
                }
            }
        }
    }
}

package com.orsastudio;

import java.net.HttpURLConnection;
import java.net.URL;

public class RemoteFileChecker {

    public static final String HEAD_REQUEST_METHOD = "HEAD";

    public boolean checkIfRemoteFileExists(String url) {
        try {
            HttpURLConnection.setFollowRedirects(false);
            HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
            con.setRequestMethod(HEAD_REQUEST_METHOD);
            return (con.getResponseCode() == HttpURLConnection.HTTP_OK);
        } catch (Exception e) {
            return false;
        }
    }
}

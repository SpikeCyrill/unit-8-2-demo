package com.brunoyam.networking.examples;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class UrlExample {

    public void show() throws Exception {
        URL justUrl = getUrl();
        URL relativeURL = getRelativeURL(justUrl);
        URL pieceMealeURL = getURLpiecemeal();

        System.out.println("First url:");
        parseURL(new URL("https://www.google.ru/maps/@60.03276,30.3446765,13z"));
        System.out.println();
        System.out.println("Second url:");
        parseURL(new URL("http://localhost:8080/api/turn?player=1#ANCHOR"));

        readURLstream(relativeURL);

        URLConnection connection = getConnectToURL(pieceMealeURL);
        readFromConnection(connection);
        writeToConnection(pieceMealeURL);
    }

    private URL getUrl() throws MalformedURLException {
        return new URL("https://www.google.com");
    }

    private URL getRelativeURL(URL url) throws MalformedURLException {
        return new URL(url, "maps");
    }

    private URL getURLpiecemeal() throws MalformedURLException {
        return new URL("http", "google.com", "/maps");
    }

    private void parseURL(URL url) {
        System.out.println("protocol = " + url.getProtocol());
        System.out.println("authority = " + url.getAuthority());
        System.out.println("host = " + url.getHost());
        System.out.println("port = " + url.getPort());
        System.out.println("path = " + url.getPath());
        System.out.println("query = " + url.getQuery());
        System.out.println("filename = " + url.getFile());
        System.out.println("ref = " + url.getRef());
    }

    private void readURLstream(URL url) throws IOException {

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(url.openStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null)
                System.out.println(inputLine);
            in.close();
    }

    private URLConnection getConnectToURL(URL url) throws IOException {
        URLConnection myURLConnection = url.openConnection();
        myURLConnection.connect();
        return myURLConnection;
    }

        private void readFromConnection(URLConnection connection) throws Exception {
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null)
                System.out.println(inputLine);
            in.close();
        }

        private void writeToConnection(URL url) throws IOException {

            URLConnection connection = url.openConnection();
            connection.setDoOutput(true);

            OutputStreamWriter out = new OutputStreamWriter(
                    connection.getOutputStream());
            out.write("string=" + connection.getURL().toString());
            out.close();

            connection = url.openConnection();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            connection.getInputStream()));
            String decodedString;
            while ((decodedString = in.readLine()) != null) {
                System.out.println(decodedString);
            }
            in.close();
        }

}

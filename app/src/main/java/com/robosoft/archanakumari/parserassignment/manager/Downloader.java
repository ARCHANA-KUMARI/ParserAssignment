package com.robosoft.archanakumari.parserassignment.manager;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
/**
 * Created by archanakumari on 16/12/15.
 */
public class Downloader {

    /*
      Download a file from the Internet and store it locally
      @param URL - the url of the file to download
      @param fos - a FileOutputStream to save the downloaded file to.
     */
    public static void DownloadFromUrl(String URL, FileOutputStream fileOutputStream) {  //this is the downloader method
        try {

            URL url = new URL(URL); //URL of the file
            /* Open a connection to that URL. */
            URLConnection urlConnection = url.openConnection();
            // Define InputStreams to read from the URLConnection.
            InputStream inputStream = urlConnection.getInputStream();
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            // Define OutputStreams to write to our file.
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            // Start reading the and writing our file.
            byte data[] = new byte[1024];
            int count;
            //loop and read the current chunk
            while ((count = bufferedInputStream .read(data)) != -1) {
                bufferedOutputStream.write(data, 0, count);
            }
            //Have to call flush or the  file can get corrupted.
            bufferedOutputStream.flush();
            bufferedOutputStream.close();

        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}

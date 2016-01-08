package com.robosoft.archanakumari.parserassignment.network;

import android.content.Context;

import com.robosoft.archanakumari.parserassignment.Modal.SongSite;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by archanakumari on 16/12/15.
 */
public class SitesXmlPullParser {

    static final String KEY_SONG = "song";
    static final String KEY_ID = "id";
    static final String KEY_TITLE = "title";
    static final String KEY_ARTIST = "artist";
    static final String KEY_DURATION = "duration";
    static final String kEY_THUMB_URL="thumb_url";

    public static List<SongSite> getStackSitesFromFile(Context ctx) {

        // List of SongSites that we will return
        List<SongSite> songSites;
        songSites = new ArrayList<SongSite>();

        // temp holder for current SongSite while parsing
        SongSite curSongSite = null;
        // temp holder for current text value while parsing
        String curText = "";

        try {
            // Get our factory and PullParser
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xpp = factory.newPullParser();

            // Open up InputStream and Reader of our file.
            FileInputStream fis = ctx.openFileInput("SongSites.xml");
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));

            // point the parser to our file.
            xpp.setInput(reader);

            // get initial eventType
            int eventType = xpp.getEventType();

            // Loop through pull events until we reach END_DOCUMENT
            while (eventType != XmlPullParser.END_DOCUMENT) {
                // Get the current tag
                String tagname = xpp.getName();

                // React to different event types appropriately
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if (tagname.equalsIgnoreCase(KEY_SONG)) {
                            // If we are starting a new <site> block we need
                            //a new SongSite object to represent it
                            curSongSite = new SongSite();
                        }
                        break;

                    case XmlPullParser.TEXT:
                        //grab the current text so we can use it in END_TAG event
                        curText = xpp.getText();
                        break;

                    case XmlPullParser.END_TAG:
                        if (tagname.equalsIgnoreCase(KEY_SONG )) {
                            // if </song> then we are done with current Site
                            // add it to the list.
                            songSites.add(curSongSite);
                        } else if (tagname.equalsIgnoreCase(KEY_ID )) {
                            // if </id> use setId() on curSite
                            curSongSite.setId(curText);
                        } else if (tagname.equalsIgnoreCase(KEY_TITLE)) {
                            // if </Title> use setTitle() on curSite
                            curSongSite.setTitle(curText);
                        } else if (tagname.equalsIgnoreCase(KEY_ARTIST)) {
                            // if </artist> use setArtist() on curSite
                            curSongSite.setArtist(curText);
                        } else if (tagname.equalsIgnoreCase(KEY_DURATION )) {
                            // if </duration> use setDuration() on curSite
                            curSongSite.setDuration(curText);
                        } else if (tagname.equalsIgnoreCase(kEY_THUMB_URL))
                            //if </thumb_url> use setThumb_url() on curSite
                            curSongSite.setThumb_url(curText);

                        break;

                    default:
                        break;
                }
                //move on to next iteration
                eventType = xpp.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // return the populated list.
        return songSites ;
    }
}

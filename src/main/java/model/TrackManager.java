package model;

import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import controller.Controller;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class TrackManager {
    private Controller controller;
    private ArrayList<Track> trackList;

    public TrackManager(Controller controller) {
        this.controller = controller;
        trackList = new ArrayList<>();
    }

    /**
     * Adds a track to the track list.
     * @param track The track
     * @return The track's info.
     */
    public String addToTrackList(Track track) {
        trackList.add(track);
        return getTrackList().get(getTrackList().size()-1).toString();
    }

    /**
     * Getter for the track list.
     * @return The track list.
     */
    public ArrayList<Track> getTrackList() {
        return trackList;
    }

    /**
     * Clears the track list.
     * @return The empty track list.
     */
    public ArrayList<Track> clearTrackList() {
        trackList.clear();
        return getTrackList();
    }


}

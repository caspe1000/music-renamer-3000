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

    public String addToTrackList(Track track) {
        trackList.add(track);
        return getTrackList().get(getTrackList().size()-1).toString();
    }

    public ArrayList<Track> getTrackList() {
        return trackList;
    }


}

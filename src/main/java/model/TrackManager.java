package model;

import java.util.ArrayList;

public class TrackManager {

    private ArrayList<Track> trackList;

    public TrackManager() {
        trackList = new ArrayList<>();
    }

    public ArrayList<Track> getTrackList() {
        return trackList;
    }
}

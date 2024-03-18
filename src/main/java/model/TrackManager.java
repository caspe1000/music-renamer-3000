package model;

import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import controller.Controller;

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

    public ArrayList<Track> importTrack(File[] files) {
        for (File file : files) {
            try {
                Mp3File track = new Mp3File(file);
                ID3v2 trackTag = track.getId3v2Tag();

                String filename = file.getName();
                String title = trackTag.getTitle();
                String artist = trackTag.getArtist();
                String album = trackTag.getAlbum();

                addToTrackList(new Track(filename, title, artist, album));

            } catch (IOException | UnsupportedTagException | InvalidDataException e) {
                e.printStackTrace();
            }
        }
        return getTrackList();
    }

    public String addToTrackList(Track track) {
        trackList.add(track);
        return track.toString();
    }

    public ArrayList<Track> getTrackList() {
        return trackList;
    }
}

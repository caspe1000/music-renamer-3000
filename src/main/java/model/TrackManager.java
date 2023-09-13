package model;

import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import controller.Controller;

import java.io.IOException;
import java.util.ArrayList;

public class TrackManager {

    private Controller controller;
    private ArrayList<Track> trackList;

    public TrackManager(Controller controller) {
        trackList = new ArrayList<>();
    }

    public void importTrack(String filepath) {
        try {
            Mp3File track = new Mp3File(filepath);
            ID3v2 trackTag = track.getId3v2Tag();

            String filename = track.getFilename();
            String title = trackTag.getTitle();
            String artist = trackTag.getArtist();
            String album = trackTag.getAlbum();

            Track newTrack = new Track(filename, title, artist, album);

            trackList.add(newTrack);
            System.out.println(newTrack);

        } catch (IOException | UnsupportedTagException | InvalidDataException e) {
            System.out.println("nej");
        }
    }

    public ArrayList<Track> getTrackList() {
        return trackList;
    }
}

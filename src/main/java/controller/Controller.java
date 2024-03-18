package controller;

import model.Track;
import model.TrackManager;
import view.MainWindow;

import java.io.File;
import java.util.ArrayList;

public class Controller {
    private MainWindow view;
    private TrackLoader trackLoader;
    private TrackManager trackManager;
    public Controller(){
        view = new MainWindow(this);
        trackLoader = new TrackLoader(this);
        trackManager = new TrackManager(this);
    }

    public void loadTrack() {
        File[] files = trackLoader.loadTracks();
        addTrackToTable(trackManager.importTrack(files));
    }

    public void addTrackToTable(ArrayList<Track> trackList) {
        for (Track track : trackList) {
            view.addTrackToTable(track.getFilename(), track.getTitle(), track.getArtist(), track.getAlbum());
        }
    }
}

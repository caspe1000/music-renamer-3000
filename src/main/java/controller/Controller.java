package controller;

import model.Track;
import model.TrackManager;
import view.MainWindow;

import java.io.File;

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
        File file = trackLoader.loadTrack();
        trackManager.importTrack(file);
    }

    public void addTrackToTable(Track track) {
        view.addTrackToTable(track.getFilename(), track.getTitle(), track.getArtist(), track.getAlbum());
    }
}

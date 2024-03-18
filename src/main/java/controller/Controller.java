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

    /**
     * Method that runs when the Open-button is pressed in the GUI.
     */
    public void selectTracks() {
        File[] files = trackLoader.selectFiles("music", "mp3");
        addToTrackList(trackLoader.convertFilesToTracks(files));
        displayTrackList(trackManager.getTrackList());
    }


    private void addToTrackList(ArrayList<Track> tracks) {
        for (Track t : tracks) {
            trackManager.addToTrackList(t);
        }
    }


    private void displayTrackList(ArrayList<Track> trackList) {
        for (Track track : trackList) {
            view.addTrackToTable(track.getFilename(), track.getTitle(), track.getArtist(), track.getAlbum());
        }
    }


    public void noTrackSelectedPopup(String s) {
        view.noTrackSelectedPopup(s);
    }
}

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
     * Selects files, converts them to mp3-tracks and displays them.
     */
    public void selectTracks() {
        File[] files = trackLoader.selectFiles("music", "mp3");
        addToTrackList(trackLoader.convertFilesToTracks(files));
        displayTrackList(trackManager.getTrackList());
    }

    /**
     * Adds tracks from an arraylist to the track list.
     * @param tracks The tracks to add.
     */
    private void addToTrackList(ArrayList<Track> tracks) {
        for (Track t : tracks) {
            trackManager.addToTrackList(t);
        }
    }

    /**
     * Takes the tracks from the track list and displays them on the GUI.
     * @param trackList
     */
    private void displayTrackList(ArrayList<Track> trackList) {
        for (Track track : trackList) {
            view.addTrackToTable(track.getFilename(), track.getTitle(), track.getArtist(), track.getAlbum());
        }
    }

    /**
     * A pop up with a message.
     * @param s The message.
     */
    public void createPopup(String s) {
        view.createPopup(s);
    }
}

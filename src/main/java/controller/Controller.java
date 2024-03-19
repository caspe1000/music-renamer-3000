package controller;

import model.Track;
import model.TrackManager;
import view.MainWindow;

import java.io.File;
import java.util.ArrayList;

public class Controller {
    private MainWindow view;
    private TrackLoader trackLoader;
    private TrackSaver trackSaver;
    private TrackManager trackManager;
    private Track selectedTrack;

    public Controller(){
        view = new MainWindow(this);
        trackLoader = new TrackLoader(this);
        trackSaver = new TrackSaver(this);
        trackManager = new TrackManager(this);
    }

    /**
     * Method that runs when the Open-button is pressed in the GUI.
     * Selects files, converts them to mp3-files, then to Track-objects and then displays them.
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

    /**
     * Sets the selected track to the one that is highlighted in the GUI.
     * @param filename Filename for the track.
     * @param title The track's title.
     * @param artist The track's artist.
     * @param album The track's album.
     */
    public void setSelectedTrack(String filename, String title, String artist, String album) {
        selectedTrack = findTrack(filename, title, artist, album);
        view.fillTextfields(filename, title, artist, album);
    }

    /**
     * Finds a track on the computer.
     * @param filename Filename for the track.
     * @param title The track's title.
     * @param artist The track's artist.
     * @param album The track's album.
     * @return The track.
     */
    private Track findTrack(String filename, String title, String artist, String album) {
        for (Track t : trackManager.getTrackList()) {
            if (t.getFilename().equals(filename) && t.getTitle().equals(title) && t.getArtist().equals(artist) && t.getAlbum().equals(album)) {
                return t;
            }
        }
        return new Track(filename, title, artist, album, "");
    }

    /**
     * Saves the new track info to the track.
     * @param newFilename New filename for the track.
     * @param newTitle The track's new title.
     * @param newArtist The track's new artist.
     * @param newAlbum The track's new album.
     * @return The new filepath.
     */
    public String saveNewInfo(String newFilename, String newTitle, String newArtist, String newAlbum) {
        return trackSaver.saveNewTrackInfo(selectedTrack, newFilename, newTitle, newArtist, newAlbum);
    }

}

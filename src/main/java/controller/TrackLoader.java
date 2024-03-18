package controller;

import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import model.Track;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class TrackLoader {
    private Controller controller;
    public TrackLoader(Controller controller)  {
        this.controller = controller;
    }

    /**
     * Opens a JFileChooser and lets the user select mp3-files. Then adds them to a File array.
     * @param description A description of what kind of files the user can import.
     * @param extension The file extension for the files.
     * @return An array of files.
     */
    public File[] selectFiles(String description, String extension){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setMultiSelectionEnabled(true);

        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter(description, extension));
        fileChooser.setAcceptAllFileFilterUsed(true);
        int dlg = fileChooser.showOpenDialog(null);

        if (dlg == JFileChooser.APPROVE_OPTION) {
            File[] files = fileChooser.getSelectedFiles();
            return files;
        }

        return null;
    }

    /**
     * Converts file objects into mp3 files, gets their metadata, creates a Track object and finally adds them to the track list.
     * @param files A file array.
     * @return An arraylist of tracks.
     */
    public ArrayList<Track> convertFilesToTracks(File[] files) {
        ArrayList<Track> trackList = new ArrayList<>();

        try {
            for (File file : files) {
                Mp3File track = new Mp3File(file);
                ID3v2 trackTag = track.getId3v2Tag();

                String filename = file.getName();
                String title = trackTag.getTitle();
                String artist = trackTag.getArtist();
                String album = trackTag.getAlbum();

                trackList.add(new Track(filename, title, artist, album));
            }
        } catch (NullPointerException npe) {
            controller.createPopup("No track selected!");
        } catch (InvalidDataException | UnsupportedTagException | IOException e) {
            throw new RuntimeException(e);
        }

        return trackList;
    }
}

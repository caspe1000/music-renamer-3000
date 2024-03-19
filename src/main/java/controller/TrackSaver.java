package controller;

import com.mpatric.mp3agic.*;
import model.Track;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TrackSaver {
    private Controller controller;
    public TrackSaver(Controller controller) {
        this.controller = controller;
    }


    /**
     * Takes the selected track from the GUI and changes its metadata.
     * Then calls methods to save it to file.
     * @param selectedTrack The selected track.
     * @param newFilename The new filename.
     * @param newTitle The new title.
     * @param newArtist The new artist.
     * @param newAlbum The new album.
     * @return The new filepath as a String.
     */
    public String saveNewTrackInfo(Track selectedTrack, String newFilename, String newTitle, String newArtist, String newAlbum) {

        try {
            Mp3File track = new Mp3File(selectedTrack.getFilepath() + selectedTrack.getFilename());

            ID3v2 trackTag;
            if (track.hasId3v2Tag()) {
                trackTag = track.getId3v2Tag();
            } else {
                trackTag = new ID3v24Tag();
                track.setId3v2Tag(trackTag);
            }

            trackTag.setTitle(newTitle);
            trackTag.setArtist(newArtist);
            trackTag.setAlbum(newAlbum);

            saveToFile(track, selectedTrack.getFilepath(), newFilename);
            deleteOldTrack(selectedTrack);
            renameFile(selectedTrack.getFilepath(), newFilename);

            return selectedTrack.getFilepath() + newFilename;
        } catch (InvalidDataException | UnsupportedTagException | IOException e) {
            e.printStackTrace();
            return "Could not save track to file.";
        }
    }


    /**
     * Saves the track to file. Adds a "1" after the filename to surpass a file saving error.
     * @param track The track.
     * @param path The track's path.
     * @param filename The track's filename.
     * @return The new filepath.
     */
    public String saveToFile(Mp3File track, String path, String filename) {
        try {
            track.save(path + filename + "1");
            return path + filename;
        } catch (IOException | NotSupportedException e) {
            e.printStackTrace();
            return "Could not save track to file.";
        }
    }

    /**
     * Deletes a track.
     * @param selectedTrack The track to delete.
     * @return A boolean confirming the file has been deleted.
     */
    public boolean deleteOldTrack(Track selectedTrack) {
        File file = new File(selectedTrack.getFilepath() + selectedTrack.getFilename());
        return file.delete();
    }

    /**
     * Removes the "1" from the filename.
     * @param path Path to the file.
     * @param filename Filename to the file.
     * @return The new filepath.
     */
    public String renameFile(String path, String filename) {
        Path source = Paths.get(path + filename + "1");
        Path newSource = Paths.get(path + filename);
        try {
            Files.move(source, source.resolveSibling(newSource));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return path + filename;
    }
}

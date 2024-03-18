package controller;

import com.mpatric.mp3agic.*;
import model.Track;

import java.io.File;
import java.io.IOException;

public class TrackSaver {

    private Controller controller;

    public TrackSaver(Controller controller) {
        this.controller = controller;
    }

    public void saveNewTrackInfo(Track selectedTrack, String newFilename, String newTitle, String newArtist, String newAlbum) {

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

            track.save(selectedTrack.getFilepath() + newFilename);
            deleteOldTrack(selectedTrack);
        } catch (InvalidDataException | UnsupportedTagException | IOException | NotSupportedException e) {
            throw new RuntimeException(e);
        }

    }

    private void deleteOldTrack(Track selectedTrack) {
        File file = new File(selectedTrack.getFilepath() + selectedTrack.getFilename());
        file.delete();
    }
}

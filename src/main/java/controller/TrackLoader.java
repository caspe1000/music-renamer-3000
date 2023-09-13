package controller;

import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.IOException;

public class TrackLoader {

    private Mp3File track;
    private String filepath;
    private Controller controller;


    public TrackLoader(Controller controller)  {
        this.controller = controller;
    }


    public void loadTrack(){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("music", "mp3"));
        fileChooser.setAcceptAllFileFilterUsed(true);
        int dlg = fileChooser.showOpenDialog(null);

        if (dlg == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            filepath = file.getPath();
            System.out.println(filepath);

            try{
                track = new Mp3File(file);
                ID3v2 id3v2tag = track.getId3v2Tag();
            } catch (IOException | UnsupportedTagException | InvalidDataException e) {
                System.out.println("nej");
            }
        }

    }
}

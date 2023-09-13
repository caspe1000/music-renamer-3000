package controller;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.IOException;

public class TrackLoader {

    private String filePath;
    private Controller controller;


    public TrackLoader(Controller controller)  {
        this.controller = controller;
    }


    public File loadTrack(){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("music", "mp3"));
        fileChooser.setAcceptAllFileFilterUsed(true);
        int dlg = fileChooser.showOpenDialog(null);

        if (dlg == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            return file;
        }
        return null;
    }
}

package controller;

import model.TrackManager;
import view.MainWindow;

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
        String filePath = trackLoader.loadTrack();
        trackManager.importTrack(filePath);
    }
}

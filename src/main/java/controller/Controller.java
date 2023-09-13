package controller;

import view.MainWindow;

public class Controller {

    private MainWindow view;
    private TrackLoader trackLoader;
    public Controller(){
        view = new MainWindow(this);
        trackLoader = new TrackLoader(this);
    }
}

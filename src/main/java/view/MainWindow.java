package view;

import javax.swing.*;

public class MainWindow extends JFrame {


    private JPanel mainPanel;
    private JPanel pnlBackground;
    private JPanel pnlTop;
    private JPanel pnlCenter;
    private JPanel pnlBot;
    private JScrollPane jspTracklist;
    private JTable tblTracklist;

    public MainWindow() {
        setupFrame();
    }

    private void setupFrame() {
        setTitle("MUSIC RENAMER 3000");
        setSize(500, 700);
        setLocation(900, 100);
        setVisible(true);
        setContentPane(mainPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        this.revalidate();
    }
}

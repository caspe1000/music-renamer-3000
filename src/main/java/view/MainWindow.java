package view;

import controller.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame {


    private JPanel mainPanel;
    private JPanel pnlBackground;
    private JPanel pnlTop;
    private JPanel pnlCenter;
    private JPanel pnlBot;
    private JScrollPane jspTracklist;
    private JTable tblTracklist;
    private JButton btnOpen;
    private Controller controller;

    public MainWindow(Controller controller) {
        setupFrame();
        this.controller = controller;
    }

    private void setupFrame() {
        setTitle("MUSIC RENAMER 3000");
        setSize(600, 700);
        setLocation(900, 100);
        setVisible(true);
        setContentPane(mainPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        initButtons();
        this.revalidate();
    }

    private void initButtons() {
        btnOpen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.loadTrack();
            }
        });
    }
}

package view;

import controller.Controller;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

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
    private List<String[]> values;
    private List<String> columns;

    public MainWindow(Controller controller) {
        setupFrame();
        this.controller = controller;
    }

    private void setupFrame() {
        setTitle("MUSIC RENAMER 3000");
        setSize(800, 700);
        setLocation(900, 100);
        setVisible(true);
        setContentPane(mainPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        initButtons();
        initTracklistTable();
        this.revalidate();
    }

    private void initButtons() {
        btnOpen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openButtonPressed();
            }
        });
    }

    private void initTracklistTable() {
        columns = new ArrayList<>();
        values = new ArrayList<>();

        columns.add("Filename");
        columns.add("Title");
        columns.add("Artist");
        columns.add("Album");

        TableModel tableModel = new DefaultTableModel(values.toArray(new Object[][] {}), columns.toArray());
        tblTracklist.setModel(tableModel);
    }

    public String addTrackToTable(String filename, String title, String artist, String album){
        values.add(new String[] {filename, title, artist, album});
        refreshList();
        return String.format("%s %s %s %s", filename, title, artist, album);
    }

    private TableModel refreshList() {
        TableModel tableModel = new DefaultTableModel(values.toArray(new Object[][] {}), columns.toArray());
        tblTracklist.setModel(tableModel);
        return tableModel;
    }

    public String noTrackSelectedPopup(String message) {
        JOptionPane.showMessageDialog(null, message);
        return message;
    }

    public boolean openButtonPressed() {
        controller.selectTracks();
        return true;
    }

}

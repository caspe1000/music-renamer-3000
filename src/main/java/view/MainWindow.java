package view;

import controller.Controller;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
    private JTextField txfTitle;
    private JTextField txfAlbum;
    private JTextField txfArtist;
    private JTextField txfFileName;
    private JButton btnClear;
    private JButton btnSave;
    private JLabel lblFilename;
    private JLabel lblTitle;
    private JLabel lblArtist;
    private JLabel lblAlbum;
    private JPanel pnlSave;
    private Controller controller;
    private List<String[]> values;
    private List<String> columns;

    public MainWindow(Controller controller) {
        setupFrame();
        this.controller = controller;

    }

    /**
     * Adds a track to the table.
     * @param filename File name of the track.
     * @param title Title of the track.
     * @param artist Artist of the track.
     * @param album Album of the track.
     * @return A string with all the track info.
     */
    public String addTrackToTable(String filename, String title, String artist, String album){
        values.add(new String[] {filename, title, artist, album});
        refreshTable();
        return String.format("%s %s %s %s", filename, title, artist, album);
    }

    /**
     * Refreshes the track list table.
     * @return The table model of the table.
     */
    private TableModel refreshTable() {
        TableModel tableModel = new DefaultTableModel(values.toArray(new Object[][] {}), columns.toArray());
        tblTracklist.setModel(tableModel);
        return tableModel;
    }

    /**
     * Displays a pop up with a text.
     * @param message The message to be displayed.
     * @return The message.
     */
    public String createPopup(String message) {
        JOptionPane.showMessageDialog(null, message);
        return message;
    }

    /**
     * Method that runs when the Open button is pressed.
     * @return A boolean.
     */
    public boolean openButtonPressed() {
        controller.selectTracks();
        return true;
    }

    /**
     * Gets the data from the selected row in the table and displays it in the text fields.
     */
    private void getSelectedRowInfo() {
        int row = tblTracklist.getSelectedRow();

        String filename = tblTracklist.getModel().getValueAt(row, 0).toString();
        String title = tblTracklist.getModel().getValueAt(row, 1).toString();
        String artist = tblTracklist.getModel().getValueAt(row, 2).toString();
        String album = tblTracklist.getModel().getValueAt(row, 3).toString();

        controller.setSelectedTrack(filename, title, artist, album);

        fillTextfields(filename, title, artist, album);
    }

    /**
     * Adds data to the text fields.
     * @param filename Filename of the track.
     * @param title Title of the track.
     * @param artist Artist of the track.
     * @param album Album of the track.
     */
    public void fillTextfields(String filename, String title, String artist, String album) {
        txfFileName.setText(filename);
        txfTitle.setText(title);
        txfArtist.setText(artist);
        txfAlbum.setText(album);
    }

    /**
     * Method that runs when the Save button is pressed.
     */
    private void saveButtonPressed(String newFilename, String newTitle, String newArtist, String newAlbum) {
        controller.saveNewInfo(newFilename, newTitle, newArtist, newAlbum);
    }

    /**
     * Set up the JFrame.
     */
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

    /**
     * Initiates the buttons on the GUI.
     */
    private void initButtons() {
        btnOpen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openButtonPressed();
            }
        });

        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveButtonPressed(txfFileName.getText(), txfTitle.getText(), txfArtist.getText(), txfAlbum.getText());
            }
        });

        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    /**
     * Initiates the track list table on the GUI.
     */
    private void initTracklistTable() {
        columns = new ArrayList<>();
        values = new ArrayList<>();

        columns.add("Filename");
        columns.add("Title");
        columns.add("Artist");
        columns.add("Album");

        TableModel tableModel = new DefaultTableModel(values.toArray(new Object[][] {}), columns.toArray());
        tblTracklist.setModel(tableModel);
        addMouseListenerToTable();
    }

    /**
     * MouseListener for the track table.
     */
    private void addMouseListenerToTable() {
        tblTracklist.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                getSelectedRowInfo();
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

}

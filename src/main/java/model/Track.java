package model;

public class Track {

    private String filename;
    private String title;
    private String artist;
    private String album;

    public Track(String filename, String title, String artist, String album) {
        this.filename = filename;
        this.title = title;
        this.artist = artist;
        this.album = album;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String toString() {
        return filename + " " + title + " - " + artist + " - " + album;
    }
}

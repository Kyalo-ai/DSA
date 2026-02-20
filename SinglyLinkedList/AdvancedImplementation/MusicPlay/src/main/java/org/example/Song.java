package org.example;


import java.io.File;
public class Song {
    private String title;
    private String artist;
    private String filePath;
    private Song next;
    private long fileSize;

    public Song(String filePath) {
        this.filePath = filePath;
        extractMetadataFromFilename();
        getFileInfo();
        this.next = null;
    }

    private void extractMetadataFromFilename() {
        File file = new File(filePath);
        String filename = file.getName();


        filename = filename.substring(0, filename.lastIndexOf('.'));


        if (filename.contains(" - ")) {
            String[] parts = filename.split(" - ", 2);
            this.artist = parts[0].trim();
            this.title = parts[1].trim();
        } else {

            this.title = filename;
            this.artist = "Unknown Artist";
        }
    }

    private void getFileInfo() {
        File file = new File(filePath);
        if (file.exists()) {
            this.fileSize = file.length();
        }
    }


    public String getTitle() { return title; }
    public String getArtist() { return artist; }
    public String getFilePath() { return filePath; }
    public Song getNext() { return next; }
    public long getFileSize() { return fileSize; }

    public void setNext(Song next) { this.next = next; }


    public boolean fileExists() {
        return new File(filePath).exists();
    }


    public String getReadableFileSize() {
        if (fileSize < 1024) return fileSize + " B";
        int exp = (int) (Math.log(fileSize) / Math.log(1024));
        String pre = "KMGTPE".charAt(exp-1) + "";
        return String.format("%.1f %sB", fileSize / Math.pow(1024, exp), pre);
    }

    @Override
    public String toString() {
        return title + " - " + artist;
    }
}

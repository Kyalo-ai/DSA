package org.example;

import java.util.ArrayList;
import java.util.List;

public class Playlist {
    private Song head;
    private Song tail;
    private Song currentSong;
    private int size;

    public Playlist() {
        this.head = null;
        this.tail = null;
        this.currentSong = null;
        this.size = 0;
    }


    public void addSong(Song song) {
        if (head == null) {
            head = song;
            tail = song;
            currentSong = song;
        } else {
            tail.setNext(song);
            tail = song;
        }
        size++;
    }


    public void addAll(List<Song> songs) {
        for (Song song : songs) {
            addSong(song);
        }
    }


    public Song getCurrentSong() {
        return currentSong;
    }


    public Song getNextSong() {
        if (currentSong != null && currentSong.getNext() != null) {
            currentSong = currentSong.getNext();
            return currentSong;
        }
        return null;
    }


    public Song getPreviousSong() {
        if (currentSong != null && currentSong != head) {
            Song temp = head;
            while (temp != null && temp.getNext() != currentSong) {
                temp = temp.getNext();
            }
            if (temp != null) {
                currentSong = temp;
                return currentSong;
            }
        }
        return null;
    }

    public List<Song> getAllSongs() {
        List<Song> songs = new ArrayList<>();
        Song current = head;
        while (current != null) {
            songs.add(current);
            current = current.getNext();
        }
        return songs;
    }


    public Song getSongAt(int index) {
        if (index < 0 || index >= size) return null;

        Song current = head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return current;
    }


    public int getSize() {
        return size;
    }


    public void resetToFirst() {
        currentSong = head;
    }


    public boolean isEmpty() {
        return size == 0;
    }


    public void clear() {
        head = null;
        tail = null;
        currentSong = null;
        size = 0;
    }
}
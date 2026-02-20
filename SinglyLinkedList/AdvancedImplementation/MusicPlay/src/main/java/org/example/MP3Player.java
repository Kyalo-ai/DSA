package org.example;
// MP3Player.java
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MP3Player {
    private AdvancedPlayer player;
    private Thread playerThread;
    private String currentFilePath;
    private boolean isPlaying;
    private boolean isPaused;
    private int pausedFrame;
    private PlaybackListener playbackListener;

    public MP3Player() {
        this.isPlaying = false;
        this.isPaused = false;
        this.pausedFrame = 0;
    }

    public void play(String filePath) {
        if (isPlaying && !isPaused && currentFilePath != null && currentFilePath.equals(filePath)) {
            System.out.println("⚠ Song is already playing");
            return;
        }

        stop();

        try {
            this.currentFilePath = filePath;
            FileInputStream fis = new FileInputStream(filePath);
            BufferedInputStream bis = new BufferedInputStream(fis);

            player = new AdvancedPlayer(bis);

            // Add playback listener
            player.setPlayBackListener(new PlaybackListener() {
                @Override
                public void playbackStarted(PlaybackEvent evt) {
                    isPlaying = true;
                    isPaused = false;
                }

                @Override
                public void playbackFinished(PlaybackEvent evt) {
                    isPlaying = false;
                    isPaused = false;
                    pausedFrame = 0;
                }
            });

            // Play in separate thread
            playerThread = new Thread(() -> {
                try {
                    if (pausedFrame > 0) {
                        player.play(pausedFrame, Integer.MAX_VALUE);
                    } else {
                        player.play();
                    }
                } catch (JavaLayerException e) {
                    System.err.println("Error playing MP3: " + e.getMessage());
                }
            });

            playerThread.start();

        } catch (FileNotFoundException | JavaLayerException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public void pause() {
        if (isPlaying && !isPaused && player != null) {
            stop();
            isPaused = true;
        }
    }

    public void resume() {
        if (isPaused && currentFilePath != null) {
            play(currentFilePath);
        }
    }

    public void stop() {
        if (player != null) {
            player.close();
            player = null;
        }
        if (playerThread != null && playerThread.isAlive()) {
            playerThread.interrupt();
        }
        isPlaying = false;
        isPaused = false;
        pausedFrame = 0;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public boolean isPaused() {
        return isPaused;
    }
}
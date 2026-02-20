package org.example;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MusicPlayerApp {
    private Playlist playlist;
    private MP3Player mp3Player;
    private Scanner scanner;
    private String musicFolderPath;

    public MusicPlayerApp() {
        this.playlist = new Playlist();
        this.mp3Player = new MP3Player();
        this.scanner = new Scanner(System.in);


        String projectPath = System.getProperty("user.dir");
        this.musicFolderPath ="C:/Users/HP/Desktop/DSA/SinglyLinkedList/AdvancedImplementation/MusicPlay/music";

        checkAndLoadExistingMusic();
    }


    private void checkAndLoadExistingMusic() {
        File musicFolder = new File(musicFolderPath);

        if (musicFolder.exists()) {
            File[] mp3Files = musicFolder.listFiles((dir, name) ->
                    name.toLowerCase().endsWith(".mp3"));

            if (mp3Files != null && mp3Files.length > 0) {
                System.out.println("\n🔍 Found existing music folder with " + mp3Files.length + " songs!");
                System.out.print("❓ Would you like to load them now? (y/n): ");
                String response = scanner.nextLine().toLowerCase();

                if (response.equals("y") || response.equals("yes")) {
                    loadMusicFromFolder(musicFolderPath);
                } else {
                    System.out.println("📁 Music folder ready at: " + musicFolderPath);
                    System.out.println("   Use option 1 to load songs when ready.\n");
                }
            } else {
                System.out.println("\n📁 Music folder found but it's empty.");
                System.out.println("   Add MP3 files to: " + musicFolderPath);
                System.out.println("   Then use option 1 to load them.\n");
            }
        }
    }


    public void loadMusicFromFolder(String folderPath) {
        File folder = new File(folderPath);

        if (!folder.exists()) {
            System.out.println("❌ Folder not found: " + folderPath);
            createMusicFolderPrompt();
            return;
        }

        if (!folder.isDirectory()) {
            System.out.println("❌ Not a directory: " + folderPath);
            return;
        }

        File[] mp3Files = folder.listFiles((dir, name) ->
                name.toLowerCase().endsWith(".mp3"));

        if (mp3Files == null || mp3Files.length == 0) {
            System.out.println("❌ No MP3 files found in: " + folderPath);
            System.out.println("📝 Please add some MP3 files to this folder and try again.");
            return;
        }


        if (playlist.getSize() > 0) {
            System.out.print("❓ Playlist already has " + playlist.getSize() + " songs. Replace? (y/n): ");
            String response = scanner.nextLine().toLowerCase();
            if (response.equals("y") || response.equals("yes")) {
                playlist = new Playlist();
            } else {
                System.out.println("✅ Keeping existing playlist.");
                return;
            }
        }

        List<Song> songs = new ArrayList<>();
        System.out.println("\n📀 Loading songs from: " + folderPath);
        System.out.println("──────────────────────────────────");

        int validCount = 0;
        for (File file : mp3Files) {
            if (file.canRead()) {
                Song song = new Song(file.getAbsolutePath());
                songs.add(song);
                System.out.println("  ✓ " + song);
                validCount++;
            } else {
                System.out.println("  ⚠ Cannot read: " + file.getName());
            }
        }

        playlist.addAll(songs);
        System.out.println("──────────────────────────────────");
        System.out.println("✅ Successfully loaded " + validCount + " songs!\n");


        displayPlaylistSummary();
    }


    private void displayPlaylistSummary() {
        List<Song> songs = playlist.getAllSongs();
        System.out.println("📋 Playlist Summary:");
        System.out.println("   Total songs: " + songs.size());
        if (!songs.isEmpty()) {
            System.out.println("   First song: " + songs.get(0));
            System.out.println("   Last song: " + songs.get(songs.size() - 1));
        }
    }


    public void loadFromProjectMusicFolder() {
        File musicFolder = new File(musicFolderPath);

        if (!musicFolder.exists()) {
            createMusicFolderPrompt();
            return;
        }

        loadMusicFromFolder(musicFolderPath);
    }

    private void createMusicFolderPrompt() {
        System.out.println("\n📁 Music folder not found at: " + musicFolderPath);
        System.out.print("❓ Would you like to create it now? (y/n): ");
        String response = scanner.nextLine().toLowerCase();

        if (response.equals("y") || response.equals("yes")) {
            boolean created = new File(musicFolderPath).mkdir();
            if (created) {
                System.out.println("✅ Created music folder at: " + musicFolderPath);
                System.out.println("📝 Please add your MP3 files to this folder and select option 1 again.\n");
            } else {
                System.out.println("❌ Failed to create folder. Please create it manually.\n");
            }
        }
    }


    public void displayPlaylist() {
        List<Song> songs = playlist.getAllSongs();
        if (songs.isEmpty()) {
            System.out.println("\n📋 Playlist is empty! Load some songs first.\n");
            return;
        }

        System.out.println("\n📋 ===== YOUR PLAYLIST =====");
        System.out.println("Total songs: " + songs.size());
        System.out.println("────────────────────────────");

        Song current = playlist.getCurrentSong();
        for (int i = 0; i < songs.size(); i++) {
            Song song = songs.get(i);
            String playingMark = "";
            if (song == current && mp3Player.isPlaying()) {
                playingMark = " ▶ NOW PLAYING";
            } else if (song == current && mp3Player.isPaused()) {
                playingMark = " ⏸ PAUSED";
            } else if (song == current) {
                playingMark = " ⏹ STOPPED";
            }
            System.out.println((i + 1) + ". " + song + playingMark);
        }
        System.out.println("────────────────────────────\n");
    }


    public void playCurrent() {
        Song current = playlist.getCurrentSong();
        if (current == null) {
            System.out.println("\n❌ No song selected! Load some songs first.\n");
            return;
        }

        System.out.println("\n▶ Now Playing: " + current);
        System.out.println("   File: " + new File(current.getFilePath()).getName() + "\n");
        mp3Player.play(current.getFilePath());
    }


    public void playNext() {
        if (playlist.getSize() == 0) {
            System.out.println("\n❌ Playlist is empty!\n");
            return;
        }

        Song next = playlist.getNextSong();
        if (next == null) {
            System.out.println("\n⏹ End of playlist reached! Starting from beginning.\n");
            playlist.resetToFirst();
            next = playlist.getCurrentSong();
        }
        playCurrent();
    }


    public void playPrevious() {
        if (playlist.getSize() == 0) {
            System.out.println("\n❌ Playlist is empty!\n");
            return;
        }

        Song prev = playlist.getPreviousSong();
        if (prev == null) {
            System.out.println("\n⏹ Already at the first song!\n");
            return;
        }
        playCurrent();
    }

    public void pauseCurrent() {
        if (mp3Player.isPlaying()) {
            mp3Player.pause();
            System.out.println("\n⏸ Paused: " + playlist.getCurrentSong() + "\n");
        } else {
            System.out.println("\n❌ No song is playing!\n");
        }
    }


    public void resumeCurrent() {
        if (mp3Player.isPaused()) {
            mp3Player.resume();
            System.out.println("\n▶ Resumed: " + playlist.getCurrentSong() + "\n");
        } else {
            System.out.println("\n❌ No paused song found!\n");
        }
    }


    public void stopCurrent() {
        if (mp3Player.isPlaying() || mp3Player.isPaused()) {
            mp3Player.stop();
            System.out.println("\n⏹ Stopped: " + playlist.getCurrentSong() + "\n");
        } else {
            System.out.println("\n❌ No song is playing!\n");
        }
    }


    public void showFolderInfo() {
        File musicFolder = new File(musicFolderPath);

        System.out.println("\n📁 ===== PROJECT FOLDER INFO =====");
        System.out.println("📍 Project path: " + System.getProperty("user.dir"));

        if (musicFolder.exists()) {
            System.out.println("📍 Music folder: " + musicFolderPath);

            File[] mp3Files = musicFolder.listFiles((dir, name) ->
                    name.toLowerCase().endsWith(".mp3"));

            if (mp3Files != null && mp3Files.length > 0) {
                System.out.println("📀 MP3 files found: " + mp3Files.length);
                System.out.println("\n📋 Files in music folder:");
                for (int i = 0; i < Math.min(10, mp3Files.length); i++) {
                    String status = isFileInPlaylist(mp3Files[i]) ? " ✓" : "";
                    System.out.println("   " + (i+1) + ". " + mp3Files[i].getName() + status);
                }
                if (mp3Files.length > 10) {
                    System.out.println("   ... and " + (mp3Files.length - 10) + " more");
                }
            } else {
                System.out.println("📂 No MP3 files found in music folder");
            }
        } else {
            System.out.println("📂 Music folder not created yet");
        }

        System.out.println("\n📊 Playlist status: " + playlist.getSize() + " songs loaded");
        if (playlist.getCurrentSong() != null) {
            System.out.println("🎵 Current song: " + playlist.getCurrentSong());
        }
        System.out.println("===================================\n");
    }

    private boolean isFileInPlaylist(File file) {
        List<Song> songs = playlist.getAllSongs();
        String filePath = file.getAbsolutePath();
        for (Song song : songs) {
            if (song.getFilePath().equals(filePath)) {
                return true;
            }
        }
        return false;
    }


    public void quickLoad() {
        File musicFolder = new File(musicFolderPath);
        if (musicFolder.exists()) {
            File[] mp3Files = musicFolder.listFiles((dir, name) ->
                    name.toLowerCase().endsWith(".mp3"));

            if (mp3Files != null && mp3Files.length > 0) {
                System.out.println("\n⚡ Quick loading " + mp3Files.length + " songs...");
                loadMusicFromFolder(musicFolderPath);
            } else {
                System.out.println("\n❌ No MP3 files found in music folder.\n");
            }
        } else {
            createMusicFolderPrompt();
        }
    }


    public void start() {
        boolean running = true;

        while (running) {
            displayMenu();
            System.out.print("👉 Enter your choice: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        loadFromProjectMusicFolder();
                        break;
                    case 2:
                        quickLoad();
                    case 3:
                        displayPlaylist();
                        break;
                    case 4:
                        playCurrent();
                        break;
                    case 5:
                        playNext();
                        break;
                    case 6:
                        playPrevious();
                        break;
                    case 7:
                        pauseCurrent();
                        break;
                    case 8:
                        resumeCurrent();
                        break;
                    case 9:
                        stopCurrent();
                        break;
                    case 10:
                        showFolderInfo();
                        break;
                    case 11:
                        System.out.println("\n👋 Thanks for using MP3 Player! Goodbye!\n");
                        mp3Player.stop();
                        running = false;
                        break;
                    default:
                        System.out.println("\n❌ Invalid choice. Please try again.\n");
                }

                if (running && choice != 11) {
                    System.out.println("\nPress Enter to continue...");
                    scanner.nextLine();
                }

            } catch (NumberFormatException e) {
                System.out.println("\n❌ Please enter a valid number.\n");
                System.out.println("Press Enter to continue...");
                scanner.nextLine();
            }
        }
    }

    private void displayMenu() {
        System.out.println("🎮 ========== MUSIC PLAYER MENU ==========");
        System.out.println("||  1. 📁 Load/Reload Music from Folder  ||");
        System.out.println("||  2. ⚡  Quick Load (skip prompts)     ||");
        System.out.println("||  3. 📋 Show Playlist                  ||");
        System.out.println("||  4. ▶  Play Current Song             ||");
        System.out.println("||  5. ⏭  Play Next Song                ||");
        System.out.println("||  6. ⏮  Play Previous Song            ||");
        System.out.println("||  7. ⏸  Pause                          ||");
        System.out.println("||  8. ⏵  Resume                         ||");
        System.out.println("||  9. ⏹  Stop                           ||");
        System.out.println("|| 10. 📁 Show Folder Info               ||");
        System.out.println("|| 11. 🚪 Exit                            ||");
        System.out.println("===========================================");
    }
}
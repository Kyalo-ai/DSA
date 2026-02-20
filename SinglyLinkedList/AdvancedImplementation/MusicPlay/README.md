##  🎵 Console MP3 Music Player
Singly Linked List Implementation in Java
##  📌 Project Overview

--- This project is a console-based MP3 music player developed in Java.
---  It demonstrates the practical implementation of a Singly Linked List to manage songs dynamically.
---- The system allows users to:

1.Load MP3 files from a folder

2.Automatically extract song metadata

3.Manage a playlist

4.Play, Pause, Resume, Stop songs

5.Navigate Next and Previous

6.Display playlist and folder information

## 🏗 System Architecture

The system follows a layered design:

User (Console Menu)
        ↓
MusicPlayerApp (Controller)
        ↓
Playlist (Singly Linked List)
        ↓
Song (Node)
        ↕
MP3Player (Playback Engine with Threading)
## 📚 1️⃣ Pseudocode – Song Class
START

CLASS Song

    DECLARE title : String
    DECLARE artist : String
    DECLARE filePath : String
    DECLARE next : Song
    DECLARE fileSize : Long

    CONSTRUCTOR Song(inputFilePath)

        filePath ← inputFilePath
        CALL extractMetadataFromFilename()
        CALL getFileInfo()
        next ← NULL

    END CONSTRUCTOR


    METHOD extractMetadataFromFilename()

        GET filename from filePath
        REMOVE file extension

        IF filename contains " - " THEN
            SPLIT filename into artist and title
        ELSE
            title ← filename
            artist ← "Unknown Artist"
        END IF

    END METHOD


    METHOD getFileInfo()

        IF file exists THEN
            fileSize ← file size in bytes
        END IF

    END METHOD


    METHOD getTitle()
        RETURN title
    END METHOD

    METHOD getArtist()
        RETURN artist
    END METHOD

    METHOD getFilePath()
        RETURN filePath
    END METHOD

    METHOD getNext()
        RETURN next
    END METHOD

    METHOD setNext(song)
        next ← song
    END METHOD

END CLASS

STOP
## 📚 2️⃣ Pseudocode – Playlist Class

(Singly Linked List Implementation)

START

CLASS Playlist

    DECLARE head : Song
    DECLARE tail : Song
    DECLARE currentSong : Song
    DECLARE size : Integer

    CONSTRUCTOR Playlist()
        head ← NULL
        tail ← NULL
        currentSong ← NULL
        size ← 0
    END CONSTRUCTOR


    METHOD addSong(song)

        IF head IS NULL THEN
            head ← song
            tail ← song
            currentSong ← song
        ELSE
            tail.next ← song
            tail ← song
        END IF

        size ← size + 1

    END METHOD


    METHOD getNextSong()

        IF currentSong.next IS NOT NULL THEN
            currentSong ← currentSong.next
            RETURN currentSong
        ELSE
            RETURN NULL
        END IF

    END METHOD


    METHOD getPreviousSong()

        temp ← head

        WHILE temp.next ≠ currentSong
            temp ← temp.next
        END WHILE

        currentSong ← temp
        RETURN currentSong

    END METHOD


    METHOD getAllSongs()

        temp ← head

        WHILE temp IS NOT NULL
            DISPLAY temp
            temp ← temp.next
        END WHILE

    END METHOD


END CLASS

STOP
##  📚 3️⃣ Pseudocode – MP3Player Class

(Playback Engine + Threading + State Machine)

START

CLASS MP3Player

    DECLARE player
    DECLARE playerThread
    DECLARE currentFilePath : String
    DECLARE isPlaying : Boolean
    DECLARE isPaused : Boolean
    DECLARE pausedFrame : Integer


    CONSTRUCTOR MP3Player()

        isPlaying ← FALSE
        isPaused ← FALSE
        pausedFrame ← 0

    END CONSTRUCTOR


    METHOD play(filePath)

        IF already playing same file THEN
            RETURN
        END IF

        CALL stop()

        currentFilePath ← filePath

        CREATE player
        CREATE new Thread

            IF pausedFrame > 0 THEN
                player.play from pausedFrame
            ELSE
                player.play normally
            END IF

        START thread

        isPlaying ← TRUE

    END METHOD


    METHOD pause()

        IF isPlaying THEN
            CALL stop()
            isPaused ← TRUE
        END IF

    END METHOD


    METHOD resume()

        IF isPaused THEN
            CALL play(currentFilePath)
        END IF

    END METHOD


    METHOD stop()

        CLOSE player
        STOP thread
        isPlaying ← FALSE
        isPaused ← FALSE
        pausedFrame ← 0

    END METHOD


END CLASS

STOP
## 📚 4️⃣ Pseudocode – MusicPlayerApp Class

(Main Controller + Menu System)

START

CLASS MusicPlayerApp

    DECLARE playlist : Playlist
    DECLARE mp3Player : MP3Player
    DECLARE musicFolderPath : String


    CONSTRUCTOR MusicPlayerApp()

        CREATE Playlist
        CREATE MP3Player
        SET musicFolderPath
        CALL checkAndLoadExistingMusic()

    END CONSTRUCTOR


    METHOD loadMusicFromFolder(folderPath)

        IF folder NOT EXISTS THEN
            DISPLAY error
            RETURN
        END IF

        GET all .mp3 files

        FOR each file
            CREATE Song
            playlist.addSong(song)
        END FOR

    END METHOD


    METHOD playCurrent()

        current ← playlist.getCurrentSong()

        IF current IS NOT NULL THEN
            mp3Player.play(current.filePath)
        END IF

    END METHOD


    METHOD start()

        running ← TRUE

        WHILE running

            DISPLAY menu
            READ user choice

            SWITCH choice

                1 → Load Music
                2 → Show Playlist
                3 → Play Current
                4 → Next Song
                5 → Previous Song
                6 → Pause
                7 → Resume
                8 → Stop
                9 → Exit (running ← FALSE)

            END SWITCH

        END WHILE

    END METHOD


END CLASS

STOP


## 🧠 Key Concepts Demonstrated

--- Singly Linked List Implementation

----Node-Based Data Storage

----File Handling

----Multithreading

----State Machine Design

----Object-Oriented Programming

----Menu-Driven Console Application



## 🎓 Conclusion



It demonstrates how a Singly Linked List can be used to dynamically manage a playlist while integrating playback functionality through multithreading and state management.



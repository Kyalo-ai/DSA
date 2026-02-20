# 🎵 Java Playlist Application (Linked List Implementation)

## Project Description

This project is a simple Java playlist system implemented using a Singly Linked List.

It allows you to:

* Create songs
* Add songs to a playlist
* Play all songs sequentially
* Simulate playback duration using Thread.sleep()

This project demonstrates object-oriented programming and linked list data structures in Java.

---

## Project Structure

### 1️⃣ Song Class

Represents a song with:

* Title (String)
* artist (String)
* duration (int — in seconds)

Includes:

* Default constructor
* Parameterized constructor

---

### 2️⃣ SongNode Class

Represents a node in the linked list.

Each node contains:

* A Song object
* A reference to the next node (Next)

Used to build the playlist structure.

---

### playlist Class (Static Inner Class)

Implements the playlist using a singly linked list.

#### Attributes

* SongNode head → First song in the playlist

#### Methods

* add(Song song) → Adds a song to the end of the playlist
* playAll() → Plays all songs in order
* playSong(Song song) → Simulates playing a song

---

##  How It Works

### Adding a Song

* A new SongNode is created.
* If the playlist is empty → it becomes the head.
* Otherwise → it is added to the end of the list.

### Playing Songs

* Starts from the head.
* Traverses through each node.
* Prints song details.
* Uses Thread.sleep(song.duration * 1000) to simulate playback time.

---

##  Data Structure Used

Singly Linked List

Head → Song1 → Song2 → Song3 → null

---

## Example Usage

```java
Song.playlist myPlaylist = new Song.playlist();

myPlaylist.add(new Song("Shape of You", "Ed Sheeran", 4));
myPlaylist.add(new Song("Blinding Lights", "The Weeknd", 3));
myPlaylist.add(new Song("Perfect", "Ed Sheeran", 5));

myPlaylist.playAll();
```

---

##  Sample Output

playing: Shape of You artist: Ed Sheeran duration: 4
playing: Blinding Lights artist: The Weeknd duration: 3
playing: Perfect artist: Ed Sheeran duration: 5
END OF PLAYLIST !

---

## Pseudo code

START

CLASS SongNode
    DECLARE song AS Song
    DECLARE Next AS SongNode

    CONSTRUCTOR SongNode(song)
        SET this.song = song
        SET this.Next = NULL
    END CONSTRUCTOR
END CLASS


CLASS Song
    DECLARE Title AS String
    DECLARE artist AS String
    DECLARE duration AS Integer

    CONSTRUCTOR Song()
        
    END CONSTRUCTOR

    CONSTRUCTOR Song(Title, artist, duration)
        SET this.Title = Title
        SET this.artist = artist
        SET this.duration = duration
    END CONSTRUCTOR


    CLASS Playlist
        DECLARE head AS SongNode

        CONSTRUCTOR Playlist()
            SET head = NULL
        END CONSTRUCTOR


        METHOD add(song)
            CREATE newNode AS SongNode(song)

            IF head IS NULL THEN
                SET head = newNode
                RETURN
            END IF

            SET temp = head
            WHILE temp.Next IS NOT NULL DO
                SET temp = temp.Next
            END WHILE

            SET temp.Next = newNode
        END METHOD


        METHOD playAll()
            SET current = head

            WHILE current IS NOT NULL DO
                CALL playSong(current.song)
                SET current = current.Next
            END WHILE

            PRINT "END OF PLAYLIST!"
        END METHOD


        METHOD playSong(song)
            PRINT "Playing: " + song.Title
            PRINT "Artist: " + song.artist
            PRINT "Duration: " + song.duration + " seconds"

            TRY
                WAIT for (song.duration × 1000 milliseconds)
            CATCH InterruptedException
                PRINT "Playback Interrupted"
            END TRY
        END METHOD

    END CLASS
END CLASS
END



## Concepts Demonstrated

* Object-Oriented Programming (OOP)
* Classes and Objects
* Constructors
* Static Inner Classes
* Linked List Implementation
* Traversal of Linked List
* Thread.sleep() for simulation
* Exception Handling




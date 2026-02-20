package org.example;

class SongNode{
    Song song;
    SongNode Next;
    public SongNode(Song song){
        this.song=song;
        this.Next=null;
    }
}


public class Song {
    String Title;
    String artist;
    int duration;
    public Song(){

    }
    public Song(String Title,String artist,int duration){
        this.Title=Title;
        this.artist=artist;
        this.duration=duration;

    }

    public static class playlist{
        SongNode head;
        public playlist(){
            this.head=null;

        }
        void add(Song song){
            SongNode newNode= new SongNode(song);
            if(head==null){
                head=newNode;
                return;

            }
            SongNode temp= head;
            while(temp.Next !=null){
                temp=temp.Next;
            }
            temp.Next=newNode;
        }
        void playAll(){
            SongNode current=head;
            while(current !=null){
                playSong(current.song);
                current=current.Next;

            }
            System.out.println("END OF PLAYLIST !");


            }
            void playSong(Song song){
            System.out.println("playing:" +song.Title + "artist :"+ song.artist +"1 duration:" + song.duration);
            try {
                Thread.sleep(song.duration * 1000);
            }catch(InterruptedException e){
                System.out.println("PlaybackInterrupted");

            }
        }

    }

}

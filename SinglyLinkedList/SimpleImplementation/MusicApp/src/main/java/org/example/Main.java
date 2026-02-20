package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        Song.playlist playlist=new Song.playlist();
        playlist.add(new Song("Butterfly effect",     "Travis scott",4));
        playlist.add(new Song("Baddest",        "Yung blue ft ChrisBrown",6));
        playlist.playAll();
    }
}

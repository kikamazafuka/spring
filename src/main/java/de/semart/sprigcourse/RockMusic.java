package de.semart.sprigcourse;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class RockMusic implements Music{

    List<String> songList;

    public RockMusic() {
        List<String> classicalSongList = new ArrayList<>();
        classicalSongList.add("d");
        classicalSongList.add("e");
        classicalSongList.add("f");
        this.songList = classicalSongList;
    }

    public List<String> getSongList() {
        return songList;
    }

    public void setSongList(List<String> songList) {
        this.songList = songList;
    }

    @Override
    public List<String> getSong(){
        return songList;
    }
}

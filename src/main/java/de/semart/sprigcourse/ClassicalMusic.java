package de.semart.sprigcourse;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class ClassicalMusic implements Music{
    private List<String> songList;

    public ClassicalMusic() {
        List<String> classicalSongList = new ArrayList<>();
        classicalSongList.add("a");
        classicalSongList.add("b");
        classicalSongList.add("c");
        this.songList = classicalSongList;
    }

    public List<String> getSongList() {
        return songList;
    }

    public void setSongList() {

    }

    @Override
    public List<String> getSong(){
        return songList;
    }
}

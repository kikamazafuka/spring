package de.semart.sprigcourse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ClassicalMusic implements Music{

    @PostConstruct
    public void doMyInit(){
        System.out.println("Classical initialization");
    }

    @PreDestroy
    public void doMyDestr(){
        System.out.println("Classical destroy method");
    }
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

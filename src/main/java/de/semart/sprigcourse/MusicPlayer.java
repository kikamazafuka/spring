package de.semart.sprigcourse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class MusicPlayer {

    @Value("${musicPlayer.name}")
    private String name;
    @Value("${musicPlayer.volume}")
    private int volume;

    private List<String> genreList;
    private Music music1;
    private Music music2;

    @Autowired
    public MusicPlayer(@Qualifier("rockMusic") Music music1,
                       @Qualifier("classicalMusic") Music music2) {
        this.music1 = music1;
        this.music2 = music2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public String playMusic(Genre genre){
        Random random = new Random();
        int number = random.nextInt(3);
        String playingSong ="";
        switch (genre){
            case ROCK:
                playingSong = "Playing "+music1.getSong().get(number);
                break;
            case CLASSICAL:
                playingSong = "Playing "+music2.getSong().get(number);
                break;
        }

        return playingSong;
    }

}

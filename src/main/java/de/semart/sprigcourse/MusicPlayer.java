package de.semart.sprigcourse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class MusicPlayer {

    private Music music1;
    private Music music2;

    @Autowired
    public MusicPlayer(@Qualifier("rockMusic") Music music1,
                       @Qualifier("classicalMusic") Music music2) {
        this.music1 = music1;
        this.music2 = music2;
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

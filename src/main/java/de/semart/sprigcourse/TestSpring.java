package de.semart.sprigcourse;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class TestSpring {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "applicationContext.xml");



        MusicPlayer musicPlayer = context.getBean("musicPlayer", MusicPlayer.class);
        Genre genre = Genre.ROCK;


        System.out.println(musicPlayer.playMusic(genre));

        Computer computer = context.getBean("computer", Computer.class);
        System.out.println(computer);


        //Music music = context.getBean("musicBean", Music.class);
       // MusicPlayer musicPlayer = new MusicPlayer(music);
/*
        MusicPlayer firstMusicPlayer = context.getBean("musicPlayer", MusicPlayer.class);
        MusicPlayer secondMusicPlayer = context.getBean("musicPlayer", MusicPlayer.class);
        MusicPlayer musicPlayer = context.getBean("musicPlayer", MusicPlayer.class);
        musicPlayer.playMusicList();
        System.out.println(musicPlayer.getName());
        System.out.println(musicPlayer.getVolume());

        boolean comp = firstMusicPlayer==secondMusicPlayer;
        System.out.println(comp);
        System.out.println(firstMusicPlayer);
        System.out.println(secondMusicPlayer);

        firstMusicPlayer.setVolume(11);
        secondMusicPlayer.setVolume(123);

        System.out.println(firstMusicPlayer.getVolume());
        System.out.println(secondMusicPlayer.getVolume());

        RapMusic rapMusic = context.getBean("musicRapBean", RapMusic.class);
        System.out.println(rapMusic.getSong());

*/

        context.close();
    }
}

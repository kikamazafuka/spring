package de.semart.sprigcourse;

import java.util.ArrayList;
import java.util.List;

public class MusicPlayer {
    public void beanInit(){
        System.out.println("MusicPlayer Bean Init");
    }

    public void beanDestroy(){
        System.out.println("MusicPlayer Bean Destroy");
    }

    private Music music;
    private List<Music> musicList;
    private String name;
    private int volume;

    public MusicPlayer() {
    }

    public MusicPlayer(Music music){
        this.music = music;
    }

    public void setMusic(Music music) {
        this.music = music;
    }

    public Music getMusic() {
        return music;
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

    public List<Music> getMusicList() {
        return musicList;
    }

    public void setMusicList(List<Music> musicList) {
        this.musicList = musicList;
    }

    public void playMusicList(){
        for (Music music : musicList){
            System.out.println(music.getSong());
        }
    }
}

package advancedwiring.soundsystem;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class BlankDisc2 {

    private String title;
    private String artist;

    public BlankDisc2(
            @Value("${disc.title}") String title,
            @Value("${disc.artist}") String artist) {
        this.title = title;
        this.artist = artist;
//        this.tracks = tracks;
    }


    public void play() {
        System.out.println("Playing " + title + " by " + artist);
    }


}

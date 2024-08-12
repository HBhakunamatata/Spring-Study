package advancedwiring.soundsystem;

public class BlankDisc{

    private String title;
    private String artist;

    public BlankDisc(String title, String artist) {
        this.title = title;
        this.artist = artist;
//        this.tracks = tracks;
    }


    public void play() {
        System.out.println("Playing " + title + " by " + artist);
    }


}

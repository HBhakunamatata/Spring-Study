package cloud.popples.aop.soundsystem;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.util.HashMap;
import java.util.Map;

@Aspect
public class TrackCounter {

    private Map<Integer, Integer> trackCounts;

    public TrackCounter() {
        trackCounts = new HashMap<>();
    }

    @Pointcut("execution(* cloud.popples.aop.soundsystem.CompactDisc.playTrack(int)) " +
            "&& args(trackNumber)")
    public void trackPlayed(int trackNumber) {}


    @Before("trackPlayed(trackNumber)")
    public void countTracks(int trackNumber) {
        int playCount = getPlayCount(trackNumber);
        trackCounts.put(trackNumber, playCount+1);
    }

    public int getPlayCount(int trackNumber) {
        return trackCounts.getOrDefault(trackNumber, 0);
    }

}

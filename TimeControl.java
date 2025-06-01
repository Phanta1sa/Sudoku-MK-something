
import java.time.*;
import java.util.Timer;
import java.util.TimerTask;

public class TimeControl {
    public static boolean waiting = true;
    static TimerTask task = new TimerTask() {
        public void run() {
        waiting = false;
        }
    };
    static Instant start;
    public static void timerForRush(int min){
        Timer timer = new Timer(true); // isDaemon ?
        timer.schedule(task,(min* 60000L));
    }
    public static void timerStart() {
         start =Instant.now();
    }
    public static Instant timerStop() {

        return Instant.now();
    }
    public static Duration timeTaken() {

        return Duration.between(start, timerStop());
    }


}

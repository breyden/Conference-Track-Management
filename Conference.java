public Class Conference{


public class Configuration
{

   //morning session total time in minutes.
    public static final int MORNING_MINUTES = 180;
    //afternoon session total time in minutes.
    public static final int AFTERNOON_MINUTES = 240;
    //total time in minutes.
    public static final Double TOTAL_TRACK_MINUTES = 420.0;
    // Read data from file and create a list of String.
    public static final String TALKS_INPUT_FILE = "/Users/breydenmonyemoratho/Desktop/PRIVY SEAL SOLUTION/TalksInput.txt";

}
public   Conference()
    {
        this.trackTalks = new ArrayList();
    }

    List<Talks> trackTalks;

    int totalTrackMinutes;
    int countTrack;
    int countTalks;

    // getter and setters
    public int getTotalTrackMinutes() {
        return totalTrackMinutes;
    }

    public void setTotalTrackMinutes(int totalTrackMinutes) {
        this.totalTrackMinutes = totalTrackMinutes;
    }

    public int getCountTrack() {
        return countTrack;
    }

    public void setCountTrack(int countTrack) {
        this.countTrack = countTrack;
    }

    public int getCountTalks() {
        return countTalks;
    }

    public void setCountTalks(int countTalks) {
        this.countTalks = countTalks;
    }

    public List<Talks> getTrackTalks() {
        return trackTalks;
    }
    public void setTrackTalks(List<Talks> trackTalks) {
        this.trackTalks = trackTalks;
    }


  


}
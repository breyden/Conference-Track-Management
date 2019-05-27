import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;
public class Conference{



public   Conference()
    {
        this.trackTalks = new ArrayList();
    }

    List<Talks> trackTalks;

    int totalTrackMinutes;
    int countTrack;
    int countTalks;

    public int getTotalTrackMinutes()
    {
        return totalTrackMinutes;
    }

    public void setTotalTrackMinutes(int totalTrackMinutes)
    {
        this.totalTrackMinutes = totalTrackMinutes;
    }

    public int getCountTrack()
    {
        return countTrack;
    }

    public void setCountTrack(int countTrack)
    {
        this.countTrack = countTrack;
    }

    public int getCountTalks()
    {
        return countTalks;
    }

    public void setCountTalks(int countTalks)
    {
        this.countTalks = countTalks;
    }

    public List<Talks> getTrackTalks()
    {
        return trackTalks;
    }
    public void setTrackTalks(List<Talks> trackTalks)
    {
        this.trackTalks = trackTalks;
    }

    /*
     * Read the  input from FileName, get the title, total track count, prepare a trackTalk list to include all
     talks and at the end sort all the talks based on the talk time.  */


    public void ReceiveInput(String FileName)throws Exception
    {
        int id =0;
        int noOfTracks = 0;
        FileInputStream fstream =new FileInputStream(FileName);
       
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

        String strLine;
        int intMinutes;
        int totalMinutes = 0;


        try 
        {
            while ((strLine = br.readLine()) != null)
            {
                // if line is empty 
                if(strLine.contains("//") || strLine.isEmpty())
                    continue;

                id ++;
                /** Process the given talk, get the title and minutes 
                ** Assume the time is given minutes */

                String Title = strLine.substring(0, strLine.lastIndexOf(" "));
                String MinutesString = strLine.substring(strLine.lastIndexOf(" ") + 1);
                String Minutes = strLine.replaceAll("\\D+", "");

                
            
                intMinutes = Integer.parseInt(Minutes);
                totalMinutes = totalMinutes + intMinutes;
                

                // new Talk Object
                Talks singleTalk = new Talks(intMinutes,Title,id);

                // Add this Talk Object to the List of Track Talks
                trackTalks.add(singleTalk);

            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        // close the BufferReader
        br.close();
        // Sort all talks based on the talks-time in descending order.
        Collections.sort(trackTalks, new TalksCompare());

        // Set the total number  of  talks.
        this.setCountTalks(id);

        // set total number of minutes of talks.
        this.setTotalTrackMinutes(totalMinutes);

        // Calculate the no. of tracks
        Double totalMinutesInDouble =  totalMinutes*1.0;

        Double numberOfTracks =  totalMinutesInDouble/Configuration.TOTAL_TRACK_MINUTES;

        double fractionalPart = numberOfTracks % 1;
        double integralPart = numberOfTracks - fractionalPart;

        int leftMinutes = totalMinutes - (int)integralPart*Configuration.TOTAL_TRACK_MINUTES.intValue();

        // if it comes 1.5 or 1.4 or 1.8 - it will give the value of 2 Tracks
        if (leftMinutes > 0) 
        {
            noOfTracks = (int) integralPart + 1;
        }else
        {
            noOfTracks = (int) integralPart;
        }

        this.setCountTrack(noOfTracks);

      
    }



    public int ScheduleTalksIntoTracks(int trackCountIndex, List<Talks> trackTalks, int trackCount,int startTalkIndex , int totalTalkCount)
    {

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm a");
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR, 9);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.AM_PM, Calendar.AM);

        int sum180 = Configuration.MORNING_MINUTES;
        int sum240 = Configuration.AFTERNOON_MINUTES;

        int TalkIndex;

        String sessionTime;
        String SessionTitle;

        for(TalkIndex=startTalkIndex; TalkIndex< totalTalkCount;TalkIndex++) {


            // Get the combination of 180 and fill it
            if (sum180 >= trackTalks.get(TalkIndex).getMinutes()) {
                sum180 = sum180 - trackTalks.get(TalkIndex).getMinutes();
                sessionTime = sdf.format(cal.getTime()) + " " + trackTalks.get(TalkIndex).getTitle() + " " + trackTalks.get(TalkIndex).getMinutes() + "min";
                trackTalks.get(TalkIndex).setTitle(sessionTime);
                cal.add(Calendar.MINUTE, trackTalks.get(TalkIndex).getMinutes());
                SessionTitle = "Track" + " " + (trackCountIndex + 1);
                trackTalks.get(TalkIndex).setTrackTitle(SessionTitle);
            }
            if (sum180 < trackTalks.get(TalkIndex).getMinutes())
                break;

            if (sum180 > 0)
                continue;

            if (sum180 <= 0)
                break;
        }

        trackTalks.get(TalkIndex).setLunchFlag(true);
        sessionTime = "12:00 PM" + " " + "Lunch";
        trackTalks.get(TalkIndex).setLunchTitle(sessionTime);
        cal.add(Calendar.MINUTE, 60);

        TalkIndex++;

        for(;TalkIndex< totalTalkCount;TalkIndex++) {
            // Get the combination of 180 
            if (sum240 >= trackTalks.get(TalkIndex).getMinutes()) {
                sum240 = sum240 - trackTalks.get(TalkIndex).getMinutes();
                sessionTime = sdf.format(cal.getTime()) + " " + trackTalks.get(TalkIndex).getTitle() + " " + trackTalks.get(TalkIndex).getMinutes() + "min";
                trackTalks.get(TalkIndex).setTitle(sessionTime);
                cal.add(Calendar.MINUTE, trackTalks.get(TalkIndex).getMinutes());
                SessionTitle = "Track" + " " + (trackCountIndex + 1);
                trackTalks.get(TalkIndex).setTrackTitle(SessionTitle);
            }
            if (sum240 < trackTalks.get(TalkIndex).getMinutes())
                break;

            if (sum240 > 0)
                continue;

            if (sum240 <= 0)
                break;
        }

        if(totalTalkCount == (TalkIndex))
            --TalkIndex;
        trackTalks.get(TalkIndex).setNetworkingFlag(true);
        sessionTime = "5:00 PM" + " " + "Networking Event";
        trackTalks.get(TalkIndex).setNetworkingTitle(sessionTime);

        TalkIndex++;
        return TalkIndex;

    }



    /*
     * Print out the Talks from  trackTalks,
    */
    public void OutputOfTalksIntoTracks(List<Talks> trackTalks){

        System.out.println("Test Output :");
        System.out.println("");
        String TrackTitle = "dummyValue";

        // Output the talks into tracks based on the totalTalks and the count of Talks.
        for(int trackCountIndex=0;trackCountIndex<trackTalks.size();trackCountIndex++)
        {

            // Print the Track Title
            if(!TrackTitle.equals(trackTalks.get(trackCountIndex).getTrackTitle()))
            {
                System.out.println(trackTalks.get(trackCountIndex).getTrackTitle() + ":");
                System.out.println("");
                TrackTitle = trackTalks.get(trackCountIndex).getTrackTitle();
            }

            // Print the prepared talk's title for this Track
            System.out.println(trackTalks.get(trackCountIndex).getTitle());

            // output the Lunch Title
            if(trackTalks.get(trackCountIndex).isLunchFlag())
            {
                System.out.println(trackTalks.get(trackCountIndex).getLunchTitle());
            }

            // output the Networking Title
            if(trackTalks.get(trackCountIndex).isNetworkingFlag())
            {
                System.out.println(trackTalks.get(trackCountIndex).getNetworkingTitle());
                System.out.println("");
                System.out.println("");
            }

        }
    }
  


}
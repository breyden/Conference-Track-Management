public Class Conference{



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
  


}
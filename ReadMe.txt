Algorithm :  Below are the step which used to schedule the talks to satisfy above condition.

/ **
  * 1. Read data from file and create a list of String.
  * 2. validate each string talk, check the time.
  * 3. sort the list of talks.
  * 4. find the possible days to schedule conference.
  * 5. find out the combination which can fill the first half (morning session total time 180 mins).
  * 6. find out the combination that can fill the evening sessions (180 >= totalSessionTime <= 240).
  * 7. check if any task remaining in the list if yes then try to fill all the eve session.

    Perform step 4 to 7 in the following way:
      /**
     * Set the calender date format, initialize calendar , set Track Configurations its session e.g. Morning session 180- minutes,
     * Lunch 60 minutes,Afternoon Session-240 minutes, networking event starts from 5:00 PM.
     *
     * Step-1 : It picks all the tracktalks in descending order based on the Talk-Time,
     *
     * Step-2 : It process it track by track e.g. if track count is 2 then first it process for track-1 and process the N Talks,
     * and then on next round it process (totalTalks - N talks) for next track until all talks get processed.
     *
     * Step-3: It picks the talks and start putting to talk until morning session don't get filled, so either morning session get filled
     * or it left by some minutes but it doesn't go beyond 180 minutes. or if this is afternoon session then it won't go beyond 240 minutes
     *
     * Step-4: During the processing it sets the information in each talks, e.g. Its 'required title' with required information,
     * Its Track number, its minutes, if after the talk it supposed to be Lunch or Networking then set appropriate flag and put the Lunch or
     * Networking Title.
     *
     * Step-5: At last it return the no. of Talks processed for this Track, and leftover talks will be processed for next track.
     *
     * Step-6 :
     * TBD : Add rules to re-evaluate the Schedule of Talks into Tracks e.g. If on evaluation its found that on Track-1
     * we have 30 free minutes and on Track-2 we have 45 free minutes, and one talk of 60 minutes need to schedule.
     * We can shuffle 30 mins talks from Track-1 to Track-2 , and accommodate this 60 mins talk to track-1
     * Only varieties of input will provide right sense.
     *
     * */
  * 
  * ASSUMPTION :- 
  * 1. This algorithm made to be consider we will not have any task which have time more than 240 mins(4 hrs maximum time for session).
  * 2. To initialize the object of Talk class its assumed that the time for Networking Event is 1 hr, however its not used any where else to schedule talks.
  */
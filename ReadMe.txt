Algorithm :  Below are the step which used to schedule the talks to satisfy above condition.

/ **
  * 1. Read data from file and create a list of String.
  * 2. validate each string talk, check the time.
  * 3. sort the list of talks.
  * 4. find the possible days to schedule conference.
  * 5. find out the combination which can fill the first half (morning session total time 180 mins).
  * 6. find out the combination that can fill the evening sessions (180 >= totalSessionTime <= 240).
  * 7. check if any task remaining in the list if yes then try to fill all the eve session.
  * 
  * ASSUMPTION :- 
  * 1. This algorithm made to be consider we will not have any task which have time more than 240 mins(4 hrs maximum time for session).
  * 2. To initialize the object of Talk class its assumed that the time for Networking Event is 1 hr, however its not used any where else to schedule talks.
  */
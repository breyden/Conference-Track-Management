public class Main {

    public static void main(String[] args) throws Exception {
        // Create a new Conference Object.
        Conference conference = new Conference();
        // Process the received input of Talks's Title and their time.
        conference.ReceiveInput(Configuration.TALKS_INPUT_FILE);

    }
}
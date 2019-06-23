import java.util.Comparator;

public class TalksCompare implements Comparator<Talks>{

    public int compare(Talks a, Talks b)
    {
        if(a.getMinutes() < b.getMinutes())
        {
            return 1;
        } else 
        {
            return -1;
        }
    }
}

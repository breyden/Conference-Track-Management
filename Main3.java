import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main3 
{

    public static void main(String [] args) throws Exception
    {

        String filePath = "testinput.txt";
        Main3.ReceiveInput(filePath);

    }


public static void ReceiveInput(String FileName)throws Exception
{
    FileInputStream fstream =new FileInputStream(FileName);
    BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
    String strLine;

    try 
    {
        while ((strLine = br.readLine()) != null)
        {
            // if line is empty 
            if(strLine.contains("//") || strLine.isEmpty())
                continue;
            RomanNumerals.discern(strLine);

        }
    } catch (IOException e)
    {
        e.printStackTrace();
    }
    // close the BufferReader
    br.close();
}
}
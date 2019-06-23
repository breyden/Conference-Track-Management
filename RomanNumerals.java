import java.text.DecimalFormat;
import java.util.HashMap;

import java.util.regex.Pattern;
/**
 * *conversion between intergalactic units and roman numerals. 
 */
public class RomanNumerals 
{

    static HashMap<String, String> howMuchMoney = new HashMap<String, String>();

    public static boolean check(String s)
    {
       //boolean  b = ( s.contains("XXXX") || s.contains("DD") || s.contains("LL") || s.contains("VV") || s.contains("MMMM") || s.contains("CCCC")) ? true: false;
        if ( s.contains("XXXX") || s.contains("DD") || s.contains("LL") || s.contains("VV") || s.contains("MMMM") || s.contains("CCCC"))
        {
            return false;
        }
          
        else
        {
            return true;
        }

       
           
    }

    public static void discern(String s)
    {
        

        if (check(s))
         {

            if (s.contains("how many Credits")) 
            {
                translate(s, "how many Credits is");
            } else if (s.contains("Silver"))
            {

                whichMetal(s, "Silver");

            } else if (s.contains("Gold")) 
            {
                whichMetal(s, "Gold");
            } else if (s.contains("Iron")) 
            {
                whichMetal(s, "Iron");
            } else if (s.contains("how much is")) 
            {
                translate(s, "how much is ");
            } else if (!s.equals("") && s.contains("is")) 
            {
                String delimiter = " is ";

                String[] output = s.split(Pattern.quote(delimiter));

                howMuchMoney.put(output[0], output[1]);
            } else {
                System.out.println("I have no idea what you are talking about");
            }

        }
        ////////////
        else 
        {
            System.out.println("The string is invalid");
        }
    }


    public static int romanToInt(String s) 
    {
        int nums[] = new int[s.length()];
        for (int i = 0; i < s.length(); i++) 
        {
           
            if (s.charAt(i)=='M')
            {
                nums[i] = 1000;
            }
            else if (s.charAt(i)=='D')
            {
                nums[i] = 500;
            }
            else if (s.charAt(i)=='C')
            {
                nums[i] = 100; 
            }
            else if (s.charAt(i)=='L')
            {
                nums[i] = 50; 
            }
            else if (s.charAt(i)=='X')
            {
                nums[i] = 10; 
            }
            else if (s.charAt(i)=='V')
            {
                nums[i] = 5; 
            }
            else if (s.charAt(i)=='I')
            {
                nums[i] = 1; 
            }

        }
        int sum = 0;
        for (int i = 0; i < nums.length - 1; i++)
        {
            if (nums[i] < nums[i + 1])
            {
                sum -= nums[i];
            }
            else
            {
                sum += nums[i];
            }


        }
        return sum + nums[nums.length - 1];
    }




    public static void whichMetal(String s, String metal) 
    {



        String[] output = s.split(Pattern.quote(metal));

        // Galatic words before the type of metal

        String individualWords = output[0];
        String[] outputWords = individualWords.split(" ");

        String romanNumeral = "";

        // Convert galactic words to Roman numerals

        for (String galactic_words : outputWords) 
        {
            romanNumeral +=  howMuchMoney.get(galactic_words);
        }

        //Roman numerals to regular numbers
        double convertedInt = romanToInt(romanNumeral);

        String creditWords = output[1];


        String[] creditOutputWords = creditWords.split(" ");

        int money = creditOutputWords.length - 2;

        double creditMoney = Double.parseDouble(creditOutputWords[money]);

        double valueToInsert = 0;
        if (convertedInt !=0 )
        {
            valueToInsert = creditMoney / convertedInt;
        }

        else
        {
            System.out.println("creditMoney " + creditMoney);
            System.out.println("convertedInt " + convertedInt);
        }
        // inserts the value of metal and the numeric value into the hashmap

         howMuchMoney.put(metal, Double.toString(valueToInsert));
    }

    static void translate(String s, String separator)
    {

        double sum = 0;
        DecimalFormat df2  = new DecimalFormat("");

        // Splits the string based on whether we are asking how much or how many Credits

        String[] output = s.split(Pattern.quote(separator));

        // Intergalatic words (plus metal), ie glob prok Silver or tegj glob glob

        String individualWords = output[1];

        String[] outputWords = individualWords.split(" ");

        String romanNumeral ="";

        double value =0;

        for (String ow: outputWords)
        {

            if (!ow.equals(""))
             {


                if (!ow.equals("Silver") && (!ow.equals("Gold") && (!ow.equals("Iron"))))
                {

                    // Gets Roman numeral for the Galatic words ( sentence doesn't have Credits)

                    romanNumeral +=  howMuchMoney.get(ow);

                }
                else 
                {


                    // Gets Roman numeral before the metal

                    value= Double.parseDouble(howMuchMoney.get(ow));
                  //  System.out.println("with metals " + romanNumeral);

                    // Multiplies the number of units of the metal by the value of the metal

                    sum = value * romanToInt(romanNumeral);

                    // Remove the last space and ? and also show without decimals

                    System.out.println(output[1].trim().replaceAll(" \\?", "") +" is " + df2.format(sum).toString().replaceAll(",", "") + " Credits");
                }
            }

        }

        // Only prints out if it is not How many credits (since that has already been printed out)

         if (!output[1].contains("Silver") && !output[1].contains("Gold") && !output[1].contains("Iron"))
         {
            System.out.println(output[1].trim().replaceAll(" \\?", "") + " is " + romanToInt(romanNumeral));
         }
        

    }

  


}
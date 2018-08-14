import java.io.IOException;
import java.util.ArrayList;
import java.util.*;
import java.io.File;

public class Main {
    static boolean playing = true;
    static int guessesRemaining = 3;
    static String hiddenWord = "";
    static String maskedWord = "";
    static List<String> guessedCharsList = new ArrayList<String>();
    public static void main(String[] args) throws IOException {
        String guessedCharacter = "";
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to my simple Hangman-game.");
        hiddenWord = randomizeWordFromFile();
        for(int i = 0; i < hiddenWord.length(); i++)
        {
            maskedWord += "*";
        }
        //--------------------------------------------------------------------------------------------------------------
        while(playing){
            System.out.println("The word is: " + maskedWord);
            System.out.print("Characters guessed already: ");
            for(String s : guessedCharsList)
            {
                System.out.print(s + ", ");
            }
            System.out.println("\nGuess a character: ");
            guessedCharacter = sc.nextLine().substring(0,1).toUpperCase();
            if(!isCharGuessed(guessedCharacter)){
                if(checkIfWordContainsChar(guessedCharacter)){
                    System.out.println("You guessed right.");
                }
                else
                {
                    System.out.println("You guessed wrong.");
                }
            }
            else{
                System.out.println("You've already guessed " + guessedCharacter + ".");
            }
        }
        //--------------------------------------------------------------------------------------------------------------
        if(guessesRemaining < 1)
        {
            System.out.println("You are out of guesses. The secret word was: " + hiddenWord);
        }
        else
        {
            System.out.println("---------------------\nYou guessed the whole word. Good job!\n" +
                    "Number of guesses used "+ (10 - guessesRemaining) + ".");
        }
    }
    public static boolean isCharGuessed(String c) {
        for(String s : guessedCharsList)
        {
            if(s.equals(c))
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        return false;
    }
    public static boolean checkIfWordContainsChar(String c){
        guessedCharsList.add(c);
        guessesRemaining--;
        if(guessesRemaining < 1)
            playing = false;

        if(hiddenWord.contains(c)){
            for(int i = 0; i < hiddenWord.length(); i++)
            {
                if(hiddenWord.substring(i, i+1).contains(c))
                {
                    maskedWord = maskedWord.substring(0,i)+c+maskedWord.substring(i+1);
                }
            }
            if(!maskedWord.contains("*")){
                playing = false;
            }
            return true;
        }
        else{
            return false;
        }

    }
    public static String randomizeWordFromFile() throws IOException
    {
        Scanner sc = new Scanner(new File("C:\\Projects\\Java\\Hangman\\Dictionary.txt"));
        List<String> lines = new ArrayList<String>();
        while(sc.hasNextLine())
        {
            lines.add(sc.nextLine());
        }
        String[] arr = lines.toArray(new String[0]);
        int random = (int)(Math.random() * arr.length);
        return arr[random];
    }
}

// interface (implemented with proposal)
import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;


// --== CS400 Project Two File Header ==--
// Name: Tianyou Wen
// Email: twen22@wisc.edu
// Team: Red
// Group: BA
// TA: Cameron Ruggles
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>
/**
 * This interface generates a command menu for movies that will
 * be used for SearchFrontEnd.
 *
 * @author Tianyou Wen
 */
interface SearchFrontEndInterface  {    
    public void run(SearchBackEndInterface searchEngine);

    // Here is a sample of the minimal set of options that 
    // this front end will support:

    // MovieSearch Command Menu:
    // 1. Entering a rank to get a trivia question asked
    // 2. Entering in their own movies with ranks that are above 1000
    // 3. Quit
    void menu();
}

// public class (implemented primarilly in final app week)

/**
 * This class generates a command menu for songs though use of
 * front end while implementing SearchFrontEndInterface.
 * 
 * @author Tianyou Wen
 */
public class SearchFrontEnd implements SearchFrontEndInterface {
    private SearchBackEndInterface backEndInterface;
    private SearchBackEndInterface searchBackEndPlaceholder;
    private int score = 0;
    @Override
    public void run(SearchBackEndInterface searchEngine) {
        this.backEndInterface = searchEngine;
		menu();
    }

    @Override
    public void menu(){
        Scanner scnr = new Scanner(System.in);
		while (true){
			System.out.println("Movie Tree Menu");
			System.out.println("1. Entering a rank to get a trivia question asked");
			System.out.println("2. Entering in their own movies with ranks that are above 1000");
			System.out.println("3. Quit");
			System.out.println("Please Select 1, 2, or 3.");
			String result = scnr.next();
            // When insert 1
            if (result.equals("1")){
                int count = 0;
                System.out.println("After you enter a rank, we will provide the movie's name");
                System.out.println("You can guess the movie's Year, Director and Gerne");
                System.out.println("If at least two of them are 80% correct, you will earn 1 point");
                System.out.println("Current Score: " + score);
                System.out.println("Enter a rank: ");
                // get the next input as rank
                String rank = scnr.next();
                System.out.println("Movie Title is: " + backEndInterface.findTitles(rank));

                System.out.println("Please guess the movie's Year:");
                String year = scnr.next();
                System.out.println("Please guess the movie's Director:");
                String director = scnr.next();
                if (director.equals(backEndInterface.findDirector(rank))) {
                    System.out.println("Congradulation, you are guessing correct");
                    count++;
                }
                else {
                    System.out.println("Sorry, you are guessing incorrect, the correct answer is: " 
                    + backEndInterface.findDirector(rank));
                }
                System.out.println("Please guess the movie's Gerne:");

                String gerne = scnr.next();
                // compare if the year, director and gerne are correct
                // if true, score + 1
                // else System.out.println("Sorry, you are guessing wrong")
                if (count >= 2) {
                    System.out.println("Congradulation, your guessing correct percentage is more than 60%, score+1");
                    score++;
                }
                else System.out.println("Sorry, your guessing correct percentage is less than 60%, score+1");
                continue;
            }
            // When insert 2
            else if (result.equals("2")){
                // Insert an own movie
                System.out.println("Please insert a rank number");
                int rank = 0;
                try{
                    rank = scnr.nextInt();
                }
                catch ( InputMismatchException e){
					System.out.println("Noninteger value included in Rank. Please Start Over.");
				}
                System.out.println("Please insert a year");
                int year = 0;
                try{
                    year = scnr.nextInt();
                }
                catch ( InputMismatchException e){
					System.out.println("Noninteger value included in Movie Year. Please Start Over.");
				}
                System.out.println("Please insert a movie title");
                String title = scnr.nextLine();
                System.out.println("Please insert a movie genre");
                String genre = scnr.next();
                String Director = scnr.nextLine();
                MovieData movie = new MovieData(rank, year, title, genre, Director);

                //backEndInterface.insert(movie);
                continue;
            }
            else if (result.equals("3")){
                break;
            }
            else {
                System.out.println("The input is invalid, please insert 1, 2, or 3");
                continue;
            }
        }
    }
    /*
    public static void menu2() {
        Scanner scnr = new Scanner(System.in);
		while (true){
			System.out.println("Movie Tree Menu");
			System.out.println("1. Entering a rank to get a trivia question asked");
			System.out.println("2. Entering in their own movies with ranks that are above 1000");
			System.out.println("3. Quit");
			System.out.println("Please Select 1, 2, or 3.");
			String result = scnr.next();
            // When insert 1
            if (result.equals("1")){
                int count = 0;
                System.out.println("After you enter a rank, we will provide the movie's name");
                System.out.println("You can guess the movie's Year, Director and Gerne");
                System.out.println("If at least two of them are 80% correct, you will earn 1 point");
                System.out.println("Current Score: " + score);
                System.out.println("Enter a rank: ");
                // get the next input as rank
                String rank = scnr.next();
                System.out.println("Movie Title is: " + backEndInterface.findTitles(rank));

                System.out.println("Please guess the movie's Year:");
                String year = scnr.next();
                System.out.println("Please guess the movie's Director:");
                String director = scnr.next();
                if (director.equals(backEndInterface.findDirector(rank))) {
                    System.out.println("Congradulation, you are guessing correct");
                    count++;
                }
                else {
                    System.out.println("Sorry, you are guessing incorrect, the correct answer is: " 
                    + backEndInterface.findDirector(rank));
                }
                System.out.println("Please guess the movie's Gerne:");

                String gerne = scnr.next();
                // compare if the year, director and gerne are correct
                // if true, score + 1
                // else System.out.println("Sorry, you are guessing wrong")
                if (count >= 2) {
                    System.out.println("Congradulation, your guessing correct percentage is more than 60%, score+1");
                    score++;
                }
                else System.out.println("Sorry, your guessing correct percentage is less than 60%, score+1");
                continue;
            }
            // When insert 2
            else if (result.equals("2")){
                // Insert an own movie
                System.out.println("Please insert a rank number");
                int rank = 0;
                try{
                    rank = scnr.nextInt();
                }
                catch ( InputMismatchException e){
					System.out.println("Noninteger value included in Rank. Please Start Over.");
				}
                System.out.println("Please insert a year");
                int year = 0;
                try{
                    year = scnr.nextInt();
                }
                catch ( InputMismatchException e){
					System.out.println("Noninteger value included in Movie Year. Please Start Over.");
				}
                System.out.println("Please insert a movie title");
                String title = scnr.nextLine();
                System.out.println("Please insert a movie genre");
                String genre = scnr.next();
                String Director = scnr.nextLine();
                MovieData movie = new MovieData(rank, year, title, genre, Director);

                //backEndInterface.insert(movie);
                continue;
            }
            else if (result.equals("3")){
                break;
            }
            else {
                System.out.println("The input is invalid, please insert 1, 2, or 3");
                continue;
            }
        }
    }

    public static void main(String[] args) {
        //Application.launch();
        menu2();
    }*/
}

class SearchFrontEndPlaceholder implements SearchFrontEndInterface {
    private SearchBackEndInterface searchBackEndPlaceholder;

    public void run(SearchBackEndInterface searchEngine) {
        System.out.println("This front end has not been implemented yet.");
    }  
    
    public void menu(){

    }
}

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
    int score();
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
    // Get the whole menu of that movie tree
    @Override
    public void menu(){
		while (true){
            Scanner scnr = new Scanner(System.in);
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
                int rank = 1000;
                boolean rank_bo = false;
                // get the next input as rank
                while(!rank_bo){
                    try {
                        rank = scnr.nextInt();
                        MovieDataInterface rank_movie = new MovieData(rank, 1, "", "", "");
                        if (!backEndInterface.containsMovie(rank_movie)) {
                            System.out.println("Sorry, that rank is not exist, please try another one");
                            continue;
                        }
                        rank_bo = true;
                    }
                    catch (InputMismatchException e){
                        System.out.println("The rank has to be an integer, please try again");
                        scnr.nextLine();
                    }
                }
                
                System.out.println("Movie Title is: " + backEndInterface.findTitles(rank));
                System.out.println("Please guess the movie's Year:");
                int year = 0;
                boolean year_bo = false;
                while(!year_bo){
                    try {
                        year = scnr.nextInt();
                        year_bo = true;
                    }
                    catch (InputMismatchException e){
                        System.out.println("The year has to be an integer, please try again");
                        scnr.nextLine();
                    }
                }
                if (year == backEndInterface.findYear(rank)) {
                    System.out.println("Congratulations, you are guessing correct");
                    count++;
                }
                else {
                    System.out.println("Sorry, you are guessing incorrect, the correct answer is: " 
                    + backEndInterface.findYear(rank));
                }
                scnr.nextLine();
                System.out.println("Please guess the movie's Director:");
                String director = scnr.nextLine();
                if (getSimilarityRatio(director, backEndInterface.findDirector(rank)) >= 0.5) {
                    System.out.println("Congratulations, you are guessing correct");
                    count++;
                }
                else {
                    System.out.println("Sorry, you are guessing incorrect, the correct answer is: " 
                    + backEndInterface.findDirector(rank));
                }
                System.out.println("Please guess the movie's Gerne:");
                String gerne = scnr.nextLine();
                if (getSimilarityRatio(gerne, backEndInterface.findGenre(rank)) >= 0.25) {
                    System.out.println("Congratulations, you are guessing correct");
                    count++;
                }
                else {
                    System.out.println("Sorry, you are guessing incorrect, the correct answer is: " 
                    + backEndInterface.findGenre(rank));
                }
                // compare if the year, director and gerne are correct
                // if true, score + 1
                // else System.out.println("Sorry, you are guessing wrong")
                if (count >= 2) {
                    System.out.println("Congratulations, your guessing correct percentage is more than 60%, score + 1");
                    this.score++;
                }
                else System.out.println("Sorry, your guessing correct percentage is less than 60%");
                continue;
            }
            // When insert 2
            else if (result.equals("2")){
                // Insert an own movie
                System.out.println("Please insert a rank number");
                int rank = 1000;
                boolean rank_bo = false;
                while(!rank_bo){
                    try{
                        rank = scnr.nextInt();
                        MovieDataInterface rank_movie = new MovieData(rank, 1, "", "", "");
                        if (backEndInterface.containsMovie(rank_movie)) {
                            System.out.println("Sorry, that rank is already used, please try another one");
                            continue;
                        }
                        rank_bo = true;
                    }
                    catch ( InputMismatchException e){
                        System.out.println("Noninteger value included in Rank. Please Start Over.");
                        scnr.nextLine();
                    }
                }
                
                System.out.println("Please insert a year");
                int year = 0;
                boolean year_bo = false;
                while(!year_bo){
                    try{
                        year = scnr.nextInt();
                        year_bo = true;
                    }
                    catch ( InputMismatchException e){
                        System.out.println("Noninteger value included in Movie Year. Please Start Over.");
                        scnr.nextLine();
                    }
                }
                System.out.println("Please insert a movie genre");
                String genre = scnr.next();
                scnr.nextLine();
                System.out.println("Please insert a movie title");
                String title = scnr.nextLine();
                System.out.println("Please insert a movie director");
                String Director = scnr.nextLine();
                MovieDataInterface movie = new MovieData(rank, year, title, genre, Director);
                if (backEndInterface.containsMovie(movie)){
                    System.out.println("Sorry, that movie already exist, please try another");
                    continue;
                }
                else {
                    backEndInterface.insert(movie);
                    System.out.println("Movie inserted successfully");
                }
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
    /**
     * return the current score
     */
    public int score() {
        return this.score;
    }

    private int compare(String str, String target) {
        int d[][];              // matrix
        int n = str.length();
        int m = target.length();
        int i;                  // for str
        int j;                  // for target
        char ch1;               // for str
        char ch2;               // for target
        int temp;               // record same letter, could be 0/1
        if (n == 0) {
            return m;
        }
        if (m == 0) {
            return n;
        }
        d = new int[n + 1][m + 1];
        // Initialize the first column
        for (i = 0; i <= n; i++) {
            d[i][0] = i;
        }
        // Initialize the first row
        for (j = 0; j <= m; j++) {
            d[0][j] = j;
        }
        for (i = 1; i <= n; i++) {
            // Check the whole str
            ch1 = str.charAt(i - 1);
            // match str to target
            for (j = 1; j <= m; j++) {
                ch2 = target.charAt(j - 1);
                if (ch1 == ch2 || ch1 == ch2 + 32 || ch1 + 32 == ch2) {
                    temp = 0;
                } else {
                    temp = 1;
                }
                // left+1,above+1, get the min of temp + left above corner
                d[i][j] = min(d[i - 1][j] + 1, d[i][j - 1] + 1, d[i - 1][j - 1] + temp);
            }
        }
        return d[n][m];
    }
 
 
    /**
     * get the minimum value
     */
    private int min(int one, int two, int three) {
        return (one = one < two ? one : two) < three ? one : three;
    }
 
    /**
     * get the percentage of similarity
     */
    public float getSimilarityRatio(String str, String target) {
        int max = Math.max(str.length(), target.length());
        return 1 - (float) compare(str, target) / max;
    }

}


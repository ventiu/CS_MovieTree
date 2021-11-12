// interface (implemented with proposal)
import java.util.ArrayList;
import java.util.Scanner;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.application.Platform;
import javafx.geometry.Pos;
import java.util.Random;

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
    public static int score = 0;
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
                System.out.println("After you enter a rank, we will provide the movie's name");
                System.out.println("You can guess the movie's Year, Director and Gerne");
                System.out.println("Enter a rank: ");
                continue;
            }
            // When insert 2
            else if (result.equals("2")){

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
                System.out.println("After you enter a rank, we will provide the movie's name");
                System.out.println("You can guess the movie's Year, Director and Gerne");
                System.out.println("If at least two of them are 80% correct, you will earn 1 point");
                System.out.println("Current Score: " + score);
                System.out.println("Enter a rank: ");
                // get the next input as rank
                String rank = scnr.next();
                // print out a movie name here 
                System.out.println("Please guess the movie's Year:");
                String year = scnr.next();
                System.out.println("Please guess the movie's Director:");
                String director = scnr.next();
                System.out.println("Please guess the movie's Gerne:");
                String gerne = scnr.next();
                // compare if the year, director and gerne are correct
                // if true, score + 1
                // else System.out.println("Sorry, you are guessing wrong")
                continue;
            }
            // When insert 2
            else if (result.equals("2")){
                // Insert an own movie
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
    }
}
/*
class SearchFrontEndPlaceholder implements SearchFrontEndInterface {
    private SearchBackEndInterface searchBackEndPlaceholder;

    public void run(SearchBackEndInterface searchEngine) {
        System.out.println("This front end has not been implemented yet.");
    }  
    
    public void menu(){

    }
}*/

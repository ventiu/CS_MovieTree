// interface (implemented with proposal)
import java.util.ArrayList;
import java.util.Scanner;
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
interface SearchFrontEndInterface {    
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

    @Override
    public void run(SearchBackEndInterface searchEngine) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void menu(){

    }
}
class SearchFrontEndPlaceholder implements SearchFrontEndInterface {
    public void run(SearchBackEndInterface searchEngine) {
        System.out.println("This front end has not been implemented yet.");
    }  
    
    public void menu(){

    }
}
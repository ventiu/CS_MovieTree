import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class MovieSearchTests {
    /**
     * This method tests the MovieLoaders ability to read from a directory accurately.
     * @throws FileNotFoundException
     * @author Justin and Emma
     */
    @Test
    public void testReadDirectory() throws FileNotFoundException {
            MovieLoader tester = new MovieLoader();
                List<MovieDataInterface> corr = new LinkedList<MovieDataInterface>(); 
                corr.add(new MovieData(1, 2014, "Guardians of the Galaxy", "Action,Adventure,Sci-Fi", "James Gunn"));
                  
                List<MovieDataInterface> test1 = tester.loadAllFilesInDirectory("DWTests/TestDir1/"); 
                assertEquals(corr.get(0).getDirector(),                 test1.get(0).getDirector());
                assertEquals(corr.get(0).getTitle(),                    test1.get(0).getTitle());
                assertEquals(corr.get(0).getYearPublished(),    test1.get(0).getYearPublished());
                assertEquals(corr.get(0).getGenre(),                    test1.get(0).getGenre());
                assertEquals(corr.get(0).getRank(),                     test1.get(0).getRank());
                  
                corr.add(new MovieData(2, 2012, "Prometheus", "Adventure,Mystery,Sci-Fi","Ridley Scott"));
                  
                List<MovieDataInterface> test2 = tester.loadAllFilesInDirectory("DWTests/TestDir1/"); 
                assertEquals(corr.get(1).getDirector(),                 test2.get(1).getDirector());
                assertEquals(corr.get(1).getTitle(),                    test2.get(1).getTitle());
                assertEquals(corr.get(1).getYearPublished(),    test2.get(1).getYearPublished());
                assertEquals(corr.get(1).getGenre(),                    test2.get(1).getGenre());
                assertEquals(corr.get(1).getRank(),                     test2.get(1).getRank());
    
        
    }
    /**
     * This method tests the MovieLoaders ability to properly read and format an individual CSV line.
     * @return Boolean of successful completion(True), else False.
     * @author Justin and Emma
     */
          @Test 
          public void testReadCSVLine() { 
                  MovieLoader tester = new MovieLoader();
                  String test1 = "1,2,3";
                  List<String> corr1 = new LinkedList<String>(); 
                  corr1.add("1");
                  corr1.add("2"); 
                  corr1.add("3"); 
                  
                  List<String> out1 = tester.readCSVLine(test1); 
                  assertEquals(corr1, out1);
                  
                  String test2 = "\"1\",\"2\",\"3\""; 
                  List<String> out2 = tester.readCSVLine(test2); 
                  assertEquals(corr1, out2);
                  
                  String test3 = "\"\"1\"\",\"\"2\"\",\"\"\"3\"\"\""; 
                  List<String> corr3 = new LinkedList<String>(); 
                  corr3.add("\"1\""); 
                  corr3.add("\"2\"");
                  corr3.add("\"3\""); 
                  List<String> out3 = tester.readCSVLine(test3); 
                  assertEquals(corr3, out3);
                  
          }
         
    /**
     * This method tests the MovieLoaders ability to accurately read a CSV file by testing against the first three 
lines.
     * @throws FileNotFoundException
     * @author Justin and Emma
     */
          @Test 
          public void testReadFile() throws FileNotFoundException { 
                  MovieLoader tester = new MovieLoader();
              List<MovieDataInterface> corr = new LinkedList<MovieDataInterface>();
              corr.add(new MovieData(1, 2014, "Guardians of the Galaxy", "Action,Adventure,Sci-Fi", "James Gunn"));
              corr.add(new MovieData(2, 2012, "Prometheus", "Adventure,Mystery,Sci-Fi", "Ridley Scott"));
              corr.add(new MovieData(3, 2016, "Split", "Horror,Thriller", "M. Night Shyamalan"));
              List<MovieDataInterface> test0 = tester.loadFile("DWTests/TestDir1/MovieData.csv");
              assertEquals(corr.get(0).getDirector(),           test0.get(0).getDirector());
              assertEquals(corr.get(0).getTitle(),                      test0.get(0).getTitle());
              assertEquals(corr.get(0).getYearPublished(),      test0.get(0).getYearPublished());
              assertEquals(corr.get(0).getGenre(),                      test0.get(0).getGenre());
              assertEquals(corr.get(0).getRank(),                       test0.get(0).getRank());
              List<MovieDataInterface> test1 = tester.loadFile("DWTests/TestDir2/MovieData.csv");
              assertEquals(corr.get(1).getDirector(),           test1.get(1).getDirector());
              assertEquals(corr.get(1).getTitle(),                      test1.get(1).getTitle());
              assertEquals(corr.get(1).getYearPublished(),      test1.get(1).getYearPublished());
              assertEquals(corr.get(1).getGenre(),                      test1.get(1).getGenre());
              assertEquals(corr.get(1).getRank(),                       test1.get(1).getRank());
              List<MovieDataInterface> test2 = tester.loadFile("DWTests/TestDir3/MovieData.csv");
              assertEquals(corr.get(2).getDirector(),           test2.get(2).getDirector());
              assertEquals(corr.get(2).getTitle(),                      test2.get(2).getTitle());
              assertEquals(corr.get(2).getYearPublished(),      test2.get(2).getYearPublished());
              assertEquals(corr.get(2).getGenre(),                      test2.get(2).getGenre());
              assertEquals(corr.get(2).getRank(),                       test2.get(2).getRank());
          }
         
          /**
           * This test reads a directory containing multiple files and checks to see if the list is the correct length
           * @throws FileNotFoundException
           * @author Emma
           */
          @Test
          public void testMultipleFiles() throws FileNotFoundException{
                  MovieLoader tester = new MovieLoader();
                  List<MovieDataInterface> test1 = tester.loadAllFilesInDirectory("DWTests/TestDir3/"); 
                  assertEquals(2000,test1.size());
          }
    // Back End Developer Tests
          /**
       * JUnit5 test that tests inserting red uncle, and double checks that the correct nodes end up in the correct
 locations in the tree.
       * @author Julia Oghigian
       */ 
      @Test
      public void BackEndDeveloperTestingInsertingWithRedUncle() {
        SearchBackEnd redBlackTree = new SearchBackEnd();
        MovieData data = new MovieData(1, 2001, "Harry Potter", "Fantasy", "David Yates");
        MovieData data1 = new MovieData(2, 2004, "Spider-Man", "Action", "Sam Raimi");
        MovieData data2 = new MovieData(3, 2012, "The Avengers", "Action", "Josh Whedon");
        SearchBackEnd.Node<MovieData> node1 = new SearchBackEnd.Node<MovieData>(data);
        SearchBackEnd.Node<MovieData> node2 = new SearchBackEnd.Node<MovieData>(data1);
        SearchBackEnd.Node<MovieData> node3 = new SearchBackEnd.Node<MovieData>(data2);
        redBlackTree.root = node3;
        redBlackTree.root.rightChild = node1;
        redBlackTree.root.leftChild = node2;
        redBlackTree.size = 3;
        node3.isBlack = true;
        node2.isBlack = false;
        node1.isBlack = false;
        node2.parent = redBlackTree.root;
        node1.parent = redBlackTree.root;
        MovieData data3 = new MovieData(4, 2000, "X-Men", "Action", "Byran Singer");
        SearchBackEnd.Node<MovieData> node4 = new SearchBackEnd.Node<MovieData>(data3);
        redBlackTree.insert(node4.data);
        
        assertEquals(redBlackTree.root.data.getRank(),3);
        assertEquals(redBlackTree.root.leftChild.data.getRank(),2);
        assertEquals(redBlackTree.root.rightChild.data.getRank(),1);
        assertEquals(redBlackTree.root.rightChild.rightChild.data.getRank(),4);
}
/**
 * JUnit5 test that tests isBlack and node coloring while inserting with red
 * uncle. This makes sure red black tree properties are being followed
 * @author Julia Oghigian
 */
@Test
public void BackEndDeveloperTestIsBlack() {
        SearchBackEnd redBlackTree = new SearchBackEnd();
        MovieData data = new MovieData(1, 2001, "Harry Potter", "Fantasy", "David Yates");
        MovieData data1 = new MovieData(2, 2004, "Spider-Man", "Action", "Sam Raimi");
        MovieData data2 = new MovieData(3, 2012, "The Avengers", "Action", "Josh Whedon");
        SearchBackEnd.Node<MovieData> node1 = new SearchBackEnd.Node<MovieData>(data);
        SearchBackEnd.Node<MovieData> node2 = new SearchBackEnd.Node<MovieData>(data1);
        SearchBackEnd.Node<MovieData> node3 = new SearchBackEnd.Node<MovieData>(data2);
        redBlackTree.root = node3;
        redBlackTree.root.rightChild = node1;
        redBlackTree.root.leftChild = node2;
        redBlackTree.size = 3;
        node3.isBlack = true;
        node2.isBlack = false;
        node1.isBlack = false;
        node2.parent = redBlackTree.root;
        node1.parent = redBlackTree.root;
        MovieData data3 = new MovieData(4, 2000, "X-Men", "Action", "Byran Singer");
        SearchBackEnd.Node<MovieData> node4 = new SearchBackEnd.Node<MovieData>(data3);
        redBlackTree.insert(node4.data);
        
        assertEquals(redBlackTree.root.isBlack, true);
        assertEquals(redBlackTree.root.leftChild.isBlack, true);
        assertEquals(redBlackTree.root.rightChild.isBlack, true);
        assertEquals(redBlackTree.root.rightChild.rightChild.isBlack, false);
}
/**
 * JUnit5 test that tests findDirector by adding 4 elements to the red black tree and seeing if it gets the c
orrect director 
 * when it is called
 * @author Julia Oghigian
 */
@Test
      public void backEndDeveloperTestingFindDirector() {
              SearchBackEnd redBlackTree = new SearchBackEnd();
              MovieData data = new MovieData(1, 2001, "Harry Potter", "Fantasy", "David Yates");
              MovieData data1 = new MovieData(2, 2004, "Spider-Man", "Action", "Sam Raimi");
              MovieData data2 = new MovieData(3, 2012, "The Avengers", "Action", "Josh Whedon");
              redBlackTree.insert(data);
              redBlackTree.insert(data1);
              redBlackTree.insert(data2);
              MovieData data3 = new MovieData(4, 2000, "X-Men", "Action", "Byran Singer");
              redBlackTree.insert(data3);
              assertEquals("Sam Raimi", redBlackTree.findDirector(2));
      }
      /**
       * This method adds 5 movies to a red black tree then checks the contains() method on two that do exist and o
ne that doesn't
       * and sees if they are true and false respectively
       * @authon Emma
       */
      @Test
      public void testContians()
      {
          SearchBackEnd redBlackTree = new SearchBackEnd();
          MovieData data = new MovieData(1, 2001, "Harry Potter", "Fantasy", "David Yates");
          MovieData data1 = new MovieData(2, 2004, "Spider-Man", "Action", "Sam Raimi");
          MovieData data2 = new MovieData(3, 2012, "The Avengers", "Action", "Josh Whedon");
          MovieData data3 = new MovieData(4, 2000, "X-Men", "Action", "Byran Singer");
          MovieData data4 = new MovieData(5, 2016, "Split", "Horror,Thriller", "M. Night Shyamalan");
          redBlackTree.insert(data);
          redBlackTree.insert(data1);
          redBlackTree.insert(data2);
          redBlackTree.insert(data3);
          redBlackTree.insert(data4);
          
          assertEquals(redBlackTree.contains(data.getRank()), true);
          assertEquals(redBlackTree.contains(data3.getRank()), true);
          assertEquals(redBlackTree.contains(-10), false);
        }
      
        /**
         * This method loads all the elements from the datafile into the red black tree
         * then does a preorder traversal with traverse() and gets an array that has true for all elements that do ex
  ist
         * and false for all those that don't. If any of the elements are false it fails the test.
         * @throws FileNotFoundException
         * @author Emma
         */
        @Test
        public void testHasAllNodes() throws FileNotFoundException
        {
            List<MovieDataInterface> movies = new MovieLoader().loadAllFilesInDirectory("DWTests/TestDir1/");
            SearchBackEnd engine = new SearchBackEnd();
            for(MovieDataInterface movie : movies) engine.insert(movie);
            boolean allTrue = true;
            boolean[] output = new boolean[movies.size()];
            for(int i = 0; i < 1000; i++)
            {
                    output[i] = false;
            }
            traverse(output, engine.root);
            for(int i = 1; i <=1000; i++)
            {
                    if(!output[i-1]) {
                            allTrue = false;
                    }
                            
            }
            
            assertEquals(allTrue, true);
        }
        /**
         * A helper method that does a pre-order traversal of the tree and updates the boolean array with true for al
  l ranks that it finds
  * @param output - an array of booleans, it is initialized to false and is set to true for all indexes equal 
to rank - 1
       * @param subtree - the root of the subtree
       * @author Emma
       */
      private void traverse(boolean[] output,  SearchBackEnd.Node<MovieData> subtree)
      {      
          
          if(subtree == null) {return;}
          traverse(output, subtree.leftChild);
          output[subtree.data.getRank() - 1] = true;
          
          traverse(output, subtree.rightChild);
      }
      //https://stackoverflow.com/questions/31635698/junit-testing-for-user-input-using
      
    // Front End Developer Tests
     /**
	*
	* This tests the menu with input 1 and see if all 3 output are correct
    * @author Tianyou
	*/
    @Test
    public void testMenu(){
        MovieData data = new MovieData(1, 2001, "Harry Potter", "Fantasy", "David Yates");
        MovieData data1 = new MovieData(2, 2004, "Spider-Man", "Action", "Sam Raimi");
        MovieData data2 = new MovieData(3, 2012, "The Avengers", "Action", "Josh Whedon");
        MovieData data3 = new MovieData(4, 2000, "X-Men", "Action", "Byran Singer");
        MovieData data4 = new MovieData(5, 2016, "Split", "Horror,Thriller", "M. Night Shyamalan");
          
        TextUITester tester = new TextUITester("3");
        SearchFrontEnd FrontEnd = new SearchFrontEnd();
		SearchBackEnd BackEnd = new SearchBackEnd();
        BackEnd.insert(data);
        BackEnd.insert(data1);
        BackEnd.insert(data2);
        BackEnd.insert(data3);
        BackEnd.insert(data4);
		FrontEnd.run(BackEnd);
        String output = tester.checkOutput();
        assertTrue(output.startsWith("Movie Tree Menu") && 
           output.contains("1. Entering a rank to get a trivia question asked") &&
		   output.contains("2. Entering in their own movies with ranks that are above 1000") &&
		   output.contains("3. Quit") );
    }
     /**
	*
	* This tests the game part format and input 1, 1 with rank, 2014 with year, jason with director, action with genre
	* and check if the output is asking for guessing movie's year, director and genre
    * @author Tianyou
	*/
    @Test
    public void testGameFormat() {
        MovieData data = new MovieData(1, 2001, "Harry Potter", "Fantasy", "David Yates");
        MovieData data1 = new MovieData(2, 2004, "Spider-Man", "Action", "Sam Raimi");
        MovieData data2 = new MovieData(3, 2012, "The Avengers", "Action", "Josh Whedon");
        MovieData data3 = new MovieData(4, 2000, "X-Men", "Action", "Byran Singer");
        MovieData data4 = new MovieData(5, 2016, "Split", "Horror,Thriller", "M. Night Shyamalan");
        TextUITester tester = new TextUITester("1\n1\n2001\nDavid\nFantasy\n3\n");
        SearchFrontEnd FrontEnd = new SearchFrontEnd();
		SearchBackEnd BackEnd = new SearchBackEnd();
        BackEnd.insert(data);
        BackEnd.insert(data1);
        BackEnd.insert(data2);
        BackEnd.insert(data3);
        BackEnd.insert(data4);
		FrontEnd.run(BackEnd);
        String output = tester.checkOutput();
        assertTrue(
           output.contains("Enter a rank:") &&
		   output.contains("Please guess the movie's Year:") &&
		   output.contains("Please guess the movie's Director:") &&
           output.contains("Please guess the movie's Gerne:"));
    }
    /**
	*
	* This tests the game with input 1, 1 with rank, 2014 with year, jason with director, action with genre
	* and check if the game is working well. The user get 60% correct and the score should plus 1
    * @author Tianyou
	*/
    @Test 
    public void testScore() {
        MovieData data = new MovieData(1, 2001, "Harry Potter", "Fantasy", "David Yates");
        MovieData data1 = new MovieData(2, 2004, "Spider-Man", "Action", "Sam Raimi");
        MovieData data2 = new MovieData(3, 2012, "The Avengers", "Action", "Josh Whedon");
        MovieData data3 = new MovieData(4, 2000, "X-Men", "Action", "Byran Singer");
        MovieData data4 = new MovieData(5, 2016, "Split", "Horror,Thriller", "M. Night Shyamalan");
        TextUITester tester = new TextUITester("1\n1\n2001\nJulia\nFantasy\n3\n");
        SearchFrontEnd FrontEnd = new SearchFrontEnd();
		SearchBackEnd BackEnd = new SearchBackEnd();
        BackEnd.insert(data);
        BackEnd.insert(data1);
        BackEnd.insert(data2);
        BackEnd.insert(data3);
        BackEnd.insert(data4);
		FrontEnd.run(BackEnd);
        String output = tester.checkOutput();
        assertTrue(
           output.contains("Congratulations, you are guessing correct") &&
		   output.contains("Sorry, you are guessing incorrect, the correct answer is: David Yates") &&
		   output.contains("Congratulations, your guessing correct percentage is more than 60%, score + 1"));
    }
    
      /**
       * This tests the inserting of new movies into the list from the ui. It first adds a movie that already exist
s and makes
       * sure that it isn't overwritten. Then it adds a new movie and makes sure it was actually added to the list
       * @author Emma
       */
      @Test
      public void testInsertUI()
      {
          SearchBackEnd engine = new SearchBackEnd();
          SearchFrontEnd ui = new SearchFrontEnd();
          engine.insert(new MovieData(0, 100, "", "", ""));
          
          String input = "2\n0\n1\na\nb\nc\n1\n1\na\nb\nc\n3\n";
          InputStream in = new ByteArrayInputStream(input.getBytes());
          System.setIn(in);
          
          ui.run(engine);
          
          assertEquals(engine.findYear(0), 100);
          assertEquals(engine.contains(1), true);
      }
      
      /**
       * This tests the trivia portion of the ui. First it answers the trivia question correctly and makes sure the
 score was increased
       * then it answers the questions wrong and makes sure the score is not increased.
       * @throws FileNotFoundException
       * @author Emma
       */
      @Test
      public void testTrivia() throws FileNotFoundException
      {
          List<MovieDataInterface> movies = new MovieLoader().loadAllFilesInDirectory("DWTests/TestDir1/");
          SearchBackEnd engine = new SearchBackEnd();
          for(MovieDataInterface movie : movies) engine.insert(movie);
          SearchFrontEnd ui = new SearchFrontEnd();
          
          String input = "1\n1\n2014\nJames Gunn\nAction\n3\n";
          InputStream in = new ByteArrayInputStream(input.getBytes());
          System.setIn(in);
          ui.run(engine);
          assertEquals(1, ui.score());
          input = "1\n1\n0\nPerson\nGenre\n3\n";
          in = new ByteArrayInputStream(input.getBytes());
          System.setIn(in);
          ui.run(engine);
          assertEquals(1, ui.score());
      }
      
      /**
       * This tests the string compare method. It makes sure case is ignored, and makes sure that it counts any ele
ment from a
       * list is counted. It also checks that a string that is the correct answer plus some is not counted and that
 a string that is the
       * correct answer minus some is not counted
       * @author Emma
       */
      @Test
      public void testStringCompare()
      {
        SearchFrontEnd ui = new SearchFrontEnd();
        assertEquals(true, ui.stringCompare("hi", "HI"));
        assertEquals(true, ui.stringCompare("comedy", "comedy,tragedy"));
        assertEquals(false, ui.stringCompare("thing1", "thing"));
        assertEquals(false, ui.stringCompare("t", "tt,ttt,tttt"));
    }
}
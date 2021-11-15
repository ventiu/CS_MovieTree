import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class MovieSearchTests {


    /**
     * This method tests the MovieLoaders ability to read from a directory accurately.
     * @throws FileNotFoundException
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testReadDirectory() throws FileNotFoundException {
	    MovieLoader tester = new MovieLoader();
		List<MovieDataInterface> corr = new LinkedList<MovieDataInterface>(); 
		corr.add(new MovieData(1, 2014, "Guardians of the Galaxy", "Action,Adventure,Sci-Fi", "James Gunn"));
		  
		List<MovieDataInterface> test1 = tester.loadAllFilesInDirectory("DWTests/TestDir1/"); 
	
		assertEquals(corr.get(0).getDirector(), 		test1.get(0).getDirector());
		assertEquals(corr.get(0).getTitle(), 			test1.get(0).getTitle());
		assertEquals(corr.get(0).getYearPublished(), 	test1.get(0).getYearPublished());
		assertEquals(corr.get(0).getGenre(),		 	test1.get(0).getGenre());
		assertEquals(corr.get(0).getRank(), 			test1.get(0).getRank());
		  
		corr.add(new MovieData(2, 2012, "Prometheus", "Adventure,Mystery,Sci-Fi","Ridley Scott"));
		  
		List<MovieDataInterface> test2 = tester.loadAllFilesInDirectory("DWTests/TestDir1/"); 
		assertEquals(corr.get(1).getDirector(), 		test2.get(1).getDirector());
		assertEquals(corr.get(1).getTitle(), 			test2.get(1).getTitle());
		assertEquals(corr.get(1).getYearPublished(), 	test2.get(1).getYearPublished());
		assertEquals(corr.get(1).getGenre(),		 	test2.get(1).getGenre());
		assertEquals(corr.get(1).getRank(), 			test2.get(1).getRank());
    	
        

    }

    /**
     * This method tests the MovieLoaders ability to properly read and format an individual CSV line.
     * @return Boolean of successful completion(True), else False.
     */
	  @Test 
	@SuppressWarnings("unchecked")  
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
     * This method tests the MovieLoaders ability to accurately read a CSV file by testing against the first three lines.
     * @throws FileNotFoundException
     */
	@SuppressWarnings("unchecked")
	  @Test 
	  public void testReadFile() throws FileNotFoundException { 
		  MovieLoader tester = new MovieLoader();
	      List<MovieDataInterface> corr = new LinkedList<MovieDataInterface>();
	      corr.add(new MovieData(1, 2014, "Guardians of the Galaxy", "Action,Adventure,Sci-Fi", "James Gunn"));
	      corr.add(new MovieData(2, 2012, "Prometheus", "Adventure,Mystery,Sci-Fi", "Ridley Scott"));
	      corr.add(new MovieData(3, 2016, "Split", "Horror,Thriller", "M. Night Shyamalan"));

	      List<MovieDataInterface> test0 = tester.loadFile("DWTests/TestDir1/MovieData.csv");

	      assertEquals(corr.get(0).getDirector(), 		test0.get(0).getDirector());
	      assertEquals(corr.get(0).getTitle(), 			test0.get(0).getTitle());
	      assertEquals(corr.get(0).getYearPublished(), 	test0.get(0).getYearPublished());
	      assertEquals(corr.get(0).getGenre(), 			test0.get(0).getGenre());
	      assertEquals(corr.get(0).getRank(), 			test0.get(0).getRank());

	      List<MovieDataInterface> test1 = tester.loadFile("DWTests/TestDir2/MovieData.csv");

	      assertEquals(corr.get(1).getDirector(), 		test1.get(1).getDirector());
	      assertEquals(corr.get(1).getTitle(), 			test1.get(1).getTitle());
	      assertEquals(corr.get(1).getYearPublished(), 	test1.get(1).getYearPublished());
	      assertEquals(corr.get(1).getGenre(), 			test1.get(1).getGenre());
	      assertEquals(corr.get(1).getRank(), 			test1.get(1).getRank());

	      List<MovieDataInterface> test2 = tester.loadFile("DWTests/TestDir3/MovieData.csv");

	      assertEquals(corr.get(2).getDirector(), 		test2.get(2).getDirector());
	      assertEquals(corr.get(2).getTitle(), 			test2.get(2).getTitle());
	      assertEquals(corr.get(2).getYearPublished(), 	test2.get(2).getYearPublished());
	      assertEquals(corr.get(2).getGenre(), 			test2.get(2).getGenre());
	      assertEquals(corr.get(2).getRank(), 			test2.get(2).getRank());
	  }
	 
	  /**
	   * This test reads a directory containing multiple files and checks to see if the list is the correct length
	   * @throws FileNotFoundException
	   */

	@SuppressWarnings("unchecked")
	  @Test
	  public void testMultipleFiles() throws FileNotFoundException{
		  MovieLoader tester = new MovieLoader();
		  List<MovieDataInterface> test1 = tester.loadAllFilesInDirectory("DWTests/TestDir3/"); 
		  assertEquals(2000,test1.size());
	  }
    // Back End Developer Tests
        /**
	 * JUnit5 test that tests inserting red uncle.
	 */
	@Test
	public void BackEndDeveloperTestingInsertingWithRedUncle() {
		SearchBackEnd redBlackTree = new SearchBackEnd();
		MovieData data = new MovieData(1, 2001, "Harry Potter", "Fantasy", "David Yates");
		MovieData data1 = new MovieData(2, 2004, "Spider-Man", "Action", "Sam Raimi");
		MovieData data2 = new MovieData(3, 2012, "The Avengers", "Action", "Josh Whedon");
		SearchBackEnd.Node<MovieData> node1 = new Node<MovieData>(data);
		SearchBackEnd.Node<MovieData> node2 = new Node<MovieData>(data1);
		SearchBackEnd.Node<MovieData> node3 = new Node<MovieData>(data2);

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
		SearchBackEnd.Node<MovieData> node4 = new Node<MovieData>(data3);
		redBlackTree.insert(node4.data);

		Assertions.assertEquals("3, 2, 1, 4",
				Integer.toString(redBlackTree.root.data.getRank()).toString() + ", "
						+ Integer.toString(redBlackTree.root.leftChild.data.getRank()).toString() + ", "
						+ Integer.toString(redBlackTree.root.rightChild.data.getRank()).toString() + ", "
						+ Integer.toString(redBlackTree.root.rightChild.rightChild.data.getRank()).toString());
	}

	/**
	 * JUnit5 test that tests isBlack and node coloring while inserting with red
	 * uncle.
	 */

	@Test
	public void BackEndDeveloperTestIsBlack() {
		SearchBackEnd redBlackTree = new SearchBackEnd();
		MovieData data = new MovieData(1, 2001, "Harry Potter", "Fantasy", "David Yates");
		MovieData data1 = new MovieData(2, 2004, "Spider-Man", "Action", "Sam Raimi");
		MovieData data2 = new MovieData(3, 2012, "The Avengers", "Action", "Josh Whedon");
		SearchBackEnd.Node<MovieData> node1 = new Node<MovieData>(data);
		SearchBackEnd.Node<MovieData> node2 = new Node<MovieData>(data1);
		SearchBackEnd.Node<MovieData> node3 = new Node<MovieData>(data2);

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
		SearchBackEnd.Node<MovieData> node4 = new Node<MovieData>(data3);
		redBlackTree.insert(node4.data);

		boolean n1 = redBlackTree.root.isBlack;
		boolean n2 = redBlackTree.root.leftChild.isBlack;
		boolean n3 = redBlackTree.root.rightChild.isBlack;
		boolean n4 = redBlackTree.root.rightChild.rightChild.isBlack;
		String checkBoolean = n1 + " " + n2 + " " + n3 + " " + n4;
		Assertions.assertEquals("true true true false", checkBoolean);
	}

	/**
	 * JUnit5 test that tests findDirector
	 */
	@Test
	public void backEndDeveloperTestingFindDirector() {
		SearchBackEnd redBlackTree = new SearchBackEnd();
		MovieData data = new MovieData(1, 2001, "Harry Potter", "Fantasy", "David Yates");
		MovieData data1 = new MovieData(2, 2004, "Spider-Man", "Action", "Sam Raimi");
		MovieData data2 = new MovieData(3, 2012, "The Avengers", "Action", "Josh Whedon");
		SearchBackEnd.Node<MovieData> node1 = new Node<MovieData>(data);
		SearchBackEnd.Node<MovieData> node2 = new Node<MovieData>(data1);
		SearchBackEnd.Node<MovieData> node3 = new Node<MovieData>(data2);
		insert(node1.data);
		insert(node2.data);
		insert(node3.data);
		MovieData data3 = new MovieData(4, 2000, "X-Men", "Action", "Byran Singer");
		SearchBackEnd.Node<MovieData> node4 = new Node<MovieData>(data3);
		redBlackTree.insert(node4.data);
		List<String> list = new LinkedList();
		list.add(data1.getDirector());
		Assertions.assertEquals(list, findDirector("2"));
		
	}
    // Front End Developer Tests

    // Integration Manager Tests


}


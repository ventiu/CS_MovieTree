import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;


public class MovieSearchTests {

    public static void main(String[] args) throws Exception {
        // Run All Tests
	runAllDataWrangler();
    }

    // Data Wrangler Code Tests
    /**
     * This method runs all of the Data Wrangler test methods and prints their results.
     * @throws FileNotFoundException
     */
    public static void runAllDataWrangler() throws FileNotFoundException {
        System.out.println("Read Directory: " + testReadDirectory());
        System.out.println("Read CSV Line : " + testReadCSVLine());
        System.out.println("Read File     : " + testReadFile());
    }

    /**
     * This method tests the MovieLoaders ability to read from multiple directories accurately.
     * @return Boolean of successful completion(True), else False.
     * @throws FileNotFoundException
     */
    private static boolean testReadDirectory() throws FileNotFoundException {
        MovieLoader tester = new MovieLoader();
        List<MovieDataInterface> corr = new LinkedList<MovieDataInterface>();
        corr.add(new MovieData(1, 2014, "Guardians of the Galaxy", "Action,Adventure,Sci-Fi", "James Gunn"));
        corr.add(new MovieData(2, 2012, "Prometheus", "Adventure,Mystery,Sci-Fi", "Ridley Scott"));
        corr.add(new MovieData(3, 2016, "Split", "Horror,Thriller", "M. Night Shyamalan"));

        List<MovieDataInterface> test1 = tester.loadFile("DWTests/TestDir1/MovieData.csv");
        if (!corr.get(0).getDirector().equals(test1.get(0).getDirector()) ||
                !corr.get(0).getTitle().equals(test1.get(0).getTitle()) ||
                corr.get(0).getYearPublished() != test1.get(0).getYearPublished() ||
                !corr.get(0).getGenre().equals(test1.get(0).getGenre()) ||
                corr.get(0).getRank() != test1.get(0).getRank()) {
            return false;
        }

        List<MovieDataInterface> test2 = tester.loadFile("DWTests/TestDir2/MovieData.csv");
        if (!corr.get(2).getDirector().equals(test2.get(2).getDirector()) ||
                !corr.get(2).getTitle().equals(test2.get(2).getTitle()) ||
                corr.get(2).getYearPublished() != test2.get(2).getYearPublished() ||
                !corr.get(2).getGenre().equals(test2.get(2).getGenre()) ||
                corr.get(2).getRank() != test2.get(2).getRank()) {
            return false;
        }

        List<MovieDataInterface> test3 = tester.loadFile("DWTests/TestDir3/MovieData.csv");
        if (!corr.get(1).getDirector().equals(test3.get(1).getDirector()) ||
                !corr.get(1).getTitle().equals(test3.get(1).getTitle()) ||
                corr.get(1).getYearPublished() != test3.get(1).getYearPublished() ||
                !corr.get(1).getGenre().equals(test3.get(1).getGenre()) ||
                corr.get(1).getRank() != test3.get(1).getRank()) {
            return false;
        }
        return true;
    }

    /**
     * This method tests the MovieLoaders ability to properly read and format an individual CSV line.
     * @return Boolean of successful completion(True), else False.
     */
    private static boolean testReadCSVLine() {
        MovieLoader tester = new MovieLoader();
        String test1 = "1,2,3";

        List<String> corr1 = new LinkedList<String>();
        corr1.add("1");
        corr1.add("2");
        corr1.add("3");
        List<String> out1 = tester.readCSVLine(test1);
        if (!corr1.equals(out1)) {
            return false;
        }

        String test2 = "\"1\",\"2\",\"3\"";
        List<String> out2 = tester.readCSVLine(test2);
        if (!corr1.equals(out2)) {
            return false;
        }

        String test3 = "\"\"1\"\",\"\"2\"\",\"\"\"3\"\"\"";
        List<String> corr3 = new LinkedList<String>();
        corr3.add("\"1\"");
        corr3.add("\"2\"");
        corr3.add("\"3\"");
        List<String> out3 = tester.readCSVLine(test3);
        if (!corr3.equals(out3)) {
            return false;
        }
        return true;
    }

    /**
     * This method tests the MovieLoaders ability to accurately read a CSV file by testing against the first three lines.
     * @return Boolean of successful completion(True), else False.
     * @throws FileNotFoundException
     */
    private static boolean testReadFile() throws FileNotFoundException {
        MovieLoader tester = new MovieLoader();
        List<MovieDataInterface> corr = new LinkedList<MovieDataInterface>();
        corr.add(new MovieData(1, 2014, "Guardians of the Galaxy", "Action,Adventure,Sci-Fi", "James Gunn"));

        List<MovieDataInterface> test1 = tester.loadAllFilesInDirectory("DWTests/TestDir1");
        if (!corr.get(0).getDirector().equals(test1.get(0).getDirector()) ||
                !corr.get(0).getTitle().equals(test1.get(0).getTitle()) ||
                corr.get(0).getYearPublished() != test1.get(0).getYearPublished() ||
                !corr.get(0).getGenre().equals(test1.get(0).getGenre()) ||
                corr.get(0).getRank() != test1.get(0).getRank()) {
            return false;
        }

        corr.add(new MovieData(2, 2012, "Prometheus", "Adventure,Mystery,Sci-Fi", "Ridley Scott"));

        List<MovieDataInterface> test2 = tester.loadAllFilesInDirectory("DWTests/TestDir1");
        if (!corr.get(1).getDirector().equals(test2.get(1).getDirector()) ||
                !corr.get(1).getTitle().equals(test2.get(1).getTitle()) ||
                corr.get(1).getYearPublished() != test2.get(1).getYearPublished() ||
                !corr.get(1).getGenre().equals(test2.get(1).getGenre()) ||
                corr.get(1).getRank() != test2.get(1).getRank()) {
            return false;
        }
        return true;
    }
    // Back End Developer Tests

    // Front End Developer Tests

    // Integration Manager Tests


}

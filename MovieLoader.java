// --== CS400 Project Two File Header ==--
// Name: Justin Garza
// Email: jmgarza2@wisc.edu
// Team: Red
// Group: BA
// TA: Cameron Ruggles
// Lecturer: Gary Dahl
// Notes to Grader:

import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;

interface MovieLoaderInterface {
    public List<MovieDataInterface> loadFile(String csvFilePath) throws FileNotFoundException;

    public List<MovieDataInterface> loadAllFilesInDirectory(String directoryPath) throws FileNotFoundException;
}

public class MovieLoader implements MovieLoaderInterface{
    public List<MovieDataInterface> loadFile(String csvFilePath) throws FileNotFoundException {
        return null;
    }

    public List<MovieDataInterface> loadAllFilesInDirectory(String directoryPath) throws FileNotFoundException {
        return null;
    }
}

class MovieLoaderPlaceholder implements MovieLoaderInterface {
    public List<MovieDataInterface> loadFile(String csvFilePath) throws FileNotFoundException{
        List<MovieDataInterface> list = new LinkedList<>();
        list.add(new MovieDataPlaceholder1());
        list.add(new MovieDataPlaceholder2());
        return list;
    }
    public List<MovieDataInterface> loadAllFilesInDirectory(String directoryPath) throws FileNotFoundException{
        List<MovieDataInterface> list = new LinkedList<>();
        list.add(new MovieDataPlaceholder1());
        list.add(new MovieDataPlaceholder2());
        list.add(new MovieDataPlaceholder3());
        return list;
    }
}

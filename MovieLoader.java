// --== CS400 Project Two File Header ==--
// Name: Justin Garza
// Email: jmgarza2@wisc.edu
// Team: Red
// Group: BA
// TA: Cameron Ruggles
// Lecturer: Gary Dahl
// Notes to Grader:

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

interface MovieLoaderInterface {
    public List<MovieDataInterface> loadFile(String csvFilePath) throws FileNotFoundException;

    public List<MovieDataInterface> loadAllFilesInDirectory(String directoryPath) throws FileNotFoundException;
}

/**
 * This Class is responsible for reading and parsing all information from a set of CSV files into a linked
 * list that can be loaded into a tree by iterating through the returned list.
 */
public class MovieLoader implements MovieLoaderInterface {

    /**
     * This method parses one line of a CSV file. It reads the line character by character, keeping track
     * of quotes and when it hits a comma while having balanced quotes(not an in text comma) it will format the quotes
     * (getting rid of wrapping quotes and turns double quotes into single) and adds the string to the output list.
     * After reading all of the character in the input String it will return the full output list.
     *
     * @param line The current line of CSV text that needs to be parsed.
     * @return a linked list of Strings where each element is one of the parsed values.
     */
    public List<String> readCSVLine(String line) {
        List<String> out = new LinkedList<String>();
        int quoteCount = 0;
        String currString = "";
        int charCount = 0;

        //append a comma to the end so it will properly capture the last string
        if (line.charAt(line.length() - 1) != ',') {
            line = line + ",";
        }

        for (char currChar : line.toCharArray()) {
            charCount++;
            //ending a block only when quotes are balanced(method 2 described in class)
            if ((currChar == ',' && quoteCount % 2 == 0)) {
                //This deals with empty strings
                if (currString.equals("")) {
                    out.add(currString);
                    continue;
                }

                //Get rid of the quote characters on either side of a block(if they exist)
                if (currString.charAt(0) == '"' && currString.charAt(currString.length() - 1) == '"') {
                    //this deals with strings that are just two quote characters
                    if (currString.length() == 2) {
                        currString = "";
                        out.add(currString);
                        continue;
                    }
                    currString = currString.substring(1, currString.length() - 1);
                }

                //turn the double quotes into single quotes
                currString = currString.replaceAll("\"\"", "\"");
                out.add(currString);
                currString = "";
                continue;
            }

            if (currChar == '"') {
                quoteCount++;
            }

            currString = currString + currChar;

        }
        return out;
    }

    /**
     * This method reads the heading of a CSV file for the variable information and calls readCSVLine()
     * for every line in the file. It loads the information from each line into a new MovieData object.
     *
     * @param csvFilePath The file path to the CSV file location.
     * @return a linked list of MovieData objects from a single CSV file to be loaded into a tree.
     * @throws FileNotFoundException
     */
    @Override
    public List<MovieDataInterface> loadFile(String csvFilePath) throws FileNotFoundException {
        Scanner scnr = new Scanner(new File(csvFilePath), "UTF-8");
        List<MovieDataInterface> out = new LinkedList<MovieDataInterface>();

        //Reads the header line of the CSV file and sets the index of the variable names.
        List<String> header = readCSVLine(scnr.nextLine());
        int rankIndex = header.indexOf("Rank");
        int publishIndex = header.indexOf("Year");
        int titleIndex = header.indexOf("Title");
        int genreIndex = header.indexOf("Genre");
        int directorIndex = header.indexOf("Director");

        while (scnr.hasNextLine()) {
            List<String> line = readCSVLine(scnr.nextLine());
            //extracts just the numerical part of the year(some had months)
            String year = line.get(publishIndex).replaceAll("[^\\d]", "");
            MovieData temp = new MovieData(Integer.parseInt(line.get(rankIndex)), Integer.parseInt(year),
                    line.get(titleIndex), line.get(genreIndex), line.get(directorIndex));
            out.add(temp);
        }
        return out;
    }

    /**
     * This method calls loadFile() for every CSV in a directory and compiles the results into single linked
     * list to be returned as final output.
     *
     * @param directoryPath The path to the directory that contains the CSV files.
     * @return a final linked list comprised of all of the CSV files in a specified directory.
     * @throws FileNotFoundException
     */
    @Override
    public List<MovieDataInterface> loadAllFilesInDirectory(String directoryPath) throws FileNotFoundException {
        List<MovieDataInterface> out = new LinkedList<MovieDataInterface>();
        File target = new File(directoryPath);
        File[] targetList = target.listFiles();
        for (File file : targetList) {
            out.addAll(loadFile(file.getAbsolutePath()));
        }
        return out;
    }
}

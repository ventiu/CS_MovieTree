// --== CS400 Project Two File Header ==--
// Name: Julia Oghigian
// Email: oghigian@wisc.edu 
// Team: Red
// Group: BA 
// TA: Cameron Ruggles
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>
/**
 * This interface generates methods need to create a RedBlack Tree in  SearchBackEnd.
 * 
 * @author Julia Oghigian
 */
public interface SearchBackEndInterface<T extends Comparable<T>> extends Iterable<T> {
    // Note that the provided iterators step through the data within this
    // collection in sorted order, as defined by their compareTo() method.

    public boolean insert(T data) throws NullPointerException, IllegalArgumentException;

    public boolean containsMovie(T data);

    public int size();

    public boolean isEmpty();

}
/**
 * This class generates a RedBlack Tree for movies  while implementing SearchFrontEndInterface.
 *
 * @author Julia Oghigian
 */
public class SearchBackEnd implements SearchBackEndInterface {
	     /**
	     * Adds the given movie to the tree.
	     *
	     * @param movie The movie to be added to the tree.
	     */
    @Override
    public boolean insert(MovieDataInterface movie) {
        // TODO Auto-generated method stub

    }
             /**
	     * Determines if a movie has been added to the tree.
	     *
	     * @param movie  The movie being searched for.
	     * @return true if movie is in tree else return false.
	     */
    @Override
    public boolean containsMovie(MovieDataInterface movie) {
        // TODO Auto-generated method stub
        return false;
    }
            /**
             * Finds the titles at the specific node using movieRank as a key.
             *
             * @param movieRank key
             * @returns List contain movie titles
             */
    @Override
    public List<String> findTitles(String movieRank) {
        // TODO Auto-generated method stub
        return null;
    }
  	    /**
	     * Finds the director at the specific node using movieRank as a key.
	     *
	     * @param movieRank key
	     * @returns List contain director names
	     */
    @Override
    public List<String> findDirector(String movieRank) {
        // TODO Auto-generated method stub
        return null;
    }
	     /**
	     * Finds the size of the tree.
	     *
	     * @return size
	     */
    @Override
    public int size(){
	return 0;
    }
    	     /**
	     * Determines if RedBlack tree is empty or not.	     *
	     * @return true if tree is empty else returns false.
	     */
    @Override
    public boolean isEmpty(){
	return false;
    }


}
class SearchBackEndPlaceholder implements SearchBackEndInterface {
    private MovieDataInterface onlyMovie;

    public void addMovie(MovieDataInterface movie) {
        this.onlyMovie = movie;
    }
    public boolean containsMovie(MovieDataInterface movie) {
        return onlyMovie.equals(movie);
    }
    public List<String> findTitles(String movieRank) {
        List<String> titles = new LinkedList<>();
        if(onlyMovie.getTitle().contains(MovieRank))
            titles.add(onlyMovie.getTitle());
        return titles;
    }
    public List<String> findDirector(String movieRank) {
        List<String> directors = new LinkedList<>();
        if(onlyMovie.getDirector().contains(movieRank))
            directors.add(onlyMovie.getDirector());
        return directors;
    }
   
}

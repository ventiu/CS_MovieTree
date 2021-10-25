public interface SearchBackEndInterface<T extends Comparable<T>> extends Iterable<T> {
    // Note that the provided iterators step through the data within this
    // collection in sorted order, as defined by their compareTo() method.

    public boolean insert(T data) throws NullPointerException, IllegalArgumentException;

    public boolean containsMovie(T data);

    public int size();

    public boolean isEmpty();

}
public class SearchBackEnd implements SearchBackEndInterface {

    @Override
    public boolean insert(MovieDataInterface movie) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean containsMovie(MovieDataInterface movie) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public List<String> findTitles(String movieRank) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<String> findDirector(String movieRank) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int size(){
	return 0;
    }
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

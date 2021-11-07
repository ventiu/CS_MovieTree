// --== CS400 Project Two File Header ==--
// Name: Justin Garza
// Email: jmgarza2@wisc.edu
// Team: Red
// Group: BA
// TA: Cameron Ruggles
// Lecturer: Gary Dahl
// Notes to Grader:

interface MovieDataInterface {
    public int getRank();

    public String getTitle();

    public String getGenre();

    public String getDirector();

    public int getYearPublished();
}

/**
 * The movieData object stores all of the relevant information of the given movie and
 * allows access through its given interface.
 */
public class MovieData implements MovieDataInterface {
    private final int rank;
    private final int yearPub;
    private final String title;
    private final String genre;
    private final String director;

    /**
     * This method is the constructor to set all of the fields in the movieData
     * object.
     *
     * @param rank     the rank of the given movie.
     * @param yearPub  the year the given movie was published.
     * @param title    the title of the given movie.
     * @param genre    the genre(s) of the given movie.
     * @param director the director of the movie.
     */
    public MovieData(int rank, int yearPub, String title, String genre, String director) {
        this.rank = rank;
        this.yearPub = yearPub;
        this.title = title;
        this.genre = genre;
        this.director = director;
    }

    /**
     * Returns the assigned rank.
     *
     * @return the rank int.
     */
    public int getRank() {
        return rank;
    }

    /**
     * Returns the assigned title.
     *
     * @return the title string.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns the assigned genre.
     *
     * @return the genre string.
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Returns the assigned director.
     *
     * @return the director string.
     */
    public String getDirector() {
        return director;
    }

    /**
     * Returns the assigned year published.
     *
     * @return the year published int.
     */
    public int getYearPublished() {
        return yearPub;
    }
}

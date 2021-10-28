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

public class MovieData implements MovieDataInterface {
    public int getRank() { return 0; }

    public String getTitle() { return null; }

   public String getGenre() { return null; }

    public String getDirector() { return null; }

    public int getYearPublished() { return 0; }
}

class MovieDataPlaceholder1 implements MovieDataInterface {
    public int getRank() { return 0; }

    public String getTitle() { return "Movie 1 Dune"; }

    public String getGenre() { return "Action/Adventure"; }

    public String getDirector() { return "Denis Villeneuve"; }

    public int getYearPublished() { return 2021; }
}

class MovieDataPlaceholder2 implements MovieDataInterface {
    public int getRank() { return 2; }

    public String getTitle() { return "Movie 2 BlackWidow"; }

    public String getGenre() { return "Action"; }

    public String getDirector() { return "Cate Shortland"; }

    public int getYearPublished() { return 2021; }
}

class MovieDataPlaceholder3 implements MovieDataInterface {
    public int getRank() { return 1; }

    public String getTitle() { return "Edward Scissorhands"; }

    public String getGenre() { return "Fantasy/Romance"; }

    public String getDirector() { return "Tim Burton"; }

    public int getYearPublished() { return 1990; }
}

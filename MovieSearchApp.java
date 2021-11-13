import java.util.List;
  
public class MovieSearchApp {

    public static void main(String[] args) throws Exception {
        System.out.println("Welcome to Movie Search Trivia");
        List<MovieDataInterface> movies = new MovieLoader().loadAllFilesInDirectory("./data/");
        SearchBackEnd engine = new SearchBackEnd();
        for(MovieDataInterface movie : movies) engine.insert(movie);
        SearchFrontEndInterface ui = new SearchFrontEnd();
        ui.run(engine);
    }

}


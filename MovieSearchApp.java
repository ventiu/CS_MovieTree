import java.util.List;
  
public class MovieSearchApp {

    public static void main(String[] args) throws Exception {
        System.out.println("Welcome to Movie Search Trivia");
        List<MovieDataInterface> moviess = new MovieLoader().loadAllFilesInDirectory("./data/");
        SearchBackEndInterface engine = new SearchBackEnd();
        for(MovieDataInterface movie : movies) engine.addSong(movie);
        SearchFrontEndInterface ui = new SearchFrontEnd();
        ui.run(engine);
    }

}


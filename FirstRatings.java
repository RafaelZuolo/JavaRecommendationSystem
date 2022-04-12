
import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

/**
 * In this assignment you will create a new class 
 * named ​FirstRatings​ to process the movie and 
 * ratings data and to answer questions about them.
 * 
 * @author Rafael Zuolo Coppini Lima
 * @version 03/09/2021
 */

public class FirstRatings {
    
    /**
     * This method should process every record from the CSV file 
     * whose name is filename, a file of movie information, 
     * and return an ArrayList of type Movie with all of 
     * the movie data from the file.
     */
    public static ArrayList<Movie> loadMovies(String filename) {
        ArrayList<Movie> movieList = new ArrayList<>();
        FileResource fr = new FileResource(filename);
        for (CSVRecord record : fr.getCSVParser()) {
            Movie movie = new Movie(
                            record.get("id"),
                            record.get("title"),
                            record.get("year"),
                            record.get("genre"), 
                            record.get("director"),                            
                            record.get("country"),
                            record.get("poster"),
                            Integer.parseInt(record.get("minutes"))
                            );
            movieList.add(movie);
        }
        return movieList;
    }
    
    /**
     * tester of loadMovies
     */
    public static void testLoadMovies() {
        //ArrayList<Movie> movieList = loadMovies("data/ratedmovies_short.csv");
        ArrayList<Movie> movieList = loadMovies("data/ratedmoviesfull.csv");
        System.out.println("Number of movies: "+ movieList.size());
        long nComedy = 0;
        long nTimeGreaterThan150 = 0;
        
        String[] genres = null;
        String[] directors = null;
        int minutes=0;
        long maxDirectedMovies = 0;
        TreeMap<String, Long> movieDirectedCounter = new TreeMap<>();
        for (Movie m : movieList) {
            // how many movies have more than 150 min of duration?
            if (m.getMinutes() > 150) nTimeGreaterThan150++;
            
            // how many movies have comedy in its genre?
            genres = m.getGenres().split(", ");
            for (int i = 0; i< genres.length; i++) {
                if (genres[i].trim().equals("Comedy")) nComedy++;
            }
            
            /*  What is the maximum number of movies directed by the same 
             * director, and who are the directors that 
             * directed that many movies?
             */
            directors = m.getDirector().split(", ");
            for (int i = 0; i < directors.length; i++) {
                Long frequency = movieDirectedCounter.get(directors[i]);
                if (frequency == null) {
                    frequency = Long.valueOf(1);
                    movieDirectedCounter.put(directors[i], frequency);
                } else movieDirectedCounter.put(directors[i], ++frequency);
                maxDirectedMovies = Math.max(frequency, maxDirectedMovies);
            }
        }
        System.out.println("Number of comedy movies: "+nComedy);
        System.out.println("Number of movies with more than 150 min: "
                            +nTimeGreaterThan150);
        System.out.println("Max Number of movies directed by the same director: "
                            +maxDirectedMovies);
        for (Map.Entry<String, Long> pair : movieDirectedCounter.entrySet()) {
            if (pair.getValue() >= maxDirectedMovies) 
            System.out.print(pair.getKey() + ", ");
        }
    }
    
    /**
     * This method should process every record from the CSV file whose
     * name is filename, a file of raters and their ratings, and return an 
     * ArrayList of type Rater with all the rater data from the file. 
     */
    public static ArrayList<Rater> loadRaters(String filename) {
        TreeMap<String, Rater> raterList = new TreeMap<>();
        FileResource fr = new FileResource("data/"+filename);
        for (CSVRecord record : fr.getCSVParser()) {
            String id = record.get("rater_id");
            Rater rater = raterList.get(id);
            if (rater == null) {
                rater = new EfficientRater(id);
                raterList.put(id, rater);
            }
            rater.addRating(record.get("movie_id"), 
                            Double.parseDouble(record.get("rating")));
        }
        return new ArrayList<Rater>(raterList.values());
    }
    
    /**
     * Test loadRaters, duh.
     *
     * call loadRaters for data/ratings_short.CSV and/or data/ratings.CSV
     * and do a bunch of stuff
     * 
     */
    public static void testLoadRaters() {
        //ArrayList<Rater> raterList = loadRaters("data/ratings_short.CSV");
        ArrayList<Rater> raterList = loadRaters("data/ratings.CSV");
        System.out.println("Total number of raters: " + raterList.size());
        // for (Rater r : raterList) {
            // System.out.println("\nRater ID: "+r.getID()
                                // +", number of ratings: "+r.numRatings());
            // r.getItemsRated().forEach(item -> System.out.print(r.getRating(item)+ " "));
            // System.out.println();
        // }
        String rater_id = "193";
        int max_ratings = 0;
        for(Rater r : raterList) {
            if (r.getID().equals(rater_id)) {
                System.out.println("The number of ratins of rater "
                    +rater_id+ " is " + r.numRatings());
            }
            max_ratings = Math.max(max_ratings, r.numRatings());
        }
        int num_maxRaters = 0;
        ArrayList<String> maxRaters = new ArrayList<>();
        for(Rater r : raterList) {
            if (r.numRatings() == max_ratings) {
                num_maxRaters++;
                maxRaters.add(r.getID());
            }
        }
        System.out.println("We have "+ num_maxRaters+" max raters. They are:");
        maxRaters.forEach(r->System.out.print(r + " "));
        System.out.println("\n with "+ max_ratings+" rates");
        System.out.println();
        String movieID = "1798709";
        int movieNumRating = 0;
        for (Rater r : raterList) {
            if (r.hasRating(movieID)) movieNumRating++;
        }
        System.out.println("The movie "+movieID+" have "+movieNumRating+" raters");
        Set<String> moviesRated = new TreeSet<>();
        for (Rater r : raterList) {
            for (String movie: r.getItemsRated()) {
                moviesRated.add(movie);
            }
        }
        System.out.println("In total "+ moviesRated.size()+" movies were rated");
    }
}



import java.util.*;
/**
 * Test class for FourthRatings.java
 * 
 * @author Rafael Zuolo Coppini Lima
 * @version 07/09/2021
 */
public class MovieRunnerSimilarRatings {
    /**
     * Simple test for FourthRatings
     */
    public static void printAverageRatings() {
        // first item
        //FourthRatings tr = new FourthRatings("ratings_short.csv");
        FourthRatings tr = new FourthRatings();
        //MovieDatabase.initialize("ratedmovies_short.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");
        
        System.out.println("Number of raters: "+RaterDatabase.size());
        System.out.println("Number of movies: "+MovieDatabase.size());
        // second item
        ArrayList<Rating> aveRates = tr.getAverageRatings(35);
        System.out.println("Movies in list: "+aveRates.size());
        aveRates.sort((r,s)->r.compareTo(s));
        aveRates.forEach(r->System.out.println(r.getValue()+" " 
                                +MovieDatabase.getTitle(r.getItem())));
    }
    /**
     * Another simple test for ThirdRatings and the AllFilters filter
     */
    public static void printAverageRatingsByYearAfterAndGenre() {
        // first item
        //FourthRatings tr = new ThirdRatings("ratings_short.csv");
        FourthRatings tr = new FourthRatings();
        //MovieDatabase.initialize("ratedmovies_short.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");
        AllFilters filter = new AllFilters();
        filter.addFilter(new YearAfterFilter(1990));
        filter.addFilter(new GenreFilter("Drama"));
        System.out.println("Raters loaded: "+RaterDatabase.size());
        System.out.println("Movies loaded: "+MovieDatabase.size());
        // second item
        ArrayList<Rating> aveRates = tr.getAverageRatingsByFilter(8, filter);
        System.out.println("Movies filtered: "+aveRates.size());
        aveRates.sort((r,s)->r.compareTo(s));
        // aveRates.forEach(r->{
                                // System.out.println(r.getValue()+" "+MovieDatabase.getYear(r.getItem())+" "
                                        // +MovieDatabase.getTitle(r.getItem()));
                                // System.out.println("\t"+MovieDatabase.getGenres(r.getItem()));
                            // });
    }
    
    /**
     * Tester of getSimilarRatings.
     *
     * @param  raterID  id of rater we want to recommend to
     * @param  numRaters number of similar raters we will consider
     * @param  minRaters min number of raters a movie must have to be considered
     * @return    void
     */
    public static void printSimilarRatings (String raterID, int numRaters, int minRaters) {
        //FourthRatings tr = new ThirdRatings("ratings_short.csv");
        FourthRatings fr = new FourthRatings();
        //MovieDatabase.initialize("ratedmovies_short.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");
        //RaterDatabase.initialize("ratings_short.csv");
        ArrayList<Rating> similarMovies = fr.getSimilarRatings(raterID, numRaters,minRaters);
        System.out.println("The top 15 movies are: ");
        for (int i = 0; i < 15 && i< similarMovies.size(); i++) {
            System.out.println((i+1) +"th " +MovieDatabase.getTitle(similarMovies.get(i).getItem())
                +" with rating "+ similarMovies.get(i).getValue());
        }
    }
    
    /**
     * Tester of getSimilarRatingsByFilter.
     *
     * @param  raterID  id of rater we want to recommend to
     * @param  numRaters number of similar raters we will consider
     * @param  minRaters min number of raters a movie must have to be considered
     * @return    void
     */
    public static void printSimilarRatingsByGenre (String raterID, int numRaters, int minRaters, String genre) {
        //FourthRatings tr = new ThirdRatings("ratings_short.csv");
        FourthRatings fr = new FourthRatings();
        //MovieDatabase.initialize("ratedmovies_short.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");
        //RaterDatabase.initialize("ratings_short.csv");
        ArrayList<Rating> similarMovies = fr.getSimilarRatingsByFilter(raterID, 
                                            numRaters,minRaters, new GenreFilter(genre));
        System.out.println("The top 15 "+genre+" movies are: ");
        for (int i = 0; i < 15 && i< similarMovies.size(); i++) {
            System.out.println((i+1) +"th " +MovieDatabase.getTitle(similarMovies.get(i).getItem())
                +" with rating "+ similarMovies.get(i).getValue());
        }
    }
    /**
     * Tester of getSimilarRatingsByFilter.
     *
     * @param  raterID  id of rater we want to recommend to
     * @param  numRaters number of similar raters we will consider
     * @param  minRaters min number of raters a movie must have to be considered
     * @return    void
     */
    public static void printSimilarRatingsByDirector (String raterID, int numRaters, int minRaters, String director) {
        //FourthRatings tr = new ThirdRatings("ratings_short.csv");
        FourthRatings fr = new FourthRatings();
        //MovieDatabase.initialize("ratedmovies_short.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");
        //RaterDatabase.initialize("ratings_short.csv");
        ArrayList<Rating> similarMovies = fr.getSimilarRatingsByFilter(raterID, 
                                            numRaters,minRaters, new DirectorsFilter(director));
        System.out.println("The top 15 movies directed by " + director+" are: ");
        for (int i = 0; i < 15 && i< similarMovies.size(); i++) {
            System.out.println((i+1) +"th " +MovieDatabase.getTitle(similarMovies.get(i).getItem())
                +" with rating "+ similarMovies.get(i).getValue());
        }
    }
    /**
     * Tester of getSimilarRatingsByFilter.
     *
     * @param  raterID  id of rater we want to recommend to
     * @param  numRaters number of similar raters we will consider
     * @param  minRaters min number of raters a movie must have to be considered
     * @return    void
     */
    public static void printSimilarRatingsByGenreAndMinutes​ (String raterID, int numRaters, int minRaters, String genre, int min, int max) {
        //FourthRatings tr = new ThirdRatings("ratings_short.csv");
        FourthRatings fr = new FourthRatings();
        //MovieDatabase.initialize("ratedmovies_short.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");
        //RaterDatabase.initialize("ratings_short.csv");
        AllFilters filter = new AllFilters();
        filter.addFilter(new GenreFilter(genre));
        filter.addFilter(new MinutesFilter(min, max));
        ArrayList<Rating> similarMovies = fr.getSimilarRatingsByFilter(raterID, 
                                            numRaters,minRaters, filter);
        System.out.println("The top 15 " +genre+" movies with time restriction by are: ");
        for (int i = 0; i < 15 && i< similarMovies.size(); i++) {
            System.out.println((i+1) +"th " +MovieDatabase.getTitle(similarMovies.get(i).getItem())
                +" with rating "+ similarMovies.get(i).getValue());
        }
    }
    
    /**
     * Tester of getSimilarRatingsByFilter.
     *
     * @param  raterID  id of rater we want to recommend to
     * @param  numRaters number of similar raters we will consider
     * @param  minRaters min number of raters a movie must have to be considered
     * @return    void
     */
    public static void printSimilarRatingsByYearsAfterAndMinutes(String raterID, int numRaters, int minRaters, int year, int min, int max) {
        //FourthRatings tr = new ThirdRatings("ratings_short.csv");
        FourthRatings fr = new FourthRatings();
        //MovieDatabase.initialize("ratedmovies_short.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");
        //RaterDatabase.initialize("ratings_short.csv");
        AllFilters filter = new AllFilters();
        filter.addFilter(new YearAfterFilter(year));
        filter.addFilter(new MinutesFilter(min, max));
        ArrayList<Rating> similarMovies = fr.getSimilarRatingsByFilter(raterID, 
                                            numRaters,minRaters, filter);
        System.out.println("The top 15 movies of "+year+" or later are directed by are: ");
        for (int i = 0; i < 15 && i< similarMovies.size(); i++) {
            System.out.println((i+1) +"th " +MovieDatabase.getTitle(similarMovies.get(i).getItem())
                +" with rating "+ similarMovies.get(i).getValue());
        }
    }
}

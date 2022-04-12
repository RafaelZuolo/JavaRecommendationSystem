import java.util.*;
/**
 * Test class for ThirdRatings
 * 
 * @author Rafael Zuollo Coppini Lima 
 * @version 05/09/2021
 */

public class MovieRunnerWithFilters {
    /**
     * Simple test for ThirdRatings
     */
    public static void printAverageRatings() {
        // first item
        //ThirdRatings tr = new ThirdRatings("ratings_short.csv");
        ThirdRatings tr = new ThirdRatings();
        //MovieDatabase.initialize("ratedmovies_short.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        
        System.out.println("Number of raters: "+tr.getRaterSize());
        System.out.println("Number of movies: "+MovieDatabase.size());
        // second item
        ArrayList<Rating> aveRates = tr.getAverageRatings(35);
        System.out.println("Movies in list: "+aveRates.size());
        aveRates.sort((r,s)->r.compareTo(s));
        aveRates.forEach(r->System.out.println(r.getValue()+" " 
                                        +MovieDatabase.getTitle(r.getItem())));
    }
    
    /**
     * Another simple test for ThirdRatings and the YearAfter filter
     */
    public static void printAverageRatingsByYearAfter() {
        // first item
        //ThirdRatings tr = new ThirdRatings("ratings_short.csv");
        ThirdRatings tr = new ThirdRatings();
        //MovieDatabase.initialize("ratedmovies_short.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        Filter filter = new YearAfterFilter(2000);
        System.out.println("Raters loaded: "+tr.getRaterSize());
        System.out.println("Movies loaded: "+MovieDatabase.size());
        // second item
        ArrayList<Rating> aveRates = tr.getAverageRatingsByFilter(20, filter);
        System.out.println("Movies filtered: "+aveRates.size());
        aveRates.sort((r,s)->r.compareTo(s));
        aveRates.forEach(r->System.out.println(r.getValue()+" " 
                                        +MovieDatabase.getTitle(r.getItem())));
    }
    
    /**
     * Another simple test for ThirdRatings and the Genre filter
     */
    public static void printAverageRatingsByGenre() {
        // first item
        //ThirdRatings tr = new ThirdRatings("ratings_short.csv");
        ThirdRatings tr = new ThirdRatings();
        //MovieDatabase.initialize("ratedmovies_short.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        Filter filter = new GenreFilter("Comedy");
        System.out.println("Raters loaded: "+tr.getRaterSize());
        System.out.println("Movies loaded: "+MovieDatabase.size());
        // second item
        ArrayList<Rating> aveRates = tr.getAverageRatingsByFilter(20, filter);
        System.out.println("Movies filtered: "+aveRates.size());
        aveRates.sort((r,s)->r.compareTo(s));
        aveRates.forEach(r->{
                                System.out.println(r.getValue()+" " 
                                        +MovieDatabase.getTitle(r.getItem()));
                                System.out.println("\t"+MovieDatabase.getGenres(r.getItem()));
                            });
    }
    
    /**
     * Another simple test for ThirdRatings and the MinutesFilter filter
     */
    public static void printAverageRatingsByMinutes() {
        // first item
        //ThirdRatings tr = new ThirdRatings("ratings_short.csv");
        ThirdRatings tr = new ThirdRatings();
        //MovieDatabase.initialize("ratedmovies_short.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        Filter filter = new MinutesFilter(105, 135);
        System.out.println("Raters loaded: "+tr.getRaterSize());
        System.out.println("Movies loaded: "+MovieDatabase.size());
        // second item
        ArrayList<Rating> aveRates = tr.getAverageRatingsByFilter(5, filter);
        System.out.println("Movies filtered: "+aveRates.size());
        aveRates.sort((r,s)->r.compareTo(s));
        // aveRates.forEach(r-> System.out.println(r.getValue() 
                             // +" "+MovieDatabase.getMinutes(r.getItem())
                             // +" "+MovieDatabase.getTitle(r.getItem()))
                            // );
    }
    /**
     * Another simple test for ThirdRatings and the DirectorsFilter filter
     */
    public static void printAverageRatingsByDirectors() {
        // first item
        //ThirdRatings tr = new ThirdRatings("ratings_short.csv");
        ThirdRatings tr = new ThirdRatings();
        //MovieDatabase.initialize("ratedmovies_short.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        Filter filter = new DirectorsFilter("Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack");
        System.out.println("Raters loaded: "+tr.getRaterSize());
        System.out.println("Movies loaded: "+MovieDatabase.size());
        // second item
        ArrayList<Rating> aveRates = tr.getAverageRatingsByFilter(4, filter);
        System.out.println("Movies filtered: "+aveRates.size());
        aveRates.sort((r,s)->r.compareTo(s));
        // aveRates.forEach(r->{
                                // System.out.println(r.getValue()+" " 
                                        // +MovieDatabase.getTitle(r.getItem()));
                                // System.out.println("\t"+MovieDatabase.getDirector(r.getItem()));
                            // });
    }
    
    /**
     * Another simple test for ThirdRatings and the AllFilters filter
     */
    public static void printAverageRatingsByYearAfterAndGenre() {
        // first item
        //ThirdRatings tr = new ThirdRatings("ratings_short.csv");
        ThirdRatings tr = new ThirdRatings();
        //MovieDatabase.initialize("ratedmovies_short.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        AllFilters filter = new AllFilters();
        filter.addFilter(new YearAfterFilter(1990));
        filter.addFilter(new GenreFilter("Drama"));
        System.out.println("Raters loaded: "+tr.getRaterSize());
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
     * Another simple test for ThirdRatings and the AllFilters filter
     */
    public static void printAverageRatingsByDirectorsAndMinutes() {
        // first item
        //ThirdRatings tr = new ThirdRatings("ratings_short.csv");
        ThirdRatings tr = new ThirdRatings();
        //MovieDatabase.initialize("ratedmovies_short.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        AllFilters filter = new AllFilters();
        filter.addFilter(new DirectorsFilter("Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack"));
        filter.addFilter(new MinutesFilter( 90,180));
        System.out.println("Raters loaded: "+tr.getRaterSize());
        System.out.println("Movies loaded: "+MovieDatabase.size());
        // second item
        ArrayList<Rating> aveRates = tr.getAverageRatingsByFilter(3, filter);
        System.out.println("Movies filtered: "+aveRates.size());
        aveRates.sort((r,s)->r.compareTo(s));
        // aveRates.forEach(r->
        // {
            // System.out.println(r.getValue()+" Time: "+MovieDatabase.getMinutes(r.getItem())+" "
                // +MovieDatabase.getTitle(r.getItem()));
            // System.out.println("\t"+MovieDatabase.getDirector(r.getItem()));
        // });
    }
}

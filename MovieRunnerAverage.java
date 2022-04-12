import java.util.*;

/**
 * you will use to test the methods you created in 
 * SecondRatings by creating a SecondRatings object in 
 * MovieRunnerAverage and calling its methods.
 * 
 * @author Rafael Zuolo Coppini Lima
 * @version 04/09/2021
 */
public class MovieRunnerAverage {
    
    /**
     * 1 - Create a SecondRatings object and use the CSV filenames 
     * of movie information and ratings information from the 
     * first assignment when calling the constructor.  
     * Print the number of movies and number of raters from 
     * the two files by calling the appropriate methods in 
     * the SecondRatings class. Test your program to make 
     * sure it is reading in all the data from the two files.
     * 
     * 2 - print a list of movies and their average ratings, for 
     * all those movies that have at least a specified number 
     * of ratings, sorted by averages. Specifically, this 
     * method should print the list of movies, one movie per 
     * line (print its rating first, followed by its title) 
     * in ​sorted order​ by ratings, lowest rating to 
     * highest rating
     *
     */
    public static void printAverageRatings() {
        // first item
        //SecondRatings secondRatings = new SecondRatings("data/ratedmovies_short.csv", "data/ratings_short.csv");
        SecondRatings secondRatings = new SecondRatings();
        
        System.out.println("Number of movies: "+secondRatings.getMovieSize()
                            +". Number of raters: "+secondRatings.getRaterSize()+".");
        // second item
        ArrayList<Rating> aveRates = secondRatings.getAverageRatings(12);
        System.out.println("Number of movies is "+aveRates.size());
        aveRates.sort((r,s)->r.compareTo(s));
        aveRates.forEach(r->System.out.println(r.getValue()+" " 
                                        +secondRatings.getTitle(r.getItem())));
    }

    /**
     * tester of SecondRatings
     *
     */
    public static void getAverageRatingOneMovie() {
        //SecondRatings sr = new SecondRatings("data/ratedmovies_short.csv", "data/ratings_short.csv");
        SecondRatings sr = new SecondRatings();
        ArrayList<Rating> aveRates = sr.getAverageRatings(3);
        String movieTitle = "Vacation";
        String movieID = sr.getID(movieTitle);
        for (Rating r : aveRates) {
            if (r.getItem().equals(movieID)) {
                System.out.println("The average rate of "+movieTitle+" is "+r.getValue());
                break;
            }
        }
    }

}


import java.util.*;

/**
 * Refactorization of SecondRatings, with filters 
 * and other nice stuff added
 * 
 * @author Rafael Zuolo Coppini Lima
 * @version 05/09/2021
 */
public class ThirdRatings {

    private ArrayList<Rater> myRaters;
    
    public ThirdRatings(String ratingsfile) {
        this.myRaters = FirstRatings.loadRaters(ratingsfile);
    }
    
    public ThirdRatings() {
        // default constructor
        this("ratings.csv");
    }
    
    /**
     * Return the number of raters
     *
     * @return     the size of the ArrayList myRaters
     */
    public int getRaterSize() {
        return this.myRaters.size();
    }
    
    /**
     * This method returns a double representing the average movie rating for 
     * this ID if there are at least ​minimalRaters​ ratings
     *
     * @param   id   movie ID
     * @param   minimalRaters number of minimal raters
     * @return     the average movie rating for this ID if there 
     *             are at least ​minimalRaters​ ratings. If there 
     *             are not ​minimalRaters ratings, then it 
     *             returns 0.0
     */
    private double getAverageByID(String id, int minimalRaters) {
        double sum = 0;
        int raters = 0;
        for (Rater r : myRaters) {
            double rate = r.getRating(id);
            if (rate != -1.0) {
                sum += rate;
                raters++;
            }
        }
        if (raters < minimalRaters) return 0.0;
        else return sum/raters;
    }
    
    /**
     * This method should find the average rating for every movie 
     * that has been rated by at least ​minimalRaters​ raters
     *
     * @param  minimalRaters   
     * @return     list of average ratings
     */
    public ArrayList<Rating> getAverageRatings(int minimalRaters) {
        Filter f = new TrueFilter();
        MovieDatabase.initialize("ratedmovies_short.csv");
        //MovieDatabase.initialize("ratedmoviesfull.csv");
        ArrayList<String> movies = MovieDatabase.filterBy(f); 
        ArrayList<Rating> averageRating = new ArrayList<>();
        for (String movieID : movies) {
            double averageRate = getAverageByID(movieID, minimalRaters);
            if (averageRate > 0.0) {
                averageRating.add(new Rating(movieID, averageRate));
            }
        }
        return averageRating;
    }
    
    /**
     * Return an ArrayList of Rating of the movies that satisfy the filterCriteria
     *
     * @param minimalRaters   number of raters that a movie must have to be in the list     
     * @param filterCriteria   the filter applied to the database to select the movies
     * @return     an ArrayList of the movies that have sufficient raters and satisfy the filter criteria
     */
    public  ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria) {
        ArrayList<String> moviesFiltered = MovieDatabase.filterBy(filterCriteria);
        ArrayList<Rating> rates = new ArrayList<Rating>();
        for (String movieID : moviesFiltered) {
            double averageRate = getAverageByID(movieID, minimalRaters);
            if (averageRate > 0.0) {
                rates.add(new Rating(movieID, averageRate));
            }
        }
        return rates;
    }
}

import java.util.*;

/**
 * In this assignment you will modify a new class 
 * named ​SecondRatings​, which has been started 
 * for you, to do many of the calculations focusing 
 * on computing averages on movie ratings. 
 * You will also create a second new class 
 * named ​MovieRunnerAverage​, which you will use to test
 * the methods you created in SecondRatings by creating 
 * a SecondRatings object in MovieRunnerAverage and 
 * calling its methods
 * 
 * @author Rafael Zuolo Coppini Lima
 * @version 04/09/2021
 */



public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public SecondRatings(String moviefile, String ratingsfile) {
        this.myMovies = FirstRatings.loadMovies(moviefile);
        this.myRaters = FirstRatings.loadRaters(ratingsfile);
    }
    
    public SecondRatings() {
        // default constructor
        this("data/ratedmoviesfull.csv", "data/ratings.csv");
    }
    
    /**
     * Return the number of movies
     *
     * @return     the size of myMovies
     */
    public int getMovieSize() {
        // put your code here
        return this.myMovies.size();
    }
    
    /**
     * Return the number of raters
     *
     * @return     the size of myRaters
     */
    public int getRaterSize() {
        // put your code here
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
            if (rate != -1) {
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
        ArrayList<Rating> averageRating = new ArrayList<>();
        for (Movie m : myMovies) {
            double averageRate = getAverageByID(m.getID(), minimalRaters);
            if (averageRate > 0.0) {
                averageRating.add(new Rating(m.getID(), averageRate));
            }
        }
        return averageRating;
    }

    /**
     * This method returns the title of the movie with that ID
     * If the id doesn't exists, return null
     *
     * @param  id   id of Movie
     * @return     the title of the movie with that id. If not found, return null
     */
    public String getTitle(String id) {
        for (Movie m : myMovies) {
            if (m.getID().equals(id)) return m.getTitle();
        }
        return null;
    }

    /**
     * This method returns the ID of the movie with that title
     * If the title doesn't exists, return null
     *
     * @param  title   title of the Movie
     * @return     the id of the movie with that title. 
     *              If not found, return null
     */
    public String getID(String title) {
        for (Movie m : myMovies) {
            if (m.getTitle().equals(title)) return m.getID();
        }
        System.out.println("NO SUCH TITLE");
        return null;
    }

}

import java.util.*;
/**
 * Refactorization of ThirdRatings, with more
 * nice stuff added. Now we use the dot product 
 * method to obtain movies with similar ratings,
 * and thus similar ''taste'' as the user.
 * There are some caveats to this implementations.
 * We are not normalizing the ''rate vector'' of
 * each user, therefore raters that have more movies
 * rated may bias our choice for the ''top similar rater''.
 * 
 * @author Rafael Zuolo Coppini Lima
 * @version 06/09/2021
 */

public class FourthRatings {
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
        for (Rater r : RaterDatabase.getRaters()) {
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
        //MovieDatabase.initialize("ratedmovies_short.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
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
    
    /**
     * This method should first translate a rating from the 
     * scale 0 to 10 to the scale ­5 to 5 and return the 
     * dot product of the ratings of movies that they both rated.
     *
     * @param  me    Rater being compared
     * @param  other Rater being compared
     * @return     the scaled dot product of the ratins of me and other
     */
    private double dotProduct(Rater me, Rater other) {
        // put your code here
        double product = 0.0;
        ArrayList<String> myRates = me.getItemsRated();
        for (String id : myRates) {
            if (other.hasRating(id)) {
                product += (me.getRating(id)-5)*(other.getRating(id)-5);
            }
        }
        return product;
    }
    
    /**
     * This method computes a similarity rating for each rater in the 
     * RaterDatabase (except the rater with the ID given by the parameter) 
     * to see how similar they are to the Rater given.
     * Returns an ArrayList of type Rating ​sorted​ by ratings 
     * from highest to lowest rating with the highest rating 
     * first and only including those raters who have a positive 
     * similarity rating since those with negative values are not 
     * similar in any way.
     *
     * @param  id  ID of the Rater whose similarity we want
     * @return     Sorted list of Rating, with positive similarities as value and Rater ID as item
     */
    private ArrayList<Rating> getSimilarities(String id) {
        Rater me = RaterDatabase.getRater(id);
        ArrayList<Rating> similarities = new ArrayList<>();
        for (Rater other : RaterDatabase.getRaters()) {
            if (other.getID().equals(me.getID())) continue; // they will point to the same object, so == is okay
            double similarity = dotProduct(me, other);
            if (similarity > 0) similarities.add(new Rating(other.getID(), similarity));
        }
        similarities.sort(Collections.reverseOrder());
        return similarities;
    }

    /**
     * This method should return an ArrayList of type Rating, 
     * of movies and their weighted average ratings using only 
     * the top numSimilarRaters​ with positive ratings and 
     * including only those movies that have at least ​minimalRaters​ 
     * ratings from those top raters. These Rating objects should 
     * be returned in sorted order by weighted average rating 
     * from largest to smallest ratings. The weight will be
     * decided by the similarity of the rates between id and
     * the others raters.
     *
     * @param  id               the rater we want to find the top recommendations
     * @param numSimilarRaters  number of top similar raters to be considered
     * @param minimalRaters     number of rates a movie must have to be considered
     * @return                  the top recommendations for rater id
     */
    public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters) {
        ArrayList<Rating> recommendations = new ArrayList<>();
        ArrayList<Rating> temp = getSimilarities(id);
        ArrayList<Rating> similarRaters = new ArrayList(temp.subList(0, Math.min(numSimilarRaters,temp.size())));
        ArrayList<String> moviesID = MovieDatabase.filterBy(new TrueFilter()); // get all movies ID
        // now we iterate through all movies to get the weighted rate
        for (String movieID : moviesID) {
            double totalRate = 0.0;
            int numberOfRaters = 0;
            for (Rating r : similarRaters) {
                // remember, r.item = rater, r.value = similarity with rater id
                Rater rater = RaterDatabase.getRater(r.getItem());
                if (!rater.hasRating(movieID)) continue;
                totalRate += rater.getRating(movieID)*r.getValue();
                numberOfRaters++;
            }
            if (numberOfRaters >= minimalRaters) {
                recommendations.add(new Rating(movieID, totalRate/numberOfRaters));
            }
        }
        recommendations.sort(Collections.reverseOrder());
        return recommendations; 
    }
    
    /**
     * This method should return an ArrayList of type Rating, 
     * of movies and their weighted average ratings using only 
     * the top numSimilarRaters​ with positive ratings and 
     * including only those movies that have at least ​minimalRaters​ 
     * ratings from those top raters. These Rating objects should 
     * be returned in sorted order by weighted average rating 
     * from largest to smallest ratings. The weight will be
     * decided by the similarity of the rates between id and
     * the others raters.
     *
     * @param  id               the rater we want to find the top recommendations
     * @param numSimilarRaters  number of top similar raters that will be considered
     * @param minimalRaters     number of rates a movie must have to be considered
     * @param filterCriteria    filter that the movies must satisfy to be considered
     * @return                  the top recommendations for rater id
     */
    public ArrayList<Rating> getSimilarRatingsByFilter(String id, int numSimilarRaters, 
                                                        int minimalRaters, Filter filterCriteria) {
        ArrayList<Rating> recommendations = new ArrayList<>();
        ArrayList<Rating> similarRaters = new ArrayList(getSimilarities(id).subList(0, numSimilarRaters));
        ArrayList<String> moviesID = MovieDatabase.filterBy(filterCriteria); // get filtered movies ID
        // now we iterate through all movies to get the weighted rate
        for (String movieID : moviesID) {
            double totalRate = 0.0;
            int numberOfRaters = 0;
            for (Rating r : similarRaters) {
                // remember, r.item = rater, r.value = similarity with rater id
                Rater rater = RaterDatabase.getRater(r.getItem());
                if (!rater.hasRating(movieID)) continue;
                totalRate += rater.getRating(movieID)*r.getValue();
                numberOfRaters++;
            }
            if (numberOfRaters >= minimalRaters) {
                recommendations.add(new Rating(movieID, totalRate/numberOfRaters));
            }
        }
        recommendations.sort(Collections.reverseOrder());
        return recommendations;
    }
}

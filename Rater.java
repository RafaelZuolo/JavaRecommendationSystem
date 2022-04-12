import java.util.*;
/**
 * Interface of a class that collects all rates of a 
 * rater with given id.
 * 
 * @author Rafael Zuolo Coppini Lima
 * @version 05/09/2021
 */

public interface Rater {
    /**
     *  Add a rating of the item. If the item was already
     *  rated, overwrite the rate
     */
    public void addRating(String item, double rating);
    
    /**
     * return true if item has a rating. False otherwise
     */
    public boolean hasRating(String item);
    
    /**
     * return the unique ID of this rater
     */
    public String getID();
    
    /**
     * return the rating of the item
     */
    public double getRating(String item);
    
    /**
     * Return the number of items rated
     */
    public int numRatings();
    
    /**
     *  Return a ArrayList of the ID of the items rated
     *  
     *  @return list of the IDs of items
     */
    public ArrayList<String> getItemsRated();
}

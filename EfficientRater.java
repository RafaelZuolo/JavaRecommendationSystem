import java.util.*;
/**
 * ArrayList implementation of Rater that collects all rates of a 
 * rater with given id.
 * @author Rafael Zuolo Coppini Lima
 * @version 05/09/2021
 */

public class EfficientRater implements Rater{
    private String myID;
    private HashMap<String,Rating> myRatings;

    public EfficientRater(String id) {
        myID = id;
        myRatings = new HashMap<String,Rating>();
    }

    public void addRating(String item, double rating) {
        myRatings.put(item, new Rating(item,rating));
    }

    public boolean hasRating(String item) {
        if (myRatings.get(item) != null){
            return true;
        }
        return false;
    }

    public String getID() {
        return myID;
    }

    public double getRating(String item) {
        Rating r = myRatings.get(item);
        if (r == null) return -1.0;
        else           return r.getValue();
    }

    public int numRatings() {
        return myRatings.size();
    }

    public ArrayList<String> getItemsRated() {
        return new ArrayList<String>(myRatings.keySet());
    }
}




import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;
/**
 * This class represents a "database" for raters. Here
 * any rater can be acessed efficiently using HashMap 
 * after loading the .CSV into the memory of the computer.
 * All methods are static.
 * 
 * @author Rafael Zuolo Coppini Lima 
 * @version 06/09/2021
 */

public class RaterDatabase {
    private static HashMap<String,Rater> ourRaters;
     
    private static void initialize() {
        // this method is only called from addRatings 
        if (ourRaters == null) {
            ourRaters = new HashMap<String,Rater>();
        }
    }
    /**
     * Load the .CSV database named "filename" in
     * the memory.
     */
    public static void initialize(String filename) {
        if (ourRaters == null) {
            ourRaters= new HashMap<String,Rater>();
            addRatings("data/" + filename);
        }
    }   
    /**
     * Add the .CSV database named "filename" in
     * the memory. If the database is not initialized, 
     * initialize it with the current filename. Else
     * add more data to the current database.
     */
    public static void addRatings(String filename) {
        initialize(); 
        FileResource fr = new FileResource(filename);
        CSVParser csvp = fr.getCSVParser();
        for(CSVRecord rec : csvp) {
                String id = rec.get("rater_id");
                String item = rec.get("movie_id");
                String rating = rec.get("rating");
                addRaterRating(id,item,Double.parseDouble(rating));
        } 
    }
    
    /**
     * Add a single rating from a rater. If it's the first
     * rating of this rater, load him to the memory.
     */
    public static void addRaterRating(String raterID, String movieID, double rating) {
        initialize(); 
        Rater rater =  null;
        if (ourRaters.containsKey(raterID)) {
            rater = ourRaters.get(raterID); 
        } 
        else { 
            rater = new EfficientRater(raterID);
            ourRaters.put(raterID,rater);
        }
        rater.addRating(movieID,rating);
    } 
    /**
     * Return the rater with ID id.
     */         
    public static Rater getRater(String id) {
        initialize();
        
        return ourRaters.get(id);
    }
    /**
     * return a list of all raters current loaded
     */
    public static ArrayList<Rater> getRaters() {
        initialize();
        ArrayList<Rater> list = new ArrayList<Rater>(ourRaters.values());
            
        return list;
    }
    /**
     * Return the number of raters loaded.
     */
    public static int size() {
        return ourRaters.size();
    }
    /**
     * NOT IMPLEMENTED: print a CSV of the current raters loaded
     */
    public static void dumpCurrentDatabase(String filename) {
        System.out.println("NOT IMPLEMENTED");
        return;
    }
        
}

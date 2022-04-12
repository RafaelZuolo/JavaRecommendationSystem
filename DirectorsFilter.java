import java.util.Arrays;

/**
 *  Filter that select movies based on it's directors.
 * A movie satisfies​this method it has at least one of 
 * the provided directors as one of its directors. 
 * Note that each movie may have several directors.
 * 
 * @author Rafael Zuolo Coppini Lima
 * @version 05/09/2021
 */
public class DirectorsFilter implements Filter{
    private String[] directors;
    /**
     * Constructor of the filter. The parameter should be a String with
     * a list of directors separated by commas. 
     * Example: "Charles Chaplin,Michael Mann,Spike Jonze"
     * 
     * @param directors   String with the list of directors separated by comma
     * 
     */
    public DirectorsFilter(String directors) {
        this.directors = directors.split(",");
    }
    
    @Override
    public boolean satisfies(String id) {
        String[] movieDirectors = MovieDatabase.getDirector(id).split(",");
        /* Since the number of directors rarely is higher than 10, we
         * will use a quadratic low-overhead cost implementation
         */ 
        for (String director : directors) {
            for (String movieDirector : movieDirectors) {
                if (director.trim().equals(movieDirector.trim())) {
                    return true;
                }
            }
        }
        return false;
    }
}
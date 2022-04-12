
/**
 *  Filter that select movies based on it's length.
 * A movie satisfy this filter if it's running time is less
 * than the min and at most the max
 * 
 * @author Rafael Zuolo Coppini Lima
 * @version 05/09/2021
 */
public class MinutesFilter implements Filter{
    private int min, max;
    
    public MinutesFilter(int min, int max) {
        this.min = min;
        this.max = max;
    }
    
    @Override
    public boolean satisfies(String id) {
        int movieLength = MovieDatabase.getMovie(id).getMinutes();
        return min <= movieLength && movieLength <= max;
    }
}

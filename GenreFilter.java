
/**
 * Filter that select movies based on it's genre.
 * A movie satisfy this filter if it contains the
 * genre given
 * 
 * 
 * @author Rafael Zuolo Coppini Lima
 * @version 05/09/2021
 */
public class GenreFilter implements Filter {
    String genre;
    public GenreFilter(String genre) {
        this.genre = genre;
    }
    
    @Override
    public boolean satisfies(String id) {
        String[] genres = MovieDatabase.getGenres(id).split(", ");
        for (String genre : genres) {
            if (genre.equals(this.genre)) return true;
        }
        return false;
    }
}

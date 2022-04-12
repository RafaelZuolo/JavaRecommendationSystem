
/**
 * This class is the assignemnt that will be sent to the website.
 *
 */
import java.util.*;
public class RecommendationRunner implements Recommender {
    /**
     * This method returns a list of movie IDs that will be used to look up 
     * the movies in the MovieDatabase and present them to users to rate. 
     *  
     * The movies returned in the list will be displayed on a web page, so
     * the number you choose may affect how long the page takes to load and
     * how willing users are to rate the movies.  For example, 10-20 should
     * be fine, 50 or more would be too many.
     * 
     * There are no restrictions on the method you use to generate this list
     * of movies: the most recent movies, movies from a specific genre, 
     * randomly chosen movies, or simply your favorite movies.
     * 
     * The ratings for these movies will make the profile for a new Rater 
     * that will be used to compare to for finding recommendations.
     */
    private static String bestMovie;
    private static int totalList= -1;
    @Override
    public ArrayList<String> getItemsToRate () {
        FourthRatings fr = new FourthRatings();
        AllFilters f = new AllFilters();
        f.addFilter(new YearAfterFilter(1990));
        ArrayList<Rating> ratedList = fr.getAverageRatingsByFilter(5, f);

        ArrayList<String> movieSelection = new ArrayList<>();
        for (int i = 0; i < 300 && i < ratedList.size(); i++) {
            movieSelection.add(ratedList.get(i).getItem());
            totalList += i;
        }
        bestMovie = movieSelection.get(0);
        Collections.shuffle(movieSelection);
        movieSelection = new ArrayList(movieSelection.subList(0,Math.min(15, movieSelection.size())));
        return movieSelection;
    }

    /**
     * This method returns nothing, but prints out an HTML table of the 
     * movies recommended for the given rater.
     * 
     * The HTML printed will be displayed on a web page, so the number you
     * choose to display may affect how long the page takes to load.  For 
     * example, you may want to limit the number printed to only the top 
     * 20-50 movies recommended or to movies not rater by the given rater.
     * 
     * You may also include CSS styling for your table using the &lt;style&gt;
     * tag before you print the table.  There are no restrictions on which 
     * movies you print, what order you print them in, or what information
     * you include about each movie. 
     * 
     * @param webRaterID the ID of a new Rater that has been already added to 
     *        the RaterDatabase with ratings for the movies returned by the 
     *        method getItemsToRate
     */
    @Override
    public void printRecommendationsFor (String webRaterID) {
        FourthRatings fr = new FourthRatings();
        List<Rating> webRecommendations = fr.getSimilarRatings(webRaterID, 20, 3);
        List<String> yourMoviesRatedID = RaterDatabase.getRater(webRaterID).getItemsRated();
        if (webRecommendations.size() == 0) {
            System.out.println("<h2>Not enough data. Please rate more movies</h2>");
            return;
        }
        System.out.println("<h1>Found "+webRecommendations.size()+" movies to recomend</h1>");
        System.out.println("<h2>Printing the first 15 or less movies you haven't rated</h2>");
        System.out.println("<div class=\"tablediv\">");
        System.out.println("<table>");
        int i = 0;
        for (Rating r : webRecommendations) {
            if (!yourMoviesRatedID.contains(r.getItem())) {
                String URL = "http://www.dukelearntoprogram.com/capstone/data/" + MovieDatabase.getPoster(r.getItem()).substring(7);
                System.out.println("<tr><td><img height=100 src=\""+URL+"\"/></td><td><h3>"
                                    +MovieDatabase.getTitle(r.getItem())+"</h3></td></tr>");
                i++;
            }
            if (i>= 15) break;
        }
        if (i == 0) System.out.println("<tr><td>Sorry, couldn't find movies you haven't watched</td></tr>");
        System.out.println("</table></html>");
        System.out.println("<style>.tablediv{width: 100%;margin: 0px 0px 0px -9px;padding: 2px 9px 0px 0px;}\ntable{margin:auto;}</style>");        
    }
}